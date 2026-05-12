package com.google.android.gms.ads.internal.formats;

import com.google.android.gms.internal.zzft;
import com.google.android.gms.internal.zzih$zza;
import org.json.JSONObject;

class zzi$1 extends zzih$zza {
    final /* synthetic */ JSONObject zzbgj;
    final /* synthetic */ zzi zzbgk;

    zzi$1(zzi com_google_android_gms_ads_internal_formats_zzi, JSONObject jSONObject) {
        this.zzbgk = com_google_android_gms_ads_internal_formats_zzi;
        this.zzbgj = jSONObject;
    }

    public void zze(zzft com_google_android_gms_internal_zzft) {
        com_google_android_gms_internal_zzft.zza("google.afma.nativeAds.handleClickGmsg", this.zzbgj);
    }
}
