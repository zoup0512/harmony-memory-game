package com.appodeal.ads.g;

import android.app.Activity;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.ah;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.VastVideoViewController;

public class o extends aq {
    private static ap b;
    private MoPubInterstitial c;

    public static ap getInstance(String str, String[] strArr) {
        if (b == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new o();
            }
            b = new ap(str, g(), aqVar).d();
        }
        return b;
    }

    private static String[] g() {
        return new String[]{"com.mopub.mobileads.MoPubActivity", "com.mopub.common.MoPubBrowser", "com.mopub.mobileads.MraidActivity", "com.mopub.mobileads.MraidVideoPlayerActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        String string = ((ar) ah.m.get(i)).l.getString("mopub_key");
        if (((ar) ah.m.get(i)).l.has("preload")) {
            AppodealSettings.b = ((ar) ah.m.get(i)).l.getBoolean("preload");
        }
        this.c = new MoPubInterstitial(activity, string);
        this.c.setInterstitialAdListener(new p(b, i, i2));
        String toMopubString = Appodeal.getUserSettings(activity).toMopubString();
        if (toMopubString != null) {
            this.c.setKeywords(toMopubString);
        }
        this.c.load();
    }

    public void a(Activity activity, int i) {
        VastVideoViewController.mShowingSkippable = true;
        this.c.show();
    }

    public boolean f() {
        return true;
    }
}
