package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.common.CreativeOrientation;
import com.mopub.common.DataKeys;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import java.util.Map;

public class HtmlInterstitial extends ResponseBodyInterstitial {
    private String mClickthroughUrl;
    private String mHtmlData;
    private boolean mIsScrollable;
    @NonNull
    private CreativeOrientation mOrientation;
    private String mRedirectUrl;

    protected void extractExtras(Map<String, String> map) {
        this.mHtmlData = (String) map.get(DataKeys.HTML_RESPONSE_BODY_KEY);
        this.mIsScrollable = Boolean.valueOf((String) map.get(DataKeys.SCROLLABLE_KEY)).booleanValue();
        this.mRedirectUrl = (String) map.get(DataKeys.REDIRECT_URL_KEY);
        this.mClickthroughUrl = (String) map.get(DataKeys.CLICKTHROUGH_URL_KEY);
        this.mOrientation = CreativeOrientation.fromHeader((String) map.get(DataKeys.CREATIVE_ORIENTATION_KEY));
    }

    protected void preRenderHtml(CustomEventInterstitialListener customEventInterstitialListener) {
        MoPubActivity.preRenderHtml(this.mContext, this.mAdReport, customEventInterstitialListener, this.mHtmlData);
    }

    public void showInterstitial() {
        MoPubActivity.start(this.mContext, this.mHtmlData, this.mAdReport, this.mIsScrollable, this.mRedirectUrl, this.mClickthroughUrl, this.mOrientation, this.mBroadcastIdentifier);
    }
}
