package com.google.android.gms.ads.internal;

import android.os.AsyncTask;
import com.google.android.gms.internal.zzbw;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzkd;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class zzt$zza extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ zzt zzano;

    private zzt$zza(zzt com_google_android_gms_ads_internal_zzt) {
        this.zzano = com_google_android_gms_ads_internal_zzt;
    }

    protected Void doInBackground(Void... voidArr) {
        Throwable e;
        try {
            zzt.zza(this.zzano, (zzbw) zzt.zze(this.zzano).get(((Long) zzdc.zzbdd.get()).longValue(), TimeUnit.MILLISECONDS));
        } catch (InterruptedException e2) {
            e = e2;
            zzkd.zzd("Failed to load ad data", e);
        } catch (ExecutionException e3) {
            e = e3;
            zzkd.zzd("Failed to load ad data", e);
        } catch (TimeoutException e4) {
            zzkd.zzcx("Timed out waiting for ad data");
        }
        return null;
    }

    protected void onPostExecute(Void voidR) {
        String zzfe = this.zzano.zzfe();
        if (zzt.zzf(this.zzano) != null) {
            zzt.zzf(this.zzano).loadUrl(zzfe);
        }
    }
}
