package com.google.android.gms.ads.internal.request;

import com.google.android.gms.internal.zzft;
import com.google.android.gms.internal.zzkd;
import com.google.android.gms.internal.zzla.zza;
import com.google.android.gms.internal.zzla.zzc;
import org.json.JSONObject;

class zzn$2 implements Runnable {
    final /* synthetic */ zzn zzcdl;
    final /* synthetic */ JSONObject zzcdm;
    final /* synthetic */ String zzcdn;

    zzn$2(zzn com_google_android_gms_ads_internal_request_zzn, JSONObject jSONObject, String str) {
        this.zzcdl = com_google_android_gms_ads_internal_request_zzn;
        this.zzcdm = jSONObject;
        this.zzcdn = str;
    }

    public void run() {
        zzn.zza(this.zzcdl, zzn.zzrd().zzma());
        zzn.zzb(this.zzcdl).zza(new zzc<zzft>(this) {
            final /* synthetic */ zzn$2 zzcdo;

            {
                this.zzcdo = r1;
            }

            public void zzb(zzft com_google_android_gms_internal_zzft) {
                try {
                    com_google_android_gms_internal_zzft.zza("AFMA_getAdapterLessMediationAd", this.zzcdo.zzcdm);
                } catch (Throwable e) {
                    zzkd.zzb("Error requesting an ad url", e);
                    zzn.zzrc().zzax(this.zzcdo.zzcdn);
                }
            }

            public /* synthetic */ void zzd(Object obj) {
                zzb((zzft) obj);
            }
        }, new zza(this) {
            final /* synthetic */ zzn$2 zzcdo;

            {
                this.zzcdo = r1;
            }

            public void run() {
                zzn.zzrc().zzax(this.zzcdo.zzcdn);
            }
        });
    }
}
