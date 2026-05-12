package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzah.zzj;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class zzct implements zze {
    private final String auF;
    private String avc;
    private zzbm<zzj> axf;
    private zzs axg;
    private final zza axi;
    private ScheduledFuture<?> axj;
    private boolean mClosed;
    private final Context mContext;
    private final ScheduledExecutorService qF;

    interface zzb {
        ScheduledExecutorService zzccp();
    }

    interface zza {
        zzcs zza(zzs com_google_android_gms_tagmanager_zzs);
    }

    public zzct(Context context, String str, zzs com_google_android_gms_tagmanager_zzs) {
        this(context, str, com_google_android_gms_tagmanager_zzs, null, null);
    }

    zzct(Context context, String str, zzs com_google_android_gms_tagmanager_zzs, zzb com_google_android_gms_tagmanager_zzct_zzb, zza com_google_android_gms_tagmanager_zzct_zza) {
        this.axg = com_google_android_gms_tagmanager_zzs;
        this.mContext = context;
        this.auF = str;
        if (com_google_android_gms_tagmanager_zzct_zzb == null) {
            com_google_android_gms_tagmanager_zzct_zzb = new zzb(this) {
                final /* synthetic */ zzct axk;

                {
                    this.axk = r1;
                }

                public ScheduledExecutorService zzccp() {
                    return Executors.newSingleThreadScheduledExecutor();
                }
            };
        }
        this.qF = com_google_android_gms_tagmanager_zzct_zzb.zzccp();
        if (com_google_android_gms_tagmanager_zzct_zza == null) {
            this.axi = new zza(this) {
                final /* synthetic */ zzct axk;

                {
                    this.axk = r1;
                }

                public zzcs zza(zzs com_google_android_gms_tagmanager_zzs) {
                    return new zzcs(this.axk.mContext, this.axk.auF, com_google_android_gms_tagmanager_zzs);
                }
            };
        } else {
            this.axi = com_google_android_gms_tagmanager_zzct_zza;
        }
    }

    private synchronized void zzcco() {
        if (this.mClosed) {
            throw new IllegalStateException("called method after closed");
        }
    }

    private zzcs zzpb(String str) {
        zzcs zza = this.axi.zza(this.axg);
        zza.zza(this.axf);
        zza.zzol(this.avc);
        zza.zzpa(str);
        return zza;
    }

    public synchronized void release() {
        zzcco();
        if (this.axj != null) {
            this.axj.cancel(false);
        }
        this.qF.shutdown();
        this.mClosed = true;
    }

    public synchronized void zza(zzbm<zzj> com_google_android_gms_tagmanager_zzbm_com_google_android_gms_internal_zzah_zzj) {
        zzcco();
        this.axf = com_google_android_gms_tagmanager_zzbm_com_google_android_gms_internal_zzah_zzj;
    }

    public synchronized void zzf(long j, String str) {
        String str2 = this.auF;
        zzbn.v(new StringBuilder(String.valueOf(str2).length() + 55).append("loadAfterDelay: containerId=").append(str2).append(" delay=").append(j).toString());
        zzcco();
        if (this.axf == null) {
            throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
        }
        if (this.axj != null) {
            this.axj.cancel(false);
        }
        this.axj = this.qF.schedule(zzpb(str), j, TimeUnit.MILLISECONDS);
    }

    public synchronized void zzol(String str) {
        zzcco();
        this.avc = str;
    }
}
