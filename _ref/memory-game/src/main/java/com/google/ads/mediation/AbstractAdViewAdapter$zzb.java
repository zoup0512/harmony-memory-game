package com.google.ads.mediation;

import android.view.View;
import com.google.android.gms.ads.formats.NativeAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;

class AbstractAdViewAdapter$zzb extends NativeContentAdMapper {
    private final NativeContentAd zzfk;

    public AbstractAdViewAdapter$zzb(NativeContentAd nativeContentAd) {
        this.zzfk = nativeContentAd;
        setHeadline(nativeContentAd.getHeadline().toString());
        setImages(nativeContentAd.getImages());
        setBody(nativeContentAd.getBody().toString());
        if (nativeContentAd.getLogo() != null) {
            setLogo(nativeContentAd.getLogo());
        }
        setCallToAction(nativeContentAd.getCallToAction().toString());
        setAdvertiser(nativeContentAd.getAdvertiser().toString());
        setOverrideImpressionRecording(true);
        setOverrideClickHandling(true);
    }

    public void trackView(View view) {
        if (view instanceof NativeAdView) {
            ((NativeAdView) view).setNativeAd(this.zzfk);
        }
    }
}
