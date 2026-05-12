package com.mopub.mobileads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appodeal.ads.AppodealSettings;
import com.mopub.common.AdReport;
import com.mopub.common.Constants;
import com.mopub.common.DataKeys;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mraid.MraidController;
import com.mopub.mraid.MraidController$MraidListener;
import com.mopub.mraid.MraidController$UseCustomCloseListener;
import com.mopub.mraid.MraidWebViewDebugListener;
import com.mopub.mraid.PlacementType;
import com.mopub.network.Networking;

public class MraidActivity extends BaseInterstitialActivity {
    @Nullable
    private MraidWebViewDebugListener mDebugListener;
    @Nullable
    private MraidController mMraidController;

    public static void preRenderHtml(@NonNull Context context, @NonNull CustomEventInterstitialListener customEventInterstitialListener, @NonNull String str) {
        preRenderHtml(customEventInterstitialListener, str, new BaseWebView(context));
    }

    @VisibleForTesting
    static void preRenderHtml(@NonNull final CustomEventInterstitialListener customEventInterstitialListener, @NonNull String str, @NonNull BaseWebView baseWebView) {
        baseWebView.enablePlugins(false);
        baseWebView.enableJavascriptCaching();
        if (AppodealSettings.b) {
            baseWebView.getSettings().setJavaScriptEnabled(true);
        }
        baseWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView webView, String str) {
                customEventInterstitialListener.onInterstitialLoaded();
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                return true;
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
                customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
            }
        });
        baseWebView.loadDataWithBaseURL(Networking.getBaseUrlScheme() + "://" + Constants.HOST + "/", str, "text/html", "UTF-8", null);
    }

    public static void start(@NonNull Context context, @Nullable AdReport adReport, @NonNull String str, long j) {
        try {
            context.startActivity(createIntent(context, adReport, str, j));
        } catch (ActivityNotFoundException e) {
            Log.d("MraidInterstitial", "MraidActivity.class not found. Did you declare MraidActivity in your manifest?");
        }
    }

    @VisibleForTesting
    protected static Intent createIntent(@NonNull Context context, @Nullable AdReport adReport, @NonNull String str, long j) {
        Intent intent = new Intent(context, MraidActivity.class);
        intent.putExtra(DataKeys.HTML_RESPONSE_BODY_KEY, str);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, j);
        intent.putExtra(DataKeys.AD_REPORT_KEY, adReport);
        intent.addFlags(268435456);
        return intent;
    }

    public View getAdView() {
        String stringExtra = getIntent().getStringExtra(DataKeys.HTML_RESPONSE_BODY_KEY);
        if (stringExtra == null) {
            MoPubLog.w("MraidActivity received a null HTML body. Finishing the activity.");
            finish();
            return new View(this);
        }
        this.mMraidController = new MraidController(this, this.mAdReport, PlacementType.INTERSTITIAL);
        this.mMraidController.setDebugListener(this.mDebugListener);
        this.mMraidController.setMraidListener(new MraidController$MraidListener() {
            public void onLoaded(View view) {
                MraidActivity.this.mMraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getJavascript());
            }

            public void onFailedToLoad() {
                MoPubLog.d("MraidActivity failed to load. Finishing the activity");
                BaseBroadcastReceiver.broadcastAction(MraidActivity.this, MraidActivity.this.getBroadcastIdentifier().longValue(), EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_FAIL);
                MraidActivity.this.finish();
            }

            public void onClose() {
                MraidActivity.this.mMraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_CLOSE.getJavascript());
                MraidActivity.this.finish();
            }

            public void onExpand() {
            }

            public void onOpen() {
                BaseBroadcastReceiver.broadcastAction(MraidActivity.this, MraidActivity.this.getBroadcastIdentifier().longValue(), EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_CLICK);
            }
        });
        this.mMraidController.setUseCustomCloseListener(new MraidController$UseCustomCloseListener() {
            public void useCustomCloseChanged(boolean z) {
                if (z) {
                    MraidActivity.this.hideInterstitialCloseButton();
                } else {
                    MraidActivity.this.showInterstitialCloseButton();
                }
            }
        });
        this.mMraidController.loadContent(stringExtra);
        return this.mMraidController.getAdContainer();
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        BaseBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_SHOW);
        if (VERSION.SDK_INT >= 14) {
            getWindow().setFlags(16777216, 16777216);
        }
    }

    protected void onPause() {
        if (this.mMraidController != null) {
            this.mMraidController.pause(isFinishing());
        }
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        if (this.mMraidController != null) {
            this.mMraidController.resume();
        }
    }

    protected void onDestroy() {
        if (this.mMraidController != null) {
            this.mMraidController.destroy();
        }
        BaseBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_DISMISS);
        super.onDestroy();
    }

    @VisibleForTesting
    public void setDebugListener(@Nullable MraidWebViewDebugListener mraidWebViewDebugListener) {
        this.mDebugListener = mraidWebViewDebugListener;
        if (this.mMraidController != null) {
            this.mMraidController.setDebugListener(mraidWebViewDebugListener);
        }
    }
}
