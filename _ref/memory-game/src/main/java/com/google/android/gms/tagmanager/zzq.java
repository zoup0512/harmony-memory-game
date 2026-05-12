package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzq extends zzal {
    private static final String ID = zzaf.CONTAINER_VERSION.toString();
    private final String oi;

    public zzq(String str) {
        super(ID, new String[0]);
        this.oi = str;
    }

    public zza zzav(Map<String, zza> map) {
        return this.oi == null ? zzdl.zzcdu() : zzdl.zzap(this.oi);
    }

    public boolean zzcag() {
        return true;
    }
}
