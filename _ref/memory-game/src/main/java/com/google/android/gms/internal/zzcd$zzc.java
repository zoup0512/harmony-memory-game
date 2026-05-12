package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.view.View;

public class zzcd$zzc implements zzck {
    @Nullable
    private final View mView;
    @Nullable
    private final zzju zzard;

    public zzcd$zzc(View view, zzju com_google_android_gms_internal_zzju) {
        this.mView = view;
        this.zzard = com_google_android_gms_internal_zzju;
    }

    public View zzhh() {
        return this.mView;
    }

    public boolean zzhi() {
        return this.zzard == null || this.mView == null;
    }

    public zzck zzhj() {
        return this;
    }
}
