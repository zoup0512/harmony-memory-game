package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences.Editor;

class zzkf$11 extends zzkf$zza {
    final /* synthetic */ Context zzala;
    final /* synthetic */ boolean zzckt;

    zzkf$11(Context context, boolean z) {
        this.zzala = context;
        this.zzckt = z;
        super();
    }

    public void zzew() {
        Editor edit = zzkf.zzn(this.zzala).edit();
        edit.putBoolean("auto_collect_location", this.zzckt);
        edit.apply();
    }
}
