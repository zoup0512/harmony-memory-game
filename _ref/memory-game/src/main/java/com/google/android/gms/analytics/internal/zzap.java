package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public class zzap extends zzd {
    protected int I;
    protected boolean aA;
    protected boolean az;
    protected boolean zzcsj;
    protected String zzcum;
    protected String zzcun;
    protected int zzcze;

    public zzap(zzf com_google_android_gms_analytics_internal_zzf) {
        super(com_google_android_gms_analytics_internal_zzf);
    }

    private static int zzfd(String str) {
        String toLowerCase = str.toLowerCase();
        return "verbose".equals(toLowerCase) ? 0 : "info".equals(toLowerCase) ? 1 : "warning".equals(toLowerCase) ? 2 : "error".equals(toLowerCase) ? 3 : -1;
    }

    public int getLogLevel() {
        zzzg();
        return this.zzcze;
    }

    void zza(zzaa com_google_android_gms_analytics_internal_zzaa) {
        int zzfd;
        zzeh("Loading global XML config values");
        if (com_google_android_gms_analytics_internal_zzaa.zzacp()) {
            String zzxb = com_google_android_gms_analytics_internal_zzaa.zzxb();
            this.zzcum = zzxb;
            zzb("XML config - app name", zzxb);
        }
        if (com_google_android_gms_analytics_internal_zzaa.zzacq()) {
            zzxb = com_google_android_gms_analytics_internal_zzaa.zzxc();
            this.zzcun = zzxb;
            zzb("XML config - app version", zzxb);
        }
        if (com_google_android_gms_analytics_internal_zzaa.zzacr()) {
            zzfd = zzfd(com_google_android_gms_analytics_internal_zzaa.zzacs());
            if (zzfd >= 0) {
                this.zzcze = zzfd;
                zza("XML config - log level", Integer.valueOf(zzfd));
            }
        }
        if (com_google_android_gms_analytics_internal_zzaa.zzact()) {
            zzfd = com_google_android_gms_analytics_internal_zzaa.zzacu();
            this.I = zzfd;
            this.az = true;
            zzb("XML config - dispatch period (sec)", Integer.valueOf(zzfd));
        }
        if (com_google_android_gms_analytics_internal_zzaa.zzacv()) {
            boolean zzacw = com_google_android_gms_analytics_internal_zzaa.zzacw();
            this.zzcsj = zzacw;
            this.aA = true;
            zzb("XML config - dry run", Boolean.valueOf(zzacw));
        }
    }

    public boolean zzacr() {
        zzzg();
        return false;
    }

    public boolean zzact() {
        zzzg();
        return this.az;
    }

    public boolean zzacv() {
        zzzg();
        return this.aA;
    }

    public boolean zzacw() {
        zzzg();
        return this.zzcsj;
    }

    public int zzaek() {
        zzzg();
        return this.I;
    }

    protected void zzael() {
        ApplicationInfo applicationInfo;
        Context context = getContext();
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 129);
        } catch (NameNotFoundException e) {
            zzd("PackageManager doesn't know about the app package", e);
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            zzek("Couldn't get ApplicationInfo to load global config");
            return;
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle != null) {
            int i = bundle.getInt("com.google.android.gms.analytics.globalConfigResource");
            if (i > 0) {
                zzaa com_google_android_gms_analytics_internal_zzaa = (zzaa) new zzz(zzyu()).zzbx(i);
                if (com_google_android_gms_analytics_internal_zzaa != null) {
                    zza(com_google_android_gms_analytics_internal_zzaa);
                }
            }
        }
    }

    protected void zzwv() {
        zzael();
    }

    public String zzxb() {
        zzzg();
        return this.zzcum;
    }

    public String zzxc() {
        zzzg();
        return this.zzcun;
    }
}
