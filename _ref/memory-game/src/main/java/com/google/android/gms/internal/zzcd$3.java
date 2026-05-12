package com.google.android.gms.internal;

import java.util.Map;

class zzcd$3 implements zzep {
    final /* synthetic */ zzcd zzara;

    zzcd$3(zzcd com_google_android_gms_internal_zzcd) {
        this.zzara = com_google_android_gms_internal_zzcd;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        if (this.zzara.zzb(map)) {
            String str = "Received request to untrack: ";
            String valueOf = String.valueOf(this.zzara.zzaqk.zzhn());
            zzkd.zzcv(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzara.destroy();
        }
    }
}
