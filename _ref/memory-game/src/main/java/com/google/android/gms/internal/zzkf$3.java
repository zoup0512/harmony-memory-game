package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences.Editor;

class zzkf$3 extends zzkf$zza {
    final /* synthetic */ Context zzala;
    final /* synthetic */ String zzckp;
    final /* synthetic */ long zzckq;

    zzkf$3(Context context, String str, long j) {
        this.zzala = context;
        this.zzckp = str;
        this.zzckq = j;
        super();
    }

    public void zzew() {
        Editor edit = zzkf.zzn(this.zzala).edit();
        edit.putString("app_settings_json", this.zzckp);
        edit.putLong("app_settings_last_update_ms", this.zzckq);
        edit.apply();
    }
}
