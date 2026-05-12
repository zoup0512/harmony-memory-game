package com.google.android.gms.internal;

import java.util.Comparator;

class zzcq$1 implements Comparator<zzct$zza> {
    final /* synthetic */ zzcq zzate;

    zzcq$1(zzcq com_google_android_gms_internal_zzcq) {
        this.zzate = com_google_android_gms_internal_zzcq;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return zza((zzct$zza) obj, (zzct$zza) obj2);
    }

    public int zza(zzct$zza com_google_android_gms_internal_zzct_zza, zzct$zza com_google_android_gms_internal_zzct_zza2) {
        int i = com_google_android_gms_internal_zzct_zza.zzatj - com_google_android_gms_internal_zzct_zza2.zzatj;
        return i != 0 ? i : (int) (com_google_android_gms_internal_zzct_zza.value - com_google_android_gms_internal_zzct_zza2.value);
    }
}
