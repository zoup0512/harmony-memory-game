package com.mopub.mobileads;

import com.mopub.common.logging.MoPubLog;

class VastManager$1 implements VideoDownloader$VideoDownloaderListener {
    final /* synthetic */ VastManager this$0;
    final /* synthetic */ VastVideoConfig val$vastVideoConfig;

    VastManager$1(VastManager vastManager, VastVideoConfig vastVideoConfig) {
        this.this$0 = vastManager;
        this.val$vastVideoConfig = vastVideoConfig;
    }

    public void onComplete(boolean z) {
        if (z && VastManager.access$000(this.this$0, this.val$vastVideoConfig)) {
            VastManager.access$100(this.this$0).onVastVideoConfigurationPrepared(this.val$vastVideoConfig);
            return;
        }
        MoPubLog.d("Failed to download VAST video.");
        VastManager.access$100(this.this$0).onVastVideoConfigurationPrepared(null);
    }
}
