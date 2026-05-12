package com.unity3d.ads2.api;

class AdUnit$2 implements Runnable {
    final /* synthetic */ Integer val$orientation;

    AdUnit$2(Integer num) {
        this.val$orientation = num;
    }

    public void run() {
        if (AdUnit.getAdUnitActivity() != null) {
            AdUnit.getAdUnitActivity().setOrientation(this.val$orientation.intValue());
        }
    }
}
