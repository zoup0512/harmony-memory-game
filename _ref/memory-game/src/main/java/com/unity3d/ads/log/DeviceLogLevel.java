package com.unity3d.ads.log;

public class DeviceLogLevel {
    private static final String LOG_TAG = "UnityAds";
    private String _receivingMethodName = null;

    public DeviceLogLevel(String str) {
        this._receivingMethodName = str;
    }

    public String getLogTag() {
        return LOG_TAG;
    }

    public String getReceivingMethodName() {
        return this._receivingMethodName;
    }
}
