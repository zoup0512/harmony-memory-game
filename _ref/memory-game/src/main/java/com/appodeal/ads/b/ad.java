package com.appodeal.ads.b;

import android.app.Activity;
import android.os.Build.VERSION;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialActivity;
import com.appodeal.ads.an;
import com.appodeal.ads.as;
import com.appodeal.ads.n;
import com.appodeal.ads.networks.m;
import com.appodeal.ads.networks.t;
import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.appodeal.ads.r;
import com.appodeal.ads.s;
import org.nexage.sourcekit.mraid.MRAIDInterstitial;
import org.nexage.sourcekit.mraid.MRAIDInterstitialListener;

public class ad extends r {
    private static o b;
    private MRAIDInterstitial c;
    private InterstitialActivity d;

    private class a implements com.appodeal.ads.networks.m.a {
        final /* synthetic */ ad a;

        private a(ad adVar) {
            this.a = adVar;
        }

        public void a(int i, int i2) {
            q.b(i, i2, ad.b);
        }

        public void a(String str, int i, int i2) {
            if (str.contains("appodealpassback") || str.contains("no-ads")) {
                q.b(i, i2, ad.b);
                return;
            }
            try {
                MRAIDInterstitialListener tVar = new t(ad.b, i, i2);
                this.a.a = str;
                this.a.c = new MRAIDInterstitial(Appodeal.b, null, this.a.a, null, 320, 480, tVar, tVar, this.a.a(ad.b.a(), i));
            } catch (Throwable e) {
                Appodeal.a(e);
                q.b(i, i2, ad.b);
            }
        }
    }

    public static o getInstance(String str, String[] strArr) {
        if (b == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new ad();
            }
            b = new o(str, g(), rVar);
        }
        return b;
    }

    private static String[] g() {
        return new String[0];
    }

    public void a(Activity activity, int i, int i2) {
        if (VERSION.SDK_INT < 17) {
            q.b(i, i2, b);
            return;
        }
        this.c = null;
        String string = ((s) n.p.get(i)).m.getString("rp_account");
        String string2 = ((s) n.p.get(i)).m.getString("rp_site");
        String string3 = ((s) n.p.get(i)).m.getString("rp_zonesize");
        String string4 = ((s) n.p.get(i)).m.getString("rp_adtype");
        String string5 = ((s) n.p.get(i)).m.getString("loadFunction");
        String a = t.a(string, string2, string3, string4);
        m mVar = new m(activity, new a(), i, i2, a, string5);
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
