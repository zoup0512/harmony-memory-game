package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzp.zza;

class zzfi$5 extends zza {
    final /* synthetic */ zzfi zzbjt;

    zzfi$5(zzfi com_google_android_gms_internal_zzfi) {
        this.zzbjt = com_google_android_gms_internal_zzfi;
    }

    public void onAdClicked() throws RemoteException {
        zzfi.zza(this.zzbjt).add(new zzfi$zza(this) {
            final /* synthetic */ zzfi$5 zzbkc;

            {
                this.zzbkc = r1;
            }

            public void zzb(zzfj com_google_android_gms_internal_zzfj) throws RemoteException {
                if (com_google_android_gms_internal_zzfj.zzbkk != null) {
                    com_google_android_gms_internal_zzfj.zzbkk.onAdClicked();
                }
            }
        });
    }
}
