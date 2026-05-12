package com.google.android.gms.internal;

import com.google.android.gms.internal.zzla.zza;
import com.google.android.gms.internal.zzla.zzc;

class zzip$2 implements Runnable {
    final /* synthetic */ zzdk zzakg;
    final /* synthetic */ zzfs zzakw;
    final /* synthetic */ zzir zzcee;
    final /* synthetic */ zzdi zzcef;
    final /* synthetic */ String zzceg;

    zzip$2(zzfs com_google_android_gms_internal_zzfs, zzir com_google_android_gms_internal_zzir, zzdk com_google_android_gms_internal_zzdk, zzdi com_google_android_gms_internal_zzdi, String str) {
        this.zzakw = com_google_android_gms_internal_zzfs;
        this.zzcee = com_google_android_gms_internal_zzir;
        this.zzakg = com_google_android_gms_internal_zzdk;
        this.zzcef = com_google_android_gms_internal_zzdi;
        this.zzceg = str;
    }

    public void run() {
        zzfs$zzc zzma = this.zzakw.zzma();
        this.zzcee.zzb(zzma);
        this.zzakg.zza(this.zzcef, new String[]{"rwc"});
        final zzdi zzkg = this.zzakg.zzkg();
        zzma.zza(new zzc<zzft>(this) {
            final /* synthetic */ zzip$2 zzcei;

            public void zzb(zzft com_google_android_gms_internal_zzft) {
                this.zzcei.zzakg.zza(zzkg, new String[]{"jsf"});
                this.zzcei.zzakg.zzkh();
                com_google_android_gms_internal_zzft.zza("/invalidRequest", this.zzcei.zzcee.zzcep);
                com_google_android_gms_internal_zzft.zza("/loadAdURL", this.zzcei.zzcee.zzceq);
                com_google_android_gms_internal_zzft.zza("/loadAd", this.zzcei.zzcee.zzcer);
                try {
                    com_google_android_gms_internal_zzft.zzj("AFMA_getAd", this.zzcei.zzceg);
                } catch (Throwable e) {
                    zzkd.zzb("Error requesting an ad url", e);
                }
            }

            public /* synthetic */ void zzd(Object obj) {
                zzb((zzft) obj);
            }
        }, new zza(this) {
            final /* synthetic */ zzip$2 zzcei;

            {
                this.zzcei = r1;
            }

            public void run() {
            }
        });
    }
}
