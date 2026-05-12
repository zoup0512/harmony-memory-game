package com.google.android.gms.ads.internal;

import android.view.View;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzgn;
import com.google.android.gms.internal.zzgo;
import com.google.android.gms.internal.zzkd;
import com.google.android.gms.internal.zzlh;
import java.util.Map;

class zzn$5 implements zzep {
    final /* synthetic */ zzgn zzamo;
    final /* synthetic */ zzf$zza zzamp;
    final /* synthetic */ zzgo zzamq;

    zzn$5(zzgn com_google_android_gms_internal_zzgn, zzf$zza com_google_android_gms_ads_internal_zzf_zza, zzgo com_google_android_gms_internal_zzgo) {
        this.zzamo = com_google_android_gms_internal_zzgn;
        this.zzamp = com_google_android_gms_ads_internal_zzf_zza;
        this.zzamq = com_google_android_gms_internal_zzgo;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        View view = com_google_android_gms_internal_zzlh.getView();
        if (view != null) {
            try {
                if (this.zzamo != null) {
                    if (this.zzamo.getOverrideClickHandling()) {
                        zzn.zzb(com_google_android_gms_internal_zzlh);
                        return;
                    }
                    this.zzamo.zzk(zze.zzac(view));
                    this.zzamp.onClick();
                } else if (this.zzamq == null) {
                } else {
                    if (this.zzamq.getOverrideClickHandling()) {
                        zzn.zzb(com_google_android_gms_internal_zzlh);
                        return;
                    }
                    this.zzamq.zzk(zze.zzac(view));
                    this.zzamp.onClick();
                }
            } catch (Throwable e) {
                zzkd.zzd("Unable to call handleClick on mapper", e);
            }
        }
    }
}
