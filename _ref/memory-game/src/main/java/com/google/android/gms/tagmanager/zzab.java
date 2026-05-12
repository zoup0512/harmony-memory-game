package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzab extends zzal {
    private static final String ID = zzaf.DEVICE_NAME.toString();

    public zzab() {
        super(ID, new String[0]);
    }

    public zza zzav(Map<String, zza> map) {
        String str = Build.MANUFACTURER;
        Object obj = Build.MODEL;
        if (!(obj.startsWith(str) || str.equals("unknown"))) {
            obj = new StringBuilder((String.valueOf(str).length() + 1) + String.valueOf(obj).length()).append(str).append(" ").append(obj).toString();
        }
        return zzdl.zzap(obj);
    }

    public boolean zzcag() {
        return true;
    }
}
