package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.ads.internal.client.zzz.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzin;

@zzin
public class zzai extends zzg<zzaa> {
    public zzai() {
        super("com.google.android.gms.ads.MobileAdsSettingManagerCreatorImpl");
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return zzv(iBinder);
    }

    public zzz zzm(Context context) {
        try {
            return zza.zzr(((zzaa) zzcr(context)).zza(zze.zzac(context), com.google.android.gms.common.internal.zze.xM));
        } catch (Throwable e) {
            zzb.zzd("Could not get remote MobileAdsSettingManager.", e);
            return null;
        } catch (Throwable e2) {
            zzb.zzd("Could not get remote MobileAdsSettingManager.", e2);
            return null;
        }
    }

    protected zzaa zzv(IBinder iBinder) {
        return zzaa.zza.zzs(iBinder);
    }
}
