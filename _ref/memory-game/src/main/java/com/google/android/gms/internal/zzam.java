package com.google.android.gms.internal;

import com.google.android.gms.clearcut.zzb;
import com.google.android.gms.internal.zzad.zza;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class zzam {
    protected static volatile zzb zzaez = null;
    private static volatile Random zzafb = null;
    private static final Object zzafc = new Object();
    private zzax zzaey;
    protected boolean zzafa = false;

    public zzam(zzax com_google_android_gms_internal_zzax) {
        this.zzaey = com_google_android_gms_internal_zzax;
        zzdc.initialize(com_google_android_gms_internal_zzax.getContext());
        this.zzafa = ((Boolean) zzdc.zzbbi.get()).booleanValue();
        if (this.zzafa && zzaez == null) {
            synchronized (zzafc) {
                if (zzaez == null) {
                    zzaez = new zzb(com_google_android_gms_internal_zzax.getContext(), "ADSHIELD", null);
                }
            }
        }
    }

    private static Random zzau() {
        if (zzafb == null) {
            synchronized (zzafc) {
                if (zzafb == null) {
                    zzafb = new Random();
                }
            }
        }
        return zzafb;
    }

    public void zza(int i, int i2, long j) throws IOException {
        try {
            if (this.zzafa && zzaez != null && this.zzaey.zzcj()) {
                zzapv com_google_android_gms_internal_zzad_zza = new zza();
                com_google_android_gms_internal_zzad_zza.zzck = this.zzaey.getContext().getPackageName();
                com_google_android_gms_internal_zzad_zza.zzcl = Long.valueOf(j);
                zzb.zza zzl = zzaez.zzl(zzapv.zzf(com_google_android_gms_internal_zzad_zza));
                zzl.zzez(i2);
                zzl.zzey(i);
                zzl.zze(this.zzaey.zzch());
            }
        } catch (Exception e) {
        }
    }

    public int zzat() {
        try {
            return ThreadLocalRandom.current().nextInt();
        } catch (NoClassDefFoundError e) {
            return zzau().nextInt();
        } catch (RuntimeException e2) {
            return zzau().nextInt();
        }
    }
}
