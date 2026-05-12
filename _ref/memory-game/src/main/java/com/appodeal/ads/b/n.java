package com.appodeal.ads.b;

import android.app.Activity;
import android.os.Build.VERSION;
import com.appodeal.ads.an;
import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.appodeal.ads.r;
import com.appodeal.ads.s;
import com.facebook.ads.InterstitialAd;

public class n extends r {
    private static o b;
    private InterstitialAd c;

    public static o getInstance(String str, String[] strArr) {
        if (b == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new n();
            }
            b = new o(str, f(), rVar);
        }
        return b;
    }

    public static String[] f() {
        return new String[]{"com.facebook.ads.InterstitialAdActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        if (VERSION.SDK_INT < 11) {
            q.b(i, i2, b);
            return;
        }
        this.c = new InterstitialAd(activity, ((s) com.appodeal.ads.n.p.get(i)).m.getString("facebook_key"));
        this.c.setAdListener(new o(b, i, i2));
        this.c.loadAd();
    }

    public void a(Activity activity, int i) {
        this.c.show();
    }
}
