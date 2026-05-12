package com.appodeal.ads.b;

import android.app.Activity;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialActivity;
import com.appodeal.ads.an;
import com.appodeal.ads.as;
import com.appodeal.ads.n;
import com.appodeal.ads.networks.p;
import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.appodeal.ads.r;
import com.appodeal.ads.s;
import org.nexage.sourcekit.mraid.MRAIDInterstitial;
import org.nexage.sourcekit.mraid.MRAIDInterstitialListener;

public class z extends r {
    private static o b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private MRAIDInterstitial i;
    private InterstitialActivity j;

    private class a implements com.appodeal.ads.networks.q.a {
        final /* synthetic */ z a;

        private a(z zVar) {
            this.a = zVar;
        }

        public void a(int i, int i2) {
            q.b(i, i2, z.b);
        }

        public void a(p pVar, int i, int i2) {
            try {
                this.a.c = pVar.a;
                this.a.d = pVar.b;
                this.a.e = pVar.c;
                if (pVar.d != null) {
                    this.a.a = pVar.d;
                    MRAIDInterstitialListener tVar = new t(z.b, i, i2);
                    this.a.i = new MRAIDInterstitial(Appodeal.b, null, this.a.a, null, pVar.e, pVar.f, tVar, tVar, this.a.a(z.b.a(), i));
                    return;
                }
                q.b(i, i2, z.b);
            } catch (Throwable e) {
                Appodeal.a(e);
                q.b(i, i2, z.b);
            }
        }
    }

    public static o getInstance(String str, String[] strArr) {
        if (b == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new z();
            }
            b = new o(str, g(), rVar);
        }
        return b;
    }

    private static String[] g() {
        return new String[0];
    }

    public void b(Activity activity, int i) {
        com.appodeal.ads.networks.q qVar = new com.appodeal.ads.networks.q(activity, null, i, 0, this.g, this.f, false);
    }

    public void c(Activity activity, int i) {
        com.appodeal.ads.networks.q qVar = new com.appodeal.ads.networks.q(activity, null, i, 0, this.h, this.f, false);
    }

    public void a(Activity activity, int i, int i2) {
        this.a = null;
        this.i = null;
        Activity activity2 = activity;
        com.appodeal.ads.networks.q qVar = new com.appodeal.ads.networks.q(activity2, new a(), i, i2, ((s) n.p.get(i)).m.getString("url"), null, true);
    }

    public void a(Activity activity, int i) {
        an.a(activity, b, i);
    }

    public void a(InterstitialActivity interstitialActivity, int i) {
        this.j = interstitialActivity;
        as.a(interstitialActivity);
        this.i.show(interstitialActivity);
        q.a(i, b);
    }

    public InterstitialActivity c() {
        return this.j;
    }

    public void d() {
        this.f = this.c;
        this.g = this.d;
        this.h = this.e;
    }
}
