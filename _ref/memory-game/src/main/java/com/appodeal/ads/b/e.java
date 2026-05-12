package com.appodeal.ads.b;

import android.app.Activity;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdTargetingOptions;
import com.amazon.device.ads.InterstitialAd;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.an;
import com.appodeal.ads.n;
import com.appodeal.ads.o;
import com.appodeal.ads.r;
import com.appodeal.ads.s;

public class e extends r {
    private static o c;
    public f b;
    private InterstitialAd d;

    public static o getInstance(String str, String[] strArr) {
        if (c == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new e();
            }
            c = new o(str, g(), rVar);
        }
        return c;
    }

    public static o f() {
        if (c == null) {
            r rVar = null;
            if (an.a("com.amazon.device.ads.AdRegistration")) {
                rVar = new e();
            }
            c = new o("amazon_ads", g(), rVar);
        }
        return c;
    }

    private static String[] g() {
        return new String[]{AdUtils.REQUIRED_ACTIVITY};
    }

    public void a(Activity activity, int i, int i2) {
        AdRegistration.setAppKey(((s) n.p.get(i)).m.getString("amazon_key"));
        if (AppodealSettings.a) {
            AdRegistration.enableTesting(true);
        }
        this.d = new InterstitialAd(activity);
        this.b = new f(c, i, i2);
        this.d.setListener(this.b);
        this.d.loadAd(new AdTargetingOptions().enableGeoLocation(true).setAdvancedOption("enableVideoAds", "false"));
    }

    public void a(Activity activity, int i) {
        this.d.showAd();
    }

    public boolean e() {
        return true;
    }

    public void a(boolean z) {
        AdRegistration.enableLogging(z);
    }
}
