package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzf extends zzal {
    private static final String ID = zzaf.APP_ID.toString();
    private final Context mContext;

    public zzf(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public zza zzav(Map<String, zza> map) {
        return zzdl.zzap(this.mContext.getPackageName());
    }

    public boolean zzcag() {
        return true;
    }
}
