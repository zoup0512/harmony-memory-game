package com.appodeal.ads.g;

import android.app.Activity;
import android.os.Build.VERSION;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;
import com.appodeal.ads.ah;
import com.appodeal.ads.aj;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;

public class c extends aq {
    private static ap c;
    AppLovinAd b;
    private d d;
    private AppLovinSdk e;

    public static ap getInstance(String str, String[] strArr) {
        if (c == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new c();
            }
            c = new ap(str, g(), aqVar).a(18);
        }
        return c;
    }

    private static String[] g() {
        return new String[]{"com.applovin.adview.AppLovinInterstitialActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        if (VERSION.SDK_INT < 11) {
            aj.b(i, i2, c);
            return;
        }
        this.e = AppLovinSdk.getInstance(((ar) ah.m.get(i)).l.getString("applovin_key"), new AppLovinSdkSettings(), activity);
        this.e.initializeSdk();
        this.e.getTargetingData().setLocation(an.e(activity));
        this.d = new d(c, i, i2);
        this.e.getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, this.d);
    }

    public void a(Activity activity, int i) {
        AppLovinInterstitialAdDialog create = AppLovinInterstitialAd.create(this.e, activity);
        create.setAdDisplayListener(this.d);
        create.setAdClickListener(this.d);
        create.setAdVideoPlaybackListener(this.d);
        create.showAndRender(this.b);
    }
}
