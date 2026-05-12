package com.mopub.common;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideoManager;
import com.mopub.mobileads.MoPubRewardedVideoManager$RequestParameters;

public class MoPub {
    private static final int DEFAULT_LOCATION_PRECISION = 6;
    public static final String SDK_VERSION = "4.7.1";
    private static volatile LocationAwareness sLocationLocationAwareness = LocationAwareness.NORMAL;
    private static volatile int sLocationPrecision = 6;

    public enum LocationAwareness {
        NORMAL,
        TRUNCATED,
        DISABLED
    }

    public static LocationAwareness getLocationAwareness() {
        return sLocationLocationAwareness;
    }

    public static void setLocationAwareness(LocationAwareness locationAwareness) {
        sLocationLocationAwareness = locationAwareness;
    }

    public static int getLocationPrecision() {
        return sLocationPrecision;
    }

    public static void setLocationPrecision(int i) {
        sLocationPrecision = Math.min(Math.max(0, i), 6);
    }

    public static void onCreate(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onCreate(activity);
        updateActivity(activity);
    }

    public static void onStart(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onStart(activity);
        updateActivity(activity);
    }

    public static void onPause(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onPause(activity);
    }

    public static void onResume(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onResume(activity);
        updateActivity(activity);
    }

    public static void onRestart(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onRestart(activity);
        updateActivity(activity);
    }

    public static void onStop(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onStop(activity);
    }

    public static void onDestroy(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onDestroy(activity);
    }

    public static void onBackPressed(@NonNull Activity activity) {
        MoPubLifecycleManager.getInstance(activity).onBackPressed(activity);
    }

    public static void initializeRewardedVideo(@NonNull Activity activity, MediationSettings... mediationSettingsArr) {
        MoPubRewardedVideoManager.init(activity, mediationSettingsArr);
    }

    private static void updateActivity(@NonNull Activity activity) {
        MoPubRewardedVideoManager.updateActivity(activity);
    }

    public static void setRewardedVideoListener(@Nullable MoPubRewardedVideoListener moPubRewardedVideoListener) {
        MoPubRewardedVideoManager.setVideoListener(moPubRewardedVideoListener);
    }

    public static void loadRewardedVideo(@NonNull String str, @Nullable MediationSettings... mediationSettingsArr) {
        MoPubRewardedVideoManager.loadVideo(str, null, mediationSettingsArr);
    }

    public static void loadRewardedVideo(@NonNull String str, @Nullable MoPubRewardedVideoManager$RequestParameters moPubRewardedVideoManager$RequestParameters, @Nullable MediationSettings... mediationSettingsArr) {
        MoPubRewardedVideoManager.loadVideo(str, moPubRewardedVideoManager$RequestParameters, mediationSettingsArr);
    }

    public static boolean hasRewardedVideo(@NonNull String str) {
        return MoPubRewardedVideoManager.hasVideo(str);
    }

    public static void showRewardedVideo(@NonNull String str) {
        MoPubRewardedVideoManager.showVideo(str);
    }
}
