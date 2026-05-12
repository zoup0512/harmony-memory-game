package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgn.zza;
import java.util.ArrayList;
import java.util.List;

@zzin
public class zzgs extends zza {
    private final NativeAppInstallAdMapper zzbpm;

    public zzgs(NativeAppInstallAdMapper nativeAppInstallAdMapper) {
        this.zzbpm = nativeAppInstallAdMapper;
    }

    public String getBody() {
        return this.zzbpm.getBody();
    }

    public String getCallToAction() {
        return this.zzbpm.getCallToAction();
    }

    public Bundle getExtras() {
        return this.zzbpm.getExtras();
    }

    public String getHeadline() {
        return this.zzbpm.getHeadline();
    }

    public List getImages() {
        List<Image> images = this.zzbpm.getImages();
        if (images == null) {
            return null;
        }
        List arrayList = new ArrayList();
        for (Image image : images) {
            arrayList.add(new zzc(image.getDrawable(), image.getUri(), image.getScale()));
        }
        return arrayList;
    }

    public boolean getOverrideClickHandling() {
        return this.zzbpm.getOverrideClickHandling();
    }

    public boolean getOverrideImpressionRecording() {
        return this.zzbpm.getOverrideImpressionRecording();
    }

    public String getPrice() {
        return this.zzbpm.getPrice();
    }

    public double getStarRating() {
        return this.zzbpm.getStarRating();
    }

    public String getStore() {
        return this.zzbpm.getStore();
    }

    public void recordImpression() {
        this.zzbpm.recordImpression();
    }

    public void zzk(zzd com_google_android_gms_dynamic_zzd) {
        this.zzbpm.handleClick((View) zze.zzad(com_google_android_gms_dynamic_zzd));
    }

    public zzdr zzku() {
        Image icon = this.zzbpm.getIcon();
        return icon != null ? new zzc(icon.getDrawable(), icon.getUri(), icon.getScale()) : null;
    }

    public void zzl(zzd com_google_android_gms_dynamic_zzd) {
        this.zzbpm.trackView((View) zze.zzad(com_google_android_gms_dynamic_zzd));
    }

    public void zzm(zzd com_google_android_gms_dynamic_zzd) {
        this.zzbpm.untrackView((View) zze.zzad(com_google_android_gms_dynamic_zzd));
    }
}
