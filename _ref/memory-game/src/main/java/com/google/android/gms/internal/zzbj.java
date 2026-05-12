package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbj extends zzbp {
    private static final Object zzafc = new Object();
    private static volatile Long zzahb = null;

    public zzbj(zzax com_google_android_gms_internal_zzax, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        super(com_google_android_gms_internal_zzax, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
    }

    protected void zzcu() throws IllegalAccessException, InvocationTargetException {
        if (zzahb == null) {
            synchronized (zzafc) {
                if (zzahb == null) {
                    zzahb = (Long) this.zzahh.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zzaha) {
            this.zzaha.zzdm = zzahb;
        }
    }
}
