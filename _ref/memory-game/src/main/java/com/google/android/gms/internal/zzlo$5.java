package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsPromptResult;

class zzlo$5 implements OnClickListener {
    final /* synthetic */ JsPromptResult zzcqj;

    zzlo$5(JsPromptResult jsPromptResult) {
        this.zzcqj = jsPromptResult;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.zzcqj.cancel();
    }
}
