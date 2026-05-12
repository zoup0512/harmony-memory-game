package com.amazon.device.ads;

public class AmazonViewableAdSDKBridgeFactory implements AdSDKBridgeFactory {
    public AdSDKBridge createAdSDKBridge(AdControlAccessor adControlAccessor) {
        return new AmazonViewableAdSDKBridge(adControlAccessor, new JavascriptInteractor());
    }
}
