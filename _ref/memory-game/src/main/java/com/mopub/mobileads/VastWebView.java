package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.Utils;
import com.mopub.common.util.VersionCode;
import com.mopub.network.Networking;

class VastWebView extends BaseWebView {
    @Nullable
    VastWebViewClickListener mVastWebViewClickListener;

    VastWebView(Context context) {
        super(context);
        disableScrollingAndZoom();
        getSettings().setJavaScriptEnabled(true);
        if (VersionCode.currentApiLevel().isAtLeast(VersionCode.ICE_CREAM_SANDWICH)) {
            enablePlugins(true);
        }
        setBackgroundColor(0);
        setOnTouchListener(new VastWebViewOnTouchListener(this));
        setId((int) Utils.generateUniqueId());
    }

    void loadData(String str) {
        loadDataWithBaseURL(Networking.getBaseUrlScheme() + "://" + Constants.HOST + "/", str, "text/html", "utf-8", null);
    }

    void setVastWebViewClickListener(@NonNull VastWebViewClickListener vastWebViewClickListener) {
        this.mVastWebViewClickListener = vastWebViewClickListener;
    }

    private void disableScrollingAndZoom() {
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        getSettings().setSupportZoom(false);
        setScrollBarStyle(0);
    }

    @NonNull
    static VastWebView createView(@NonNull Context context, @NonNull VastResource vastResource) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(vastResource);
        VastWebView vastWebView = new VastWebView(context);
        vastResource.initializeWebView(vastWebView);
        return vastWebView;
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    VastWebViewClickListener getVastWebViewClickListener() {
        return this.mVastWebViewClickListener;
    }
}
