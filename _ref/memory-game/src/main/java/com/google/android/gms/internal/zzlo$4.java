package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.webkit.JsPromptResult;

class zzlo$4 implements OnCancelListener {
    final /* synthetic */ JsPromptResult zzcqj;

    zzlo$4(JsPromptResult jsPromptResult) {
        this.zzcqj = jsPromptResult;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.zzcqj.cancel();
    }
}
