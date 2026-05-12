package com.google.android.gms.plus;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzuz;
import com.google.android.gms.internal.zzva;
import com.google.android.gms.internal.zzvb;
import com.google.android.gms.internal.zzvc;
import com.google.android.gms.plus.internal.zze;

@Deprecated
public final class Plus {
    @Deprecated
    public static final Api<PlusOptions> API = new Api("Plus.API", bK, bJ);
    @Deprecated
    public static final Account AccountApi = new zzuz();
    @Deprecated
    public static final People PeopleApi = new zzvc();
    public static final Scope SCOPE_PLUS_LOGIN = new Scope(Scopes.PLUS_LOGIN);
    public static final Scope SCOPE_PLUS_PROFILE = new Scope(Scopes.PLUS_ME);
    @Deprecated
    public static final zzb ary = new zzvb();
    public static final zza arz = new zzva();
    public static final zzf<zze> bJ = new zzf();
    static final zza<zze, PlusOptions> bK = new 1();

    private Plus() {
    }

    public static zze zzf(GoogleApiClient googleApiClient, boolean z) {
        zzab.zzb(googleApiClient != null, "GoogleApiClient parameter is required.");
        zzab.zza(googleApiClient.isConnected(), "GoogleApiClient must be connected.");
        zzab.zza(googleApiClient.zza(API), "GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
        boolean hasConnectedApi = googleApiClient.hasConnectedApi(API);
        if (!z || hasConnectedApi) {
            return hasConnectedApi ? (zze) googleApiClient.zza(bJ) : null;
        } else {
            throw new IllegalStateException("GoogleApiClient has an optional Plus.API and is not connected to Plus. Use GoogleApiClient.hasConnectedApi(Plus.API) to guard this call.");
        }
    }
}
