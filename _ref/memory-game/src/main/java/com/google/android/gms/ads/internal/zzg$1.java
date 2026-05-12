package com.google.android.gms.ads.internal;

import com.facebook.internal.ServerProtocol;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzlh;
import java.util.Map;

class zzg$1 implements zzep {
    final /* synthetic */ zzg zzakv;

    zzg$1(zzg com_google_android_gms_ads_internal_zzg) {
        this.zzakv = com_google_android_gms_ads_internal_zzg;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        com_google_android_gms_internal_zzlh.zzb("/appSettingsFetched", this);
        synchronized (zzg.zza(this.zzakv)) {
            if (map != null) {
                if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase((String) map.get("isSuccessful"))) {
                    zzu.zzft().zzd(zzg.zzb(this.zzakv), (String) map.get("appSettingsJson"));
                }
            }
        }
    }
}
