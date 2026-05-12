package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.internal.reward.client.zzf;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.internal.zzhh;
import com.google.android.gms.internal.zzhu;
import com.google.android.gms.internal.zzin;

@zzin
public class zzm {
    private static final Object zzamr = new Object();
    private static zzm zzavm;
    private final zza zzavn = new zza();
    private final zzl zzavo = new zzl(new zze(), new zzd(), new zzai(), new zzef(), new zzf(), new zzhu(), new zzhh());

    static {
        zza(new zzm());
    }

    protected zzm() {
    }

    protected static void zza(zzm com_google_android_gms_ads_internal_client_zzm) {
        synchronized (zzamr) {
            zzavm = com_google_android_gms_ads_internal_client_zzm;
        }
    }

    private static zzm zziv() {
        zzm com_google_android_gms_ads_internal_client_zzm;
        synchronized (zzamr) {
            com_google_android_gms_ads_internal_client_zzm = zzavm;
        }
        return com_google_android_gms_ads_internal_client_zzm;
    }

    public static zza zziw() {
        return zziv().zzavn;
    }

    public static zzl zzix() {
        return zziv().zzavo;
    }
}
