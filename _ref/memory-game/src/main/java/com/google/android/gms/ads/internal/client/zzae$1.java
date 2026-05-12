package com.google.android.gms.ads.internal.client;

class zzae$1 extends zzo {
    final /* synthetic */ zzae zzawo;

    zzae$1(zzae com_google_android_gms_ads_internal_client_zzae) {
        this.zzawo = com_google_android_gms_ads_internal_client_zzae;
    }

    public void onAdFailedToLoad(int i) {
        zzae.zza(this.zzawo).zza(this.zzawo.zzjk());
        super.onAdFailedToLoad(i);
    }

    public void onAdLoaded() {
        zzae.zza(this.zzawo).zza(this.zzawo.zzjk());
        super.onAdLoaded();
    }
}
