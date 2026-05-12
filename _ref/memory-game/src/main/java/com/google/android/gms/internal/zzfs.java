package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzu;

@zzin
public class zzfs {
    private final Context mContext;
    private final Object zzail;
    private final VersionInfoParcel zzalo;
    private final String zzblr;
    private zzkl<zzfp> zzbls;
    private zzkl<zzfp> zzblt;
    @Nullable
    private zzd zzblu;
    private int zzblv;

    public zzfs(Context context, VersionInfoParcel versionInfoParcel, String str) {
        this.zzail = new Object();
        this.zzblv = 1;
        this.zzblr = str;
        this.mContext = context.getApplicationContext();
        this.zzalo = versionInfoParcel;
        this.zzbls = new zzb();
        this.zzblt = new zzb();
    }

    public zzfs(Context context, VersionInfoParcel versionInfoParcel, String str, zzkl<zzfp> com_google_android_gms_internal_zzkl_com_google_android_gms_internal_zzfp, zzkl<zzfp> com_google_android_gms_internal_zzkl_com_google_android_gms_internal_zzfp2) {
        this(context, versionInfoParcel, str);
        this.zzbls = com_google_android_gms_internal_zzkl_com_google_android_gms_internal_zzfp;
        this.zzblt = com_google_android_gms_internal_zzkl_com_google_android_gms_internal_zzfp2;
    }

    private zzd zza(@Nullable zzas com_google_android_gms_internal_zzas) {
        zzd com_google_android_gms_internal_zzfs_zzd = new zzd(this.zzblt);
        zzu.zzfq().runOnUiThread(new 1(this, com_google_android_gms_internal_zzas, com_google_android_gms_internal_zzfs_zzd));
        return com_google_android_gms_internal_zzfs_zzd;
    }

    protected zzfp zza(Context context, VersionInfoParcel versionInfoParcel, @Nullable zzas com_google_android_gms_internal_zzas) {
        return new zzfr(context, versionInfoParcel, com_google_android_gms_internal_zzas);
    }

    protected zzd zzb(@Nullable zzas com_google_android_gms_internal_zzas) {
        zzd zza = zza(com_google_android_gms_internal_zzas);
        zza.zza(new 2(this, zza), new 3(this, zza));
        return zza;
    }

    public zzc zzc(@Nullable zzas com_google_android_gms_internal_zzas) {
        zzc zzmb;
        synchronized (this.zzail) {
            if (this.zzblu == null || this.zzblu.getStatus() == -1) {
                this.zzblv = 2;
                this.zzblu = zzb(com_google_android_gms_internal_zzas);
                zzmb = this.zzblu.zzmb();
            } else if (this.zzblv == 0) {
                zzmb = this.zzblu.zzmb();
            } else if (this.zzblv == 1) {
                this.zzblv = 2;
                zzb(com_google_android_gms_internal_zzas);
                zzmb = this.zzblu.zzmb();
            } else if (this.zzblv == 2) {
                zzmb = this.zzblu.zzmb();
            } else {
                zzmb = this.zzblu.zzmb();
            }
        }
        return zzmb;
    }

    public zzc zzma() {
        return zzc(null);
    }
}
