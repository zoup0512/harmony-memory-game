package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import com.google.android.gms.internal.zzdk;
import com.google.android.gms.internal.zzdl;
import com.google.android.gms.internal.zzdn;
import com.google.android.gms.internal.zzjo;
import com.google.android.gms.internal.zzju.zza;
import com.google.android.gms.internal.zzkd;
import com.google.android.gms.internal.zzlh;

class zzc$3 implements Runnable {
    final /* synthetic */ zzc zzakd;
    final /* synthetic */ zza zzake;
    final /* synthetic */ zzjo zzakf;
    final /* synthetic */ zzdk zzakg;

    zzc$3(zzc com_google_android_gms_ads_internal_zzc, zza com_google_android_gms_internal_zzju_zza, zzjo com_google_android_gms_internal_zzjo, zzdk com_google_android_gms_internal_zzdk) {
        this.zzakd = com_google_android_gms_ads_internal_zzc;
        this.zzake = com_google_android_gms_internal_zzju_zza;
        this.zzakf = com_google_android_gms_internal_zzjo;
        this.zzakg = com_google_android_gms_internal_zzdk;
    }

    public void run() {
        if (this.zzake.zzciq.zzcch && this.zzakd.zzajs.zzapq != null) {
            String str = null;
            if (this.zzake.zzciq.zzbto != null) {
                str = zzu.zzfq().zzco(this.zzake.zzciq.zzbto);
            }
            zzdn com_google_android_gms_internal_zzdl = new zzdl(this.zzakd, str, this.zzake.zzciq.body);
            this.zzakd.zzajs.zzapw = 1;
            try {
                this.zzakd.zzajq = false;
                this.zzakd.zzajs.zzapq.zza(com_google_android_gms_internal_zzdl);
                return;
            } catch (Throwable e) {
                zzkd.zzd("Could not call the onCustomRenderedAdLoadedListener.", e);
                this.zzakd.zzajq = true;
            }
        }
        final zze com_google_android_gms_ads_internal_zze = new zze(this.zzakd.zzajs.zzagf, this.zzake);
        zzlh zza = this.zzakd.zza(this.zzake, com_google_android_gms_ads_internal_zze, this.zzakf);
        zza.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ zzc$3 zzaki;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                com_google_android_gms_ads_internal_zze.recordClick();
                return false;
            }
        });
        zza.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ zzc$3 zzaki;

            public void onClick(View view) {
                com_google_android_gms_ads_internal_zze.recordClick();
            }
        });
        this.zzakd.zzajs.zzapw = 0;
        this.zzakd.zzajs.zzaoz = zzu.zzfp().zza(this.zzakd.zzajs.zzagf, this.zzakd, this.zzake, this.zzakd.zzajs.zzaov, zza, this.zzakd.zzajz, this.zzakd, this.zzakg);
    }
}
