package com.appodeal.ads.e;

import android.app.Activity;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.ak;
import com.appodeal.ads.am;
import com.appodeal.ads.an;
import com.appodeal.ads.ao;
import com.appodeal.ads.ao.b;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.appodeal.ads.networks.k;
import org.nexage.sourcekit.vast.VASTPlayer;

public class r extends aq {
    private static ap b;
    private VASTPlayer c;
    private boolean d;
    private boolean e;
    private ad f;

    private class a implements com.appodeal.ads.networks.k.a {
        final /* synthetic */ r a;

        private a(r rVar) {
            this.a = rVar;
        }

        public void a(int i, int i2) {
            am.b(i, i2, r.b);
        }

        public void a(String str, int i, int i2, String str2) {
            try {
                this.a.c = new VASTPlayer(Appodeal.b);
                this.a.c.setPrecache(true);
                if (str2 != null) {
                    this.a.c.setXmlUrl(str2);
                }
                this.a.a = str;
                this.a.c.setRtbInfo(this.a.a(r.b.a(), i, true));
                this.a.c.setMaxDuration(ak.w);
                this.a.c.setDisableLongVideo(ak.x);
                this.a.c.loadVideoWithData(this.a.a, this.a.f);
            } catch (Throwable e) {
                Appodeal.a(e);
                am.b(i, i2, r.b);
            }
        }
    }

    public static ap getInstance(String str, String[] strArr) {
        if (b == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new r();
            }
            b = new ap(str, h(), aqVar);
        }
        return b;
    }

    private static String[] h() {
        return new String[]{"org.nexage.sourcekit.vast.activity.VASTActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        this.a = ((ar) ak.m.get(i)).l.optString("vast_xml");
        String optString = ((ar) ak.m.get(i)).l.optString("vast_url");
        String optString2 = ((ar) ak.m.get(i)).l.optString("vpaid_url");
        this.e = ao.a() ? ao.a() : ((ar) ak.m.get(i)).l.optBoolean("video_wo_banners");
        this.d = ((ar) ak.m.get(i)).l.optBoolean("video_auto_close", true);
        if ((this.a == null || this.a.isEmpty() || this.a.equals(" ")) && (optString == null || optString.isEmpty() || optString.equals(" "))) {
            am.b(i, i2, b);
            return;
        }
        this.f = new ad(b, i, i2);
        if (this.a == null || this.a.isEmpty() || this.a.equals(" ")) {
            k kVar = new k(activity, new a(), i, i2, optString);
            return;
        }
        this.c = new VASTPlayer(activity);
        this.c.setPrecache(true);
        this.c.setRtbInfo(a(b.a(), i, true));
        if (optString2 != null) {
            this.c.setXmlUrl(optString2);
        }
        this.c.setMaxDuration(ak.w);
        this.c.setDisableLongVideo(ak.x);
        this.c.loadVideoWithData(this.a, this.f);
    }

    public void a(Activity activity, int i) {
        if (this.c.checkFile()) {
            this.c.play(b.REWARDED, this.e, this.d, this.f);
            am.a(i, b);
            return;
        }
        am.a(true);
    }

    public boolean e() {
        return true;
    }

    public boolean f() {
        return true;
    }
}
