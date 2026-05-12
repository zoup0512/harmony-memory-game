package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzdk;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzlh;

@zzin
public abstract class zzj {
    @Nullable
    public abstract zzi zza(Context context, zzlh com_google_android_gms_internal_zzlh, int i, boolean z, zzdk com_google_android_gms_internal_zzdk, zzdi com_google_android_gms_internal_zzdi);

    protected boolean zzg(zzlh com_google_android_gms_internal_zzlh) {
        return com_google_android_gms_internal_zzlh.zzdn().zzaus;
    }

    protected boolean zzq(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return zzs.zzavq() && (applicationInfo == null || applicationInfo.targetSdkVersion >= 11);
    }
}
