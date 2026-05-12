package com.google.android.gms.internal;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONObject;

@zzin
public class zzfv implements zzfu {
    private final zzft zzbms;
    private final HashSet<SimpleEntry<String, zzep>> zzbmt = new HashSet();

    public zzfv(zzft com_google_android_gms_internal_zzft) {
        this.zzbms = com_google_android_gms_internal_zzft;
    }

    public void zza(String str, zzep com_google_android_gms_internal_zzep) {
        this.zzbms.zza(str, com_google_android_gms_internal_zzep);
        this.zzbmt.add(new SimpleEntry(str, com_google_android_gms_internal_zzep));
    }

    public void zza(String str, JSONObject jSONObject) {
        this.zzbms.zza(str, jSONObject);
    }

    public void zzb(String str, zzep com_google_android_gms_internal_zzep) {
        this.zzbms.zzb(str, com_google_android_gms_internal_zzep);
        this.zzbmt.remove(new SimpleEntry(str, com_google_android_gms_internal_zzep));
    }

    public void zzb(String str, JSONObject jSONObject) {
        this.zzbms.zzb(str, jSONObject);
    }

    public void zzj(String str, String str2) {
        this.zzbms.zzj(str, str2);
    }

    public void zzmf() {
        Iterator it = this.zzbmt.iterator();
        while (it.hasNext()) {
            SimpleEntry simpleEntry = (SimpleEntry) it.next();
            String str = "Unregistering eventhandler: ";
            String valueOf = String.valueOf(((zzep) simpleEntry.getValue()).toString());
            zzkd.v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzbms.zzb((String) simpleEntry.getKey(), (zzep) simpleEntry.getValue());
        }
        this.zzbmt.clear();
    }
}
