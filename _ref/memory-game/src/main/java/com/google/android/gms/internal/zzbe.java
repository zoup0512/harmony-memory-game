package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbe extends zzbp {
    public zzbe(zzax com_google_android_gms_internal_zzax, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        super(com_google_android_gms_internal_zzax, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
    }

    protected void zzcu() throws IllegalAccessException, InvocationTargetException {
        this.zzaha.zzcw = Long.valueOf(-1);
        this.zzaha.zzcx = Long.valueOf(-1);
        int[] iArr = (int[]) this.zzahh.invoke(null, new Object[]{this.zzaey.getContext()});
        synchronized (this.zzaha) {
            this.zzaha.zzcw = Long.valueOf((long) iArr[0]);
            this.zzaha.zzcx = Long.valueOf((long) iArr[1]);
        }
    }
}
