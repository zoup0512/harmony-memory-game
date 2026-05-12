package com.google.android.gms.internal;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import java.util.Map;

class zzcd$4 implements zzep {
    final /* synthetic */ zzcd zzara;

    zzcd$4(zzcd com_google_android_gms_internal_zzcd) {
        this.zzara = com_google_android_gms_internal_zzcd;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        if (this.zzara.zzb(map) && map.containsKey("isVisible")) {
            boolean z = AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("isVisible")) || ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(map.get("isVisible"));
            this.zzara.zzj(Boolean.valueOf(z).booleanValue());
        }
    }
}
