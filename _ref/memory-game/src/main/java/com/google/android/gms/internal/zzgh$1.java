package com.google.android.gms.internal;

class zzgh$1 implements Runnable {
    final /* synthetic */ zzge zzbpe;
    final /* synthetic */ zzgh zzbpf;

    zzgh$1(zzgh com_google_android_gms_internal_zzgh, zzge com_google_android_gms_internal_zzge) {
        this.zzbpf = com_google_android_gms_internal_zzgh;
        this.zzbpe = com_google_android_gms_internal_zzge;
    }

    public void run() {
        try {
            this.zzbpe.zzboo.destroy();
        } catch (Throwable e) {
            zzkd.zzd("Could not destroy mediation adapter.", e);
        }
    }
}
