package com.appodeal.ads.g;

import android.app.Activity;
import android.content.Context;
import com.appodeal.ads.ah;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.appodeal.ads.networks.n;
import com.my.target.ads.InterstitialAd;

public class m extends aq {
    private static ap b;
    private InterstitialAd c;

    public static ap getInstance(String str, String[] strArr) {
        if (b == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new m();
            }
            b = new ap(str, g(), aqVar);
        }
        return b;
    }

    private static String[] g() {
        return new String[]{"com.my.target.ads.MyTargetActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        this.c = new InterstitialAd(((ar) ah.m.get(i)).l.getInt("mailru_slot_id"), activity, n.a((Context) activity));
        this.c.setListener(new n(b, i, i2));
        this.c.load();
    }

    public void a(Activity activity, int i) {
        this.c.show();
    }
}
