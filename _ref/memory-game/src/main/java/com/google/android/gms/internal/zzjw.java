package com.google.android.gms.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.mopub.common.AdType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
public class zzjw {
    private final long zzcjc;
    private final List<String> zzcjd = new ArrayList();
    private final Map<String, zzb> zzcje = new HashMap();
    private String zzcjf;
    private String zzcjg;
    private boolean zzcjh = false;

    public zzjw(String str, long j) {
        this.zzcjg = str;
        this.zzcjc = j;
        zzcl(str);
    }

    private void zzcl(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.optInt("status", -1) != 1) {
                    this.zzcjh = false;
                    zzb.zzcx("App settings could not be fetched successfully.");
                    return;
                }
                this.zzcjh = true;
                this.zzcjf = jSONObject.optString("app_id");
                JSONArray optJSONArray = jSONObject.optJSONArray("ad_unit_id_settings");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        zzi(optJSONArray.getJSONObject(i));
                    }
                }
            } catch (Throwable e) {
                zzb.zzd("Exception occurred while processing app setting json", e);
                zzu.zzft().zzb(e, true);
            }
        }
    }

    private void zzi(JSONObject jSONObject) throws JSONException {
        String optString = jSONObject.optString("format");
        CharSequence optString2 = jSONObject.optString("ad_unit_id");
        if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
            if (AdType.INTERSTITIAL.equalsIgnoreCase(optString)) {
                this.zzcjd.add(optString2);
            } else if ("rewarded".equalsIgnoreCase(optString)) {
                JSONObject optJSONObject = jSONObject.optJSONObject("mediation_config");
                if (optJSONObject != null) {
                    JSONArray optJSONArray = optJSONObject.optJSONArray("ad_networks");
                    if (optJSONArray != null) {
                        int i = 0;
                        while (i < optJSONArray.length()) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                            JSONArray optJSONArray2 = jSONObject2.optJSONArray("adapters");
                            if (optJSONArray2 != null) {
                                List arrayList = new ArrayList();
                                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    arrayList.add(optJSONArray2.getString(i2));
                                }
                                jSONObject2 = jSONObject2.optJSONObject(ShareConstants.WEB_DIALOG_PARAM_DATA);
                                if (jSONObject2 != null) {
                                    Bundle bundle = new Bundle();
                                    Iterator keys = jSONObject2.keys();
                                    while (keys.hasNext()) {
                                        optString = (String) keys.next();
                                        bundle.putString(optString, jSONObject2.getString(optString));
                                    }
                                    zza com_google_android_gms_internal_zzjw_zza = new zza(this, arrayList, bundle);
                                    zzb com_google_android_gms_internal_zzjw_zzb = this.zzcje.containsKey(optString2) ? (zzb) this.zzcje.get(optString2) : new zzb(this);
                                    com_google_android_gms_internal_zzjw_zzb.zza(com_google_android_gms_internal_zzjw_zza);
                                    this.zzcje.put(optString2, com_google_android_gms_internal_zzjw_zzb);
                                    i++;
                                } else {
                                    return;
                                }
                            }
                            return;
                        }
                    }
                }
            }
        }
    }

    public long zzse() {
        return this.zzcjc;
    }

    public boolean zzsf() {
        return this.zzcjh;
    }

    public String zzsg() {
        return this.zzcjg;
    }

    public String zzsh() {
        return this.zzcjf;
    }
}
