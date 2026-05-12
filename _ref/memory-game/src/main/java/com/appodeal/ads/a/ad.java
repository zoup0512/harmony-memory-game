package com.appodeal.ads.a;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import com.appodeal.ads.an;
import com.appodeal.ads.g;
import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.appodeal.ads.k;
import com.appodeal.ads.l;
import com.yandex.metrica.YandexMetrica;
import com.yandex.mobile.ads.AdRequest;
import com.yandex.mobile.ads.AdSize;
import com.yandex.mobile.ads.AdView;
import com.yandex.mobile.ads.MobileAds;

public class ad extends k {
    private static h c;
    private AdView d;

    public static h getInstance(String str, String[] strArr) {
        if (c == null) {
            k kVar = null;
            if (an.a(strArr)) {
                kVar = new ad();
            }
            c = new h(str, kVar).c();
        }
        return c;
    }

    public void a(Activity activity, int i, int i2) {
        if (!YandexMetrica.getLibraryVersion().equals("2.41") || VERSION.SDK_INT < 10) {
            j.b(i, i2, c);
            return;
        }
        String string = ((l) g.x.get(i)).l.getString("metrica_id");
        String string2 = ((l) g.x.get(i)).l.getString("block_id");
        int optInt = ((l) g.x.get(i)).l.optInt("width", 728);
        this.b = ((l) g.x.get(i)).l.optInt("height", 90);
        if (optInt > g.d() || this.b > g.c()) {
            j.b(i, i2, c);
            return;
        }
        YandexMetrica.activate(activity, string);
        this.d = new AdView(activity);
        this.d.setBlockId(string2);
        float g = an.g(activity);
        float h = an.h(activity);
        if (g.t && g >= 728.0f && h > 720.0f && optInt == 728 && this.b == 90) {
            this.d.setAdSize(AdSize.BANNER_728x90);
            this.b = 90;
        } else if (optInt == 320 && this.b == 50) {
            this.d.setAdSize(AdSize.BANNER_320x50);
            this.b = 50;
        } else {
            j.b(i, i2, c);
            return;
        }
        AdRequest build = AdRequest.builder().withLocation(an.e(activity)).build();
        this.d.setAdEventListener(new ae(c, i, i2));
        this.d.setAutoRefreshEnabled(false);
        this.d.loadAd(build);
    }

    public ViewGroup c() {
        return this.d;
    }

    public void a(View view) {
        if (view instanceof AdView) {
            ((AdView) view).destroy();
        }
    }

    public void a(boolean z) {
        MobileAds.enableLogging(z);
    }
}
