package com.google.android.gms.internal;

class zzii$2 implements Runnable {
    final /* synthetic */ zzii zzbzk;
    final /* synthetic */ zzkv zzbzm;
    final /* synthetic */ String zzbzn;

    zzii$2(zzii com_google_android_gms_internal_zzii, zzkv com_google_android_gms_internal_zzkv, String str) {
        this.zzbzk = com_google_android_gms_internal_zzii;
        this.zzbzm = com_google_android_gms_internal_zzkv;
        this.zzbzn = str;
    }

    public void run() {
        this.zzbzm.zzh((zzee) zzii.zzb(this.zzbzk).zzfb().get(this.zzbzn));
    }
}
