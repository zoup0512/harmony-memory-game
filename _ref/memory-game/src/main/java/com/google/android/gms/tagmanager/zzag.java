package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

public class zzag extends zzdg {
    private static final String ID = zzaf.EQUALS.toString();

    public zzag() {
        super(ID);
    }

    protected boolean zza(String str, String str2, Map<String, zza> map) {
        return str.equals(str2);
    }
}
