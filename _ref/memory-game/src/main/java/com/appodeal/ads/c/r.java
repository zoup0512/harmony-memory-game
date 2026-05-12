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
import com.yandex.metrica.YandexMetrica;
import com.yandex.mobile.ads.AdRequest;
import com.yandex.mobile.ads.AdSize;
import com.yandex.mobile.ads.AdView;
import com.yandex.mobile.ads.MobileAds;

public class r extends z {
    private static w b;
    private AdView c;

    public static w getInstance(String str, String[] strArr) {
        if (b == null) {
            z zVar = null;
            if (an.a(strArr)) {
                zVar = new r();
            }
            b = new w(str, zVar).c();
        }
        return b;
    }

    public void a(Activity activity, int i, int i2) {
        if (!YandexMetrica.getLibraryVersion().equals("2.41") || VERSION.SDK_INT < 10) {
            y.b(i, i2, b);
            return;
        }
        String string = ((aa) v.t.get(i)).l.getString("metrica_id");
        String string2 = ((aa) v.t.get(i)).l.getString("block_id");
        YandexMetrica.activate(activity, string);
        this.c = new AdView(activity);
        this.c.setBlockId(string2);
        this.c.setAdSize(AdSize.BANNER_300x250);
        AdRequest build = AdRequest.builder().withLocation(an.e(activity)).build();
        this.c.setAdEventListener(new s(b, i, i2));
        this.c.loadAd(build);
    }

    public ViewGroup c() {
        return this.c;
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
