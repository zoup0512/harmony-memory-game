package com.appodeal.ads.a;

import android.app.Activity;
import android.location.Location;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.an;
import com.appodeal.ads.g;
import com.appodeal.ads.h;
import com.appodeal.ads.k;
import com.appodeal.ads.l;
import com.startapp.android.publish.StartAppSDK;
import com.startapp.android.publish.banner.bannerstandard.BannerStandard;
import com.startapp.android.publish.model.AdPreferences;
import java.lang.reflect.Method;

public class ab extends k {
    private static h c;
    private BannerStandard d;

    public static h getInstance(String str, String[] strArr) {
        if (c == null) {
            k kVar = null;
            if (an.a(strArr)) {
                kVar = new ab();
            }
            c = new h(str, kVar);
        }
        return c;
    }

    public void a(Activity activity, int i, int i2) {
        StartAppSDK.init(activity, ((l) g.x.get(i)).l.getString("app_id"), false);
        AdPreferences adPreferences = new AdPreferences();
        Location e = an.e(activity);
        if (e != null) {
            adPreferences.setLatitude(e.getLatitude()).setLongitude(e.getLongitude());
        }
        if (AppodealSettings.a) {
            adPreferences.setTestMode(true);
        }
        this.d = new BannerStandard(activity, adPreferences, new ac(c, i, i2));
        float g = an.g(activity);
        float h = an.h(activity);
        if (!g.t || g < 728.0f || h <= 720.0f) {
            this.b = 50;
            this.d.setLayoutParams(new LayoutParams(Math.round(320.0f * an.i(activity)), Math.round(((float) this.b) * an.i(activity))));
        } else {
            this.b = 90;
            this.d.setLayoutParams(new LayoutParams(Math.round(an.i(activity) * 728.0f), Math.round(((float) this.b) * an.i(activity))));
        }
        try {
            Method declaredMethod = this.d.getClass().getDeclaredMethod("initBanner", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this.d, new Object[0]);
        } catch (Throwable e2) {
            Appodeal.a(e2);
        } catch (Throwable e22) {
            Appodeal.a(e22);
        } catch (Throwable e222) {
            Appodeal.a(e222);
        }
    }

    public ViewGroup c() {
        return this.d;
    }

    public void a(View view) {
    }

    public boolean g() {
        return true;
    }
}
