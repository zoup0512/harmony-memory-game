package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

public class zzkm$zzb {
    private final List<String> zzclz = new ArrayList();
    private final List<Double> zzcma = new ArrayList();
    private final List<Double> zzcmb = new ArrayList();

    public zzkm$zzb zza(String str, double d, double d2) {
        int i = 0;
        while (i < this.zzclz.size()) {
            double doubleValue = ((Double) this.zzcmb.get(i)).doubleValue();
            double doubleValue2 = ((Double) this.zzcma.get(i)).doubleValue();
            if (d < doubleValue || (doubleValue == d && d2 < doubleValue2)) {
                break;
            }
            i++;
        }
        this.zzclz.add(i, str);
        this.zzcmb.add(i, Double.valueOf(d));
        this.zzcma.add(i, Double.valueOf(d2));
        return this;
    }

    public zzkm zzto() {
        return new zzkm(this, null);
    }
}
