package com.mopub.mobileads;

import android.app.Activity;
import android.location.Location;
import com.mopub.common.VisibleForTesting;
import com.mopub.mobileads.CustomEventInterstitialAdapter.CustomEventInterstitialAdapterListener;
import java.util.Map;

public class MoPubInterstitial implements CustomEventInterstitialAdapterListener {
    private Activity mActivity;
    private String mAdUnitId;
    private InterstitialState mCurrentInterstitialState;
    private CustomEventInterstitialAdapter mCustomEventInterstitialAdapter;
    private InterstitialAdListener mInterstitialAdListener;
    private MoPubInterstitialView mInterstitialView = new MoPubInterstitialView(this, this.mActivity);
    private boolean mIsDestroyed;

    public MoPubInterstitial(Activity activity, String str) {
        this.mActivity = activity;
        this.mAdUnitId = str;
        this.mInterstitialView.setAdUnitId(this.mAdUnitId);
        this.mCurrentInterstitialState = InterstitialState.NOT_READY;
    }

    public void load() {
        resetCurrentInterstitial();
        this.mInterstitialView.loadAd();
    }

    public void forceRefresh() {
        resetCurrentInterstitial();
        this.mInterstitialView.forceRefresh();
    }

    private void resetCurrentInterstitial() {
        this.mCurrentInterstitialState = InterstitialState.NOT_READY;
        if (this.mCustomEventInterstitialAdapter != null) {
            this.mCustomEventInterstitialAdapter.invalidate();
            this.mCustomEventInterstitialAdapter = null;
        }
        this.mIsDestroyed = false;
    }

    public boolean isReady() {
        return this.mCurrentInterstitialState.isReady();
    }

    boolean isDestroyed() {
        return this.mIsDestroyed;
    }

    public boolean show() {
        switch (1.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState[this.mCurrentInterstitialState.ordinal()]) {
            case 1:
                showCustomEventInterstitial();
                return true;
            default:
                return false;
        }
    }

    private void showCustomEventInterstitial() {
        if (this.mCustomEventInterstitialAdapter != null) {
            this.mCustomEventInterstitialAdapter.showInterstitial();
        }
    }

    Integer getAdTimeoutDelay() {
        return this.mInterstitialView.getAdTimeoutDelay();
    }

    MoPubInterstitialView getMoPubInterstitialView() {
        return this.mInterstitialView;
    }

    public void setKeywords(String str) {
        this.mInterstitialView.setKeywords(str);
    }

    public String getKeywords() {
        return this.mInterstitialView.getKeywords();
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public Location getLocation() {
        return this.mInterstitialView.getLocation();
    }

    public void destroy() {
        this.mIsDestroyed = true;
        if (this.mCustomEventInterstitialAdapter != null) {
            this.mCustomEventInterstitialAdapter.invalidate();
            this.mCustomEventInterstitialAdapter = null;
        }
        this.mInterstitialView.setBannerAdListener(null);
        this.mInterstitialView.destroy();
    }

    public void setInterstitialAdListener(InterstitialAdListener interstitialAdListener) {
        this.mInterstitialAdListener = interstitialAdListener;
    }

    public InterstitialAdListener getInterstitialAdListener() {
        return this.mInterstitialAdListener;
    }

    public void setTesting(boolean z) {
        this.mInterstitialView.setTesting(z);
    }

    public boolean getTesting() {
        return this.mInterstitialView.getTesting();
    }

    public void setLocalExtras(Map<String, Object> map) {
        this.mInterstitialView.setLocalExtras(map);
    }

    public Map<String, Object> getLocalExtras() {
        return this.mInterstitialView.getLocalExtras();
    }

    public void onCustomEventInterstitialLoaded() {
        if (!this.mIsDestroyed) {
            this.mCurrentInterstitialState = InterstitialState.CUSTOM_EVENT_AD_READY;
            if (this.mInterstitialAdListener != null) {
                this.mInterstitialAdListener.onInterstitialLoaded(this);
            }
        }
    }

    public void onCustomEventInterstitialFailed(MoPubErrorCode moPubErrorCode) {
        if (!isDestroyed()) {
            this.mCurrentInterstitialState = InterstitialState.NOT_READY;
            this.mInterstitialView.loadFailUrl(moPubErrorCode);
        }
    }

    public void onCustomEventInterstitialShown() {
        if (!isDestroyed()) {
            this.mInterstitialView.trackImpression();
            if (this.mInterstitialAdListener != null) {
                this.mInterstitialAdListener.onInterstitialShown(this);
            }
        }
    }

    public void onCustomEventInterstitialClicked() {
        if (!isDestroyed()) {
            this.mInterstitialView.registerClick();
            if (this.mInterstitialAdListener != null) {
                this.mInterstitialAdListener.onInterstitialClicked(this);
            }
        }
    }

    public void onCustomEventInterstitialFinished() {
        if (!isDestroyed() && this.mInterstitialAdListener != null) {
            this.mInterstitialAdListener.onInterstitialFinished(this);
        }
    }

    public void onCustomEventInterstitialDismissed() {
        if (!isDestroyed()) {
            this.mCurrentInterstitialState = InterstitialState.NOT_READY;
            if (this.mInterstitialAdListener != null) {
                this.mInterstitialAdListener.onInterstitialDismissed(this);
            }
        }
    }

    @Deprecated
    @VisibleForTesting
    void setInterstitialView(MoPubInterstitialView moPubInterstitialView) {
        this.mInterstitialView = moPubInterstitialView;
    }

    public MoPubInterstitialView getmInterstitialView() {
        return this.mInterstitialView;
    }
}
