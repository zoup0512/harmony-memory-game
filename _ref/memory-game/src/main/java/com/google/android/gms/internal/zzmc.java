package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import java.util.HashMap;
import java.util.Map;

public final class zzmc extends zzg<zzmc> {
    public String zzcvf;
    public boolean zzcvg;

    public String getDescription() {
        return this.zzcvf;
    }

    public void setDescription(String str) {
        this.zzcvf = str;
    }

    public String toString() {
        Map hashMap = new HashMap();
        hashMap.put("description", this.zzcvf);
        hashMap.put("fatal", Boolean.valueOf(this.zzcvg));
        return zzg.zzj(hashMap);
    }

    public void zza(zzmc com_google_android_gms_internal_zzmc) {
        if (!TextUtils.isEmpty(this.zzcvf)) {
            com_google_android_gms_internal_zzmc.setDescription(this.zzcvf);
        }
        if (this.zzcvg) {
            com_google_android_gms_internal_zzmc.zzan(this.zzcvg);
        }
    }

    public void zzan(boolean z) {
        this.zzcvg = z;
    }

    public /* synthetic */ void zzb(zzg com_google_android_gms_analytics_zzg) {
        zza((zzmc) com_google_android_gms_analytics_zzg);
    }

    public boolean zzxw() {
        return this.zzcvg;
    }
}
