package com.amazon.device.ads;

import android.graphics.Rect;

public class DefaultAdListener implements ExtendedAdListener {
    private static final String LOGTAG = DefaultAdListener.class.getSimpleName();
    private final MobileAdsLogger logger;

    public DefaultAdListener() {
        this(LOGTAG);
    }

    DefaultAdListener(String str) {
        this(new MobileAdsLoggerFactory(), str);
    }

    DefaultAdListener(MobileAdsLoggerFactory mobileAdsLoggerFactory, String str) {
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(str);
    }

    MobileAdsLogger getLogger() {
        return this.logger;
    }

    public void onAdLoaded(Ad ad, AdProperties adProperties) {
        this.logger.d("Default ad listener called - AdLoaded.");
    }

    public void onAdFailedToLoad(Ad ad, AdError adError) {
        this.logger.d("Default ad listener called - Ad Failed to Load. Error code: %s, Error Message: %s", adError.getCode(), adError.getMessage());
    }

    public void onAdExpanded(Ad ad) {
        this.logger.d("Default ad listener called - Ad Will Expand.");
    }

    public void onAdCollapsed(Ad ad) {
        this.logger.d("Default ad listener called - Ad Collapsed.");
    }

    public void onAdDismissed(Ad ad) {
        this.logger.d("Default ad listener called - Ad Dismissed.");
    }

    public void onAdResized(Ad ad, Rect rect) {
        this.logger.d("Default ad listener called - Ad Resized.");
    }

    public void onAdExpired(Ad ad) {
        this.logger.d("Default ad listener called - Ad Expired.");
    }
}
