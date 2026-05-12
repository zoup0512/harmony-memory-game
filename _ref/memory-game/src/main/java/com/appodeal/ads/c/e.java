package com.appodeal.ads.c;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdSize;
import com.amazon.device.ads.AdTargetingOptions;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.aa;
import com.appodeal.ads.an;
import com.appodeal.ads.v;
import com.appodeal.ads.w;
import com.appodeal.ads.z;

public class e extends z {
    private static w c;
    public f b;
    private AdLayout d;

    public static w getInstance(String str, String[] strArr) {
        if (c == null) {
            z zVar = null;
            if (an.a(strArr)) {
                zVar = new e();
            }
            c = new w(str, zVar);
        }
        return c;
    }

    public static w h() {
        if (c == null) {
            z zVar = null;
            if (an.a("com.amazon.device.ads.AdLayout")) {
                zVar = new e();
            }
            c = new w("amazon_ads", zVar);
        }
        return c;
    }

    public void a(Activity activity, int i, int i2) {
        AdRegistration.setAppKey(((aa) v.t.get(i)).l.getString("amazon_key"));
        if (AppodealSettings.a) {
            AdRegistration.enableTesting(true);
        }
        this.d = new AdLayout((Context) activity, AdSize.SIZE_300x250);
        this.b = new f(c, i, i2);
        this.d.setListener(this.b);
        this.d.setLayoutParams(new LayoutParams(-2, -2));
        this.d.loadAd(new AdTargetingOptions().enableGeoLocation(true));
    }

    public ViewGroup c() {
        return this.d;
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
