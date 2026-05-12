package com.google.android.gms.ads.internal.request;

import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzkd;
import com.google.android.gms.internal.zzlh;
import java.util.Map;

public class zzn$zzc implements zzep {
    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        String str = (String) map.get("request_id");
        String str2 = "Invalid request: ";
        String valueOf = String.valueOf((String) map.get("errors"));
        zzkd.zzcx(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        zzn.zzrc().zzax(str);
    }
}
