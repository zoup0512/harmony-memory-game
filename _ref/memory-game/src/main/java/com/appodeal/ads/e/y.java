package com.appodeal.ads.e;

import android.app.Activity;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.ak;
import com.appodeal.ads.am;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.tapjoy.TJConnectListener;
import com.tapjoy.TJPlacement;
import com.tapjoy.Tapjoy;
import java.util.Hashtable;

public class y extends aq {
    private static ap b;
    private TJPlacement c;

    public static ap getInstance(String str, String[] strArr) {
        if (b == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new y();
            }
            b = new ap(str, h(), aqVar).d();
        }
        return b;
    }

    private static String[] h() {
        return new String[]{"com.tapjoy.TJContentActivity"};
    }

    public void a(Activity activity, final int i, final int i2) {
        String optString = ((ar) ak.m.get(i)).l.optString("sdk_key");
        final String optString2 = ((ar) ak.m.get(i)).l.optString("placement");
        Tapjoy.connect(Appodeal.b, optString, new Hashtable(), new TJConnectListener(this) {
            final /* synthetic */ y d;

            public void onConnectSuccess() {
                z zVar = new z(y.b, i, i2);
                this.d.c = new TJPlacement(Appodeal.b, optString2, zVar);
                this.d.c.setVideoListener(zVar);
                this.d.c.setMediationName("appodeal");
                this.d.c.setAdapterVersion("1.0.0");
                this.d.c.requestContent();
            }

            public void onConnectFailure() {
                am.b(i, i2, y.b);
            }
        });
    }

    public void a(Activity activity, int i) {
        if (this.c != null && this.c.isContentReady()) {
            this.c.showContent();
        }
    }
}
