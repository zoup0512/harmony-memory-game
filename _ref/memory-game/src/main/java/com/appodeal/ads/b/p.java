package com.appodeal.ads.b;

import android.app.Activity;
import android.os.Build.VERSION;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.appodeal.ads.n;
import com.appodeal.ads.networks.j;
import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.appodeal.ads.r;
import com.appodeal.ads.s;
import com.flurry.android.FlurryAgent;
import com.flurry.android.ads.FlurryAdInterstitial;
import java.lang.ref.WeakReference;

public class p extends r {
    private static o c;
    WeakReference<Activity> b;
    private FlurryAdInterstitial d;

    public static o getInstance(String str, String[] strArr) {
        if (c == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new p();
            }
            c = new o(str, f(), rVar);
        }
        return c;
    }

    private static String[] f() {
        return new String[]{"com.flurry.android.FlurryFullscreenTakeoverActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        String string = ((s) n.p.get(i)).m.getString("app_key");
        String string2 = ((s) n.p.get(i)).m.getString("placement_key");
        FlurryAgent.init(activity, string);
        FlurryAgent.onStartSession(activity);
        this.b = new WeakReference(activity);
        this.d = new FlurryAdInterstitial(activity, string2);
        this.d.setListener(new q(c, i, i2));
        this.d.setTargeting(j.a(activity));
        this.d.fetchAd();
    }

    public boolean e() {
        return true;
    }

    public void a(Activity activity, int i) {
        try {
            Activity activity2 = (Activity) this.b.get();
            boolean z;
            if (activity2 == null) {
                z = true;
            } else if (VERSION.SDK_INT < 18 || !activity2.isDestroyed()) {
                z = false;
            } else {
                z = true;
            }
            if (!this.d.isReady() || r0) {
                q.a(true);
            } else {
                this.d.displayAd();
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            q.a(true);
        }
    }

    public void a(boolean z) {
        FlurryAgent.setLogEnabled(z);
    }
}
