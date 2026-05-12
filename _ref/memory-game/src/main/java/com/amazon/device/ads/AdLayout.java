package com.amazon.device.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.amazon.device.ads.AdError.ErrorCode;
import com.amazon.device.ads.MobileAdsLogger.Level;
import com.mopub.common.AdType;
import java.util.Locale;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressLint({"InlinedApi"})
public class AdLayout extends FrameLayout implements Ad {
    private static final String CONTENT_DESCRIPTION_AD_LAYOUT = "adLayoutObject";
    public static final int DEFAULT_TIMEOUT = 20000;
    static final String LAYOUT_NOT_RUN_ERR_MSG = "Can't load an ad because the view size cannot be determined.";
    static final String LAYOUT_PARAMS_NULL_ERR_MSG = "Can't load an ad because layout parameters are blank. Use setLayoutParams() to specify dimensions for this AdLayout.";
    static final String LOADING_IN_PROGRESS_LOG_MSG = "Can't load an ad because an ad is currently loading. Please wait for the ad to finish loading and showing before loading another ad.";
    static final String LOADING_OR_LOADED_LOG_MSG = "Can't load an ad because an ad is currently loading or already loaded. Please wait for the ad to finish loading or showing before loading another ad.";
    private static final String LOGTAG = AdLayout.class.getSimpleName();
    private static ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(1);
    private View activityRootView;
    private AdController adController;
    private final AdControllerFactory adControllerFactory;
    private AdListenerExecutor adListenerExecutor;
    private final AdListenerExecutorFactory adListenerExecutorFactory;
    private final AdLoadStarter adLoadStarter;
    private final AdRegistrationExecutor adRegistration;
    private AdSize adSize;
    private AdTargetingOptions adTargetingOptions;
    private boolean attached;
    private boolean autoShow;
    private final Context context;
    private Destroyable currentDestroyable;
    private View currentView;
    private boolean hasRegisterBroadcastReciever;
    private boolean isDestroyed;
    private boolean isInForeground;
    private boolean isInitialized;
    private boolean isParentViewMissingAtLoadTime;
    private int lastVisibility;
    private final MobileAdsLogger logger;
    private final MobileAdsLoggerFactory loggerFactory;
    private AtomicBoolean needsToLoadAdOnLayout;
    private final AtomicBoolean previousAdExpired;
    private AdProperties properties;
    private BroadcastReceiver screenStateReceiver;
    private boolean shouldDisableWebViewHardwareAcceleration;

    class AdLayoutAdControlCallback implements AdControlCallback {
        AdLayoutAdControlCallback() {
        }

        public boolean isAdReady(boolean z) {
            return AdLayout.this.prepareAd(z);
        }

        public void onAdLoaded(AdProperties adProperties) {
            AdLayout.this.properties = adProperties;
            AdLayout.this.getAdController().render();
        }

        @SuppressLint({"InlinedApi"})
        public void onAdRendered() {
            if (!AdLayout.this.autoShow) {
                AdLayout.this.getAdController().getMetricsCollector().startMetric(MetricType.AD_LOADED_TO_AD_SHOW_TIME);
                AdLayout.this.logger.d("Ad is ready to show. Please call showAd to display it.");
                AdLayout.this.getAdListenerExecutor().onAdLoaded(AdLayout.this, AdLayout.this.properties);
            } else if (AdLayout.this.showAd()) {
                AdLayout.this.getAdListenerExecutor().onAdLoaded(AdLayout.this, AdLayout.this.properties);
            }
        }

        public void onAdFailed(AdError adError) {
            if (ErrorCode.NETWORK_TIMEOUT.equals(adError.getCode())) {
                AdLayout.this.adController = null;
            }
            AdLayout.this.getAdListenerExecutor().onAdFailedToLoad(AdLayout.this, adError);
        }

        public void onAdEvent(AdEvent adEvent) {
            handleAdEvent(adEvent);
        }

        boolean handleAdEvent(AdEvent adEvent) {
            switch (adEvent.getAdEventType()) {
                case EXPANDED:
                    AdLayout.this.getAdListenerExecutor().onAdExpanded(AdLayout.this);
                    return true;
                case CLOSED:
                    AdLayout.this.getAdListenerExecutor().onAdCollapsed(AdLayout.this);
                    return true;
                case RESIZED:
                    AdLayout.this.getAdListenerExecutor().onAdResized(AdLayout.this, (Rect) adEvent.getParameters().getParameter(AdEvent.POSITION_ON_SCREEN));
                    return true;
                default:
                    return false;
            }
        }

        public int adClosing() {
            if (AdLayout.this.getAdController().getAdState().equals(AdState.EXPANDED)) {
                return 0;
            }
            return 2;
        }

        public void postAdRendered() {
        }

        public void onAdExpired() {
            AdLayout.this.getAdController().getMetricsCollector().incrementMetric(MetricType.AD_EXPIRED_BEFORE_SHOWING);
            AdLayout.this.previousAdExpired.set(true);
            AdLayout.this.adController = null;
            AdLayout.this.getAdListenerExecutor().onAdExpired(AdLayout.this);
        }
    }

    private static class OnLayoutChangeListenerUtil {
        private OnLayoutChangeListenerUtil() {
        }

        @TargetApi(11)
        protected static void setOnLayoutChangeListenerForRoot(final AdLayout adLayout) {
            adLayout.activityRootView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
                public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    if (adLayout.getAndSetNeedsToLoadAdOnLayout(false)) {
                        adLayout.setFloatingWindowDimensions();
                        adLayout.startAdLoadUponLayout();
                        adLayout.activityRootView.removeOnLayoutChangeListener(this);
                    }
                }
            });
        }
    }

    static {
        threadPool.setKeepAliveTime(60, TimeUnit.SECONDS);
    }

    public AdLayout(Context context) {
        this(context, AdSize.SIZE_AUTO);
    }

    public AdLayout(Context context, AdSize adSize) {
        this(context, adSize, new MobileAdsLoggerFactory(), new AdControllerFactory(), AdRegistration.getAmazonAdRegistrationExecutor(), new AdLoadStarter());
    }

    AdLayout(Context context, AdSize adSize, MobileAdsLoggerFactory mobileAdsLoggerFactory, AdControllerFactory adControllerFactory, AdRegistrationExecutor adRegistrationExecutor, AdLoadStarter adLoadStarter) {
        this(context, adSize, mobileAdsLoggerFactory, new AdListenerExecutorFactory(mobileAdsLoggerFactory), adControllerFactory, adRegistrationExecutor, adLoadStarter);
    }

    AdLayout(Context context, AdSize adSize, MobileAdsLoggerFactory mobileAdsLoggerFactory, AdListenerExecutorFactory adListenerExecutorFactory, AdControllerFactory adControllerFactory, AdRegistrationExecutor adRegistrationExecutor, AdLoadStarter adLoadStarter) {
        super(context);
        this.hasRegisterBroadcastReciever = false;
        this.attached = false;
        this.lastVisibility = 8;
        this.needsToLoadAdOnLayout = new AtomicBoolean(false);
        this.isParentViewMissingAtLoadTime = false;
        this.activityRootView = null;
        this.adTargetingOptions = null;
        this.isDestroyed = false;
        this.isInitialized = false;
        this.autoShow = true;
        this.previousAdExpired = new AtomicBoolean(false);
        this.context = context;
        this.adSize = adSize;
        this.loggerFactory = mobileAdsLoggerFactory;
        this.logger = this.loggerFactory.createMobileAdsLogger(LOGTAG);
        this.adListenerExecutorFactory = adListenerExecutorFactory;
        this.adControllerFactory = adControllerFactory;
        this.adRegistration = adRegistrationExecutor;
        this.adLoadStarter = adLoadStarter;
    }

    public AdLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, new MobileAdsLoggerFactory(), new AdControllerFactory(), AdRegistration.getAmazonAdRegistrationExecutor(), new AdLoadStarter());
    }

    AdLayout(Context context, AttributeSet attributeSet, MobileAdsLoggerFactory mobileAdsLoggerFactory, AdControllerFactory adControllerFactory, AdRegistrationExecutor adRegistrationExecutor, AdLoadStarter adLoadStarter) {
        this(context, attributeSet, mobileAdsLoggerFactory, new AdListenerExecutorFactory(mobileAdsLoggerFactory), adControllerFactory, adRegistrationExecutor, adLoadStarter);
    }

    AdLayout(Context context, AttributeSet attributeSet, MobileAdsLoggerFactory mobileAdsLoggerFactory, AdListenerExecutorFactory adListenerExecutorFactory, AdControllerFactory adControllerFactory, AdRegistrationExecutor adRegistrationExecutor, AdLoadStarter adLoadStarter) {
        super(context, attributeSet);
        this.hasRegisterBroadcastReciever = false;
        this.attached = false;
        this.lastVisibility = 8;
        this.needsToLoadAdOnLayout = new AtomicBoolean(false);
        this.isParentViewMissingAtLoadTime = false;
        this.activityRootView = null;
        this.adTargetingOptions = null;
        this.isDestroyed = false;
        this.isInitialized = false;
        this.autoShow = true;
        this.previousAdExpired = new AtomicBoolean(false);
        this.context = context;
        this.adSize = determineAdSize(attributeSet);
        this.loggerFactory = mobileAdsLoggerFactory;
        this.logger = this.loggerFactory.createMobileAdsLogger(LOGTAG);
        this.adListenerExecutorFactory = adListenerExecutorFactory;
        this.adControllerFactory = adControllerFactory;
        this.adRegistration = adRegistrationExecutor;
        this.adLoadStarter = adLoadStarter;
    }

    public AdLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, new MobileAdsLoggerFactory(), new AdControllerFactory(), AdRegistration.getAmazonAdRegistrationExecutor(), new AdLoadStarter());
    }

    AdLayout(Context context, AttributeSet attributeSet, int i, MobileAdsLoggerFactory mobileAdsLoggerFactory, AdControllerFactory adControllerFactory, AdRegistrationExecutor adRegistrationExecutor, AdLoadStarter adLoadStarter) {
        this(context, attributeSet, i, mobileAdsLoggerFactory, new AdListenerExecutorFactory(mobileAdsLoggerFactory), adControllerFactory, adRegistrationExecutor, adLoadStarter);
    }

    AdLayout(Context context, AttributeSet attributeSet, int i, MobileAdsLoggerFactory mobileAdsLoggerFactory, AdListenerExecutorFactory adListenerExecutorFactory, AdControllerFactory adControllerFactory, AdRegistrationExecutor adRegistrationExecutor, AdLoadStarter adLoadStarter) {
        super(context, attributeSet, i);
        this.hasRegisterBroadcastReciever = false;
        this.attached = false;
        this.lastVisibility = 8;
        this.needsToLoadAdOnLayout = new AtomicBoolean(false);
        this.isParentViewMissingAtLoadTime = false;
        this.activityRootView = null;
        this.adTargetingOptions = null;
        this.isDestroyed = false;
        this.isInitialized = false;
        this.autoShow = true;
        this.previousAdExpired = new AtomicBoolean(false);
        this.context = context;
        this.adSize = determineAdSize(attributeSet);
        this.loggerFactory = mobileAdsLoggerFactory;
        this.logger = this.loggerFactory.createMobileAdsLogger(LOGTAG);
        this.adListenerExecutorFactory = adListenerExecutorFactory;
        this.adControllerFactory = adControllerFactory;
        this.adRegistration = adRegistrationExecutor;
        this.adLoadStarter = adLoadStarter;
    }

    public void setX(float f) {
        super.setX(f);
        fireViewableEvent();
    }

    public void setY(float f) {
        super.setY(f);
        fireViewableEvent();
    }

    public void setTranslationX(float f) {
        super.setTranslationX(f);
        fireViewableEvent();
    }

    public void setTranslationY(float f) {
        super.setTranslationY(f);
        fireViewableEvent();
    }

    private void fireViewableEvent() {
        if (this.adController != null) {
            this.adController.fireViewableEvent();
        }
    }

    private AdSize determineAdSize(AttributeSet attributeSet) {
        String str = "adSize";
        str = "http://schemas.android.com/apk/lib/com.amazon.device.ads";
        str = getAttributeValue(attributeSet, "http://schemas.android.com/apk/lib/com.amazon.device.ads", "adSize");
        if (str == null) {
            str = getAttributeValue(attributeSet, "http://schemas.android.com/apk/res/" + this.context.getPackageName(), "adSize");
            if (str != null) {
                this.logger.forceLog(Level.WARN, "DEPRECATED - Please use the XML namespace \"http://schemas.android.com/apk/lib/com.amazon.device.ads\" for specifying AdLayout properties.", new Object[0]);
                if (str.toLowerCase(Locale.US).equals(AdType.CUSTOM)) {
                    str = "Using \"custom\" or \"CUSTOM\" for the \"adSize\" property is no longer supported. Please specifiy a size or remove the property to use Auto Ad Size.";
                    this.logger.forceLog(Level.ERROR, str, new Object[0]);
                    throw new IllegalArgumentException(str);
                }
            }
        }
        return parseAdSize(str);
    }

    MobileAdsLogger getLogger() {
        return this.logger;
    }

    boolean shouldDisableWebViewHardwareAcceleration() {
        return this.shouldDisableWebViewHardwareAcceleration;
    }

    void setShouldDisableWebViewHardwareAcceleration(boolean z) {
        this.shouldDisableWebViewHardwareAcceleration = z;
        if (this.adController != null) {
            this.adController.requestDisableHardwareAcceleration(this.shouldDisableWebViewHardwareAcceleration);
        }
    }

    void initializeIfNecessary() {
        if (!isInitialized()) {
            long nanoTime = System.nanoTime();
            this.logger.d("Initializing AdLayout.");
            this.adRegistration.initializeAds(this.context);
            setContentDescription(CONTENT_DESCRIPTION_AD_LAYOUT);
            if (isInEditMode()) {
                View textView = new TextView(this.context);
                textView.setText("AdLayout");
                textView.setLayoutParams(new LayoutParams(-1, -1));
                textView.setGravity(17);
                addView(textView);
                this.isInitialized = true;
                return;
            }
            this.isInForeground = getVisibility() == 0;
            setHorizontalScrollBarEnabled(false);
            setVerticalScrollBarEnabled(false);
            this.isInitialized = true;
            if (this.adListenerExecutor == null) {
                setListener(null);
            }
            initializeAdController();
            if (isWebViewDatabaseNull()) {
                this.logger.forceLog(Level.ERROR, "Disabling ads. Local cache file is inaccessible so ads will fail if we try to create a WebView. Details of this Android bug found at: http://code.google.com/p/android/issues/detail?id=10789", new Object[0]);
                this.isInitialized = false;
                return;
            }
            this.adController.getMetricsCollector().startMetricInMillisecondsFromNanoseconds(MetricType.AD_LAYOUT_INITIALIZATION, nanoTime);
            this.adController.getMetricsCollector().stopMetric(MetricType.AD_LAYOUT_INITIALIZATION);
        }
    }

    private void initializeAdController() {
        if (this.adController == null) {
            AdSize adSize;
            if (this.adSize == null) {
                adSize = AdSize.SIZE_AUTO;
            } else {
                adSize = this.adSize;
            }
            setAdController(createAdController(adSize, this.context));
            this.adController.requestDisableHardwareAcceleration(this.shouldDisableWebViewHardwareAcceleration);
        }
    }

    private void setAdController(AdController adController) {
        this.adController = adController;
        this.adController.setCallback(createAdControlCallback());
    }

    private AdController getAdController() {
        initializeIfNecessary();
        if (this.adController == null) {
            initializeAdController();
        }
        return this.adController;
    }

    private static String getAttributeValue(AttributeSet attributeSet, String str, String str2) {
        return attributeSet.getAttributeValue(str, str2);
    }

    private static AdSize parseAdSize(String str) {
        int i = 0;
        AdSize adSize = AdSize.SIZE_AUTO;
        if (str != null) {
            String toLowerCase = str.toLowerCase(Locale.US);
            if (toLowerCase.equals("autonoscale")) {
                return AdSize.SIZE_AUTO_NO_SCALE;
            }
            if (!toLowerCase.equals("auto")) {
                int parseInt;
                String[] split = toLowerCase.split("x");
                if (split.length == 2) {
                    parseInt = NumberUtils.parseInt(split[0], 0);
                    i = NumberUtils.parseInt(split[1], 0);
                } else {
                    parseInt = 0;
                }
                return new AdSize(parseInt, i);
            }
        }
        return adSize;
    }

    private AdController createAdController(AdSize adSize, Context context) {
        return this.adControllerFactory.buildAdController(context, adSize);
    }

    AdControlCallback createAdControlCallback() {
        return new AdLayoutAdControlCallback();
    }

    boolean isWebViewDatabaseNull() {
        return !getAdController().canShowViews();
    }

    AdData getAdData() {
        return getAdController().getAdData();
    }

    boolean isInitialized() {
        return this.isInitialized;
    }

    private void registerScreenStateBroadcastReceiver() {
        if (!this.hasRegisterBroadcastReciever) {
            this.hasRegisterBroadcastReciever = true;
            this.screenStateReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals("android.intent.action.SCREEN_OFF") && AdLayout.this.isInForeground) {
                        AdLayout.this.getAdController().closeAd();
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.context.getApplicationContext().registerReceiver(this.screenStateReceiver, intentFilter);
        }
    }

    private void unregisterScreenStateBroadcastReceiver() {
        if (this.hasRegisterBroadcastReciever) {
            this.hasRegisterBroadcastReciever = false;
            this.context.getApplicationContext().unregisterReceiver(this.screenStateReceiver);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.attached = true;
            registerScreenStateBroadcastReceiver();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
        unregisterScreenStateBroadcastReceiver();
    }

    protected void onWindowVisibilityChanged(int i) {
        if (this.attached && this.lastVisibility != i) {
            if (i != 0) {
                this.isInForeground = false;
                collapseAd();
                unregisterScreenStateBroadcastReceiver();
            } else if (i == 0) {
                this.isInForeground = true;
            }
        }
    }

    private void collapseAd() {
        if (getAdController().getAdState().equals(AdState.EXPANDED)) {
            ThreadUtils.scheduleOnMainThread(new Runnable() {
                public void run() {
                    if (AdLayout.this.getAdController().getAdState().equals(AdState.EXPANDED)) {
                        AdLayout.this.getAdController().closeAd();
                    }
                }
            });
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (!this.isDestroyed) {
            int i5 = i3 - i;
            int i6 = i4 - i2;
            super.onLayout(z, i, i2, i3, i4);
            if (!isInEditMode()) {
                getAdController().setWindowDimensions(i5, i6);
                if (getAndSetNeedsToLoadAdOnLayout(false)) {
                    startAdLoadUponLayout();
                }
            }
        }
    }

    private void startAdLoadUponLayout() {
        AdTargetingOptions adTargetingOptions = this.adTargetingOptions;
        AdSlot deferredLoad = new AdSlot(getAdController(), adTargetingOptions).setDeferredLoad(true);
        this.adLoadStarter.loadAds(getAdController().getTimeout(), adTargetingOptions, deferredLoad);
        if (!getAndResetIsPrepared()) {
            onRequestError("Could not load ad on layout.");
        }
    }

    public int getTimeout() {
        if (getAdController() == null) {
            return -1;
        }
        return getAdController().getTimeout();
    }

    public void setTimeout(int i) {
        AdController adController = getAdController();
        if (adController != null) {
            adController.setTimeout(i);
        }
    }

    public AdSize getAdSize() {
        AdController adController = getAdController();
        if (adController == null) {
            return null;
        }
        return adController.getAdSize();
    }

    void setMaxWidth(int i) {
        if (this.adController != null) {
            this.logger.w("The maximum width cannot be changed because this ad has already been initialized. Max width should be set immediately after construction of the Ad object.");
        } else {
            this.adSize = this.adSize.newMaxWidth(i);
        }
    }

    public boolean loadAd() {
        return loadAd(new AdTargetingOptions());
    }

    public boolean loadAd(AdTargetingOptions adTargetingOptions) {
        if (adTargetingOptions == null) {
            adTargetingOptions = new AdTargetingOptions();
        }
        this.adTargetingOptions = adTargetingOptions;
        if (getNeedsToLoadAdOnLayout()) {
            this.logger.e(LOADING_IN_PROGRESS_LOG_MSG);
            return false;
        }
        initializeIfNecessary();
        if (!isInitialized()) {
            this.logger.e("The ad could not be initialized properly.");
            return false;
        } else if (isReadyToLoad()) {
            if (getAdController().getAdState().equals(AdState.SHOWING)) {
                getAdController().getMetricsCollector().stopMetric(MetricType.AD_SHOW_DURATION);
            }
            this.previousAdExpired.set(false);
            this.adLoadStarter.loadAds(getAdController().getTimeout(), adTargetingOptions, new AdSlot(getAdController(), adTargetingOptions));
            if (getNeedsToLoadAdOnLayout()) {
                return true;
            }
            return getAndResetIsPrepared();
        } else {
            switch (getAdController().getAdState()) {
                case INVALID:
                    if (getAdController().isExpired()) {
                        getAdController().setAdState(AdState.READY_TO_LOAD);
                        getAdController().resetToReady();
                        return loadAd(adTargetingOptions);
                    }
                    this.logger.e("An ad could not be loaded because of an unknown issue with the web views.");
                    return false;
                case DESTROYED:
                    this.logger.e("An ad could not be loaded because the AdLayout has been destroyed.");
                    return false;
                case EXPANDED:
                    this.logger.e("An ad could not be loaded because another ad is currently expanded.");
                    return false;
                default:
                    this.logger.e(LOADING_OR_LOADED_LOG_MSG);
                    return false;
            }
        }
    }

    private boolean isReadyToLoad() {
        return AdState.READY_TO_LOAD.equals(getAdController().getAdState()) || AdState.SHOWING.equals(getAdController().getAdState());
    }

    private boolean isReadyToShow() {
        return getAdController().getAdState().equals(AdState.RENDERED);
    }

    public boolean isShowing() {
        return getAdController().getAdState().equals(AdState.SHOWING);
    }

    boolean prepareAd(boolean z) {
        if (z) {
            this.logger.d("Skipping ad layout preparation steps because the layout is already prepared.");
            return true;
        } else if (!isReadyToLoad()) {
            return false;
        } else {
            if (getNeedsToLoadAdOnLayout()) {
                this.logger.e(LOADING_IN_PROGRESS_LOG_MSG);
                return false;
            }
            if (getAdSize().isAuto()) {
                this.logger.d("Ad size to be determined automatically.");
            }
            setIsParentViewMissingAtLoadTime();
            if (getAdSize().isAuto() && getAdController().areWindowDimensionsSet()) {
                return true;
            }
            if (isLayoutRequested() && getAdSize().isAuto() && !isParentViewMissingAtLoadTime()) {
                deferAdLoadToLayoutEvent();
                return false;
            } else if (isParentViewMissingAtLoadTime()) {
                this.logger.d("The ad's parent view is missing at load time.");
                return loadAdWhenParentViewMissing();
            } else {
                setWindowDimensions();
                return true;
            }
        }
    }

    private boolean loadAdWhenParentViewMissing() {
        if (getLayoutParams() == null) {
            Metrics.getInstance().getMetricsCollector().incrementMetric(MetricType.AD_FAILED_NULL_LAYOUT_PARAMS);
            onRequestError(LAYOUT_PARAMS_NULL_ERR_MSG);
            return false;
        } else if (AndroidTargetUtils.isAtLeastAndroidAPI(11)) {
            setActivityRootView();
            if (isActivityRootViewNull()) {
                onRequestError("Ad load failed because root view could not be obtained from the activity.");
                return false;
            } else if (isActivityRootViewLayoutRequested()) {
                this.logger.d("Activity root view layout is requested.");
                deferAdLoadToLayoutEvent();
                setOnLayoutChangeListenerForRoot();
                return false;
            } else {
                setFloatingWindowDimensions();
                return true;
            }
        } else {
            setFloatingWindowDimensions();
            return true;
        }
    }

    void adFailed(AdError adError) {
        getAdController().adFailed(adError);
    }

    void bypassAdRenderingProcess() {
        getAdController().setAdState(AdState.RENDERING);
        getAdController().adRendered("custom-render");
    }

    void adShown() {
        getAdController().adShown();
    }

    void setOnLayoutChangeListenerForRoot() {
        OnLayoutChangeListenerUtil.setOnLayoutChangeListenerForRoot(this);
    }

    void setFloatingWindowDimensions() {
        if (getLayoutParams().width == -1 || getLayoutParams().height == -1) {
            this.logger.d("The requested ad will scale based on the screen's dimensions because at least one AdLayout dimension is set to MATCH_PARENT but the AdLayout is currently missing a fixed-size parent view.");
        }
        setWindowDimensions();
    }

    private void setWindowDimensions() {
        int resolveLayoutParam = resolveLayoutParam(true);
        int resolveLayoutParam2 = resolveLayoutParam(false);
        if (resolveLayoutParam > 0 || resolveLayoutParam2 > 0) {
            getAdController().setWindowDimensions(resolveLayoutParam, resolveLayoutParam2);
        }
    }

    int resolveLayoutParam(boolean z) {
        int i;
        if (z) {
            i = getLayoutParams().width;
        } else {
            i = getLayoutParams().height;
        }
        if (i == -1) {
            if (isActivityRootViewNull()) {
                return getRawScreenDimension(z);
            }
            return getActivityRootViewDimension(z);
        } else if (i == -2) {
            return 0;
        } else {
            return i;
        }
    }

    int getRawScreenDimension(boolean z) {
        WindowManager windowManager = (WindowManager) this.context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return z ? displayMetrics.widthPixels : displayMetrics.heightPixels;
    }

    void deferAdLoadToLayoutEvent() {
        setNeedsToLoadAdOnLayout(true);
        scheduleTaskForCheckingIfLayoutHasRun();
    }

    void scheduleTaskForCheckingIfLayoutHasRun() {
        threadPool.schedule(new Runnable() {
            public void run() {
                AdLayout.this.failLoadIfLayoutHasNotRun();
            }
        }, (long) getTimeout(), TimeUnit.MILLISECONDS);
    }

    void failLoadIfLayoutHasNotRun() {
        if (getAndSetNeedsToLoadAdOnLayout(false)) {
            Metrics.getInstance().getMetricsCollector().incrementMetric(MetricType.AD_FAILED_LAYOUT_NOT_RUN);
            onRequestError(LAYOUT_NOT_RUN_ERR_MSG);
        }
    }

    boolean getNeedsToLoadAdOnLayout() {
        return this.needsToLoadAdOnLayout.get();
    }

    void setNeedsToLoadAdOnLayout(boolean z) {
        this.needsToLoadAdOnLayout.set(z);
    }

    boolean getAndSetNeedsToLoadAdOnLayout(boolean z) {
        return this.needsToLoadAdOnLayout.getAndSet(z);
    }

    boolean getAndResetIsPrepared() {
        return getAdController().getAndResetIsPrepared();
    }

    private void onRequestError(String str) {
        getAdController().onRequestError(str);
    }

    boolean isParentViewMissingAtLoadTime() {
        return this.isParentViewMissingAtLoadTime;
    }

    void setIsParentViewMissingAtLoadTime() {
        this.isParentViewMissingAtLoadTime = getParent() == null;
    }

    void setIsParentViewMissingAtLoadTime(boolean z) {
        this.isParentViewMissingAtLoadTime = z;
    }

    void setActivityRootView() {
        Activity contextAsActivity = ContextUtils.getContextAsActivity(this.context);
        if (contextAsActivity == null) {
            this.logger.e("unable to set activity root view because the context did not contain an activity");
        } else {
            this.activityRootView = contextAsActivity.getWindow().getDecorView().findViewById(16908290).getRootView();
        }
    }

    boolean isActivityRootViewLayoutRequested() {
        return this.activityRootView.isLayoutRequested();
    }

    boolean isActivityRootViewNull() {
        return this.activityRootView == null;
    }

    int getActivityRootViewDimension(boolean z) {
        return z ? this.activityRootView.getWidth() : this.activityRootView.getHeight();
    }

    public void setListener(AdListener adListener) {
        if (adListener == null) {
            adListener = new DefaultAdListener(LOGTAG);
        }
        this.adListenerExecutor = this.adListenerExecutorFactory.createAdListenerExecutor(adListener);
    }

    AdListenerExecutor getAdListenerExecutor() {
        return this.adListenerExecutor;
    }

    public boolean isLoading() {
        if (getAdController() == null) {
            return false;
        }
        return getAdController().getAdState().equals(AdState.LOADING);
    }

    public boolean isAdLoading() {
        return isLoading();
    }

    public void destroy() {
        if (isInitialized()) {
            this.logger.d("Destroying the AdLayout");
            this.isDestroyed = true;
            unregisterScreenStateBroadcastReceiver();
            getAdController().destroy();
        }
    }

    public boolean showAd() {
        if (this.previousAdExpired.get()) {
            this.logger.w("This banner ad has expired. Please load another ad.");
            return false;
        } else if (isReadyToShow()) {
            if (getAdController().isExpired()) {
                this.logger.w("This banner ad has expired. Please load another ad.");
                return false;
            } else if (getAdController().startAdDrawing()) {
                if (!this.autoShow) {
                    getAdController().getMetricsCollector().stopMetric(MetricType.AD_LOADED_TO_AD_SHOW_TIME);
                }
                getAdController().getMetricsCollector().startMetric(MetricType.AD_SHOW_LATENCY);
                if (this.currentView != null) {
                    removeView(this.currentView);
                }
                if (this.currentDestroyable != null) {
                    this.currentDestroyable.destroy();
                }
                this.currentView = getAdController().getView();
                this.currentDestroyable = getAdController().getDestroyable();
                addView(this.currentView, new LayoutParams(-1, -1, 17));
                getAdController().getMetricsCollector().startMetric(MetricType.AD_SHOW_DURATION);
                adShown();
                return true;
            } else {
                this.logger.w("Banner ad could not be shown.");
                return false;
            }
        } else if (isLoading()) {
            this.logger.w("The banner ad cannot be shown because it is still loading.");
            return false;
        } else if (isShowing()) {
            this.logger.w("The banner ad cannot be shown because it is already showing.");
            return false;
        } else if (isReadyToLoad()) {
            this.logger.w("The banner ad cannot be shown because it has not loaded successfully.");
            return false;
        } else {
            this.logger.w("A banner ad is not ready to show.");
            return false;
        }
    }

    public void enableAutoShow() {
        this.autoShow = true;
    }

    public void disableAutoShow() {
        this.autoShow = false;
    }
}
