package com.google.android.gms.internal;

import android.content.ComponentName;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;

public class zzaqb extends CustomTabsServiceConnection {
    private WeakReference<zzaqc> bkx;

    public zzaqb(zzaqc com_google_android_gms_internal_zzaqc) {
        this.bkx = new WeakReference(com_google_android_gms_internal_zzaqc);
    }

    public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
        zzaqc com_google_android_gms_internal_zzaqc = (zzaqc) this.bkx.get();
        if (com_google_android_gms_internal_zzaqc != null) {
            com_google_android_gms_internal_zzaqc.zza(customTabsClient);
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        zzaqc com_google_android_gms_internal_zzaqc = (zzaqc) this.bkx.get();
        if (com_google_android_gms_internal_zzaqc != null) {
            com_google_android_gms_internal_zzaqc.zzkm();
        }
    }
}
