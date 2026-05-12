package com.appodeal.ads.networks;

import com.applovin.sdk.AppLovinAd;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;

public class d {
    public static String a(AppLovinAd appLovinAd) {
        if (appLovinAd != null) {
            try {
                Object a = an.a(appLovinAd, "d", false, 0);
                if (a == null) {
                    return null;
                }
                Object a2 = an.a(appLovinAd, "k", false, 0);
                if (a2 == null) {
                    return null;
                }
                String str;
                if (((String) a2).contains("stage")) {
                    str = "http://stage-vid.applovin.com/" + a.toString();
                } else {
                    str = "http://vid.applovin.com/" + a.toString();
                }
                return an.d(str);
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
        return null;
    }
}
