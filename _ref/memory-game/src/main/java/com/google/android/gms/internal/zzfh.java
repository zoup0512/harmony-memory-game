package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzl;

@zzin
public class zzfh {
    private Context mContext;
    private final zzd zzajv;
    private final zzgj zzajz;
    private final VersionInfoParcel zzalo;

    zzfh(Context context, zzgj com_google_android_gms_internal_zzgj, VersionInfoParcel versionInfoParcel, zzd com_google_android_gms_ads_internal_zzd) {
        this.mContext = context;
        this.zzajz = com_google_android_gms_internal_zzgj;
        this.zzalo = versionInfoParcel;
        this.zzajv = com_google_android_gms_ads_internal_zzd;
    }

    public Context getApplicationContext() {
        return this.mContext.getApplicationContext();
    }

    public zzl zzbc(String str) {
        return new zzl(this.mContext, new AdSizeParcel(), str, this.zzajz, this.zzalo, this.zzajv);
    }

    public zzl zzbd(String str) {
        return new zzl(this.mContext.getApplicationContext(), new AdSizeParcel(), str, this.zzajz, this.zzalo, this.zzajv);
    }

    public zzfh zzln() {
        return new zzfh(getApplicationContext(), this.zzajz, this.zzalo, this.zzajv);
    }
}
