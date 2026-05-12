package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzhq.zza;

@zzin
public final class zzhu extends zzg<zzhq> {
    public zzhu() {
        super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
    }

    protected zzhq zzaz(IBinder iBinder) {
        return zza.zzaw(iBinder);
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return zzaz(iBinder);
    }

    public zzhp zzg(Activity activity) {
        try {
            return zzhp.zza.zzav(((zzhq) zzcr(activity)).zzo(zze.zzac(activity)));
        } catch (Throwable e) {
            zzb.zzd("Could not create remote InAppPurchaseManager.", e);
            return null;
        } catch (Throwable e2) {
            zzb.zzd("Could not create remote InAppPurchaseManager.", e2);
            return null;
        }
    }
}
