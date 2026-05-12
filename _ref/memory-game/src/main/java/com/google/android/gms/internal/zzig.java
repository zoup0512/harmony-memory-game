package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.internal.zzic.zza;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzin
public class zzig extends zzkc {
    private final Object zzail;
    private final zza zzbxq;
    private final zzju.zza zzbxr;
    private final AdResponseParcel zzbxs;
    private final zzii zzbyq;
    private Future<zzju> zzbyr;

    public zzig(Context context, zzq com_google_android_gms_ads_internal_zzq, zzju.zza com_google_android_gms_internal_zzju_zza, zzas com_google_android_gms_internal_zzas, zza com_google_android_gms_internal_zzic_zza) {
        this(com_google_android_gms_internal_zzju_zza, com_google_android_gms_internal_zzic_zza, new zzii(context, com_google_android_gms_ads_internal_zzq, new zzkn(context), com_google_android_gms_internal_zzas, com_google_android_gms_internal_zzju_zza));
    }

    zzig(zzju.zza com_google_android_gms_internal_zzju_zza, zza com_google_android_gms_internal_zzic_zza, zzii com_google_android_gms_internal_zzii) {
        this.zzail = new Object();
        this.zzbxr = com_google_android_gms_internal_zzju_zza;
        this.zzbxs = com_google_android_gms_internal_zzju_zza.zzciq;
        this.zzbxq = com_google_android_gms_internal_zzic_zza;
        this.zzbyq = com_google_android_gms_internal_zzii;
    }

    private zzju zzam(int i) {
        return new zzju(this.zzbxr.zzcip.zzcar, null, null, i, null, null, this.zzbxs.orientation, this.zzbxs.zzbns, this.zzbxr.zzcip.zzcau, false, null, null, null, null, null, this.zzbxs.zzcbz, this.zzbxr.zzapa, this.zzbxs.zzcbx, this.zzbxr.zzcik, this.zzbxs.zzccc, this.zzbxs.zzccd, this.zzbxr.zzcie, null, null, null, null, this.zzbxr.zzciq.zzccq, this.zzbxr.zzciq.zzccr, null, null);
    }

    public void onStop() {
        synchronized (this.zzail) {
            if (this.zzbyr != null) {
                this.zzbyr.cancel(true);
            }
        }
    }

    public void zzew() {
        zzju com_google_android_gms_internal_zzju;
        int i;
        try {
            synchronized (this.zzail) {
                this.zzbyr = zzkg.zza(this.zzbyq);
            }
            com_google_android_gms_internal_zzju = (zzju) this.zzbyr.get(60000, TimeUnit.MILLISECONDS);
            i = -2;
        } catch (TimeoutException e) {
            zzb.zzcx("Timed out waiting for native ad.");
            this.zzbyr.cancel(true);
            i = 2;
            com_google_android_gms_internal_zzju = null;
        } catch (ExecutionException e2) {
            com_google_android_gms_internal_zzju = null;
            i = 0;
        } catch (InterruptedException e3) {
            com_google_android_gms_internal_zzju = null;
            i = 0;
        } catch (CancellationException e4) {
            com_google_android_gms_internal_zzju = null;
            i = 0;
        }
        if (com_google_android_gms_internal_zzju == null) {
            com_google_android_gms_internal_zzju = zzam(i);
        }
        zzkh.zzclc.post(new 1(this, com_google_android_gms_internal_zzju));
    }
}
