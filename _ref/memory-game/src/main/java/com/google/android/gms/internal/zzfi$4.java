package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzdo.zza;

class zzfi$4 extends zza {
    final /* synthetic */ zzfi zzbjt;

    zzfi$4(zzfi com_google_android_gms_internal_zzfi) {
        this.zzbjt = com_google_android_gms_internal_zzfi;
    }

    public void zza(final zzdn com_google_android_gms_internal_zzdn) throws RemoteException {
        zzfi.zza(this.zzbjt).add(new zzfi$zza(this) {
            final /* synthetic */ zzfi$4 zzbkb;

            public void zzb(zzfj com_google_android_gms_internal_zzfj) throws RemoteException {
                if (com_google_android_gms_internal_zzfj.zzbkj != null) {
                    com_google_android_gms_internal_zzfj.zzbkj.zza(com_google_android_gms_internal_zzdn);
                }
            }
        });
    }
}
