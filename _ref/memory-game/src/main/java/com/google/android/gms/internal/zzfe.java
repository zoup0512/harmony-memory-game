package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.util.Map;
import java.util.concurrent.Future;

@zzin
public class zzfe implements zzep {
    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        zzfc zzgj = zzu.zzgj();
        if (!map.containsKey("abort")) {
            String str = (String) map.get("src");
            if (str == null) {
                zzb.zzcx("Precache video action is missing the src parameter.");
                return;
            }
            int parseInt;
            try {
                parseInt = Integer.parseInt((String) map.get("player"));
            } catch (NumberFormatException e) {
                parseInt = 0;
            }
            String str2 = map.containsKey("mimetype") ? (String) map.get("mimetype") : "";
            if (zzgj.zze(com_google_android_gms_internal_zzlh)) {
                zzb.zzcx("Precache task already running.");
                return;
            }
            com.google.android.gms.common.internal.zzb.zzu(com_google_android_gms_internal_zzlh.zzug());
            Future future = (Future) new zzfb(com_google_android_gms_internal_zzlh, com_google_android_gms_internal_zzlh.zzug().zzakj.zza(com_google_android_gms_internal_zzlh, parseInt, str2), str).zzpy();
        } else if (!zzgj.zzd(com_google_android_gms_internal_zzlh)) {
            zzb.zzcx("Precache abort but no preload task running.");
        }
    }
}
