package com.google.android.gms.internal;

import android.webkit.ValueCallback;
import android.webkit.WebView;

class zzco$2 implements Runnable {
    final /* synthetic */ zzco zzasr;
    ValueCallback<String> zzass = new ValueCallback<String>(this) {
        final /* synthetic */ zzco$2 zzasw;

        {
            this.zzasw = r1;
        }

        public /* synthetic */ void onReceiveValue(Object obj) {
            zzz((String) obj);
        }

        public void zzz(String str) {
            this.zzasw.zzasr.zza(this.zzasw.zzast, this.zzasw.zzasu, str, this.zzasw.zzasv);
        }
    };
    final /* synthetic */ zzcl zzast;
    final /* synthetic */ WebView zzasu;
    final /* synthetic */ boolean zzasv;

    zzco$2(zzco com_google_android_gms_internal_zzco, zzcl com_google_android_gms_internal_zzcl, WebView webView, boolean z) {
        this.zzasr = com_google_android_gms_internal_zzco;
        this.zzast = com_google_android_gms_internal_zzcl;
        this.zzasu = webView;
        this.zzasv = z;
    }

    public void run() {
        if (this.zzasu.getSettings().getJavaScriptEnabled()) {
            try {
                this.zzasu.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.zzass);
            } catch (Throwable th) {
                this.zzass.onReceiveValue("");
            }
        }
    }
}
