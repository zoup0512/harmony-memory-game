package com.appodeal.ads.g;

import com.appodeal.ads.aj;
import com.appodeal.ads.ap;
import com.appodeal.ads.networks.vpaid.a;
import com.appodeal.ads.networks.vpaid.c;

class ab implements a {
    private final ap a;
    private final int b;
    private final int c;

    ab(ap apVar, int i, int i2) {
        this.a = apVar;
        this.b = i;
        this.c = i2;
    }

    public void a() {
        c.a("VPAIDListener", "vpaidAdUserClose");
        aj.d(this.b, this.a);
    }

    public void a(String str) {
        c.a("VPAIDListener", "vpaidAdError (" + str + ")");
        aj.b(this.b, this.c, this.a);
    }
}
