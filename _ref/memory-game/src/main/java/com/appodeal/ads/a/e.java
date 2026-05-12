package com.appodeal.ads.a;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdSize;
import com.amazon.device.ads.AdTargetingOptions;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.an;
import com.appodeal.ads.g;
import com.appodeal.ads.h;
import com.appodeal.ads.k;
import com.appodeal.ads.l;

public class e extends k {
    private static h d;
    public f c;
    private AdLayout e;

    public static h getInstance(String str, String[] strArr) {
        if (d == null) {
            k kVar = null;
            if (an.a(strArr)) {
                kVar = new e();
            }
            d = new h(str, kVar);
        }
        return d;
    }

    public static h h() {
        if (d == null) {
            k kVar = null;
            if (an.a("com.amazon.device.ads.AdLayout")) {
                kVar = new e();
            }
            d = new h("amazon_ads", kVar);
        }
        return d;
    }

    public void a(Activity activity, int i, int i2) {
        AdRegistration.setAppKey(((l) g.x.get(i)).l.getString("amazon_key"));
        if (AppodealSettings.a) {
            AdRegistration.enableTesting(true);
        }
        float g = an.g(activity);
        float h = an.h(activity);
        if (!g.t || g < 728.0f || h <= 720.0f) {
            this.e = new AdLayout((Context) activity, AdSize.SIZE_320x50.disableScaling());
            this.b = 50;
        } else {
            this.e = new AdLayout((Context) activity, AdSize.SIZE_728x90.disableScaling());
            this.b = 90;
        }
        this.c = new f(d, i, i2);
        this.e.setListener(this.c);
        this.e.setLayoutParams(new LayoutParams(-2, -2));
        this.e.loadAd(new AdTargetingOptions().enableGeoLocation(true));
    }

    public ViewGroup c() {
        return this.e;
    }

    public void a(View view) {
        if (view instanceof AdLayout) {
            ((AdLayout) view).destroy();
        }
    }

    public boolean g() {
        return true;
    }

    public void a(boolean z) {
        AdRegistration.enableLogging(z);
    }
}
