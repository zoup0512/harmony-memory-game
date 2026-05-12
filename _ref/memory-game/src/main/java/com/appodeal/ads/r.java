package com.appodeal.ads;

import android.app.Activity;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;

public abstract class r extends d {
    public abstract void a(Activity activity, int i);

    public abstract void a(Activity activity, int i, int i2);

    public void a(InterstitialActivity interstitialActivity, int i) {
    }

    public InterstitialActivity c() {
        return null;
    }

    public void b(Activity activity, int i) {
    }

    public void c(Activity activity, int i) {
    }

    public void d() {
    }

    public boolean e() {
        return false;
    }

    public RtbInfo a(String str, int i) {
        try {
            return new RtbInfo(this.a, ((s) n.p.get(i)).m.getString("id"), ((s) n.p.get(i)).n, str, 1);
        } catch (Throwable e) {
            Appodeal.a(e);
            return null;
        }
    }
}
