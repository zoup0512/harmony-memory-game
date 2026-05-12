package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzadw;
import com.google.android.gms.internal.zzadw.zzc;
import com.google.android.gms.internal.zzadw.zzd;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class zzbg {
    private static zza zzai(Object obj) throws JSONException {
        return zzdl.zzap(zzaj(obj));
    }

    static Object zzaj(Object obj) throws JSONException {
        if (obj instanceof JSONArray) {
            throw new RuntimeException("JSONArrays are not supported");
        } else if (JSONObject.NULL.equals(obj)) {
            throw new RuntimeException("JSON nulls are not supported");
        } else if (!(obj instanceof JSONObject)) {
            return obj;
        } else {
            JSONObject jSONObject = (JSONObject) obj;
            Map hashMap = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                hashMap.put(str, zzaj(jSONObject.get(str)));
            }
            return hashMap;
        }
    }

    public static zzc zzox(String str) throws JSONException {
        zza zzai = zzai(new JSONObject(str));
        zzd zzcha = zzc.zzcha();
        for (int i = 0; i < zzai.zzwv.length; i++) {
            zzcha.zzc(zzadw.zza.zzcgy().zzb(zzag.INSTANCE_NAME.toString(), zzai.zzwv[i]).zzb(zzag.FUNCTION.toString(), zzdl.zzpi(zzn.zzcaj())).zzb(zzn.zzcak(), zzai.zzww[i]).zzcgz());
        }
        return zzcha.zzchc();
    }
}
