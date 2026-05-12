package com.google.android.gms.ads.internal;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.applinks.AppLinkData;
import com.google.android.gms.ads.internal.formats.zze;
import com.google.android.gms.internal.zzkd;
import com.google.android.gms.internal.zzlh;
import com.google.android.gms.internal.zzli$zza;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class zzn$2 implements zzli$zza {
    final /* synthetic */ String zzaml;
    final /* synthetic */ zzlh zzamm;
    final /* synthetic */ zze zzamn;

    zzn$2(zze com_google_android_gms_ads_internal_formats_zze, String str, zzlh com_google_android_gms_internal_zzlh) {
        this.zzamn = com_google_android_gms_ads_internal_formats_zze;
        this.zzaml = str;
        this.zzamm = com_google_android_gms_internal_zzlh;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", this.zzamn.getHeadline());
            jSONObject.put("body", this.zzamn.getBody());
            jSONObject.put("call_to_action", this.zzamn.getCallToAction());
            jSONObject.put("advertiser", this.zzamn.getAdvertiser());
            jSONObject.put("logo", zzn.zza(this.zzamn.zzky()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = this.zzamn.getImages();
            if (images != null) {
                for (Object zzf : images) {
                    jSONArray.put(zzn.zza(zzn.zzf(zzf)));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put(AppLinkData.ARGUMENTS_EXTRAS_KEY, zzn.zzb(this.zzamn.getExtras(), this.zzaml));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            this.zzamm.zza("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (Throwable e) {
            zzkd.zzd("Exception occurred when loading assets", e);
        }
    }
}
