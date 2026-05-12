package com.appodeal.ads.e;

import android.app.Activity;
import android.os.Build.VERSION;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.ak;
import com.appodeal.ads.am;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.appodeal.ads.networks.j;
import com.flurry.android.FlurryAgent;
import com.flurry.android.ads.FlurryAdInterstitial;
import java.lang.ref.WeakReference;

public class g extends aq {
    private static ap c;
    WeakReference<Activity> b;
    private FlurryAdInterstitial d;

    public static ap getInstance(String str, String[] strArr) {
        if (c == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new g();
            }
            c = new ap(str, g(), aqVar);
        }
        return c;
    }

    private static String[] g() {
        return new String[]{"com.flurry.android.FlurryFullscreenTakeoverActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        String string = ((ar) ak.m.get(i)).l.getString("app_key");
        String string2 = ((ar) ak.m.get(i)).l.getString("placement_key");
        FlurryAgent.init(activity, string);
        FlurryAgent.onStartSession(activity);
        this.b = new WeakReference(activity);
        this.d = new FlurryAdInterstitial(activity, string2);
        this.d.setListener(new h(c, i, i2));
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
                am.a(true);
            } else {
                this.d.displayAd();
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            am.a(true);
        }
    }

    public void a(boolean z) {
        FlurryAgent.setLogEnabled(z);
    }
}
