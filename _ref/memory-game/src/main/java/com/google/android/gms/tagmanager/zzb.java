package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzb extends zzal {
    private static final String ID = zzaf.ADVERTISER_ID.toString();
    private final zza auv;

    public zzb(Context context) {
        this(zza.zzdu(context));
    }

    zzb(zza com_google_android_gms_tagmanager_zza) {
        super(ID, new String[0]);
        this.auv = com_google_android_gms_tagmanager_zza;
    }

    public zza zzav(Map<String, zza> map) {
        String zzcaa = this.auv.zzcaa();
        return zzcaa == null ? zzdl.zzcdu() : zzdl.zzap(zzcaa);
    }

    public boolean zzcag() {
        return false;
    }
}
