package com.google.android.gms.internal;

import com.google.android.gms.internal.zzla.zza;
import com.google.android.gms.internal.zzla.zzb;
import com.google.android.gms.internal.zzla.zzc;

public class zzfs$zzc extends zzlb<zzft> {
    private final Object zzail = new Object();
    private final zzfs$zzd zzbmi;
    private boolean zzbmj;

    public zzfs$zzc(zzfs$zzd com_google_android_gms_internal_zzfs_zzd) {
        this.zzbmi = com_google_android_gms_internal_zzfs_zzd;
    }

    public void release() {
        synchronized (this.zzail) {
            if (this.zzbmj) {
                return;
            }
            this.zzbmj = true;
            zza(new zzc<zzft>(this) {
                final /* synthetic */ zzfs$zzc zzbmk;

                {
                    this.zzbmk = r1;
                }

                public void zzb(zzft com_google_android_gms_internal_zzft) {
                    zzkd.v("Ending javascript session.");
                    ((zzfu) com_google_android_gms_internal_zzft).zzmf();
                }

                public /* synthetic */ void zzd(Object obj) {
                    zzb((zzft) obj);
                }
            }, new zzb());
            zza(new zzc<zzft>(this) {
                final /* synthetic */ zzfs$zzc zzbmk;

                {
                    this.zzbmk = r1;
                }

                public void zzb(zzft com_google_android_gms_internal_zzft) {
                    zzkd.v("Releasing engine reference.");
                    this.zzbmk.zzbmi.zzmc();
                }

                public /* synthetic */ void zzd(Object obj) {
                    zzb((zzft) obj);
                }
            }, new zza(this) {
                final /* synthetic */ zzfs$zzc zzbmk;

                {
                    this.zzbmk = r1;
                }

                public void run() {
                    this.zzbmk.zzbmi.zzmc();
                }
            });
        }
    }
}
