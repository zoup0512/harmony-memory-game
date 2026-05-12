package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

class zzfd$3 implements Runnable {
    final /* synthetic */ String zzbjh;
    final /* synthetic */ String zzbji;
    final /* synthetic */ zzfd zzbjm;
    final /* synthetic */ String zzbjn;
    final /* synthetic */ String zzbjo;

    zzfd$3(zzfd com_google_android_gms_internal_zzfd, String str, String str2, String str3, String str4) {
        this.zzbjm = com_google_android_gms_internal_zzfd;
        this.zzbjh = str;
        this.zzbji = str2;
        this.zzbjn = str3;
        this.zzbjo = str4;
    }

    public void run() {
        Map hashMap = new HashMap();
        hashMap.put("event", "precacheCanceled");
        hashMap.put("src", this.zzbjh);
        if (!TextUtils.isEmpty(this.zzbji)) {
            hashMap.put("cachedSrc", this.zzbji);
        }
        hashMap.put("type", zzfd.zza(this.zzbjm, this.zzbjn));
        hashMap.put("reason", this.zzbjn);
        if (!TextUtils.isEmpty(this.zzbjo)) {
            hashMap.put("message", this.zzbjo);
        }
        zzfd.zza(this.zzbjm, "onPrecacheEvent", hashMap);
    }
}
