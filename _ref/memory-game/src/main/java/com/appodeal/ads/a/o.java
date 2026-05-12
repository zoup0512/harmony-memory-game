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
import org.nexage.sourcekit.mraid.MRAIDView;
import org.nexage.sourcekit.mraid.MRAIDViewListener;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;

public class o extends k {
    private static h c;
    private MRAIDView d;
    private int e;

    private class a implements com.appodeal.ads.networks.l.a {
        final /* synthetic */ o a;

        private a(o oVar) {
            this.a = oVar;
        }

        public void a(int i, int i2) {
            j.b(i, i2, o.c);
        }

        public void a(String str, int i, int i2) {
            try {
                MRAIDViewListener qVar = new q(o.c, i, i2);
                this.a.a = str;
                if (this.a.e > g.d() || this.a.b > g.c()) {
                    j.b(i, i2, o.c);
                    return;
                }
                RtbInfo a = this.a.a(o.c.a(), i);
                this.a.d = new MRAIDView(Appodeal.b, null, this.a.a, null, qVar, qVar, false, this.a.e, this.a.b, a, false);
            } catch (Throwable e) {
                Appodeal.a(e);
                j.b(i, i2, o.c);
            }
        }
    }

    public static h getInstance(String str, String[] strArr) {
        if (c == null) {
            k kVar = null;
            if (an.a(strArr)) {
                kVar = new o();
            }
            c = new h(str, kVar);
        }
        return c;
    }

    public void a(Activity activity, int i, int i2) {
        String string = ((l) g.x.get(i)).l.getString("url");
        this.e = ((l) g.x.get(i)).l.optInt("width", 320);
        this.b = ((l) g.x.get(i)).l.optInt("height", 50);
        if (this.e > g.d() || this.b > g.c()) {
            j.b(i, i2, c);
            return;
        }
        Integer valueOf = Integer.valueOf(((l) g.x.get(i)).l.optInt("speed_limit", 100));
        if (valueOf.intValue() != -1 || an.b((Context) activity).c) {
            com.appodeal.ads.networks.l lVar = new com.appodeal.ads.networks.l(activity, new a(), i, i2, string, valueOf);
            return;
        }
        j.b(i, i2, c);
    }

    public ViewGroup c() {
        this.d.show();
        return this.d;
    }

    public void a(View view) {
        if (view instanceof MRAIDView) {
            ((MRAIDView) view).destroy();
        }
    }
}
