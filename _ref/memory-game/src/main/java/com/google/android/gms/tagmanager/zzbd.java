package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzbd extends zzal {
    private static final String ID = zzaf.INSTALL_REFERRER.toString();
    private static final String auw = zzag.COMPONENT.toString();
    private final Context zzagf;

    public zzbd(Context context) {
        super(ID, new String[0]);
        this.zzagf = context;
    }

    public zza zzav(Map<String, zza> map) {
        String zzx = zzbe.zzx(this.zzagf, ((zza) map.get(auw)) != null ? zzdl.zzg((zza) map.get(auw)) : null);
        return zzx != null ? zzdl.zzap(zzx) : zzdl.zzcdu();
    }

    public boolean zzcag() {
        return true;
    }
}
