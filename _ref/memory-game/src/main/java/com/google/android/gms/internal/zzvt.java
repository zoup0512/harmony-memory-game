package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.signin.internal.zzg;

public final class zzvt {
    public static final Api<zzvv> API = new Api("SignIn.API", bK, bJ);
    public static final Api<zza> Dz = new Api("SignIn.INTERNAL_API", atQ, atP);
    public static final zzf<zzg> atP = new zzf();
    static final com.google.android.gms.common.api.Api.zza<zzg, zza> atQ = new com.google.android.gms.common.api.Api.zza<zzg, zza>() {
        public zzg zza(Context context, Looper looper, com.google.android.gms.common.internal.zzg com_google_android_gms_common_internal_zzg, zza com_google_android_gms_internal_zzvt_zza, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzg(context, looper, false, com_google_android_gms_common_internal_zzg, com_google_android_gms_internal_zzvt_zza.zzbzn(), connectionCallbacks, onConnectionFailedListener);
        }
    };
    public static final zzf<zzg> bJ = new zzf();
    public static final com.google.android.gms.common.api.Api.zza<zzg, zzvv> bK = new com.google.android.gms.common.api.Api.zza<zzg, zzvv>() {
        public zzg zza(Context context, Looper looper, com.google.android.gms.common.internal.zzg com_google_android_gms_common_internal_zzg, zzvv com_google_android_gms_internal_zzvv, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzg(context, looper, true, com_google_android_gms_common_internal_zzg, com_google_android_gms_internal_zzvv == null ? zzvv.atR : com_google_android_gms_internal_zzvv, connectionCallbacks, onConnectionFailedListener);
        }
    };
    public static final Scope dK = new Scope(Scopes.PROFILE);
    public static final Scope dL = new Scope("email");

    public static class zza implements HasOptions {
        public Bundle zzbzn() {
            return null;
        }
    }
}
