package com.appodeal.ads.a;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.appodeal.ads.g;
import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.appodeal.ads.k;
import com.appodeal.ads.l;
import com.mopub.mobileads.MoPubView;

public class t extends k {
    private static h c;
    private MoPubView d;

    public static h getInstance(String str, String[] strArr) {
        if (c == null) {
            k kVar = null;
            if (an.a(strArr)) {
                kVar = new t();
            }
            c = new h(str, kVar).c();
        }
        return c;
    }

    public void a(Activity activity, int i, int i2) {
        String string = ((l) g.x.get(i)).l.getString("mopub_key");
        int optInt = ((l) g.x.get(i)).l.optInt("width", 320);
        this.b = ((l) g.x.get(i)).l.optInt("height", 50);
        if (optInt > g.d() || this.b > g.c()) {
            j.b(i, i2, c);
            return;
        }
        this.d = new MoPubView(activity);
        this.d.setAdUnitId(string);
        this.d.setAutorefreshEnabled(false);
        this.d.setBannerAdListener(new u(c, i, i2));
        String toMopubString = Appodeal.getUserSettings(activity).toMopubString();
        if (toMopubString != null) {
            this.d.setKeywords(toMopubString);
        }
        this.d.setLocation(an.e(activity));
        this.d.loadAd();
    }

    public ViewGroup c() {
        return this.d;
    }

    public void a(View view) {
        if (view instanceof MoPubView) {
            ((MoPubView) view).destroy();
        }
    }
}
