package com.mopub.mobileads;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.location.Location;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebViewDatabase;
import android.widget.FrameLayout;
import com.mopub.common.AdFormat;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.ManifestUtils;
import com.mopub.common.util.Visibility;
import com.mopub.mobileads.factories.AdViewControllerFactory;
import com.mopub.mobileads.factories.CustomEventBannerAdapterFactory;
import java.util.Map;
import java.util.TreeMap;

public class MoPubView extends FrameLayout {
    @Nullable
    protected AdViewController mAdViewController;
    private BannerAdListener mBannerAdListener;
    private Context mContext;
    protected CustomEventBannerAdapter mCustomEventBannerAdapter;
    private BroadcastReceiver mScreenStateReceiver;
    private int mScreenVisibility;

    public MoPubView(Context context) {
        this(context, null);
    }

    public MoPubView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ManifestUtils.checkWebViewActivitiesDeclared(context);
        this.mContext = context;
        this.mScreenVisibility = getVisibility();
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        if (WebViewDatabase.getInstance(context) == null) {
            MoPubLog.e("Disabling MoPub. Local cache file is inaccessible so MoPub will fail if we try to create a WebView. Details of this Android bug found at:https://code.google.com/p/android/issues/detail?id=10789");
        } else {
            this.mAdViewController = AdViewControllerFactory.create(context, this);
        }
    }

    private void registerScreenStateBroadcastReceiver() {
        this.mScreenStateReceiver = new 1(this);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.mContext.registerReceiver(this.mScreenStateReceiver, intentFilter);
    }

    private void unregisterScreenStateBroadcastReceiver() {
        try {
            this.mContext.unregisterReceiver(this.mScreenStateReceiver);
        } catch (Exception e) {
            MoPubLog.d("Failed to unregister screen state broadcast receiver (never registered).");
        }
    }

    public void loadAd() {
        if (this.mAdViewController != null) {
            this.mAdViewController.loadAd();
        }
    }

    public void destroy() {
        removeAllViews();
        if (this.mAdViewController != null) {
            this.mAdViewController.cleanup();
            this.mAdViewController = null;
        }
        if (this.mCustomEventBannerAdapter != null) {
            this.mCustomEventBannerAdapter.invalidate();
            this.mCustomEventBannerAdapter = null;
        }
    }

    Integer getAdTimeoutDelay() {
        return this.mAdViewController != null ? this.mAdViewController.getAdTimeoutDelay() : null;
    }

    protected void loadFailUrl(MoPubErrorCode moPubErrorCode) {
        if (this.mAdViewController != null) {
            this.mAdViewController.loadFailUrl(moPubErrorCode);
        }
    }

    protected void loadCustomEvent(String str, Map<String, String> map) {
        if (this.mAdViewController != null) {
            if (TextUtils.isEmpty(str)) {
                MoPubLog.d("Couldn't invoke custom event because the server did not specify one.");
                loadFailUrl(MoPubErrorCode.ADAPTER_NOT_FOUND);
                return;
            }
            if (this.mCustomEventBannerAdapter != null) {
                this.mCustomEventBannerAdapter.invalidate();
            }
            MoPubLog.d("Loading custom event adapter.");
            this.mCustomEventBannerAdapter = CustomEventBannerAdapterFactory.create(this, str, map, this.mAdViewController.getBroadcastIdentifier(), this.mAdViewController.getAdReport());
            this.mCustomEventBannerAdapter.loadAd();
        }
    }

    protected void registerClick() {
        if (this.mAdViewController != null) {
            this.mAdViewController.registerClick();
            adClicked();
        }
    }

    protected void trackNativeImpression() {
        MoPubLog.d("Tracking impression for native adapter.");
        if (this.mAdViewController != null) {
            this.mAdViewController.trackImpression();
        }
    }

    protected void onWindowVisibilityChanged(int i) {
        if (Visibility.hasScreenVisibilityChanged(this.mScreenVisibility, i)) {
            this.mScreenVisibility = i;
            setAdVisibility(this.mScreenVisibility);
        }
    }

    private void setAdVisibility(int i) {
        if (this.mAdViewController != null) {
            if (Visibility.isScreenVisible(i)) {
                this.mAdViewController.unpauseRefresh();
            } else {
                this.mAdViewController.pauseRefresh();
            }
        }
    }

    protected void adLoaded() {
        MoPubLog.d("adLoaded");
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onBannerLoaded(this);
        }
    }

    protected void adFailed(MoPubErrorCode moPubErrorCode) {
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onBannerFailed(this, moPubErrorCode);
        }
    }

    protected void adPresentedOverlay() {
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onBannerExpanded(this);
        }
    }

    protected void adClosed() {
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onBannerCollapsed(this);
        }
    }

    protected void adClicked() {
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onBannerClicked(this);
        }
    }

    protected void nativeAdLoaded() {
        if (this.mAdViewController != null) {
            this.mAdViewController.scheduleRefreshTimerIfEnabled();
        }
        adLoaded();
    }

    public void setAdUnitId(String str) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setAdUnitId(str);
        }
    }

    public String getAdUnitId() {
        return this.mAdViewController != null ? this.mAdViewController.getAdUnitId() : null;
    }

    public void setKeywords(String str) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setKeywords(str);
        }
    }

    public String getKeywords() {
        return this.mAdViewController != null ? this.mAdViewController.getKeywords() : null;
    }

    public void setLocation(Location location) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setLocation(location);
        }
    }

    public Location getLocation() {
        return this.mAdViewController != null ? this.mAdViewController.getLocation() : null;
    }

    public int getAdWidth() {
        return this.mAdViewController != null ? this.mAdViewController.getAdWidth() : 0;
    }

    public int getAdHeight() {
        return this.mAdViewController != null ? this.mAdViewController.getAdHeight() : 0;
    }

    public Activity getActivity() {
        return (Activity) this.mContext;
    }

    public void setBannerAdListener(BannerAdListener bannerAdListener) {
        this.mBannerAdListener = bannerAdListener;
    }

    public BannerAdListener getBannerAdListener() {
        return this.mBannerAdListener;
    }

    public void setLocalExtras(Map<String, Object> map) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setLocalExtras(map);
        }
    }

    public Map<String, Object> getLocalExtras() {
        if (this.mAdViewController != null) {
            return this.mAdViewController.getLocalExtras();
        }
        return new TreeMap();
    }

    public void setAutorefreshEnabled(boolean z) {
        if (this.mAdViewController != null) {
            this.mAdViewController.forceSetAutorefreshEnabled(z);
        }
    }

    public boolean getAutorefreshEnabled() {
        if (this.mAdViewController != null) {
            return this.mAdViewController.getAutorefreshEnabled();
        }
        MoPubLog.d("Can't get autorefresh status for destroyed MoPubView. Returning false.");
        return false;
    }

    public void setAdContentView(View view) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setAdContentView(view);
        }
    }

    public void setTesting(boolean z) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setTesting(z);
        }
    }

    public boolean getTesting() {
        if (this.mAdViewController != null) {
            return this.mAdViewController.getTesting();
        }
        MoPubLog.d("Can't get testing status for destroyed MoPubView. Returning false.");
        return false;
    }

    public void forceRefresh() {
        if (this.mCustomEventBannerAdapter != null) {
            this.mCustomEventBannerAdapter.invalidate();
            this.mCustomEventBannerAdapter = null;
        }
        if (this.mAdViewController != null) {
            this.mAdViewController.forceRefresh();
        }
    }

    AdViewController getAdViewController() {
        return this.mAdViewController;
    }

    public AdFormat getAdFormat() {
        return AdFormat.BANNER;
    }

    @Deprecated
    public void setTimeout(int i) {
    }

    @Deprecated
    public String getResponseString() {
        return null;
    }

    @Deprecated
    public String getClickTrackingUrl() {
        return null;
    }

    public AdViewController getmAdViewController() {
        return this.mAdViewController;
    }
}
