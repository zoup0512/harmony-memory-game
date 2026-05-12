package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzaj extends zzal {
    private static final String ID = zzaf.EVENT.toString();
    private final zzcw auH;

    public zzaj(zzcw com_google_android_gms_tagmanager_zzcw) {
        super(ID, new String[0]);
        this.auH = com_google_android_gms_tagmanager_zzcw;
    }

    public zza zzav(Map<String, zza> map) {
        String zzccs = this.auH.zzccs();
        return zzccs == null ? zzdl.zzcdu() : zzdl.zzap(zzccs);
    }

    public boolean zzcag() {
        return false;
    }
}
