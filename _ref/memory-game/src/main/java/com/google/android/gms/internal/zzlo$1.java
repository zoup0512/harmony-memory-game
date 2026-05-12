package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.webkit.JsResult;

class zzlo$1 implements OnCancelListener {
    final /* synthetic */ JsResult zzcqi;

    zzlo$1(JsResult jsResult) {
        this.zzcqi = jsResult;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.zzcqi.cancel();
    }
}
