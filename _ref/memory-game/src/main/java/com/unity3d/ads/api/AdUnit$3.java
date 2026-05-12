package com.unity3d.ads.api;

class AdUnit$3 implements Runnable {
    final /* synthetic */ Boolean val$screenOn;

    AdUnit$3(Boolean bool) {
        this.val$screenOn = bool;
    }

    public void run() {
        if (AdUnit.getAdUnitActivity() != null) {
            AdUnit.getAdUnitActivity().setKeepScreenOn(this.val$screenOn.booleanValue());
        }
    }
}
