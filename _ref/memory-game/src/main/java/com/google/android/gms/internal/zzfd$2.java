package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

class zzfd$2 implements Runnable {
    final /* synthetic */ String zzbjh;
    final /* synthetic */ String zzbji;
    final /* synthetic */ int zzbjk;
    final /* synthetic */ zzfd zzbjm;

    zzfd$2(zzfd com_google_android_gms_internal_zzfd, String str, String str2, int i) {
        this.zzbjm = com_google_android_gms_internal_zzfd;
        this.zzbjh = str;
        this.zzbji = str2;
        this.zzbjk = i;
    }

    public void run() {
        Map hashMap = new HashMap();
        hashMap.put("event", "precacheComplete");
        hashMap.put("src", this.zzbjh);
        hashMap.put("cachedSrc", this.zzbji);
        hashMap.put("totalBytes", Integer.toString(this.zzbjk));
        zzfd.zza(this.zzbjm, "onPrecacheEvent", hashMap);
    }
}
