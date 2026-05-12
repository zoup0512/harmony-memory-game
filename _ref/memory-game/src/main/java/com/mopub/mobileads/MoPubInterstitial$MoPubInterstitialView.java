package com.mopub.mobileads;

import android.content.Context;
import android.text.TextUtils;
import com.mopub.common.AdFormat;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.factories.CustomEventInterstitialAdapterFactory;
import java.util.Map;

public class MoPubInterstitial$MoPubInterstitialView extends MoPubView {
    final /* synthetic */ MoPubInterstitial this$0;

    public MoPubInterstitial$MoPubInterstitialView(MoPubInterstitial moPubInterstitial, Context context) {
        this.this$0 = moPubInterstitial;
        super(context);
        setAutorefreshEnabled(false);
    }

    public AdFormat getAdFormat() {
        return AdFormat.INTERSTITIAL;
    }

    protected void loadCustomEvent(String str, Map<String, String> map) {
        if (this.mAdViewController != null) {
            if (TextUtils.isEmpty(str)) {
                MoPubLog.d("Couldn't invoke custom event because the server did not specify one.");
                loadFailUrl(MoPubErrorCode.ADAPTER_NOT_FOUND);
                return;
            }
            if (MoPubInterstitial.access$000(this.this$0) != null) {
                MoPubInterstitial.access$000(this.this$0).invalidate();
            }
            MoPubLog.d("Loading custom event interstitial adapter.");
            MoPubInterstitial.access$002(this.this$0, CustomEventInterstitialAdapterFactory.create(this.this$0, str, map, this.mAdViewController.getBroadcastIdentifier(), this.mAdViewController.getAdReport()));
            MoPubInterstitial.access$000(this.this$0).setAdapterListener(this.this$0);
            MoPubInterstitial.access$000(this.this$0).loadInterstitial();
        }
    }

    protected void trackImpression() {
        MoPubLog.d("Tracking impression for interstitial.");
        if (this.mAdViewController != null) {
            this.mAdViewController.trackImpression();
        }
    }

    protected void adFailed(MoPubErrorCode moPubErrorCode) {
        if (MoPubInterstitial.access$100(this.this$0) != null) {
            MoPubInterstitial.access$100(this.this$0).onInterstitialFailed(this.this$0, moPubErrorCode);
        }
    }
}
