package com.appodeal.ads.g;

import android.app.Activity;
import android.content.Context;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.VideoActivity;
import com.appodeal.ads.ah;
import com.appodeal.ads.aj;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.appodeal.ads.as;
import com.appodeal.ads.networks.k;
import com.appodeal.ads.utils.a.b;
import com.mopub.common.AdType;
import org.json.JSONObject;
import org.nexage.sourcekit.mraid.MRAIDInterstitial;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;

public class i extends aq {
    private static ap b;
    private MRAIDInterstitial c;
    private VideoActivity d;
    private b e;
    private int f;
    private int g;
    private String h;
    private long i;

    private class a implements com.appodeal.ads.networks.k.a {
        final /* synthetic */ i a;

        private a(i iVar) {
            this.a = iVar;
        }

        public void a(int i, int i2) {
            aj.b(i, i2, i.b);
        }

        public void a(String str, int i, int i2, String str2) {
            try {
                this.a.a = str;
                RtbInfo a = this.a.a(i.b.a(), i, false);
                j jVar = new j(i.b, i, i2, this.a.h, this.a.i);
                this.a.c = new MRAIDInterstitial(Appodeal.b, null, this.a.a, null, this.a.f, this.a.g, jVar, jVar, a);
            } catch (Throwable e) {
                Appodeal.a(e);
                aj.b(i, i2, i.b);
            }
        }
    }

    public static ap getInstance(String str, String[] strArr) {
        if (b == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new i();
            }
            b = new ap(str, h(), aqVar);
        }
        return b;
    }

    private static String[] h() {
        return new String[0];
    }

    public void a(Activity activity, int i, int i2) {
        JSONObject optJSONObject = ((ar) ah.m.get(i)).l.optJSONObject("freq");
        this.h = ((ar) ah.m.get(i)).l.optString("package");
        this.i = ((ar) ah.m.get(i)).l.optLong("expiry");
        if (optJSONObject != null) {
            this.e = new b(activity, optJSONObject);
            if (!this.e.a((Context) activity)) {
                this.e = null;
                aj.b(i, i2, b);
                return;
            }
        }
        this.e = null;
        this.a = ((ar) ah.m.get(i)).l.optString(AdType.HTML);
        String optString = ((ar) ah.m.get(i)).l.optString("mraid_url");
        if ((this.a == null || this.a.isEmpty() || this.a.equals(" ")) && (optString == null || optString.isEmpty() || optString.equals(" "))) {
            aj.b(i, i2, b);
            return;
        }
        this.f = Integer.parseInt(((ar) ah.m.get(i)).l.getString("width"));
        this.g = Integer.parseInt(((ar) ah.m.get(i)).l.getString("height"));
        if (this.a == null || this.a.isEmpty() || this.a.equals(" ")) {
            k kVar = new k(activity, new a(), i, i2, optString);
            return;
        }
        j jVar = new j(b, i, i2, this.h, this.i);
        Context context = activity;
        String str = null;
        String[] strArr = null;
        this.c = new MRAIDInterstitial(context, str, this.a, strArr, this.f, this.g, jVar, jVar, a(b.a(), i, false));
    }

    public void a(Activity activity, int i) {
        if (this.e != null) {
            this.e.b(activity);
        }
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

    public boolean f() {
        return true;
    }
}
