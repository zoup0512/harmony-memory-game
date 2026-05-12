package com.appodeal.ads.e;

import android.app.Activity;
import android.location.Location;
import android.os.Build.VERSION;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.ak;
import com.appodeal.ads.am;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppAd.AdMode;
import com.startapp.android.publish.StartAppSDK;
import com.startapp.android.publish.model.AdPreferences;

public class w extends aq {
    private static ap b;
    private StartAppAd c;
    private x d;

    public static ap getInstance(String str, String[] strArr) {
        if (b == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new w();
            }
            b = new ap(str, g(), aqVar);
        }
        return b;
    }

    private static String[] g() {
        return new String[]{"com.startapp.android.publish.FullScreenActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        if (VERSION.SDK_INT < 16) {
            am.b(i, i2, b);
            return;
        }
        StartAppSDK.init(activity, ((ar) ak.m.get(i)).l.getString("app_id"), false);
        this.c = new StartAppAd(activity);
        AdPreferences adPreferences = new AdPreferences();
        Location e = an.e(activity);
        if (e != null) {
            adPreferences.setLatitude(e.getLatitude()).setLongitude(e.getLongitude());
        }
        if (AppodealSettings.a) {
            adPreferences.setTestMode(true);
        }
        this.d = new x(b, i, i2);
        this.c.setVideoListener(this.d);
        this.c.loadAd(AdMode.REWARDED_VIDEO, adPreferences, this.d);
    }

    public void a(Activity activity, int i) {
        this.c.showAd(this.d);
    }

    public boolean e() {
        return true;
    }
}
