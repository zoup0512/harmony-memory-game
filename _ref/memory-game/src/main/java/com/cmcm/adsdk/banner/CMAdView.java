package com.cmcm.adsdk.banner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.cmcm.adsdk.CMAdError;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.baseapi.ads.INativeAdLoaderListener;
import com.cmcm.utils.Commons;
import com.cmcm.utils.g;
import com.cmcm.utils.i;
import java.lang.ref.WeakReference;

public class CMAdView extends FrameLayout {
    private static final int DEFAULT_REFRESH_TIME_MILLISECONDS = 30000;
    private static final String TAG = "CMAdView";
    protected CMBannerAdSize adSize;
    protected boolean isFirstLoaded;
    protected boolean mAdWasLoaded;
    private boolean mAutoRefreshEnabled;
    protected CMBannerAdListener mBannerAdListener;
    private WeakReference<Context> mContextRef;
    private Handler mHandler;
    protected boolean mIsViewDestroyed;
    private boolean mPreviousAutoRefreshSetting;
    private final Runnable mRefreshRunnable;
    private long mRefreshTimeMillis;
    private BroadcastReceiver mScreenStateReceiver;
    private int mScreenVisibility;
    private BannerAdManagerRequest managerRequest;
    protected String posid;

    class MyBannerViewLoadListener implements INativeAdLoaderListener {
        MyBannerViewLoadListener() {
        }

        public void adLoaded() {
            g.a(CMAdView.TAG, "adLoaded");
            if (CMAdView.this.managerRequest != null) {
                CMAdView.this.removeAllViews();
                if (CMAdView.this.managerRequest.getAdObject() == null || !(CMAdView.this.managerRequest.getAdObject() instanceof View)) {
                    CMAdView.this.notifyFailed(20000);
                    return;
                }
                CMAdView.this.setViewSize((View) CMAdView.this.managerRequest.getAdObject());
                if (CMAdView.this.mBannerAdListener != null) {
                    if (CMAdView.this.isFirstLoaded) {
                        CMAdView.this.scheduleRefreshTimerIfEnabled();
                    }
                    CMAdView.this.mBannerAdListener.onAdLoaded(CMAdView.this);
                }
            }
        }

        public void adFailedToLoad(int errorCode) {
            g.a(CMAdView.TAG, "onAdLoadFailed");
            CMAdView.this.notifyFailed(errorCode);
            CMAdView.this.scheduleRefreshTimerIfEnabled();
        }

        public void adClicked(INativeAd nativeAd) {
            g.a(CMAdView.TAG, "onAdClicked");
            if (CMAdView.this.mBannerAdListener != null) {
                CMAdView.this.mBannerAdListener.onAdClicked(CMAdView.this);
            }
        }
    }

    public CMAdView(Context context) {
        this(context, null);
    }

    public CMAdView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CMAdView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mPreviousAutoRefreshSetting = true;
        this.mAutoRefreshEnabled = true;
        this.isFirstLoaded = false;
        this.mAdWasLoaded = false;
        this.mIsViewDestroyed = false;
        this.mContextRef = new WeakReference(context);
        this.mScreenVisibility = getVisibility();
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        registerScreenStateBroadcastReceiver();
        this.mRefreshRunnable = new Runnable() {
            public void run() {
                g.b(CMAdView.TAG, "banner refresh runnable execute :" + System.currentTimeMillis());
                CMAdView.this.internalLoadAd();
            }
        };
        this.mRefreshTimeMillis = 30000;
        this.mHandler = new Handler();
    }

    public void setBannerAutorefreshTime(long refreshTimeMillis) {
        if (refreshTimeMillis < 10000 && refreshTimeMillis != 0) {
            refreshTimeMillis = 10000;
        }
        this.mRefreshTimeMillis = refreshTimeMillis;
        setBannerAutorefreshEnabled(refreshTimeMillis != 0);
    }

    public void setAdListener(CMBannerAdListener listener) {
        this.mBannerAdListener = listener;
    }

    public void setPosid(String posid) {
        this.posid = posid;
    }

    public void setAdSize(CMBannerAdSize adSize) {
        this.adSize = adSize;
    }

    public void loadAd() {
        boolean z = true;
        g.a(TAG, "loadAd");
        if (this.mContextRef.get() == null || TextUtils.isEmpty(this.posid) || this.adSize == null) {
            boolean z2;
            String str = TAG;
            StringBuilder append = new StringBuilder().append("params error ,context is null: ");
            if (this.mContextRef.get() == null) {
                z2 = true;
            } else {
                z2 = false;
            }
            StringBuilder append2 = append.append(z2).append("or posid is empty:").append(TextUtils.isEmpty(this.posid)).append("or banner adsize is null:");
            if (this.adSize != null) {
                z = false;
            }
            g.d(str, append2.append(z).toString());
            notifyFailed(CMAdError.PARAMS_ERROR);
            return;
        }
        internalLoadAd();
    }

    protected void internalLoadAd() {
        this.mAdWasLoaded = true;
        if (i.e((Context) this.mContextRef.get())) {
            invalidateView();
            if (this.managerRequest == null) {
                this.managerRequest = new BannerAdManagerRequest((Context) this.mContextRef.get(), this.posid, this.adSize);
            }
            this.managerRequest.setAdListener(new MyBannerViewLoadListener());
            this.managerRequest.loadAd();
            return;
        }
        g.b(TAG, "Can't load an ad because there is no network connectivity.");
        scheduleRefreshTimerIfEnabled();
    }

    public void setBannerAutorefreshEnabled(boolean enabled) {
        setAutorefreshEnabled(enabled);
        this.mPreviousAutoRefreshSetting = enabled;
    }

    protected void onWindowVisibilityChanged(int visibility) {
        boolean z = isScreenVisible(this.mScreenVisibility) != isScreenVisible(visibility);
        g.b(TAG, "window visibility:" + visibility + ",screen visibility:" + this.mScreenVisibility + ",flag:" + z);
        if (z) {
            this.mScreenVisibility = visibility;
            setAdVisibility(this.mScreenVisibility);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        g.a(TAG, "on ad attach to window");
        scheduleRefreshTimerIfEnabled();
    }

    private boolean isScreenVisible(int visibility) {
        return visibility == 0;
    }

    private void registerScreenStateBroadcastReceiver() {
        this.mScreenStateReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (CMAdView.this.isScreenVisible(CMAdView.this.mScreenVisibility) && intent != null) {
                    String action = intent.getAction();
                    if ("android.intent.action.USER_PRESENT".equals(action)) {
                        CMAdView.this.setAdVisibility(0);
                    } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                        CMAdView.this.setAdVisibility(8);
                    }
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        ((Context) this.mContextRef.get()).registerReceiver(this.mScreenStateReceiver, intentFilter);
    }

    private void setAdVisibility(int visibility) {
        if (isScreenVisible(visibility)) {
            unpauseRefresh();
        } else {
            pauseRefresh();
        }
    }

    private void pauseRefresh() {
        this.mPreviousAutoRefreshSetting = this.mAutoRefreshEnabled;
        setAutorefreshEnabled(false);
    }

    private void unpauseRefresh() {
        setAutorefreshEnabled(this.mPreviousAutoRefreshSetting);
    }

    private void setAutorefreshEnabled(boolean enabled) {
        Object obj = (!this.mAdWasLoaded || this.mAutoRefreshEnabled == enabled) ? null : 1;
        if (obj != null) {
            g.b(TAG, "Refresh " + (enabled ? "enabled" : "disabled") + " for posid :" + this.posid);
        }
        this.mAutoRefreshEnabled = enabled;
        if (this.mAdWasLoaded && this.mAutoRefreshEnabled) {
            scheduleRefreshTimerIfEnabled();
        } else if (!this.mAutoRefreshEnabled) {
            cancelRefreshTimer();
        }
    }

    private void cancelRefreshTimer() {
        if (this.mHandler != null) {
            this.mHandler.removeCallbacks(this.mRefreshRunnable);
        }
    }

    protected void scheduleRefreshTimerIfEnabled() {
        cancelRefreshTimer();
        if (this.mAutoRefreshEnabled && !this.mIsViewDestroyed && this.mRefreshTimeMillis > 0) {
            g.b(TAG, "banner record refresh time :" + System.currentTimeMillis());
            this.mHandler.postDelayed(this.mRefreshRunnable, this.mRefreshTimeMillis);
        }
    }

    private void unregisterScreenStateBroadcastReceiver() {
        try {
            ((Context) this.mContextRef.get()).unregisterReceiver(this.mScreenStateReceiver);
        } catch (Exception e) {
            g.b(TAG, "Failed to unregister screen state broadcast receiver (never registered).");
        }
    }

    public void prepare() {
        if (this.managerRequest != null) {
            this.managerRequest.prepare(this);
        }
    }

    private void setViewSize(View cmView) {
        if (cmView != null) {
            LayoutParams layoutParams = null;
            try {
                if (this.adSize == CMBannerAdSize.BANNER_300_250) {
                    layoutParams = new FrameLayout.LayoutParams(Commons.dip2px((Context) this.mContextRef.get(), 300.0f), Commons.dip2px((Context) this.mContextRef.get(), 250.0f), 17);
                } else if (this.adSize == CMBannerAdSize.BANNER_600_314) {
                    layoutParams = new FrameLayout.LayoutParams(Commons.dip2px((Context) this.mContextRef.get(), 600.0f), Commons.dip2px((Context) this.mContextRef.get(), 314.0f), 17);
                } else if (this.adSize == CMBannerAdSize.BANNER_640_960) {
                    layoutParams = new FrameLayout.LayoutParams(Commons.dip2px((Context) this.mContextRef.get(), 640.0f), Commons.dip2px((Context) this.mContextRef.get(), 960.0f), 17);
                } else if (this.adSize == CMBannerAdSize.BANNER_320_50) {
                    layoutParams = new FrameLayout.LayoutParams(Commons.dip2px((Context) this.mContextRef.get(), 300.0f), Commons.dip2px((Context) this.mContextRef.get(), 50.0f), 17);
                }
                addView(cmView, layoutParams);
            } catch (Exception e) {
                notifyFailed(CMAdError.INTERNAL_ERROR);
            }
        }
    }

    private void notifyFailed(int errorCode) {
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.adFailedToLoad(this, errorCode);
        }
    }

    protected void invalidateView() {
        if (this.managerRequest != null) {
            this.isFirstLoaded = true;
            this.managerRequest.destroy();
        }
    }

    public void onDestroy() {
        g.a(TAG, "onDestroy");
        unregisterScreenStateBroadcastReceiver();
        this.mBannerAdListener = null;
        invalidateView();
        this.mIsViewDestroyed = true;
    }
}
