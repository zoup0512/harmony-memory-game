package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.view.View.MeasureSpec;
import android.webkit.WebView;

protected final class zzhz$zza extends AsyncTask<Void, Void, Boolean> {
    private final WebView zzbyd;
    private Bitmap zzbye;
    final /* synthetic */ zzhz zzbyf;

    public zzhz$zza(zzhz com_google_android_gms_internal_zzhz, WebView webView) {
        this.zzbyf = com_google_android_gms_internal_zzhz;
        this.zzbyd = webView;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return zza((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        zza((Boolean) obj);
    }

    protected synchronized void onPreExecute() {
        this.zzbye = Bitmap.createBitmap(zzhz.zza(this.zzbyf), zzhz.zzb(this.zzbyf), Config.ARGB_8888);
        this.zzbyd.setVisibility(0);
        this.zzbyd.measure(MeasureSpec.makeMeasureSpec(zzhz.zza(this.zzbyf), 0), MeasureSpec.makeMeasureSpec(zzhz.zzb(this.zzbyf), 0));
        this.zzbyd.layout(0, 0, zzhz.zza(this.zzbyf), zzhz.zzb(this.zzbyf));
        this.zzbyd.draw(new Canvas(this.zzbye));
        this.zzbyd.invalidate();
    }

    protected synchronized Boolean zza(Void... voidArr) {
        Boolean valueOf;
        int width = this.zzbye.getWidth();
        int height = this.zzbye.getHeight();
        if (width == 0 || height == 0) {
            valueOf = Boolean.valueOf(false);
        } else {
            int i = 0;
            for (int i2 = 0; i2 < width; i2 += 10) {
                for (int i3 = 0; i3 < height; i3 += 10) {
                    if (this.zzbye.getPixel(i2, i3) != 0) {
                        i++;
                    }
                }
            }
            valueOf = Boolean.valueOf(((double) i) / (((double) (width * height)) / 100.0d) > 0.1d);
        }
        return valueOf;
    }

    protected void zza(Boolean bool) {
        zzhz.zzc(this.zzbyf);
        if (bool.booleanValue() || this.zzbyf.zzqb() || zzhz.zzd(this.zzbyf) <= 0) {
            this.zzbyf.zzbyc = bool.booleanValue();
            zzhz.zze(this.zzbyf).zza(this.zzbyf.zzbgf, true);
        } else if (zzhz.zzd(this.zzbyf) > 0) {
            if (zzkd.zzaz(2)) {
                zzkd.zzcv("Ad not detected, scheduling another run.");
            }
            zzhz.zzg(this.zzbyf).postDelayed(this.zzbyf, zzhz.zzf(this.zzbyf));
        }
    }
}
