package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class zzhc$2 implements OnClickListener {
    final /* synthetic */ zzhc zzbqw;

    zzhc$2(zzhc com_google_android_gms_internal_zzhc) {
        this.zzbqw = com_google_android_gms_internal_zzhc;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.zzbqw.zzbt("User canceled the download.");
    }
}
