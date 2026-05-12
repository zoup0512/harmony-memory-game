package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

@zzin
public final class zzek implements zzep {
    private final zzel zzbhm;

    public zzek(zzel com_google_android_gms_internal_zzel) {
        this.zzbhm = com_google_android_gms_internal_zzel;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        String str = (String) map.get("name");
        if (str == null) {
            zzb.zzcx("App event with no name parameter.");
        } else {
            this.zzbhm.onAppEvent(str, (String) map.get("info"));
        }
    }
}
