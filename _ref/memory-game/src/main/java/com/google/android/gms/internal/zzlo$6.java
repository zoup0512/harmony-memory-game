package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsPromptResult;
import android.widget.EditText;

class zzlo$6 implements OnClickListener {
    final /* synthetic */ JsPromptResult zzcqj;
    final /* synthetic */ EditText zzcqk;

    zzlo$6(JsPromptResult jsPromptResult, EditText editText) {
        this.zzcqj = jsPromptResult;
        this.zzcqk = editText;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.zzcqj.confirm(this.zzcqk.getText().toString());
    }
}
