package com.google.android.gms.ads.internal.formats;

import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzdr;
import com.google.android.gms.internal.zzdz.zza;
import com.google.android.gms.internal.zzin;
import java.util.Arrays;
import java.util.List;

@zzin
public class zzf extends zza implements zzh.zza {
    private final Object zzail = new Object();
    private final zza zzbfo;
    private zzh zzbfp;
    private final String zzbfs;
    private final SimpleArrayMap<String, zzc> zzbft;
    private final SimpleArrayMap<String, String> zzbfu;

    public zzf(String str, SimpleArrayMap<String, zzc> simpleArrayMap, SimpleArrayMap<String, String> simpleArrayMap2, zza com_google_android_gms_ads_internal_formats_zza) {
        this.zzbfs = str;
        this.zzbft = simpleArrayMap;
        this.zzbfu = simpleArrayMap2;
        this.zzbfo = com_google_android_gms_ads_internal_formats_zza;
    }

    public List<String> getAvailableAssetNames() {
        int i = 0;
        String[] strArr = new String[(this.zzbft.size() + this.zzbfu.size())];
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzbft.size(); i3++) {
            strArr[i2] = (String) this.zzbft.keyAt(i3);
            i2++;
        }
        while (i < this.zzbfu.size()) {
            strArr[i2] = (String) this.zzbfu.keyAt(i);
            i++;
            i2++;
        }
        return Arrays.asList(strArr);
    }

    public String getCustomTemplateId() {
        return this.zzbfs;
    }

    public void performClick(String str) {
        synchronized (this.zzail) {
            if (this.zzbfp == null) {
                zzb.e("Attempt to call performClick before ad initialized.");
                return;
            }
            this.zzbfp.zza(str, null, null, null);
        }
    }

    public void recordImpression() {
        synchronized (this.zzail) {
            if (this.zzbfp == null) {
                zzb.e("Attempt to perform recordImpression before ad initialized.");
                return;
            }
            this.zzbfp.recordImpression();
        }
    }

    public String zzat(String str) {
        return (String) this.zzbfu.get(str);
    }

    public zzdr zzau(String str) {
        return (zzdr) this.zzbft.get(str);
    }

    public void zzb(zzh com_google_android_gms_ads_internal_formats_zzh) {
        synchronized (this.zzail) {
            this.zzbfp = com_google_android_gms_ads_internal_formats_zzh;
        }
    }

    public String zzkw() {
        return "3";
    }

    public zza zzkx() {
        return this.zzbfo;
    }
}
