package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzr extends zzdg {
    private static final String ID = zzaf.CONTAINS.toString();

    public zzr() {
        super(ID);
    }

    protected boolean zza(String str, String str2, Map<String, zza> map) {
        return str.contains(str2);
    }
}
