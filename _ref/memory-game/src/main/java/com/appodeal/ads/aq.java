package com.appodeal.ads;

import android.app.Activity;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;

public abstract class aq extends d {
    public abstract void a(Activity activity, int i);

    public abstract void a(Activity activity, int i, int i2);

    public void a(VideoActivity videoActivity, int i) {
    }

    public VideoActivity c() {
        return null;
    }

    public boolean d() {
        return false;
    }

    public void b(boolean z) {
    }

    public boolean e() {
        return false;
    }

    public boolean f() {
        return false;
    }

    public RtbInfo a(String str, int i, boolean z) {
        if (!z) {
            return new RtbInfo(this.a, ((ar) ah.m.get(i)).l.getString("id"), ((ar) ah.m.get(i)).m, str, 2);
        }
        try {
            return new RtbInfo(this.a, ((ar) ak.m.get(i)).l.getString("id"), ((ar) ak.m.get(i)).m, str, 128);
        } catch (Throwable e) {
            Appodeal.a(e);
            return null;
        }
    }
}
