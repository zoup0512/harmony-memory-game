package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzee;

public class zzaj extends com.google.android.gms.ads.internal.client.zzs.zza {
    private zzq zzalf;

    private class zza extends com.google.android.gms.ads.internal.client.zzr.zza {
        final /* synthetic */ zzaj zzawu;

        private zza(zzaj com_google_android_gms_ads_internal_client_zzaj) {
            this.zzawu = com_google_android_gms_ads_internal_client_zzaj;
        }

        public String getMediationAdapterClassName() throws RemoteException {
            return null;
        }

        public boolean isLoading() throws RemoteException {
            return false;
        }

        public void zzf(AdRequestParcel adRequestParcel) throws RemoteException {
            zzb.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
            com.google.android.gms.ads.internal.util.client.zza.zzcnb.post(new Runnable(this) {
                final /* synthetic */ zza zzawv;

                {
                    this.zzawv = r1;
                }

                public void run() {
                    if (this.zzawv.zzawu.zzalf != null) {
                        try {
                            this.zzawv.zzawu.zzalf.onAdFailedToLoad(1);
                        } catch (Throwable e) {
                            zzb.zzd("Could not notify onAdFailedToLoad event.", e);
                        }
                    }
                }
            });
        }
    }

    public void zza(NativeAdOptionsParcel nativeAdOptionsParcel) throws RemoteException {
    }

    public void zza(zzeb com_google_android_gms_internal_zzeb) throws RemoteException {
    }

    public void zza(zzec com_google_android_gms_internal_zzec) throws RemoteException {
    }

    public void zza(String str, zzee com_google_android_gms_internal_zzee, zzed com_google_android_gms_internal_zzed) throws RemoteException {
    }

    public void zzb(zzq com_google_android_gms_ads_internal_client_zzq) throws RemoteException {
        this.zzalf = com_google_android_gms_ads_internal_client_zzq;
    }

    public void zzb(zzy com_google_android_gms_ads_internal_client_zzy) throws RemoteException {
    }

    public zzr zzes() throws RemoteException {
        return new zza();
    }
}
