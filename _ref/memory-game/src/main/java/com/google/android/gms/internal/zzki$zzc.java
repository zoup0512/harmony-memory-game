package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.net.http.SslError;
import android.webkit.WebChromeClient;

@TargetApi(14)
public class zzki$zzc extends zzki$zzb {
    public String zza(SslError sslError) {
        return sslError.getUrl();
    }

    public WebChromeClient zzk(zzlh com_google_android_gms_internal_zzlh) {
        return new zzlq(com_google_android_gms_internal_zzlh);
    }
}
