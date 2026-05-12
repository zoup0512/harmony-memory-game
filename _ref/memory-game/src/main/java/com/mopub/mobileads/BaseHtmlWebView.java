package com.mopub.mobileads;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.mopub.common.AdReport;
import com.mopub.common.Constants;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.VersionCode;
import com.mopub.network.Networking;

public class BaseHtmlWebView extends BaseWebView implements ViewGestureDetector$UserClickListener {
    private boolean mClicked;
    private final ViewGestureDetector mViewGestureDetector;

    public BaseHtmlWebView(Context context, AdReport adReport) {
        super(context);
        disableScrollingAndZoom();
        getSettings().setJavaScriptEnabled(true);
        this.mViewGestureDetector = new ViewGestureDetector(context, this, adReport);
        this.mViewGestureDetector.setUserClickListener(this);
        if (VersionCode.currentApiLevel().isAtLeast(VersionCode.ICE_CREAM_SANDWICH)) {
            enablePlugins(true);
        }
        setBackgroundColor(0);
    }

    public void init(boolean z) {
        initializeOnTouchListener(z);
    }

    public void loadUrl(String str) {
        if (str != null) {
            MoPubLog.d("Loading url: " + str);
            if (str.startsWith("javascript:") && !this.mIsDestroyed) {
                super.loadUrl(str);
            }
        }
    }

    private void disableScrollingAndZoom() {
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        getSettings().setSupportZoom(false);
    }

    void loadHtmlResponse(String str) {
        loadDataWithBaseURL(Networking.getBaseUrlScheme() + "://" + Constants.HOST + "/", str, "text/html", "utf-8", null);
    }

    void initializeOnTouchListener(final boolean z) {
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                BaseHtmlWebView.this.mViewGestureDetector.sendTouchEvent(motionEvent);
                return motionEvent.getAction() == 2 && !z;
            }
        });
    }

    public void onUserClick() {
        this.mClicked = true;
    }

    public void onResetUserClick() {
        this.mClicked = false;
    }

    public boolean wasClicked() {
        return this.mClicked;
    }
}
