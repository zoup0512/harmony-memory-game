package com.appodeal.ads.a;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.appodeal.ads.g;
import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.appodeal.ads.k;
import com.appodeal.ads.l;
import com.appodeal.ads.utils.a.b;
import com.mopub.common.AdType;
import org.json.JSONObject;
import org.nexage.sourcekit.mraid.MRAIDView;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;

public class p extends k {
    private static h c;
    private MRAIDView d;
    private int e;
    private b f;
    private String g;
    private long h;

    private class a implements com.appodeal.ads.networks.k.a {
        final /* synthetic */ p a;

        private a(p pVar) {
            this.a = pVar;
        }

        public void a(int i, int i2) {
            j.b(i, i2, p.c);
        }

        public void a(String str, int i, int i2, String str2) {
            try {
                this.a.a = str;
                q qVar = new q(p.c, i, i2, this.a.g, this.a.h);
                RtbInfo a = this.a.a(p.c.a(), i);
                this.a.d = new MRAIDView(Appodeal.b, null, this.a.a, null, qVar, qVar, this.a.e, this.a.b, a);
            } catch (Throwable e) {
                Appodeal.a(e);
                j.b(i, i2, p.c);
            }
        }
    }

    public static h getInstance(String str, String[] strArr) {
        if (c == null) {
            k kVar = null;
            if (an.a(strArr)) {
                kVar = new p();
            }
            c = new h(str, kVar);
        }
        return c;
    }

    public void a(Activity activity, int i, int i2) {
        JSONObject optJSONObject = ((l) g.x.get(i)).l.optJSONObject("freq");
        this.g = ((l) g.x.get(i)).l.optString("package");
        this.h = ((l) g.x.get(i)).l.optLong("expiry");
        if (optJSONObject != null) {
            this.f = new b(activity, optJSONObject);
            if (!this.f.a((Context) activity)) {
                this.f = null;
                j.b(i, i2, c);
                return;
            }
        }
        this.f = null;
        this.a = ((l) g.x.get(i)).l.optString(AdType.HTML);
        String optString = ((l) g.x.get(i)).l.optString("mraid_url");
        if ((this.a == null || this.a.isEmpty() || this.a.equals(" ")) && (optString == null || optString.isEmpty() || optString.equals(" "))) {
            j.b(i, i2, c);
            return;
        }
        this.e = Integer.parseInt(((l) g.x.get(i)).l.getString("width"));
        this.b = Integer.parseInt(((l) g.x.get(i)).l.getString("height"));
        if (this.e > g.d() || this.b > g.c()) {
            this.e = (this.e * 50) / this.b;
            this.b = 50;
            if (this.e > g.d() || this.b > g.c()) {
                j.b(i, i2, c);
                return;
            }
        }
        if (this.a == null || this.a.isEmpty() || this.a.equals(" ")) {
            com.appodeal.ads.networks.k kVar = new com.appodeal.ads.networks.k(activity, new a(), i, i2, optString);
            return;
        }
        q qVar = new q(c, i, i2, this.g, this.h);
        String str = null;
        String[] strArr = null;
        q qVar2 = qVar;
        q qVar3 = qVar;
        this.d = new MRAIDView(Appodeal.b, str, this.a, strArr, qVar2, qVar3, this.e, this.b, a(c.a(), i));
    }

    public ViewGroup c() {
        if (this.f != null) {
            this.f.b(Appodeal.b);
        }
        return this.d;
    }

    public int d() {
        return Math.round(((float) this.e) * an.i(Appodeal.b));
    }

    public void a(View view) {
        if (view instanceof MRAIDView) {
            ((MRAIDView) view).destroy();
        }
    }

    public boolean g() {
        return true;
    }
}
