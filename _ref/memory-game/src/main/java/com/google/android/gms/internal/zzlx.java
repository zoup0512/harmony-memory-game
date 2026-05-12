package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzg;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class zzlx extends zzg<zzlx> {
    private Map<Integer, Double> zzcux = new HashMap(4);

    public String toString() {
        Map hashMap = new HashMap();
        for (Entry entry : this.zzcux.entrySet()) {
            String valueOf = String.valueOf(entry.getKey());
            hashMap.put(new StringBuilder(String.valueOf(valueOf).length() + 6).append("metric").append(valueOf).toString(), entry.getValue());
        }
        return zzg.zzj(hashMap);
    }

    public void zza(zzlx com_google_android_gms_internal_zzlx) {
        com_google_android_gms_internal_zzlx.zzcux.putAll(this.zzcux);
    }

    public /* synthetic */ void zzb(zzg com_google_android_gms_analytics_zzg) {
        zza((zzlx) com_google_android_gms_analytics_zzg);
    }

    public Map<Integer, Double> zzxl() {
        return Collections.unmodifiableMap(this.zzcux);
    }
}
