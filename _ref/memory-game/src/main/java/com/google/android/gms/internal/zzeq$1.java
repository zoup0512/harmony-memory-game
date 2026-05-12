package com.google.android.gms.internal;

import java.util.Map;
import org.json.JSONObject;

class zzeq$1 implements Runnable {
    final /* synthetic */ Map zzbgn;
    final /* synthetic */ zzlh zzbie;
    final /* synthetic */ zzeq zzbif;

    zzeq$1(zzeq com_google_android_gms_internal_zzeq, Map map, zzlh com_google_android_gms_internal_zzlh) {
        this.zzbif = com_google_android_gms_internal_zzeq;
        this.zzbgn = map;
        this.zzbie = com_google_android_gms_internal_zzlh;
    }

    public void run() {
        zzkd.zzcv("Received Http request.");
        final JSONObject zzav = this.zzbif.zzav((String) this.zzbgn.get("http_request"));
        if (zzav == null) {
            zzkd.e("Response should not be null.");
        } else {
            zzkh.zzclc.post(new Runnable(this) {
                final /* synthetic */ zzeq$1 zzbih;

                public void run() {
                    this.zzbih.zzbie.zzb("fetchHttpRequestCompleted", zzav);
                    zzkd.zzcv("Dispatched http response.");
                }
            });
        }
    }
}
