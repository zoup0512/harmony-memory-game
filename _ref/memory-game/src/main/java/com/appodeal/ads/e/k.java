package com.appodeal.ads.e;

import android.app.Activity;
import com.appodeal.ads.VideoActivity;
import com.appodeal.ads.ak;
import com.appodeal.ads.am;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.mopub.common.AdType;
import org.nexage.sourcekit.mraid.MRAIDVideoAddendumInterstitial;
import org.nexage.sourcekit.mraid.MRAIDVideoAddendumInterstitial.Builder;

public class k extends aq {
    private static ap b;
    private MRAIDVideoAddendumInterstitial c;
    private VideoActivity d;

    public static ap getInstance(String str, String[] strArr) {
        if (b == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new k();
            }
            b = new ap(str, g(), aqVar);
        }
        return b;
    }

    private static String[] g() {
        return new String[0];
    }

    public void a(Activity activity, int i, int i2) {
        this.a = ((ar) ak.m.get(i)).l.getString(AdType.HTML);
        int parseInt = Integer.parseInt(((ar) ak.m.get(i)).l.getString("width"));
        int parseInt2 = Integer.parseInt(((ar) ak.m.get(i)).l.getString("height"));
        Object lVar = new l(b, i, i2);
        this.c = new Builder().setContext(activity).setBaseUrl(null).setData(this.a).setSupportedNativeFeatures(null).setWidth(parseInt).setHeight(parseInt2).setListener(lVar).setNativeFeatureListener(lVar).setSkippable(false).setRtbInfo(a(b.a(), i, true)).build();
    }

    public void a(Activity activity, int i) {
        an.b(activity, b, i);
    }

    public void a(VideoActivity videoActivity, int i) {
        this.d = videoActivity;
        if (this.c != null) {
            this.d.a(false);
            this.c.show(videoActivity);
            am.a(i, b);
        }
    }

    public VideoActivity c() {
        return this.d;
    }

    public boolean f() {
        return false;
    }
}
