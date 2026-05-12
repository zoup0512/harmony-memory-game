package com.google.android.gms.internal;

import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

@zzin
public class zzes implements zzep {
    private final zzet zzbir;

    public zzes(zzet com_google_android_gms_internal_zzet) {
        this.zzbir = com_google_android_gms_internal_zzet;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        float parseFloat;
        boolean equals = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("transparentBackground"));
        boolean equals2 = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("blur"));
        try {
            if (map.get("blurRadius") != null) {
                parseFloat = Float.parseFloat((String) map.get("blurRadius"));
                this.zzbir.zzg(equals);
                this.zzbir.zza(equals2, parseFloat);
            }
        } catch (Throwable e) {
            zzb.zzb("Fail to parse float", e);
        }
        parseFloat = 0.0f;
        this.zzbir.zzg(equals);
        this.zzbir.zza(equals2, parseFloat);
    }
}
