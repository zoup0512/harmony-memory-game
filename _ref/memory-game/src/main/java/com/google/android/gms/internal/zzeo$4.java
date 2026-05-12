package com.google.android.gms.internal;

import com.facebook.internal.NativeProtocol;
import java.util.Map;

class zzeo$4 implements zzep {
    zzeo$4() {
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        String str = (String) map.get(NativeProtocol.WEB_DIALOG_ACTION);
        if ("pause".equals(str)) {
            com_google_android_gms_internal_zzlh.zzef();
        } else if ("resume".equals(str)) {
            com_google_android_gms_internal_zzlh.zzeg();
        }
    }
}
