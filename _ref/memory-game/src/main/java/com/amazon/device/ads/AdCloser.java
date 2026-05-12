package com.amazon.device.ads;

import com.amazon.device.ads.SDKEvent.SDKEventType;
import java.util.concurrent.atomic.AtomicBoolean;

class AdCloser {
    private static final String LOGTAG = AdCloser.class.getSimpleName();
    private final AdController adController;
    private final AtomicBoolean isClosing;
    private final MobileAdsLogger logger;

    public AdCloser(AdController adController) {
        this(adController, new MobileAdsLoggerFactory());
    }

    AdCloser(AdController adController, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this.isClosing = new AtomicBoolean(false);
        this.adController = adController;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
    }

    public boolean closeAd() {
        boolean z = true;
        this.logger.d("Ad is attempting to close.");
        if (this.adController.getAdState().equals(AdState.READY_TO_LOAD) || this.isClosing.getAndSet(true)) {
            return false;
        }
        boolean z2;
        boolean z3;
        switch (this.adController.getAdControlCallback().adClosing()) {
            case 0:
                z2 = true;
                z3 = false;
                break;
            case 1:
                z2 = true;
                z3 = true;
                break;
            default:
                z2 = false;
                z3 = false;
                break;
        }
        if (z2) {
            this.adController.fireSDKEvent(new SDKEvent(SDKEventType.CLOSED));
        } else {
            z = false;
        }
        if (z3) {
            this.adController.resetToReady();
        }
        this.isClosing.set(false);
        return z;
    }
}
