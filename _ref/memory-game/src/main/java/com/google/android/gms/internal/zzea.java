package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.List;

@zzin
public class zzea implements NativeCustomTemplateAd {
    private final zzdz zzbhh;

    public zzea(zzdz com_google_android_gms_internal_zzdz) {
        this.zzbhh = com_google_android_gms_internal_zzdz;
    }

    public List<String> getAvailableAssetNames() {
        try {
            return this.zzbhh.getAvailableAssetNames();
        } catch (Throwable e) {
            zzb.zzb("Failed to get available asset names.", e);
            return null;
        }
    }

    public String getCustomTemplateId() {
        try {
            return this.zzbhh.getCustomTemplateId();
        } catch (Throwable e) {
            zzb.zzb("Failed to get custom template id.", e);
            return null;
        }
    }

    public Image getImage(String str) {
        try {
            zzdr zzau = this.zzbhh.zzau(str);
            if (zzau != null) {
                return new zzds(zzau);
            }
        } catch (Throwable e) {
            zzb.zzb("Failed to get image.", e);
        }
        return null;
    }

    public CharSequence getText(String str) {
        try {
            return this.zzbhh.zzat(str);
        } catch (Throwable e) {
            zzb.zzb("Failed to get string.", e);
            return null;
        }
    }

    public void performClick(String str) {
        try {
            this.zzbhh.performClick(str);
        } catch (Throwable e) {
            zzb.zzb("Failed to perform click.", e);
        }
    }

    public void recordImpression() {
        try {
            this.zzbhh.recordImpression();
        } catch (Throwable e) {
            zzb.zzb("Failed to record impression.", e);
        }
    }
}
