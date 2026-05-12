package com.appodeal.ads.b;

import android.app.Activity;
import android.os.Build.VERSION;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;
import com.appodeal.ads.an;
import com.appodeal.ads.n;
import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.appodeal.ads.r;
import com.appodeal.ads.s;

public class g extends r {
    private static o c;
    AppLovinAd b;
    private h d;
    private AppLovinSdk e;

    public static o getInstance(String str, String[] strArr) {
        if (c == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new g();
            }
            c = new o(str, f(), rVar).a(18);
        }
        return c;
    }

    private static String[] f() {
        return new String[]{"com.applovin.adview.AppLovinInterstitialActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        if (VERSION.SDK_INT < 11) {
            q.b(i, i2, c);
            return;
        }
        this.e = AppLovinSdk.getInstance(((s) n.p.get(i)).m.getString("applovin_key"), new AppLovinSdkSettings(), activity);
        this.e.initializeSdk();
        this.e.getTargetingData().setLocation(an.e(activity));
        AppLovinAdService adService = this.e.getAdService();
        this.d = new h(c, i, i2);
        adService.loadNextAd(AppLovinAdSize.INTERSTITIAL, this.d);
    }

    public void a(Activity activity, int i) {
        AppLovinInterstitialAdDialog create = AppLovinInterstitialAd.create(this.e, activity);
        create.setAdDisplayListener(this.d);
        create.setAdClickListener(this.d);
        create.showAndRender(this.b);
    }
}
