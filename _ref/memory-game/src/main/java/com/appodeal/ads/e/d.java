package com.appodeal.ads.e;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.appodeal.ads.am;
import com.appodeal.ads.ap;
import java.util.Map;

class d implements AppLovinAdClickListener, AppLovinAdDisplayListener, AppLovinAdLoadListener, AppLovinAdRewardListener, AppLovinAdVideoPlaybackListener {
    private final ap a;
    private final int b;
    private final int c;

    d(ap apVar, int i, int i2) {
        this.a = apVar;
        this.b = i;
        this.c = i2;
    }

    public void adReceived(AppLovinAd appLovinAd) {
        if (appLovinAd.isVideoAd()) {
            am.a(this.b, this.c, this.a);
        } else {
            am.b(this.b, this.c, this.a);
        }
    }

    public void failedToReceiveAd(int i) {
        am.b(this.b, this.c, this.a);
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
        this.a.g().a(com.appodeal.ads.networks.d.a(appLovinAd));
        am.a(this.b, this.a);
    }

    public void adClicked(AppLovinAd appLovinAd) {
    }

    public void adHidden(AppLovinAd appLovinAd) {
        am.d(this.b, this.a);
    }

    public void videoPlaybackBegan(AppLovinAd appLovinAd) {
    }

    public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
        if (z) {
            am.b(this.b, this.a);
        }
    }

    public void userRewardVerified(AppLovinAd appLovinAd, Map map) {
    }

    public void userOverQuota(AppLovinAd appLovinAd, Map map) {
    }

    public void userRewardRejected(AppLovinAd appLovinAd, Map map) {
    }

    public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
    }

    public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
    }
}
