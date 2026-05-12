package com.google.android.gms.internal;

import org.json.JSONObject;

class zzfr$1 implements Runnable {
    final /* synthetic */ String zzbll;
    final /* synthetic */ JSONObject zzblm;
    final /* synthetic */ zzfr zzbln;

    zzfr$1(zzfr com_google_android_gms_internal_zzfr, String str, JSONObject jSONObject) {
        this.zzbln = com_google_android_gms_internal_zzfr;
        this.zzbll = str;
        this.zzblm = jSONObject;
    }

    public void run() {
        zzfr.zza(this.zzbln).zza(this.zzbll, this.zzblm);
    }
}
