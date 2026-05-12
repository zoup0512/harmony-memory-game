package com.appodeal.ads.c;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.aa;
import com.appodeal.ads.an;
import com.appodeal.ads.networks.k;
import com.appodeal.ads.utils.a.b;
import com.appodeal.ads.v;
import com.appodeal.ads.w;
import com.appodeal.ads.y;
import com.appodeal.ads.z;
import com.mopub.common.AdType;
import org.json.JSONObject;
import org.nexage.sourcekit.mraid.MRAIDView;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;

public class m extends z {
    private static w b;
    private MRAIDView c;
    private int d;
    private int e;
    private b f;
    private String g;
    private long h;

    private class a implements com.appodeal.ads.networks.k.a {
        final /* synthetic */ m a;

        private a(m mVar) {
            this.a = mVar;
        }

        public void a(int i, int i2) {
            y.b(i, i2, m.b);
        }

        public void a(String str, int i, int i2, String str2) {
            try {
                this.a.a = str;
                n nVar = new n(m.b, i, i2, this.a.g, this.a.h);
                RtbInfo a = this.a.a(m.b.a(), i);
                this.a.c = new MRAIDView(Appodeal.b, null, this.a.a, null, nVar, nVar, this.a.d, this.a.e, a);
            } catch (Throwable e) {
                Appodeal.a(e);
                y.b(i, i2, m.b);
            }
        }
    }

    public static w getInstance(String str, String[] strArr) {
        if (b == null) {
            z zVar = null;
            if (an.a(strArr)) {
                zVar = new m();
            }
            b = new w(str, zVar);
        }
        return b;
    }

    public void a(Activity activity, int i, int i2) {
        JSONObject optJSONObject = ((aa) v.t.get(i)).l.optJSONObject("freq");
        this.g = ((aa) v.t.get(i)).l.optString("package");
        this.h = ((aa) v.t.get(i)).l.optLong("expiry");
        if (optJSONObject != null) {
            this.f = new b(activity, optJSONObject);
            if (!this.f.a((Context) activity)) {
                this.f = null;
                y.b(i, i2, b);
                return;
            }
        }
        this.f = null;
        this.a = ((aa) v.t.get(i)).l.optString(AdType.HTML);
        String optString = ((aa) v.t.get(i)).l.optString("mraid_url");
        if ((this.a == null || this.a.isEmpty() || this.a.equals(" ")) && (optString == null || optString.isEmpty() || optString.equals(" "))) {
            y.b(i, i2, b);
            return;
        }
        this.d = Integer.parseInt(((aa) v.t.get(i)).l.getString("width"));
        this.e = Integer.parseInt(((aa) v.t.get(i)).l.getString("height"));
        if (this.a == null || this.a.isEmpty() || this.a.equals(" ")) {
            k kVar = new k(activity, new a(), i, i2, optString);
            return;
        }
        n nVar = new n(b, i, i2, this.g, this.h);
        String str = null;
        String[] strArr = null;
        n nVar2 = nVar;
        n nVar3 = nVar;
        this.c = new MRAIDView(Appodeal.b, str, this.a, strArr, nVar2, nVar3, this.d, this.e, a(b.a(), i));
    }

    public ViewGroup c() {
        if (this.f != null) {
            this.f.b(Appodeal.b);
        }
        return this.c;
    }

    public int d() {
        return Math.round(((float) this.d) * an.i(Appodeal.b));
    }

    public int e() {
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
