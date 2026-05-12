package com.google.android.gms.internal;

class zzlm$2 implements Runnable {
    final /* synthetic */ zzlm zzcqf;
    final /* synthetic */ int zzcqg;
    final /* synthetic */ int zzcqh;

    zzlm$2(zzlm com_google_android_gms_internal_zzlm, int i, int i2) {
        this.zzcqf = com_google_android_gms_internal_zzlm;
        this.zzcqg = i;
        this.zzcqh = i2;
    }

    public void run() {
        boolean z = false;
        synchronized (zzlm.zzc(this.zzcqf)) {
            boolean z2 = this.zzcqg != this.zzcqh;
            boolean z3 = !zzlm.zzd(this.zzcqf) && this.zzcqh == 1;
            boolean z4 = z2 && this.zzcqh == 1;
            boolean z5 = z2 && this.zzcqh == 2;
            z2 = z2 && this.zzcqh == 3;
            zzlm com_google_android_gms_internal_zzlm = this.zzcqf;
            if (zzlm.zzd(this.zzcqf) || z3) {
                z = true;
            }
            zzlm.zza(com_google_android_gms_internal_zzlm, z);
            if (zzlm.zze(this.zzcqf) == null) {
                return;
            }
            if (z3) {
                try {
                    zzlm.zze(this.zzcqf).zzjb();
                } catch (Throwable e) {
                    zzkd.zzd("Unable to call onVideoStart()", e);
                }
            }
            if (z4) {
                try {
                    zzlm.zze(this.zzcqf).zzjc();
                } catch (Throwable e2) {
                    zzkd.zzd("Unable to call onVideoPlay()", e2);
                }
            }
            if (z5) {
                try {
                    zzlm.zze(this.zzcqf).zzjd();
                } catch (Throwable e22) {
                    zzkd.zzd("Unable to call onVideoPause()", e22);
                }
            }
            if (z2) {
                try {
                    zzlm.zze(this.zzcqf).onVideoEnd();
                } catch (Throwable e222) {
                    zzkd.zzd("Unable to call onVideoEnd()", e222);
                }
            }
        }
    }
}
