package com.appodeal.ads.networks;

import com.appodeal.ads.ao;
import com.appodeal.ads.g.a;
import com.jirbo.adcolony.AdColonyAdAvailabilityListener;

class b implements AdColonyAdAvailabilityListener {
    b() {
    }

    public void onAdColonyAdAvailabilityChange(boolean z, String str) {
        if (a.c.contains(str)) {
            if (z) {
                a.b = ao.a.AVAILABLE;
            } else {
                a.b = ao.a.NOT_AVAILABLE_AFTER_DELAY;
            }
        }
        if (com.appodeal.ads.e.a.c.contains(str)) {
            if (z) {
                com.appodeal.ads.e.a.b = ao.a.AVAILABLE;
            } else {
                com.appodeal.ads.e.a.b = ao.a.NOT_AVAILABLE_AFTER_DELAY;
            }
        }
        if (!com.appodeal.ads.native_ad.a.c.contains(str)) {
            return;
        }
        if (z) {
            com.appodeal.ads.native_ad.a.b = ao.a.AVAILABLE;
        } else {
            com.appodeal.ads.native_ad.a.b = ao.a.NOT_AVAILABLE_AFTER_DELAY;
        }
    }
}
