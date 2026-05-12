package com.mopub.mraid;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.mopub.common.AdReport;
import com.mopub.common.CloseableLayout;
import com.mopub.common.CloseableLayout.ClosePosition;
import com.mopub.common.Preconditions;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.common.util.Views;
import com.mopub.mobileads.MraidVideoPlayerActivity;
import com.mopub.mobileads.util.WebViews;
import com.mopub.mraid.MraidBridge.MraidBridgeListener;
import com.mopub.mraid.MraidBridge.MraidWebView;
import java.lang.ref.WeakReference;
import java.net.URI;

public class MraidController {
    private final AdReport mAdReport;
    private boolean mAllowOrientationChange;
    @NonNull
    private final CloseableLayout mCloseableAdContainer;
    @NonNull
    private final Context mContext;
    @Nullable
    private MraidWebViewDebugListener mDebugListener;
    @NonNull
    private final FrameLayout mDefaultAdContainer;
    private MraidOrientation mForceOrientation;
    private boolean mIsPaused;
    @NonNull
    private final MraidBridge mMraidBridge;
    private final MraidBridgeListener mMraidBridgeListener;
    @Nullable
    private MraidListener mMraidListener;
    private final MraidNativeCommandHandler mMraidNativeCommandHandler;
    @Nullable
    private MraidWebView mMraidWebView;
    @Nullable
    private UseCustomCloseListener mOnCloseButtonListener;
    @NonNull
    private OrientationBroadcastReceiver mOrientationBroadcastReceiver;
    @Nullable
    private Integer mOriginalActivityOrientation;
    @NonNull
    private final PlacementType mPlacementType;
    @Nullable
    private ViewGroup mRootView;
    @NonNull
    private final MraidScreenMetrics mScreenMetrics;
    @NonNull
    private final ScreenMetricsWaiter mScreenMetricsWaiter;
    @NonNull
    private final MraidBridge mTwoPartBridge;
    private final MraidBridgeListener mTwoPartBridgeListener;
    @Nullable
    private MraidWebView mTwoPartWebView;
    @NonNull
    private ViewState mViewState;
    @NonNull
    private final WeakReference<Activity> mWeakActivity;

    public MraidController(@NonNull Context context, @Nullable AdReport adReport, @NonNull PlacementType placementType) {
        this(context, adReport, placementType, new MraidBridge(adReport, placementType), new MraidBridge(adReport, PlacementType.INTERSTITIAL), new ScreenMetricsWaiter());
    }

    @VisibleForTesting
    MraidController(@NonNull Context context, @Nullable AdReport adReport, @NonNull PlacementType placementType, @NonNull MraidBridge mraidBridge, @NonNull MraidBridge mraidBridge2, @NonNull ScreenMetricsWaiter screenMetricsWaiter) {
        this.mViewState = ViewState.LOADING;
        this.mOrientationBroadcastReceiver = new OrientationBroadcastReceiver(this);
        this.mAllowOrientationChange = true;
        this.mForceOrientation = MraidOrientation.NONE;
        this.mMraidBridgeListener = new 3(this);
        this.mTwoPartBridgeListener = new 4(this);
        this.mContext = context.getApplicationContext();
        Preconditions.checkNotNull(this.mContext);
        this.mAdReport = adReport;
        if (context instanceof Activity) {
            this.mWeakActivity = new WeakReference((Activity) context);
        } else {
            this.mWeakActivity = new WeakReference(null);
        }
        this.mPlacementType = placementType;
        this.mMraidBridge = mraidBridge;
        this.mTwoPartBridge = mraidBridge2;
        this.mScreenMetricsWaiter = screenMetricsWaiter;
        this.mViewState = ViewState.LOADING;
        this.mScreenMetrics = new MraidScreenMetrics(this.mContext, this.mContext.getResources().getDisplayMetrics().density);
        this.mDefaultAdContainer = new FrameLayout(this.mContext);
        this.mCloseableAdContainer = new CloseableLayout(this.mContext);
        this.mCloseableAdContainer.setOnCloseListener(new 1(this));
        View view = new View(this.mContext);
        view.setOnTouchListener(new 2(this));
        this.mCloseableAdContainer.addView(view, new LayoutParams(-1, -1));
        this.mOrientationBroadcastReceiver.register(this.mContext);
        this.mMraidBridge.setMraidBridgeListener(this.mMraidBridgeListener);
        this.mTwoPartBridge.setMraidBridgeListener(this.mTwoPartBridgeListener);
        this.mMraidNativeCommandHandler = new MraidNativeCommandHandler();
    }

    public void setMraidListener(@Nullable MraidListener mraidListener) {
        this.mMraidListener = mraidListener;
    }

    public void setUseCustomCloseListener(@Nullable UseCustomCloseListener useCustomCloseListener) {
        this.mOnCloseButtonListener = useCustomCloseListener;
    }

    public void setDebugListener(@Nullable MraidWebViewDebugListener mraidWebViewDebugListener) {
        this.mDebugListener = mraidWebViewDebugListener;
    }

    public void loadContent(@NonNull String str) {
        Preconditions.checkState(this.mMraidWebView == null, "loadContent should only be called once");
        this.mMraidWebView = new MraidWebView(this.mContext);
        this.mMraidBridge.attachView(this.mMraidWebView);
        this.mDefaultAdContainer.addView(this.mMraidWebView, new LayoutParams(-1, -1));
        this.mMraidBridge.setContentHtml(str);
    }

    private int getDisplayRotation() {
        return ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    @VisibleForTesting
    boolean handleConsoleMessage(@NonNull ConsoleMessage consoleMessage) {
        if (this.mDebugListener != null) {
            return this.mDebugListener.onConsoleMessage(consoleMessage);
        }
        return true;
    }

    @VisibleForTesting
    boolean handleJsAlert(@NonNull String str, @NonNull JsResult jsResult) {
        if (this.mDebugListener != null) {
            return this.mDebugListener.onJsAlert(str, jsResult);
        }
        jsResult.confirm();
        return true;
    }

    @Nullable
    private View getCurrentWebView() {
        return this.mTwoPartBridge.isAttached() ? this.mTwoPartWebView : this.mMraidWebView;
    }

    private boolean isInlineVideoAvailable() {
        Activity activity = (Activity) this.mWeakActivity.get();
        if (activity == null || getCurrentWebView() == null) {
            return false;
        }
        return this.mMraidNativeCommandHandler.isInlineVideoAvailable(activity, getCurrentWebView());
    }

    @VisibleForTesting
    void handlePageLoad() {
        setViewState(ViewState.DEFAULT, new 5(this));
        if (this.mMraidListener != null) {
            this.mMraidListener.onLoaded(this.mDefaultAdContainer);
        }
    }

    @VisibleForTesting
    void handleTwoPartPageLoad() {
        updateScreenMetricsAsync(new 6(this));
    }

    private void updateScreenMetricsAsync(@Nullable Runnable runnable) {
        this.mScreenMetricsWaiter.cancelLastRequest();
        View currentWebView = getCurrentWebView();
        if (currentWebView != null) {
            this.mScreenMetricsWaiter.waitFor(new View[]{this.mDefaultAdContainer, currentWebView}).start(new 7(this, currentWebView, runnable));
        }
    }

    void handleOrientationChange(int i) {
        updateScreenMetricsAsync(null);
    }

    public void pause(boolean z) {
        this.mIsPaused = true;
        if (this.mMraidWebView != null) {
            WebViews.onPause(this.mMraidWebView, z);
        }
        if (this.mTwoPartWebView != null) {
            WebViews.onPause(this.mTwoPartWebView, z);
        }
    }

    public void resume() {
        this.mIsPaused = false;
        if (this.mMraidWebView != null) {
            WebViews.onResume(this.mMraidWebView);
        }
        if (this.mTwoPartWebView != null) {
            WebViews.onResume(this.mTwoPartWebView);
        }
    }

    public void destroy() {
        this.mScreenMetricsWaiter.cancelLastRequest();
        try {
            this.mOrientationBroadcastReceiver.unregister();
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().contains("Receiver not registered")) {
                throw e;
            }
        }
        if (!this.mIsPaused) {
            pause(true);
        }
        Views.removeFromParent(this.mCloseableAdContainer);
        this.mMraidBridge.detach();
        if (this.mMraidWebView != null) {
            this.mMraidWebView.destroy();
            this.mMraidWebView = null;
        }
        this.mTwoPartBridge.detach();
        if (this.mTwoPartWebView != null) {
            this.mTwoPartWebView.destroy();
            this.mTwoPartWebView = null;
        }
    }

    private void setViewState(@NonNull ViewState viewState) {
        setViewState(viewState, null);
    }

    private void setViewState(@NonNull ViewState viewState, @Nullable Runnable runnable) {
        MoPubLog.d("MRAID state set to " + viewState);
        this.mViewState = viewState;
        this.mMraidBridge.notifyViewState(viewState);
        if (this.mTwoPartBridge.isLoaded()) {
            this.mTwoPartBridge.notifyViewState(viewState);
        }
        if (this.mMraidListener != null) {
            if (viewState == ViewState.EXPANDED) {
                this.mMraidListener.onExpand();
            } else if (viewState == ViewState.HIDDEN) {
                this.mMraidListener.onClose();
            }
        }
        updateScreenMetricsAsync(runnable);
    }

    int clampInt(int i, int i2, int i3) {
        return Math.max(i, Math.min(i2, i3));
    }

    @VisibleForTesting
    void handleResize(int i, int i2, int i3, int i4, @NonNull ClosePosition closePosition, boolean z) {
        if (this.mMraidWebView == null) {
            throw new MraidCommandException("Unable to resize after the WebView is destroyed");
        } else if (this.mViewState != ViewState.LOADING && this.mViewState != ViewState.HIDDEN) {
            if (this.mViewState == ViewState.EXPANDED) {
                throw new MraidCommandException("Not allowed to resize from an already expanded ad");
            } else if (this.mPlacementType == PlacementType.INTERSTITIAL) {
                throw new MraidCommandException("Not allowed to resize from an interstitial ad");
            } else {
                Rect rootViewRect;
                int dipsToIntPixels = Dips.dipsToIntPixels((float) i, this.mContext);
                int dipsToIntPixels2 = Dips.dipsToIntPixels((float) i2, this.mContext);
                int dipsToIntPixels3 = Dips.dipsToIntPixels((float) i3, this.mContext);
                dipsToIntPixels3 += this.mScreenMetrics.getDefaultAdRect().left;
                int dipsToIntPixels4 = Dips.dipsToIntPixels((float) i4, this.mContext) + this.mScreenMetrics.getDefaultAdRect().top;
                Rect rect = new Rect(dipsToIntPixels3, dipsToIntPixels4, dipsToIntPixels + dipsToIntPixels3, dipsToIntPixels4 + dipsToIntPixels2);
                if (!z) {
                    rootViewRect = this.mScreenMetrics.getRootViewRect();
                    if (rect.width() > rootViewRect.width() || rect.height() > rootViewRect.height()) {
                        throw new MraidCommandException("resizeProperties specified a size (" + i + ", " + i2 + ") and offset (" + i3 + ", " + i4 + ") that doesn't allow the ad to appear within the max allowed size (" + this.mScreenMetrics.getRootViewRectDips().width() + ", " + this.mScreenMetrics.getRootViewRectDips().height() + ")");
                    }
                    rect.offsetTo(clampInt(rootViewRect.left, rect.left, rootViewRect.right - rect.width()), clampInt(rootViewRect.top, rect.top, rootViewRect.bottom - rect.height()));
                }
                rootViewRect = new Rect();
                this.mCloseableAdContainer.applyCloseRegionBounds(closePosition, rect, rootViewRect);
                if (!this.mScreenMetrics.getRootViewRect().contains(rootViewRect)) {
                    throw new MraidCommandException("resizeProperties specified a size (" + i + ", " + i2 + ") and offset (" + i3 + ", " + i4 + ") that doesn't allow the close region to appear within the max allowed size (" + this.mScreenMetrics.getRootViewRectDips().width() + ", " + this.mScreenMetrics.getRootViewRectDips().height() + ")");
                } else if (rect.contains(rootViewRect)) {
                    this.mCloseableAdContainer.setCloseVisible(false);
                    this.mCloseableAdContainer.setClosePosition(closePosition);
                    ViewGroup.LayoutParams layoutParams = new LayoutParams(rect.width(), rect.height());
                    layoutParams.leftMargin = rect.left - this.mScreenMetrics.getRootViewRect().left;
                    layoutParams.topMargin = rect.top - this.mScreenMetrics.getRootViewRect().top;
                    if (this.mViewState == ViewState.DEFAULT) {
                        this.mDefaultAdContainer.removeView(this.mMraidWebView);
                        this.mDefaultAdContainer.setVisibility(4);
                        this.mCloseableAdContainer.addView(this.mMraidWebView, new LayoutParams(-1, -1));
                        getRootView().addView(this.mCloseableAdContainer, layoutParams);
                    } else if (this.mViewState == ViewState.RESIZED) {
                        this.mCloseableAdContainer.setLayoutParams(layoutParams);
                    }
                    this.mCloseableAdContainer.setClosePosition(closePosition);
                    setViewState(ViewState.RESIZED);
                } else {
                    throw new MraidCommandException("resizeProperties specified a size (" + i + ", " + dipsToIntPixels2 + ") and offset (" + i3 + ", " + i4 + ") that don't allow the close region to appear within the resized ad.");
                }
            }
        }
    }

    void handleExpand(@Nullable URI uri, boolean z) {
        if (this.mMraidWebView == null) {
            throw new MraidCommandException("Unable to expand after the WebView is destroyed");
        } else if (this.mPlacementType != PlacementType.INTERSTITIAL) {
            if (this.mViewState == ViewState.DEFAULT || this.mViewState == ViewState.RESIZED) {
                applyOrientation();
                Object obj = uri != null ? 1 : null;
                if (obj != null) {
                    this.mTwoPartWebView = new MraidWebView(this.mContext);
                    this.mTwoPartBridge.attachView(this.mTwoPartWebView);
                    this.mTwoPartBridge.setContentUrl(uri.toString());
                }
                ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
                if (this.mViewState == ViewState.DEFAULT) {
                    if (obj != null) {
                        this.mCloseableAdContainer.addView(this.mTwoPartWebView, layoutParams);
                    } else {
                        this.mDefaultAdContainer.removeView(this.mMraidWebView);
                        this.mDefaultAdContainer.setVisibility(4);
                        this.mCloseableAdContainer.addView(this.mMraidWebView, layoutParams);
                    }
                    getRootView().addView(this.mCloseableAdContainer, new LayoutParams(-1, -1));
                } else if (this.mViewState == ViewState.RESIZED && obj != null) {
                    this.mCloseableAdContainer.removeView(this.mMraidWebView);
                    this.mDefaultAdContainer.addView(this.mMraidWebView, layoutParams);
                    this.mDefaultAdContainer.setVisibility(4);
                    this.mCloseableAdContainer.addView(this.mTwoPartWebView, layoutParams);
                }
                this.mCloseableAdContainer.setLayoutParams(layoutParams);
                handleCustomClose(z);
                setViewState(ViewState.EXPANDED);
            }
        }
    }

    @VisibleForTesting
    void handleClose() {
        if (this.mMraidWebView != null && this.mViewState != ViewState.LOADING && this.mViewState != ViewState.HIDDEN) {
            if (this.mViewState == ViewState.EXPANDED || this.mPlacementType == PlacementType.INTERSTITIAL) {
                unApplyOrientation();
            }
            if (this.mViewState == ViewState.RESIZED || this.mViewState == ViewState.EXPANDED) {
                if (!this.mTwoPartBridge.isAttached() || this.mTwoPartWebView == null) {
                    this.mCloseableAdContainer.removeView(this.mMraidWebView);
                    this.mDefaultAdContainer.addView(this.mMraidWebView, new LayoutParams(-1, -1));
                    this.mDefaultAdContainer.setVisibility(0);
                } else {
                    this.mCloseableAdContainer.removeView(this.mTwoPartWebView);
                    this.mTwoPartBridge.detach();
                }
                getRootView().removeView(this.mCloseableAdContainer);
                setViewState(ViewState.DEFAULT);
            } else if (this.mViewState == ViewState.DEFAULT) {
                this.mDefaultAdContainer.setVisibility(4);
                setViewState(ViewState.HIDDEN);
            }
        }
    }

    @TargetApi(19)
    @NonNull
    private ViewGroup getRootView() {
        if (this.mRootView == null) {
            if (VERSION.SDK_INT >= 19) {
                Preconditions.checkState(this.mDefaultAdContainer.isAttachedToWindow());
            }
            this.mRootView = (ViewGroup) this.mDefaultAdContainer.getRootView().findViewById(16908290);
        }
        return this.mRootView;
    }

    @VisibleForTesting
    void handleShowVideo(@NonNull String str) {
        MraidVideoPlayerActivity.startMraid(this.mContext, str);
    }

    @VisibleForTesting
    void lockOrientation(int i) {
        Activity activity = (Activity) this.mWeakActivity.get();
        if (activity == null || !shouldAllowForceOrientation(this.mForceOrientation)) {
            throw new MraidCommandException("Attempted to lock orientation to unsupported value: " + this.mForceOrientation.name());
        }
        if (this.mOriginalActivityOrientation == null) {
            this.mOriginalActivityOrientation = Integer.valueOf(activity.getRequestedOrientation());
        }
        activity.setRequestedOrientation(i);
    }

    @VisibleForTesting
    void applyOrientation() {
        if (this.mForceOrientation != MraidOrientation.NONE) {
            lockOrientation(this.mForceOrientation.getActivityInfoOrientation());
        } else if (this.mAllowOrientationChange) {
            unApplyOrientation();
        } else {
            Activity activity = (Activity) this.mWeakActivity.get();
            if (activity == null) {
                throw new MraidCommandException("Unable to set MRAID expand orientation to 'none'; expected passed in Activity Context.");
            }
            lockOrientation(DeviceUtils.getScreenOrientation(activity));
        }
    }

    @VisibleForTesting
    void unApplyOrientation() {
        Activity activity = (Activity) this.mWeakActivity.get();
        if (!(activity == null || this.mOriginalActivityOrientation == null)) {
            activity.setRequestedOrientation(this.mOriginalActivityOrientation.intValue());
        }
        this.mOriginalActivityOrientation = null;
    }

    @TargetApi(13)
    @VisibleForTesting
    boolean shouldAllowForceOrientation(MraidOrientation mraidOrientation) {
        if (mraidOrientation == MraidOrientation.NONE) {
            return true;
        }
        Activity activity = (Activity) this.mWeakActivity.get();
        if (activity == null) {
            return false;
        }
        try {
            ActivityInfo activityInfo = activity.getPackageManager().getActivityInfo(new ComponentName(activity, activity.getClass()), 0);
            int i = activityInfo.screenOrientation;
            boolean z;
            if (i != -1) {
                if (i == mraidOrientation.getActivityInfoOrientation()) {
                    z = true;
                } else {
                    z = false;
                }
                return z;
            }
            z = Utils.bitMaskContainsFlag(activityInfo.configChanges, 128);
            if (VERSION.SDK_INT < 13) {
                return z;
            }
            if (z && Utils.bitMaskContainsFlag(activityInfo.configChanges, 1024)) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    @VisibleForTesting
    void handleCustomClose(boolean z) {
        boolean z2 = true;
        if (z != (!this.mCloseableAdContainer.isCloseVisible())) {
            CloseableLayout closeableLayout = this.mCloseableAdContainer;
            if (z) {
                z2 = false;
            }
            closeableLayout.setCloseVisible(z2);
            if (this.mOnCloseButtonListener != null) {
                this.mOnCloseButtonListener.useCustomCloseChanged(z);
            }
        }
    }

    @NonNull
    public FrameLayout getAdContainer() {
        return this.mDefaultAdContainer;
    }

    public void loadJavascript(@NonNull String str) {
        this.mMraidBridge.injectJavaScript(str);
    }

    @NonNull
    public Context getContext() {
        return this.mContext;
    }

    @VisibleForTesting
    void handleSetOrientationProperties(boolean z, MraidOrientation mraidOrientation) {
        if (shouldAllowForceOrientation(mraidOrientation)) {
            this.mAllowOrientationChange = z;
            this.mForceOrientation = mraidOrientation;
            if (this.mViewState == ViewState.EXPANDED || this.mPlacementType == PlacementType.INTERSTITIAL) {
                applyOrientation();
                return;
            }
            return;
        }
        throw new MraidCommandException("Unable to force orientation to " + mraidOrientation);
    }

    @VisibleForTesting
    void handleOpen(@NonNull String str) {
        if (this.mMraidListener != null) {
            this.mMraidListener.onOpen();
        }
        Builder builder = new Builder();
        if (this.mAdReport != null) {
            builder.withDspCreativeId(this.mAdReport.getDspCreativeId());
        }
        builder.withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, new UrlAction[]{UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK}).build().handleUrl(this.mContext, str);
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    ViewState getViewState() {
        return this.mViewState;
    }

    @Deprecated
    @VisibleForTesting
    void setViewStateForTesting(@NonNull ViewState viewState) {
        this.mViewState = viewState;
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    CloseableLayout getExpandedAdContainer() {
        return this.mCloseableAdContainer;
    }

    @Deprecated
    @VisibleForTesting
    void setRootView(FrameLayout frameLayout) {
        this.mRootView = frameLayout;
    }

    @Deprecated
    @VisibleForTesting
    void setRootViewSize(int i, int i2) {
        this.mScreenMetrics.setRootViewPosition(0, 0, i, i2);
    }

    @Deprecated
    @VisibleForTesting
    Integer getOriginalActivityOrientation() {
        return this.mOriginalActivityOrientation;
    }

    @Deprecated
    @VisibleForTesting
    boolean getAllowOrientationChange() {
        return this.mAllowOrientationChange;
    }

    @Deprecated
    @VisibleForTesting
    MraidOrientation getForceOrientation() {
        return this.mForceOrientation;
    }

    @Deprecated
    @VisibleForTesting
    void setOrientationBroadcastReceiver(OrientationBroadcastReceiver orientationBroadcastReceiver) {
        this.mOrientationBroadcastReceiver = orientationBroadcastReceiver;
    }

    @Deprecated
    @VisibleForTesting
    MraidWebView getMraidWebView() {
        return this.mMraidWebView;
    }

    @Deprecated
    @VisibleForTesting
    MraidWebView getTwoPartWebView() {
        return this.mTwoPartWebView;
    }
}
