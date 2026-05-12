package com.my.target.ads;

import com.my.target.Tracer;
import com.my.target.core.facades.c;
import com.my.target.core.facades.c.a;

class InterstitialAd$1 implements a {
    final /* synthetic */ InterstitialAd this$0;

    InterstitialAd$1(InterstitialAd interstitialAd) {
        this.this$0 = interstitialAd;
    }

    public void onLoad(c cVar) {
        if (InterstitialAd.access$000(this.this$0) != null) {
            InterstitialAd.access$000(this.this$0).onLoad(this.this$0);
        }
    }

    public void onError(String str, c cVar) {
        Tracer.d("InterstitialImageAd has no banners");
        if (InterstitialAd.access$000(this.this$0) != null) {
            InterstitialAd.access$000(this.this$0).onNoAd("No ad", this.this$0);
        }
    }

    public void onClick(c cVar) {
        if (InterstitialAd.access$000(this.this$0) != null) {
            InterstitialAd.access$000(this.this$0).onClick(this.this$0);
        }
    }

    public void onDismiss(c cVar) {
        if (InterstitialAd.access$000(this.this$0) != null) {
            InterstitialAd.access$000(this.this$0).onDismiss(this.this$0);
        }
    }

    public void onVideoCompleted(c cVar) {
        if (InterstitialAd.access$000(this.this$0) != null) {
            InterstitialAd.access$000(this.this$0).onVideoCompleted(this.this$0);
        }
    }

    public void onDisplay(c cVar) {
        if (InterstitialAd.access$000(this.this$0) != null) {
            InterstitialAd.access$000(this.this$0).onDisplay(this.this$0);
        }
    }
}
