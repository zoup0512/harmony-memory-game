package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.internal.zzfs.zzb;
import com.google.android.gms.internal.zzfs.zze;
import com.google.android.gms.internal.zzju.zza;
import com.mopub.common.Constants;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzin
public class zzih {
    private static final Object zzamr = new Object();
    private static final long zzbyt = TimeUnit.SECONDS.toMillis(60);
    private static boolean zzbyu = false;
    private static zzfs zzbyv = null;
    private final Context mContext;
    private final zzq zzbfx;
    private final zzas zzbgd;
    private final zza zzbxr;
    private zzfq zzbyw;
    private zze zzbyx;
    private zzfp zzbyy;
    private boolean zzbyz = false;

    public zzih(Context context, zza com_google_android_gms_internal_zzju_zza, zzq com_google_android_gms_ads_internal_zzq, zzas com_google_android_gms_internal_zzas) {
        this.mContext = context;
        this.zzbxr = com_google_android_gms_internal_zzju_zza;
        this.zzbfx = com_google_android_gms_ads_internal_zzq;
        this.zzbgd = com_google_android_gms_internal_zzas;
        this.zzbyz = ((Boolean) zzdc.zzbcf.get()).booleanValue();
    }

    private String zzd(zza com_google_android_gms_internal_zzju_zza) {
        String str = (String) zzdc.zzbac.get();
        String valueOf = String.valueOf(com_google_android_gms_internal_zzju_zza.zzciq.zzbto.indexOf(Constants.HTTPS) == 0 ? "https:" : "http:");
        str = String.valueOf(str);
        return str.length() != 0 ? valueOf.concat(str) : new String(valueOf);
    }

    private void zzqi() {
        synchronized (zzamr) {
            if (!zzbyu) {
                zzbyv = new zzfs(this.mContext.getApplicationContext() != null ? this.mContext.getApplicationContext() : this.mContext, this.zzbxr.zzcip.zzaow, zzd(this.zzbxr), new 3(this), new zzb());
                zzbyu = true;
            }
        }
    }

    private void zzqj() {
        this.zzbyx = new zze(zzqo().zzc(this.zzbgd));
    }

    private void zzqk() {
        this.zzbyw = new zzfq();
    }

    private void zzql() throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        this.zzbyy = (zzfp) zzqm().zza(this.mContext, this.zzbxr.zzcip.zzaow, zzd(this.zzbxr), this.zzbgd).get(zzbyt, TimeUnit.MILLISECONDS);
        this.zzbyy.zza(this.zzbfx, this.zzbfx, this.zzbfx, this.zzbfx, false, null, null, null, null);
    }

    public void zza(zza com_google_android_gms_internal_zzih_zza) {
        if (this.zzbyz) {
            zze zzqp = zzqp();
            if (zzqp == null) {
                com.google.android.gms.ads.internal.util.client.zzb.zzcx("SharedJavascriptEngine not initialized");
                return;
            } else {
                zzqp.zza(new 1(this, com_google_android_gms_internal_zzih_zza), new 2(this, com_google_android_gms_internal_zzih_zza));
                return;
            }
        }
        zzft zzqn = zzqn();
        if (zzqn == null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcx("JavascriptEngine not initialized");
        } else {
            com_google_android_gms_internal_zzih_zza.zze(zzqn);
        }
    }

    public void zzqg() {
        if (this.zzbyz) {
            zzqi();
        } else {
            zzqk();
        }
    }

    public void zzqh() throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        if (this.zzbyz) {
            zzqj();
        } else {
            zzql();
        }
    }

    protected zzfq zzqm() {
        return this.zzbyw;
    }

    protected zzfp zzqn() {
        return this.zzbyy;
    }

    protected zzfs zzqo() {
        return zzbyv;
    }

    protected zze zzqp() {
        return this.zzbyx;
    }
}
