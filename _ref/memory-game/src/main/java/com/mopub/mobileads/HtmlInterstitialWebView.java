package com.mopub.mobileads;

import android.content.Context;
import android.os.Handler;
import com.mopub.common.AdReport;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;

public class HtmlInterstitialWebView extends BaseHtmlWebView {
    private Handler mHandler = new Handler();

    static class HtmlInterstitialWebViewListener implements HtmlWebViewListener {
        private final CustomEventInterstitialListener mCustomEventInterstitialListener;

        public HtmlInterstitialWebViewListener(CustomEventInterstitialListener customEventInterstitialListener) {
            this.mCustomEventInterstitialListener = customEventInterstitialListener;
        }

        public void onLoaded(BaseHtmlWebView baseHtmlWebView) {
            this.mCustomEventInterstitialListener.onInterstitialLoaded();
        }

        public void onFailed(MoPubErrorCode moPubErrorCode) {
            this.mCustomEventInterstitialListener.onInterstitialFailed(moPubErrorCode);
        }

        public void onClicked() {
            this.mCustomEventInterstitialListener.onInterstitialClicked();
        }

        public void onCollapsed() {
        }
    }

    public HtmlInterstitialWebView(Context context, AdReport adReport) {
        super(context, adReport);
    }

    public void init(CustomEventInterstitialListener customEventInterstitialListener, boolean z, String str, String str2, String str3) {
        super.init(z);
        setWebViewClient(new HtmlWebViewClient(new HtmlInterstitialWebViewListener(customEventInterstitialListener), this, str2, str, str3));
    }

    private void postHandlerRunnable(Runnable runnable) {
        this.mHandler.post(runnable);
    }
}
