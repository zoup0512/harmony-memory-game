package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.text.TextUtils;

@zzin
public class zzdf {
    @Nullable
    public zzde zza(@Nullable zzdd com_google_android_gms_internal_zzdd) {
        if (com_google_android_gms_internal_zzdd == null) {
            throw new IllegalArgumentException("CSI configuration can't be null. ");
        } else if (!com_google_android_gms_internal_zzdd.zzjz()) {
            zzkd.v("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
            return null;
        } else if (com_google_android_gms_internal_zzdd.getContext() == null) {
            throw new IllegalArgumentException("Context can't be null. Please set up context in CsiConfiguration.");
        } else if (!TextUtils.isEmpty(com_google_android_gms_internal_zzdd.zzhl())) {
            return new zzde(com_google_android_gms_internal_zzdd.getContext(), com_google_android_gms_internal_zzdd.zzhl(), com_google_android_gms_internal_zzdd.zzka(), com_google_android_gms_internal_zzdd.zzkb());
        } else {
            throw new IllegalArgumentException("AfmaVersion can't be null or empty. Please set up afmaVersion in CsiConfiguration.");
        }
    }
}
