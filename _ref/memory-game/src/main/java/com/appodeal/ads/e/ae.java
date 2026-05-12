package com.appodeal.ads.e;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build.VERSION;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.ak;
import com.appodeal.ads.am;
import com.appodeal.ads.an;
import com.appodeal.ads.ao;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.appodeal.ads.networks.k;
import com.appodeal.ads.networks.vpaid.VPAIDActivity;
import com.appodeal.ads.networks.vpaid.VPAIDView;
import com.appodeal.ads.networks.vpaid.b;
import com.mopub.common.AdType;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;
import org.nexage.sourcekit.vast.model.VASTModel;

public class ae extends aq {
    private static ap b;
    private static boolean e = false;
    private VPAIDView c;
    private String d;

    private class a implements com.appodeal.ads.networks.k.a {
        final /* synthetic */ ae a;

        private a(ae aeVar) {
            this.a = aeVar;
        }

        public void a(int i, int i2) {
            am.b(i, i2, ae.b);
        }

        public void a(String str, int i, int i2, String str2) {
            try {
                this.a.a = str;
                this.a.a(this.a.a, i, i2, this.a.a(ae.b.a(), i, true));
            } catch (Throwable e) {
                Appodeal.a(e);
                am.b(i, i2, ae.b);
            }
        }
    }

    public static ap getInstance(String str, String[] strArr) {
        if (b == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new ae();
            }
            b = new ap(str, i(), aqVar);
        }
        return b;
    }

    private static String[] i() {
        return new String[]{"com.appodeal.ads.networks.vpaid.VPAIDActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        if (VERSION.SDK_INT < 17) {
            am.a(i, i2);
            return;
        }
        this.d = ((ar) ak.m.get(i)).l.optString(AdType.HTML);
        this.a = ((ar) ak.m.get(i)).l.optString("vpaid_xml");
        String optString = ((ar) ak.m.get(i)).l.optString("vpaid_url");
        if ((this.a == null || this.a.isEmpty() || this.a.equals(" ")) && (optString == null || optString.isEmpty() || optString.equals(" "))) {
            am.b(i, i2, b);
        } else if (this.a == null || this.a.isEmpty() || this.a.equals(" ")) {
            k kVar = new k(activity, new a(), i, i2, optString);
        } else {
            a(this.a, i, i2, a(b.a(), i, true));
        }
    }

    private void a(String str, int i, int i2, RtbInfo rtbInfo) {
        VASTModel a = new b(str).a();
        if (a == null) {
            am.b(i, i2, b);
        } else {
            this.c = new VPAIDView(Appodeal.b, new af(b, i, i2), true, this.d, a, rtbInfo);
        }
    }

    public VPAIDView g() {
        return this.c;
    }

    public void a(Activity activity, int i) {
        try {
            Intent intent = new Intent(activity, VPAIDActivity.class);
            intent.addFlags(268435456);
            intent.addFlags(8388608);
            intent.putExtra("type", ao.b.REWARDED);
            intent.putExtra("videoClass", b.a());
            try {
                activity.startActivity(intent);
                am.a(i, b);
            } catch (ActivityNotFoundException e) {
                Appodeal.a("VPAIDActivity not found - did you declare it in AndroidManifest.xml?");
                am.a(true);
            }
        } catch (Throwable e2) {
            Appodeal.a(e2);
            am.a(true);
        }
    }

    public boolean d() {
        return e;
    }

    public void b(boolean z) {
        e = z;
    }
}
