package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.view.View;

@TargetApi(18)
public class zzki$zze extends zzki$zzd {
    public boolean isAttachedToWindow(View view) {
        return super.isAttachedToWindow(view) || view.getWindowId() != null;
    }

    public int zztl() {
        return 14;
    }
}
