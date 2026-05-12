package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgo.zza;
import java.util.ArrayList;
import java.util.List;

@zzin
public class zzgt extends zza {
    private final NativeContentAdMapper zzbpn;

    public zzgt(NativeContentAdMapper nativeContentAdMapper) {
        this.zzbpn = nativeContentAdMapper;
    }

    public String getAdvertiser() {
        return this.zzbpn.getAdvertiser();
    }

    public String getBody() {
        return this.zzbpn.getBody();
    }

    public String getCallToAction() {
        return this.zzbpn.getCallToAction();
    }

    public Bundle getExtras() {
        return this.zzbpn.getExtras();
    }

    public String getHeadline() {
        return this.zzbpn.getHeadline();
    }

    public List getImages() {
        List<Image> images = this.zzbpn.getImages();
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
        return this.zzbpn.getOverrideClickHandling();
    }

    public boolean getOverrideImpressionRecording() {
        return this.zzbpn.getOverrideImpressionRecording();
    }

    public void recordImpression() {
        this.zzbpn.recordImpression();
    }

    public void zzk(zzd com_google_android_gms_dynamic_zzd) {
        this.zzbpn.handleClick((View) zze.zzad(com_google_android_gms_dynamic_zzd));
    }

    public zzdr zzky() {
        Image logo = this.zzbpn.getLogo();
        return logo != null ? new zzc(logo.getDrawable(), logo.getUri(), logo.getScale()) : null;
    }

    public void zzl(zzd com_google_android_gms_dynamic_zzd) {
        this.zzbpn.trackView((View) zze.zzad(com_google_android_gms_dynamic_zzd));
    }

    public void zzm(zzd com_google_android_gms_dynamic_zzd) {
        this.zzbpn.untrackView((View) zze.zzad(com_google_android_gms_dynamic_zzd));
    }
}
