package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.zzz.zza;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzin;

@zzin
public class zzo extends zza {
    private static final Object zzamr = new Object();
    @Nullable
    private static zzo zzams;
    private final Context mContext;
    private final Object zzail = new Object();
    private boolean zzamt;
    private boolean zzamu;
    private float zzamv = -1.0f;
    private VersionInfoParcel zzamw;

    zzo(Context context, VersionInfoParcel versionInfoParcel) {
        this.mContext = context;
        this.zzamw = versionInfoParcel;
        this.zzamt = false;
    }

    public static zzo zza(Context context, VersionInfoParcel versionInfoParcel) {
        zzo com_google_android_gms_ads_internal_zzo;
        synchronized (zzamr) {
            if (zzams == null) {
                zzams = new zzo(context.getApplicationContext(), versionInfoParcel);
            }
            com_google_android_gms_ads_internal_zzo = zzams;
        }
        return com_google_android_gms_ads_internal_zzo;
    }

    @Nullable
    public static zzo zzex() {
        zzo com_google_android_gms_ads_internal_zzo;
        synchronized (zzamr) {
            com_google_android_gms_ads_internal_zzo = zzams;
        }
        return com_google_android_gms_ads_internal_zzo;
    }

    public void initialize() {
        synchronized (zzamr) {
            if (this.zzamt) {
                zzb.zzcx("Mobile ads is initialized already.");
                return;
            }
            this.zzamt = true;
        }
    }

    public void setAppMuted(boolean z) {
        synchronized (this.zzail) {
            this.zzamu = z;
        }
    }

    public void setAppVolume(float f) {
        synchronized (this.zzail) {
            this.zzamv = f;
        }
    }

    public float zzey() {
        float f;
        synchronized (this.zzail) {
            f = this.zzamv;
        }
        return f;
    }

    public boolean zzez() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzamv >= 0.0f;
        }
        return z;
    }

    public boolean zzfa() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzamu;
        }
        return z;
    }

    public void zzu(String str) {
        zzdc.initialize(this.mContext);
        if (!TextUtils.isEmpty(str) && ((Boolean) zzdc.zzbct.get()).booleanValue()) {
            zzu.zzgi().zza(this.mContext, this.zzamw, true, null, str, null);
        }
    }
}
