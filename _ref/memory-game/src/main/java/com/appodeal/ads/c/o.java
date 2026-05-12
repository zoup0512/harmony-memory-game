package com.appodeal.ads.c;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.aa;
import com.appodeal.ads.an;
import com.appodeal.ads.v;
import com.appodeal.ads.w;
import com.appodeal.ads.z;
import com.mopub.mobileads.MoPubView;

public class o extends z {
    private static w b;
    private MoPubView c;

    public static w getInstance(String str, String[] strArr) {
        if (b == null) {
            z zVar = null;
            if (an.a(strArr)) {
                zVar = new o();
            }
            b = new w(str, zVar).c();
        }
        return b;
    }

    public void a(Activity activity, int i, int i2) {
        String string = ((aa) v.t.get(i)).l.getString("mopub_key");
        this.c = new MoPubView(activity);
        this.c.setAdUnitId(string);
        this.c.setAutorefreshEnabled(false);
        this.c.setBannerAdListener(new p(b, i, i2));
        string = Appodeal.getUserSettings(activity).toMopubString();
        if (string != null) {
            this.c.setKeywords(string);
        }
        this.c.setLocation(an.e(activity));
        this.c.loadAd();
    }

    public ViewGroup c() {
        return this.c;
    }

    public void a(View view) {
        if (view instanceof MoPubView) {
            ((MoPubView) view).destroy();
        }
    }
}
