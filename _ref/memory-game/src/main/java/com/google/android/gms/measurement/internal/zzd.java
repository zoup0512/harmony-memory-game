package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri.Builder;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.zzqf;
import com.google.android.gms.measurement.internal.zzl.zza;
import io.fabric.sdk.android.services.common.AbstractSpiCall;

public class zzd extends zzz {
    static final String ail = String.valueOf(zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000).replaceAll("(\\d+)(\\d)(\\d\\d)", "$1.$2.$3");
    private Boolean zzczb;

    zzd(zzx com_google_android_gms_measurement_internal_zzx) {
        super(com_google_android_gms_measurement_internal_zzx);
    }

    @Nullable
    private Boolean zzli(@Size(min = 1) String str) {
        Boolean bool = null;
        zzab.zzhr(str);
        try {
            PackageManager packageManager = getContext().getPackageManager();
            if (packageManager == null) {
                zzbsd().zzbsv().log("Failed to load metadata: PackageManager is null");
            } else {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getContext().getPackageName(), 128);
                if (applicationInfo == null) {
                    zzbsd().zzbsv().log("Failed to load metadata: ApplicationInfo is null");
                } else if (applicationInfo.metaData == null) {
                    zzbsd().zzbsv().log("Failed to load metadata: Metadata bundle is null");
                } else if (applicationInfo.metaData.containsKey(str)) {
                    bool = Boolean.valueOf(applicationInfo.metaData.getBoolean(str));
                }
            }
        } catch (NameNotFoundException e) {
            zzbsd().zzbsv().zzj("Failed to load metadata: Package name not found", e);
        }
        return bool;
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public long zza(String str, zza<Long> com_google_android_gms_measurement_internal_zzl_zza_java_lang_Long) {
        if (str == null) {
            return ((Long) com_google_android_gms_measurement_internal_zzl_zza_java_lang_Long.get()).longValue();
        }
        Object zzaw = zzbsa().zzaw(str, com_google_android_gms_measurement_internal_zzl_zza_java_lang_Long.getKey());
        if (TextUtils.isEmpty(zzaw)) {
            return ((Long) com_google_android_gms_measurement_internal_zzl_zza_java_lang_Long.get()).longValue();
        }
        try {
            return ((Long) com_google_android_gms_measurement_internal_zzl_zza_java_lang_Long.get(Long.valueOf(Long.valueOf(zzaw).longValue()))).longValue();
        } catch (NumberFormatException e) {
            return ((Long) com_google_android_gms_measurement_internal_zzl_zza_java_lang_Long.get()).longValue();
        }
    }

    public boolean zzabc() {
        return false;
    }

    public boolean zzabd() {
        if (this.zzczb == null) {
            synchronized (this) {
                if (this.zzczb == null) {
                    ApplicationInfo applicationInfo = getContext().getApplicationInfo();
                    String zzawa = zzt.zzawa();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        boolean z = str != null && str.equals(zzawa);
                        this.zzczb = Boolean.valueOf(z);
                    }
                    if (this.zzczb == null) {
                        this.zzczb = Boolean.TRUE;
                        zzbsd().zzbsv().log("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzczb.booleanValue();
    }

    long zzabx() {
        return ((Long) zzl.ajl.get()).longValue();
    }

    public String zzacc() {
        return "google_app_measurement.db";
    }

    public String zzacd() {
        return "google_app_measurement2.db";
    }

    public long zzaci() {
        return Math.max(0, ((Long) zzl.aiP.get()).longValue());
    }

    public String zzap(String str, String str2) {
        Builder builder = new Builder();
        Builder encodedAuthority = builder.scheme((String) zzl.aiR.get()).encodedAuthority((String) zzl.aiS.get());
        String str3 = "config/app/";
        String valueOf = String.valueOf(str);
        encodedAuthority.path(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3)).appendQueryParameter("app_instance_id", str2).appendQueryParameter("platform", AbstractSpiCall.ANDROID_CLIENT_TYPE).appendQueryParameter("gmp_version", String.valueOf(zzbpz()));
        return builder.build().toString();
    }

    public boolean zzaqp() {
        return zzqf.zzaqp();
    }

    public int zzb(String str, zza<Integer> com_google_android_gms_measurement_internal_zzl_zza_java_lang_Integer) {
        if (str == null) {
            return ((Integer) com_google_android_gms_measurement_internal_zzl_zza_java_lang_Integer.get()).intValue();
        }
        Object zzaw = zzbsa().zzaw(str, com_google_android_gms_measurement_internal_zzl_zza_java_lang_Integer.getKey());
        if (TextUtils.isEmpty(zzaw)) {
            return ((Integer) com_google_android_gms_measurement_internal_zzl_zza_java_lang_Integer.get()).intValue();
        }
        try {
            return ((Integer) com_google_android_gms_measurement_internal_zzl_zza_java_lang_Integer.get(Integer.valueOf(Integer.valueOf(zzaw).intValue()))).intValue();
        } catch (NumberFormatException e) {
            return ((Integer) com_google_android_gms_measurement_internal_zzl_zza_java_lang_Integer.get()).intValue();
        }
    }

    public long zzbpz() {
        return 9452;
    }

    String zzbql() {
        return (String) zzl.aiN.get();
    }

    public int zzbqm() {
        return 25;
    }

    public int zzbqn() {
        return 32;
    }

    public int zzbqo() {
        return 24;
    }

    int zzbqp() {
        return 24;
    }

    int zzbqq() {
        return 36;
    }

    int zzbqr() {
        return 256;
    }

    public int zzbqs() {
        return 36;
    }

    public int zzbqt() {
        return 2048;
    }

    int zzbqu() {
        return 500;
    }

    public long zzbqv() {
        return (long) ((Integer) zzl.aiX.get()).intValue();
    }

    public long zzbqw() {
        return (long) ((Integer) zzl.aiY.get()).intValue();
    }

    public long zzbqx() {
        return 1000;
    }

    int zzbqy() {
        return 25;
    }

    int zzbqz() {
        return 50;
    }

    long zzbra() {
        return 3600000;
    }

    long zzbrb() {
        return 60000;
    }

    long zzbrc() {
        return 61000;
    }

    public boolean zzbrd() {
        if (zzabc()) {
            return false;
        }
        Boolean zzli = zzli("firebase_analytics_collection_deactivated");
        return (zzli == null || zzli.booleanValue()) ? false : true;
    }

    public Boolean zzbre() {
        return zzabc() ? null : zzli("firebase_analytics_collection_enabled");
    }

    public long zzbrf() {
        return ((Long) zzl.ajj.get()).longValue();
    }

    public long zzbrg() {
        return ((Long) zzl.ajf.get()).longValue();
    }

    public long zzbrh() {
        return 1000;
    }

    public int zzbri() {
        return Math.max(0, ((Integer) zzl.aiV.get()).intValue());
    }

    public int zzbrj() {
        return Math.max(1, ((Integer) zzl.aiW.get()).intValue());
    }

    public String zzbrk() {
        return (String) zzl.ajb.get();
    }

    public long zzbrl() {
        return ((Long) zzl.aiQ.get()).longValue();
    }

    public long zzbrm() {
        return Math.max(0, ((Long) zzl.ajc.get()).longValue());
    }

    public long zzbrn() {
        return Math.max(0, ((Long) zzl.aje.get()).longValue());
    }

    public long zzbro() {
        return ((Long) zzl.ajd.get()).longValue();
    }

    public long zzbrp() {
        return Math.max(0, ((Long) zzl.ajg.get()).longValue());
    }

    public long zzbrq() {
        return Math.max(0, ((Long) zzl.ajh.get()).longValue());
    }

    public int zzbrr() {
        return Math.min(20, Math.max(0, ((Integer) zzl.aji.get()).intValue()));
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

    public int zzlf(@Size(min = 1) String str) {
        return zzb(str, zzl.aiZ);
    }

    long zzlg(String str) {
        return zza(str, zzl.aiO);
    }

    int zzlh(String str) {
        return zzb(str, zzl.ajk);
    }

    public int zzlj(String str) {
        return zzb(str, zzl.aiT);
    }

    public int zzlk(String str) {
        return Math.max(0, zzb(str, zzl.aiU));
    }

    public int zzll(String str) {
        return Math.max(0, Math.min(1000000, zzb(str, zzl.aja)));
    }

    public /* bridge */ /* synthetic */ void zzwu() {
        super.zzwu();
    }

    public /* bridge */ /* synthetic */ void zzyv() {
        super.zzyv();
    }

    public /* bridge */ /* synthetic */ zze zzyw() {
        return super.zzyw();
    }
}
