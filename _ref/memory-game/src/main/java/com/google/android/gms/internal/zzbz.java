package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.zzc;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;

public final class zzbz extends zzg<zzcb> {
    private static final zzbz zzaiu = new zzbz();

    private zzbz() {
        super("com.google.android.gms.ads.adshield.AdShieldCreatorImpl");
    }

    public static zzca zzb(String str, Context context, boolean z) {
        if (zzc.zzang().isGooglePlayServicesAvailable(context) == 0) {
            zzca zzc = zzaiu.zzc(str, context, z);
            if (zzc != null) {
                return zzc;
            }
        }
        return new zzby(str, context, z);
    }

    private zzca zzc(String str, Context context, boolean z) {
        IBinder zza;
        zzd zzac = zze.zzac(context);
        if (z) {
            try {
                zza = ((zzcb) zzcr(context)).zza(str, zzac);
            } catch (RemoteException e) {
                return null;
            } catch (zza e2) {
                return null;
            }
        }
        zza = ((zzcb) zzcr(context)).zzb(str, zzac);
        return zzca.zza.zzd(zza);
    }

    protected zzcb zzb(IBinder iBinder) {
        return zzcb.zza.zze(iBinder);
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return zzb(iBinder);
    }
}
