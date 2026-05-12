package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences.Editor;

class zzkf$1 extends zzkf$zza {
    final /* synthetic */ Context zzala;
    final /* synthetic */ boolean zzckn;

    zzkf$1(Context context, boolean z) {
        this.zzala = context;
        this.zzckn = z;
        super();
    }

    public void zzew() {
        Editor edit = zzkf.zzn(this.zzala).edit();
        edit.putBoolean("use_https", this.zzckn);
        edit.apply();
    }
}
