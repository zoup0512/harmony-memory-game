package com.google.android.gms.internal;

import android.os.Handler;
import com.google.android.gms.ads.internal.zzl;
import java.util.LinkedList;
import java.util.List;

@zzin
class zzfi {
    private final List<zza> zzalc = new LinkedList();

    zzfi() {
    }

    void zza(zzfj com_google_android_gms_internal_zzfj) {
        Handler handler = zzkh.zzclc;
        for (zza 7 : this.zzalc) {
            handler.post(new 7(this, 7, com_google_android_gms_internal_zzfj));
        }
    }

    void zzc(zzl com_google_android_gms_ads_internal_zzl) {
        com_google_android_gms_ads_internal_zzl.zza(new 1(this));
        com_google_android_gms_ads_internal_zzl.zza(new 2(this));
        com_google_android_gms_ads_internal_zzl.zza(new 3(this));
        com_google_android_gms_ads_internal_zzl.zza(new 4(this));
        com_google_android_gms_ads_internal_zzl.zza(new 5(this));
        com_google_android_gms_ads_internal_zzl.zza(new 6(this));
    }
}
