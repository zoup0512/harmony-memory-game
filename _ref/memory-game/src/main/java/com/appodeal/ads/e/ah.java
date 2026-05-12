package com.appodeal.ads.e;

import com.appodeal.ads.am;
import com.appodeal.ads.ao.a;
import com.appodeal.ads.ap;
import com.vungle.publisher.EventListener;

class ah implements EventListener {
    private final ap a;
    private final int b;

    ah(ap apVar, int i) {
        this.a = apVar;
        this.b = i;
    }

    public void onAdPlayableChanged(boolean z) {
        if (z) {
            ag.b = a.AVAILABLE;
        }
    }

    public void onAdUnavailable(String str) {
        ag.b = a.NOT_AVAILABLE_AFTER_DELAY;
    }

    public void onAdStart() {
        am.a(this.b, this.a);
    }

    public void onVideoView(boolean z, int i, int i2) {
        if (z) {
            am.b(this.b, this.a);
        }
    }

    public void onAdEnd(boolean z) {
        am.d(this.b, this.a);
    }
}
