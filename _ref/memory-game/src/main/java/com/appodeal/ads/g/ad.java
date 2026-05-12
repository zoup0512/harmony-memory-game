package com.appodeal.ads.g;

import com.appodeal.ads.aj;
import com.appodeal.ads.ao.a;
import com.appodeal.ads.ap;
import com.vungle.publisher.EventListener;

class ad implements EventListener {
    private final ap a;
    private final int b;

    ad(ap apVar, int i) {
        this.a = apVar;
        this.b = i;
    }

    public void onAdPlayableChanged(boolean z) {
        if (z) {
            ac.b = a.AVAILABLE;
        }
    }

    public void onAdUnavailable(String str) {
        ac.b = a.NOT_AVAILABLE_AFTER_DELAY;
    }

    public void onAdStart() {
        aj.a(this.b, this.a);
    }

    public void onVideoView(boolean z, int i, int i2) {
        if (z) {
            aj.b(this.b, this.a);
        }
    }

    public void onAdEnd(boolean z) {
        aj.d(this.b, this.a);
    }
}
