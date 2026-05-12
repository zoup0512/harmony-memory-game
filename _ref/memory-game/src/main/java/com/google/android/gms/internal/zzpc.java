package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.internal.zzpf.zza;

public class zzpc extends zzk<zzpf> {
    public zzpc(Context context, Looper looper, zzg com_google_android_gms_common_internal_zzg, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 40, com_google_android_gms_common_internal_zzg, connectionCallbacks, onConnectionFailedListener);
    }

    public void zza(zzpe com_google_android_gms_internal_zzpe, LogEventParcelable logEventParcelable) throws RemoteException {
        ((zzpf) zzasa()).zza(com_google_android_gms_internal_zzpe, logEventParcelable);
    }

    protected /* synthetic */ IInterface zzbb(IBinder iBinder) {
        return zzdk(iBinder);
    }

    protected zzpf zzdk(IBinder iBinder) {
        return zza.zzdm(iBinder);
    }

    protected String zzqz() {
        return "com.google.android.gms.clearcut.service.START";
    }

    protected String zzra() {
        return "com.google.android.gms.clearcut.internal.IClearcutLoggerService";
    }
}
