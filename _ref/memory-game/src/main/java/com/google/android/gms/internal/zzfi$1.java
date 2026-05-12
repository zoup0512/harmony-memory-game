package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzq.zza;
import com.google.android.gms.ads.internal.zzu;

class zzfi$1 extends zza {
    final /* synthetic */ zzfi zzbjt;

    zzfi$1(zzfi com_google_android_gms_internal_zzfi) {
        this.zzbjt = com_google_android_gms_internal_zzfi;
    }

    public void onAdClosed() throws RemoteException {
        zzfi.zza(this.zzbjt).add(new zzfi$zza(this) {
            final /* synthetic */ zzfi$1 zzbju;

            {
                this.zzbju = r1;
            }

            public void zzb(zzfj com_google_android_gms_internal_zzfj) throws RemoteException {
                if (com_google_android_gms_internal_zzfj.zzalf != null) {
                    com_google_android_gms_internal_zzfj.zzalf.onAdClosed();
                }
                zzu.zzgb().zzlo();
            }
        });
    }

    public void onAdFailedToLoad(final int i) throws RemoteException {
        zzfi.zza(this.zzbjt).add(new zzfi$zza(this) {
            final /* synthetic */ zzfi$1 zzbju;

            public void zzb(zzfj com_google_android_gms_internal_zzfj) throws RemoteException {
                if (com_google_android_gms_internal_zzfj.zzalf != null) {
                    com_google_android_gms_internal_zzfj.zzalf.onAdFailedToLoad(i);
                }
            }
        });
        zzkd.v("Pooled interstitial failed to load.");
    }

    public void onAdLeftApplication() throws RemoteException {
        zzfi.zza(this.zzbjt).add(new zzfi$zza(this) {
            final /* synthetic */ zzfi$1 zzbju;

            {
                this.zzbju = r1;
            }

            public void zzb(zzfj com_google_android_gms_internal_zzfj) throws RemoteException {
                if (com_google_android_gms_internal_zzfj.zzalf != null) {
                    com_google_android_gms_internal_zzfj.zzalf.onAdLeftApplication();
                }
            }
        });
    }

    public void onAdLoaded() throws RemoteException {
        zzfi.zza(this.zzbjt).add(new zzfi$zza(this) {
            final /* synthetic */ zzfi$1 zzbju;

            {
                this.zzbju = r1;
            }

            public void zzb(zzfj com_google_android_gms_internal_zzfj) throws RemoteException {
                if (com_google_android_gms_internal_zzfj.zzalf != null) {
                    com_google_android_gms_internal_zzfj.zzalf.onAdLoaded();
                }
            }
        });
        zzkd.v("Pooled interstitial loaded.");
    }

    public void onAdOpened() throws RemoteException {
        zzfi.zza(this.zzbjt).add(new zzfi$zza(this) {
            final /* synthetic */ zzfi$1 zzbju;

            {
                this.zzbju = r1;
            }

            public void zzb(zzfj com_google_android_gms_internal_zzfj) throws RemoteException {
                if (com_google_android_gms_internal_zzfj.zzalf != null) {
                    com_google_android_gms_internal_zzfj.zzalf.onAdOpened();
                }
            }
        });
    }
}
