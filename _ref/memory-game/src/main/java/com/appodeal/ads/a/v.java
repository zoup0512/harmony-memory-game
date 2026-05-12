package com.appodeal.ads.a;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.appodeal.ads.g;
import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.appodeal.ads.k;
import com.appodeal.ads.l;
import com.appodeal.ads.networks.p;
import com.appodeal.ads.networks.q;
import org.nexage.sourcekit.mraid.MRAIDView;
import org.nexage.sourcekit.mraid.MRAIDViewListener;

public class v extends k {
    private static h c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private MRAIDView j;

    private class a implements com.appodeal.ads.networks.q.a {
        final /* synthetic */ v a;

        private a(v vVar) {
            this.a = vVar;
        }

        public void a(int i, int i2) {
            j.b(i, i2, v.c);
        }

        public void a(p pVar, int i, int i2) {
            try {
                this.a.d = pVar.a;
                this.a.e = pVar.b;
                this.a.f = pVar.c;
                if (pVar.e > g.d() || pVar.f > g.c()) {
                    j.b(i, i2, v.c);
                } else if (pVar.d != null) {
                    this.a.a = pVar.d;
                    MRAIDViewListener qVar = new q(v.c, i, i2);
                    this.a.b = pVar.f;
                    MRAIDViewListener mRAIDViewListener = qVar;
                    this.a.j = new MRAIDView(Appodeal.b, null, this.a.a, null, qVar, mRAIDViewListener, pVar.e, pVar.f, this.a.a(v.c.a(), i));
                } else {
                    j.b(i, i2, v.c);
                }
            } catch (Throwable e) {
                Appodeal.a(e);
                j.b(i, i2, v.c);
            }
        }
    }

    public static h getInstance(String str, String[] strArr) {
        if (c == null) {
            k kVar = null;
            if (an.a(strArr)) {
                kVar = new v();
            }
            c = new h(str, kVar);
        }
        return c;
    }

    public void a(Activity activity, int i) {
        q qVar = new q(activity, null, i, 0, this.h, this.g, false);
    }

    public void b(Activity activity, int i) {
        q qVar = new q(activity, null, i, 0, this.i, this.g, false);
    }

    public void a(Activity activity, int i, int i2) {
        this.a = null;
        this.j = null;
        Activity activity2 = activity;
        q qVar = new q(activity2, new a(), i, i2, ((l) g.x.get(i)).l.getString("url"), null, true);
    }

    public ViewGroup c() {
        return this.j;
    }

    public void a(View view) {
        if (view instanceof MRAIDView) {
            ((MRAIDView) view).destroy();
        }
    }

    public void f() {
        this.g = this.d;
        this.h = this.e;
        this.i = this.f;
    }
}
