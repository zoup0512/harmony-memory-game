package org.nexage.sourcekit.mraid.properties;

import com.amazon.device.ads.DeviceInfo;
import java.util.Arrays;

public final class MRAIDOrientationProperties {
    public static final int FORCE_ORIENTATION_LANDSCAPE = 1;
    public static final int FORCE_ORIENTATION_NONE = 2;
    public static final int FORCE_ORIENTATION_PORTRAIT = 0;
    public boolean allowOrientationChange;
    public int forceOrientation;

    public MRAIDOrientationProperties() {
        this(true, 2);
    }

    public MRAIDOrientationProperties(boolean z, int i) {
        this.allowOrientationChange = z;
        this.forceOrientation = i;
    }

    public static int forceOrientationFromString(String str) {
        int indexOf = Arrays.asList(new String[]{DeviceInfo.ORIENTATION_PORTRAIT, DeviceInfo.ORIENTATION_LANDSCAPE, "none"}).indexOf(str);
        return indexOf != -1 ? indexOf : 2;
    }

    public String forceOrientationString() {
        switch (this.forceOrientation) {
            case 0:
                return DeviceInfo.ORIENTATION_PORTRAIT;
            case 1:
                return DeviceInfo.ORIENTATION_LANDSCAPE;
            case 2:
                return "none";
            default:
                return "error";
        }
    }
}
