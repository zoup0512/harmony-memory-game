package com.appodeal.ads.c;

import com.appodeal.ads.w;
import com.appodeal.ads.y;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;
import com.mopub.mobileads.MoPubView.BannerAdListener;

class p implements BannerAdListener {
    private final w a;
    private final int b;
    private final int c;

    p(w wVar, int i, int i2) {
        this.a = wVar;
        this.b = i;
        this.c = i2;
    }

    public void onBannerLoaded(MoPubView moPubView) {
        if (!(moPubView == null || moPubView.getmAdViewController() == null || moPubView.getmAdViewController().getmAdResponse() == null || moPubView.getmAdViewController().getmAdResponse().getStringBody() == null)) {
            this.a.f().a(moPubView.getmAdViewController().getmAdResponse().getStringBody());
        }
        y.a(this.b, this.c, this.a);
    }

    public void onBannerFailed(MoPubView moPubView, MoPubErrorCode moPubErrorCode) {
        y.b(this.b, this.c, this.a);
    }

    public void onBannerClicked(MoPubView moPubView) {
        y.c(this.b, this.a);
    }

    public void onBannerExpanded(MoPubView moPubView) {
    }

    public void onBannerCollapsed(MoPubView moPubView) {
    }
}
