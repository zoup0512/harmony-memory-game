package com.mopub.nativeads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.AdFormat;
import com.mopub.common.Constants;
import com.mopub.common.GpsHelper;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.ManifestUtils;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.network.AdRequest;
import com.mopub.network.AdRequest.Listener;
import com.mopub.network.AdResponse;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.Networking;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.VolleyError;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.TreeMap;

public class MoPubNative {
    static final MoPubNativeNetworkListener EMPTY_NETWORK_LISTENER = new 1();
    @NonNull
    private final WeakReference<Activity> mActivity;
    @NonNull
    AdRendererRegistry mAdRendererRegistry;
    @NonNull
    private final String mAdUnitId;
    @NonNull
    private Map<String, Object> mLocalExtras;
    @NonNull
    private MoPubNativeNetworkListener mMoPubNativeNetworkListener;
    @Nullable
    private AdRequest mNativeRequest;
    @NonNull
    private final Listener mVolleyListener;

    public MoPubNative(@NonNull Activity activity, @NonNull String str, @NonNull MoPubNativeNetworkListener moPubNativeNetworkListener) {
        this(activity, str, new AdRendererRegistry(), moPubNativeNetworkListener);
    }

    @VisibleForTesting
    public MoPubNative(@NonNull Activity activity, @NonNull String str, @NonNull AdRendererRegistry adRendererRegistry, @NonNull MoPubNativeNetworkListener moPubNativeNetworkListener) {
        this.mLocalExtras = new TreeMap();
        Preconditions.checkNotNull(activity, "Activity may not be null.");
        Preconditions.checkNotNull(str, "AdUnitId may not be null.");
        Preconditions.checkNotNull(adRendererRegistry, "AdRendererRegistry may not be null.");
        Preconditions.checkNotNull(moPubNativeNetworkListener, "MoPubNativeNetworkListener may not be null.");
        ManifestUtils.checkNativeActivitiesDeclared(activity);
        this.mActivity = new WeakReference(activity);
        this.mAdUnitId = str;
        this.mMoPubNativeNetworkListener = moPubNativeNetworkListener;
        this.mAdRendererRegistry = adRendererRegistry;
        this.mVolleyListener = new 2(this);
        GpsHelper.fetchAdvertisingInfoAsync(activity, null);
    }

    public void registerAdRenderer(MoPubAdRenderer moPubAdRenderer) {
        this.mAdRendererRegistry.registerAdRenderer(moPubAdRenderer);
    }

    public void destroy() {
        this.mActivity.clear();
        if (this.mNativeRequest != null) {
            this.mNativeRequest.cancel();
            this.mNativeRequest = null;
        }
        this.mMoPubNativeNetworkListener = EMPTY_NETWORK_LISTENER;
    }

    public void setLocalExtras(@Nullable Map<String, Object> map) {
        if (map == null) {
            this.mLocalExtras = new TreeMap();
        } else {
            this.mLocalExtras = new TreeMap(map);
        }
    }

    public void makeRequest() {
        makeRequest((RequestParameters) null);
    }

    public void makeRequest(@Nullable RequestParameters requestParameters) {
        makeRequest(requestParameters, null);
    }

    public void makeRequest(@Nullable RequestParameters requestParameters, @Nullable Integer num) {
        Context activityOrDestroy = getActivityOrDestroy();
        if (activityOrDestroy != null) {
            if (DeviceUtils.isNetworkAvailable(activityOrDestroy)) {
                loadNativeAd(requestParameters, num);
            } else {
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.CONNECTION_ERROR);
            }
        }
    }

    private void loadNativeAd(@Nullable RequestParameters requestParameters, @Nullable Integer num) {
        Context activityOrDestroy = getActivityOrDestroy();
        if (activityOrDestroy != null) {
            NativeUrlGenerator withRequest = new NativeUrlGenerator(activityOrDestroy).withAdUnitId(this.mAdUnitId).withRequest(requestParameters);
            if (num != null) {
                withRequest.withSequenceNumber(num.intValue());
            }
            String generateUrlString = withRequest.generateUrlString(Constants.HOST);
            if (generateUrlString != null) {
                MoPubLog.d("Loading ad from: " + generateUrlString);
            }
            requestNativeAd(generateUrlString);
        }
    }

    void requestNativeAd(@Nullable String str) {
        Context activityOrDestroy = getActivityOrDestroy();
        if (activityOrDestroy != null) {
            if (str == null) {
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.INVALID_REQUEST_URL);
                return;
            }
            this.mNativeRequest = new AdRequest(str, AdFormat.NATIVE, this.mAdUnitId, activityOrDestroy, this.mVolleyListener);
            Networking.getRequestQueue(activityOrDestroy).add(this.mNativeRequest);
        }
    }

    private void onAdLoad(@NonNull AdResponse adResponse) {
        Activity activityOrDestroy = getActivityOrDestroy();
        if (activityOrDestroy != null) {
            CustomEventNativeAdapter.loadNativeAd(activityOrDestroy, this.mLocalExtras, adResponse, new 3(this, adResponse));
        }
    }

    @VisibleForTesting
    void onAdError(@NonNull VolleyError volleyError) {
        MoPubLog.d("Native ad request failed.", volleyError);
        if (volleyError instanceof MoPubNetworkError) {
            switch (4.$SwitchMap$com$mopub$network$MoPubNetworkError$Reason[((MoPubNetworkError) volleyError).getReason().ordinal()]) {
                case 1:
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.INVALID_RESPONSE);
                    return;
                case 2:
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.INVALID_RESPONSE);
                    return;
                case 3:
                    MoPubLog.c(MoPubErrorCode.WARMUP.toString());
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.EMPTY_AD_RESPONSE);
                    return;
                case 4:
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.EMPTY_AD_RESPONSE);
                    return;
                default:
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.UNSPECIFIED);
                    return;
            }
        }
        NetworkResponse networkResponse = volleyError.networkResponse;
        if (networkResponse != null && networkResponse.statusCode >= 500 && networkResponse.statusCode < SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_DEFAULT) {
            this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.SERVER_ERROR_RESPONSE_CODE);
        } else if (networkResponse != null || DeviceUtils.isNetworkAvailable((Context) this.mActivity.get())) {
            this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.UNSPECIFIED);
        } else {
            MoPubLog.c(String.valueOf(MoPubErrorCode.NO_CONNECTION.toString()));
            this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.CONNECTION_ERROR);
        }
    }

    Activity getActivityOrDestroy() {
        Activity activity = (Activity) this.mActivity.get();
        if (activity == null) {
            destroy();
            MoPubLog.d("Weak reference to Activity in MoPubNative became null. This instance of MoPubNative is destroyed and No more requests will be processed.");
        }
        return activity;
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    MoPubNativeNetworkListener getMoPubNativeNetworkListener() {
        return this.mMoPubNativeNetworkListener;
    }
}
