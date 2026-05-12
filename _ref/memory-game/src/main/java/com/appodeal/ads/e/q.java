package com.appodeal.ads.e;

import android.app.Activity;
import com.appodeal.ads.VideoActivity;
import com.appodeal.ads.ak;
import com.appodeal.ads.am;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.appodeal.ads.as;
import com.mopub.common.AdType;
import org.json.JSONObject;
import org.nexage.sourcekit.mraid.MRAIDInterstitial;
import org.nexage.sourcekit.mraid.MRAIDInterstitialListener;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;

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
        int i3 = 0;
        this.a = ((ar) ak.m.get(i)).l.getString(AdType.HTML);
        int parseInt = Integer.parseInt(((ar) ak.m.get(i)).l.getString("width"));
        int parseInt2 = Integer.parseInt(((ar) ak.m.get(i)).l.getString("height"));
        MRAIDInterstitialListener jVar = new j(b, i, i2);
        RtbInfo a = a(b.a(), i, true);
        JSONObject optJSONObject = ((ar) ak.m.get(i)).l.optJSONObject("ext");
        if (optJSONObject != null) {
            i3 = optJSONObject.optInt("close_time", 0);
        }
        this.c = new MRAIDInterstitial(activity, null, this.a, null, parseInt, parseInt2, jVar, jVar, a, true, i3);
    }

    public void a(Activity activity, int i) {
        an.b(activity, b, i);
    }

    public void a(VideoActivity videoActivity, int i) {
        this.d = videoActivity;
        as.a(videoActivity);
        if (this.c != null) {
            this.d.a(false);
            this.c.show(videoActivity);
            am.a(i, b);
        }
    }

    public VideoActivity c() {
        return this.d;
    }

    public boolean e() {
        return true;
    }
}
