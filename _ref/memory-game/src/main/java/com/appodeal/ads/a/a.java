package com.appodeal.ads.a;

import android.app.Activity;
import android.location.Location;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.an;
import com.appodeal.ads.g;
import com.appodeal.ads.h;
import com.appodeal.ads.k;
import com.appodeal.ads.l;
import com.appodeal.ads.networks.c;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class a extends k {
    private static h c;
    private AdView d;

    public static h getInstance(String str, String[] strArr) {
        if (c == null) {
            k kVar = null;
            if (an.a(strArr)) {
                kVar = new a();
            }
            c = new h(str, kVar);
        }
        return c;
    }

    public void a(Activity activity, int i, int i2) {
        String string = ((l) g.x.get(i)).l.getString("admob_key");
        this.d = new AdView(activity);
        this.d.setAdUnitId(string);
        float g = an.g(activity);
        float h = an.h(activity);
        if (g.s && VERSION.SDK_INT >= 14) {
            this.d.setAdSize(AdSize.SMART_BANNER);
            if (h <= 400.0f) {
                this.b = 32;
            } else if (h > 720.0f) {
                this.b = 90;
            } else {
                this.b = 50;
            }
        } else if (!g.t || g < 728.0f || h <= 720.0f) {
            this.d.setAdSize(AdSize.BANNER);
            this.b = 50;
        } else {
            this.d.setAdSize(AdSize.LEADERBOARD);
            this.b = 90;
        }
        Builder builder = new Builder();
        builder.tagForChildDirectedTreatment(AppodealSettings.d);
        if (AppodealSettings.a) {
            builder.addTestDevice(c.a(activity));
            builder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
        }
        try {
            builder.getClass().getDeclaredMethod("setLocation", new Class[]{Location.class});
            builder.setLocation(an.e(activity));
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        AdRequest build = builder.build();
        this.d.setAdListener(new b(c, i, i2, this.d.getAdSize()));
        this.d.loadAd(build);
    }

    public ViewGroup c() {
        return this.d;
    }

    public void a(View view) {
        if (view instanceof AdView) {
            ((AdView) view).destroy();
        }
    }

    public boolean g() {
        return true;
    }
}
