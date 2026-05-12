package com.google.android.gms.internal;

import android.text.TextUtils;
import com.facebook.GraphResponse;
import com.google.android.gms.common.internal.zzab;
import java.util.Map;
import org.json.JSONObject;

class zzii$1 extends zzih$zza {
    final /* synthetic */ zzii$zzb zzbzh;
    final /* synthetic */ zzkv zzbzi;
    final /* synthetic */ String zzbzj;
    final /* synthetic */ zzii zzbzk;

    zzii$1(zzii com_google_android_gms_internal_zzii, zzii$zzb com_google_android_gms_internal_zzii_zzb, zzkv com_google_android_gms_internal_zzkv, String str) {
        this.zzbzk = com_google_android_gms_internal_zzii;
        this.zzbzh = com_google_android_gms_internal_zzii_zzb;
        this.zzbzi = com_google_android_gms_internal_zzkv;
        this.zzbzj = str;
    }

    public void zze(final zzft com_google_android_gms_internal_zzft) {
        zzep anonymousClass1 = new zzep(this) {
            final /* synthetic */ zzii$1 zzbzl;

            public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
                com_google_android_gms_internal_zzft.zzb("/nativeAdPreProcess", this.zzbzl.zzbzh.zzbzz);
                try {
                    String str = (String) map.get(GraphResponse.SUCCESS_KEY);
                    if (!TextUtils.isEmpty(str)) {
                        this.zzbzl.zzbzi.zzh(new JSONObject(str).getJSONArray("ads").getJSONObject(0));
                        return;
                    }
                } catch (Throwable e) {
                    zzkd.zzb("Malformed native JSON response.", e);
                }
                this.zzbzl.zzbzk.zzan(0);
                zzab.zza(this.zzbzl.zzbzk.zzqs(), (Object) "Unable to set the ad state error!");
                this.zzbzl.zzbzi.zzh(null);
            }
        };
        this.zzbzh.zzbzz = anonymousClass1;
        com_google_android_gms_internal_zzft.zza("/nativeAdPreProcess", anonymousClass1);
        try {
            JSONObject jSONObject = new JSONObject(zzii.zza(this.zzbzk).zzciq.body);
            jSONObject.put("ads_id", this.zzbzj);
            com_google_android_gms_internal_zzft.zza("google.afma.nativeAds.preProcessJsonGmsg", jSONObject);
        } catch (Throwable e) {
            zzkd.zzd("Exception occurred while invoking javascript", e);
            this.zzbzi.zzh(null);
        }
    }

    public void zzqq() {
        this.zzbzi.zzh(null);
    }
}
