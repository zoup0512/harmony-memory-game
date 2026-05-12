package com.google.android.gms.internal;

import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.internal.formats.zzf;
import com.google.android.gms.ads.internal.formats.zzh;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzii.zza;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
public class zzil implements zza<zzf> {
    private final boolean zzcaa;

    public zzil(boolean z) {
        this.zzcaa = z;
    }

    private void zza(zzii com_google_android_gms_internal_zzii, JSONObject jSONObject, SimpleArrayMap<String, Future<zzc>> simpleArrayMap) throws JSONException {
        simpleArrayMap.put(jSONObject.getString("name"), com_google_android_gms_internal_zzii.zza(jSONObject, "image_value", this.zzcaa));
    }

    private void zza(JSONObject jSONObject, SimpleArrayMap<String, String> simpleArrayMap) throws JSONException {
        simpleArrayMap.put(jSONObject.getString("name"), jSONObject.getString("string_value"));
    }

    private <K, V> SimpleArrayMap<K, V> zzc(SimpleArrayMap<K, Future<V>> simpleArrayMap) throws InterruptedException, ExecutionException {
        SimpleArrayMap<K, V> simpleArrayMap2 = new SimpleArrayMap();
        for (int i = 0; i < simpleArrayMap.size(); i++) {
            simpleArrayMap2.put(simpleArrayMap.keyAt(i), ((Future) simpleArrayMap.valueAt(i)).get());
        }
        return simpleArrayMap2;
    }

    public /* synthetic */ zzh.zza zza(zzii com_google_android_gms_internal_zzii, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        return zzd(com_google_android_gms_internal_zzii, jSONObject);
    }

    public zzf zzd(zzii com_google_android_gms_internal_zzii, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        SimpleArrayMap simpleArrayMap2 = new SimpleArrayMap();
        Future zzg = com_google_android_gms_internal_zzii.zzg(jSONObject);
        JSONArray jSONArray = jSONObject.getJSONArray("custom_assets");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString("type");
            if ("string".equals(string)) {
                zza(jSONObject2, simpleArrayMap2);
            } else if ("image".equals(string)) {
                zza(com_google_android_gms_internal_zzii, jSONObject2, simpleArrayMap);
            } else {
                String str = "Unknown custom asset type: ";
                String valueOf = String.valueOf(string);
                zzb.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        }
        return new zzf(jSONObject.getString("custom_template_id"), zzc(simpleArrayMap), simpleArrayMap2, (com.google.android.gms.ads.internal.formats.zza) zzg.get());
    }
}
