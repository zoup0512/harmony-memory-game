package com.cmcm.picks.vastvideo;

public interface VastVideoProgressListener {
    void onVastVideoClick();

    void onVastVideoComplete();

    void onVastVideoShow();

    void onVastVideoShowFail(String str);
}
