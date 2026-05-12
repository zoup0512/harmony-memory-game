package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import java.util.HashMap;
import java.util.Map;

public final class zzlz extends zzg<zzlz> {
    public int zzbrf;
    public int zzbrg;
    private String zzcuy;
    public int zzcuz;
    public int zzcva;
    public int zzcvb;

    public String getLanguage() {
        return this.zzcuy;
    }

    public void setLanguage(String str) {
        this.zzcuy = str;
    }

    public String toString() {
        Map hashMap = new HashMap();
        hashMap.put("language", this.zzcuy);
        hashMap.put("screenColors", Integer.valueOf(this.zzcuz));
        hashMap.put("screenWidth", Integer.valueOf(this.zzbrf));
        hashMap.put("screenHeight", Integer.valueOf(this.zzbrg));
        hashMap.put("viewportWidth", Integer.valueOf(this.zzcva));
        hashMap.put("viewportHeight", Integer.valueOf(this.zzcvb));
        return zzg.zzj(hashMap);
    }

    public void zza(zzlz com_google_android_gms_internal_zzlz) {
        if (this.zzcuz != 0) {
            com_google_android_gms_internal_zzlz.zzbp(this.zzcuz);
        }
        if (this.zzbrf != 0) {
            com_google_android_gms_internal_zzlz.zzbq(this.zzbrf);
        }
        if (this.zzbrg != 0) {
            com_google_android_gms_internal_zzlz.zzbr(this.zzbrg);
        }
        if (this.zzcva != 0) {
            com_google_android_gms_internal_zzlz.zzbs(this.zzcva);
        }
        if (this.zzcvb != 0) {
            com_google_android_gms_internal_zzlz.zzbt(this.zzcvb);
        }
        if (!TextUtils.isEmpty(this.zzcuy)) {
            com_google_android_gms_internal_zzlz.setLanguage(this.zzcuy);
        }
    }

    public /* synthetic */ void zzb(zzg com_google_android_gms_analytics_zzg) {
        zza((zzlz) com_google_android_gms_analytics_zzg);
    }

    public void zzbp(int i) {
        this.zzcuz = i;
    }

    public void zzbq(int i) {
        this.zzbrf = i;
    }

    public void zzbr(int i) {
        this.zzbrg = i;
    }

    public void zzbs(int i) {
        this.zzcva = i;
    }

    public void zzbt(int i) {
        this.zzcvb = i;
    }

    public int zzxn() {
        return this.zzcuz;
    }

    public int zzxo() {
        return this.zzbrf;
    }

    public int zzxp() {
        return this.zzbrg;
    }

    public int zzxq() {
        return this.zzcva;
    }

    public int zzxr() {
        return this.zzcvb;
    }
}
