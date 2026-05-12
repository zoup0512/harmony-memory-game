package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.view.View;
import android.webkit.WebChromeClient.CustomViewCallback;

@TargetApi(14)
@zzin
public final class zzlq extends zzlo {
    public zzlq(zzlh com_google_android_gms_internal_zzlh) {
        super(com_google_android_gms_internal_zzlh);
    }

    public void onShowCustomView(View view, int i, CustomViewCallback customViewCallback) {
        zza(view, i, customViewCallback);
    }
}
