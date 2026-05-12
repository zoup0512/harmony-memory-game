package com.google.android.gms.ads.internal.request;

import android.content.Context;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzdc;

class zzc$1 implements zzc$zzb {
    final /* synthetic */ Context zzala;

    zzc$1(Context context) {
        this.zzala = context;
    }

    public boolean zza(VersionInfoParcel versionInfoParcel) {
        return versionInfoParcel.zzcnm || (zzi.zzcl(this.zzala) && !((Boolean) zzdc.zzayz.get()).booleanValue());
    }
}
