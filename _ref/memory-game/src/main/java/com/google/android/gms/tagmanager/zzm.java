package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;

class zzm<K, V> {
    final zza<K, V> auD = new zza<K, V>(this) {
        final /* synthetic */ zzm auE;

        {
            this.auE = r1;
        }

        public int sizeOf(K k, V v) {
            return 1;
        }
    };

    public interface zza<K, V> {
        int sizeOf(K k, V v);
    }

    public zzl<K, V> zza(int i, zza<K, V> com_google_android_gms_tagmanager_zzm_zza_K__V) {
        if (i > 0) {
            return zzcai() < 12 ? new zzdd(i, com_google_android_gms_tagmanager_zzm_zza_K__V) : new zzbh(i, com_google_android_gms_tagmanager_zzm_zza_K__V);
        } else {
            throw new IllegalArgumentException("maxSize <= 0");
        }
    }

    int zzcai() {
        return VERSION.SDK_INT;
    }
}
