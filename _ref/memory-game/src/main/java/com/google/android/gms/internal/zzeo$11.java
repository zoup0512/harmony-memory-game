package com.google.android.gms.internal;

import java.util.Map;
import java.util.concurrent.Future;

class zzeo$11 implements zzep {
    zzeo$11() {
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        String str = (String) map.get("u");
        if (str == null) {
            zzkd.zzcx("URL missing from httpTrack GMSG.");
        } else {
            Future future = (Future) new zzkq(com_google_android_gms_internal_zzlh.getContext(), com_google_android_gms_internal_zzlh.zzum().zzcs, str).zzpy();
        }
    }
}
