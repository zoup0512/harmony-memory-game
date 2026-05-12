package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbl extends zzbp {
    private static final Object zzafc = new Object();
    private static volatile String zzct = null;

    public zzbl(zzax com_google_android_gms_internal_zzax, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        super(com_google_android_gms_internal_zzax, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
    }

    protected void zzcu() throws IllegalAccessException, InvocationTargetException {
        this.zzaha.zzct = "E";
        if (zzct == null) {
            synchronized (zzafc) {
                if (zzct == null) {
                    zzct = (String) this.zzahh.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zzaha) {
            this.zzaha.zzct = zzct;
        }
    }
}
