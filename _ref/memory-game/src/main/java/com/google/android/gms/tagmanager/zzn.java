package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzn extends zzal {
    private static final String ID = zzaf.CONSTANT.toString();
    private static final String VALUE = zzag.VALUE.toString();

    public zzn() {
        super(ID, VALUE);
    }

    public static String zzcaj() {
        return ID;
    }

    public static String zzcak() {
        return VALUE;
    }

    public zza zzav(Map<String, zza> map) {
        return (zza) map.get(VALUE);
    }

    public boolean zzcag() {
        return true;
    }
}
