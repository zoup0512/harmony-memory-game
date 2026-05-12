package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbc extends zzbp {
    private static final Object zzafc = new Object();
    private static volatile Long zzcr = null;

    public zzbc(zzax com_google_android_gms_internal_zzax, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        super(com_google_android_gms_internal_zzax, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
    }

    protected void zzcu() throws IllegalAccessException, InvocationTargetException {
        this.zzaha.zzdu = Long.valueOf(-1);
        if (zzcr == null) {
            synchronized (zzafc) {
                if (zzcr == null) {
                    zzcr = (Long) this.zzahh.invoke(null, new Object[]{this.zzaey.getContext()});
                }
            }
        }
        synchronized (this.zzaha) {
            this.zzaha.zzdu = zzcr;
        }
    }
}
