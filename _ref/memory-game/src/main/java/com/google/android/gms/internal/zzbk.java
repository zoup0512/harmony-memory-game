package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbk extends zzbp {
    private long zzahc = -1;

    public zzbk(zzax com_google_android_gms_internal_zzax, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        super(com_google_android_gms_internal_zzax, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
    }

    protected void zzcu() throws IllegalAccessException, InvocationTargetException {
        this.zzaha.zzdd = Long.valueOf(-1);
        if (this.zzahc == -1) {
            this.zzahc = (long) ((Integer) this.zzahh.invoke(null, new Object[]{this.zzaey.getContext()})).intValue();
        }
        synchronized (this.zzaha) {
            this.zzaha.zzdd = Long.valueOf(this.zzahc);
        }
    }
}
