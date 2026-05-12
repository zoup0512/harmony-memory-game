package com.google.android.gms.internal;

import android.content.Context;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzic.zza;

@zzin
public abstract class zzib extends zzkc {
    protected final Context mContext;
    protected final Object zzail = new Object();
    protected final zza zzbxq;
    protected final zzju.zza zzbxr;
    protected AdResponseParcel zzbxs;
    protected final Object zzbxu = new Object();

    protected zzib(Context context, zzju.zza com_google_android_gms_internal_zzju_zza, zza com_google_android_gms_internal_zzic_zza) {
        super(true);
        this.mContext = context;
        this.zzbxr = com_google_android_gms_internal_zzju_zza;
        this.zzbxs = com_google_android_gms_internal_zzju_zza.zzciq;
        this.zzbxq = com_google_android_gms_internal_zzic_zza;
    }

    public void onStop() {
    }

    protected abstract zzju zzak(int i);

    public void zzew() {
        int errorCode;
        synchronized (this.zzail) {
            zzb.zzcv("AdRendererBackgroundTask started.");
            int i = this.zzbxr.errorCode;
            try {
                zzh(SystemClock.elapsedRealtime());
            } catch (zza e) {
                errorCode = e.getErrorCode();
                if (errorCode == 3 || errorCode == -1) {
                    zzb.zzcw(e.getMessage());
                } else {
                    zzb.zzcx(e.getMessage());
                }
                if (this.zzbxs == null) {
                    this.zzbxs = new AdResponseParcel(errorCode);
                } else {
                    this.zzbxs = new AdResponseParcel(errorCode, this.zzbxs.zzbns);
                }
                zzkh.zzclc.post(new 1(this));
                i = errorCode;
            }
            zzkh.zzclc.post(new 2(this, zzak(i)));
        }
    }

    protected abstract void zzh(long j) throws zza;

    protected void zzm(zzju com_google_android_gms_internal_zzju) {
        this.zzbxq.zzb(com_google_android_gms_internal_zzju);
    }
}
