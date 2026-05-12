package com.appodeal.ads.b;

import android.app.Activity;
import android.location.Location;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.an;
import com.appodeal.ads.n;
import com.appodeal.ads.networks.c;
import com.appodeal.ads.o;
import com.appodeal.ads.r;
import com.appodeal.ads.s;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;

public class a extends r {
    private static o b;
    private InterstitialAd c;

    public static o getInstance(String str, String[] strArr) {
        if (b == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new a();
            }
            b = new o(str, f(), rVar);
        }
        return b;
    }

    private static String[] f() {
        return new String[]{AdActivity.CLASS_NAME};
    }

    public void a(Activity activity, int i, int i2) {
        String string = ((s) n.p.get(i)).m.getString("admob_key");
        this.c = new InterstitialAd(activity);
        this.c.setAdUnitId(string);
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
        this.c.setAdListener(new b(b, i, i2));
        this.c.loadAd(build);
    }

    public void a(Activity activity, int i) {
        this.c.show();
    }

    public boolean e() {
        return true;
    }
}
