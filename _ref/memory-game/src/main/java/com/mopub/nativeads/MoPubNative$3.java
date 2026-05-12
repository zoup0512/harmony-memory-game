package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import com.mopub.common.logging.MoPubLog;
import com.mopub.nativeads.CustomEventNative.CustomEventNativeListener;
import com.mopub.network.AdResponse;

class MoPubNative$3 implements CustomEventNativeListener {
    final /* synthetic */ MoPubNative this$0;
    final /* synthetic */ AdResponse val$response;

    MoPubNative$3(MoPubNative moPubNative, AdResponse adResponse) {
        this.this$0 = moPubNative;
        this.val$response = adResponse;
    }

    public void onNativeAdLoaded(@NonNull BaseNativeAd baseNativeAd) {
        Context activityOrDestroy = this.this$0.getActivityOrDestroy();
        if (activityOrDestroy != null) {
            MoPubAdRenderer rendererForAd = this.this$0.mAdRendererRegistry.getRendererForAd(baseNativeAd);
            if (rendererForAd == null) {
                onNativeAdFailed(NativeErrorCode.NATIVE_RENDERER_CONFIGURATION_ERROR);
            } else {
                MoPubNative.access$200(this.this$0).onNativeLoad(new NativeAd(activityOrDestroy, this.val$response.getImpressionTrackingUrl(), this.val$response.getClickTrackingUrl(), MoPubNative.access$100(this.this$0), baseNativeAd, rendererForAd));
            }
        }
    }

    public void onNativeAdFailed(NativeErrorCode nativeErrorCode) {
        MoPubLog.v(String.format("Native Ad failed to load with error: %s.", new Object[]{nativeErrorCode}));
        this.this$0.requestNativeAd(this.val$response.getFailoverUrl());
    }
}
