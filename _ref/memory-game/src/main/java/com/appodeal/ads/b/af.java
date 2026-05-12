package com.appodeal.ads.b;

import android.app.Activity;
import android.location.Location;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.an;
import com.appodeal.ads.n;
import com.appodeal.ads.o;
import com.appodeal.ads.r;
import com.appodeal.ads.s;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppAd.AdMode;
import com.startapp.android.publish.StartAppSDK;
import com.startapp.android.publish.model.AdPreferences;

public class af extends r {
    private static o b;
    private StartAppAd c;
    private ag d;

    public static o getInstance(String str, String[] strArr) {
        if (b == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new af();
            }
            b = new o(str, f(), rVar);
        }
        return b;
    }

    private static String[] f() {
        return new String[]{"com.startapp.android.publish.FullScreenActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        StartAppSDK.init(activity, ((s) n.p.get(i)).m.getString("app_id"), false);
        this.c = new StartAppAd(activity);
        AdPreferences adPreferences = new AdPreferences();
        Location e = an.e(activity);
        if (e != null) {
            adPreferences.setLatitude(e.getLatitude()).setLongitude(e.getLongitude());
        }
        if (AppodealSettings.a) {
            adPreferences.setTestMode(true);
        }
        this.d = new ag(b, i, i2);
        this.c.loadAd(AdMode.FULLPAGE, adPreferences, this.d);
    }

    public void a(Activity activity, int i) {
        this.c.showAd(this.d);
    }

    public boolean e() {
        return true;
    }
}
