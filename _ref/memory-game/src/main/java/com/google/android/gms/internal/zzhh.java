package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzhj.zza;

@zzin
public final class zzhh extends zzg<zzhj> {
    public zzhh() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    protected zzhj zzap(IBinder iBinder) {
        return zza.zzar(iBinder);
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return zzap(iBinder);
    }

    public zzhi zzf(Activity activity) {
        try {
            return zzhi.zza.zzaq(((zzhj) zzcr(activity)).zzn(zze.zzac(activity)));
        } catch (Throwable e) {
            zzb.zzd("Could not create remote AdOverlay.", e);
            return null;
        } catch (Throwable e2) {
            zzb.zzd("Could not create remote AdOverlay.", e2);
            return null;
        }
    }
}
