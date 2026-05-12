package com.appodeal.ads.g;

import android.app.Activity;
import android.content.Context;
import com.appodeal.ads.VideoActivity;
import com.appodeal.ads.ah;
import com.appodeal.ads.aj;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.appodeal.ads.as;
import com.mopub.common.AdType;
import org.nexage.sourcekit.mraid.MRAIDInterstitial;
import org.nexage.sourcekit.mraid.MRAIDInterstitialListener;

public class q extends aq {
    private static ap b;
    private MRAIDInterstitial c;
    private VideoActivity d;

    public static ap getInstance(String str, String[] strArr) {
        if (b == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new q();
            }
            b = new ap(str, g(), aqVar);
        }
        return b;
    }

    private static String[] g() {
        return new String[0];
    }

    public void a(Activity activity, int i, int i2) {
        this.a = ((ar) ah.m.get(i)).l.getString(AdType.HTML);
        int parseInt = Integer.parseInt(((ar) ah.m.get(i)).l.getString("width"));
        int parseInt2 = Integer.parseInt(((ar) ah.m.get(i)).l.getString("height"));
        MRAIDInterstitialListener jVar = new j(b, i, i2);
        Context context = activity;
        this.c = new MRAIDInterstitial(context, null, this.a, null, parseInt, parseInt2, jVar, jVar, a(b.a(), i, false));
    }

    public void a(Activity activity, int i) {
        an.a(activity, b, i);
    }

    public void a(VideoActivity videoActivity, int i) {
        this.d = videoActivity;
        as.a(videoActivity);
        if (this.c != null) {
            this.d.a(true);
            this.c.show(videoActivity);
            aj.a(i, b);
        }
    }

    public VideoActivity c() {
        return this.d;
    }

    public boolean e() {
        return true;
    }
}
