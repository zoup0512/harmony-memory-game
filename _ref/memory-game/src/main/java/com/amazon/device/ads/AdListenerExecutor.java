package com.amazon.device.ads;

import android.graphics.Rect;
import com.amazon.device.ads.ThreadUtils.ExecutionStyle;
import com.amazon.device.ads.ThreadUtils.ExecutionThread;
import com.amazon.device.ads.ThreadUtils.ThreadRunner;

class AdListenerExecutor {
    private static final String LOGTAG = AdListenerExecutor.class.getSimpleName();
    private final AdListener adListener;
    private final MobileAdsLogger logger;
    private OnAdEventCommand onAdEventCommand;
    private OnAdExpiredCommand onAdExpiredCommand;
    private OnAdReceivedCommand onAdReceivedCommand;
    private OnAdResizedCommand onAdResizedCommand;
    private OnSpecialUrlClickedCommand onSpecialUrlClickedCommand;
    private final ThreadRunner threadRunner;

    public AdListenerExecutor(AdListener adListener, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this(adListener, ThreadUtils.getThreadRunner(), mobileAdsLoggerFactory);
    }

    AdListenerExecutor(AdListener adListener, ThreadRunner threadRunner, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this.adListener = adListener;
        this.threadRunner = threadRunner;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
    }

    AdListener getAdListener() {
        return this.adListener;
    }

    public void onAdLoaded(final Ad ad, final AdProperties adProperties) {
        execute(new Runnable() {
            public void run() {
                AdListenerExecutor.this.getAdListener().onAdLoaded(ad, adProperties);
            }
        });
    }

    public void onAdFailedToLoad(final Ad ad, final AdError adError) {
        execute(new Runnable() {
            public void run() {
                AdListenerExecutor.this.getAdListener().onAdFailedToLoad(ad, adError);
            }
        });
    }

    public void onAdExpanded(final Ad ad) {
        execute(new Runnable() {
            public void run() {
                AdListenerExecutor.this.getAdListener().onAdExpanded(ad);
            }
        });
    }

    public void onAdCollapsed(final Ad ad) {
        execute(new Runnable() {
            public void run() {
                AdListenerExecutor.this.getAdListener().onAdCollapsed(ad);
            }
        });
    }

    public void onAdDismissed(final Ad ad) {
        execute(new Runnable() {
            public void run() {
                AdListenerExecutor.this.getAdListener().onAdDismissed(ad);
            }
        });
    }

    public void onAdResized(Ad ad, Rect rect) {
        if (this.onAdResizedCommand == null) {
            this.logger.d("Ad listener called - Ad Resized.");
        } else {
            this.onAdResizedCommand.onAdResized(ad, rect);
        }
    }

    public void onAdExpired(Ad ad) {
        if (this.onAdExpiredCommand == null) {
            this.logger.d("Ad listener called - Ad Expired.");
        } else {
            this.onAdExpiredCommand.onAdExpired(ad);
        }
    }

    public void onSpecialUrlClicked(Ad ad, String str) {
        if (this.onSpecialUrlClickedCommand == null) {
            this.logger.d("Ad listener called - Special Url Clicked.");
        } else {
            this.onSpecialUrlClickedCommand.onSpecialUrlClicked(ad, str);
        }
    }

    public ActionCode onAdReceived(Ad ad, AdData adData) {
        if (this.onAdReceivedCommand != null) {
            return this.onAdReceivedCommand.onAdReceived(ad, adData);
        }
        this.logger.d("Ad listener called - Ad Received.");
        return ActionCode.DISPLAY;
    }

    public void onAdEvent(AdEvent adEvent) {
        if (this.onAdEventCommand == null) {
            this.logger.d("Ad listener called - Ad Event: " + adEvent);
        } else {
            this.onAdEventCommand.onAdEvent(adEvent);
        }
    }

    protected void execute(Runnable runnable) {
        this.threadRunner.execute(runnable, ExecutionStyle.SCHEDULE, ExecutionThread.MAIN_THREAD);
    }

    public void setOnAdEventCommand(OnAdEventCommand onAdEventCommand) {
        this.onAdEventCommand = onAdEventCommand;
    }

    public void setOnAdResizedCommand(OnAdResizedCommand onAdResizedCommand) {
        this.onAdResizedCommand = onAdResizedCommand;
    }

    public void setOnAdExpiredCommand(OnAdExpiredCommand onAdExpiredCommand) {
        this.onAdExpiredCommand = onAdExpiredCommand;
    }

    public void setOnSpecialUrlClickedCommand(OnSpecialUrlClickedCommand onSpecialUrlClickedCommand) {
        this.onSpecialUrlClickedCommand = onSpecialUrlClickedCommand;
    }

    public void setOnAdReceivedCommand(OnAdReceivedCommand onAdReceivedCommand) {
        this.onAdReceivedCommand = onAdReceivedCommand;
    }
}
