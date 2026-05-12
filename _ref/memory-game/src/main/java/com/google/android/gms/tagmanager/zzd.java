package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import java.util.Map;

class zzd implements zzb {
    private final Context zzagf;

    public zzd(Context context) {
        this.zzagf = context;
    }

    public void zzaw(Map<String, Object> map) {
        Object obj;
        Object obj2 = map.get("gtm.url");
        if (obj2 == null) {
            obj = map.get("gtm");
            if (obj != null && (obj instanceof Map)) {
                obj = ((Map) obj).get("url");
                if (obj != null && (obj instanceof String)) {
                    String queryParameter = Uri.parse((String) obj).getQueryParameter("referrer");
                    if (queryParameter != null) {
                        zzbe.zzy(this.zzagf, queryParameter);
                        return;
                    }
                    return;
                }
            }
        }
        obj = obj2;
        if (obj != null) {
        }
    }
}
