package com.appodeal.ads.a;

import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.my.target.ads.MyTargetView;
import com.my.target.ads.MyTargetView.MyTargetViewListener;

class s implements MyTargetViewListener {
    private final h a;
    private final int b;
    private final int c;

    s(h hVar, int i, int i2) {
        this.a = hVar;
        this.b = i;
        this.c = i2;
    }

    public void onLoad(MyTargetView myTargetView) {
        j.a(this.b, this.c, this.a);
    }

    public void onNoAd(String str, MyTargetView myTargetView) {
        j.b(this.b, this.c, this.a);
    }

    public void onClick(MyTargetView myTargetView) {
        j.c(this.b, this.a);
    }
}
