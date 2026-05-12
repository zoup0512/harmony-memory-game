package org.nexage.sourcekit.mraid;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import org.nexage.sourcekit.mraid.internal.MRAIDLog;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;

public class MRAIDInterstitial implements MRAIDViewListener {
    private static final String TAG = "MRAID";
    private boolean isReady;
    private MRAIDInterstitialListener listener;
    private MRAIDView mraidView;

    public MRAIDInterstitial(Context context, String str, String str2, String[] strArr, int i, int i2, MRAIDInterstitialListener mRAIDInterstitialListener, MRAIDNativeFeatureListener mRAIDNativeFeatureListener, RtbInfo rtbInfo) {
        this(context, str, str2, strArr, i, i2, mRAIDInterstitialListener, mRAIDNativeFeatureListener, rtbInfo, true, 0);
    }

    public MRAIDInterstitial(Context context, String str, String str2, String[] strArr, int i, int i2, MRAIDInterstitialListener mRAIDInterstitialListener, MRAIDNativeFeatureListener mRAIDNativeFeatureListener, RtbInfo rtbInfo, boolean z) {
        this(context, str, str2, strArr, i, i2, mRAIDInterstitialListener, mRAIDNativeFeatureListener, rtbInfo, z, 0);
    }

    public MRAIDInterstitial(Context context, String str, String str2, String[] strArr, int i, int i2, MRAIDInterstitialListener mRAIDInterstitialListener, MRAIDNativeFeatureListener mRAIDNativeFeatureListener, RtbInfo rtbInfo, boolean z, int i3) {
        this.listener = mRAIDInterstitialListener;
        this.mraidView = new MRAIDView(context, str, str2, strArr, this, mRAIDNativeFeatureListener, true, i, i2, rtbInfo, z, i3);
    }

    public void show() {
        show(null);
    }

    public void show(Activity activity) {
        if (this.isReady) {
            this.mraidView.showAsInterstitial(activity);
        } else {
            MRAIDLog.w(TAG, "interstitial is not ready to show");
        }
    }

    public void mraidViewLoaded(MRAIDView mRAIDView) {
        Log.d("MRAID-ViewListener", "mraidViewLoaded");
        this.isReady = true;
        if (this.listener != null) {
            this.listener.mraidInterstitialLoaded(this);
        }
    }

    public void mraidViewExpand(MRAIDView mRAIDView) {
        Log.d("MRAID-ViewListener", "mraidViewExpand");
        if (this.listener != null) {
            this.listener.mraidInterstitialShow(this);
        }
    }

    public void mraidViewClose(MRAIDView mRAIDView) {
        Log.d("MRAID-ViewListener", "mraidViewClose");
        this.isReady = false;
        this.mraidView = null;
        if (this.listener != null) {
            this.listener.mraidInterstitialHide(this);
        }
    }

    public boolean mraidViewResize(MRAIDView mRAIDView, int i, int i2, int i3, int i4) {
        return true;
    }

    public void mraidViewNoFill(MRAIDView mRAIDView) {
        Log.d("MRAID-ViewListener", "mraidViewClose");
        this.isReady = false;
        this.mraidView = null;
        if (this.listener != null) {
            this.listener.mraidInterstitialNoFill(this);
        }
    }
}
