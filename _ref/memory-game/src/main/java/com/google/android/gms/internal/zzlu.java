package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import java.util.HashMap;
import java.util.Map;

public final class zzlu extends zzg<zzlu> {
    private String zzcjf;
    private String zzcum;
    private String zzcun;
    private String zzcuo;

    public void setAppId(String str) {
        this.zzcjf = str;
    }

    public void setAppInstallerId(String str) {
        this.zzcuo = str;
    }

    public void setAppName(String str) {
        this.zzcum = str;
    }

    public void setAppVersion(String str) {
        this.zzcun = str;
    }

    public String toString() {
        Map hashMap = new HashMap();
        hashMap.put("appName", this.zzcum);
        hashMap.put("appVersion", this.zzcun);
        hashMap.put("appId", this.zzcjf);
        hashMap.put("appInstallerId", this.zzcuo);
        return zzg.zzj(hashMap);
    }

    public void zza(zzlu com_google_android_gms_internal_zzlu) {
        if (!TextUtils.isEmpty(this.zzcum)) {
            com_google_android_gms_internal_zzlu.setAppName(this.zzcum);
        }
        if (!TextUtils.isEmpty(this.zzcun)) {
            com_google_android_gms_internal_zzlu.setAppVersion(this.zzcun);
        }
        if (!TextUtils.isEmpty(this.zzcjf)) {
            com_google_android_gms_internal_zzlu.setAppId(this.zzcjf);
        }
        if (!TextUtils.isEmpty(this.zzcuo)) {
            com_google_android_gms_internal_zzlu.setAppInstallerId(this.zzcuo);
        }
    }

    public /* synthetic */ void zzb(zzg com_google_android_gms_analytics_zzg) {
        zza((zzlu) com_google_android_gms_analytics_zzg);
    }

    public String zzsh() {
        return this.zzcjf;
    }

    public String zzxb() {
        return this.zzcum;
    }

    public String zzxc() {
        return this.zzcun;
    }

    public String zzxd() {
        return this.zzcuo;
    }
}
