package com.amazon.device.ads;

public class ViewabilityCheckerFactory {
    public ViewabilityChecker buildViewabilityChecker(AdController adController) {
        return new ViewabilityChecker(adController);
    }
}
