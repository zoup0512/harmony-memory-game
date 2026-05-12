package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
public class zzex implements zzep {
    private final Object zzail = new Object();
    private final Map<String, zza> zzbix = new HashMap();

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        String str = (String) map.get("id");
        String str2 = (String) map.get("fail");
        String str3 = (String) map.get("fail_reason");
        String str4 = (String) map.get("result");
        synchronized (this.zzail) {
            zza com_google_android_gms_internal_zzex_zza = (zza) this.zzbix.remove(str);
            if (com_google_android_gms_internal_zzex_zza == null) {
                str2 = "Received result for unexpected method invocation: ";
                str = String.valueOf(str);
                zzb.zzcx(str.length() != 0 ? str2.concat(str) : new String(str2));
            } else if (!TextUtils.isEmpty(str2)) {
                com_google_android_gms_internal_zzex_zza.zzay(str3);
            } else if (str4 == null) {
                com_google_android_gms_internal_zzex_zza.zzay("No result.");
            } else {
                try {
                    com_google_android_gms_internal_zzex_zza.zzd(new JSONObject(str4));
                } catch (JSONException e) {
                    com_google_android_gms_internal_zzex_zza.zzay(e.getMessage());
                }
            }
        }
    }
}
