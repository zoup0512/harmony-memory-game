package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import java.util.List;

class zzkh$1 implements zzdq$zza {
    final /* synthetic */ Context zzala;
    final /* synthetic */ List zzclf;
    final /* synthetic */ zzdq zzclg;
    final /* synthetic */ zzkh zzclh;

    zzkh$1(zzkh com_google_android_gms_internal_zzkh, List list, zzdq com_google_android_gms_internal_zzdq, Context context) {
        this.zzclh = com_google_android_gms_internal_zzkh;
        this.zzclf = list;
        this.zzclg = com_google_android_gms_internal_zzdq;
        this.zzala = context;
    }

    public void zzkn() {
        for (String str : this.zzclf) {
            String str2 = "Pinging url: ";
            String valueOf = String.valueOf(str);
            zzkd.zzcw(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            this.zzclg.mayLaunchUrl(Uri.parse(str), null, null);
        }
        this.zzclg.zzd((Activity) this.zzala);
    }

    public void zzko() {
    }
}
