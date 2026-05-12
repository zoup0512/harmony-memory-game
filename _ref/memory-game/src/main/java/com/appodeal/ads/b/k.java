package com.appodeal.ads.b;

import android.app.Activity;
import com.appodeal.ads.an;
import com.appodeal.ads.n;
import com.appodeal.ads.networks.i;
import com.appodeal.ads.networks.i.a;
import com.appodeal.ads.o;
import com.appodeal.ads.r;
import com.appodeal.ads.s;
import com.cmcm.adsdk.CMAdManagerFactory;
import com.cmcm.adsdk.interstitial.InterstitialAdManager;

public class k extends r {
    private static o b;
    private InterstitialAdManager c;

    public static o getInstance(String str, String[] strArr) {
        if (b == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new k();
            }
            b = new o(str, f(), rVar).d();
        }
        return b;
    }

    private static String[] f() {
        return new String[]{"com.cmcm.adsdk.interstitial.PicksInterstitialActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        String string = ((s) n.p.get(i)).m.getString("appId");
        String string2 = ((s) n.p.get(i)).m.getString("posId");
        i.a(activity, string, ((s) n.p.get(i)).m.getString("channelId"));
        CMAdManagerFactory.setImageDownloadListener(new a());
        this.c = new InterstitialAdManager(activity, string2);
        this.c.setInterstitialCallBack(new l(b, i, i2));
        this.c.loadAd();
    }

    public void a(Activity activity, int i) {
        this.c.showAd();
    }

    public boolean e() {
        return true;
    }
}
