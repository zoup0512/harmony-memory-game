package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzbp extends zzal {
    private static final String ID = zzaf.LOWERCASE_STRING.toString();
    private static final String avP = zzag.ARG0.toString();

    public zzbp() {
        super(ID, avP);
    }

    public zza zzav(Map<String, zza> map) {
        return zzdl.zzap(zzdl.zzg((zza) map.get(avP)).toLowerCase());
    }

    public boolean zzcag() {
        return true;
    }
}
