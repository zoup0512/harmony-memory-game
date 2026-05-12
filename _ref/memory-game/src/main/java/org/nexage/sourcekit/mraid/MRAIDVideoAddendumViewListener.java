package org.nexage.sourcekit.mraid;

public interface MRAIDVideoAddendumViewListener {
    void mraidVideoAddendumViewClickThru(MRAIDVideoAddendumView mRAIDVideoAddendumView, String str);

    void mraidVideoAddendumViewClose(MRAIDVideoAddendumView mRAIDVideoAddendumView);

    void mraidVideoAddendumViewComplete(MRAIDVideoAddendumView mRAIDVideoAddendumView);

    void mraidVideoAddendumViewError(MRAIDVideoAddendumView mRAIDVideoAddendumView, String str);

    void mraidVideoAddendumViewExpand(MRAIDVideoAddendumView mRAIDVideoAddendumView);

    void mraidVideoAddendumViewFirstQuartile(MRAIDVideoAddendumView mRAIDVideoAddendumView);

    void mraidVideoAddendumViewLoaded(MRAIDVideoAddendumView mRAIDVideoAddendumView);

    void mraidVideoAddendumViewLog(MRAIDVideoAddendumView mRAIDVideoAddendumView, String str);

    void mraidVideoAddendumViewMidpoint(MRAIDVideoAddendumView mRAIDVideoAddendumView);

    void mraidVideoAddendumViewNoFill(MRAIDVideoAddendumView mRAIDVideoAddendumView);

    void mraidVideoAddendumViewPaused(MRAIDVideoAddendumView mRAIDVideoAddendumView);

    void mraidVideoAddendumViewPlaying(MRAIDVideoAddendumView mRAIDVideoAddendumView);

    boolean mraidVideoAddendumViewResize(MRAIDVideoAddendumView mRAIDVideoAddendumView, int i, int i2, int i3, int i4);

    void mraidVideoAddendumViewSkippableStateChange(MRAIDVideoAddendumView mRAIDVideoAddendumView, boolean z);

    void mraidVideoAddendumViewSkipped(MRAIDVideoAddendumView mRAIDVideoAddendumView);

    void mraidVideoAddendumViewStarted(MRAIDVideoAddendumView mRAIDVideoAddendumView);

    void mraidVideoAddendumViewStopped(MRAIDVideoAddendumView mRAIDVideoAddendumView);

    void mraidVideoAddendumViewThirdQuartile(MRAIDVideoAddendumView mRAIDVideoAddendumView);

    void mraidVideoAddendumViewUserClose(MRAIDVideoAddendumView mRAIDVideoAddendumView);

    void mraidVideoAddendumViewVideoStart(MRAIDVideoAddendumView mRAIDVideoAddendumView);
}
