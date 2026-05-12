package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.zzg;
import com.google.android.gms.ads.internal.util.client.zzb;

class zzgy$2 implements zzg {
    final /* synthetic */ zzgy zzbpx;

    zzgy$2(zzgy com_google_android_gms_internal_zzgy) {
        this.zzbpx = com_google_android_gms_internal_zzgy;
    }

    public void onPause() {
        zzb.zzcv("AdMobCustomTabsAdapter overlay is paused.");
    }

    public void onResume() {
        zzb.zzcv("AdMobCustomTabsAdapter overlay is resumed.");
    }

    public void zzdx() {
        zzb.zzcv("AdMobCustomTabsAdapter overlay is closed.");
        zzgy.zza(this.zzbpx).onAdClosed(this.zzbpx);
        zzgy.zzc(this.zzbpx).zzd(zzgy.zzb(this.zzbpx));
    }

    public void zzdy() {
        zzb.zzcv("Opening AdMobCustomTabsAdapter overlay.");
        zzgy.zza(this.zzbpx).onAdOpened(this.zzbpx);
    }
}
