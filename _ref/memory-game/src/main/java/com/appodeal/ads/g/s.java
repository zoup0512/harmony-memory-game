package com.appodeal.ads.g;

import android.app.Activity;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.ah;
import com.appodeal.ads.aj;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.revmob.RevMob;
import com.revmob.RevMobAdsListener;
import com.revmob.ads.interstitial.RevMobFullscreen;
import com.revmob.ads.interstitial.a.b;

public class s extends aq {
    private static ap b;
    private static boolean e = false;
    private RevMob c;
    private RevMobFullscreen d;

    public static ap getInstance(String str, String[] strArr) {
        if (b == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new s();
            }
            b = new ap(str, h(), aqVar).d();
        }
        return b;
    }

    private static String[] h() {
        return new String[]{"com.revmob.FullscreenActivity"};
    }

    public void a(final Activity activity, final int i, final int i2) {
        String string = ((ar) ah.m.get(i)).l.getString("media_id");
        if (RevMob.session() != null) {
            this.d = RevMob.session().createVideo(activity, new t(b, i, i2));
            return;
        }
        this.c = RevMob.startWithListener(activity, new RevMobAdsListener(this) {
            final /* synthetic */ s d;

            public void onRevMobSessionNotStarted(String str) {
                aj.b(i, i2, s.b);
            }

            public void onRevMobSessionStarted() {
                this.d.d = this.d.c.createVideo(activity, new t(s.b, i, i2));
            }
        }, string);
        com.appodeal.ads.networks.s.a(activity, this.c);
    }

    public void a(Activity activity, int i) {
        try {
            this.a = an.d(((b) an.a(this.d, "c", false, 0)).o());
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        this.d.show();
    }

    public boolean e() {
        return true;
    }

    public boolean d() {
        return e;
    }

    public void b(boolean z) {
        e = z;
    }
}
