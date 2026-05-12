package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import java.util.HashMap;
import java.util.Map;

public class zzadv {
    Map<String, Object> aBL;
    private final Map<String, Object> aBM;
    private final zzadx aCX;
    private String avh;
    private final Context mContext;
    private final zze zzaoc;

    public zzadv(Context context) {
        this(context, new HashMap(), new zzadx(context), zzh.zzavm());
    }

    zzadv(Context context, Map<String, Object> map, zzadx com_google_android_gms_internal_zzadx, zze com_google_android_gms_common_util_zze) {
        this.avh = null;
        this.aBL = new HashMap();
        this.mContext = context;
        this.zzaoc = com_google_android_gms_common_util_zze;
        this.aCX = com_google_android_gms_internal_zzadx;
        this.aBM = map;
    }

    public void zzqi(String str) {
        this.avh = str;
    }
}
