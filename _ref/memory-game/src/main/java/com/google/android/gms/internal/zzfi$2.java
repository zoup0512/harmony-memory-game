package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzw.zza;

class zzfi$2 extends zza {
    final /* synthetic */ zzfi zzbjt;

    zzfi$2(zzfi com_google_android_gms_internal_zzfi) {
        this.zzbjt = com_google_android_gms_internal_zzfi;
    }

    public void onAppEvent(final String str, final String str2) throws RemoteException {
        zzfi.zza(this.zzbjt).add(new zzfi$zza(this) {
            final /* synthetic */ zzfi$2 zzbjx;

            public void zzb(zzfj com_google_android_gms_internal_zzfj) throws RemoteException {
                if (com_google_android_gms_internal_zzfj.zzbkh != null) {
                    com_google_android_gms_internal_zzfj.zzbkh.onAppEvent(str, str2);
                }
            }
        });
    }
}
