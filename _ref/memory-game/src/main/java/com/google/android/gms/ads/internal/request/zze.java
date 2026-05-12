package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.ads.internal.request.zzk.zza;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzd.zzb;
import com.google.android.gms.common.internal.zzd.zzc;
import com.google.android.gms.internal.zzin;

@zzin
public class zze extends zzd<zzk> {
    final int zzcap;

    public zze(Context context, Looper looper, zzb com_google_android_gms_common_internal_zzd_zzb, zzc com_google_android_gms_common_internal_zzd_zzc, int i) {
        super(context, looper, 8, com_google_android_gms_common_internal_zzd_zzb, com_google_android_gms_common_internal_zzd_zzc, null);
        this.zzcap = i;
    }

    protected zzk zzba(IBinder iBinder) {
        return zza.zzbc(iBinder);
    }

    protected /* synthetic */ IInterface zzbb(IBinder iBinder) {
        return zzba(iBinder);
    }

    protected String zzqz() {
        return "com.google.android.gms.ads.service.START";
    }

    protected String zzra() {
        return "com.google.android.gms.ads.internal.request.IAdRequestService";
    }

    public zzk zzrb() throws DeadObjectException {
        return (zzk) super.zzasa();
    }
}
