package com.appodeal.ads.c;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.aa;
import com.appodeal.ads.an;
import com.appodeal.ads.v;
import com.appodeal.ads.w;
import com.appodeal.ads.y;
import com.appodeal.ads.z;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;

public class j extends z {
    private static w b;
    private AdView c;

    public static w getInstance(String str, String[] strArr) {
        if (b == null) {
            z zVar = null;
            if (an.a(strArr)) {
                zVar = new j();
            }
            b = new w(str, zVar);
        }
        return b;
    }

    public void a(Activity activity, int i, int i2) {
        if (VERSION.SDK_INT < 11) {
            y.b(i, i2, b);
            return;
        }
        this.c = new AdView(activity, ((aa) v.t.get(i)).l.getString("facebook_key"), AdSize.RECTANGLE_HEIGHT_250);
        this.c.disableAutoRefresh();
        this.c.setAdListener(new k(b, i, i2));
        this.c.loadAd();
    }

    public ViewGroup c() {
        return this.c;
    }

    public void a(View view) {
        if (view instanceof AdView) {
            ((AdView) view).destroy();
        }
    }
}
