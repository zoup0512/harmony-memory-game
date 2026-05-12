package org.nexage.sourcekit.mraid;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.VideoActivity;
import org.nexage.sourcekit.mraid.internal.MRAIDLog;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;

public class MRAIDVideoAddendumInterstitial implements MRAIDVideoAddendumViewListener {
    private static final String TAG = "MRAIDVAI";
    private Activity activity;
    private boolean isReady;
    private MRAIDVideoAddendumInterstitialListener listener;
    private MRAIDVideoAddendumView mraidVideoAddendumView;

    public static class Builder {
        private String baseUrl;
        private Context context;
        private String data;
        private int height;
        private MRAIDVideoAddendumInterstitialListener listener;
        private MRAIDNativeFeatureListener nativeFeatureListener;
        private boolean preload = true;
        private RtbInfo rtbInfo = null;
        private boolean skippable = true;
        private String[] supportedNativeFeatures;
        private int width;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setBaseUrl(String str) {
            this.baseUrl = str;
            return this;
        }

        public Builder setData(String str) {
            this.data = str;
            return this;
        }

        public Builder setSupportedNativeFeatures(String[] strArr) {
            this.supportedNativeFeatures = strArr;
            return this;
        }

        public Builder setListener(MRAIDVideoAddendumInterstitialListener mRAIDVideoAddendumInterstitialListener) {
            this.listener = mRAIDVideoAddendumInterstitialListener;
            return this;
        }

        public Builder setNativeFeatureListener(MRAIDNativeFeatureListener mRAIDNativeFeatureListener) {
            this.nativeFeatureListener = mRAIDNativeFeatureListener;
            return this;
        }

        public Builder setWidth(int i) {
            this.width = i;
            return this;
        }

        public Builder setHeight(int i) {
            this.height = i;
            return this;
        }

        public Builder setRtbInfo(RtbInfo rtbInfo) {
            this.rtbInfo = rtbInfo;
            return this;
        }

        public Builder setPreload(boolean z) {
            this.preload = z;
            return this;
        }

        public Builder setSkippable(boolean z) {
            this.skippable = z;
            return this;
        }

        public MRAIDVideoAddendumInterstitial build() {
            return new MRAIDVideoAddendumInterstitial(this.context, this.baseUrl, this.data, this.supportedNativeFeatures, this.width, this.height, this.listener, this.nativeFeatureListener, this.rtbInfo, this.preload, this.skippable);
        }
    }

    private MRAIDVideoAddendumInterstitial(Context context, String str, String str2, String[] strArr, int i, int i2, MRAIDVideoAddendumInterstitialListener mRAIDVideoAddendumInterstitialListener, MRAIDNativeFeatureListener mRAIDNativeFeatureListener, RtbInfo rtbInfo, boolean z, boolean z2) {
        this.listener = mRAIDVideoAddendumInterstitialListener;
        this.mraidVideoAddendumView = new org.nexage.sourcekit.mraid.MRAIDVideoAddendumView.Builder().setContext(context).setBaseUrl(str).setData(str2).setSupportedNativeFeatures(strArr).setListener(this).setNativeFeatureListener(mRAIDNativeFeatureListener).setIsInterstitial(true).setWidth(i).setHeight(i2).setRtbInfo(rtbInfo).setPreload(z).setSkippable(z2).build();
    }

    public void show() {
        show(null);
    }

    public void finish() {
        this.mraidVideoAddendumView.clearView();
    }

    public void show(Activity activity) {
        if (this.isReady) {
            this.activity = activity;
            this.mraidVideoAddendumView.showAsInterstitial(activity);
            return;
        }
        MRAIDLog.w(TAG, "interstitial is not ready to show");
    }

    public void mraidVideoAddendumViewLoaded(MRAIDVideoAddendumView mRAIDVideoAddendumView) {
        Log.d("MRAIDVAI-ViewListener", "mraidViewLoaded");
        this.isReady = true;
        if (this.listener != null) {
            this.listener.mraidVideoAddendumInterstitialLoaded(this);
        }
    }

    public void mraidVideoAddendumViewExpand(MRAIDVideoAddendumView mRAIDVideoAddendumView) {
        Log.d("MRAIDVAI-ViewListener", "mraidViewExpand");
        if (this.listener != null) {
            this.listener.mraidVideoAddendumInterstitialShow(this);
        }
    }

    public void mraidVideoAddendumViewClose(MRAIDVideoAddendumView mRAIDVideoAddendumView) {
        Log.d("MRAIDVAI-ViewListener", "mraidViewClose");
        this.isReady = false;
        this.mraidVideoAddendumView = null;
        this.activity = null;
        if (this.listener != null) {
            this.listener.mraidVideoAddendumInterstitialHide(this);
        }
    }

    public boolean mraidVideoAddendumViewResize(MRAIDVideoAddendumView mRAIDVideoAddendumView, int i, int i2, int i3, int i4) {
        return true;
    }

    public void mraidVideoAddendumViewNoFill(MRAIDVideoAddendumView mRAIDVideoAddendumView) {
        Log.d("MRAIDVAI-ViewListener", "mraidViewClose");
        this.isReady = false;
        this.mraidVideoAddendumView = null;
        if (this.listener != null) {
            this.listener.mraidVideoAddendumInterstitialNoFill(this);
        }
    }

    public void mraidVideoAddendumViewStarted(MRAIDVideoAddendumView mRAIDVideoAddendumView) {
        Log.d("MRAIDVAI-ViewListener", "AdStarted");
        if (this.listener != null) {
            this.listener.mraidVideoAddendumViewStarted(this);
        }
    }

    public void mraidVideoAddendumViewStopped(MRAIDVideoAddendumView mRAIDVideoAddendumView) {
        Log.d("MRAIDVAI-ViewListener", "AdStopped");
        if (this.listener != null) {
            this.listener.mraidVideoAddendumViewStopped(this);
        }
    }

    public void mraidVideoAddendumViewSkipped(MRAIDVideoAddendumView mRAIDVideoAddendumView) {
        Log.d("MRAIDVAI-ViewListener", "AdSkipped");
        if (this.listener != null) {
            this.listener.mraidVideoAddendumViewSkipped(this);
        }
    }

    public void mraidVideoAddendumViewVideoStart(MRAIDVideoAddendumView mRAIDVideoAddendumView) {
        Log.d("MRAIDVAI-ViewListener", "AdVideoStart");
        if (this.listener != null) {
            this.listener.mraidVideoAddendumViewVideoStart(this);
        }
    }

    public void mraidVideoAddendumViewFirstQuartile(MRAIDVideoAddendumView mRAIDVideoAddendumView) {
        Log.d("MRAIDVAI-ViewListener", "AdVideoFirstQuartile");
        if (this.listener != null) {
            this.listener.mraidVideoAddendumViewFirstQuartile(this);
        }
    }

    public void mraidVideoAddendumViewMidpoint(MRAIDVideoAddendumView mRAIDVideoAddendumView) {
        Log.d("MRAIDVAI-ViewListener", "AdVideoMidpoint");
        if (this.listener != null) {
            this.listener.mraidVideoAddendumViewMidpoint(this);
        }
    }

    public void mraidVideoAddendumViewThirdQuartile(MRAIDVideoAddendumView mRAIDVideoAddendumView) {
        Log.d("MRAIDVAI-ViewListener", "AdVideoThirdQuartile");
        if (this.listener != null) {
            this.listener.mraidVideoAddendumViewThirdQuartile(this);
        }
    }

    public void mraidVideoAddendumViewComplete(MRAIDVideoAddendumView mRAIDVideoAddendumView) {
        Log.d("MRAIDVAI-ViewListener", "AdVideoComplete");
        if (this.listener != null) {
            this.listener.mraidVideoAddendumViewComplete(this);
        }
    }

    public void mraidVideoAddendumViewPaused(MRAIDVideoAddendumView mRAIDVideoAddendumView) {
        Log.d("MRAIDVAI-ViewListener", "AdPaused");
        if (this.listener != null) {
            this.listener.mraidVideoAddendumViewPaused(this);
        }
    }

    public void mraidVideoAddendumViewPlaying(MRAIDVideoAddendumView mRAIDVideoAddendumView) {
        Log.d("MRAIDVAI-ViewListener", "AdPlaying");
        if (this.listener != null) {
            this.listener.mraidVideoAddendumViewPlaying(this);
        }
    }

    public void mraidVideoAddendumViewError(MRAIDVideoAddendumView mRAIDVideoAddendumView, String str) {
        Log.d("MRAIDVAI-ViewListener", "AdError");
        if (this.listener != null) {
            this.listener.mraidVideoAddendumViewError(this, str);
        }
    }

    public void mraidVideoAddendumViewClickThru(MRAIDVideoAddendumView mRAIDVideoAddendumView, String str) {
        Log.d("MRAIDVAI-ViewListener", "AdClickThru");
        if (this.listener != null) {
            this.listener.mraidVideoAddendumViewClickThru(this, str);
        }
        this.mraidVideoAddendumView.clearView();
    }

    public void mraidVideoAddendumViewUserClose(MRAIDVideoAddendumView mRAIDVideoAddendumView) {
        Log.d("MRAIDVAI-ViewListener", "AdUserClose");
        if (this.listener != null) {
            this.listener.mraidVideoAddendumViewUserClose(this);
        }
    }

    public void mraidVideoAddendumViewSkippableStateChange(MRAIDVideoAddendumView mRAIDVideoAddendumView, boolean z) {
        Log.d("MRAIDVAI-ViewListener", "AdSkippableStateChange");
        if (this.listener != null) {
            this.listener.mraidVideoAddendumViewSkippableStateChange(this, z);
        }
        try {
            if (this.activity instanceof VideoActivity) {
                ((VideoActivity) this.activity).a(z);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public void mraidVideoAddendumViewLog(MRAIDVideoAddendumView mRAIDVideoAddendumView, String str) {
        Log.d("MRAIDVAI-ViewListener", "AdLog");
        if (this.listener != null) {
            this.listener.mraidVideoAddendumViewLog(this, str);
        }
    }
}
