package com.amazon.device.ads;

import android.os.Build;
import android.os.Build.VERSION;

class AndroidBuildInfo {
    private String make = Build.MANUFACTURER;
    private String model = Build.MODEL;
    private String osVersion = VERSION.RELEASE;
    private int sdkInt = VERSION.SDK_INT;

    AndroidBuildInfo() {
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public int getSDKInt() {
        return this.sdkInt;
    }
}
