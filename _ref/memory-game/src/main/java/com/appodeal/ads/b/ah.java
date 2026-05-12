package com.appodeal.ads.b;

import android.app.Activity;
import android.os.Build.VERSION;
import com.appodeal.ads.an;
import com.appodeal.ads.n;
import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.appodeal.ads.r;
import com.appodeal.ads.s;
import com.yandex.metrica.YandexMetrica;
import com.yandex.mobile.ads.AdRequest;
import com.yandex.mobile.ads.InterstitialAd;
import com.yandex.mobile.ads.MobileAds;

public class ah extends r {
    private static o b;
    private InterstitialAd c;

    public static o getInstance(String str, String[] strArr) {
        if (b == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new ah();
            }
            b = new o(str, f(), rVar).d();
        }
        return b;
    }

    private static String[] f() {
        return new String[]{"com.yandex.mobile.ads.AdActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        if (!YandexMetrica.getLibraryVersion().equals("2.41") || VERSION.SDK_INT < 10) {
            q.b(i, i2, b);
            return;
        }
        String string = ((s) n.p.get(i)).m.getString("metrica_id");
        String string2 = ((s) n.p.get(i)).m.getString("block_id");
        YandexMetrica.activate(activity, string);
        this.c = new InterstitialAd(activity);
        this.c.setBlockId(string2);
        AdRequest build = AdRequest.builder().withLocation(an.e(activity)).build();
        this.c.setInterstitialEventListener(new ai(b, i, i2));
        this.c.loadAd(build);
    }

    public void a(Activity activity, int i) {
        this.c.show();
    }

    public void a(boolean z) {
        MobileAds.enableLogging(z);
    }
}
