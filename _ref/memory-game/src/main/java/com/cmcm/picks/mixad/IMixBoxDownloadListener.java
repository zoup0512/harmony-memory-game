package com.cmcm.picks.mixad;

public interface IMixBoxDownloadListener {
    boolean ctrlDownloadPause();

    void onDownloadProgress(String str, int i, int i2);
}
