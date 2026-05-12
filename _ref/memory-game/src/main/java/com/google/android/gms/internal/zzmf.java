package com.google.android.gms.internal;

import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.analytics.zzg;
import java.util.HashMap;
import java.util.Map;

public final class zzmf extends zzg<zzmf> {
    public String zzcvc;
    public String zzcvw;
    public String zzcvx;

    public String getAction() {
        return this.zzcvc;
    }

    public String getTarget() {
        return this.zzcvx;
    }

    public String toString() {
        Map hashMap = new HashMap();
        hashMap.put("network", this.zzcvw);
        hashMap.put(NativeProtocol.WEB_DIALOG_ACTION, this.zzcvc);
        hashMap.put("target", this.zzcvx);
        return zzg.zzj(hashMap);
    }

    public void zza(zzmf com_google_android_gms_internal_zzmf) {
        if (!TextUtils.isEmpty(this.zzcvw)) {
            com_google_android_gms_internal_zzmf.zzeb(this.zzcvw);
        }
        if (!TextUtils.isEmpty(this.zzcvc)) {
            com_google_android_gms_internal_zzmf.zzdu(this.zzcvc);
        }
        if (!TextUtils.isEmpty(this.zzcvx)) {
            com_google_android_gms_internal_zzmf.zzec(this.zzcvx);
        }
    }

    public /* synthetic */ void zzb(zzg com_google_android_gms_analytics_zzg) {
        zza((zzmf) com_google_android_gms_analytics_zzg);
    }

    public void zzdu(String str) {
        this.zzcvc = str;
    }

    public void zzeb(String str) {
        this.zzcvw = str;
    }

    public void zzec(String str) {
        this.zzcvx = str;
    }

    public String zzyi() {
        return this.zzcvw;
    }
}
