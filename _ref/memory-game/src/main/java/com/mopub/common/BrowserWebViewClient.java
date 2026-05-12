package com.mopub.common;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.UrlHandler.ResultActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Drawables;
import java.util.EnumSet;

class BrowserWebViewClient extends WebViewClient {
    private static final EnumSet<UrlAction> SUPPORTED_URL_ACTIONS = EnumSet.of(UrlAction.HANDLE_PHONE_SCHEME, new UrlAction[]{UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK});
    @NonNull
    private MoPubBrowser mMoPubBrowser;

    public BrowserWebViewClient(@NonNull MoPubBrowser moPubBrowser) {
        this.mMoPubBrowser = moPubBrowser;
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        MoPubLog.d("MoPubBrowser error: " + str);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return new Builder().withSupportedUrlActions(SUPPORTED_URL_ACTIONS).withoutMoPubBrowser().withResultActions(new ResultActions() {
            public void urlHandlingSucceeded(@NonNull String str, @NonNull UrlAction urlAction) {
                if (urlAction.equals(UrlAction.OPEN_IN_APP_BROWSER)) {
                    BrowserWebViewClient.this.mMoPubBrowser.getWebView().loadUrl(str);
                } else {
                    BrowserWebViewClient.this.mMoPubBrowser.finish();
                }
            }

            public void urlHandlingFailed(@NonNull String str, @NonNull UrlAction urlAction) {
            }
        }).build().handleResolvedUrl(this.mMoPubBrowser.getApplicationContext(), str, true, null);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        this.mMoPubBrowser.getForwardButton().setImageDrawable(Drawables.UNRIGHT_ARROW.createDrawable(this.mMoPubBrowser));
    }

    public void onPageFinished(WebView webView, String str) {
        Drawable createDrawable;
        super.onPageFinished(webView, str);
        if (webView.canGoBack()) {
            createDrawable = Drawables.LEFT_ARROW.createDrawable(this.mMoPubBrowser);
        } else {
            createDrawable = Drawables.UNLEFT_ARROW.createDrawable(this.mMoPubBrowser);
        }
        this.mMoPubBrowser.getBackButton().setImageDrawable(createDrawable);
        if (webView.canGoForward()) {
            createDrawable = Drawables.RIGHT_ARROW.createDrawable(this.mMoPubBrowser);
        } else {
            createDrawable = Drawables.UNRIGHT_ARROW.createDrawable(this.mMoPubBrowser);
        }
        this.mMoPubBrowser.getForwardButton().setImageDrawable(createDrawable);
    }
}
