package com.mopub.mraid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.AdViewController;
import com.mopub.mobileads.CustomEventBanner;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.factories.MraidControllerFactory;
import java.util.Map;

class MraidBanner extends CustomEventBanner {
    @Nullable
    private CustomEventBannerListener mBannerListener;
    @Nullable
    private MraidWebViewDebugListener mDebugListener;
    @Nullable
    private MraidController mMraidController;

    MraidBanner() {
    }

    protected void loadBanner(@NonNull Context context, @NonNull CustomEventBannerListener customEventBannerListener, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) {
        this.mBannerListener = customEventBannerListener;
        if (extrasAreValid(map2)) {
            String str = (String) map2.get(DataKeys.HTML_RESPONSE_BODY_KEY);
            try {
                this.mMraidController = MraidControllerFactory.create(context, (AdReport) map.get(DataKeys.AD_REPORT_KEY), PlacementType.INLINE);
                this.mMraidController.setDebugListener(this.mDebugListener);
                this.mMraidController.setMraidListener(new MraidController$MraidListener() {
                    public void onLoaded(View view) {
                        AdViewController.setShouldHonorServerDimensions(view);
                        MraidBanner.this.mBannerListener.onBannerLoaded(view);
                    }

                    public void onFailedToLoad() {
                        MraidBanner.this.mBannerListener.onBannerFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
                    }

                    public void onExpand() {
                        MraidBanner.this.mBannerListener.onBannerExpanded();
                        MraidBanner.this.mBannerListener.onBannerClicked();
                    }

                    public void onOpen() {
                        MraidBanner.this.mBannerListener.onBannerClicked();
                    }

                    public void onClose() {
                        MraidBanner.this.mBannerListener.onBannerCollapsed();
                    }
                });
                this.mMraidController.loadContent(str);
                return;
            } catch (Throwable e) {
                MoPubLog.w("MRAID banner creating failed:", e);
                this.mBannerListener.onBannerFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
                return;
            }
        }
        this.mBannerListener.onBannerFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
    }

    protected void onInvalidate() {
        if (this.mMraidController != null) {
            this.mMraidController.setMraidListener(null);
            this.mMraidController.destroy();
        }
    }

    private boolean extrasAreValid(Map<String, String> map) {
        return map.containsKey(DataKeys.HTML_RESPONSE_BODY_KEY);
    }

    @VisibleForTesting
    public void setDebugListener(@Nullable MraidWebViewDebugListener mraidWebViewDebugListener) {
        this.mDebugListener = mraidWebViewDebugListener;
        if (this.mMraidController != null) {
            this.mMraidController.setDebugListener(mraidWebViewDebugListener);
        }
    }
}
