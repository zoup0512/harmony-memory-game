package com.appodeal.ads.b;

import android.app.Activity;
import android.util.Pair;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialActivity;
import com.appodeal.ads.an;
import com.appodeal.ads.as;
import com.appodeal.ads.n;
import com.appodeal.ads.networks.f;
import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.appodeal.ads.r;
import com.appodeal.ads.s;
import org.nexage.sourcekit.mraid.MRAIDInterstitial;
import org.nexage.sourcekit.mraid.MRAIDInterstitialListener;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;

public class i extends r {
    private static o b;
    private MRAIDInterstitial c;
    private InterstitialActivity d;

    private class a implements com.appodeal.ads.networks.f.a {
        final /* synthetic */ i a;

        private a(i iVar) {
            this.a = iVar;
        }

        public void a(int i, int i2) {
            q.b(i, i2, i.b);
        }

        public void a(Pair<String, Pair<Integer, Integer>> pair, int i, int i2) {
            MRAIDInterstitialListener tVar = new t(i.b, i, i2);
            this.a.a = (String) pair.first;
            RtbInfo a = this.a.a(i.b.a(), i);
            this.a.c = new MRAIDInterstitial(Appodeal.b, null, this.a.a, null, ((Integer) ((Pair) pair.second).first).intValue(), ((Integer) ((Pair) pair.second).first).intValue(), tVar, tVar, a);
        }
    }

    public static o getInstance(String str, String[] strArr) {
        if (b == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new i();
            }
            b = new o(str, g(), rVar);
        }
        return b;
    }

    private static String[] g() {
        return new String[0];
    }

    public void a(Activity activity, int i, int i2) {
        this.c = null;
        Activity activity2 = activity;
        f fVar = new f(activity2, new a(), i, i2, ((s) n.p.get(i)).m.getString("url"));
    }

    public void a(Activity activity, int i) {
        an.a(activity, b, i);
    }

    public void a(InterstitialActivity interstitialActivity, int i) {
        this.d = interstitialActivity;
        as.a(interstitialActivity);
        this.c.show(interstitialActivity);
        q.a(i, b);
    }

    public InterstitialActivity c() {
        return this.d;
    }
}
