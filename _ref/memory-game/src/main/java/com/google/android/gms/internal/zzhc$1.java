package com.google.android.gms.internal;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class zzhc$1 implements OnClickListener {
    final /* synthetic */ String zzbqu;
    final /* synthetic */ String zzbqv;
    final /* synthetic */ zzhc zzbqw;

    zzhc$1(zzhc com_google_android_gms_internal_zzhc, String str, String str2) {
        this.zzbqw = com_google_android_gms_internal_zzhc;
        this.zzbqu = str;
        this.zzbqv = str2;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            ((DownloadManager) zzhc.zza(this.zzbqw).getSystemService("download")).enqueue(this.zzbqw.zzk(this.zzbqu, this.zzbqv));
        } catch (IllegalStateException e) {
            this.zzbqw.zzbt("Could not store picture.");
        }
    }
}
