package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.util.concurrent.Callable;

public class zzbi implements Callable {
    private final zzax zzaey;
    private final zza zzaha;

    public zzbi(zzax com_google_android_gms_internal_zzax, zza com_google_android_gms_internal_zzae_zza) {
        this.zzaey = com_google_android_gms_internal_zzax;
        this.zzaha = com_google_android_gms_internal_zzae_zza;
    }

    public /* synthetic */ Object call() throws Exception {
        return zzcx();
    }

    public Void zzcx() throws Exception {
        if (this.zzaey.zzcm() != null) {
            this.zzaey.zzcm().get();
        }
        zzapv zzcl = this.zzaey.zzcl();
        if (zzcl != null) {
            try {
                synchronized (this.zzaha) {
                    zzapv.zza(this.zzaha, zzapv.zzf(zzcl));
                }
            } catch (zzapu e) {
            }
        }
        return null;
    }
}
