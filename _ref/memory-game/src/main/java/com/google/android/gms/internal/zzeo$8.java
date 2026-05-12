package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.zzd;
import java.util.Map;

class zzeo$8 implements zzep {
    zzeo$8() {
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        zzd zzuh = com_google_android_gms_internal_zzlh.zzuh();
        if (zzuh != null) {
            zzuh.close();
            return;
        }
        zzuh = com_google_android_gms_internal_zzlh.zzui();
        if (zzuh != null) {
            zzuh.close();
        } else {
            zzkd.zzcx("A GMSG tried to close something that wasn't an overlay.");
        }
    }
}
