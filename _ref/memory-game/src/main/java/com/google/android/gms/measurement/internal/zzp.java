package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.measurement.AppMeasurement;

public class zzp extends zzaa {
    private final long ahR = zzbsf().zzbpz();
    private final char ajp;
    private final zza ajq;
    private final zza ajr;
    private final zza ajs;
    private final zza ajt;
    private final zza aju;
    private final zza ajv;
    private final zza ajw;
    private final zza ajx;
    private final zza ajy;
    private final String zc = zzbsf().zzbql();

    public class zza {
        final /* synthetic */ zzp ajA;
        private final boolean ajB;
        private final boolean ajC;
        private final int mPriority;

        zza(zzp com_google_android_gms_measurement_internal_zzp, int i, boolean z, boolean z2) {
            this.ajA = com_google_android_gms_measurement_internal_zzp;
            this.mPriority = i;
            this.ajB = z;
            this.ajC = z2;
        }

        public void log(String str) {
            this.ajA.zza(this.mPriority, this.ajB, this.ajC, str, null, null, null);
        }

        public void zzd(String str, Object obj, Object obj2, Object obj3) {
            this.ajA.zza(this.mPriority, this.ajB, this.ajC, str, obj, obj2, obj3);
        }

        public void zze(String str, Object obj, Object obj2) {
            this.ajA.zza(this.mPriority, this.ajB, this.ajC, str, obj, obj2, null);
        }

        public void zzj(String str, Object obj) {
            this.ajA.zza(this.mPriority, this.ajB, this.ajC, str, obj, null, null);
        }
    }

    zzp(zzx com_google_android_gms_measurement_internal_zzx) {
        super(com_google_android_gms_measurement_internal_zzx);
        if (zzbsf().zzabd()) {
            this.ajp = zzbsf().zzabc() ? 'P' : 'C';
        } else {
            this.ajp = zzbsf().zzabc() ? 'p' : 'c';
        }
        this.ajq = new zza(this, 6, false, false);
        this.ajr = new zza(this, 6, true, false);
        this.ajs = new zza(this, 6, false, true);
        this.ajt = new zza(this, 5, false, false);
        this.aju = new zza(this, 5, true, false);
        this.ajv = new zza(this, 5, false, true);
        this.ajw = new zza(this, 4, false, false);
        this.ajx = new zza(this, 3, false, false);
        this.ajy = new zza(this, 2, false, false);
    }

    static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            Object obj4 = "";
        }
        Object zzc = zzc(z, obj);
        Object zzc2 = zzc(z, obj2);
        Object zzc3 = zzc(z, obj3);
        StringBuilder stringBuilder = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(obj4)) {
            stringBuilder.append(obj4);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(zzc)) {
            stringBuilder.append(str2);
            stringBuilder.append(zzc);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzc2)) {
            stringBuilder.append(str2);
            stringBuilder.append(zzc2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzc3)) {
            stringBuilder.append(str2);
            stringBuilder.append(zzc3);
        }
        return stringBuilder.toString();
    }

    static String zzc(boolean z, Object obj) {
        if (obj == null) {
            return "";
        }
        Object valueOf = obj instanceof Integer ? Long.valueOf((long) ((Integer) obj).intValue()) : obj;
        if (valueOf instanceof Long) {
            if (!z) {
                return String.valueOf(valueOf);
            }
            if (Math.abs(((Long) valueOf).longValue()) < 100) {
                return String.valueOf(valueOf);
            }
            String str = String.valueOf(valueOf).charAt(0) == '-' ? "-" : "";
            String valueOf2 = String.valueOf(Math.abs(((Long) valueOf).longValue()));
            return new StringBuilder((String.valueOf(str).length() + 43) + String.valueOf(str).length()).append(str).append(Math.round(Math.pow(10.0d, (double) (valueOf2.length() - 1)))).append("...").append(str).append(Math.round(Math.pow(10.0d, (double) valueOf2.length()) - 1.0d)).toString();
        } else if (valueOf instanceof Boolean) {
            return String.valueOf(valueOf);
        } else {
            if (!(valueOf instanceof Throwable)) {
                return z ? "-" : String.valueOf(valueOf);
            } else {
                Throwable th = (Throwable) valueOf;
                StringBuilder stringBuilder = new StringBuilder(z ? th.getClass().getName() : th.toString());
                String zzlw = zzlw(AppMeasurement.class.getCanonicalName());
                String zzlw2 = zzlw(zzx.class.getCanonicalName());
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    if (!stackTraceElement.isNativeMethod()) {
                        String className = stackTraceElement.getClassName();
                        if (className != null) {
                            className = zzlw(className);
                            if (className.equals(zzlw) || className.equals(zzlw2)) {
                                stringBuilder.append(": ");
                                stringBuilder.append(stackTraceElement);
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                }
                return stringBuilder.toString();
            }
        }
    }

    private static String zzlw(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf != -1 ? str.substring(0, lastIndexOf) : str;
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    protected void zza(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && zzaz(i)) {
            zzo(i, zza(false, str, obj, obj2, obj3));
        }
        if (!z2 && i >= 5) {
            zzb(i, str, obj, obj2, obj3);
        }
    }

    protected boolean zzaz(int i) {
        return Log.isLoggable(this.zc, i);
    }

    public void zzb(int i, String str, Object obj, Object obj2, Object obj3) {
        zzab.zzy(str);
        zzw zzbtq = this.ahD.zzbtq();
        if (zzbtq == null) {
            zzo(6, "Scheduler not set. Not logging error/warn.");
        } else if (!zzbtq.isInitialized()) {
            zzo(6, "Scheduler not initialized. Not logging error/warn.");
        } else if (zzbtq.zzbul()) {
            zzo(6, "Scheduler shutdown. Not logging error/warn.");
        } else {
            if (i < 0) {
                i = 0;
            }
            if (i >= "01VDIWEA?".length()) {
                i = "01VDIWEA?".length() - 1;
            }
            String valueOf = String.valueOf(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            char charAt = "01VDIWEA?".charAt(i);
            char c = this.ajp;
            long j = this.ahR;
            String valueOf2 = String.valueOf(zza(true, str, obj, obj2, obj3));
            valueOf = new StringBuilder((String.valueOf(valueOf).length() + 23) + String.valueOf(valueOf2).length()).append(valueOf).append(charAt).append(c).append(j).append(":").append(valueOf2).toString();
            if (valueOf.length() > 1024) {
                valueOf = str.substring(0, 1024);
            }
            zzbtq.zzm(new Runnable(this) {
                final /* synthetic */ zzp ajA;

                public void run() {
                    zzt zzbse = this.ajA.ahD.zzbse();
                    if (!zzbse.isInitialized() || zzbse.zzbul()) {
                        this.ajA.zzo(6, "Persisted config not initialized . Not logging error/warn.");
                    } else {
                        zzbse.ajX.zzev(valueOf);
                    }
                }
            });
        }
    }

    public /* bridge */ /* synthetic */ void zzbrs() {
        super.zzbrs();
    }

    public /* bridge */ /* synthetic */ zzc zzbrt() {
        return super.zzbrt();
    }

    public /* bridge */ /* synthetic */ zzac zzbru() {
        return super.zzbru();
    }

    public /* bridge */ /* synthetic */ zzn zzbrv() {
        return super.zzbrv();
    }

    public /* bridge */ /* synthetic */ zzg zzbrw() {
        return super.zzbrw();
    }

    public /* bridge */ /* synthetic */ zzad zzbrx() {
        return super.zzbrx();
    }

    public /* bridge */ /* synthetic */ zze zzbry() {
        return super.zzbry();
    }

    public /* bridge */ /* synthetic */ zzal zzbrz() {
        return super.zzbrz();
    }

    public /* bridge */ /* synthetic */ zzv zzbsa() {
        return super.zzbsa();
    }

    public /* bridge */ /* synthetic */ zzaf zzbsb() {
        return super.zzbsb();
    }

    public /* bridge */ /* synthetic */ zzw zzbsc() {
        return super.zzbsc();
    }

    public /* bridge */ /* synthetic */ zzp zzbsd() {
        return super.zzbsd();
    }

    public /* bridge */ /* synthetic */ zzt zzbse() {
        return super.zzbse();
    }

    public /* bridge */ /* synthetic */ zzd zzbsf() {
        return super.zzbsf();
    }

    public zza zzbsv() {
        return this.ajq;
    }

    public zza zzbsw() {
        return this.ajr;
    }

    public zza zzbsx() {
        return this.ajt;
    }

    public zza zzbsy() {
        return this.aju;
    }

    public zza zzbsz() {
        return this.ajv;
    }

    public zza zzbta() {
        return this.ajw;
    }

    public zza zzbtb() {
        return this.ajx;
    }

    public zza zzbtc() {
        return this.ajy;
    }

    public String zzbtd() {
        Pair zzadv = zzbse().ajX.zzadv();
        if (zzadv == null || zzadv == zzt.ajW) {
            return null;
        }
        String valueOf = String.valueOf(String.valueOf(zzadv.second));
        String str = (String) zzadv.first;
        return new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(str).length()).append(valueOf).append(":").append(str).toString();
    }

    protected void zzo(int i, String str) {
        Log.println(i, this.zc, str);
    }

    public /* bridge */ /* synthetic */ void zzwu() {
        super.zzwu();
    }

    protected void zzwv() {
    }

    public /* bridge */ /* synthetic */ void zzyv() {
        super.zzyv();
    }

    public /* bridge */ /* synthetic */ zze zzyw() {
        return super.zzyw();
    }
}
