package com.google.android.gms.ads.internal.client;

import android.app.Activity;
import android.os.RemoteException;
import com.applovin.sdk.AppLovinEventTypes;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzhp;

class zzl$8 extends zzl$zza<zzhp> {
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ zzl zzavj;

    zzl$8(zzl com_google_android_gms_ads_internal_client_zzl, Activity activity) {
        this.zzavj = com_google_android_gms_ads_internal_client_zzl;
        this.val$activity = activity;
        super(com_google_android_gms_ads_internal_client_zzl);
    }

    public /* synthetic */ Object zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
        return zzg(com_google_android_gms_ads_internal_client_zzx);
    }

    public zzhp zzg(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
        return com_google_android_gms_ads_internal_client_zzx.createInAppPurchaseManager(zze.zzac(this.val$activity));
    }

    public /* synthetic */ Object zzin() {
        return zzis();
    }

    public zzhp zzis() {
        zzhp zzg = zzl.zzg(this.zzavj).zzg(this.val$activity);
        if (zzg != null) {
            return zzg;
        }
        zzl.zza(this.zzavj, this.val$activity, AppLovinEventTypes.USER_COMPLETED_IN_APP_PURCHASE);
        return null;
    }
}
