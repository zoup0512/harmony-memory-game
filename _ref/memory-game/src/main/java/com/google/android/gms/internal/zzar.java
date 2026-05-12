package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.internal.zzae.zza;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class zzar extends zzaq {
    private static final String TAG = zzar.class.getSimpleName();

    protected zzar(Context context, String str, boolean z) {
        super(context, str, z);
    }

    public static zzar zza(String str, Context context, boolean z) {
        zzaq.zza(context, z);
        return new zzar(context, str, z);
    }

    protected List<Callable<Void>> zzb(zzax com_google_android_gms_internal_zzax, zza com_google_android_gms_internal_zzae_zza) {
        if (com_google_android_gms_internal_zzax.zzcd() == null || !this.zzafn) {
            return super.zzb(com_google_android_gms_internal_zzax, com_google_android_gms_internal_zzae_zza);
        }
        int zzat = com_google_android_gms_internal_zzax.zzat();
        List<Callable<Void>> arrayList = new ArrayList();
        arrayList.addAll(super.zzb(com_google_android_gms_internal_zzax, com_google_android_gms_internal_zzae_zza));
        arrayList.add(new zzbh(com_google_android_gms_internal_zzax, zzav.zzbl(), zzav.zzbm(), com_google_android_gms_internal_zzae_zza, zzat, 24));
        return arrayList;
    }
}
