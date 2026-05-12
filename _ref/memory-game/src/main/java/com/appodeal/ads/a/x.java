package com.appodeal.ads.a;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.appodeal.ads.an;
import com.appodeal.ads.g;
import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.appodeal.ads.k;
import com.appodeal.ads.l;
import com.appodeal.ads.networks.s;
import com.revmob.RevMob;
import com.revmob.RevMobAdsListener;
import com.revmob.ads.banner.RevMobBanner;

public class x extends k {
    private static h c;
    private RevMobBanner d;
    private RevMob e;

    public static h getInstance(String str, String[] strArr) {
        if (c == null) {
            k kVar = null;
            if (an.a(strArr)) {
                kVar = new x();
            }
            c = new h(str, kVar).c();
        }
        return c;
    }

    public void a(final Activity activity, final int i, final int i2) {
        String string = ((l) g.x.get(i)).l.getString("media_id");
        if (RevMob.session() != null) {
            this.d = RevMob.session().createBanner(activity, new y(c, i, i2));
            this.d.setLayoutParams(new LayoutParams(Math.round(320.0f * an.i(activity)), Math.round(((float) this.b) * an.i(activity))));
        } else {
            this.e = RevMob.startWithListener(activity, new RevMobAdsListener(this) {
                final /* synthetic */ x d;

                public void onRevMobSessionNotStarted(String str) {
                    j.b(i, i2, x.c);
                }

                public void onRevMobSessionStarted() {
                    this.d.d = this.d.e.createBanner(activity, new y(x.c, i, i2));
                    this.d.d.setLayoutParams(new LayoutParams(Math.round(320.0f * an.i(activity)), Math.round(((float) this.d.b) * an.i(activity))));
                }
            }, string);
            s.a(activity, this.e);
        }
        this.b = 50;
    }

    public ViewGroup c() {
        return this.d;
    }

    public void a(View view) {
        if (view instanceof RevMobBanner) {
            ((RevMobBanner) view).release();
        }
    }

    public boolean g() {
        return true;
    }
}
