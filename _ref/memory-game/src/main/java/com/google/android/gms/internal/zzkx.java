package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@zzin
public class zzkx {
    public static <A, B> zzky<B> zza(zzky<A> com_google_android_gms_internal_zzky_A, zza<A, B> com_google_android_gms_internal_zzkx_zza_A__B) {
        zzky com_google_android_gms_internal_zzkv = new zzkv();
        com_google_android_gms_internal_zzky_A.zzc(new 1(com_google_android_gms_internal_zzkv, com_google_android_gms_internal_zzkx_zza_A__B, com_google_android_gms_internal_zzky_A));
        return com_google_android_gms_internal_zzkv;
    }

    public static <V> zzky<List<V>> zzn(List<zzky<V>> list) {
        zzky com_google_android_gms_internal_zzkv = new zzkv();
        int size = list.size();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (zzky zzc : list) {
            zzc.zzc(new 2(atomicInteger, size, com_google_android_gms_internal_zzkv, list));
        }
        return com_google_android_gms_internal_zzkv;
    }

    private static <V> List<V> zzo(List<zzky<V>> list) throws ExecutionException, InterruptedException {
        List<V> arrayList = new ArrayList();
        for (zzky com_google_android_gms_internal_zzky : list) {
            Object obj = com_google_android_gms_internal_zzky.get();
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
