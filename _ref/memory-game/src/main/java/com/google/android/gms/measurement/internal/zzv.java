package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.internal.zzapn;
import com.google.android.gms.internal.zzapo;
import com.google.android.gms.internal.zzug.zza;
import com.google.android.gms.internal.zzug.zzb;
import com.google.android.gms.internal.zzug.zzc;
import com.google.android.gms.measurement.AppMeasurement$zza;
import java.io.IOException;
import java.util.Map;

public class zzv extends zzaa {
    private final Map<String, Map<String, String>> aku = new ArrayMap();
    private final Map<String, Map<String, Boolean>> akv = new ArrayMap();
    private final Map<String, Map<String, Boolean>> akw = new ArrayMap();
    private final Map<String, zzb> akx = new ArrayMap();
    private final Map<String, String> aky = new ArrayMap();

    zzv(zzx com_google_android_gms_measurement_internal_zzx) {
        super(com_google_android_gms_measurement_internal_zzx);
    }

    private Map<String, String> zza(zzb com_google_android_gms_internal_zzug_zzb) {
        Map<String, String> arrayMap = new ArrayMap();
        if (!(com_google_android_gms_internal_zzug_zzb == null || com_google_android_gms_internal_zzug_zzb.ane == null)) {
            for (zzc com_google_android_gms_internal_zzug_zzc : com_google_android_gms_internal_zzug_zzb.ane) {
                if (com_google_android_gms_internal_zzug_zzc != null) {
                    arrayMap.put(com_google_android_gms_internal_zzug_zzc.zzcb, com_google_android_gms_internal_zzug_zzc.value);
                }
            }
        }
        return arrayMap;
    }

    private void zza(String str, zzb com_google_android_gms_internal_zzug_zzb) {
        Map arrayMap = new ArrayMap();
        Map arrayMap2 = new ArrayMap();
        if (!(com_google_android_gms_internal_zzug_zzb == null || com_google_android_gms_internal_zzug_zzb.anf == null)) {
            for (zza com_google_android_gms_internal_zzug_zza : com_google_android_gms_internal_zzug_zzb.anf) {
                if (com_google_android_gms_internal_zzug_zza != null) {
                    String str2 = (String) AppMeasurement$zza.ahE.get(com_google_android_gms_internal_zzug_zza.name);
                    if (str2 != null) {
                        com_google_android_gms_internal_zzug_zza.name = str2;
                    }
                    arrayMap.put(com_google_android_gms_internal_zzug_zza.name, com_google_android_gms_internal_zzug_zza.ana);
                    arrayMap2.put(com_google_android_gms_internal_zzug_zza.name, com_google_android_gms_internal_zzug_zza.anb);
                }
            }
        }
        this.akv.put(str, arrayMap);
        this.akw.put(str, arrayMap2);
    }

    @WorkerThread
    private zzb zze(String str, byte[] bArr) {
        if (bArr == null) {
            return new zzb();
        }
        zzapn zzbd = zzapn.zzbd(bArr);
        zzb com_google_android_gms_internal_zzug_zzb = new zzb();
        try {
            zzb com_google_android_gms_internal_zzug_zzb2 = (zzb) com_google_android_gms_internal_zzug_zzb.zzb(zzbd);
            zzbsd().zzbtc().zze("Parsed config. version, gmp_app_id", com_google_android_gms_internal_zzug_zzb.anc, com_google_android_gms_internal_zzug_zzb.aic);
            return com_google_android_gms_internal_zzug_zzb;
        } catch (IOException e) {
            zzbsd().zzbsx().zze("Unable to merge remote config", str, e);
            return null;
        }
    }

    @WorkerThread
    private void zzma(String str) {
        zzzg();
        zzwu();
        zzab.zzhr(str);
        if (!this.akx.containsKey(str)) {
            byte[] zzlp = zzbry().zzlp(str);
            if (zzlp == null) {
                this.aku.put(str, null);
                this.akv.put(str, null);
                this.akw.put(str, null);
                this.akx.put(str, null);
                this.aky.put(str, null);
                return;
            }
            zzb zze = zze(str, zzlp);
            this.aku.put(str, zza(zze));
            zza(str, zze);
            this.akx.put(str, zze);
            this.aky.put(str, null);
        }
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    String zzaw(String str, String str2) {
        zzwu();
        zzma(str);
        Map map = (Map) this.aku.get(str);
        return map != null ? (String) map.get(str2) : null;
    }

    @WorkerThread
    boolean zzax(String str, String str2) {
        zzwu();
        zzma(str);
        Map map = (Map) this.akv.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        return bool == null ? false : bool.booleanValue();
    }

    @WorkerThread
    boolean zzay(String str, String str2) {
        zzwu();
        zzma(str);
        Map map = (Map) this.akw.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        return bool == null ? false : bool.booleanValue();
    }

    @WorkerThread
    protected boolean zzb(String str, byte[] bArr, String str2) {
        zzzg();
        zzwu();
        zzab.zzhr(str);
        zzb zze = zze(str, bArr);
        if (zze == null) {
            return false;
        }
        zza(str, zze);
        this.akx.put(str, zze);
        this.aky.put(str, str2);
        this.aku.put(str, zza(zze));
        zzbrt().zza(str, zze.ang);
        try {
            zze.ang = null;
            byte[] bArr2 = new byte[zze.aM()];
            zze.zza(zzapo.zzbe(bArr2));
            bArr = bArr2;
        } catch (IOException e) {
            zzbsd().zzbsx().zzj("Unable to serialize reduced-size config.  Storing full config instead.", e);
        }
        zzbry().zzd(str, bArr);
        return true;
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

    @WorkerThread
    protected zzb zzmb(String str) {
        zzzg();
        zzwu();
        zzab.zzhr(str);
        zzma(str);
        return (zzb) this.akx.get(str);
    }

    @WorkerThread
    protected String zzmc(String str) {
        zzwu();
        return (String) this.aky.get(str);
    }

    @WorkerThread
    protected void zzmd(String str) {
        zzwu();
        this.aky.put(str, null);
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
