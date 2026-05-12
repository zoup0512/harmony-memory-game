package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences.Editor;

class zzkf$7 extends zzkf$zza {
    final /* synthetic */ Context zzala;
    final /* synthetic */ boolean zzckr;

    zzkf$7(Context context, boolean z) {
        this.zzala = context;
        this.zzckr = z;
        super();
    }

    public void zzew() {
        Editor edit = zzkf.zzn(this.zzala).edit();
        edit.putBoolean("content_url_opted_out", this.zzckr);
        edit.apply();
    }
}
