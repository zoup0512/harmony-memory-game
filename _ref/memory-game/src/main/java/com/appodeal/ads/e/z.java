package com.appodeal.ads.e;

import com.appodeal.ads.am;
import com.appodeal.ads.ap;
import com.tapjoy.TJActionRequest;
import com.tapjoy.TJError;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementVideoListener;

public class z implements TJPlacementListener, TJPlacementVideoListener {
    private final ap a;
    private final int b;
    private final int c;

    z(ap apVar, int i, int i2) {
        this.a = apVar;
        this.b = i;
        this.c = i2;
    }

    public void onRequestSuccess(TJPlacement tJPlacement) {
        if (!tJPlacement.isContentAvailable()) {
            am.b(this.b, this.c, this.a);
        }
    }

    public void onRequestFailure(TJPlacement tJPlacement, TJError tJError) {
        am.b(this.b, this.c, this.a);
    }

    public void onContentReady(TJPlacement tJPlacement) {
        am.a(this.b, this.c, this.a);
    }

    public void onContentShow(TJPlacement tJPlacement) {
    }

    public void onVideoStart(TJPlacement tJPlacement) {
        am.a(this.b, this.a);
    }

    public void onVideoError(TJPlacement tJPlacement, String str) {
    }

    public void onVideoComplete(TJPlacement tJPlacement) {
        am.b(this.b, this.a);
    }

    public void onContentDismiss(TJPlacement tJPlacement) {
        am.d(this.b, this.a);
    }

    public void onPurchaseRequest(TJPlacement tJPlacement, TJActionRequest tJActionRequest, String str) {
    }

    public void onRewardRequest(TJPlacement tJPlacement, TJActionRequest tJActionRequest, String str, int i) {
    }
}
