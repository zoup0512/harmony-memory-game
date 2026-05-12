package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzai.zza;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class zzal {
    private final Set<String> avT;
    private final String avU;

    public zzal(String str, String... strArr) {
        this.avU = str;
        this.avT = new HashSet(strArr.length);
        for (Object add : strArr) {
            this.avT.add(add);
        }
    }

    public abstract zza zzav(Map<String, zza> map);

    public abstract boolean zzcag();

    public String zzcbp() {
        return this.avU;
    }

    public Set<String> zzcbq() {
        return this.avT;
    }

    boolean zzf(Set<String> set) {
        return set.containsAll(this.avT);
    }
}
