package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class zzgz$2 implements OnClickListener {
    final /* synthetic */ zzgz zzbqd;

    zzgz$2(zzgz com_google_android_gms_internal_zzgz) {
        this.zzbqd = com_google_android_gms_internal_zzgz;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.zzbqd.zzbt("Operation denied by user.");
    }
}
