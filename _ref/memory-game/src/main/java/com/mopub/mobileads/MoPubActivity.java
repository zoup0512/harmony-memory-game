package com.mopub.mobileads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appodeal.ads.AppodealSettings;
import com.mopub.common.AdReport;
import com.mopub.common.CreativeOrientation;
import com.mopub.common.DataKeys;
import com.mopub.common.util.DeviceUtils;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.factories.HtmlInterstitialWebViewFactory;
import java.io.Serializable;

public class MoPubActivity extends BaseInterstitialActivity {
    private HtmlInterstitialWebView mHtmlInterstitialWebView;

    class BroadcastingInterstitialListener implements CustomEventInterstitialListener {
        BroadcastingInterstitialListener() {
        }

        public void onInterstitialLoaded() {
            MoPubActivity.this.mHtmlInterstitialWebView.loadUrl(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getUrl());
        }

        public void onInterstitialFailed(MoPubErrorCode moPubErrorCode) {
            BaseBroadcastReceiver.broadcastAction(MoPubActivity.this, MoPubActivity.this.getBroadcastIdentifier().longValue(), EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_FAIL);
            MoPubActivity.this.finish();
        }

        public void onInterstitialShown() {
        }

        public void onInterstitialClicked() {
            BaseBroadcastReceiver.broadcastAction(MoPubActivity.this, MoPubActivity.this.getBroadcastIdentifier().longValue(), EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_CLICK);
        }

        public void onInterstitialFinished() {
        }

        public void onLeaveApplication() {
        }

        public void onInterstitialDismissed() {
        }
    }

    public static void start(Context context, String str, AdReport adReport, boolean z, String str2, String str3, CreativeOrientation creativeOrientation, long j) {
        try {
            context.startActivity(createIntent(context, str, adReport, z, str2, str3, creativeOrientation, j));
        } catch (ActivityNotFoundException e) {
            Log.d("MoPubActivity", "MoPubActivity not found - did you declare it in AndroidManifest.xml?");
        }
    }

    static Intent createIntent(Context context, String str, AdReport adReport, boolean z, String str2, String str3, CreativeOrientation creativeOrientation, long j) {
        Intent intent = new Intent(context, MoPubActivity.class);
        intent.putExtra(DataKeys.HTML_RESPONSE_BODY_KEY, str);
        intent.putExtra(DataKeys.SCROLLABLE_KEY, z);
        intent.putExtra(DataKeys.CLICKTHROUGH_URL_KEY, str3);
        intent.putExtra(DataKeys.REDIRECT_URL_KEY, str2);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, j);
        intent.putExtra(DataKeys.AD_REPORT_KEY, adReport);
        intent.putExtra(DataKeys.CREATIVE_ORIENTATION_KEY, creativeOrientation);
        intent.addFlags(268435456);
        return intent;
    }

    static void preRenderHtml(Context context, AdReport adReport, final CustomEventInterstitialListener customEventInterstitialListener, String str) {
        HtmlInterstitialWebView create = HtmlInterstitialWebViewFactory.create(context, adReport, customEventInterstitialListener, false, null, null);
        if (AppodealSettings.b) {
            create.getSettings().setJavaScriptEnabled(true);
        }
        create.enablePlugins(false);
        create.enableJavascriptCaching();
        create.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if ("mopub://finishLoad".equals(str)) {
                    customEventInterstitialListener.onInterstitialLoaded();
                } else if ("mopub://failLoad".equals(str)) {
                    customEventInterstitialListener.onInterstitialFailed(null);
                }
                return true;
            }
        });
        create.loadHtmlResponse(str);
    }

    public View getAdView() {
        Intent intent = getIntent();
        boolean booleanExtra = intent.getBooleanExtra(DataKeys.SCROLLABLE_KEY, false);
        String stringExtra = intent.getStringExtra(DataKeys.REDIRECT_URL_KEY);
        String stringExtra2 = intent.getStringExtra(DataKeys.CLICKTHROUGH_URL_KEY);
        String stringExtra3 = intent.getStringExtra(DataKeys.HTML_RESPONSE_BODY_KEY);
        this.mHtmlInterstitialWebView = HtmlInterstitialWebViewFactory.create(getApplicationContext(), this.mAdReport, new BroadcastingInterstitialListener(), booleanExtra, stringExtra, stringExtra2);
        this.mHtmlInterstitialWebView.loadHtmlResponse(stringExtra3);
        return this.mHtmlInterstitialWebView;
    }

    protected void onCreate(Bundle bundle) {
        CreativeOrientation creativeOrientation;
        super.onCreate(bundle);
        Serializable serializableExtra = getIntent().getSerializableExtra(DataKeys.CREATIVE_ORIENTATION_KEY);
        if (serializableExtra == null || !(serializableExtra instanceof CreativeOrientation)) {
            creativeOrientation = CreativeOrientation.UNDEFINED;
        } else {
            creativeOrientation = (CreativeOrientation) serializableExtra;
        }
        DeviceUtils.lockOrientation(this, creativeOrientation);
        BaseBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_SHOW);
    }

    protected void onDestroy() {
        this.mHtmlInterstitialWebView.loadUrl(JavaScriptWebViewCallbacks.WEB_VIEW_DID_CLOSE.getUrl());
        this.mHtmlInterstitialWebView.destroy();
        BaseBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_DISMISS);
        super.onDestroy();
    }
}
