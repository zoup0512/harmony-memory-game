package com.google.android.gms.ads.internal;

import com.facebook.applinks.AppLinkData;
import com.google.android.gms.ads.internal.formats.zzd;
import com.google.android.gms.internal.zzkd;
import com.google.android.gms.internal.zzlh;
import com.google.android.gms.internal.zzli$zza;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.my.target.nativeads.banners.NavigationType;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class zzn$1 implements zzli$zza {
    final /* synthetic */ zzd zzamk;
    final /* synthetic */ String zzaml;
    final /* synthetic */ zzlh zzamm;

    zzn$1(zzd com_google_android_gms_ads_internal_formats_zzd, String str, zzlh com_google_android_gms_internal_zzlh) {
        this.zzamk = com_google_android_gms_ads_internal_formats_zzd;
        this.zzaml = str;
        this.zzamm = com_google_android_gms_internal_zzlh;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", this.zzamk.getHeadline());
            jSONObject.put("body", this.zzamk.getBody());
            jSONObject.put("call_to_action", this.zzamk.getCallToAction());
            jSONObject.put(Param.PRICE, this.zzamk.getPrice());
            jSONObject.put("star_rating", String.valueOf(this.zzamk.getStarRating()));
            jSONObject.put(NavigationType.STORE, this.zzamk.getStore());
            jSONObject.put(SettingsJsonConstants.APP_ICON_KEY, zzn.zza(this.zzamk.zzku()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = this.zzamk.getImages();
            if (images != null) {
                for (Object zzf : images) {
                    jSONArray.put(zzn.zza(zzn.zzf(zzf)));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put(AppLinkData.ARGUMENTS_EXTRAS_KEY, zzn.zzb(this.zzamk.getExtras(), this.zzaml));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", "2");
            this.zzamm.zza("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (Throwable e) {
            zzkd.zzd("Exception occurred when loading assets", e);
        }
    }
}
