package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings.Secure;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzaa extends zzal {
    private static final String ID = zzaf.DEVICE_ID.toString();
    private final Context mContext;

    public zzaa(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public zza zzav(Map<String, zza> map) {
        String zzdw = zzdw(this.mContext);
        return zzdw == null ? zzdl.zzcdu() : zzdl.zzap(zzdw);
    }

    public boolean zzcag() {
        return true;
    }

    protected String zzdw(Context context) {
        return Secure.getString(context.getContentResolver(), "android_id");
    }
}
