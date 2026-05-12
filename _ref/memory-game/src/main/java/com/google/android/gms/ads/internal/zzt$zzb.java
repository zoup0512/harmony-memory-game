package com.google.android.gms.ads.internal;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.internal.zzdc;
import java.util.Map;
import java.util.TreeMap;

class zzt$zzb {
    private final String zzanp;
    private final Map<String, String> zzanq = new TreeMap();
    private String zzanr;
    private String zzans;

    public zzt$zzb(String str) {
        this.zzanp = str;
    }

    public String getQuery() {
        return this.zzanr;
    }

    public String zzfi() {
        return this.zzans;
    }

    public String zzfj() {
        return this.zzanp;
    }

    public Map<String, String> zzfk() {
        return this.zzanq;
    }

    public void zzh(AdRequestParcel adRequestParcel) {
        this.zzanr = adRequestParcel.zzatt.zzaxl;
        Bundle bundle = adRequestParcel.zzatw != null ? adRequestParcel.zzatw.getBundle(AdMobAdapter.class.getName()) : null;
        if (bundle != null) {
            String str = (String) zzdc.zzbdc.get();
            for (String str2 : bundle.keySet()) {
                if (str.equals(str2)) {
                    this.zzans = bundle.getString(str2);
                } else if (str2.startsWith("csa_")) {
                    this.zzanq.put(str2.substring("csa_".length()), bundle.getString(str2));
                }
            }
        }
    }
}
