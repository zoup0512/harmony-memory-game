package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzdk;
import com.google.android.gms.internal.zzdo;
import com.google.android.gms.internal.zzft;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzhg;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzjo;
import com.google.android.gms.internal.zzju;
import com.google.android.gms.internal.zzju.zza;
import com.google.android.gms.internal.zzkh;
import com.google.android.gms.internal.zzlh;

@zzin
public abstract class zzc extends zzb implements zzh, zzhg {
    public zzc(Context context, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj, VersionInfoParcel versionInfoParcel, zzd com_google_android_gms_ads_internal_zzd) {
        super(context, adSizeParcel, str, com_google_android_gms_internal_zzgj, versionInfoParcel, com_google_android_gms_ads_internal_zzd);
    }

    protected zzlh zza(zza com_google_android_gms_internal_zzju_zza, @Nullable zze com_google_android_gms_ads_internal_zze, @Nullable zzjo com_google_android_gms_internal_zzjo) {
        zzlh com_google_android_gms_internal_zzlh = null;
        View nextView = this.zzajs.zzaox.getNextView();
        if (nextView instanceof zzlh) {
            com_google_android_gms_internal_zzlh = (zzlh) nextView;
            if (((Boolean) zzdc.zzazz.get()).booleanValue()) {
                zzb.zzcv("Reusing webview...");
                com_google_android_gms_internal_zzlh.zza(this.zzajs.zzagf, this.zzajs.zzapa, this.zzajn);
            } else {
                com_google_android_gms_internal_zzlh.destroy();
                com_google_android_gms_internal_zzlh = null;
            }
        }
        if (com_google_android_gms_internal_zzlh == null) {
            if (nextView != null) {
                this.zzajs.zzaox.removeView(nextView);
            }
            com_google_android_gms_internal_zzlh = zzu.zzfr().zza(this.zzajs.zzagf, this.zzajs.zzapa, false, false, this.zzajs.zzaov, this.zzajs.zzaow, this.zzajn, this, this.zzajv);
            if (this.zzajs.zzapa.zzaut == null) {
                zzb(com_google_android_gms_internal_zzlh.getView());
            }
        }
        zzft com_google_android_gms_internal_zzft = com_google_android_gms_internal_zzlh;
        com_google_android_gms_internal_zzft.zzuj().zza(this, this, this, this, false, this, null, com_google_android_gms_ads_internal_zze, this, com_google_android_gms_internal_zzjo);
        zza(com_google_android_gms_internal_zzft);
        com_google_android_gms_internal_zzft.zzcz(com_google_android_gms_internal_zzju_zza.zzcip.zzcbg);
        return com_google_android_gms_internal_zzft;
    }

    public void zza(int i, int i2, int i3, int i4) {
        zzdt();
    }

    public void zza(zzdo com_google_android_gms_internal_zzdo) {
        zzab.zzhi("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzajs.zzapq = com_google_android_gms_internal_zzdo;
    }

    protected void zza(zzft com_google_android_gms_internal_zzft) {
        com_google_android_gms_internal_zzft.zza("/trackActiveViewUnit", new 1(this));
    }

    protected void zza(zza com_google_android_gms_internal_zzju_zza, zzdk com_google_android_gms_internal_zzdk) {
        zzjo com_google_android_gms_internal_zzjo = null;
        if (com_google_android_gms_internal_zzju_zza.errorCode != -2) {
            zzkh.zzclc.post(new 2(this, com_google_android_gms_internal_zzju_zza));
            return;
        }
        if (com_google_android_gms_internal_zzju_zza.zzapa != null) {
            this.zzajs.zzapa = com_google_android_gms_internal_zzju_zza.zzapa;
        }
        if (!com_google_android_gms_internal_zzju_zza.zzciq.zzcby || com_google_android_gms_internal_zzju_zza.zzciq.zzauw) {
            if (((Boolean) zzdc.zzbdf.get()).booleanValue()) {
                com_google_android_gms_internal_zzjo = this.zzajv.zzakm.zza(this.zzajs.zzagf, com_google_android_gms_internal_zzju_zza.zzciq);
            }
            zzkh.zzclc.post(new 3(this, com_google_android_gms_internal_zzju_zza, com_google_android_gms_internal_zzjo, com_google_android_gms_internal_zzdk));
            return;
        }
        this.zzajs.zzapw = 0;
        this.zzajs.zzaoz = zzu.zzfp().zza(this.zzajs.zzagf, this, com_google_android_gms_internal_zzju_zza, this.zzajs.zzaov, null, this.zzajz, this, com_google_android_gms_internal_zzdk);
    }

    protected boolean zza(@Nullable zzju com_google_android_gms_internal_zzju, zzju com_google_android_gms_internal_zzju2) {
        if (this.zzajs.zzgp() && this.zzajs.zzaox != null) {
            this.zzajs.zzaox.zzgv().zzcs(com_google_android_gms_internal_zzju2.zzccd);
        }
        return super.zza(com_google_android_gms_internal_zzju, com_google_android_gms_internal_zzju2);
    }

    public void zzc(View view) {
        this.zzajs.zzapv = view;
        zzb(new zzju(this.zzajs.zzapc, null, null, null, null, null, null, null));
    }

    public void zzeh() {
        onAdClicked();
    }

    public void zzei() {
        recordImpression();
        zzdp();
    }

    public void zzej() {
        zzdr();
    }
}
