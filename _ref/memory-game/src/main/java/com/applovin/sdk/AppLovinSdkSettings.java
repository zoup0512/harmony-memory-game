package com.applovin.sdk;

import com.applovin.impl.sdk.NativeAdImpl;

public class AppLovinSdkSettings {
    private boolean a = false;
    private long b = -1;
    private String c = AppLovinAdSize.INTERSTITIAL.getLabel();
    private String d = (AppLovinAdType.INCENTIVIZED.getLabel() + "," + AppLovinAdType.REGULAR.getLabel() + "," + NativeAdImpl.TYPE_NATIVE.getLabel());
    private boolean e = false;

    public String getAutoPreloadSizes() {
        return this.c;
    }

    public String getAutoPreloadTypes() {
        return this.d;
    }

    public long getBannerAdRefreshSeconds() {
        return this.b;
    }

    public boolean isMuted() {
        return this.e;
    }

    public boolean isVerboseLoggingEnabled() {
        return this.a;
    }

    public void setAutoPreloadSizes(String str) {
        this.c = str;
    }

    public void setAutoPreloadTypes(String str) {
        this.d = str;
    }

    public void setBannerAdRefreshSeconds(long j) {
        this.b = j;
    }

    public void setMuted(boolean z) {
        this.e = z;
    }

    public void setVerboseLogging(boolean z) {
        this.a = z;
    }
}
