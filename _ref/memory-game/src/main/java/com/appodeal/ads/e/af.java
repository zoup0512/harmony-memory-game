package com.appodeal.ads.e;

import com.appodeal.ads.am;
import com.appodeal.ads.ap;
import com.appodeal.ads.networks.vpaid.a;
import com.appodeal.ads.networks.vpaid.c;

class af implements a {
    private final ap a;
    private final int b;
    private final int c;

    af(ap apVar, int i, int i2) {
        this.a = apVar;
        this.b = i;
        this.c = i2;
    }

    public void a() {
        c.a("VPAIDListener", "vpaidAdUserClose");
        am.d(this.b, this.a);
    }

    public void a(String str) {
        c.a("VPAIDListener", "vpaidAdError (" + str + ")");
        am.b(this.b, this.c, this.a);
    }
}
