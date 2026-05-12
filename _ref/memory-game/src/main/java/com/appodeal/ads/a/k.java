package com.appodeal.ads.a;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.appodeal.ads.g;
import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.appodeal.ads.l;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.internal.DisplayAdController;
import java.lang.reflect.Field;

public class k extends com.appodeal.ads.k {
    private static h c;
    private AdView d;

    public static h getInstance(String str, String[] strArr) {
        if (c == null) {
            com.appodeal.ads.k kVar = null;
            if (an.a(strArr)) {
                kVar = new k();
            }
            c = new h(str, kVar);
        }
        return c;
    }

    public void a(Activity activity, int i, int i2) {
        if (VERSION.SDK_INT < 11) {
            j.b(i, i2, c);
            return;
        }
        String string = ((l) g.x.get(i)).l.getString("facebook_key");
        float h = an.h(activity);
        if (!g.t || h <= 720.0f) {
            this.d = new AdView(activity, string, AdSize.BANNER_HEIGHT_50);
            this.b = 50;
        } else {
            this.d = new AdView(activity, string, AdSize.BANNER_HEIGHT_90);
            this.b = 90;
        }
        try {
            Field declaredField = this.d.getClass().getDeclaredField("e");
            declaredField.setAccessible(true);
            DisplayAdController displayAdController = (DisplayAdController) declaredField.get(this.d);
            Class cls = displayAdController.getClass();
            Field declaredField2 = cls.getDeclaredField("x");
            declaredField2.setAccessible(true);
            activity.unregisterReceiver((BroadcastReceiver) declaredField2.get(displayAdController));
            declaredField2 = cls.getDeclaredField("y");
            declaredField2.setAccessible(true);
            declaredField2.set(displayAdController, Boolean.valueOf(false));
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        this.d.disableAutoRefresh();
        this.d.setAdListener(new l(c, i, i2));
        this.d.loadAd();
    }

    public ViewGroup c() {
        return this.d;
    }

    public void a(View view) {
        if (view instanceof AdView) {
            ((AdView) view).destroy();
        }
    }
}
