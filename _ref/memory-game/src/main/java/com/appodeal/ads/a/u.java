package com.appodeal.ads.a;

import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;
import com.mopub.mobileads.MoPubView.BannerAdListener;

class u implements BannerAdListener {
    private final h a;
    private final int b;
    private final int c;

    u(h hVar, int i, int i2) {
        this.a = hVar;
        this.b = i;
        this.c = i2;
    }

    public void onBannerLoaded(MoPubView moPubView) {
        if (!(moPubView == null || moPubView.getmAdViewController() == null || moPubView.getmAdViewController().getmAdResponse() == null || moPubView.getmAdViewController().getmAdResponse().getStringBody() == null)) {
            this.a.f().a(moPubView.getmAdViewController().getmAdResponse().getStringBody());
        }
        j.a(this.b, this.c, this.a);
    }

    public void onBannerFailed(MoPubView moPubView, MoPubErrorCode moPubErrorCode) {
        j.b(this.b, this.c, this.a);
    }

    public void onBannerClicked(MoPubView moPubView) {
        j.c(this.b, this.a);
    }

    public void onBannerExpanded(MoPubView moPubView) {
    }

    public void onBannerCollapsed(MoPubView moPubView) {
    }
}
