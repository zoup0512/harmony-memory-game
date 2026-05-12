package org.nexage.sourcekit.mraid;

public interface MRAIDInterstitialListener {
    void mraidInterstitialHide(MRAIDInterstitial mRAIDInterstitial);

    void mraidInterstitialLoaded(MRAIDInterstitial mRAIDInterstitial);

    void mraidInterstitialNoFill(MRAIDInterstitial mRAIDInterstitial);

    void mraidInterstitialShow(MRAIDInterstitial mRAIDInterstitial);
}
