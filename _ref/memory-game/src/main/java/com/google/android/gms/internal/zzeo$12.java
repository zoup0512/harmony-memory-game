package com.google.android.gms.internal;

import java.util.Map;

class zzeo$12 implements zzep {
    zzeo$12() {
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        String str = "Received log message: ";
        String valueOf = String.valueOf((String) map.get("string"));
        zzkd.zzcw(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
