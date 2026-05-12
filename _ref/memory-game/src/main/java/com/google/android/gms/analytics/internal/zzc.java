package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;

public class zzc {
    private final zzf zzcwp;

    protected zzc(zzf com_google_android_gms_analytics_internal_zzf) {
        zzab.zzy(com_google_android_gms_analytics_internal_zzf);
        this.zzcwp = com_google_android_gms_analytics_internal_zzf;
    }

    private void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        zzaf com_google_android_gms_analytics_internal_zzaf = null;
        if (this.zzcwp != null) {
            com_google_android_gms_analytics_internal_zzaf = this.zzcwp.zzzj();
        }
        if (com_google_android_gms_analytics_internal_zzaf != null) {
            com_google_android_gms_analytics_internal_zzaf.zza(i, str, obj, obj2, obj3);
            return;
        }
        String str2 = (String) zzy.zzczn.get();
        if (Log.isLoggable(str2, i)) {
            Log.println(i, str2, zzc(str, obj, obj2, obj3));
        }
    }

    protected static String zzc(String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            Object obj4 = "";
        }
        Object zzk = zzk(obj);
        Object zzk2 = zzk(obj2);
        Object zzk3 = zzk(obj3);
        StringBuilder stringBuilder = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(obj4)) {
            stringBuilder.append(obj4);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(zzk)) {
            stringBuilder.append(str2);
            stringBuilder.append(zzk);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzk2)) {
            stringBuilder.append(str2);
            stringBuilder.append(zzk2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzk3)) {
            stringBuilder.append(str2);
            stringBuilder.append(zzk3);
            str2 = ", ";
        }
        return stringBuilder.toString();
    }

    private static String zzk(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (!(obj instanceof Boolean)) {
            return obj instanceof Throwable ? ((Throwable) obj).toString() : obj.toString();
        } else {
            return obj == Boolean.TRUE ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
        }
    }

    protected Context getContext() {
        return this.zzcwp.getContext();
    }

    public void zza(String str, Object obj) {
        zza(2, str, obj, null, null);
    }

    public void zza(String str, Object obj, Object obj2) {
        zza(2, str, obj, obj2, null);
    }

    public void zza(String str, Object obj, Object obj2, Object obj3) {
        zza(3, str, obj, obj2, obj3);
    }

    public void zzb(String str, Object obj) {
        zza(3, str, obj, null, null);
    }

    public void zzb(String str, Object obj, Object obj2) {
        zza(3, str, obj, obj2, null);
    }

    public void zzb(String str, Object obj, Object obj2, Object obj3) {
        zza(5, str, obj, obj2, obj3);
    }

    public void zzc(String str, Object obj) {
        zza(4, str, obj, null, null);
    }

    public void zzc(String str, Object obj, Object obj2) {
        zza(5, str, obj, obj2, null);
    }

    public void zzd(String str, Object obj) {
        zza(5, str, obj, null, null);
    }

    public void zzd(String str, Object obj, Object obj2) {
        zza(6, str, obj, obj2, null);
    }

    public void zze(String str, Object obj) {
        zza(6, str, obj, null, null);
    }

    public void zzeh(String str) {
        zza(2, str, null, null, null);
    }

    public void zzei(String str) {
        zza(3, str, null, null, null);
    }

    public void zzej(String str) {
        zza(4, str, null, null, null);
    }

    public void zzek(String str) {
        zza(5, str, null, null, null);
    }

    public void zzel(String str) {
        zza(6, str, null, null, null);
    }

    public boolean zztb() {
        return Log.isLoggable((String) zzy.zzczn.get(), 2);
    }

    public GoogleAnalytics zzvx() {
        return this.zzcwp.zzzk();
    }

    protected zzb zzwd() {
        return this.zzcwp.zzwd();
    }

    protected zzap zzwe() {
        return this.zzcwp.zzwe();
    }

    protected void zzwu() {
        this.zzcwp.zzwu();
    }

    public zzf zzyu() {
        return this.zzcwp;
    }

    protected void zzyv() {
        if (zzyy().zzabc()) {
            throw new IllegalStateException("Call only supported on the client side");
        }
    }

    protected zze zzyw() {
        return this.zzcwp.zzyw();
    }

    protected zzaf zzyx() {
        return this.zzcwp.zzyx();
    }

    protected zzr zzyy() {
        return this.zzcwp.zzyy();
    }

    protected zzi zzyz() {
        return this.zzcwp.zzyz();
    }

    protected zzv zzza() {
        return this.zzcwp.zzza();
    }

    protected zzai zzzb() {
        return this.zzcwp.zzzb();
    }

    protected zzn zzzc() {
        return this.zzcwp.zzzn();
    }

    protected zza zzzd() {
        return this.zzcwp.zzzm();
    }

    protected zzk zzze() {
        return this.zzcwp.zzze();
    }

    protected zzu zzzf() {
        return this.zzcwp.zzzf();
    }
}
