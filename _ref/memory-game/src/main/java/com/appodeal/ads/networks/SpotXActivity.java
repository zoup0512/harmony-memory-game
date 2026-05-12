package com.appodeal.ads.networks;

import android.app.Activity;
import com.appodeal.ads.networks.spotx.SpotXVPAIDView;
import org.nexage.sourcekit.util.VASTLog;

public class SpotXActivity extends Activity {
    private SpotXVPAIDView a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onCreate(android.os.Bundle r5) {
        /*
        r4 = this;
        r3 = 0;
        super.onCreate(r5);
        r0 = r4.getResources();
        r0 = r0.getConfiguration();
        r1 = r0.orientation;
        r0 = r4.getIntent();	 Catch:{ Exception -> 0x0076 }
        r2 = "type";
        r0 = r0.hasExtra(r2);	 Catch:{ Exception -> 0x0076 }
        if (r0 != 0) goto L_0x0023;
    L_0x001a:
        r4.finish();	 Catch:{ Exception -> 0x0076 }
        r0 = 0;
        r2 = 0;
        r4.overridePendingTransition(r0, r2);	 Catch:{ Exception -> 0x0076 }
    L_0x0022:
        return;
    L_0x0023:
        r0 = r4.getIntent();	 Catch:{ Exception -> 0x0076 }
        r2 = "type";
        r0 = r0.getSerializableExtra(r2);	 Catch:{ Exception -> 0x0076 }
        r0 = (com.appodeal.ads.ao.b) r0;	 Catch:{ Exception -> 0x0076 }
        r2 = com.appodeal.ads.networks.SpotXActivity.AnonymousClass2.a;	 Catch:{ Exception -> 0x0076 }
        r0 = r0.ordinal();	 Catch:{ Exception -> 0x0076 }
        r0 = r2[r0];	 Catch:{ Exception -> 0x0076 }
        switch(r0) {
            case 1: goto L_0x0049;
            case 2: goto L_0x008d;
            default: goto L_0x003a;
        };	 Catch:{ Exception -> 0x0076 }
    L_0x003a:
        r4.finish();	 Catch:{ Exception -> 0x0076 }
        r0 = 0;
        r2 = 0;
        r4.overridePendingTransition(r0, r2);	 Catch:{ Exception -> 0x0076 }
    L_0x0042:
        r0 = 2;
        if (r1 == r0) goto L_0x00c8;
    L_0x0045:
        r4.setRequestedOrientation(r3);
        goto L_0x0022;
    L_0x0049:
        r0 = r4.getIntent();	 Catch:{ Exception -> 0x0076 }
        r2 = "videoClass";
        r0 = r0.getStringExtra(r2);	 Catch:{ Exception -> 0x0076 }
        r0 = com.appodeal.ads.ah.a(r4, r0);	 Catch:{ Exception -> 0x0076 }
        if (r0 == 0) goto L_0x0081;
    L_0x005a:
        r0 = r0.g();	 Catch:{ Exception -> 0x0076 }
        r0 = (com.appodeal.ads.g.u) r0;	 Catch:{ Exception -> 0x0076 }
        r0 = r0.g();	 Catch:{ Exception -> 0x0076 }
        r4.a = r0;	 Catch:{ Exception -> 0x0076 }
        r0 = r4.a;	 Catch:{ Exception -> 0x0076 }
        if (r0 != 0) goto L_0x0042;
    L_0x006a:
        r4.finish();	 Catch:{ Exception -> 0x0076 }
        r0 = 0;
        r2 = 0;
        r4.overridePendingTransition(r0, r2);	 Catch:{ Exception -> 0x0076 }
        com.appodeal.ads.aj.a();	 Catch:{ Exception -> 0x0076 }
        goto L_0x0022;
    L_0x0076:
        r0 = move-exception;
        com.appodeal.ads.Appodeal.a(r0);
        r4.finish();
        r4.overridePendingTransition(r3, r3);
        goto L_0x0042;
    L_0x0081:
        r4.finish();	 Catch:{ Exception -> 0x0076 }
        r0 = 0;
        r2 = 0;
        r4.overridePendingTransition(r0, r2);	 Catch:{ Exception -> 0x0076 }
        com.appodeal.ads.aj.a();	 Catch:{ Exception -> 0x0076 }
        goto L_0x0022;
    L_0x008d:
        r0 = r4.getIntent();	 Catch:{ Exception -> 0x0076 }
        r2 = "videoClass";
        r0 = r0.getStringExtra(r2);	 Catch:{ Exception -> 0x0076 }
        r0 = com.appodeal.ads.ak.a(r4, r0);	 Catch:{ Exception -> 0x0076 }
        if (r0 == 0) goto L_0x00bb;
    L_0x009e:
        r0 = r0.g();	 Catch:{ Exception -> 0x0076 }
        r0 = (com.appodeal.ads.e.u) r0;	 Catch:{ Exception -> 0x0076 }
        r0 = r0.g();	 Catch:{ Exception -> 0x0076 }
        r4.a = r0;	 Catch:{ Exception -> 0x0076 }
        r0 = r4.a;	 Catch:{ Exception -> 0x0076 }
        if (r0 != 0) goto L_0x0042;
    L_0x00ae:
        r4.finish();	 Catch:{ Exception -> 0x0076 }
        r0 = 0;
        r2 = 0;
        r4.overridePendingTransition(r0, r2);	 Catch:{ Exception -> 0x0076 }
        com.appodeal.ads.am.a();	 Catch:{ Exception -> 0x0076 }
        goto L_0x0022;
    L_0x00bb:
        r4.finish();	 Catch:{ Exception -> 0x0076 }
        r0 = 0;
        r2 = 0;
        r4.overridePendingTransition(r0, r2);	 Catch:{ Exception -> 0x0076 }
        com.appodeal.ads.am.a();	 Catch:{ Exception -> 0x0076 }
        goto L_0x0022;
    L_0x00c8:
        r4.a();
        r0 = r4.a;
        r1 = new com.appodeal.ads.networks.SpotXActivity$1;
        r1.<init>(r4);
        r0.setListener(r1);
        r0 = r4.a;
        r0 = r0.getParent();
        if (r0 == 0) goto L_0x00e8;
    L_0x00dd:
        r1 = r0 instanceof android.view.ViewManager;
        if (r1 == 0) goto L_0x00e8;
    L_0x00e1:
        r0 = (android.view.ViewManager) r0;
        r1 = r4.a;
        r0.removeView(r1);
    L_0x00e8:
        r0 = r4.a;
        r4.setContentView(r0);
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.networks.SpotXActivity.onCreate(android.os.Bundle):void");
    }

    private void a() {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
    }

    private void b() {
        VASTLog.d("SpotXActivity", "finishVPAID");
        finish();
    }

    private boolean c() {
        return this.a.d();
    }

    public void onBackPressed() {
        if (c()) {
            if (this.a.getListener() != null) {
                this.a.getListener().c();
            }
            b();
        }
    }
}
