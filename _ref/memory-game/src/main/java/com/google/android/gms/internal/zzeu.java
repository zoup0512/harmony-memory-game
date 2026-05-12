package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONObject;

@zzin
public class zzeu implements zzep {
    final HashMap<String, zzkv<JSONObject>> zzbis = new HashMap();

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        zzi((String) map.get("request_id"), (String) map.get("fetched_ad"));
    }

    public Future<JSONObject> zzaw(String str) {
        Future com_google_android_gms_internal_zzkv = new zzkv();
        this.zzbis.put(str, com_google_android_gms_internal_zzkv);
        return com_google_android_gms_internal_zzkv;
    }

    public void zzax(String str) {
        zzkv com_google_android_gms_internal_zzkv = (zzkv) this.zzbis.get(str);
        if (com_google_android_gms_internal_zzkv == null) {
            zzb.e("Could not find the ad request for the corresponding ad response.");
            return;
        }
        if (!com_google_android_gms_internal_zzkv.isDone()) {
            com_google_android_gms_internal_zzkv.cancel(true);
        }
        this.zzbis.remove(str);
    }

    public void zzi(String str, String str2) {
        zzb.zzcv("Received ad from the cache.");
        zzkv com_google_android_gms_internal_zzkv = (zzkv) this.zzbis.get(str);
        if (com_google_android_gms_internal_zzkv == null) {
            zzb.e("Could not find the ad request for the corresponding ad response.");
            return;
        }
        try {
            com_google_android_gms_internal_zzkv.zzh(new JSONObject(str2));
        } catch (Throwable e) {
            zzb.zzb("Failed constructing JSON object from value passed from javascript", e);
            com_google_android_gms_internal_zzkv.zzh(null);
        } finally {
            this.zzbis.remove(str);
        }
    }
}
