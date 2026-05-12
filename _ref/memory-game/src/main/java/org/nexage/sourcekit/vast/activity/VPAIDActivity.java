package org.nexage.sourcekit.vast.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.ConsoleMessage.MessageLevel;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.appodeal.ads.ao.b;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.util.Timer;
import java.util.TimerTask;
import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.vast.VASTPlayer;
import org.nexage.sourcekit.vast.VASTPlayer.VASTPlayerListener;
import org.nexage.sourcekit.vast.model.VASTModel;

public class VPAIDActivity extends Activity {
    private static final long SKIP_TIMER_INTERVAL = 1000;
    private static String TAG = "VPAIDActivity";
    private final int LOADER_TIMEOUT_MS = 10000;
    private boolean canSkip = false;
    private Context context;
    private Handler handler;
    private String html;
    private boolean isClosed = false;
    VASTPlayerListener mListener;
    private boolean mPaused = false;
    private ProgressBar mProgressBar;
    private RelativeLayout mRootLayout;
    private int mScreenHeight;
    private int mScreenWidth;
    private TextView mSkipText;
    private int mSkipTime = 5;
    private Timer mSkipTimer;
    private boolean mStarted = false;
    private b mType;
    private VASTModel mVastModel = null;
    private WebView mWebView;
    private String url;
    private boolean videoStarted = false;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getResources().getConfiguration().orientation != 2) {
            setRequestedOrientation(0);
        } else {
            hideTitleStatusBars();
            this.context = this;
            Intent intent = getIntent();
            this.url = intent.getStringExtra("android.net.url");
            if (intent.hasExtra("com.nexage.android.vast.player.type")) {
                this.mType = (b) intent.getExtras().get("com.nexage.android.vast.player.type");
            } else {
                VASTLog.e(TAG, "video type undefined.");
                this.mType = b.a;
            }
            this.mListener = VASTPlayer.listener;
            this.mVastModel = (VASTModel) intent.getSerializableExtra("com.nexage.android.vast.player.vastModel");
            Display defaultDisplay = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            if (VERSION.SDK_INT >= 13) {
                defaultDisplay.getSize(point);
            } else {
                point.x = defaultDisplay.getWidth();
                point.y = defaultDisplay.getHeight();
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            float f = displayMetrics.density;
            int i = point.x;
            int identifier = this.context.getResources().getIdentifier("status_bar_height", "dimen", AbstractSpiCall.ANDROID_CLIENT_TYPE);
            if (identifier > 0) {
                identifier = this.context.getResources().getDimensionPixelSize(identifier);
            } else {
                identifier = 0;
            }
            identifier = point.y - identifier;
            this.mScreenWidth = Math.round(((float) i) / f);
            this.mScreenHeight = Math.round(((float) identifier) / f);
            if (this.mVastModel != null) {
                this.mSkipTime = this.mSkipTime > this.mVastModel.getSkipoffset() ? this.mSkipTime : this.mVastModel.getSkipoffset();
            }
            createUi();
        }
        if (this.mListener != null) {
            this.mListener.vastShown();
        }
    }

    private void createUi() {
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        createRootLayout(layoutParams);
        createWebView(layoutParams);
        createProgressBar();
        createSkipText();
        setContentView(this.mRootLayout);
        this.html = "<html>\n<head>\n    <title>IMA HTML5 Simple Demo</title>\n    <script src=\"https://s3.amazonaws.com/appodeal-externallibs/android/ima3.js\"></script>\n    <style>\n        #mainContainer {\n            position: relative;\n            width: " + this.mScreenWidth + ";\n            height: " + this.mScreenHeight + "px;\n        }\n\n        #content, #adContainer {\n            position: absolute;\n            top: 0px;\n            left: 0px;\n            width: " + this.mScreenWidth + ";\n            height: " + this.mScreenHeight + ";\n        }\n\n        #contentElement {\n            width: " + this.mScreenWidth + ";\n            height: " + this.mScreenHeight + ";\n            overflow: hidden;\n        }\n    </style>\n</head>\n\n<body style='margin:0;padding:0;background-color: black; position: fixed;'>\n<div id=\"mainContainer\">\n    <div id=\"content\">\n        <video id=\"contentElement\" poster=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNg+A8AAQIBANEay48AAAAASUVORK5CYII=\">\n        </video>\n    </div>\n    <div id=\"adContainer\"></div>\n</div>\n<script type=\"text/javascript\">\n        var videoContent = document.getElementById('contentElement');\n        videoContent.play();\n\n        var videoContent = document.getElementById('contentElement');\n        var adDisplayContainer = new google.ima.AdDisplayContainer(\n                        document.getElementById('adContainer'),\n                        videoContent);\n        // Must be done as the result of a user action on mobile\n        adDisplayContainer.initialize();\n\n        // Re-use this AdsLoader instance for the entire lifecycle of your page.\n        var adsLoader = new google.ima.AdsLoader(adDisplayContainer);\n        adsLoader.getSettings().setVpaidMode(google.ima.ImaSdkSettings.VpaidMode.INSECURE);\n\n        // Add event listeners\n        adsLoader.addEventListener(\n                google.ima.AdsManagerLoadedEvent.Type.ADS_MANAGER_LOADED,\n                onAdsManagerLoaded,\n                false);\n        adsLoader.addEventListener(\n                google.ima.AdErrorEvent.Type.AD_ERROR,\n                onAdError,\n                false);\n\n        function onAdError(adErrorEvent) {\n            // Handle the error logging and destroy the AdsManager\n            console.error(JSON.stringify(adErrorEvent.getError()));\n            Android.close();\n            adsManager.destroy();\n        }\n\n        // An event listener to tell the SDK that our content video\n        // is completed so the SDK can play any post-roll ads.\n        var contentEndedListener = function () {\n            adsLoader.contentComplete();\n        };\n        videoContent.onended = contentEndedListener;\n\n        // Request video ads.\n        var adsRequest = new google.ima.AdsRequest();\n        adsRequest.adTagUrl = '" + this.url + "';\n\n        // Specify the linear and nonlinear slot sizes. This helps the SDK to\n        // select the correct creative if multiple are returned.\n        adsRequest.linearAdSlotWidth = " + this.mScreenWidth + ";\n        adsRequest.linearAdSlotHeight = " + this.mScreenHeight + ";\n        adsRequest.nonLinearAdSlotWidth = " + this.mScreenWidth + ";\n        adsRequest.nonLinearAdSlotHeight = " + this.mScreenHeight + ";\n\n        function requestAds() {\n            adsLoader.requestAds(adsRequest);\n        }\n\n        function onAdsManagerLoaded(adsManagerLoadedEvent) {\n            // Get the ads manager.\n            adsManager = adsManagerLoadedEvent.getAdsManager(\n                    videoContent);  // See API reference for contentPlayback\n\n            // Add listeners to the required events.\n            adsManager.addEventListener(\n                    google.ima.AdErrorEvent.Type.AD_ERROR,\n                    onAdAdError);\n            adsManager.addEventListener(\n                    google.ima.AdEvent.Type.CONTENT_PAUSE_REQUESTED,\n                    onContentPauseRequested);\n            adsManager.addEventListener(\n                    google.ima.AdEvent.Type.CONTENT_RESUME_REQUESTED,\n                    onContentResumeRequested);\n            adsManager.addEventListener(\n                    google.ima.AdEvent.Type.ALL_ADS_COMPLETED,\n                    onAdCompleted);\n            adsManager.addEventListener(\n                    google.ima.AdEvent.Type.COMPLETE,\n                    onAdCompleted);\n            adsManager.addEventListener(\n                    google.ima.AdEvent.Type.LOADED,\n                    onAdLoaded);\n            adsManager.addEventListener(\n                    google.ima.AdEvent.Type.SKIPPED,\n                    onAdSkipped);\n            adsManager.addEventListener(\n                    google.ima.AdEvent.Type.STARTED,\n                    onAdStarted);\n            Android.loaded();\n        }\n\n        function startAd() {\n            try {\n                // Initialize the ads manager. Ad rules playlist will start at this time.\n                adsManager.init(" + this.mScreenWidth + ", " + this.mScreenHeight + ", google.ima.ViewMode.NORMAL);\n                // Call start to show ads. Single video and overlay ads will\n                // start at this time; this call will be ignored for ad rules, as ad rules\n                // ads start when the adsManager is initialized.\n                adsManager.start();\n            } catch (adError) {\n                console.error(JSON.stringify(adError));\n                Android.close();\n                // An error may be thrown if there was a problem with the VAST response.\n            }\n        }\n        function onAdStarted () {\n            Android.started();\n        }        function onAdCompleted () {\n           console.log('ad completed');\n            Android.finish();\n        }        function onAdAdError () {\n            console.error('ad error');\n            Android.close();\n        }        function onAdSkipped () {\n            console.log('skipped');\n            Android.close();\n        }        function onAdLoaded () {\n           \n        }\n        function onContentPauseRequested() {\n            // This function is where you should setup UI for showing ads (e.g.\n            // display ad timer countdown, disable seeking, etc.)\n            videoContent.removeEventListener('ended', contentEndedListener);\n            videoContent.pause();\n        }\n\n        function onContentResumeRequested() {\n            // This function is where you should ensure that your UI is ready\n            // to play content.\n            videoContent.addEventListener('ended', contentEndedListener);\n            videoContent.play();\n        }\n\n</script>\n</body>\n</html>";
    }

    private void startWebView() {
        this.mStarted = true;
        this.mWebView.loadDataWithBaseURL("http://localhost", this.html, "text/html", "utf-8", null);
        showProgressBar();
        this.handler = new Handler();
        this.handler.postDelayed(new Runnable() {
            public void run() {
                VPAIDActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        if (!VPAIDActivity.this.videoStarted) {
                            VASTLog.d(VPAIDActivity.TAG, "can skip while loading");
                            VPAIDActivity.this.showSkipButton();
                        }
                    }
                });
            }
        }, 10000);
    }

    private void createRootLayout(LayoutParams layoutParams) {
        this.mRootLayout = new RelativeLayout(this);
        this.mRootLayout.setLayoutParams(layoutParams);
        this.mRootLayout.setPadding(0, 0, 0, 0);
        this.mRootLayout.setBackgroundColor(-16777216);
    }

    private void createWebView(LayoutParams layoutParams) {
        this.mWebView = new WebView(this);
        this.mWebView.setLayoutParams(layoutParams);
        this.mWebView.getSettings().setLoadsImagesAutomatically(true);
        this.mWebView.getSettings().setPluginState(PluginState.ON);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        if (VERSION.SDK_INT >= 17) {
            this.mWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        this.mWebView.addJavascriptInterface(new WebAppInterface(this, this), "Android");
        this.mWebView.getSettings().setAppCacheEnabled(true);
        this.mWebView.getSettings().setDomStorageEnabled(true);
        this.mWebView.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                VASTLog.d(VPAIDActivity.TAG, consoleMessage.message() + " in " + consoleMessage.sourceId() + " (Line: " + consoleMessage.lineNumber() + ")");
                if (!(consoleMessage.messageLevel() != MessageLevel.ERROR || consoleMessage.sourceId() == null || consoleMessage.lineNumber() == 0)) {
                    VPAIDActivity.this.closeVPAID();
                }
                return true;
            }
        });
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                if (VPAIDActivity.this.mWebView != null && VPAIDActivity.this.mStarted && !VPAIDActivity.this.mPaused) {
                    VPAIDActivity.this.mWebView.loadUrl("javascript:requestAds()");
                }
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!str.startsWith("about:")) {
                    VPAIDActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
                return true;
            }

            @TargetApi(24)
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
            }
        });
        this.mRootLayout.addView(this.mWebView);
    }

    private void createSkipText() {
        this.mSkipText = new TextView(this);
        this.mSkipText.setVisibility(4);
        this.mSkipText.setTextColor(-1);
        this.mSkipText.setPadding(5, 5, 5, 5);
        this.mSkipText.setBackgroundColor(Color.parseColor("#6b000000"));
        this.mRootLayout.addView(this.mSkipText);
    }

    private void createProgressBar() {
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(13);
        this.mProgressBar = new ProgressBar(this);
        this.mProgressBar.setLayoutParams(layoutParams);
        this.mRootLayout.addView(this.mProgressBar);
        this.mProgressBar.setVisibility(8);
    }

    private void showProgressBar() {
        this.mProgressBar.setVisibility(0);
    }

    private void hideProgressBar() {
        this.mProgressBar.setVisibility(8);
        startSkipTimer();
    }

    private void hideTitleStatusBars() {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
    }

    private void startSkipTimer() {
        if (this.mSkipTime != 0 && this.mType == b.a && !this.canSkip) {
            this.mSkipTimer = new Timer();
            this.mSkipTimer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    VPAIDActivity.this.mSkipTime = VPAIDActivity.this.mSkipTime - 1;
                    if (VPAIDActivity.this.mSkipTime <= 0) {
                        VPAIDActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                VPAIDActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        VPAIDActivity.this.showSkipButton();
                                    }
                                });
                            }
                        });
                        cancel();
                        return;
                    }
                    VPAIDActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            final String str = "You can skip this video in " + String.valueOf(VPAIDActivity.this.mSkipTime) + " seconds";
                            VPAIDActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    VPAIDActivity.this.mSkipText.setVisibility(0);
                                    VPAIDActivity.this.mSkipText.setText(str);
                                }
                            });
                        }
                    });
                }
            }, 0, SKIP_TIMER_INTERVAL);
        }
    }

    private void showSkipButton() {
        if (!this.canSkip) {
            this.mSkipText.setText("Skip video");
            this.canSkip = true;
            this.mSkipText.setVisibility(0);
            this.mSkipText.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VPAIDActivity.this.closeVPAID();
                }
            });
        }
    }

    private void closeVPAID() {
        VASTLog.d(TAG, "closeVPAID");
        this.isClosed = true;
        if (this.mListener != null) {
            this.mListener.vastDismiss();
        }
        finishVPAID();
    }

    private void finishVPAID() {
        VASTLog.d(TAG, "finishVPAID");
        if (!(this.mListener == null || this.isClosed)) {
            this.mListener.vastComplete();
            this.mListener.vastDismiss();
        }
        finish();
    }

    private boolean isSkippable() {
        return this.canSkip;
    }

    public void onBackPressed() {
        if (isSkippable()) {
            closeVPAID();
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.mStarted) {
            this.mPaused = false;
            if (this.videoStarted) {
                showSkipButton();
            }
        }
        restartWebView();
    }

    protected void onPause() {
        super.onPause();
        if (this.mStarted) {
            this.mPaused = true;
            destroyWebView();
        }
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
        }
    }

    public void restartWebView() {
        if (this.mWebView != null) {
            if (VERSION.SDK_INT >= 11) {
                this.mWebView.onResume();
            } else {
                try {
                    Class.forName("android.webkit.WebView").getMethod("onResume", (Class[]) null).invoke(this.mWebView, (Object[]) null);
                } catch (Exception e) {
                    VASTLog.e(TAG, e.getMessage());
                }
            }
            startWebView();
        }
    }

    public void destroyWebView() {
        if (this.mWebView != null) {
            VASTLog.d(TAG, "destroyWebView");
            this.mWebView.loadUrl("about:blank");
            if (VERSION.SDK_INT >= 11) {
                this.mWebView.onPause();
                return;
            }
            try {
                Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(this.mWebView, (Object[]) null);
            } catch (Exception e) {
                VASTLog.e(TAG, e.getMessage());
            }
        }
    }
}
