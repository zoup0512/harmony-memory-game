package com.google.android.gms.internal;

import android.webkit.WebView;
import android.webkit.WebViewClient;

class zzhm$1 implements Runnable {
    final /* synthetic */ String zzbwi;
    final /* synthetic */ String zzbwj;
    final /* synthetic */ zzhm zzbwk;

    zzhm$1(zzhm com_google_android_gms_internal_zzhm, String str, String str2) {
        this.zzbwk = com_google_android_gms_internal_zzhm;
        this.zzbwi = str;
        this.zzbwj = str2;
    }

    public void run() {
        final WebView zzpl = this.zzbwk.zzpl();
        zzpl.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ zzhm$1 zzbwl;

            public void onPageFinished(WebView webView, String str) {
                zzkd.zzcv("Loading assets have finished");
                this.zzbwl.zzbwk.zzbwh.remove(zzpl);
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                zzkd.zzcx("Loading assets have failed.");
                this.zzbwl.zzbwk.zzbwh.remove(zzpl);
            }
        });
        this.zzbwk.zzbwh.add(zzpl);
        zzpl.loadDataWithBaseURL(this.zzbwi, this.zzbwj, "text/html", "UTF-8", null);
        zzkd.zzcv("Fetching assets finished.");
    }
}
