package com.appodeal.ads.b;

import android.app.Activity;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.an;
import com.appodeal.ads.n;
import com.appodeal.ads.o;
import com.appodeal.ads.r;
import com.appodeal.ads.s;
import com.mopub.mobileads.MoPubInterstitial;

public class w extends r {
    private static o b;
    private MoPubInterstitial c;

    public static o getInstance(String str, String[] strArr) {
        if (b == null) {
            r rVar = null;
            if (an.a(strArr)) {
                rVar = new w();
            }
            b = new o(str, f(), rVar).d();
        }
        return b;
    }

    private static String[] f() {
        return new String[]{"com.mopub.mobileads.MoPubActivity", "com.mopub.common.MoPubBrowser", "com.mopub.mobileads.MraidActivity", "com.mopub.mobileads.MraidVideoPlayerActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        String string = ((s) n.p.get(i)).m.getString("mopub_key");
        if (((s) n.p.get(i)).m.has("preload")) {
            AppodealSettings.b = ((s) n.p.get(i)).m.getBoolean("preload");
        }
        this.c = new MoPubInterstitial(activity, string);
        this.c.setInterstitialAdListener(new x(b, i, i2));
        String toMopubString = Appodeal.getUserSettings(activity).toMopubString();
        if (toMopubString != null) {
            this.c.setKeywords(toMopubString);
        }
        this.c.load();
    }

    public void a(Activity activity, int i) {
        this.c.show();
    }
}
