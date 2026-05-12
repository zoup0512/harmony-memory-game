package com.google.android.gms.internal;

import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

class zzfd$1 implements Runnable {
    final /* synthetic */ String zzbjh;
    final /* synthetic */ String zzbji;
    final /* synthetic */ int zzbjj;
    final /* synthetic */ int zzbjk;
    final /* synthetic */ boolean zzbjl;
    final /* synthetic */ zzfd zzbjm;

    zzfd$1(zzfd com_google_android_gms_internal_zzfd, String str, String str2, int i, int i2, boolean z) {
        this.zzbjm = com_google_android_gms_internal_zzfd;
        this.zzbjh = str;
        this.zzbji = str2;
        this.zzbjj = i;
        this.zzbjk = i2;
        this.zzbjl = z;
    }

    public void run() {
        Map hashMap = new HashMap();
        hashMap.put("event", "precacheProgress");
        hashMap.put("src", this.zzbjh);
        hashMap.put("cachedSrc", this.zzbji);
        hashMap.put("bytesLoaded", Integer.toString(this.zzbjj));
        hashMap.put("totalBytes", Integer.toString(this.zzbjk));
        hashMap.put("cacheReady", this.zzbjl ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        zzfd.zza(this.zzbjm, "onPrecacheEvent", hashMap);
    }
}
