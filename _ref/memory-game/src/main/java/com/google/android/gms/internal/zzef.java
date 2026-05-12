package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzdu.zza;

@zzin
public class zzef extends zzg<zzdu> {
    public zzef() {
        super("com.google.android.gms.ads.NativeAdViewDelegateCreatorImpl");
    }

    protected zzdu zzai(IBinder iBinder) {
        return zza.zzaa(iBinder);
    }

    public zzdt zzb(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        Throwable e;
        try {
            return zzdt.zza.zzz(((zzdu) zzcr(context)).zza(zze.zzac(context), zze.zzac(frameLayout), zze.zzac(frameLayout2), com.google.android.gms.common.internal.zze.xM));
        } catch (RemoteException e2) {
            e = e2;
            zzb.zzd("Could not create remote NativeAdViewDelegate.", e);
            return null;
        } catch (zzg.zza e3) {
            e = e3;
            zzb.zzd("Could not create remote NativeAdViewDelegate.", e);
            return null;
        }
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return zzai(iBinder);
    }
}
