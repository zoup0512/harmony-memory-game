package com.google.android.gms.tagmanager;

import com.google.android.gms.tagmanager.zzm.zza;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

class zzdd<K, V> implements zzl<K, V> {
    private final Map<K, V> ayd = new HashMap();
    private final int aye;
    private final zza<K, V> ayf;
    private int ayg;

    zzdd(int i, zza<K, V> com_google_android_gms_tagmanager_zzm_zza_K__V) {
        this.aye = i;
        this.ayf = com_google_android_gms_tagmanager_zzm_zza_K__V;
    }

    public synchronized V get(K k) {
        return this.ayd.get(k);
    }

    public synchronized void zzi(K k, V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        this.ayg += this.ayf.sizeOf(k, v);
        if (this.ayg > this.aye) {
            Iterator it = this.ayd.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                this.ayg -= this.ayf.sizeOf(entry.getKey(), entry.getValue());
                it.remove();
                if (this.ayg <= this.aye) {
                    break;
                }
            }
        }
        this.ayd.put(k, v);
    }
}
