package com.amazon.device.ads;

public class ViewabilityObserverFactory {
    public ViewabilityObserver buildViewabilityObserver(AdController adController) {
        return new ViewabilityObserver(adController);
    }
}
