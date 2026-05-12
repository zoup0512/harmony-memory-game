package com.amazon.device.ads;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.ViewGroup;
import com.amazon.device.ads.AdActivity.AdActivityAdapter;
import com.amazon.device.ads.SDKEvent.SDKEventType;

@SuppressLint({"NewApi"})
class InterstitialAdActivityAdapter implements AdActivityAdapter {
    private static final String LOGTAG = InterstitialAdActivityAdapter.class.getSimpleName();
    private Activity activity = null;
    private AdController adController;
    private final AndroidBuildInfo buildInfo = new AndroidBuildInfo();
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    class InterstitialAdSDKEventListener implements SDKEventListener {
        InterstitialAdSDKEventListener() {
        }

        public void onSDKEvent(SDKEvent sDKEvent, AdControlAccessor adControlAccessor) {
            if (sDKEvent.getEventType().equals(SDKEventType.CLOSED)) {
                InterstitialAdActivityAdapter.this.finishActivity();
            }
        }
    }

    InterstitialAdActivityAdapter() {
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void preOnCreate() {
        this.activity.requestWindowFeature(1);
        this.activity.getWindow().setFlags(1024, 1024);
        AndroidTargetUtils.hideActionAndStatusBars(this.buildInfo, this.activity);
    }

    public void onCreate() {
        AndroidTargetUtils.enableHardwareAcceleration(this.buildInfo, this.activity.getWindow());
        this.adController = getAdController();
        if (this.adController == null) {
            this.logger.e("Failed to show interstitial ad due to an error in the Activity.");
            InterstitialAd.resetIsAdShowing();
            this.activity.finish();
            return;
        }
        this.adController.setAdActivity(this.activity);
        this.adController.addSDKEventListener(new InterstitialAdSDKEventListener());
        ViewGroup viewGroup = (ViewGroup) this.adController.getView().getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this.adController.getView());
        }
        this.activity.setContentView(this.adController.getView());
        this.adController.adShown();
    }

    AdController getAdController() {
        return AdControllerFactory.getCachedAdController();
    }

    public void onPause() {
        if (this.adController != null) {
            this.adController.fireViewableEvent();
        }
    }

    public void onResume() {
    }

    public void onStop() {
        if (this.activity.isFinishing() && this.adController != null) {
            this.adController.fireViewableEvent();
            this.adController.closeAd();
        }
    }

    public void onDestroy() {
        if (this.adController != null) {
            this.adController.fireViewableEvent();
            this.adController.closeAd();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public boolean onBackPressed() {
        if (this.adController != null) {
            return this.adController.onBackButtonPress();
        }
        return false;
    }

    public void onWindowFocusChanged() {
        if (this.adController != null) {
            this.adController.fireViewableEvent();
        }
    }

    Activity getActivity() {
        return this.activity;
    }

    private void finishActivity() {
        if (!this.activity.isFinishing()) {
            this.adController = null;
            this.activity.finish();
        }
    }
}
