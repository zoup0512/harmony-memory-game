package com.google.android.gms.internal;

import android.net.Uri;
import java.util.Map;
import java.util.concurrent.Future;

class zzeo$7 implements zzep {
    zzeo$7() {
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        String str = (String) map.get("u");
        if (str == null) {
            zzkd.zzcx("URL missing from click GMSG.");
            return;
        }
        Uri zzb;
        Future future;
        Uri parse = Uri.parse(str);
        try {
            zzas zzul = com_google_android_gms_internal_zzlh.zzul();
            if (zzul != null && zzul.zzc(parse)) {
                zzb = zzul.zzb(parse, com_google_android_gms_internal_zzlh.getContext());
                future = (Future) new zzkq(com_google_android_gms_internal_zzlh.getContext(), com_google_android_gms_internal_zzlh.zzum().zzcs, zzb.toString()).zzpy();
            }
        } catch (zzat e) {
            String str2 = "Unable to append parameter to URL: ";
            str = String.valueOf(str);
            zzkd.zzcx(str.length() != 0 ? str2.concat(str) : new String(str2));
        }
        zzb = parse;
        future = (Future) new zzkq(com_google_android_gms_internal_zzlh.getContext(), com_google_android_gms_internal_zzlh.zzum().zzcs, zzb.toString()).zzpy();
    }
}
