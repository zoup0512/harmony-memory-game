package com.google.android.gms.internal;

import android.view.View;
import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;

@zzin
public class zzdm implements CustomRenderedAd {
    private final zzdn zzben;

    public zzdm(zzdn com_google_android_gms_internal_zzdn) {
        this.zzben = com_google_android_gms_internal_zzdn;
    }

    public String getBaseUrl() {
        try {
            return this.zzben.zzkk();
        } catch (Throwable e) {
            zzb.zzd("Could not delegate getBaseURL to CustomRenderedAd", e);
            return null;
        }
    }

    public String getContent() {
        try {
            return this.zzben.getContent();
        } catch (Throwable e) {
            zzb.zzd("Could not delegate getContent to CustomRenderedAd", e);
            return null;
        }
    }

    public void onAdRendered(View view) {
        try {
            this.zzben.zzi(view != null ? zze.zzac(view) : null);
        } catch (Throwable e) {
            zzb.zzd("Could not delegate onAdRendered to CustomRenderedAd", e);
        }
    }

    public void recordClick() {
        try {
            this.zzben.recordClick();
        } catch (Throwable e) {
            zzb.zzd("Could not delegate recordClick to CustomRenderedAd", e);
        }
    }

    public void recordImpression() {
        try {
            this.zzben.recordImpression();
        } catch (Throwable e) {
            zzb.zzd("Could not delegate recordImpression to CustomRenderedAd", e);
        }
    }
}
