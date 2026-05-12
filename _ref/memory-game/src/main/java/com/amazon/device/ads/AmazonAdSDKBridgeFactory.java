package com.amazon.device.ads;

class AmazonAdSDKBridgeFactory implements AdSDKBridgeFactory {
    AmazonAdSDKBridgeFactory() {
    }

    public AdSDKBridge createAdSDKBridge(AdControlAccessor adControlAccessor) {
        return new AmazonAdSDKBridge(adControlAccessor, new JavascriptInteractor());
    }
}
