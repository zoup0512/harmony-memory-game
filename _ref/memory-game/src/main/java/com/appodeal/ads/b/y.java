package com.appodeal.ads.b;

import android.app.Activity;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialActivity;
import com.appodeal.ads.an;
import com.appodeal.ads.as;
import com.appodeal.ads.n;
import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.appodeal.ads.r;
import com.appodeal.ads.s;
import org.nexage.sourcekit.mraid.MRAIDInterstitial;
import org.nexage.sourcekit.mraid.MRAIDInterstitialListener;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;

public class y extends r {
    private static o b;
    private int c;
    private int d;
    private MRAIDInterstitial e;
    private InterstitialActivity f;

    private class a implements com.appodeal.ads.networks.o.a {
        final /* synthetic */ y a;

        private a(y yVar) {
            this.a = yVar;
        }

        public void a(int i, int i2) {
            q.b(i, i2, y.b);
        }

        public void a(String str, int i, int i2) {
            MRAIDInterstitialListener tVar = new t(y.b, i, i2);
            this.a.a = str;
            RtbInfo a = this.a.a(y.b.a(), i);
            this.a.e = new MRAIDInterstitial(Appodeal.b, null, this.a.a, null, this.a.c, this.a.d, tVar, tVar, a);
        }
    }

    public static o getInstance(String str, String[] strArr) {
        if (b == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new y();
            }
            b = new o(str, g(), rVar);
        }
        return b;
    }

    private static String[] g() {
        return new String[0];
    }

    public void a(Activity activity, int i, int i2) {
        this.e = null;
        String string = ((s) n.p.get(i)).m.getString("url");
        this.c = Integer.parseInt(((s) n.p.get(i)).m.getString("width"));
        this.d = Integer.parseInt(((s) n.p.get(i)).m.getString("height"));
        com.appodeal.ads.networks.o oVar = new com.appodeal.ads.networks.o(activity, new a(), i, i2, string);
    }

    public void a(Activity activity, int i) {
        an.a(activity, b, i);
    }

    public void a(InterstitialActivity interstitialActivity, int i) {
        this.f = interstitialActivity;
        as.a(interstitialActivity);
        this.e.show(interstitialActivity);
        q.a(i, b);
    }

    public InterstitialActivity c() {
        return this.f;
    }
}
