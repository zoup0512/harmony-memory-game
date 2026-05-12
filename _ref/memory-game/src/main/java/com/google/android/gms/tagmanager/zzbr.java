package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings.Secure;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Map;

class zzbr extends zzal {
    private static final String ID = zzaf.MOBILE_ADWORDS_UNIQUE_ID.toString();
    private final Context mContext;

    public zzbr(Context context) {
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
