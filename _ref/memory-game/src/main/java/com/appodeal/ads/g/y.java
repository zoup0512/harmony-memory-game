package com.appodeal.ads.g;

import android.app.Activity;
import android.content.Context;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.ah;
import com.appodeal.ads.aj;
import com.appodeal.ads.an;
import com.appodeal.ads.ao;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.appodeal.ads.networks.k;
import com.appodeal.ads.utils.a.b;
import org.json.JSONObject;
import org.nexage.sourcekit.vast.VASTPlayer;

public class y extends aq {
    private static ap b;
    private VASTPlayer c;
    private boolean d;
    private boolean e;
    private b f;
    private z g;

    private class a implements com.appodeal.ads.networks.k.a {
        final /* synthetic */ y a;

        private a(y yVar) {
            this.a = yVar;
        }

        public void a(int i, int i2) {
            aj.b(i, i2, y.b);
        }

        public void a(String str, int i, int i2, String str2) {
            try {
                this.a.c = new VASTPlayer(Appodeal.b);
                this.a.c.setPrecache(true);
                if (str2 != null) {
                    this.a.c.setXmlUrl(str2);
                }
                this.a.a = str;
                this.a.c.setRtbInfo(this.a.a(y.b.a(), i, false));
                this.a.c.setMaxDuration(ah.v);
                this.a.c.setDisableLongVideo(ah.w);
                this.a.c.loadVideoWithData(this.a.a, this.a.g);
            } catch (Throwable e) {
                Appodeal.a(e);
                aj.b(i, i2, y.b);
            }
        }
    }

    public static ap getInstance(String str, String[] strArr) {
        if (b == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new y();
            }
            b = new ap(str, h(), aqVar);
        }
        return b;
    }

    private static String[] h() {
        return new String[]{"org.nexage.sourcekit.vast.activity.VASTActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        JSONObject optJSONObject = ((ar) ah.m.get(i)).l.optJSONObject("freq");
        String optString = ((ar) ah.m.get(i)).l.optString("package");
        long optLong = ((ar) ah.m.get(i)).l.optLong("expiry");
        if (optJSONObject != null) {
            this.f = new b(activity, optJSONObject);
            if (!this.f.a((Context) activity)) {
                this.f = null;
                aj.b(i, i2, b);
                return;
            }
        }
        this.f = null;
        this.a = ((ar) ah.m.get(i)).l.optString("vast_xml");
        String optString2 = ((ar) ah.m.get(i)).l.optString("vast_url");
        String optString3 = ((ar) ah.m.get(i)).l.optString("vpaid_url");
        this.e = ao.a() ? ao.a() : ((ar) ah.m.get(i)).l.optBoolean("video_wo_banners");
        this.d = ((ar) ah.m.get(i)).l.optBoolean("video_auto_close", true);
        if ((this.a == null || this.a.isEmpty() || this.a.equals(" ")) && (optString2 == null || optString2.isEmpty() || optString2.equals(" "))) {
            aj.b(i, i2, b);
            return;
        }
        this.g = new z(b, i, i2, optString, optLong);
        if (this.a == null || this.a.isEmpty() || this.a.equals(" ")) {
            k kVar = new k(activity, new a(), i, i2, optString2);
            return;
        }
        this.c = new VASTPlayer(activity);
        this.c.setPrecache(true);
        this.c.setRtbInfo(a(b.a(), i, false));
        if (optString3 != null) {
            this.c.setXmlUrl(optString3);
        }
        this.c.setMaxDuration(ah.v);
        this.c.setDisableLongVideo(ah.w);
        this.c.loadVideoWithData(this.a, this.g);
    }

    public void a(Activity activity, int i) {
        if (this.c.checkFile()) {
            if (this.f != null) {
                this.f.b(activity);
            }
            this.c.play(ao.b.NON_REWARDED, this.e, this.d, this.g);
            return;
        }
        aj.a(true);
    }

    public boolean e() {
        return true;
    }

    public boolean f() {
        return true;
    }
}
