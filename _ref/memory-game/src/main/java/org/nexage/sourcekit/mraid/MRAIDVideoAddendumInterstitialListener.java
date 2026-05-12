package org.nexage.sourcekit.mraid;

public interface MRAIDVideoAddendumInterstitialListener {
    void mraidVideoAddendumInterstitialHide(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial);

    void mraidVideoAddendumInterstitialLoaded(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial);

    void mraidVideoAddendumInterstitialNoFill(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial);

    void mraidVideoAddendumInterstitialShow(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial);

    void mraidVideoAddendumViewClickThru(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial, String str);

    void mraidVideoAddendumViewComplete(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial);

    void mraidVideoAddendumViewError(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial, String str);

    void mraidVideoAddendumViewFirstQuartile(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial);

    void mraidVideoAddendumViewLog(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial, String str);

    void mraidVideoAddendumViewMidpoint(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial);

    void mraidVideoAddendumViewPaused(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial);

    void mraidVideoAddendumViewPlaying(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial);

    void mraidVideoAddendumViewSkippableStateChange(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial, boolean z);

    void mraidVideoAddendumViewSkipped(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial);

    void mraidVideoAddendumViewStarted(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial);

    void mraidVideoAddendumViewStopped(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial);

    void mraidVideoAddendumViewThirdQuartile(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial);

    void mraidVideoAddendumViewUserClose(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial);

    void mraidVideoAddendumViewVideoStart(MRAIDVideoAddendumInterstitial mRAIDVideoAddendumInterstitial);
}
