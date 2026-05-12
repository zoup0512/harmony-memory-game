package com.appodeal.ads.c;

import android.app.Activity;
import android.location.Location;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.aa;
import com.appodeal.ads.an;
import com.appodeal.ads.v;
import com.appodeal.ads.w;
import com.appodeal.ads.z;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class c extends z {
    private static w b;
    private AdView c;

    public static w h() {
        if (b == null) {
            z zVar = null;
            if (an.a("com.google.android.gms.ads.AdView")) {
                zVar = new c();
            }
            b = new w("admob_precache", zVar);
        }
        return b;
    }

    public void a(Activity activity, int i, int i2) {
        String string = ((aa) v.t.get(i)).l.getString("admob_key");
        this.c = new AdView(activity);
        this.c.setAdUnitId(string);
        this.c.setAdSize(AdSize.MEDIUM_RECTANGLE);
        Builder builder = new Builder();
        builder.tagForChildDirectedTreatment(AppodealSettings.d);
        if (AppodealSettings.a) {
            builder.addTestDevice(com.appodeal.ads.networks.c.a(activity));
            builder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
        }
        try {
            builder.getClass().getDeclaredMethod("setLocation", new Class[]{Location.class});
            builder.setLocation(an.e(activity));
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        AdRequest build = builder.build();
        this.c.setAdListener(new d(b, i, i2));
        this.c.loadAd(build);
    }

    public ViewGroup c() {
        return this.c;
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
