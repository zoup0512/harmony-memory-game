package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzju.zza;

@zzin
public class zzjg extends zzkc implements zzjh, zzjk {
    private final Context mContext;
    private final Object zzail;
    private final String zzboc;
    private final zza zzbxr;
    private int zzbyi = 3;
    private final zzjm zzchm;
    private final zzjk zzchn;
    private final String zzcho;
    private final String zzchp;
    private int zzchq = 0;

    public zzjg(Context context, String str, String str2, String str3, zza com_google_android_gms_internal_zzju_zza, zzjm com_google_android_gms_internal_zzjm, zzjk com_google_android_gms_internal_zzjk) {
        this.mContext = context;
        this.zzboc = str;
        this.zzcho = str2;
        this.zzchp = str3;
        this.zzbxr = com_google_android_gms_internal_zzju_zza;
        this.zzchm = com_google_android_gms_internal_zzjm;
        this.zzail = new Object();
        this.zzchn = com_google_android_gms_internal_zzjk;
    }

    private void zza(AdRequestParcel adRequestParcel, zzgk com_google_android_gms_internal_zzgk) {
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzboc)) {
                com_google_android_gms_internal_zzgk.zza(adRequestParcel, this.zzcho, this.zzchp);
            } else {
                com_google_android_gms_internal_zzgk.zzc(adRequestParcel, this.zzcho);
            }
        } catch (Throwable e) {
            zzb.zzd("Fail to load ad from adapter.", e);
            zza(this.zzboc, 0);
        }
    }

    private void zzk(long j) {
        while (true) {
            synchronized (this.zzail) {
                if (this.zzchq != 0) {
                    return;
                } else if (!zzf(j)) {
                    return;
                }
            }
        }
    }

    public void onStop() {
    }

    public void zza(String str, int i) {
        synchronized (this.zzail) {
            this.zzchq = 2;
            this.zzbyi = i;
            this.zzail.notify();
        }
    }

    public void zzaw(int i) {
        zza(this.zzboc, 0);
    }

    public void zzcg(String str) {
        synchronized (this.zzail) {
            this.zzchq = 1;
            this.zzail.notify();
        }
    }

    public void zzew() {
        if (this.zzchm != null && this.zzchm.zzrv() != null && this.zzchm.zzru() != null) {
            zzjj zzrv = this.zzchm.zzrv();
            zzrv.zza((zzjk) this);
            zzrv.zza((zzjh) this);
            AdRequestParcel adRequestParcel = this.zzbxr.zzcip.zzcar;
            zzgk zzru = this.zzchm.zzru();
            try {
                if (zzru.isInitialized()) {
                    com.google.android.gms.ads.internal.util.client.zza.zzcnb.post(new 1(this, adRequestParcel, zzru));
                } else {
                    com.google.android.gms.ads.internal.util.client.zza.zzcnb.post(new 2(this, zzru, adRequestParcel, zzrv));
                }
            } catch (Throwable e) {
                zzb.zzd("Fail to check if adapter is initialized.", e);
                zza(this.zzboc, 0);
            }
            zzk(zzu.zzfu().elapsedRealtime());
            zzrv.zza(null);
            zzrv.zza(null);
            if (this.zzchq == 1) {
                this.zzchn.zzcg(this.zzboc);
            } else {
                this.zzchn.zza(this.zzboc, this.zzbyi);
            }
        }
    }

    protected boolean zzf(long j) {
        long elapsedRealtime = 20000 - (zzu.zzfu().elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            return false;
        }
        try {
            this.zzail.wait(elapsedRealtime);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }

    public void zzrs() {
        zza(this.zzbxr.zzcip.zzcar, this.zzchm.zzru());
    }
}
