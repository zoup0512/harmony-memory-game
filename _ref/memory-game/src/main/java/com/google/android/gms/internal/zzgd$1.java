package com.google.android.gms.internal;

class zzgd$1 implements Runnable {
    final /* synthetic */ zzgc zzboj;
    final /* synthetic */ zzgd zzbok;

    zzgd$1(zzgd com_google_android_gms_internal_zzgd, zzgc com_google_android_gms_internal_zzgc) {
        this.zzbok = com_google_android_gms_internal_zzgd;
        this.zzboj = com_google_android_gms_internal_zzgc;
    }

    public void run() {
        synchronized (zzgd.zza(this.zzbok)) {
            if (zzgd.zzb(this.zzbok) != -2) {
                return;
            }
            zzgd.zza(this.zzbok, zzgd.zzc(this.zzbok));
            if (zzgd.zzd(this.zzbok) == null) {
                this.zzbok.zzy(4);
            } else if (!zzgd.zze(this.zzbok) || zzgd.zza(this.zzbok, 1)) {
                this.zzboj.zza(this.zzbok);
                zzgd.zza(this.zzbok, this.zzboj);
            } else {
                String zzf = zzgd.zzf(this.zzbok);
                zzkd.zzcx(new StringBuilder(String.valueOf(zzf).length() + 56).append("Ignoring adapter ").append(zzf).append(" as delayed impression is not supported").toString());
                this.zzbok.zzy(2);
            }
        }
    }
}
