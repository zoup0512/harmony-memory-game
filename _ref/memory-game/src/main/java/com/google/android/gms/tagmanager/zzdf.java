package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzdf extends zzdg {
    private static final String ID = zzaf.STARTS_WITH.toString();

    public zzdf() {
        super(ID);
    }

    protected boolean zza(String str, String str2, Map<String, zza> map) {
        return str.startsWith(str2);
    }
}
