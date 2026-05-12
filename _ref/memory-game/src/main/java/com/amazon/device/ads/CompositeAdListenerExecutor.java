package com.amazon.device.ads;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class CompositeAdListenerExecutor extends AdListenerExecutor {
    private final List<AdListenerExecutor> adListenerExecutors = new ArrayList();

    public CompositeAdListenerExecutor(MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        super(null, mobileAdsLoggerFactory);
    }

    public void addAdListenerExecutor(AdListenerExecutor adListenerExecutor) {
        this.adListenerExecutors.add(adListenerExecutor);
    }

    private List<AdListenerExecutor> getAdListenerExecutors() {
        return this.adListenerExecutors;
    }

    public void onAdLoaded(Ad ad, AdProperties adProperties) {
        for (AdListenerExecutor onAdLoaded : getAdListenerExecutors()) {
            onAdLoaded.onAdLoaded(ad, adProperties);
        }
    }

    public void onAdFailedToLoad(Ad ad, AdError adError) {
        for (AdListenerExecutor onAdFailedToLoad : getAdListenerExecutors()) {
            onAdFailedToLoad.onAdFailedToLoad(ad, adError);
        }
    }

    public void onAdExpanded(Ad ad) {
        for (AdListenerExecutor onAdExpanded : getAdListenerExecutors()) {
            onAdExpanded.onAdExpanded(ad);
        }
    }

    public void onAdCollapsed(Ad ad) {
        for (AdListenerExecutor onAdCollapsed : getAdListenerExecutors()) {
            onAdCollapsed.onAdCollapsed(ad);
        }
    }

    public void onAdDismissed(Ad ad) {
        for (AdListenerExecutor onAdDismissed : getAdListenerExecutors()) {
            onAdDismissed.onAdDismissed(ad);
        }
    }

    public void onAdResized(Ad ad, Rect rect) {
        for (AdListenerExecutor onAdResized : getAdListenerExecutors()) {
            onAdResized.onAdResized(ad, rect);
        }
    }

    public void onAdExpired(Ad ad) {
        for (AdListenerExecutor onAdExpired : getAdListenerExecutors()) {
            onAdExpired.onAdExpired(ad);
        }
    }

    public void onSpecialUrlClicked(Ad ad, String str) {
        for (AdListenerExecutor onSpecialUrlClicked : getAdListenerExecutors()) {
            onSpecialUrlClicked.onSpecialUrlClicked(ad, str);
        }
    }

    public ActionCode onAdReceived(Ad ad, AdData adData) {
        Iterator it = getAdListenerExecutors().iterator();
        if (it.hasNext()) {
            return ((AdListenerExecutor) it.next()).onAdReceived(ad, adData);
        }
        return null;
    }

    public void onAdEvent(AdEvent adEvent) {
        for (AdListenerExecutor onAdEvent : getAdListenerExecutors()) {
            onAdEvent.onAdEvent(adEvent);
        }
    }
}
