package com.google.android.gms.internal;

import android.view.View;

class zzco$1 implements Runnable {
    final /* synthetic */ View zzasq;
    final /* synthetic */ zzco zzasr;

    zzco$1(zzco com_google_android_gms_internal_zzco, View view) {
        this.zzasr = com_google_android_gms_internal_zzco;
        this.zzasq = view;
    }

    public void run() {
        this.zzasr.zzf(this.zzasq);
    }
}
