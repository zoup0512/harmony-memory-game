package com.google.android.gms.ads.internal.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.client.zzx.zza;
import com.google.android.gms.ads.internal.reward.client.zzf;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzdt;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzhh;
import com.google.android.gms.internal.zzhi;
import com.google.android.gms.internal.zzhp;
import com.google.android.gms.internal.zzhu;
import com.google.android.gms.internal.zzin;

@zzin
public class zzl {
    private final Object zzail = new Object();
    private zzx zzauz;
    private final zze zzava;
    private final zzd zzavb;
    private final zzai zzavc;
    private final zzef zzavd;
    private final zzf zzave;
    private final zzhu zzavf;
    private final zzhh zzavg;

    public zzl(zze com_google_android_gms_ads_internal_client_zze, zzd com_google_android_gms_ads_internal_client_zzd, zzai com_google_android_gms_ads_internal_client_zzai, zzef com_google_android_gms_internal_zzef, zzf com_google_android_gms_ads_internal_reward_client_zzf, zzhu com_google_android_gms_internal_zzhu, zzhh com_google_android_gms_internal_zzhh) {
        this.zzava = com_google_android_gms_ads_internal_client_zze;
        this.zzavb = com_google_android_gms_ads_internal_client_zzd;
        this.zzavc = com_google_android_gms_ads_internal_client_zzai;
        this.zzavd = com_google_android_gms_internal_zzef;
        this.zzave = com_google_android_gms_ads_internal_reward_client_zzf;
        this.zzavf = com_google_android_gms_internal_zzhu;
        this.zzavg = com_google_android_gms_internal_zzhh;
    }

    private <T> T zza(Context context, boolean z, zza<T> com_google_android_gms_ads_internal_client_zzl_zza_T) {
        if (!(z || zzm.zziw().zzar(context))) {
            zzb.zzcv("Google Play Services is not available");
            z = true;
        }
        T zziu;
        if (z) {
            zziu = com_google_android_gms_ads_internal_client_zzl_zza_T.zziu();
            return zziu == null ? com_google_android_gms_ads_internal_client_zzl_zza_T.zzin() : zziu;
        } else {
            zziu = com_google_android_gms_ads_internal_client_zzl_zza_T.zzin();
            return zziu == null ? com_google_android_gms_ads_internal_client_zzl_zza_T.zziu() : zziu;
        }
    }

    private static boolean zza(Activity activity, String str) {
        Intent intent = activity.getIntent();
        if (intent.hasExtra(str)) {
            return intent.getBooleanExtra(str, false);
        }
        zzb.e("useClientJar flag not found in activity intent extras.");
        return false;
    }

    private void zzc(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "no_ads_fallback");
        bundle.putString("flow", str);
        zzm.zziw().zza(context, null, "gmob-apps", bundle, true);
    }

    @Nullable
    private static zzx zzik() {
        try {
            Object newInstance = zzl.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance();
            if (newInstance instanceof IBinder) {
                return zza.asInterface((IBinder) newInstance);
            }
            zzb.zzcx("ClientApi class is not an instance of IBinder");
            return null;
        } catch (Throwable e) {
            zzb.zzd("Failed to instantiate ClientApi class.", e);
            return null;
        }
    }

    @Nullable
    private zzx zzil() {
        zzx com_google_android_gms_ads_internal_client_zzx;
        synchronized (this.zzail) {
            if (this.zzauz == null) {
                this.zzauz = zzik();
            }
            com_google_android_gms_ads_internal_client_zzx = this.zzauz;
        }
        return com_google_android_gms_ads_internal_client_zzx;
    }

    public zzu zza(Context context, AdSizeParcel adSizeParcel, String str) {
        return (zzu) zza(context, false, new 2(this, context, adSizeParcel, str));
    }

    public zzu zza(Context context, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj) {
        return (zzu) zza(context, false, new 1(this, context, adSizeParcel, str, com_google_android_gms_internal_zzgj));
    }

    public com.google.android.gms.ads.internal.reward.client.zzb zza(Context context, zzgj com_google_android_gms_internal_zzgj) {
        return (com.google.android.gms.ads.internal.reward.client.zzb) zza(context, false, new 7(this, context, com_google_android_gms_internal_zzgj));
    }

    public zzdt zza(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        return (zzdt) zza(context, false, new 6(this, frameLayout, frameLayout2, context));
    }

    public zzs zzb(Context context, String str, zzgj com_google_android_gms_internal_zzgj) {
        return (zzs) zza(context, false, new 4(this, context, str, com_google_android_gms_internal_zzgj));
    }

    public zzu zzb(Context context, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj) {
        return (zzu) zza(context, false, new 3(this, context, adSizeParcel, str, com_google_android_gms_internal_zzgj));
    }

    @Nullable
    public zzhp zzb(Activity activity) {
        return (zzhp) zza((Context) activity, zza(activity, "com.google.android.gms.ads.internal.purchase.useClientJar"), new 8(this, activity));
    }

    @Nullable
    public zzhi zzc(Activity activity) {
        return (zzhi) zza((Context) activity, zza(activity, "com.google.android.gms.ads.internal.overlay.useClientJar"), new 9(this, activity));
    }

    public zzz zzl(Context context) {
        return (zzz) zza(context, false, new 5(this, context));
    }
}
