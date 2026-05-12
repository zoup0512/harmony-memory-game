package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import java.util.Set;
import java.util.concurrent.Callable;

@TargetApi(11)
public class zzki$zzb extends zzki$zza {
    public boolean zza(Request request) {
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(1);
        return true;
    }

    public boolean zza(final Context context, final WebSettings webSettings) {
        super.zza(context, webSettings);
        return ((Boolean) zzkt.zzb(new Callable<Boolean>(this) {
            final /* synthetic */ zzki$zzb zzclk;

            public /* synthetic */ Object call() throws Exception {
                return zztn();
            }

            public Boolean zztn() {
                if (context.getCacheDir() != null) {
                    webSettings.setAppCachePath(context.getCacheDir().getAbsolutePath());
                    webSettings.setAppCacheMaxSize(0);
                    webSettings.setAppCacheEnabled(true);
                }
                webSettings.setDatabasePath(context.getDatabasePath("com.google.android.gms.ads.db").getAbsolutePath());
                webSettings.setDatabaseEnabled(true);
                webSettings.setDomStorageEnabled(true);
                webSettings.setDisplayZoomControls(false);
                webSettings.setBuiltInZoomControls(true);
                webSettings.setSupportZoom(true);
                webSettings.setAllowContentAccess(false);
                return Boolean.valueOf(true);
            }
        })).booleanValue();
    }

    public boolean zza(Window window) {
        window.setFlags(16777216, 16777216);
        return true;
    }

    public zzli zzb(zzlh com_google_android_gms_internal_zzlh, boolean z) {
        return new zzlp(com_google_android_gms_internal_zzlh, z);
    }

    public Set<String> zzg(Uri uri) {
        return uri.getQueryParameterNames();
    }

    public WebChromeClient zzk(zzlh com_google_android_gms_internal_zzlh) {
        return new zzlo(com_google_android_gms_internal_zzlh);
    }

    public boolean zzo(View view) {
        view.setLayerType(0, null);
        return true;
    }

    public boolean zzp(View view) {
        view.setLayerType(1, null);
        return true;
    }
}
