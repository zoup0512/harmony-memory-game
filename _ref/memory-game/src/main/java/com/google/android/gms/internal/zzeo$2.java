package com.google.android.gms.internal;

import java.util.Map;

class zzeo$2 implements zzep {
    zzeo$2() {
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        String str = (String) map.get("ty");
        String str2 = (String) map.get("td");
        try {
            int parseInt = Integer.parseInt((String) map.get("tx"));
            int parseInt2 = Integer.parseInt(str);
            int parseInt3 = Integer.parseInt(str2);
            zzas zzul = com_google_android_gms_internal_zzlh.zzul();
            if (zzul != null) {
                zzul.zzaw().zza(parseInt, parseInt2, parseInt3);
            }
        } catch (NumberFormatException e) {
            zzkd.zzcx("Could not parse touch parameters from gmsg.");
        }
    }
}
