package com.appodeal.ads.b;

import android.app.Activity;
import com.appodeal.ads.an;
import com.appodeal.ads.n;
import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.appodeal.ads.r;
import com.appodeal.ads.s;
import com.revmob.RevMob;
import com.revmob.RevMobAdsListener;
import com.revmob.ads.interstitial.RevMobFullscreen;

public class ab extends r {
    private static o b;
    private RevMob c;
    private RevMobFullscreen d;

    public static o getInstance(String str, String[] strArr) {
        if (b == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new ab();
            }
            b = new o(str, g(), rVar).d();
        }
        return b;
    }

    private static String[] g() {
        return new String[]{"com.revmob.FullscreenActivity"};
    }

    public void a(final Activity activity, final int i, final int i2) {
        String string = ((s) n.p.get(i)).m.getString("media_id");
        if (RevMob.session() != null) {
            this.d = RevMob.session().createFullscreen(activity, new ac(b, i, i2));
            return;
        }
        this.c = RevMob.startWithListener(activity, new RevMobAdsListener(this) {
            final /* synthetic */ ab d;

            public void onRevMobSessionNotStarted(String str) {
                q.b(i, i2, ab.b);
            }

            public void onRevMobSessionStarted() {
                this.d.d = this.d.c.createFullscreen(activity, new ac(ab.b, i, i2));
            }
        }, string);
        com.appodeal.ads.networks.s.a(activity, this.c);
    }

    public void a(Activity activity, int i) {
        this.d.show();
    }

    public boolean e() {
        return true;
    }
}
