package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class zzbm extends zzbp {
    private List<Long> zzahd = null;

    public zzbm(zzax com_google_android_gms_internal_zzax, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        super(com_google_android_gms_internal_zzax, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
    }

    protected void zzcu() throws IllegalAccessException, InvocationTargetException {
        this.zzaha.zzdq = Long.valueOf(-1);
        this.zzaha.zzdr = Long.valueOf(-1);
        if (this.zzahd == null) {
            this.zzahd = (List) this.zzahh.invoke(null, new Object[]{this.zzaey.getContext()});
        }
        if (this.zzahd != null && this.zzahd.size() == 2) {
            synchronized (this.zzaha) {
                this.zzaha.zzdq = Long.valueOf(((Long) this.zzahd.get(0)).longValue());
                this.zzaha.zzdr = Long.valueOf(((Long) this.zzahd.get(1)).longValue());
            }
        }
    }
}
