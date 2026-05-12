package org.nexage.sourcekit.mraid.internal;

import android.content.Context;
import android.os.Build.VERSION;
import java.util.ArrayList;
import org.nexage.sourcekit.mraid.MRAIDNativeFeature;

public class MRAIDNativeFeatureManager {
    private static final String TAG = "MRAIDNativeFeatureManager";
    private Context context;
    private ArrayList<String> supportedNativeFeatures;

    public MRAIDNativeFeatureManager(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.supportedNativeFeatures = arrayList;
    }

    public boolean isCalendarSupported() {
        boolean z = this.supportedNativeFeatures.contains(MRAIDNativeFeature.CALENDAR) && VERSION.SDK_INT >= 14 && this.context.checkCallingOrSelfPermission("android.permission.WRITE_CALENDAR") == 0;
        MRAIDLog.d(TAG, "isCalendarSupported " + z);
        return z;
    }

    public boolean isInlineVideoSupported() {
        boolean contains = this.supportedNativeFeatures.contains(MRAIDNativeFeature.INLINE_VIDEO);
        MRAIDLog.d(TAG, "isInlineVideoSupported " + contains);
        return contains;
    }

    public boolean isSmsSupported() {
        boolean z = this.supportedNativeFeatures.contains("sms") && this.context.checkCallingOrSelfPermission("android.permission.SEND_SMS") == 0;
        MRAIDLog.d(TAG, "isSmsSupported " + z);
        return z;
    }

    public boolean isStorePictureSupported() {
        boolean contains = this.supportedNativeFeatures.contains(MRAIDNativeFeature.STORE_PICTURE);
        MRAIDLog.d(TAG, "isStorePictureSupported " + contains);
        return contains;
    }

    public boolean isTelSupported() {
        boolean z = this.supportedNativeFeatures.contains("tel") && this.context.checkCallingOrSelfPermission("android.permission.CALL_PHONE") == 0;
        MRAIDLog.d(TAG, "isTelSupported " + z);
        return z;
    }

    public ArrayList<String> getSupportedNativeFeatures() {
        return this.supportedNativeFeatures;
    }
}
