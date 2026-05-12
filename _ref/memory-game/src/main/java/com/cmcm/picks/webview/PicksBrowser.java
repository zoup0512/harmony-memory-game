package com.cmcm.picks.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.cmcm.adsdk.R;
import com.mopub.common.MoPubBrowser;

public class PicksBrowser extends Activity implements OnClickListener {
    private BaseWebView a;
    private ImageView b;
    private ImageView c;
    private ImageView d;
    private ImageView e;
    private LinearLayout f;

    @NonNull
    public ImageView a() {
        return this.b;
    }

    @NonNull
    public ImageView b() {
        return this.c;
    }

    @NonNull
    public WebView c() {
        return this.a;
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent(context, PicksBrowser.class);
        intent.putExtra(MoPubBrowser.DESTINATION_URL_KEY, str);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(-1);
        getWindow().requestFeature(2);
        getWindow().setFeatureInt(2, -1);
        setContentView(R.layout.activity_picks_browser);
        d();
        e();
        f();
        g();
    }

    private void d() {
        this.f = (LinearLayout) findViewById(R.id.panel_ll);
        this.b = (ImageView) findViewById(R.id.browser_back);
        this.c = (ImageView) findViewById(R.id.browser_forward);
        this.e = (ImageView) findViewById(R.id.browser_close);
        this.d = (ImageView) findViewById(R.id.browser_refresh);
        this.f.setBackgroundDrawable(getResources().getDrawable(R.drawable.browser_background));
        this.b.setImageDrawable(getResources().getDrawable(R.drawable.browser_left_arrow));
        this.c.setImageDrawable(getResources().getDrawable(R.drawable.browser_right_arrow));
        this.e.setImageDrawable(getResources().getDrawable(R.drawable.browser_close));
        this.d.setImageDrawable(getResources().getDrawable(R.drawable.browser_refresh));
        this.a = (BaseWebView) findViewById(R.id.webview);
    }

    @SuppressLint({"JavascriptInterface"})
    private void e() {
        WebSettings settings = this.a.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
        this.a.loadUrl(getIntent().getStringExtra(MoPubBrowser.DESTINATION_URL_KEY));
        this.a.setWebViewClient(new a(this));
        this.a.setWebChromeClient(new WebChromeClient(this) {
            final /* synthetic */ PicksBrowser a;

            {
                this.a = r1;
            }

            public void onProgressChanged(WebView webView, int progress) {
                this.a.setTitle("Loading...");
                this.a.setProgress(progress * 100);
                if (progress == 100) {
                    this.a.setTitle(webView.getUrl());
                }
            }
        });
    }

    private void f() {
        a(this.b);
        a(this.c);
        a(this.d);
        a(this.e);
    }

    private void a(ImageView imageView) {
        if (imageView != null) {
            imageView.setBackgroundColor(0);
            imageView.setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        int hashCode = view.hashCode();
        if (this.b.hashCode() == hashCode) {
            if (this.a.canGoBack()) {
                this.a.goBack();
            }
        } else if (this.c.hashCode() == hashCode) {
            if (this.a.canGoForward()) {
                this.a.goForward();
            }
        } else if (this.d.hashCode() == hashCode) {
            this.a.reload();
        } else if (this.e.hashCode() == hashCode) {
            finish();
        }
    }

    private void g() {
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().startSync();
    }

    protected void onPause() {
        super.onPause();
        CookieSyncManager.getInstance().stopSync();
        c.a(this.a, isFinishing());
    }

    protected void onResume() {
        super.onResume();
        CookieSyncManager.getInstance().startSync();
        c.a(this.a);
    }

    public void finish() {
        ((ViewGroup) getWindow().getDecorView()).removeAllViews();
        super.finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.a.destroy();
        this.a = null;
    }
}
