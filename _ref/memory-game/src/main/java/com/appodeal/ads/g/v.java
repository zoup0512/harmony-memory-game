package com.appodeal.ads.g;

import com.appodeal.ads.aj;
import com.appodeal.ads.ap;
import com.appodeal.ads.networks.spotx.SpotXVPAIDView.a;

public class v implements a {
    private final ap a;
    private final int b;
    private final int c;

    v(ap apVar, int i, int i2) {
        this.a = apVar;
        this.b = i;
        this.c = i2;
    }

    public void b() {
        aj.b(this.b, this.c, this.a);
    }

    public void a() {
        aj.a(this.b, this.c, this.a);
    }

    public void c() {
        aj.d(this.b, this.a);
    }

    public void d() {
        aj.b(this.b, this.a);
    }
}
