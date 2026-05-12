package com.google.android.gms.internal;

import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.mopub.mobileads.VastIconXmlManager;
import java.util.Map;

@zzin
class zzfa implements zzep {
    zzfa() {
    }

    private int zzg(Map<String, String> map) throws NullPointerException, NumberFormatException {
        int parseInt = Integer.parseInt((String) map.get("playbackState"));
        return (parseInt < 0 || 3 < parseInt) ? 0 : parseInt;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        Throwable e;
        if (((Boolean) zzdc.zzbbb.get()).booleanValue()) {
            zzlm com_google_android_gms_internal_zzlm;
            zzlm zzut = com_google_android_gms_internal_zzlh.zzut();
            if (zzut == null) {
                try {
                    zzut = new zzlm(com_google_android_gms_internal_zzlh, Float.parseFloat((String) map.get(VastIconXmlManager.DURATION)));
                    com_google_android_gms_internal_zzlh.zza(zzut);
                    com_google_android_gms_internal_zzlm = zzut;
                } catch (NullPointerException e2) {
                    e = e2;
                    zzb.zzb("Unable to parse videoMeta message.", e);
                    zzu.zzft().zzb(e, true);
                    return;
                } catch (NumberFormatException e3) {
                    e = e3;
                    zzb.zzb("Unable to parse videoMeta message.", e);
                    zzu.zzft().zzb(e, true);
                    return;
                }
            }
            com_google_android_gms_internal_zzlm = zzut;
            com_google_android_gms_internal_zzlm.zza(Float.parseFloat((String) map.get("currentTime")), zzg(map), AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("muted")));
        }
    }
}
