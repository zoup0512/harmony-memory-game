package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzg extends zzal {
    private static final String ID = zzaf.APP_NAME.toString();
    private final Context mContext;

    public zzg(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public zza zzav(Map<String, zza> map) {
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            return zzdl.zzap(packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.mContext.getPackageName(), 0)).toString());
        } catch (Throwable e) {
            zzbn.zzb("App name is not found.", e);
            return zzdl.zzcdu();
        }
    }

    public boolean zzcag() {
        return true;
    }
}
