package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbg extends zzbp {
    private long startTime;

    public zzbg(zzax com_google_android_gms_internal_zzax, String str, String str2, zza com_google_android_gms_internal_zzae_zza, long j, int i, int i2) {
        super(com_google_android_gms_internal_zzax, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
        this.startTime = j;
    }

    protected void zzcu() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zzahh.invoke(null, new Object[0])).longValue();
        synchronized (this.zzaha) {
            this.zzaha.zzek = Long.valueOf(longValue);
            if (this.startTime != 0) {
                this.zzaha.zzdi = Long.valueOf(longValue - this.startTime);
                this.zzaha.zzdn = Long.valueOf(this.startTime);
            }
        }
    }
}
