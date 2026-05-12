package com.google.android.gms.ads.internal;

import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzkd;

class zzt$1 extends WebViewClient {
    final /* synthetic */ zzt zzano;

    zzt$1(zzt com_google_android_gms_ads_internal_zzt) {
        this.zzano = com_google_android_gms_ads_internal_zzt;
    }

    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        if (zzt.zza(this.zzano) != null) {
            try {
                zzt.zza(this.zzano).onAdFailedToLoad(0);
            } catch (Throwable e) {
                zzkd.zzd("Could not call AdListener.onAdFailedToLoad().", e);
            }
        }
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str.startsWith(this.zzano.zzff())) {
            return false;
        }
        if (str.startsWith((String) zzdc.zzbcy.get())) {
            if (zzt.zza(this.zzano) != null) {
                try {
                    zzt.zza(this.zzano).onAdFailedToLoad(3);
                } catch (Throwable e) {
                    zzkd.zzd("Could not call AdListener.onAdFailedToLoad().", e);
                }
            }
            this.zzano.zzj(0);
            return true;
        } else if (str.startsWith((String) zzdc.zzbcz.get())) {
            if (zzt.zza(this.zzano) != null) {
                try {
                    zzt.zza(this.zzano).onAdFailedToLoad(0);
                } catch (Throwable e2) {
                    zzkd.zzd("Could not call AdListener.onAdFailedToLoad().", e2);
                }
            }
            this.zzano.zzj(0);
            return true;
        } else if (str.startsWith((String) zzdc.zzbda.get())) {
            if (zzt.zza(this.zzano) != null) {
                try {
                    zzt.zza(this.zzano).onAdLoaded();
                } catch (Throwable e22) {
                    zzkd.zzd("Could not call AdListener.onAdLoaded().", e22);
                }
            }
            this.zzano.zzj(this.zzano.zzw(str));
            return true;
        } else if (str.startsWith("gmsg://")) {
            return true;
        } else {
            if (zzt.zza(this.zzano) != null) {
                try {
                    zzt.zza(this.zzano).onAdLeftApplication();
                } catch (Throwable e222) {
                    zzkd.zzd("Could not call AdListener.onAdLeftApplication().", e222);
                }
            }
            zzt.zzb(this.zzano, zzt.zza(this.zzano, str));
            return true;
        }
    }
}
