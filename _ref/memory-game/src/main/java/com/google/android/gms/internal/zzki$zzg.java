package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

@TargetApi(19)
public class zzki$zzg extends zzki$zze {
    public boolean isAttachedToWindow(View view) {
        return view.isAttachedToWindow();
    }

    public LayoutParams zztm() {
        return new LayoutParams(-1, -1);
    }
}
