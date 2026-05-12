package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

@zzin
public class zzkm {
    private final String[] zzclr;
    private final double[] zzcls;
    private final double[] zzclt;
    private final int[] zzclu;
    private int zzclv;

    private zzkm(zzb com_google_android_gms_internal_zzkm_zzb) {
        int size = zzb.zza(com_google_android_gms_internal_zzkm_zzb).size();
        this.zzclr = (String[]) zzb.zzb(com_google_android_gms_internal_zzkm_zzb).toArray(new String[size]);
        this.zzcls = zzm(zzb.zza(com_google_android_gms_internal_zzkm_zzb));
        this.zzclt = zzm(zzb.zzc(com_google_android_gms_internal_zzkm_zzb));
        this.zzclu = new int[size];
        this.zzclv = 0;
    }

    private double[] zzm(List<Double> list) {
        double[] dArr = new double[list.size()];
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = ((Double) list.get(i)).doubleValue();
        }
        return dArr;
    }

    public List<zza> getBuckets() {
        List<zza> arrayList = new ArrayList(this.zzclr.length);
        for (int i = 0; i < this.zzclr.length; i++) {
            arrayList.add(new zza(this.zzclr[i], this.zzclt[i], this.zzcls[i], ((double) this.zzclu[i]) / ((double) this.zzclv), this.zzclu[i]));
        }
        return arrayList;
    }

    public void zza(double d) {
        this.zzclv++;
        int i = 0;
        while (i < this.zzclt.length) {
            if (this.zzclt[i] <= d && d < this.zzcls[i]) {
                int[] iArr = this.zzclu;
                iArr[i] = iArr[i] + 1;
            }
            if (d >= this.zzclt[i]) {
                i++;
            } else {
                return;
            }
        }
    }
}
