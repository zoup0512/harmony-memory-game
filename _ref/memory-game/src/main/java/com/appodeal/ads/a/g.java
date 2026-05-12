package com.appodeal.ads.a;

import android.app.Activity;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.appodeal.ads.k;
import com.appodeal.ads.l;
import com.appodeal.ads.networks.f;
import org.nexage.sourcekit.mraid.MRAIDView;
import org.nexage.sourcekit.mraid.MRAIDViewListener;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;

public class g extends k {
    private static h c;
    private MRAIDView d;

    private class a implements com.appodeal.ads.networks.f.a {
        final /* synthetic */ g a;

        private a(g gVar) {
            this.a = gVar;
        }

        public void a(int i, int i2) {
            j.b(i, i2, g.c);
        }

        public void a(Pair<String, Pair<Integer, Integer>> pair, int i, int i2) {
            try {
                this.a.a = (String) pair.first;
                int intValue = ((Integer) ((Pair) pair.second).first).intValue();
                this.a.b = ((Integer) ((Pair) pair.second).second).intValue();
                MRAIDViewListener qVar = new q(g.c, i, i2);
                if (intValue > com.appodeal.ads.g.d() || this.a.b > com.appodeal.ads.g.c()) {
                    j.b(i, i2, g.c);
                    return;
                }
                RtbInfo a = this.a.a(g.c.a(), i);
                this.a.d = new MRAIDView(Appodeal.b, null, this.a.a, null, qVar, qVar, intValue, this.a.b, a);
            } catch (Throwable e) {
                Appodeal.a(e);
                j.b(i, i2, g.c);
            }
        }
    }

    public static h getInstance(String str, String[] strArr) {
        if (c == null) {
            k kVar = null;
            if (an.a(strArr)) {
                kVar = new g();
            }
            c = new h(str, kVar);
        }
        return c;
    }

    public void a(Activity activity, int i, int i2) {
        Activity activity2 = activity;
        f fVar = new f(activity2, new a(), i, i2, ((l) com.appodeal.ads.g.x.get(i)).l.getString("url"));
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
