package com.unity3d.ads2.api;

class AdUnit$4 implements Runnable {
    final /* synthetic */ Integer val$systemUiVisibility;

    AdUnit$4(Integer num) {
        this.val$systemUiVisibility = num;
    }

    public void run() {
        if (AdUnit.getAdUnitActivity() != null) {
            AdUnit.getAdUnitActivity().setSystemUiVisibility(this.val$systemUiVisibility.intValue());
        }
    }
}
