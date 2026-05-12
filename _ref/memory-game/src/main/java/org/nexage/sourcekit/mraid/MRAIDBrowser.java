package org.nexage.sourcekit.mraid;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import java.util.ArrayList;

public class MRAIDBrowser extends Activity {
    public static final String MANAGER_EXTRA = "extra_manager";
    private static final String TAG = "MraidBrowser";
    public static final String URL_EXTRA = "extra_url";
    private ImageButton backButton;
    private ImageButton closeButton;
    private ImageButton forwardButton;
    private ImageButton refreshButton;
    private RelativeLayout rootLayout;
    private ArrayList<String> supportedNativeFeatures;
    private WebView webView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(2);
        getWindow().setFeatureInt(2, -1);
        if (getIntent().getExtras() != null) {
            this.supportedNativeFeatures = (ArrayList) getIntent().getExtras().getSerializable(MANAGER_EXTRA);
        }
        createUi();
        setButtonListeners();
        setContentView(this.rootLayout);
        initializeWebView(getIntent());
        enableCookies();
    }

    private void createUi() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        float f = displayMetrics.density;
        int i3 = displayMetrics.densityDpi;
        String str = "screen " + i + "x" + i2 + ", density=" + f + ", densityDpi=" + f + " (";
        switch (i3) {
            case 120:
                str = str + "DENSITY_LOW)";
                break;
            case 160:
                str = str + "DENSITY_MEDIUM)";
                break;
            case 240:
                str = str + "DENSITY_HIGH)";
                break;
            case 320:
                str = str + "DENSITY_XHIGH)";
                break;
        }
        Log.d(TAG, str);
        this.rootLayout = new RelativeLayout(this);
        this.rootLayout.setLayoutParams(new LayoutParams(-1, -1));
        this.rootLayout.setPadding(0, 0, 0, 0);
        this.rootLayout.setBackgroundColor(SupportMenu.CATEGORY_MASK);
        View linearLayout = new LinearLayout(this);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(12);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(0);
        linearLayout.setPadding(0, 0, 0, 0);
        linearLayout.setBackgroundDrawable(Assets.getDrawableFromBase64(getResources(), Assets.bkgrnd));
        linearLayout.setId(1);
        i >>>= 2;
        int min = Math.min(i >>> 1, i2 / 10);
        Log.d(TAG, "button size " + i + "x" + min + " min(" + (i / 2) + "," + (i2 / 10) + ")");
        i2 = min >>> 3;
        Log.d(TAG, "padding " + i2);
        this.backButton = createButton(i, min, i2, Assets.unleftarrow);
        this.forwardButton = createButton(i, min, i2, Assets.unrightarrow);
        this.refreshButton = createButton(i, min, i2, Assets.refresh);
        this.closeButton = createButton(i, min, i2, Assets.mraidClose);
        linearLayout.addView(this.backButton);
        linearLayout.addView(this.forwardButton);
        linearLayout.addView(this.refreshButton);
        linearLayout.addView(this.closeButton);
        this.rootLayout.addView(linearLayout);
        this.webView = new WebView(this);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, -1);
        layoutParams2.addRule(2, linearLayout.getId());
        this.webView.setLayoutParams(layoutParams2);
        this.rootLayout.addView(this.webView);
    }

    ImageButton createButton(int i, int i2, int i3, String str) {
        ImageButton imageButton = new ImageButton(this);
        imageButton.setImageDrawable(Assets.getDrawableFromBase64(getResources(), str));
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i2);
        layoutParams.gravity = 16;
        imageButton.setLayoutParams(layoutParams);
        imageButton.setPadding(0, i3, 0, i3);
        imageButton.setScaleType(ScaleType.FIT_CENTER);
        imageButton.setBackgroundColor(0);
        return imageButton;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initializeWebView(Intent intent) {
        WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        this.webView.loadUrl(intent.getStringExtra(URL_EXTRA));
        this.webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                Toast.makeText((Activity) webView.getContext(), "MRAID error: " + str, 0).show();
            }

            @TargetApi(24)
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str == null) {
                    return false;
                }
                String host = Uri.parse(str).getHost();
                if (!str.startsWith("market:") && !str.startsWith("tel:") && !str.startsWith("voicemail:") && !str.startsWith("sms:") && !str.startsWith("mailto:") && !str.startsWith("geo:") && !str.startsWith("google.streetview:") && !"play.google.com".equals(host) && !"market.android.com".equals(host)) {
                    return false;
                }
                try {
                    if (str.startsWith("tel:")) {
                        if (MRAIDBrowser.this.supportedNativeFeatures.contains("tel")) {
                            MRAIDBrowser.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                        }
                    } else if (!str.startsWith("sms:")) {
                        MRAIDBrowser.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    } else if (MRAIDBrowser.this.supportedNativeFeatures.contains("sms")) {
                        MRAIDBrowser.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    }
                } catch (ActivityNotFoundException e) {
                    Log.w("MoPub", "Unable to start activity for " + str + ". Ensure that your phone can handle this intent.");
                }
                MRAIDBrowser.this.finish();
                return true;
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                MRAIDBrowser.this.forwardButton.setImageDrawable(Assets.getDrawableFromBase64(MRAIDBrowser.this.getResources(), Assets.unrightarrow));
            }

            public void onPageFinished(WebView webView, String str) {
                Drawable drawableFromBase64;
                super.onPageFinished(webView, str);
                if (webView.canGoBack()) {
                    drawableFromBase64 = Assets.getDrawableFromBase64(MRAIDBrowser.this.getResources(), Assets.leftarrow);
                } else {
                    drawableFromBase64 = Assets.getDrawableFromBase64(MRAIDBrowser.this.getResources(), Assets.unleftarrow);
                }
                MRAIDBrowser.this.backButton.setImageDrawable(drawableFromBase64);
                if (webView.canGoForward()) {
                    drawableFromBase64 = Assets.getDrawableFromBase64(MRAIDBrowser.this.getResources(), Assets.rightarrow);
                } else {
                    drawableFromBase64 = Assets.getDrawableFromBase64(MRAIDBrowser.this.getResources(), Assets.unrightarrow);
                }
                MRAIDBrowser.this.forwardButton.setImageDrawable(drawableFromBase64);
            }
        });
        this.webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                Activity activity = (Activity) webView.getContext();
                activity.setTitle("Loading...");
                activity.setProgress(i * 100);
                if (i == 100) {
                    activity.setTitle(webView.getUrl());
                }
            }
        });
    }

    private void setButtonListeners() {
        this.backButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (MRAIDBrowser.this.webView.canGoBack()) {
                    MRAIDBrowser.this.webView.goBack();
                }
            }
        });
        this.forwardButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (MRAIDBrowser.this.webView.canGoForward()) {
                    MRAIDBrowser.this.webView.goForward();
                }
            }
        });
        this.refreshButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MRAIDBrowser.this.webView.reload();
            }
        });
        this.closeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MRAIDBrowser.this.finish();
            }
        });
    }

    private void enableCookies() {
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().startSync();
    }

    protected void onPause() {
        super.onPause();
        CookieSyncManager.getInstance().stopSync();
    }

    protected void onResume() {
        super.onResume();
        CookieSyncManager.getInstance().startSync();
    }
}
