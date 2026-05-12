package com.google.android.gms.internal;

import com.google.android.gms.internal.zzla.zza;
import com.google.android.gms.internal.zzla.zzc;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@zzin
public class zzlb<T> implements zzla<T> {
    private final Object zzail = new Object();
    protected int zzblv = 0;
    protected final BlockingQueue<zza> zzcob = new LinkedBlockingQueue();
    protected T zzcoc;

    public int getStatus() {
        return this.zzblv;
    }

    public void reject() {
        synchronized (this.zzail) {
            if (this.zzblv != 0) {
                throw new UnsupportedOperationException();
            }
            this.zzblv = -1;
            for (zza com_google_android_gms_internal_zzlb_zza : this.zzcob) {
                com_google_android_gms_internal_zzlb_zza.zzcoe.run();
            }
            this.zzcob.clear();
        }
    }

    public void zza(zzc<T> com_google_android_gms_internal_zzla_zzc_T, zza com_google_android_gms_internal_zzla_zza) {
        synchronized (this.zzail) {
            if (this.zzblv == 1) {
                com_google_android_gms_internal_zzla_zzc_T.zzd(this.zzcoc);
            } else if (this.zzblv == -1) {
                com_google_android_gms_internal_zzla_zza.run();
            } else if (this.zzblv == 0) {
                this.zzcob.add(new zza(this, com_google_android_gms_internal_zzla_zzc_T, com_google_android_gms_internal_zzla_zza));
            }
        }
    }

    public void zzg(T t) {
        synchronized (this.zzail) {
            if (this.zzblv != 0) {
                throw new UnsupportedOperationException();
            }
            this.zzcoc = t;
            this.zzblv = 1;
            for (zza com_google_android_gms_internal_zzlb_zza : this.zzcob) {
                com_google_android_gms_internal_zzlb_zza.zzcod.zzd(t);
            }
            this.zzcob.clear();
        }
    }
}
