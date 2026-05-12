package com.mopub.mobileads;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.UrlHandler.MoPubSchemeListener;
import com.mopub.common.UrlHandler.ResultActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import java.util.EnumSet;

class HtmlWebViewClient extends WebViewClient {
    static final String MOPUB_FAIL_LOAD = "mopub://failLoad";
    static final String MOPUB_FINISH_LOAD = "mopub://finishLoad";
    private final EnumSet<UrlAction> SUPPORTED_URL_ACTIONS = EnumSet.of(UrlAction.HANDLE_MOPUB_SCHEME, new UrlAction[]{UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.HANDLE_PHONE_SCHEME, UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK});
    private final String mClickthroughUrl;
    private final Context mContext;
    private final String mDspCreativeId;
    private BaseHtmlWebView mHtmlWebView;
    private HtmlWebViewListener mHtmlWebViewListener;
    private final String mRedirectUrl;

    HtmlWebViewClient(HtmlWebViewListener htmlWebViewListener, BaseHtmlWebView baseHtmlWebView, String str, String str2, String str3) {
        this.mHtmlWebViewListener = htmlWebViewListener;
        this.mHtmlWebView = baseHtmlWebView;
        this.mClickthroughUrl = str;
        this.mRedirectUrl = str2;
        this.mDspCreativeId = str3;
        this.mContext = baseHtmlWebView.getContext();
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        new Builder().withDspCreativeId(this.mDspCreativeId).withSupportedUrlActions(this.SUPPORTED_URL_ACTIONS).withResultActions(new ResultActions() {
            public void urlHandlingSucceeded(@NonNull String str, @NonNull UrlAction urlAction) {
                if (HtmlWebViewClient.this.mHtmlWebView.wasClicked()) {
                    HtmlWebViewClient.this.mHtmlWebViewListener.onClicked();
                    HtmlWebViewClient.this.mHtmlWebView.onResetUserClick();
                }
            }

            public void urlHandlingFailed(@NonNull String str, @NonNull UrlAction urlAction) {
            }
        }).withMoPubSchemeListener(new MoPubSchemeListener() {
            public void onFinishLoad() {
                HtmlWebViewClient.this.mHtmlWebViewListener.onLoaded(HtmlWebViewClient.this.mHtmlWebView);
            }

            public void onClose() {
                HtmlWebViewClient.this.mHtmlWebViewListener.onCollapsed();
            }

            public void onFailLoad() {
                HtmlWebViewClient.this.mHtmlWebViewListener.onFailed(MoPubErrorCode.UNSPECIFIED);
            }
        }).build().handleUrl(this.mContext, str, this.mHtmlWebView.wasClicked());
        return true;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.mRedirectUrl != null && str.startsWith(this.mRedirectUrl)) {
            webView.stopLoading();
            if (this.mHtmlWebView.wasClicked()) {
                try {
                    Intents.showMoPubBrowserForUrl(this.mContext, Uri.parse(str), this.mDspCreativeId);
                    return;
                } catch (IntentNotResolvableException e) {
                    MoPubLog.d(e.getMessage());
                    return;
                }
            }
            MoPubLog.d("Attempted to redirect without user interaction");
        }
    }
}
