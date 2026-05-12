package com.google.android.gms.analytics.internal;

import com.google.android.gms.internal.zzlu;

public class zzk extends zzd {
    private final zzlu zzctm = new zzlu();

    zzk(zzf com_google_android_gms_analytics_internal_zzf) {
        super(com_google_android_gms_analytics_internal_zzf);
    }

    public zzlu zzaad() {
        zzzg();
        return this.zzctm;
    }

    public void zzvz() {
        zzap zzwe = zzwe();
        String zzxb = zzwe.zzxb();
        if (zzxb != null) {
            this.zzctm.setAppName(zzxb);
        }
        String zzxc = zzwe.zzxc();
        if (zzxc != null) {
            this.zzctm.setAppVersion(zzxc);
        }
    }

    protected void zzwv() {
        zzyz().zzws().zza(this.zzctm);
        zzvz();
    }
}
