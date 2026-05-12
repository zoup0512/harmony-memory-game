package com.amazon.device.ads;

import android.graphics.Rect;

class AdListenerExecutorFactory {
    private final AdListenerExecutorConstructor adListenerExecutorConstructor;
    private final MobileAdsLoggerFactory loggerFactory;

    static class AdListenerExecutorConstructor {
        AdListenerExecutorConstructor() {
        }

        public AdListenerExecutor createAdListenerExecutor(AdListener adListener, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
            return new AdListenerExecutor(adListener, mobileAdsLoggerFactory);
        }
    }

    public AdListenerExecutorFactory(MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this(mobileAdsLoggerFactory, new AdListenerExecutorConstructor());
    }

    public AdListenerExecutorFactory(MobileAdsLoggerFactory mobileAdsLoggerFactory, AdListenerExecutorConstructor adListenerExecutorConstructor) {
        this.loggerFactory = mobileAdsLoggerFactory;
        this.adListenerExecutorConstructor = adListenerExecutorConstructor;
    }

    protected MobileAdsLoggerFactory getLoggerFactory() {
        return this.loggerFactory;
    }

    public AdListenerExecutor createAdListenerExecutor(AdListener adListener) {
        return createAdListenerExecutor(adListener, this.loggerFactory);
    }

    public AdListenerExecutor createAdListenerExecutor(AdListener adListener, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        AdListenerExecutor createAdListenerExecutor = this.adListenerExecutorConstructor.createAdListenerExecutor(adListener, mobileAdsLoggerFactory);
        if (adListener instanceof ExtendedAdListener) {
            createAdResizedCommand(createAdListenerExecutor);
            createAdExpiredCommand(createAdListenerExecutor);
        }
        return createAdListenerExecutor;
    }

    private void createAdResizedCommand(final AdListenerExecutor adListenerExecutor) {
        adListenerExecutor.setOnAdResizedCommand(new OnAdResizedCommand() {
            public void onAdResized(final Ad ad, final Rect rect) {
                adListenerExecutor.execute(new Runnable() {
                    public void run() {
                        ((ExtendedAdListener) adListenerExecutor.getAdListener()).onAdResized(ad, rect);
                    }
                });
            }
        });
    }

    private void createAdExpiredCommand(final AdListenerExecutor adListenerExecutor) {
        adListenerExecutor.setOnAdExpiredCommand(new OnAdExpiredCommand() {
            public void onAdExpired(final Ad ad) {
                adListenerExecutor.execute(new Runnable() {
                    public void run() {
                        ((ExtendedAdListener) adListenerExecutor.getAdListener()).onAdExpired(ad);
                    }
                });
            }
        });
    }
}
