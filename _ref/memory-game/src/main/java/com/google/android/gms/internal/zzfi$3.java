package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzho.zza;

class zzfi$3 extends zza {
    final /* synthetic */ zzfi zzbjt;

    zzfi$3(zzfi com_google_android_gms_internal_zzfi) {
        this.zzbjt = com_google_android_gms_internal_zzfi;
    }

    public void zza(final zzhn com_google_android_gms_internal_zzhn) throws RemoteException {
        zzfi.zza(this.zzbjt).add(new zzfi$zza(this) {
            final /* synthetic */ zzfi$3 zzbjz;

            public void zzb(zzfj com_google_android_gms_internal_zzfj) throws RemoteException {
                if (com_google_android_gms_internal_zzfj.zzbki != null) {
                    com_google_android_gms_internal_zzfj.zzbki.zza(com_google_android_gms_internal_zzhn);
                }
            }
        });
    }
}
