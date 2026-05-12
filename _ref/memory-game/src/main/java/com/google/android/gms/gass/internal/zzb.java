package com.google.android.gms.gass.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzd.zzc;
import com.google.android.gms.gass.internal.zze.zza;

public class zzb extends zzd<zze> {
    public zzb(Context context, Looper looper, com.google.android.gms.common.internal.zzd.zzb com_google_android_gms_common_internal_zzd_zzb, zzc com_google_android_gms_common_internal_zzd_zzc) {
        super(context, looper, 116, com_google_android_gms_common_internal_zzd_zzb, com_google_android_gms_common_internal_zzd_zzc, null);
    }

    protected /* synthetic */ IInterface zzbb(IBinder iBinder) {
        return zzgk(iBinder);
    }

    public zze zzblb() throws DeadObjectException {
        return (zze) super.zzasa();
    }

    protected zze zzgk(IBinder iBinder) {
        return zza.zzgl(iBinder);
    }

    protected String zzqz() {
        return "com.google.android.gms.gass.START";
    }

    protected String zzra() {
        return "com.google.android.gms.gass.internal.IGassService";
    }
}
