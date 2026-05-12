package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.common.internal.zzab;
import java.util.HashMap;
import java.util.Map;

public final class zzmd extends zzg<zzmd> {
    private String zzcvh;
    private String zzcvi;
    private String zzcvj;
    private String zzcvk;
    private boolean zzcvl;
    private String zzcvm;
    private boolean zzcvn;
    private double zzcvo;

    public String getUserId() {
        return this.zzcvj;
    }

    public void setClientId(String str) {
        this.zzcvi = str;
    }

    public void setSampleRate(double d) {
        boolean z = d >= 0.0d && d <= 100.0d;
        zzab.zzb(z, (Object) "Sample rate must be between 0% and 100%");
        this.zzcvo = d;
    }

    public void setUserId(String str) {
        this.zzcvj = str;
    }

    public String toString() {
        Map hashMap = new HashMap();
        hashMap.put("hitType", this.zzcvh);
        hashMap.put("clientId", this.zzcvi);
        hashMap.put("userId", this.zzcvj);
        hashMap.put("androidAdId", this.zzcvk);
        hashMap.put("AdTargetingEnabled", Boolean.valueOf(this.zzcvl));
        hashMap.put("sessionControl", this.zzcvm);
        hashMap.put("nonInteraction", Boolean.valueOf(this.zzcvn));
        hashMap.put("sampleRate", Double.valueOf(this.zzcvo));
        return zzg.zzj(hashMap);
    }

    public void zza(zzmd com_google_android_gms_internal_zzmd) {
        if (!TextUtils.isEmpty(this.zzcvh)) {
            com_google_android_gms_internal_zzmd.zzdw(this.zzcvh);
        }
        if (!TextUtils.isEmpty(this.zzcvi)) {
            com_google_android_gms_internal_zzmd.setClientId(this.zzcvi);
        }
        if (!TextUtils.isEmpty(this.zzcvj)) {
            com_google_android_gms_internal_zzmd.setUserId(this.zzcvj);
        }
        if (!TextUtils.isEmpty(this.zzcvk)) {
            com_google_android_gms_internal_zzmd.zzdx(this.zzcvk);
        }
        if (this.zzcvl) {
            com_google_android_gms_internal_zzmd.zzao(true);
        }
        if (!TextUtils.isEmpty(this.zzcvm)) {
            com_google_android_gms_internal_zzmd.zzdy(this.zzcvm);
        }
        if (this.zzcvn) {
            com_google_android_gms_internal_zzmd.zzap(this.zzcvn);
        }
        if (this.zzcvo != 0.0d) {
            com_google_android_gms_internal_zzmd.setSampleRate(this.zzcvo);
        }
    }

    public void zzao(boolean z) {
        this.zzcvl = z;
    }

    public void zzap(boolean z) {
        this.zzcvn = z;
    }

    public /* synthetic */ void zzb(zzg com_google_android_gms_analytics_zzg) {
        zza((zzmd) com_google_android_gms_analytics_zzg);
    }

    public void zzdw(String str) {
        this.zzcvh = str;
    }

    public void zzdx(String str) {
        this.zzcvk = str;
    }

    public void zzdy(String str) {
        this.zzcvm = str;
    }

    public String zzwb() {
        return this.zzcvi;
    }

    public String zzxx() {
        return this.zzcvh;
    }

    public String zzxy() {
        return this.zzcvk;
    }

    public boolean zzxz() {
        return this.zzcvl;
    }

    public String zzya() {
        return this.zzcvm;
    }

    public boolean zzyb() {
        return this.zzcvn;
    }

    public double zzyc() {
        return this.zzcvo;
    }
}
