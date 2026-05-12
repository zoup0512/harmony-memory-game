package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
public final class zzga {
    public final List<zzfz> zzbnk;
    public final long zzbnl;
    public final List<String> zzbnm;
    public final List<String> zzbnn;
    public final List<String> zzbno;
    public final List<String> zzbnp;
    public final boolean zzbnq;
    public final String zzbnr;
    public final long zzbns;
    public final String zzbnt;
    public final int zzbnu;
    public final int zzbnv;
    public final long zzbnw;
    public final boolean zzbnx;
    public int zzbny;
    public int zzbnz;

    public zzga(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        if (zzb.zzaz(2)) {
            String str2 = "Mediation Response JSON: ";
            String valueOf = String.valueOf(jSONObject.toString(2));
            zzkd.v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        JSONArray jSONArray = jSONObject.getJSONArray("ad_networks");
        List arrayList = new ArrayList(jSONArray.length());
        int i = -1;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            zzfz com_google_android_gms_internal_zzfz = new zzfz(jSONArray.getJSONObject(i2));
            arrayList.add(com_google_android_gms_internal_zzfz);
            if (i < 0 && zza(com_google_android_gms_internal_zzfz)) {
                i = i2;
            }
        }
        this.zzbny = i;
        this.zzbnz = jSONArray.length();
        this.zzbnk = Collections.unmodifiableList(arrayList);
        this.zzbnr = jSONObject.getString("qdata");
        this.zzbnv = jSONObject.optInt("fs_model_type", -1);
        this.zzbnw = jSONObject.optLong("timeout_ms", -1);
        JSONObject optJSONObject = jSONObject.optJSONObject("settings");
        if (optJSONObject != null) {
            this.zzbnl = optJSONObject.optLong("ad_network_timeout_millis", -1);
            this.zzbnm = zzu.zzgf().zza(optJSONObject, "click_urls");
            this.zzbnn = zzu.zzgf().zza(optJSONObject, "imp_urls");
            this.zzbno = zzu.zzgf().zza(optJSONObject, "nofill_urls");
            this.zzbnp = zzu.zzgf().zza(optJSONObject, "remote_ping_urls");
            this.zzbnq = optJSONObject.optBoolean("render_in_browser", false);
            long optLong = optJSONObject.optLong("refresh", -1);
            this.zzbns = optLong > 0 ? optLong * 1000 : -1;
            RewardItemParcel zza = RewardItemParcel.zza(optJSONObject.optJSONArray("rewards"));
            if (zza == null) {
                this.zzbnt = null;
                this.zzbnu = 0;
            } else {
                this.zzbnt = zza.type;
                this.zzbnu = zza.zzcid;
            }
            this.zzbnx = optJSONObject.optBoolean("use_displayed_impression", false);
            return;
        }
        this.zzbnl = -1;
        this.zzbnm = null;
        this.zzbnn = null;
        this.zzbno = null;
        this.zzbnp = null;
        this.zzbns = -1;
        this.zzbnt = null;
        this.zzbnu = 0;
        this.zzbnx = false;
        this.zzbnq = false;
    }

    public zzga(List<zzfz> list, long j, List<String> list2, List<String> list3, List<String> list4, List<String> list5, boolean z, String str, long j2, int i, int i2, String str2, int i3, int i4, long j3, boolean z2) {
        this.zzbnk = list;
        this.zzbnl = j;
        this.zzbnm = list2;
        this.zzbnn = list3;
        this.zzbno = list4;
        this.zzbnp = list5;
        this.zzbnq = z;
        this.zzbnr = str;
        this.zzbns = j2;
        this.zzbny = i;
        this.zzbnz = i2;
        this.zzbnt = str2;
        this.zzbnu = i3;
        this.zzbnv = i4;
        this.zzbnw = j3;
        this.zzbnx = z2;
    }

    private boolean zza(zzfz com_google_android_gms_internal_zzfz) {
        for (String equals : com_google_android_gms_internal_zzfz.zzbmw) {
            if (equals.equals("com.google.ads.mediation.admob.AdMobAdapter")) {
                return true;
            }
        }
        return false;
    }
}
