package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import android.view.MotionEvent;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;

public final class zzbw {
    private final zzca zzaip;

    public zzbw(String str, Context context, boolean z) {
        this.zzaip = zzbz.zzb(str, context, z);
    }

    public void zza(MotionEvent motionEvent) throws RemoteException {
        this.zzaip.zzd(zze.zzac(motionEvent));
    }

    public Uri zzc(Uri uri, Context context) throws zzbx, RemoteException {
        zzd zza = this.zzaip.zza(zze.zzac(uri), zze.zzac(context));
        if (zza != null) {
            return (Uri) zze.zzad(zza);
        }
        throw new zzbx();
    }

    public Uri zzd(Uri uri, Context context) throws zzbx, RemoteException {
        zzd zzb = this.zzaip.zzb(zze.zzac(uri), zze.zzac(context));
        if (zzb != null) {
            return (Uri) zze.zzad(zzb);
        }
        throw new zzbx();
    }
}
