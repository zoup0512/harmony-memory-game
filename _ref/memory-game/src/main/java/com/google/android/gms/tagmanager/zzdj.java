package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

abstract class zzdj extends zzal {
    public zzdj(String str, String... strArr) {
        super(str, strArr);
    }

    public zza zzav(Map<String, zza> map) {
        zzax(map);
        return zzdl.zzcdu();
    }

    public abstract void zzax(Map<String, zza> map);

    public boolean zzcag() {
        return false;
    }
}
