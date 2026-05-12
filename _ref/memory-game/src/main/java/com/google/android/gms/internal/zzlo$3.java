package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsResult;

class zzlo$3 implements OnClickListener {
    final /* synthetic */ JsResult zzcqi;

    zzlo$3(JsResult jsResult) {
        this.zzcqi = jsResult;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.zzcqi.confirm();
    }
}
