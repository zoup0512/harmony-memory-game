package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzjw;
import com.google.android.gms.internal.zzkh;

@zzin
public class zzg {
    private Context mContext;
    private final Object zzail = new Object();
    public final zzep zzaku = new 1(this);

    private static boolean zza(@Nullable zzjw com_google_android_gms_internal_zzjw) {
        if (com_google_android_gms_internal_zzjw == null) {
            return true;
        }
        boolean z = (((zzu.zzfu().currentTimeMillis() - com_google_android_gms_internal_zzjw.zzse()) > ((Long) zzdc.zzbcv.get()).longValue() ? 1 : ((zzu.zzfu().currentTimeMillis() - com_google_android_gms_internal_zzjw.zzse()) == ((Long) zzdc.zzbcv.get()).longValue() ? 0 : -1)) > 0) || !com_google_android_gms_internal_zzjw.zzsf();
        return z;
    }

    public void zza(Context context, VersionInfoParcel versionInfoParcel, boolean z, @Nullable zzjw com_google_android_gms_internal_zzjw, String str, @Nullable String str2) {
        if (!zza(com_google_android_gms_internal_zzjw)) {
            return;
        }
        if (context == null) {
            zzb.zzcx("Context not provided to fetch application settings");
        } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            zzb.zzcx("App settings could not be fetched. Required parameters missing");
        } else {
            this.mContext = context;
            zzkh.zzclc.post(new 2(this, zzu.zzfq().zzc(context, versionInfoParcel), str, str2, z, context));
        }
    }
}
