package com.appodeal.ads.e;

import android.app.Activity;
import com.appodeal.ads.ak;
import com.appodeal.ads.am;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.appodeal.ads.networks.g;
import com.appodeal.ads.networks.h;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBLogging.Level;

public class e extends aq {
    private static ap b;

    public static ap getInstance(String str, String[] strArr) {
        if (b == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new e();
            }
            b = new ap(str, g(), aqVar);
        }
        return b;
    }

    private static String[] g() {
        return new String[]{"com.chartboost.sdk.CBImpressionActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        if (g.a) {
            am.b(i, i2, b);
            return;
        }
        g.a(activity, ((ar) ak.m.get(i)).l.getString("chartboost_id"), ((ar) ak.m.get(i)).l.getString("chartboost_signature"));
        Chartboost.setDelegate(h.a().a(b, i, i2));
        if (Chartboost.hasRewardedVideo("RewardedVideo")) {
            am.a(i, i2, b);
        } else {
            Chartboost.cacheRewardedVideo("RewardedVideo");
        }
    }

    public void a(Activity activity, int i) {
        if (Chartboost.hasRewardedVideo("RewardedVideo")) {
            Chartboost.showRewardedVideo("RewardedVideo");
        } else {
            am.a(true);
        }
    }

    public boolean d() {
        return g.a;
    }

    public void b(boolean z) {
        g.a = z;
    }

    public void a(boolean z) {
        if (z) {
            Chartboost.setLoggingLevel(Level.ALL);
        } else {
            Chartboost.setLoggingLevel(Level.NONE);
        }
    }
}
