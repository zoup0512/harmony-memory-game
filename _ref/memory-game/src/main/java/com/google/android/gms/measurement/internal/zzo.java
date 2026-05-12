package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzd.zzb;
import com.google.android.gms.common.internal.zzd.zzc;
import com.google.android.gms.measurement.internal.zzm.zza;

public class zzo extends zzd<zzm> {
    public zzo(Context context, Looper looper, zzb com_google_android_gms_common_internal_zzd_zzb, zzc com_google_android_gms_common_internal_zzd_zzc) {
        super(context, looper, 93, com_google_android_gms_common_internal_zzd_zzb, com_google_android_gms_common_internal_zzd_zzc, null);
    }

    public /* synthetic */ IInterface zzbb(IBinder iBinder) {
        return zzjg(iBinder);
    }

    public zzm zzjg(IBinder iBinder) {
        return zza.zzjf(iBinder);
    }

    protected String zzqz() {
        return "com.google.android.gms.measurement.START";
    }

    protected String zzra() {
        return "com.google.android.gms.measurement.internal.IMeasurementService";
    }
}
