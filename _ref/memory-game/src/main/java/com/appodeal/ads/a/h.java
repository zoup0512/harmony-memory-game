package com.appodeal.ads.a;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.an;
import com.appodeal.ads.g;
import com.appodeal.ads.k;
import com.appodeal.ads.l;
import com.appodeal.ads.networks.i;
import com.cmcm.adsdk.banner.CMBannerAdSize;
import com.cmcm.adsdk.banner.CMNativeBannerView;

public class h extends k {
    private static com.appodeal.ads.h d;
    public CMNativeBannerView c;

    public static com.appodeal.ads.h getInstance(String str, String[] strArr) {
        if (d == null) {
            k kVar = null;
            if (an.a(strArr)) {
                kVar = new h();
            }
            d = new com.appodeal.ads.h(str, kVar).c();
        }
        return d;
    }

    public void a(Activity activity, int i, int i2) {
        String string = ((l) g.x.get(i)).l.getString("appId");
        String string2 = ((l) g.x.get(i)).l.getString("posId");
        i.a(activity, string, ((l) g.x.get(i)).l.getString("channelId"));
        this.c = new CMNativeBannerView(activity);
        this.c.setAdSize(CMBannerAdSize.BANNER_320_50);
        this.c.setBannerAutorefreshEnabled(false);
        this.c.setPosid(string2);
        this.b = 50;
        this.c.setAdListener(new i(d, i, i2));
        this.c.loadAd();
    }

    public ViewGroup c() {
        return this.c;
    }

    public void a(View view) {
        if (view instanceof CMNativeBannerView) {
            ((CMNativeBannerView) view).onDestroy();
        }
    }

    public boolean g() {
        return true;
    }
}
