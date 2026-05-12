package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.internal.formats.zzd;
import com.google.android.gms.ads.internal.formats.zzh;
import com.google.android.gms.internal.zzii.zza;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.my.target.nativeads.banners.NavigationType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
public class zzij implements zza<zzd> {
    private final boolean zzcaa;
    private final boolean zzcab;

    public zzij(boolean z, boolean z2) {
        this.zzcaa = z;
        this.zzcab = z2;
    }

    public /* synthetic */ zzh.zza zza(zzii com_google_android_gms_internal_zzii, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        return zzb(com_google_android_gms_internal_zzii, jSONObject);
    }

    public zzd zzb(zzii com_google_android_gms_internal_zzii, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        List<zzky> zza = com_google_android_gms_internal_zzii.zza(jSONObject, "images", true, this.zzcaa, this.zzcab);
        Future zza2 = com_google_android_gms_internal_zzii.zza(jSONObject, "app_icon", true, this.zzcaa);
        Future zzg = com_google_android_gms_internal_zzii.zzg(jSONObject);
        List arrayList = new ArrayList();
        for (zzky com_google_android_gms_internal_zzky : zza) {
            arrayList.add((zzc) com_google_android_gms_internal_zzky.get());
        }
        return new zzd(jSONObject.getString("headline"), arrayList, jSONObject.getString("body"), (zzdr) zza2.get(), jSONObject.getString("call_to_action"), jSONObject.optDouble("rating", -1.0d), jSONObject.optString(NavigationType.STORE), jSONObject.optString(Param.PRICE), (com.google.android.gms.ads.internal.formats.zza) zzg.get(), new Bundle());
    }
}
