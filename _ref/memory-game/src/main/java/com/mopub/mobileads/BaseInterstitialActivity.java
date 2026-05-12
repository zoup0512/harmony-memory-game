package com.mopub.mobileads;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.mopub.common.AdReport;
import com.mopub.common.CloseableLayout;
import com.mopub.common.CloseableLayout.OnCloseListener;
import com.mopub.common.DataKeys;

abstract class BaseInterstitialActivity extends Activity {
    protected AdReport mAdReport;
    private Long mBroadcastIdentifier;
    private CloseableLayout mCloseableLayout;

    enum JavaScriptWebViewCallbacks {
        WEB_VIEW_DID_APPEAR("webviewDidAppear();"),
        WEB_VIEW_DID_CLOSE("webviewDidClose();");
        
        private String mJavascript;

        private JavaScriptWebViewCallbacks(String str) {
            this.mJavascript = str;
        }

        protected String getJavascript() {
            return this.mJavascript;
        }

        protected String getUrl() {
            return "javascript:" + this.mJavascript;
        }
    }

    public abstract View getAdView();

    BaseInterstitialActivity() {
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.mBroadcastIdentifier = getBroadcastIdentifierFromIntent(intent);
        this.mAdReport = getAdReportFromIntent(intent);
        requestWindowFeature(1);
        getWindow().addFlags(1024);
        View adView = getAdView();
        this.mCloseableLayout = new CloseableLayout(this);
        this.mCloseableLayout.setOnCloseListener(new OnCloseListener() {
            public void onClose() {
                BaseInterstitialActivity.this.finish();
            }
        });
        this.mCloseableLayout.addView(adView, new LayoutParams(-1, -1));
        setContentView(this.mCloseableLayout);
    }

    protected void onDestroy() {
        this.mCloseableLayout.removeAllViews();
        super.onDestroy();
    }

    Long getBroadcastIdentifier() {
        return this.mBroadcastIdentifier;
    }

    protected void showInterstitialCloseButton() {
        this.mCloseableLayout.setCloseVisible(true);
    }

    protected void hideInterstitialCloseButton() {
        this.mCloseableLayout.setCloseVisible(false);
    }

    protected static Long getBroadcastIdentifierFromIntent(Intent intent) {
        if (intent.hasExtra(DataKeys.BROADCAST_IDENTIFIER_KEY)) {
            return Long.valueOf(intent.getLongExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, -1));
        }
        return null;
    }

    @Nullable
    protected static AdReport getAdReportFromIntent(Intent intent) {
        try {
            return (AdReport) intent.getSerializableExtra(DataKeys.AD_REPORT_KEY);
        } catch (ClassCastException e) {
            return null;
        }
    }
}
