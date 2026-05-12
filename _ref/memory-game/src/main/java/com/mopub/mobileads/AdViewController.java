package com.mopub.mobileads;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.cube.memorygames.games.Game1MemoryGridActivity;
import com.mopub.common.AdReport;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.mraid.MraidNativeCommandHandler;
import com.mopub.network.AdRequest;
import com.mopub.network.AdRequest.Listener;
import com.mopub.network.AdResponse;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.Networking;
import com.mopub.network.TrackingRequest;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Request;
import com.mopub.volley.VolleyError;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;

public class AdViewController {
    static final double BACKOFF_FACTOR = 1.5d;
    static final int DEFAULT_REFRESH_TIME_MILLISECONDS = 60000;
    static final int MAX_REFRESH_TIME_MILLISECONDS = 600000;
    private static final LayoutParams WRAP_AND_CENTER_LAYOUT_PARAMS = new LayoutParams(-2, -2, 17);
    private static final WeakHashMap<View, Boolean> sViewShouldHonorServerDimensions = new WeakHashMap();
    @Nullable
    private AdRequest mActiveRequest;
    @NonNull
    private final Listener mAdListener;
    @Nullable
    private AdResponse mAdResponse;
    @Nullable
    private String mAdUnitId;
    private boolean mAdWasLoaded;
    private boolean mAutoRefreshEnabled = true;
    @VisibleForTesting
    int mBackoffPower = 1;
    private final long mBroadcastIdentifier;
    @Nullable
    private Context mContext;
    private Handler mHandler;
    private boolean mIsDestroyed;
    private boolean mIsLoading;
    private boolean mIsTesting;
    private String mKeywords;
    private Map<String, Object> mLocalExtras = new HashMap();
    private Location mLocation;
    @Nullable
    private MoPubView mMoPubView;
    private boolean mPreviousAutoRefreshSetting = true;
    private final Runnable mRefreshRunnable;
    @Nullable
    private Integer mRefreshTimeMillis;
    private int mTimeoutMilliseconds;
    private String mUrl;
    @Nullable
    private WebViewAdUrlGenerator mUrlGenerator;

    public static void setShouldHonorServerDimensions(View view) {
        sViewShouldHonorServerDimensions.put(view, Boolean.valueOf(true));
    }

    private static boolean getShouldHonorServerDimensions(View view) {
        return sViewShouldHonorServerDimensions.get(view) != null;
    }

    public AdViewController(@NonNull Context context, @NonNull MoPubView moPubView) {
        this.mContext = context;
        this.mMoPubView = moPubView;
        this.mTimeoutMilliseconds = -1;
        this.mBroadcastIdentifier = Utils.generateUniqueId();
        this.mUrlGenerator = new WebViewAdUrlGenerator(this.mContext.getApplicationContext(), MraidNativeCommandHandler.isStorePictureSupported(this.mContext));
        this.mAdListener = new 1(this);
        this.mRefreshRunnable = new 2(this);
        this.mRefreshTimeMillis = Integer.valueOf(DEFAULT_REFRESH_TIME_MILLISECONDS);
        this.mHandler = new Handler();
    }

    @VisibleForTesting
    void onAdLoadSuccess(@NonNull AdResponse adResponse) {
        int i;
        this.mBackoffPower = 1;
        this.mAdResponse = adResponse;
        if (this.mAdResponse.getAdTimeoutMillis() == null) {
            i = this.mTimeoutMilliseconds;
        } else {
            i = this.mAdResponse.getAdTimeoutMillis().intValue();
        }
        this.mTimeoutMilliseconds = i;
        this.mRefreshTimeMillis = this.mAdResponse.getRefreshTimeMillis();
        setNotLoading();
        loadCustomEvent(this.mMoPubView, adResponse.getCustomEventClassName(), adResponse.getServerExtras());
        scheduleRefreshTimerIfEnabled();
    }

    @VisibleForTesting
    void onAdLoadError(VolleyError volleyError) {
        if (volleyError instanceof MoPubNetworkError) {
            MoPubNetworkError moPubNetworkError = (MoPubNetworkError) volleyError;
            if (moPubNetworkError.getRefreshTimeMillis() != null) {
                this.mRefreshTimeMillis = moPubNetworkError.getRefreshTimeMillis();
            }
        }
        MoPubErrorCode errorCodeFromVolleyError = getErrorCodeFromVolleyError(volleyError, this.mContext);
        if (errorCodeFromVolleyError == MoPubErrorCode.SERVER_ERROR) {
            this.mBackoffPower++;
        }
        setNotLoading();
        adDidFail(errorCodeFromVolleyError);
    }

    @VisibleForTesting
    void loadCustomEvent(@Nullable MoPubView moPubView, @Nullable String str, @NonNull Map<String, String> map) {
        Preconditions.checkNotNull(map);
        if (moPubView == null) {
            MoPubLog.d("Can't load an ad in this ad view because it was destroyed.");
        } else {
            moPubView.loadCustomEvent(str, map);
        }
    }

    @NonNull
    @VisibleForTesting
    static MoPubErrorCode getErrorCodeFromVolleyError(@NonNull VolleyError volleyError, @Nullable Context context) {
        NetworkResponse networkResponse = volleyError.networkResponse;
        if (volleyError instanceof MoPubNetworkError) {
            switch (4.$SwitchMap$com$mopub$network$MoPubNetworkError$Reason[((MoPubNetworkError) volleyError).getReason().ordinal()]) {
                case 1:
                    return MoPubErrorCode.WARMUP;
                case 2:
                    return MoPubErrorCode.NO_FILL;
                default:
                    return MoPubErrorCode.UNSPECIFIED;
            }
        } else if (networkResponse == null) {
            if (DeviceUtils.isNetworkAvailable(context)) {
                return MoPubErrorCode.UNSPECIFIED;
            }
            return MoPubErrorCode.NO_CONNECTION;
        } else if (volleyError.networkResponse.statusCode >= Game1MemoryGridActivity.START_ANIMATION_DURATION) {
            return MoPubErrorCode.SERVER_ERROR;
        } else {
            return MoPubErrorCode.UNSPECIFIED;
        }
    }

    @Nullable
    public MoPubView getMoPubView() {
        return this.mMoPubView;
    }

    public void loadAd() {
        this.mBackoffPower = 1;
        internalLoadAd();
    }

    private void internalLoadAd() {
        this.mAdWasLoaded = true;
        if (TextUtils.isEmpty(this.mAdUnitId)) {
            MoPubLog.d("Can't load an ad in this ad view because the ad unit ID is not set. Did you forget to call setAdUnitId()?");
        } else if (isNetworkAvailable()) {
            loadNonJavascript(generateAdUrl());
        } else {
            MoPubLog.d("Can't load an ad because there is no network connectivity.");
            scheduleRefreshTimerIfEnabled();
        }
    }

    void loadNonJavascript(String str) {
        if (str != null) {
            MoPubLog.d("Loading url: " + str);
            if (!this.mIsLoading) {
                this.mUrl = str;
                this.mIsLoading = true;
                fetchAd(this.mUrl);
            } else if (!TextUtils.isEmpty(this.mAdUnitId)) {
                MoPubLog.i("Already loading an ad for " + this.mAdUnitId + ", wait to finish.");
            }
        }
    }

    public void reload() {
        MoPubLog.d("Reload ad: " + this.mUrl);
        loadNonJavascript(this.mUrl);
    }

    void loadFailUrl(MoPubErrorCode moPubErrorCode) {
        this.mIsLoading = false;
        Log.v("MoPub", "MoPubErrorCode: " + (moPubErrorCode == null ? "" : moPubErrorCode.toString()));
        String failoverUrl = this.mAdResponse == null ? "" : this.mAdResponse.getFailoverUrl();
        if (TextUtils.isEmpty(failoverUrl)) {
            adDidFail(MoPubErrorCode.NO_FILL);
            return;
        }
        MoPubLog.d("Loading failover url: " + failoverUrl);
        loadNonJavascript(failoverUrl);
    }

    void setNotLoading() {
        this.mIsLoading = false;
        if (this.mActiveRequest != null) {
            if (!this.mActiveRequest.isCanceled()) {
                this.mActiveRequest.cancel();
            }
            this.mActiveRequest = null;
        }
    }

    public String getKeywords() {
        return this.mKeywords;
    }

    public void setKeywords(String str) {
        this.mKeywords = str;
    }

    public Location getLocation() {
        return this.mLocation;
    }

    public void setLocation(Location location) {
        this.mLocation = location;
    }

    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    public void setAdUnitId(@NonNull String str) {
        this.mAdUnitId = str;
    }

    public long getBroadcastIdentifier() {
        return this.mBroadcastIdentifier;
    }

    public int getAdWidth() {
        if (this.mAdResponse == null || this.mAdResponse.getWidth() == null) {
            return 0;
        }
        return this.mAdResponse.getWidth().intValue();
    }

    public int getAdHeight() {
        if (this.mAdResponse == null || this.mAdResponse.getHeight() == null) {
            return 0;
        }
        return this.mAdResponse.getHeight().intValue();
    }

    public boolean getAutorefreshEnabled() {
        return this.mAutoRefreshEnabled;
    }

    void pauseRefresh() {
        this.mPreviousAutoRefreshSetting = this.mAutoRefreshEnabled;
        setAutorefreshEnabled(false);
    }

    void unpauseRefresh() {
        setAutorefreshEnabled(this.mPreviousAutoRefreshSetting);
    }

    void forceSetAutorefreshEnabled(boolean z) {
        this.mPreviousAutoRefreshSetting = z;
        setAutorefreshEnabled(z);
    }

    private void setAutorefreshEnabled(boolean z) {
        Object obj = (!this.mAdWasLoaded || this.mAutoRefreshEnabled == z) ? null : 1;
        if (obj != null) {
            MoPubLog.d("Refresh " + (z ? "enabled" : "disabled") + " for ad unit (" + this.mAdUnitId + ").");
        }
        this.mAutoRefreshEnabled = z;
        if (this.mAdWasLoaded && this.mAutoRefreshEnabled) {
            scheduleRefreshTimerIfEnabled();
        } else if (!this.mAutoRefreshEnabled) {
            cancelRefreshTimer();
        }
    }

    @Nullable
    public AdReport getAdReport() {
        if (this.mAdUnitId == null || this.mAdResponse == null) {
            return null;
        }
        return new AdReport(this.mAdUnitId, ClientMetadata.getInstance(this.mContext), this.mAdResponse);
    }

    public boolean getTesting() {
        return this.mIsTesting;
    }

    public void setTesting(boolean z) {
        this.mIsTesting = z;
    }

    boolean isDestroyed() {
        return this.mIsDestroyed;
    }

    void cleanup() {
        if (!this.mIsDestroyed) {
            if (this.mActiveRequest != null) {
                this.mActiveRequest.cancel();
                this.mActiveRequest = null;
            }
            setAutorefreshEnabled(false);
            cancelRefreshTimer();
            this.mMoPubView = null;
            this.mContext = null;
            this.mUrlGenerator = null;
            this.mIsDestroyed = true;
        }
    }

    Integer getAdTimeoutDelay() {
        return Integer.valueOf(this.mTimeoutMilliseconds);
    }

    void trackImpression() {
        if (this.mAdResponse != null) {
            TrackingRequest.makeTrackingHttpRequest(this.mAdResponse.getImpressionTrackingUrl(), this.mContext, Name.IMPRESSION_REQUEST);
        }
    }

    void registerClick() {
        if (this.mAdResponse != null) {
            TrackingRequest.makeTrackingHttpRequest(this.mAdResponse.getClickTrackingUrl(), this.mContext, Name.CLICK_REQUEST);
        }
    }

    void fetchAd(String str) {
        MoPubView moPubView = getMoPubView();
        if (moPubView == null || this.mContext == null) {
            MoPubLog.d("Can't load an ad in this ad view because it was destroyed.");
            setNotLoading();
            return;
        }
        Request adRequest = new AdRequest(str, moPubView.getAdFormat(), this.mAdUnitId, this.mContext, this.mAdListener);
        Networking.getRequestQueue(this.mContext).add(adRequest);
        this.mActiveRequest = adRequest;
    }

    void forceRefresh() {
        setNotLoading();
        loadAd();
    }

    @Nullable
    String generateAdUrl() {
        if (this.mUrlGenerator == null) {
            return null;
        }
        return this.mUrlGenerator.withAdUnitId(this.mAdUnitId).withKeywords(this.mKeywords).withLocation(this.mLocation).generateUrlString(Constants.HOST);
    }

    void adDidFail(MoPubErrorCode moPubErrorCode) {
        MoPubLog.i("Ad failed to load.");
        setNotLoading();
        MoPubView moPubView = getMoPubView();
        if (moPubView != null) {
            scheduleRefreshTimerIfEnabled();
            moPubView.adFailed(moPubErrorCode);
        }
    }

    void scheduleRefreshTimerIfEnabled() {
        cancelRefreshTimer();
        if (this.mAutoRefreshEnabled && this.mRefreshTimeMillis != null && this.mRefreshTimeMillis.intValue() > 0) {
            this.mHandler.postDelayed(this.mRefreshRunnable, Math.min(600000, ((long) this.mRefreshTimeMillis.intValue()) * ((long) Math.pow(BACKOFF_FACTOR, (double) this.mBackoffPower))));
        }
    }

    void setLocalExtras(Map<String, Object> map) {
        this.mLocalExtras = map != null ? new TreeMap(map) : new TreeMap();
    }

    Map<String, Object> getLocalExtras() {
        return this.mLocalExtras != null ? new TreeMap(this.mLocalExtras) : new TreeMap();
    }

    private void cancelRefreshTimer() {
        this.mHandler.removeCallbacks(this.mRefreshRunnable);
    }

    private boolean isNetworkAvailable() {
        if (this.mContext == null) {
            return false;
        }
        if (!DeviceUtils.isPermissionGranted(this.mContext, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        return z;
    }

    void setAdContentView(View view) {
        this.mHandler.post(new 3(this, view));
    }

    private LayoutParams getAdLayoutParams(View view) {
        Integer width;
        Integer num = null;
        if (this.mAdResponse != null) {
            width = this.mAdResponse.getWidth();
            num = this.mAdResponse.getHeight();
        } else {
            width = null;
        }
        if (width == null || num == null || !getShouldHonorServerDimensions(view) || width.intValue() <= 0 || num.intValue() <= 0) {
            return WRAP_AND_CENTER_LAYOUT_PARAMS;
        }
        return new LayoutParams(Dips.asIntPixels((float) width.intValue(), this.mContext), Dips.asIntPixels((float) num.intValue(), this.mContext), 17);
    }

    @Deprecated
    @VisibleForTesting
    Integer getRefreshTimeMillis() {
        return this.mRefreshTimeMillis;
    }

    @Deprecated
    @VisibleForTesting
    void setRefreshTimeMillis(@Nullable Integer num) {
        this.mRefreshTimeMillis = num;
    }

    public AdResponse getmAdResponse() {
        return this.mAdResponse;
    }
}
