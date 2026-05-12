package com.google.android.gms.ads.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.internal.zzfs;
import com.google.android.gms.internal.zzft;
import com.google.android.gms.internal.zzkd;
import com.google.android.gms.internal.zzla.zzb;
import com.google.android.gms.internal.zzla.zzc;
import io.branch.indexing.ContentDiscoveryManifest;
import org.json.JSONObject;

class zzg$2 implements Runnable {
    final /* synthetic */ zzg zzakv;
    final /* synthetic */ zzfs zzakw;
    final /* synthetic */ String zzakx;
    final /* synthetic */ String zzaky;
    final /* synthetic */ boolean zzakz;
    final /* synthetic */ Context zzala;

    zzg$2(zzg com_google_android_gms_ads_internal_zzg, zzfs com_google_android_gms_internal_zzfs, String str, String str2, boolean z, Context context) {
        this.zzakv = com_google_android_gms_ads_internal_zzg;
        this.zzakw = com_google_android_gms_internal_zzfs;
        this.zzakx = str;
        this.zzaky = str2;
        this.zzakz = z;
        this.zzala = context;
    }

    public void run() {
        this.zzakw.zzma().zza(new zzc<zzft>(this) {
            final /* synthetic */ zzg$2 zzalb;

            {
                this.zzalb = r1;
            }

            public void zzb(zzft com_google_android_gms_internal_zzft) {
                com_google_android_gms_internal_zzft.zza("/appSettingsFetched", this.zzalb.zzakv.zzaku);
                try {
                    JSONObject jSONObject = new JSONObject();
                    if (!TextUtils.isEmpty(this.zzalb.zzakx)) {
                        jSONObject.put("app_id", this.zzalb.zzakx);
                    } else if (!TextUtils.isEmpty(this.zzalb.zzaky)) {
                        jSONObject.put("ad_unit_id", this.zzalb.zzaky);
                    }
                    jSONObject.put("is_init", this.zzalb.zzakz);
                    jSONObject.put(ContentDiscoveryManifest.PACKAGE_NAME_KEY, this.zzalb.zzala.getPackageName());
                    com_google_android_gms_internal_zzft.zza("AFMA_fetchAppSettings", jSONObject);
                } catch (Throwable e) {
                    com_google_android_gms_internal_zzft.zzb("/appSettingsFetched", this.zzalb.zzakv.zzaku);
                    zzkd.zzb("Error requesting application settings", e);
                }
            }

            public /* synthetic */ void zzd(Object obj) {
                zzb((zzft) obj);
            }
        }, new zzb());
    }
}
