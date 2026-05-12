package com.appodeal.ads.c;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.aa;
import com.appodeal.ads.an;
import com.appodeal.ads.networks.i;
import com.appodeal.ads.v;
import com.appodeal.ads.w;
import com.appodeal.ads.z;
import com.cmcm.adsdk.banner.CMBannerAdSize;
import com.cmcm.adsdk.banner.CMNativeBannerView;

public class g extends z {
    private static w c;
    public CMNativeBannerView b;

    public static w getInstance(String str, String[] strArr) {
        if (c == null) {
            z zVar = null;
            if (an.a(strArr)) {
                zVar = new g();
            }
            c = new w(str, zVar).c();
        }
        return c;
    }

    public void a(Activity activity, int i, int i2) {
        String string = ((aa) v.t.get(i)).l.getString("appId");
        String string2 = ((aa) v.t.get(i)).l.getString("posId");
        i.a(activity, string, ((aa) v.t.get(i)).l.getString("channelId"));
        this.b = new CMNativeBannerView(activity);
        this.b.setAdSize(CMBannerAdSize.BANNER_300_250);
        this.b.setBannerAutorefreshEnabled(false);
        this.b.setPosid(string2);
        this.b.setAdListener(new h(c, i, i2));
        this.b.loadAd();
    }

    public ViewGroup c() {
        return this.b;
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
