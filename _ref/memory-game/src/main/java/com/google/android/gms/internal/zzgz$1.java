package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.google.android.gms.ads.internal.zzu;

class zzgz$1 implements OnClickListener {
    final /* synthetic */ zzgz zzbqd;

    zzgz$1(zzgz com_google_android_gms_internal_zzgz) {
        this.zzbqd = com_google_android_gms_internal_zzgz;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        zzu.zzfq().zzb(zzgz.zza(this.zzbqd), this.zzbqd.createIntent());
    }
}
