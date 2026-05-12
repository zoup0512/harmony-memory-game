package com.google.android.gms.internal;

import com.google.android.gms.internal.zzm.zza;
import com.google.android.gms.internal.zzm.zzb;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

class zzkn$zzb<T> extends zzk<InputStream> {
    private final zzb<T> zzcg;
    private final zzkn$zza<T> zzcmj;

    class AnonymousClass1 implements zza {
        final /* synthetic */ zzb zzcmk;
        final /* synthetic */ zzkn$zza zzcml;

        AnonymousClass1(zzb com_google_android_gms_internal_zzm_zzb, zzkn$zza com_google_android_gms_internal_zzkn_zza) {
            this.zzcmk = com_google_android_gms_internal_zzm_zzb;
            this.zzcml = com_google_android_gms_internal_zzkn_zza;
        }

        public void zze(zzr com_google_android_gms_internal_zzr) {
            this.zzcmk.zzb(this.zzcml.zzqu());
        }
    }

    public zzkn$zzb(String str, zzkn$zza<T> com_google_android_gms_internal_zzkn_zza_T, zzb<T> com_google_android_gms_internal_zzm_zzb_T) {
        super(0, str, new AnonymousClass1(com_google_android_gms_internal_zzm_zzb_T, com_google_android_gms_internal_zzkn_zza_T));
        this.zzcmj = com_google_android_gms_internal_zzkn_zza_T;
        this.zzcg = com_google_android_gms_internal_zzm_zzb_T;
    }

    protected zzm<InputStream> zza(zzi com_google_android_gms_internal_zzi) {
        return zzm.zza(new ByteArrayInputStream(com_google_android_gms_internal_zzi.data), zzx.zzb(com_google_android_gms_internal_zzi));
    }

    protected /* synthetic */ void zza(Object obj) {
        zzj((InputStream) obj);
    }

    protected void zzj(InputStream inputStream) {
        this.zzcg.zzb(this.zzcmj.zzh(inputStream));
    }
}
