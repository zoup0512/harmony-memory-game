package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;

public final class zzre {
    public static final Api<NoOptions> API = new Api("Common.API", bK, bJ);
    public static final zzf<zzri> bJ = new zzf();
    private static final zza<zzri, NoOptions> bK = new zza<zzri, NoOptions>() {
        public /* synthetic */ zze zza(Context context, Looper looper, zzg com_google_android_gms_common_internal_zzg, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zzf(context, looper, com_google_android_gms_common_internal_zzg, (NoOptions) obj, connectionCallbacks, onConnectionFailedListener);
        }

        public zzri zzf(Context context, Looper looper, zzg com_google_android_gms_common_internal_zzg, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzri(context, looper, com_google_android_gms_common_internal_zzg, connectionCallbacks, onConnectionFailedListener);
        }
    };
    public static final zzrf zt = new zzrg();
}
