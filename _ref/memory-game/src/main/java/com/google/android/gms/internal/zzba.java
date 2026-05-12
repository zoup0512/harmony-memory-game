package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;

public class zzba extends zzbp {
    private static final Object zzafc = new Object();
    private static volatile String zzagy = null;

    public zzba(zzax com_google_android_gms_internal_zzax, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        super(com_google_android_gms_internal_zzax, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
    }

    protected void zzcu() throws IllegalAccessException, InvocationTargetException {
        this.zzaha.zzdp = "E";
        if (zzagy == null) {
            synchronized (zzafc) {
                if (zzagy == null) {
                    zzagy = (String) this.zzahh.invoke(null, new Object[]{this.zzaey.getContext()});
                }
            }
        }
        synchronized (this.zzaha) {
            this.zzaha.zzdp = zzaj.zza(zzagy.getBytes(), true);
        }
    }
}
