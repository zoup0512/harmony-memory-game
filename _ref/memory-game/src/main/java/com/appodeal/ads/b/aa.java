package com.appodeal.ads.b;

import android.app.Activity;
import android.content.Context;
import com.appodeal.ads.InterstitialActivity;
import com.appodeal.ads.an;
import com.appodeal.ads.as;
import com.appodeal.ads.n;
import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.appodeal.ads.r;
import com.appodeal.ads.s;
import com.mopub.common.AdType;
import org.nexage.sourcekit.mraid.MRAIDInterstitial;
import org.nexage.sourcekit.mraid.MRAIDInterstitialListener;

public class aa extends r {
    private static o b;
    private MRAIDInterstitial c;
    private InterstitialActivity d;

    public static o getInstance(String str, String[] strArr) {
        if (b == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new aa();
            }
            b = new o(str, f(), rVar);
        }
        return b;
    }

    private static String[] f() {
        return new String[0];
    }

    public void a(Activity activity, int i, int i2) {
        this.a = ((s) n.p.get(i)).m.getString(AdType.HTML);
        int parseInt = Integer.parseInt(((s) n.p.get(i)).m.getString("width"));
        int parseInt2 = Integer.parseInt(((s) n.p.get(i)).m.getString("height"));
        MRAIDInterstitialListener tVar = new t(b, i, i2);
        Context context = activity;
        this.c = new MRAIDInterstitial(context, null, this.a, null, parseInt, parseInt2, tVar, tVar, a(b.a(), i), true);
    }

    public void a(Activity activity, int i) {
        an.a(activity, b, i);
    }

    public void a(InterstitialActivity interstitialActivity, int i) {
        this.d = interstitialActivity;
        as.a(interstitialActivity);
        if (this.c != null) {
            this.c.show(interstitialActivity);
            q.a(i, b);
        }
    }

    public InterstitialActivity c() {
        return this.d;
    }

    public boolean e() {
        return true;
    }
}
