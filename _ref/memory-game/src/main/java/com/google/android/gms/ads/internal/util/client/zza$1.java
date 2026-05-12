package com.google.android.gms.ads.internal.util.client;

class zza$1 implements zza$zza {
    final /* synthetic */ zza zzcni;

    zza$1(zza com_google_android_gms_ads_internal_util_client_zza) {
        this.zzcni = com_google_android_gms_ads_internal_util_client_zza;
    }

    public void zzcr(final String str) {
        new Thread(this) {
            final /* synthetic */ zza$1 zzcnj;

            public void run() {
                new zzc().zzcr(str);
            }
        }.start();
    }
}
