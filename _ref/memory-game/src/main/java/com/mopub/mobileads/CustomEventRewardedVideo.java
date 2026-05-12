package com.mopub.mobileads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MoPubLifecycleManager;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import java.util.Map;

public abstract class CustomEventRewardedVideo {

    @VisibleForTesting
    protected interface CustomEventRewardedVideoListener {
    }

    protected abstract boolean checkAndInitializeSdk(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2);

    @NonNull
    protected abstract String getAdNetworkId();

    @Nullable
    @VisibleForTesting
    protected abstract LifecycleListener getLifecycleListener();

    @Nullable
    @VisibleForTesting
    protected abstract CustomEventRewardedVideoListener getVideoListenerForSdk();

    protected abstract boolean hasVideoAvailable();

    protected abstract void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2);

    protected abstract void onInvalidate();

    protected abstract void showVideo();

    final void loadCustomEvent(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) {
        try {
            if (checkAndInitializeSdk(activity, map, map2)) {
                MoPubLifecycleManager.getInstance(activity).addLifecycleListener(getLifecycleListener());
            }
            loadWithSdkInitialized(activity, map, map2);
        } catch (Exception e) {
            MoPubLog.e(e.getMessage());
        }
    }
}
