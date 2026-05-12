package com.google.android.gms.measurement.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;

public class zzai extends zzaa {
    private boolean zzczj;
    private final AlarmManager zzczk = ((AlarmManager) getContext().getSystemService("alarm"));

    protected zzai(zzx com_google_android_gms_measurement_internal_zzx) {
        super(com_google_android_gms_measurement_internal_zzx);
    }

    private PendingIntent zzacn() {
        Intent className = new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementReceiver");
        className.setAction("com.google.android.gms.measurement.UPLOAD");
        return PendingIntent.getBroadcast(getContext(), 0, className, 0);
    }

    public void cancel() {
        zzzg();
        this.zzczj = false;
        this.zzczk.cancel(zzacn());
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
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

    public void zzv(long j) {
        zzzg();
        zzab.zzbo(j > 0);
        zzab.zza(zzu.zzav(getContext()), (Object) "Receiver not registered/enabled");
        zzab.zza(zzae.zzaw(getContext()), (Object) "Service not registered/enabled");
        cancel();
        long elapsedRealtime = zzyw().elapsedRealtime() + j;
        this.zzczj = true;
        this.zzczk.setInexactRepeating(2, elapsedRealtime, Math.max(zzbsf().zzbro(), j), zzacn());
    }

    public /* bridge */ /* synthetic */ void zzwu() {
        super.zzwu();
    }

    protected void zzwv() {
        this.zzczk.cancel(zzacn());
    }

    public /* bridge */ /* synthetic */ void zzyv() {
        super.zzyv();
    }

    public /* bridge */ /* synthetic */ zze zzyw() {
        return super.zzyw();
    }
}
