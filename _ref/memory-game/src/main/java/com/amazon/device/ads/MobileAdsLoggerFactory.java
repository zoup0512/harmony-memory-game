package com.amazon.device.ads;

class MobileAdsLoggerFactory {
    MobileAdsLoggerFactory() {
    }

    public MobileAdsLogger createMobileAdsLogger(String str) {
        return new MobileAdsLogger(new LogcatLogger()).withLogTag(str);
    }
}
