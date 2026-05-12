package com.mopub.common.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.amazon.device.ads.DeviceInfo;

public enum DeviceUtils$ForceOrientation {
    FORCE_PORTRAIT(DeviceInfo.ORIENTATION_PORTRAIT),
    FORCE_LANDSCAPE(DeviceInfo.ORIENTATION_LANDSCAPE),
    DEVICE_ORIENTATION("device"),
    UNDEFINED("");
    
    @NonNull
    private final String mKey;

    private DeviceUtils$ForceOrientation(String str) {
        this.mKey = str;
    }

    @NonNull
    public static DeviceUtils$ForceOrientation getForceOrientation(@Nullable String str) {
        for (DeviceUtils$ForceOrientation deviceUtils$ForceOrientation : values()) {
            if (deviceUtils$ForceOrientation.mKey.equalsIgnoreCase(str)) {
                return deviceUtils$ForceOrientation;
            }
        }
        return UNDEFINED;
    }
}
