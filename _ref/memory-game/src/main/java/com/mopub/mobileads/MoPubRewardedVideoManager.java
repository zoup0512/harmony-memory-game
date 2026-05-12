package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.AdFormat;
import com.mopub.common.AdReport;
import com.mopub.common.AdUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.DataKeys;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPubReward;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.MoPubCollections;
import com.mopub.common.util.Reflection;
import com.mopub.common.util.Utils;
import com.mopub.network.AdRequest;
import com.mopub.network.AdResponse;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.Networking;
import com.mopub.network.TrackingRequest;
import com.mopub.volley.NoConnectionError;
import com.mopub.volley.VolleyError;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MoPubRewardedVideoManager {
    public static final int API_VERSION = 1;
    private static final int DEFAULT_LOAD_TIMEOUT = 30000;
    private static MoPubRewardedVideoManager sInstance;
    @NonNull
    private final AdRequestStatusMapping mAdRequestStatus;
    private final long mBroadcastIdentifier;
    @NonNull
    private final Handler mCallbackHandler = new Handler(Looper.getMainLooper());
    @NonNull
    private final Context mContext;
    @NonNull
    private final Handler mCustomEventTimeoutHandler;
    @NonNull
    private final Set<MediationSettings> mGlobalMediationSettings = new HashSet();
    @NonNull
    private final Map<String, Set<MediationSettings>> mInstanceMediationSettings;
    @NonNull
    private WeakReference<Activity> mMainActivity;
    @NonNull
    private final RewardedVideoData mRewardedVideoData = new RewardedVideoData();
    @NonNull
    private final Map<String, Runnable> mTimeoutMap;
    @Nullable
    private MoPubRewardedVideoListener mVideoListener;

    private MoPubRewardedVideoManager(@NonNull Activity activity, MediationSettings... mediationSettingsArr) {
        this.mMainActivity = new WeakReference(activity);
        this.mContext = activity.getApplicationContext();
        MoPubCollections.addAllNonNull(this.mGlobalMediationSettings, mediationSettingsArr);
        this.mInstanceMediationSettings = new HashMap();
        this.mCustomEventTimeoutHandler = new Handler();
        this.mTimeoutMap = new HashMap();
        this.mBroadcastIdentifier = Utils.generateUniqueId();
        this.mAdRequestStatus = new AdRequestStatusMapping();
    }

    public static synchronized void init(@NonNull Activity activity, MediationSettings... mediationSettingsArr) {
        synchronized (MoPubRewardedVideoManager.class) {
            if (sInstance == null) {
                sInstance = new MoPubRewardedVideoManager(activity, mediationSettingsArr);
            } else {
                MoPubLog.e("Tried to call initializeRewardedVideo more than once. Only the first initialization call has any effect.");
            }
        }
    }

    public static void updateActivity(@NonNull Activity activity) {
        if (sInstance != null) {
            sInstance.mMainActivity = new WeakReference(activity);
            return;
        }
        logErrorNotInitialized();
    }

    @Nullable
    public static <T extends MediationSettings> T getGlobalMediationSettings(@NonNull Class<T> cls) {
        if (sInstance == null) {
            logErrorNotInitialized();
            return null;
        }
        for (MediationSettings mediationSettings : sInstance.mGlobalMediationSettings) {
            if (cls.equals(mediationSettings.getClass())) {
                return (MediationSettings) cls.cast(mediationSettings);
            }
        }
        return null;
    }

    @Nullable
    public static <T extends MediationSettings> T getInstanceMediationSettings(@NonNull Class<T> cls, @NonNull String str) {
        if (sInstance == null) {
            logErrorNotInitialized();
            return null;
        }
        Set<MediationSettings> set = (Set) sInstance.mInstanceMediationSettings.get(str);
        if (set == null) {
            return null;
        }
        for (MediationSettings mediationSettings : set) {
            if (cls.equals(mediationSettings.getClass())) {
                return (MediationSettings) cls.cast(mediationSettings);
            }
        }
        return null;
    }

    public static void setVideoListener(@Nullable MoPubRewardedVideoListener moPubRewardedVideoListener) {
        if (sInstance != null) {
            sInstance.mVideoListener = moPubRewardedVideoListener;
        } else {
            logErrorNotInitialized();
        }
    }

    public static void loadVideo(@NonNull String str, @Nullable RequestParameters requestParameters, @Nullable MediationSettings... mediationSettingsArr) {
        Location location = null;
        if (sInstance == null) {
            logErrorNotInitialized();
            return;
        }
        Collection hashSet = new HashSet();
        MoPubCollections.addAllNonNull(hashSet, mediationSettingsArr);
        sInstance.mInstanceMediationSettings.put(str, hashSet);
        Object obj = requestParameters == null ? null : requestParameters.mCustomerId;
        if (!TextUtils.isEmpty(obj)) {
            sInstance.mRewardedVideoData.setCustomerId(obj);
        }
        AdUrlGenerator withKeywords = new WebViewAdUrlGenerator(sInstance.mContext, false).withAdUnitId(str).withKeywords(requestParameters == null ? null : requestParameters.mKeywords);
        if (requestParameters != null) {
            location = requestParameters.mLocation;
        }
        loadVideo(str, withKeywords.withLocation(location).generateUrlString(Constants.HOST));
    }

    private static void loadVideo(@NonNull String str, @NonNull String str2) {
        if (sInstance == null) {
            logErrorNotInitialized();
        } else if (sInstance.mAdRequestStatus.isLoading(str)) {
            MoPubLog.d(String.format(Locale.US, "Did not queue rewarded video request for ad unit %s. A request is already pending.", new Object[]{str}));
        } else {
            Networking.getRequestQueue(sInstance.mContext).add(new AdRequest(str2, AdFormat.REWARDED_VIDEO, str, sInstance.mContext, new RewardedVideoRequestListener(sInstance, str)));
            sInstance.mAdRequestStatus.markLoading(str);
        }
    }

    public static boolean hasVideo(@NonNull String str) {
        if (sInstance != null) {
            return isPlayable(str, sInstance.mRewardedVideoData.getCustomEvent(str));
        }
        logErrorNotInitialized();
        return false;
    }

    public static void showVideo(@NonNull String str) {
        if (sInstance != null) {
            CustomEventRewardedVideo customEvent = sInstance.mRewardedVideoData.getCustomEvent(str);
            if (isPlayable(str, customEvent)) {
                sInstance.mRewardedVideoData.updateCustomEventLastShownRewardMapping(customEvent.getClass(), sInstance.mRewardedVideoData.getMoPubReward(str));
                sInstance.mRewardedVideoData.setCurrentAdUnitId(str);
                sInstance.mAdRequestStatus.markPlayed(str);
                customEvent.showVideo();
                return;
            }
            sInstance.failover(str, MoPubErrorCode.VIDEO_NOT_AVAILABLE);
            return;
        }
        logErrorNotInitialized();
    }

    private static boolean isPlayable(String str, @Nullable CustomEventRewardedVideo customEventRewardedVideo) {
        return sInstance != null && sInstance.mAdRequestStatus.canPlay(str) && customEventRewardedVideo != null && customEventRewardedVideo.hasVideoAvailable();
    }

    private void onAdSuccess(AdResponse adResponse, String str) {
        Integer valueOf;
        this.mAdRequestStatus.markLoaded(str, adResponse.getFailoverUrl(), adResponse.getImpressionTrackingUrl(), adResponse.getClickTrackingUrl());
        Integer adTimeoutMillis = adResponse.getAdTimeoutMillis();
        if (adTimeoutMillis == null || adTimeoutMillis.intValue() <= 0) {
            valueOf = Integer.valueOf(30000);
        } else {
            valueOf = adTimeoutMillis;
        }
        String customEventClassName = adResponse.getCustomEventClassName();
        if (customEventClassName == null) {
            MoPubLog.e("Couldn't create custom event, class name was null.");
            failover(str, MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
            return;
        }
        CustomEventRewardedVideo customEvent = this.mRewardedVideoData.getCustomEvent(str);
        if (customEvent != null) {
            customEvent.onInvalidate();
        }
        try {
            customEvent = (CustomEventRewardedVideo) Reflection.instantiateClassWithEmptyConstructor(customEventClassName, CustomEventRewardedVideo.class);
            Map treeMap = new TreeMap();
            treeMap.put(DataKeys.AD_UNIT_ID_KEY, str);
            treeMap.put(DataKeys.REWARDED_VIDEO_CURRENCY_NAME_KEY, adResponse.getRewardedVideoCurrencyName());
            treeMap.put(DataKeys.REWARDED_VIDEO_CURRENCY_AMOUNT_STRING_KEY, adResponse.getRewardedVideoCurrencyAmount());
            treeMap.put(DataKeys.AD_REPORT_KEY, new AdReport(str, ClientMetadata.getInstance(this.mContext), adResponse));
            treeMap.put(DataKeys.BROADCAST_IDENTIFIER_KEY, Long.valueOf(this.mBroadcastIdentifier));
            treeMap.put(DataKeys.REWARDED_VIDEO_CUSTOMER_ID, this.mRewardedVideoData.getCustomerId());
            this.mRewardedVideoData.updateAdUnitRewardMapping(str, adResponse.getRewardedVideoCurrencyName(), adResponse.getRewardedVideoCurrencyAmount());
            this.mRewardedVideoData.updateAdUnitToServerCompletionUrlMapping(str, adResponse.getRewardedVideoCompletionUrl());
            Activity activity = (Activity) this.mMainActivity.get();
            if (activity == null) {
                MoPubLog.d("Could not load custom event because Activity reference was null. Call MoPub#updateActivity before requesting more rewarded videos.");
                this.mAdRequestStatus.markFail(str);
                return;
            }
            Runnable 1 = new 1(this, customEvent);
            this.mCustomEventTimeoutHandler.postDelayed(1, (long) valueOf.intValue());
            this.mTimeoutMap.put(str, 1);
            customEvent.loadCustomEvent(activity, treeMap, adResponse.getServerExtras());
            this.mRewardedVideoData.updateAdUnitCustomEventMapping(str, customEvent, customEvent.getVideoListenerForSdk(), customEvent.getAdNetworkId());
        } catch (Exception e) {
            MoPubLog.e(String.format(Locale.US, "Couldn't create custom event with class name %s", new Object[]{customEventClassName}));
            failover(str, MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        }
    }

    private void onAdError(@NonNull VolleyError volleyError, @NonNull String str) {
        MoPubErrorCode moPubErrorCode = MoPubErrorCode.INTERNAL_ERROR;
        if (volleyError instanceof MoPubNetworkError) {
            switch (14.$SwitchMap$com$mopub$network$MoPubNetworkError$Reason[((MoPubNetworkError) volleyError).getReason().ordinal()]) {
                case 1:
                case 2:
                    moPubErrorCode = MoPubErrorCode.NO_FILL;
                    break;
                default:
                    moPubErrorCode = MoPubErrorCode.INTERNAL_ERROR;
                    break;
            }
        }
        if (volleyError instanceof NoConnectionError) {
            moPubErrorCode = MoPubErrorCode.NO_CONNECTION;
        }
        failover(str, moPubErrorCode);
    }

    private void failover(@NonNull String str, @NonNull MoPubErrorCode moPubErrorCode) {
        String failoverUrl = this.mAdRequestStatus.getFailoverUrl(str);
        this.mAdRequestStatus.markFail(str);
        if (failoverUrl != null) {
            loadVideo(str, failoverUrl);
        } else if (this.mVideoListener != null) {
            this.mVideoListener.onRewardedVideoLoadFailure(str, moPubErrorCode);
        }
    }

    private void cancelTimeouts(@NonNull String str) {
        Runnable runnable = (Runnable) this.mTimeoutMap.remove(str);
        if (runnable != null) {
            this.mCustomEventTimeoutHandler.removeCallbacks(runnable);
        }
    }

    public static <T extends CustomEventRewardedVideo> void onRewardedVideoLoadSuccess(@NonNull Class<T> cls, @NonNull String str) {
        postToInstance(new 2(cls, str));
    }

    public static <T extends CustomEventRewardedVideo> void onRewardedVideoLoadFailure(@NonNull Class<T> cls, String str, MoPubErrorCode moPubErrorCode) {
        postToInstance(new 3(cls, str, moPubErrorCode));
    }

    public static <T extends CustomEventRewardedVideo> void onRewardedVideoStarted(@NonNull Class<T> cls, String str) {
        Object currentAdUnitId = sInstance.mRewardedVideoData.getCurrentAdUnitId();
        if (TextUtils.isEmpty(currentAdUnitId)) {
            postToInstance(new 4(cls, str));
        } else {
            postToInstance(new 5(currentAdUnitId));
        }
    }

    private static void onRewardedVideoStartedAction(@NonNull String str) {
        Preconditions.checkNotNull(str);
        if (sInstance.mVideoListener != null) {
            sInstance.mVideoListener.onRewardedVideoStarted(str);
        }
        TrackingRequest.makeTrackingHttpRequest(sInstance.mAdRequestStatus.getImpressionTrackerUrlString(str), sInstance.mContext);
        sInstance.mAdRequestStatus.clearImpressionUrl(str);
    }

    public static <T extends CustomEventRewardedVideo> void onRewardedVideoPlaybackError(@NonNull Class<T> cls, String str, MoPubErrorCode moPubErrorCode) {
        Object currentAdUnitId = sInstance.mRewardedVideoData.getCurrentAdUnitId();
        if (TextUtils.isEmpty(currentAdUnitId)) {
            postToInstance(new 6(cls, str, moPubErrorCode));
        } else {
            postToInstance(new 7(currentAdUnitId, moPubErrorCode));
        }
    }

    private static void onRewardedVideoPlaybackErrorAction(@NonNull String str, @NonNull MoPubErrorCode moPubErrorCode) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(moPubErrorCode);
        if (sInstance.mVideoListener != null) {
            sInstance.mVideoListener.onRewardedVideoPlaybackError(str, moPubErrorCode);
        }
    }

    public static <T extends CustomEventRewardedVideo> void onRewardedVideoClicked(@NonNull Class<T> cls, String str) {
        Object currentAdUnitId = sInstance.mRewardedVideoData.getCurrentAdUnitId();
        if (TextUtils.isEmpty(currentAdUnitId)) {
            postToInstance(new 8(cls, str));
        } else {
            postToInstance(new 9(currentAdUnitId));
        }
    }

    private static void onRewardedVideoClickedAction(@NonNull String str) {
        Preconditions.checkNotNull(str);
        TrackingRequest.makeTrackingHttpRequest(sInstance.mAdRequestStatus.getClickTrackerUrlString(str), sInstance.mContext);
        sInstance.mAdRequestStatus.clearClickUrl(str);
    }

    public static <T extends CustomEventRewardedVideo> void onRewardedVideoClosed(@NonNull Class<T> cls, String str) {
        Object currentAdUnitId = sInstance.mRewardedVideoData.getCurrentAdUnitId();
        if (TextUtils.isEmpty(currentAdUnitId)) {
            postToInstance(new 10(cls, str));
        } else {
            postToInstance(new 11(currentAdUnitId));
        }
    }

    private static void onRewardedVideoClosedAction(@NonNull String str) {
        Preconditions.checkNotNull(str);
        if (sInstance.mVideoListener != null) {
            sInstance.mVideoListener.onRewardedVideoClosed(str);
        }
    }

    public static <T extends CustomEventRewardedVideo> void onRewardedVideoCompleted(@NonNull Class<T> cls, String str, @NonNull MoPubReward moPubReward) {
        Object serverCompletionUrl = sInstance.mRewardedVideoData.getServerCompletionUrl(sInstance.mRewardedVideoData.getCurrentAdUnitId());
        if (TextUtils.isEmpty(serverCompletionUrl)) {
            postToInstance(new 12(cls, moPubReward, str));
        } else {
            postToInstance(new 13(serverCompletionUrl));
        }
    }

    @VisibleForTesting
    static MoPubReward chooseReward(@Nullable MoPubReward moPubReward, @NonNull MoPubReward moPubReward2) {
        if (!moPubReward2.isSuccessful()) {
            return moPubReward2;
        }
        if (moPubReward == null) {
            moPubReward = moPubReward2;
        }
        return moPubReward;
    }

    private static void postToInstance(@NonNull Runnable runnable) {
        if (sInstance != null) {
            sInstance.mCallbackHandler.post(runnable);
        }
    }

    private static void logErrorNotInitialized() {
        MoPubLog.e("MoPub rewarded video was not initialized. You must call MoPub.initializeRewardedVideo() before loading or attempting to play video ads.");
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    static RewardedVideoData getRewardedVideoData() {
        if (sInstance != null) {
            return sInstance.mRewardedVideoData;
        }
        return null;
    }
}
