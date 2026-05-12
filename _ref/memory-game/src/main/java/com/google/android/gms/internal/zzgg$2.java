package com.google.android.gms.internal;

class zzgg$2 implements Runnable {
    final /* synthetic */ zzgg zzbpb;
    final /* synthetic */ zzky zzbpc;

    zzgg$2(zzgg com_google_android_gms_internal_zzgg, zzky com_google_android_gms_internal_zzky) {
        this.zzbpb = com_google_android_gms_internal_zzgg;
        this.zzbpc = com_google_android_gms_internal_zzky;
    }

    public void run() {
        for (zzky com_google_android_gms_internal_zzky : zzgg.zze(this.zzbpb).keySet()) {
            if (com_google_android_gms_internal_zzky != this.zzbpc) {
                ((zzgd) zzgg.zze(this.zzbpb).get(com_google_android_gms_internal_zzky)).cancel();
            }
        }
    }
}
