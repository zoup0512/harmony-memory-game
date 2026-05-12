package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.ads.internal.client.zzs.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzin;

@zzin
public final class zzd extends zzg<zzt> {
    public zzd() {
        super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
    }

    public zzs zza(Context context, String str, zzgj com_google_android_gms_internal_zzgj) {
        try {
            return zza.zzl(((zzt) zzcr(context)).zza(zze.zzac(context), str, com_google_android_gms_internal_zzgj, com.google.android.gms.common.internal.zze.xM));
        } catch (Throwable e) {
            zzb.zzd("Could not create remote builder for AdLoader.", e);
            return null;
        } catch (Throwable e2) {
            zzb.zzd("Could not create remote builder for AdLoader.", e2);
            return null;
        }
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return zzg(iBinder);
    }

    protected zzt zzg(IBinder iBinder) {
        return zzt.zza.zzm(iBinder);
    }
}
