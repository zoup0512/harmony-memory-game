package com.appodeal.ads.b;

import com.appodeal.ads.o;
import com.flurry.android.ads.FlurryAdErrorType;
import com.flurry.android.ads.FlurryAdInterstitial;
import com.flurry.android.ads.FlurryAdInterstitialListener;

public class q implements FlurryAdInterstitialListener {
    private final o a;
    private final int b;
    private final int c;

    q(o oVar, int i, int i2) {
        this.a = oVar;
        this.b = i;
        this.c = i2;
    }

    public void onFetched(FlurryAdInterstitial flurryAdInterstitial) {
        com.appodeal.ads.q.a(this.b, this.c, this.a);
    }

    public void onError(FlurryAdInterstitial flurryAdInterstitial, FlurryAdErrorType flurryAdErrorType, int i) {
        com.appodeal.ads.q.b(this.b, this.c, this.a);
    }

    public void onDisplay(FlurryAdInterstitial flurryAdInterstitial) {
        com.appodeal.ads.q.a(this.b, this.a);
    }

    public void onClicked(FlurryAdInterstitial flurryAdInterstitial) {
        com.appodeal.ads.q.b(this.b, this.a);
    }

    public void onClose(FlurryAdInterstitial flurryAdInterstitial) {
        com.appodeal.ads.q.c(this.b, this.a);
    }

    public void onRendered(FlurryAdInterstitial flurryAdInterstitial) {
    }

    public void onAppExit(FlurryAdInterstitial flurryAdInterstitial) {
    }

    public void onVideoCompleted(FlurryAdInterstitial flurryAdInterstitial) {
    }
}
