package com.appodeal.ads.e;

import android.webkit.WebView;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.am;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import org.nexage.sourcekit.mraid.MRAIDNativeFeatureListener;
import org.nexage.sourcekit.mraid.MRAIDVideoAddendumInterstitial;
import org.nexage.sourcekit.mraid.MRAIDVideoAddendumInterstitialListener;

class l implements MRAIDNativeFeatureListener, MRAIDVideoAddendumInterstitialListener {
    private final ap a;
    private final int b;
    private final int c;

    l(ap apVar, int i, int i2) {
        this.a = apVar;
        this.b = i;
        this.c = i2;
    }

    public void mraidVideoAddendumInterstitialLoaded(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial) {
        am.a(this.b, this.c, this.a);
    }

    public void mraidVideoAddendumInterstitialShow(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial) {
    }

    public void mraidVideoAddendumInterstitialHide(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial) {
        am.d(this.b, this.a);
        if (this.a.g().c() != null) {
            this.a.g().c().finish();
            this.a.g().c().overridePendingTransition(0, 0);
        }
    }

    public void mraidVideoAddendumViewStarted(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial) {
        Appodeal.a("mraidVideoAddendumViewStarted");
    }

    public void mraidVideoAddendumViewStopped(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial) {
        Appodeal.a("mraidVideoAddendumViewStopped");
    }

    public void mraidVideoAddendumViewSkipped(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial) {
        Appodeal.a("mraidVideoAddendumViewSkipped");
    }

    public void mraidVideoAddendumViewVideoStart(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial) {
        Appodeal.a("mraidVideoAddendumViewVideoStart");
    }

    public void mraidVideoAddendumViewFirstQuartile(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial) {
        Appodeal.a("mraidVideoAddendumViewFirstQuartile");
    }

    public void mraidVideoAddendumViewMidpoint(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial) {
        Appodeal.a("mraidVideoAddendumViewMidpoint");
    }

    public void mraidVideoAddendumViewThirdQuartile(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial) {
        Appodeal.a("mraidVideoAddendumViewThirdQuartile");
    }

    public void mraidVideoAddendumViewComplete(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial) {
        Appodeal.a("mraidVideoAddendumViewComplete");
        am.b(this.b, this.a);
    }

    public void mraidVideoAddendumViewPaused(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial) {
        Appodeal.a("mraidVideoAddendumViewPaused");
    }

    public void mraidVideoAddendumViewPlaying(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial) {
        Appodeal.a("mraidVideoAddendumViewPlaying");
    }

    public void mraidVideoAddendumViewError(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial, String str) {
        Appodeal.a("mraidVideoAddendumViewError (" + str + ")");
    }

    public void mraidVideoAddendumViewClickThru(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial, String str) {
        Appodeal.a("mraidVideoAddendumViewClickThru (" + str + ")");
    }

    public void mraidVideoAddendumViewUserClose(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial) {
        Appodeal.a("mraidVideoAddendumViewUserClose");
    }

    public void mraidVideoAddendumViewSkippableStateChange(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial, boolean z) {
        Appodeal.a("mraidVideoAddendumViewSkippableStateChange - " + String.valueOf(z));
    }

    public void mraidVideoAddendumViewLog(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial, String str) {
        Appodeal.a("mraidVideoAddendumViewLog (" + str + ")");
    }

    public void mraidNativeFeatureCallTel(String str) {
    }

    public void mraidNativeFeatureCreateCalendarEvent(String str) {
    }

    public void mraidNativeFeaturePlayVideo(String str) {
    }

    public void mraidNativeFeatureOpenBrowser(String str, WebView webView) {
        an.a(Appodeal.b, str);
        if (this.a.g().c() != null) {
            this.a.g().c().finish();
            this.a.g().c().overridePendingTransition(0, 0);
        }
    }

    public void mraidNativeFeatureStorePicture(String str) {
    }

    public void mraidNativeFeatureSendSms(String str) {
    }

    public void mraidVideoAddendumInterstitialNoFill(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial) {
        am.b(this.b, this.c, this.a);
    }
}
