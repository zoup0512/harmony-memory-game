package com.appodeal.ads.b;

import android.app.Activity;
import android.content.Context;
import com.appodeal.ads.an;
import com.appodeal.ads.n;
import com.appodeal.ads.o;
import com.appodeal.ads.r;
import com.appodeal.ads.s;
import com.my.target.ads.InterstitialAd;

public class u extends r {
    private static o b;
    private InterstitialAd c;

    public static o getInstance(String str, String[] strArr) {
        if (b == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new u();
            }
            b = new o(str, f(), rVar);
        }
        return b;
    }

    private static String[] f() {
        return new String[]{"com.my.target.ads.MyTargetActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        this.c = new InterstitialAd(((s) n.p.get(i)).m.getInt("mailru_slot_id"), activity, com.appodeal.ads.networks.n.a((Context) activity));
        this.c.setListener(new v(b, i, i2));
        this.c.load();
    }

    public void a(Activity activity, int i) {
        this.c.show();
    }
}
