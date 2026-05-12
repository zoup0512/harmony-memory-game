package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzkd;
import com.google.android.gms.internal.zzlh;
import java.util.Map;

class zzc$1 implements zzep {
    final /* synthetic */ zzc zzakd;

    zzc$1(zzc com_google_android_gms_ads_internal_zzc) {
        this.zzakd = com_google_android_gms_ads_internal_zzc;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        if (this.zzakd.zzajs.zzapb != null) {
            this.zzakd.zzaju.zza(this.zzakd.zzajs.zzapa, this.zzakd.zzajs.zzapb, com_google_android_gms_internal_zzlh.getView(), com_google_android_gms_internal_zzlh);
        } else {
            zzkd.zzcx("Request to enable ActiveView before adState is available.");
        }
    }
}
