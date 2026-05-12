package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.reward.client.zzd.zza;

class zzfi$6 extends zza {
    final /* synthetic */ zzfi zzbjt;

    zzfi$6(zzfi com_google_android_gms_internal_zzfi) {
        this.zzbjt = com_google_android_gms_internal_zzfi;
    }

    public void onRewardedVideoAdClosed() throws RemoteException {
        zzfi.zza(this.zzbjt).add(new zzfi$zza(this) {
            final /* synthetic */ zzfi$6 zzbkd;

            {
                this.zzbkd = r1;
            }

            public void zzb(zzfj com_google_android_gms_internal_zzfj) throws RemoteException {
                if (com_google_android_gms_internal_zzfj.zzbkl != null) {
                    com_google_android_gms_internal_zzfj.zzbkl.onRewardedVideoAdClosed();
                }
            }
        });
    }

    public void onRewardedVideoAdFailedToLoad(final int i) throws RemoteException {
        zzfi.zza(this.zzbjt).add(new zzfi$zza(this) {
            final /* synthetic */ zzfi$6 zzbkd;

            public void zzb(zzfj com_google_android_gms_internal_zzfj) throws RemoteException {
                if (com_google_android_gms_internal_zzfj.zzbkl != null) {
                    com_google_android_gms_internal_zzfj.zzbkl.onRewardedVideoAdFailedToLoad(i);
                }
            }
        });
    }

    public void onRewardedVideoAdLeftApplication() throws RemoteException {
        zzfi.zza(this.zzbjt).add(new zzfi$zza(this) {
            final /* synthetic */ zzfi$6 zzbkd;

            {
                this.zzbkd = r1;
            }

            public void zzb(zzfj com_google_android_gms_internal_zzfj) throws RemoteException {
                if (com_google_android_gms_internal_zzfj.zzbkl != null) {
                    com_google_android_gms_internal_zzfj.zzbkl.onRewardedVideoAdLeftApplication();
                }
            }
        });
    }

    public void onRewardedVideoAdLoaded() throws RemoteException {
        zzfi.zza(this.zzbjt).add(new zzfi$zza(this) {
            final /* synthetic */ zzfi$6 zzbkd;

            {
                this.zzbkd = r1;
            }

            public void zzb(zzfj com_google_android_gms_internal_zzfj) throws RemoteException {
                if (com_google_android_gms_internal_zzfj.zzbkl != null) {
                    com_google_android_gms_internal_zzfj.zzbkl.onRewardedVideoAdLoaded();
                }
            }
        });
    }

    public void onRewardedVideoAdOpened() throws RemoteException {
        zzfi.zza(this.zzbjt).add(new zzfi$zza(this) {
            final /* synthetic */ zzfi$6 zzbkd;

            {
                this.zzbkd = r1;
            }

            public void zzb(zzfj com_google_android_gms_internal_zzfj) throws RemoteException {
                if (com_google_android_gms_internal_zzfj.zzbkl != null) {
                    com_google_android_gms_internal_zzfj.zzbkl.onRewardedVideoAdOpened();
                }
            }
        });
    }

    public void onRewardedVideoStarted() throws RemoteException {
        zzfi.zza(this.zzbjt).add(new zzfi$zza(this) {
            final /* synthetic */ zzfi$6 zzbkd;

            {
                this.zzbkd = r1;
            }

            public void zzb(zzfj com_google_android_gms_internal_zzfj) throws RemoteException {
                if (com_google_android_gms_internal_zzfj.zzbkl != null) {
                    com_google_android_gms_internal_zzfj.zzbkl.onRewardedVideoStarted();
                }
            }
        });
    }

    public void zza(final com.google.android.gms.ads.internal.reward.client.zza com_google_android_gms_ads_internal_reward_client_zza) throws RemoteException {
        zzfi.zza(this.zzbjt).add(new zzfi$zza(this) {
            final /* synthetic */ zzfi$6 zzbkd;

            public void zzb(zzfj com_google_android_gms_internal_zzfj) throws RemoteException {
                if (com_google_android_gms_internal_zzfj.zzbkl != null) {
                    com_google_android_gms_internal_zzfj.zzbkl.zza(com_google_android_gms_ads_internal_reward_client_zza);
                }
            }
        });
    }
}
