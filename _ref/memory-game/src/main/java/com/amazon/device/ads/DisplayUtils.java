package com.amazon.device.ads;

import android.content.Context;
import android.view.WindowManager;

class DisplayUtils {
    private static int[][] rotationArray = new int[][]{new int[]{1, 0, 9, 8}, new int[]{0, 9, 8, 1}};

    DisplayUtils() {
    }

    public static int determineCanonicalScreenOrientation(Context context, AndroidBuildInfo androidBuildInfo) {
        int i = 0;
        int rotation = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
        int i2 = context.getResources().getConfiguration().orientation;
        i2 = i2 == 1 ? (rotation == 0 || rotation == 2) ? 1 : 0 : i2 == 2 ? (rotation == 1 || rotation == 3) ? 1 : 0 : 1;
        if (i2 == 0) {
            i = 1;
        }
        return rotationArray[i][rotation];
    }
}
