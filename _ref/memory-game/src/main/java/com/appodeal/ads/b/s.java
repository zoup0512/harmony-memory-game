package com.appodeal.ads.b;

import android.app.Activity;
import android.content.Context;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialActivity;
import com.appodeal.ads.an;
import com.appodeal.ads.as;
import com.appodeal.ads.n;
import com.appodeal.ads.networks.k;
import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.appodeal.ads.r;
import com.appodeal.ads.utils.a.b;
import com.mopub.common.AdType;
import org.json.JSONObject;
import org.nexage.sourcekit.mraid.MRAIDInterstitial;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;

public class s extends r {
    private static o b;
    private MRAIDInterstitial c;
    private InterstitialActivity d;
    private b e;
    private int f;
    private int g;
    private String h;
    private long i;

    private class a implements com.appodeal.ads.networks.k.a {
        final /* synthetic */ s a;

        private a(s sVar) {
            this.a = sVar;
        }

        public void a(int i, int i2) {
            q.b(i, i2, s.b);
        }

        public void a(String str, int i, int i2, String str2) {
            try {
                this.a.a = str;
                RtbInfo a = this.a.a(s.b.a(), i);
                t tVar = new t(s.b, i, i2, this.a.h, this.a.i);
                this.a.c = new MRAIDInterstitial(Appodeal.b, null, this.a.a, null, this.a.f, this.a.g, tVar, tVar, a);
            } catch (Throwable e) {
                Appodeal.a(e);
                q.b(i, i2, s.b);
            }
        }
    }

    public static o getInstance(String str, String[] strArr) {
        if (b == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new s();
            }
            b = new o(str, g(), rVar);
        }
        return b;
    }

    private static String[] g() {
        return new String[0];
    }

    public void a(Activity activity, int i, int i2) {
        JSONObject optJSONObject = ((com.appodeal.ads.s) n.p.get(i)).m.optJSONObject("freq");
        this.h = ((com.appodeal.ads.s) n.p.get(i)).m.optString("package");
        this.i = ((com.appodeal.ads.s) n.p.get(i)).m.optLong("expiry");
        if (optJSONObject != null) {
            this.e = new b(activity, optJSONObject);
            if (!this.e.a((Context) activity)) {
                this.e = null;
                q.b(i, i2, b);
                return;
            }
        }
        this.e = null;
        this.a = ((com.appodeal.ads.s) n.p.get(i)).m.optString(AdType.HTML);
        String optString = ((com.appodeal.ads.s) n.p.get(i)).m.optString("mraid_url");
        if ((this.a == null || this.a.isEmpty() || this.a.equals(" ")) && (optString == null || optString.isEmpty() || optString.equals(" "))) {
            q.b(i, i2, b);
            return;
        }
        this.f = Integer.parseInt(((com.appodeal.ads.s) n.p.get(i)).m.getString("width"));
        this.g = Integer.parseInt(((com.appodeal.ads.s) n.p.get(i)).m.getString("height"));
        if (this.a == null || this.a.isEmpty() || this.a.equals(" ")) {
            k kVar = new k(activity, new a(), i, i2, optString);
            return;
        }
        RtbInfo a = a(b.a(), i);
        t tVar = new t(b, i, i2, this.h, this.i);
        this.c = new MRAIDInterstitial(activity, null, this.a, null, this.f, this.g, tVar, tVar, a);
    }

    public void a(Activity activity, int i) {
        if (this.e != null) {
            this.e.b(activity);
        }
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
