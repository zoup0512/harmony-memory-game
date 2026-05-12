package com.appodeal.ads.e;

import android.app.Activity;
import android.content.Context;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.VideoActivity;
import com.appodeal.ads.ak;
import com.appodeal.ads.am;
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
    private int h;
    private String i;
    private long j;

    private class a implements com.appodeal.ads.networks.k.a {
        final /* synthetic */ i a;

        private a(i iVar) {
            this.a = iVar;
        }

        public void a(int i, int i2) {
            am.b(i, i2, i.b);
        }

        public void a(String str, int i, int i2, String str2) {
            try {
                this.a.a = str;
                RtbInfo a = this.a.a(i.b.a(), i, true);
                j jVar = new j(i.b, i, i2, this.a.i, this.a.j);
                this.a.c = new MRAIDInterstitial(Appodeal.b, null, this.a.a, null, this.a.f, this.a.g, jVar, jVar, a, true, this.a.h);
            } catch (Throwable e) {
                Appodeal.a(e);
                am.b(i, i2, i.b);
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
        JSONObject optJSONObject = ((ar) ak.m.get(i)).l.optJSONObject("freq");
        this.i = ((ar) ak.m.get(i)).l.optString("package");
        this.j = ((ar) ak.m.get(i)).l.optLong("expiry");
        if (optJSONObject != null) {
            this.e = new b(activity, optJSONObject);
            if (!this.e.a((Context) activity)) {
                this.e = null;
                am.b(i, i2, b);
                return;
            }
        }
        this.e = null;
        this.a = ((ar) ak.m.get(i)).l.optString(AdType.HTML);
        String optString = ((ar) ak.m.get(i)).l.optString("mraid_url");
        if ((this.a == null || this.a.isEmpty() || this.a.equals(" ")) && (optString == null || optString.isEmpty() || optString.equals(" "))) {
            am.b(i, i2, b);
            return;
        }
        this.f = Integer.parseInt(((ar) ak.m.get(i)).l.getString("width"));
        this.g = Integer.parseInt(((ar) ak.m.get(i)).l.getString("height"));
        this.h = ((ar) ak.m.get(i)).l.optInt("close_time", 0);
        if (this.a == null || this.a.isEmpty() || this.a.equals(" ")) {
            i iVar = this;
            k kVar = new k(activity, new a(), i, i2, optString);
            return;
        }
        j jVar = new j(b, i, i2, this.i, this.j);
        Context context = activity;
        j jVar2 = jVar;
        j jVar3 = jVar;
        this.c = new MRAIDInterstitial(context, null, this.a, null, this.f, this.g, jVar2, jVar3, a(b.a(), i, true), true, this.h);
    }

    public void a(Activity activity, int i) {
        if (this.e != null) {
            this.e.b(activity);
        }
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

    public boolean f() {
        return true;
    }
}
