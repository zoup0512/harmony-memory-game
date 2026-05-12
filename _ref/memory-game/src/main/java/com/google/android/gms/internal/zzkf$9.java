package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences.Editor;

class zzkf$9 extends zzkf$zza {
    final /* synthetic */ Context zzala;
    final /* synthetic */ String zzcks;

    zzkf$9(Context context, String str) {
        this.zzala = context;
        this.zzcks = str;
        super();
    }

    public void zzew() {
        Editor edit = zzkf.zzn(this.zzala).edit();
        edit.putString("content_url_hashes", this.zzcks);
        edit.apply();
    }
}
