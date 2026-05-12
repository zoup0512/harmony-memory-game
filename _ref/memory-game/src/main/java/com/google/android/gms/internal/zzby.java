package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzca.zza;

public final class zzby extends zza {
    private final zzar zzaiq;
    private final zzas zzair;
    private final zzap zzais;
    private boolean zzait = false;

    public zzby(String str, Context context, boolean z) {
        this.zzaiq = zzar.zza(str, context, z);
        this.zzair = new zzas(this.zzaiq);
        this.zzais = z ? null : zzap.zze(context);
    }

    private zzd zza(zzd com_google_android_gms_dynamic_zzd, zzd com_google_android_gms_dynamic_zzd2, boolean z) {
        try {
            Uri uri = (Uri) zze.zzad(com_google_android_gms_dynamic_zzd);
            Context context = (Context) zze.zzad(com_google_android_gms_dynamic_zzd2);
            return zze.zzac(z ? this.zzair.zza(uri, context) : this.zzair.zzb(uri, context));
        } catch (zzat e) {
            return null;
        }
    }

    public zzd zza(zzd com_google_android_gms_dynamic_zzd, zzd com_google_android_gms_dynamic_zzd2) {
        return zza(com_google_android_gms_dynamic_zzd, com_google_android_gms_dynamic_zzd2, true);
    }

    public String zza(zzd com_google_android_gms_dynamic_zzd, String str) {
        return this.zzaiq.zzb((Context) zze.zzad(com_google_android_gms_dynamic_zzd), str);
    }

    public boolean zza(zzd com_google_android_gms_dynamic_zzd) {
        return this.zzair.zza((Uri) zze.zzad(com_google_android_gms_dynamic_zzd));
    }

    public zzd zzb(zzd com_google_android_gms_dynamic_zzd, zzd com_google_android_gms_dynamic_zzd2) {
        return zza(com_google_android_gms_dynamic_zzd, com_google_android_gms_dynamic_zzd2, false);
    }

    public void zzb(String str, String str2) {
        this.zzair.zzb(str, str2);
    }

    public boolean zzb(zzd com_google_android_gms_dynamic_zzd) {
        return this.zzair.zzc((Uri) zze.zzad(com_google_android_gms_dynamic_zzd));
    }

    public boolean zzb(String str, boolean z) {
        if (this.zzais == null) {
            return false;
        }
        this.zzais.zza(new Info(str, z));
        this.zzait = true;
        return true;
    }

    public String zzc(zzd com_google_android_gms_dynamic_zzd) {
        Context context = (Context) zze.zzad(com_google_android_gms_dynamic_zzd);
        String zzb = this.zzaiq.zzb(context);
        if (this.zzais == null || !this.zzait) {
            return zzb;
        }
        String zza = this.zzais.zza(zzb, this.zzais.zzb(context));
        this.zzait = false;
        return zza;
    }

    public void zzd(zzd com_google_android_gms_dynamic_zzd) {
        this.zzair.zza((MotionEvent) zze.zzad(com_google_android_gms_dynamic_zzd));
    }

    public String zzdf() {
        return "ms";
    }

    public void zzk(String str) {
        this.zzair.zzk(str);
    }
}
