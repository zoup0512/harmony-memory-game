package com.appodeal.ads.a;

import android.app.Activity;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.appodeal.ads.g;
import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.appodeal.ads.k;
import com.appodeal.ads.l;
import com.appodeal.ads.networks.u;
import com.appodeal.ads.networks.v;
import org.nexage.sourcekit.mraid.MRAIDView;
import org.nexage.sourcekit.mraid.MRAIDViewListener;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;

public class aa extends k {
    private static h c;
    private MRAIDView d;
    private int e;

    private class a implements com.appodeal.ads.networks.v.a {
        final /* synthetic */ aa a;

        private a(aa aaVar) {
            this.a = aaVar;
        }

        public void a(int i, int i2) {
            j.b(i, i2, aa.c);
        }

        public void a(Pair<String, String> pair, int i, int i2) {
            try {
                if (u.a == null && pair.second != null) {
                    u.a = (String) pair.second;
                }
                this.a.a = (String) pair.first;
                MRAIDViewListener qVar = new q(aa.c, i, i2);
                RtbInfo a = this.a.a(aa.c.a(), i);
                this.a.d = new MRAIDView(Appodeal.b, null, this.a.a, null, qVar, qVar, this.a.e, this.a.b, a);
            } catch (Throwable e) {
                Appodeal.a(e);
                j.b(i, i2, aa.c);
            }
        }
    }

    public static h getInstance(String str, String[] strArr) {
        if (c == null) {
            k kVar = null;
            if (an.a(strArr)) {
                kVar = new aa();
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
        v vVar = new v(activity, new a(), i, i2, string, u.a);
    }

    public ViewGroup c() {
        return this.d;
    }

    public void a(View view) {
        if (view instanceof MRAIDView) {
            ((MRAIDView) view).destroy();
        }
    }
}
