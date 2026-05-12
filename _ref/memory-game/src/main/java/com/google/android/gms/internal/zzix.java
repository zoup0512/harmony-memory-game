package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;

@zzin
public abstract class zzix {
    public abstract void zza(Context context, zzir com_google_android_gms_internal_zzir, VersionInfoParcel versionInfoParcel);

    protected void zze(zzir com_google_android_gms_internal_zzir) {
        com_google_android_gms_internal_zzir.zzri();
        if (com_google_android_gms_internal_zzir.zzrg() != null) {
            com_google_android_gms_internal_zzir.zzrg().release();
        }
    }
}
