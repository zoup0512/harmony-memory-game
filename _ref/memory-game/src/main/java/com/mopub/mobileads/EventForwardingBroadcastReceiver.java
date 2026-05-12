package com.mopub.mobileads;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;

public class EventForwardingBroadcastReceiver extends BaseBroadcastReceiver {
    public static final String ACTION_INTERSTITIAL_CLICK = "com.mopub.action.interstitial.click";
    public static final String ACTION_INTERSTITIAL_DISMISS = "com.mopub.action.interstitial.dismiss";
    public static final String ACTION_INTERSTITIAL_FAIL = "com.mopub.action.interstitial.fail";
    public static final String ACTION_INTERSTITIAL_FINISH = "com.mopub.action.interstitial.finish";
    public static final String ACTION_INTERSTITIAL_SHOW = "com.mopub.action.interstitial.show";
    private static IntentFilter sIntentFilter;
    private final CustomEventInterstitialListener mCustomEventInterstitialListener;

    public EventForwardingBroadcastReceiver(CustomEventInterstitialListener customEventInterstitialListener, long j) {
        super(j);
        this.mCustomEventInterstitialListener = customEventInterstitialListener;
        getIntentFilter();
    }

    @NonNull
    public IntentFilter getIntentFilter() {
        if (sIntentFilter == null) {
            sIntentFilter = new IntentFilter();
            sIntentFilter.addAction(ACTION_INTERSTITIAL_FAIL);
            sIntentFilter.addAction(ACTION_INTERSTITIAL_SHOW);
            sIntentFilter.addAction(ACTION_INTERSTITIAL_DISMISS);
            sIntentFilter.addAction(ACTION_INTERSTITIAL_CLICK);
            sIntentFilter.addAction(ACTION_INTERSTITIAL_FINISH);
        }
        return sIntentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.mCustomEventInterstitialListener != null && shouldConsumeBroadcast(intent)) {
            String action = intent.getAction();
            if (ACTION_INTERSTITIAL_FAIL.equals(action)) {
                this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_INVALID_STATE);
            } else if (ACTION_INTERSTITIAL_SHOW.equals(action)) {
                this.mCustomEventInterstitialListener.onInterstitialShown();
            } else if (ACTION_INTERSTITIAL_DISMISS.equals(action)) {
                this.mCustomEventInterstitialListener.onInterstitialDismissed();
                unregister(this);
            } else if (ACTION_INTERSTITIAL_CLICK.equals(action)) {
                this.mCustomEventInterstitialListener.onInterstitialClicked();
            } else if (ACTION_INTERSTITIAL_FINISH.equals(action)) {
                this.mCustomEventInterstitialListener.onInterstitialFinished();
            }
        }
    }
}
