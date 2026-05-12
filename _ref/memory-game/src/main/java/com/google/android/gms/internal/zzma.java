package com.google.android.gms.internal;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.zzg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzma extends zzg<zzma> {
    private ProductAction zzcso;
    private final Map<String, List<Product>> zzcsp = new HashMap();
    private final List<Promotion> zzcsq = new ArrayList();
    private final List<Product> zzcsr = new ArrayList();

    public String toString() {
        Map hashMap = new HashMap();
        if (!this.zzcsr.isEmpty()) {
            hashMap.put("products", this.zzcsr);
        }
        if (!this.zzcsq.isEmpty()) {
            hashMap.put("promotions", this.zzcsq);
        }
        if (!this.zzcsp.isEmpty()) {
            hashMap.put("impressions", this.zzcsp);
        }
        hashMap.put("productAction", this.zzcso);
        return zzg.zzj(hashMap);
    }

    public void zza(Product product, String str) {
        if (product != null) {
            Object obj;
            if (str == null) {
                obj = "";
            }
            if (!this.zzcsp.containsKey(obj)) {
                this.zzcsp.put(obj, new ArrayList());
            }
            ((List) this.zzcsp.get(obj)).add(product);
        }
    }

    public void zza(zzma com_google_android_gms_internal_zzma) {
        com_google_android_gms_internal_zzma.zzcsr.addAll(this.zzcsr);
        com_google_android_gms_internal_zzma.zzcsq.addAll(this.zzcsq);
        for (Entry entry : this.zzcsp.entrySet()) {
            String str = (String) entry.getKey();
            for (Product zza : (List) entry.getValue()) {
                com_google_android_gms_internal_zzma.zza(zza, str);
            }
        }
        if (this.zzcso != null) {
            com_google_android_gms_internal_zzma.zzcso = this.zzcso;
        }
    }

    public /* synthetic */ void zzb(zzg com_google_android_gms_analytics_zzg) {
        zza((zzma) com_google_android_gms_analytics_zzg);
    }

    public ProductAction zzxs() {
        return this.zzcso;
    }

    public List<Product> zzxt() {
        return Collections.unmodifiableList(this.zzcsr);
    }

    public Map<String, List<Product>> zzxu() {
        return this.zzcsp;
    }

    public List<Promotion> zzxv() {
        return Collections.unmodifiableList(this.zzcsq);
    }
}
