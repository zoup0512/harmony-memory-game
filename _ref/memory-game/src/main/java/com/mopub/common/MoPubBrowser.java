package com.mopub.common;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.mopub.common.event.BaseEvent.Category;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.event.BaseEvent.SamplingRate;
import com.mopub.common.event.Event.Builder;
import com.mopub.common.event.MoPubEvents;
import com.mopub.common.util.Drawables;
import com.mopub.mobileads.BaseWebView;
import com.mopub.mobileads.util.WebViews;
import com.mopub.volley.DefaultRetryPolicy;

public class MoPubBrowser extends Activity {
    public static final String DESTINATION_URL_KEY = "URL";
    public static final String DSP_CREATIVE_ID = "mopub-dsp-creative-id";
    private static final int INNER_LAYOUT_ID = 1;
    public static final int MOPUB_BROWSER_REQUEST_CODE = 1;
    private DoubleTimeTracker dwellTimeTracker;
    private ImageButton mBackButton;
    private ImageButton mCloseButton;
    private String mDspCreativeId;
    private ImageButton mForwardButton;
    private ImageButton mRefreshButton;
    private WebView mWebView;

    @NonNull
    public ImageButton getBackButton() {
        return this.mBackButton;
    }

    @NonNull
    public ImageButton getCloseButton() {
        return this.mCloseButton;
    }

    @NonNull
    public ImageButton getForwardButton() {
        return this.mForwardButton;
    }

    @NonNull
    public ImageButton getRefreshButton() {
        return this.mRefreshButton;
    }

    @NonNull
    public WebView getWebView() {
        return this.mWebView;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setResult(-1);
        getWindow().requestFeature(2);
        getWindow().setFeatureInt(2, -1);
        setContentView(getMoPubBrowserView());
        this.dwellTimeTracker = new DoubleTimeTracker();
        initializeWebView();
        initializeButtons();
        enableCookies();
    }

    private void initializeWebView() {
        WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        this.mDspCreativeId = getIntent().getStringExtra(DSP_CREATIVE_ID);
        this.mWebView.loadUrl(getIntent().getStringExtra(DESTINATION_URL_KEY));
        this.mWebView.setWebViewClient(new BrowserWebViewClient(this));
        this.mWebView.setWebChromeClient(new 1(this));
    }

    private void initializeButtons() {
        this.mBackButton.setBackgroundColor(0);
        this.mBackButton.setOnClickListener(new 2(this));
        this.mForwardButton.setBackgroundColor(0);
        this.mForwardButton.setOnClickListener(new 3(this));
        this.mRefreshButton.setBackgroundColor(0);
        this.mRefreshButton.setOnClickListener(new 4(this));
        this.mCloseButton.setBackgroundColor(0);
        this.mCloseButton.setOnClickListener(new 5(this));
    }

    private void enableCookies() {
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().startSync();
    }

    protected void onPause() {
        super.onPause();
        CookieSyncManager.getInstance().stopSync();
        WebViews.onPause(this.mWebView, isFinishing());
        this.dwellTimeTracker.pause();
    }

    protected void onResume() {
        super.onResume();
        CookieSyncManager.getInstance().startSync();
        WebViews.onResume(this.mWebView);
        this.dwellTimeTracker.start();
    }

    public void finish() {
        ((ViewGroup) getWindow().getDecorView()).removeAllViews();
        super.finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mWebView.destroy();
        this.mWebView = null;
        MoPubEvents.log(new Builder(Name.AD_DWELL_TIME, Category.AD_INTERACTIONS, SamplingRate.AD_INTERACTIONS.getSamplingRate()).withDspCreativeId(this.mDspCreativeId).withPerformanceDurationMs(Double.valueOf(this.dwellTimeTracker.getInterval())).build());
    }

    private View getMoPubBrowserView() {
        View linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new LayoutParams(-1, -2));
        linearLayout.addView(relativeLayout);
        View linearLayout2 = new LinearLayout(this);
        linearLayout2.setId(1);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        linearLayout2.setLayoutParams(layoutParams);
        linearLayout2.setBackgroundDrawable(Drawables.BACKGROUND.createDrawable(this));
        relativeLayout.addView(linearLayout2);
        this.mBackButton = getButton(Drawables.LEFT_ARROW.createDrawable(this));
        this.mForwardButton = getButton(Drawables.RIGHT_ARROW.createDrawable(this));
        this.mRefreshButton = getButton(Drawables.REFRESH.createDrawable(this));
        this.mCloseButton = getButton(Drawables.CLOSE.createDrawable(this));
        linearLayout2.addView(this.mBackButton);
        linearLayout2.addView(this.mForwardButton);
        linearLayout2.addView(this.mRefreshButton);
        linearLayout2.addView(this.mCloseButton);
        this.mWebView = new BaseWebView(this);
        ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(2, 1);
        this.mWebView.setLayoutParams(layoutParams2);
        relativeLayout.addView(this.mWebView);
        return linearLayout;
    }

    private ImageButton getButton(Drawable drawable) {
        ImageButton imageButton = new ImageButton(this);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        layoutParams.gravity = 16;
        imageButton.setLayoutParams(layoutParams);
        imageButton.setImageDrawable(drawable);
        return imageButton;
    }

    @Deprecated
    @VisibleForTesting
    void setWebView(WebView webView) {
        this.mWebView = webView;
    }
}
