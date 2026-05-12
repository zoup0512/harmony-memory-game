package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.internal.zzae.zza;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class zzap extends zzaq {
    private static final String TAG = zzap.class.getSimpleName();
    private Info zzafm;

    protected zzap(Context context) {
        super(context, "");
    }

    public static zzap zze(Context context) {
        zzaq.zza(context, true);
        return new zzap(context);
    }

    public String zza(String str, String str2) {
        return zzak.zza(str, str2, true);
    }

    public void zza(Info info) {
        this.zzafm = info;
    }

    protected void zza(zzax com_google_android_gms_internal_zzax, zza com_google_android_gms_internal_zzae_zza) {
        if (!com_google_android_gms_internal_zzax.zzci()) {
            zza(zzb(com_google_android_gms_internal_zzax, com_google_android_gms_internal_zzae_zza));
        } else if (this.zzafm != null) {
            Object id = this.zzafm.getId();
            if (!TextUtils.isEmpty(id)) {
                com_google_android_gms_internal_zzae_zza.zzeg = zzay.zzo(id);
                com_google_android_gms_internal_zzae_zza.zzeh = Integer.valueOf(5);
                com_google_android_gms_internal_zzae_zza.zzei = Boolean.valueOf(this.zzafm.isLimitAdTrackingEnabled());
            }
            this.zzafm = null;
        }
    }

    protected List<Callable<Void>> zzb(zzax com_google_android_gms_internal_zzax, zza com_google_android_gms_internal_zzae_zza) {
        List<Callable<Void>> arrayList = new ArrayList();
        if (com_google_android_gms_internal_zzax.zzcd() == null) {
            return arrayList;
        }
        zzax com_google_android_gms_internal_zzax2 = com_google_android_gms_internal_zzax;
        arrayList.add(new zzbh(com_google_android_gms_internal_zzax2, zzav.zzbl(), zzav.zzbm(), com_google_android_gms_internal_zzae_zza, com_google_android_gms_internal_zzax.zzat(), 24));
        return arrayList;
    }

    protected zza zzd(Context context) {
        return null;
    }
}
