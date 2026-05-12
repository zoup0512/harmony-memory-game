package com.google.android.gms.plus;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.util.zzv;
import com.google.android.gms.plus.internal.PlusCommonExtras;
import com.google.android.gms.plus.internal.PlusSession;
import com.google.android.gms.plus.internal.zze;

class Plus$1 extends zza<zze, Plus$PlusOptions> {
    Plus$1() {
    }

    public int getPriority() {
        return 2;
    }

    public zze zza(Context context, Looper looper, zzg com_google_android_gms_common_internal_zzg, Plus$PlusOptions plus$PlusOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        if (plus$PlusOptions == null) {
            plus$PlusOptions = new Plus$PlusOptions();
        }
        return new zze(context, looper, com_google_android_gms_common_internal_zzg, new PlusSession(com_google_android_gms_common_internal_zzg.zzary().name, zzv.zzd(com_google_android_gms_common_internal_zzg.zzask()), (String[]) plus$PlusOptions.arB.toArray(new String[0]), new String[0], context.getPackageName(), context.getPackageName(), null, new PlusCommonExtras()), connectionCallbacks, onConnectionFailedListener);
    }
}
