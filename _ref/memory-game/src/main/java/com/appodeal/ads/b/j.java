package com.appodeal.ads.b;

import android.app.Activity;
import com.appodeal.ads.an;
import com.appodeal.ads.n;
import com.appodeal.ads.networks.g;
import com.appodeal.ads.networks.h;
import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.appodeal.ads.r;
import com.appodeal.ads.s;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBLogging.Level;

public class j extends r {
    private static o b;

    public static o getInstance(String str, String[] strArr) {
        if (b == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new j();
            }
            b = new o(str, f(), rVar);
        }
        return b;
    }

    private static String[] f() {
        return new String[]{"com.chartboost.sdk.CBImpressionActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        g.a(activity, ((s) n.p.get(i)).m.getString("chartboost_id"), ((s) n.p.get(i)).m.getString("chartboost_signature"));
        Chartboost.setDelegate(h.a().a(b, i, i2));
        if (Chartboost.hasInterstitial("Interstitial")) {
            q.a(i, i2, b);
        } else {
            Chartboost.cacheInterstitial("Interstitial");
        }
    }

    public void a(Activity activity, int i) {
        if (Chartboost.hasInterstitial("Interstitial")) {
            Chartboost.showInterstitial("Interstitial");
        } else {
            q.a(true);
        }
    }

    public void a(boolean z) {
        if (z) {
            Chartboost.setLoggingLevel(Level.ALL);
        } else {
            Chartboost.setLoggingLevel(Level.NONE);
        }
    }
}
