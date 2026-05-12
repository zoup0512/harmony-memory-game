package com.appodeal.ads.b;

import android.app.Activity;
import android.content.Context;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialActivity;
import com.appodeal.ads.an;
import com.appodeal.ads.as;
import com.appodeal.ads.n;
import com.appodeal.ads.networks.l;
import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.appodeal.ads.s;
import org.nexage.sourcekit.mraid.MRAIDInterstitial;
import org.nexage.sourcekit.mraid.MRAIDInterstitialListener;

public class r extends com.appodeal.ads.r {
    private static o b;
    private MRAIDInterstitial c;
    private InterstitialActivity d;

    private class a implements com.appodeal.ads.networks.l.a {
        final /* synthetic */ r a;

        private a(r rVar) {
            this.a = rVar;
        }

        public void a(int i, int i2) {
            q.b(i, i2, r.b);
        }

        public void a(String str, int i, int i2) {
            try {
                MRAIDInterstitialListener tVar = new t(r.b, i, i2);
                this.a.a = str;
                this.a.c = new MRAIDInterstitial(Appodeal.b, null, this.a.a, null, 320, 480, tVar, tVar, this.a.a(r.b.a(), i), false);
            } catch (Throwable e) {
                Appodeal.a(e);
                q.b(i, i2, r.b);
            }
        }
    }

    public static o getInstance(String str, String[] strArr) {
        if (b == null) {
            com.appodeal.ads.r rVar = null;
            if (an.a(strArr)) {
                rVar = new r();
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
        String string = ((s) n.p.get(i)).m.getString("url");
        Integer valueOf = Integer.valueOf(((s) n.p.get(i)).m.optInt("speed_limit", 100));
        if (valueOf.intValue() != -1 || an.b((Context) activity).c) {
            l lVar = new l(activity, new a(), i, i2, string, valueOf);
            return;
        }
        q.b(i, i2, b);
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
