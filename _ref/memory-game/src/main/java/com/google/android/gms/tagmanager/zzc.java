package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzc extends zzal {
    private static final String ID = zzaf.ADVERTISING_TRACKING_ENABLED.toString();
    private final zza auv;

    public zzc(Context context) {
        this(zza.zzdu(context));
    }

    zzc(zza com_google_android_gms_tagmanager_zza) {
        super(ID, new String[0]);
        this.auv = com_google_android_gms_tagmanager_zza;
    }

    public zza zzav(Map<String, zza> map) {
        return zzdl.zzap(Boolean.valueOf(!this.auv.isLimitAdTrackingEnabled()));
    }

    public boolean zzcag() {
        return false;
    }
}
