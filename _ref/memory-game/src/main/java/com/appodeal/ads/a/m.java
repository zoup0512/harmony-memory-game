package com.appodeal.ads.a;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.appodeal.ads.an;
import com.appodeal.ads.g;
import com.appodeal.ads.g.b;
import com.appodeal.ads.h;
import com.appodeal.ads.k;
import com.appodeal.ads.l;
import com.appodeal.ads.networks.j;
import com.flurry.android.FlurryAgent;
import com.flurry.android.ads.FlurryAdBanner;

public class m extends k {
    private static h c;
    private ViewGroup d;
    private FlurryAdBanner e;

    public static h getInstance(String str, String[] strArr) {
        if (c == null) {
            k kVar = null;
            if (an.a(strArr)) {
                kVar = new m();
            }
            c = new h(str, kVar);
        }
        return c;
    }

    public void a(Activity activity, int i, int i2) {
        String string = ((l) g.x.get(i)).l.getString("app_key");
        String string2 = ((l) g.x.get(i)).l.getString("placement_key");
        FlurryAgent.init(activity, string);
        FlurryAgent.onStartSession(activity);
        this.d = new RelativeLayout(activity);
        this.e = new FlurryAdBanner(activity, this.d, string2);
        this.e.setListener(new n(c, i, i2));
        this.e.setTargeting(j.a(activity));
        this.b = 50;
        this.e.fetchAd();
    }

    public void a(Activity activity, h hVar, int i, b bVar, boolean z, b bVar2) {
        this.e.displayAd();
        super.a(activity, hVar, i, bVar, z, bVar2);
    }

    public ViewGroup c() {
        return this.d;
    }

    protected void a(View view) {
        if (view != null && view.equals(this.d)) {
            this.e.destroy();
        }
    }

    public boolean g() {
        return true;
    }

    public void a(boolean z) {
        FlurryAgent.setLogEnabled(z);
    }
}
