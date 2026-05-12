package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzq.zza;
import com.google.android.gms.ads.internal.zzu;

class zzfj$zza extends zza {
    zzq zzbkm;
    final /* synthetic */ zzfj zzbkn;

    zzfj$zza(zzfj com_google_android_gms_internal_zzfj, zzq com_google_android_gms_ads_internal_client_zzq) {
        this.zzbkn = com_google_android_gms_internal_zzfj;
        this.zzbkm = com_google_android_gms_ads_internal_client_zzq;
    }

    public void onAdClosed() throws RemoteException {
        this.zzbkm.onAdClosed();
        zzu.zzgb().zzlo();
    }

    public void onAdFailedToLoad(int i) throws RemoteException {
        this.zzbkm.onAdFailedToLoad(i);
    }

    public void onAdLeftApplication() throws RemoteException {
        this.zzbkm.onAdLeftApplication();
    }

    public void onAdLoaded() throws RemoteException {
        this.zzbkm.onAdLoaded();
    }

    public void onAdOpened() throws RemoteException {
        this.zzbkm.onAdOpened();
    }
}
