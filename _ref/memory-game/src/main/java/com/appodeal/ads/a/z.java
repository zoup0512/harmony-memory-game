package com.appodeal.ads.a;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.appodeal.ads.g;
import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.appodeal.ads.k;
import com.appodeal.ads.l;
import com.appodeal.ads.networks.m;
import com.appodeal.ads.networks.t;
import org.nexage.sourcekit.mraid.MRAIDView;
import org.nexage.sourcekit.mraid.MRAIDViewListener;

public class z extends k {
    private static h c;
    private MRAIDView d;

    private class a implements com.appodeal.ads.networks.m.a {
        final /* synthetic */ z a;

        private a(z zVar) {
            this.a = zVar;
        }

        public void a(int i, int i2) {
            j.b(i, i2, z.c);
        }

        public void a(String str, int i, int i2) {
            if (!str.contains("appodealpassback")) {
                if (!str.contains("no-ads")) {
                    try {
                        MRAIDViewListener qVar = new q(z.c, i, i2);
                        this.a.a = str;
                        this.a.d = new MRAIDView(Appodeal.b, null, this.a.a, null, qVar, qVar, false, 320, 50, this.a.a(z.c.a(), i), false);
                        return;
                    } catch (Throwable e) {
                        Appodeal.a(e);
                        j.b(i, i2, z.c);
                        return;
                    }
                }
            }
            j.b(i, i2, z.c);
        }
    }

    public static h getInstance(String str, String[] strArr) {
        if (c == null) {
            k kVar = null;
            if (an.a(strArr)) {
                kVar = new z();
            }
            c = new h(str, kVar);
        }
        return c;
    }

    public void a(Activity activity, int i, int i2) {
        if (VERSION.SDK_INT < 17) {
            j.b(i, i2, c);
            return;
        }
        String string = ((l) g.x.get(i)).l.getString("rp_account");
        String string2 = ((l) g.x.get(i)).l.getString("rp_site");
        String string3 = ((l) g.x.get(i)).l.getString("rp_zonesize");
        String string4 = ((l) g.x.get(i)).l.getString("rp_adtype");
        String string5 = ((l) g.x.get(i)).l.getString("loadFunction");
        String a = t.a(string, string2, string3, string4);
        this.b = 50;
        m mVar = new m(activity, new a(), i, i2, a, string5);
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
