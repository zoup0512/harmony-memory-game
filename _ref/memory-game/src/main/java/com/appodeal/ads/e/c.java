package com.appodeal.ads.e;

import android.app.Activity;
import android.os.Build.VERSION;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;
import com.appodeal.ads.ak;
import com.appodeal.ads.am;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;

public class c extends aq {
    private static ap b;
    private d c;
    private AppLovinIncentivizedInterstitial d;

    public static ap getInstance(String str, String[] strArr) {
        if (b == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new c();
            }
            b = new ap(str, g(), aqVar).a(18);
        }
        return b;
    }

    private static String[] g() {
        return new String[]{"com.applovin.adview.AppLovinInterstitialActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        if (VERSION.SDK_INT < 11) {
            am.b(i, i2, b);
            return;
        }
        AppLovinSdk instance = AppLovinSdk.getInstance(((ar) ak.m.get(i)).l.getString("applovin_key"), new AppLovinSdkSettings(), activity);
        instance.initializeSdk();
        instance.getTargetingData().setLocation(an.e(activity));
        this.c = new d(b, i, i2);
        this.d = AppLovinIncentivizedInterstitial.create(instance);
        this.d.preload(this.c);
    }

    public void a(Activity activity, int i) {
        this.d.show(activity, this.c, this.c, this.c, this.c);
    }
}
