package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzaf extends zzdg {
    private static final String ID = com.google.android.gms.internal.zzaf.ENDS_WITH.toString();

    public zzaf() {
        super(ID);
    }

    protected boolean zza(String str, String str2, Map<String, zza> map) {
        return str.endsWith(str2);
    }
}
