package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.amazon.device.ads.WebRequest;
import com.google.android.gms.ads.internal.zzu;

class zzkk$1 implements OnClickListener {
    final /* synthetic */ String zzclp;
    final /* synthetic */ zzkk zzclq;

    zzkk$1(zzkk com_google_android_gms_internal_zzkk, String str) {
        this.zzclq = com_google_android_gms_internal_zzkk;
        this.zzclp = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        zzu.zzfq().zzb(zzkk.zza(this.zzclq), Intent.createChooser(new Intent("android.intent.action.SEND").setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT).putExtra("android.intent.extra.TEXT", this.zzclp), "Share via"));
    }
}
