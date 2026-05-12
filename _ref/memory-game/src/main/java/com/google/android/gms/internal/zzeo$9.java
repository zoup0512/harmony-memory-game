package com.google.android.gms.internal;

import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.overlay.zzm;
import com.google.android.gms.ads.internal.zzd;
import java.util.Map;
import org.json.JSONObject;

class zzeo$9 implements zzep {
    zzeo$9() {
    }

    private void zzc(zzlh com_google_android_gms_internal_zzlh) {
        zzkd.zzcw("Received support message, responding.");
        boolean z = false;
        zzd zzug = com_google_android_gms_internal_zzlh.zzug();
        if (zzug != null) {
            zzm com_google_android_gms_ads_internal_overlay_zzm = zzug.zzakl;
            if (com_google_android_gms_ads_internal_overlay_zzm != null) {
                z = com_google_android_gms_ads_internal_overlay_zzm.zzr(com_google_android_gms_internal_zzlh.getContext());
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("event", "checkSupport");
            jSONObject.put("supports", z);
            com_google_android_gms_internal_zzlh.zzb("appStreaming", jSONObject);
        } catch (Throwable th) {
        }
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        if ("checkSupport".equals(map.get(NativeProtocol.WEB_DIALOG_ACTION))) {
            zzc(com_google_android_gms_internal_zzlh);
            return;
        }
        com.google.android.gms.ads.internal.overlay.zzd zzuh = com_google_android_gms_internal_zzlh.zzuh();
        if (zzuh != null) {
            zzuh.zzf(com_google_android_gms_internal_zzlh, map);
        }
    }
}
