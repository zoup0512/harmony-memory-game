package com.amazon.device.ads;

import android.annotation.TargetApi;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalFocusChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.ViewTreeObserver.OnWindowFocusChangeListener;
import com.amazon.device.ads.Configuration.ConfigOption;
import com.amazon.device.ads.SDKEvent.SDKEventType;
import com.facebook.internal.ServerProtocol;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

class ViewabilityObserver {
    public static final String IS_VIEWABLE_KEY = "IS_VIEWABLE";
    private static final String LOGTAG = ViewabilityObserver.class.getSimpleName();
    private static long VIEWABLE_INTERVAL = 200;
    public static final String VIEWABLE_PARAMS_KEY = "VIEWABLE_PARAMS";
    private final AdController adController;
    private final Configuration configuration;
    private final DebugProperties debugProperties;
    private boolean firedOnlyOnce;
    private final AtomicBoolean isScrollListenerAdded;
    private long lastTimeViewableEventFired;
    private final MobileAdsLogger logger;
    private boolean observersAdded;
    private final OnGlobalFocusChangeListener onGlobalFocusChangeListener;
    private final OnGlobalLayoutListener onGlobalLayoutListener;
    private final OnScrollChangedListener onScrollChangedListener;
    private OnWindowFocusChangeListener onWindowFocusChangeListener;
    private ViewTreeObserver viewTreeObserver;
    private final ViewUtils viewUtils;
    private final ViewabilityChecker viewabilityChecker;
    private final AtomicInteger viewabilityInterestCount;

    public ViewabilityObserver(AdController adController) {
        this(adController, new ViewabilityCheckerFactory(), new MobileAdsLoggerFactory(), new AmazonOnGlobalFocusChangeListenerFactory(), new AmazonOnGlobalLayoutListenerFactory(), new AmazonOnScrollChangedListenerFactory(), new AmazonOnWindowFocusChangeListenerFactory(), new AtomicInteger(0), new AtomicBoolean(false), new ViewUtils(), DebugProperties.getInstance(), Configuration.getInstance());
    }

    ViewabilityObserver(AdController adController, ViewabilityCheckerFactory viewabilityCheckerFactory, MobileAdsLoggerFactory mobileAdsLoggerFactory, AmazonOnGlobalFocusChangeListenerFactory amazonOnGlobalFocusChangeListenerFactory, AmazonOnGlobalLayoutListenerFactory amazonOnGlobalLayoutListenerFactory, AmazonOnScrollChangedListenerFactory amazonOnScrollChangedListenerFactory, AmazonOnWindowFocusChangeListenerFactory amazonOnWindowFocusChangeListenerFactory, AtomicInteger atomicInteger, AtomicBoolean atomicBoolean, ViewUtils viewUtils, DebugProperties debugProperties, Configuration configuration) {
        this.firedOnlyOnce = false;
        this.observersAdded = false;
        this.lastTimeViewableEventFired = 0;
        this.adController = adController;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.viewabilityChecker = viewabilityCheckerFactory.buildViewabilityChecker(this.adController);
        this.onGlobalFocusChangeListener = amazonOnGlobalFocusChangeListenerFactory.buildAmazonOnGlobalFocusChangedListener(this);
        this.onGlobalLayoutListener = amazonOnGlobalLayoutListenerFactory.buildAmazonOnGlobalLayoutListener(this);
        this.onScrollChangedListener = amazonOnScrollChangedListenerFactory.buildAmazonOnScrollChangedListenerFactory(this);
        if (AndroidTargetUtils.isAtLeastAndroidAPI(18)) {
            this.onWindowFocusChangeListener = amazonOnWindowFocusChangeListenerFactory.buildOnWindowFocusChangeListener(this);
        }
        this.viewabilityInterestCount = atomicInteger;
        this.isScrollListenerAdded = atomicBoolean;
        this.viewUtils = viewUtils;
        this.debugProperties = debugProperties;
        this.configuration = configuration;
        VIEWABLE_INTERVAL = this.debugProperties.getDebugPropertyAsLong(DebugProperties.DEBUG_VIEWABLE_INTERVAL, Long.valueOf(this.configuration.getLongWithDefault(ConfigOption.VIEWABLE_INTERVAL, 200))).longValue();
        this.logger.d("Viewable Interval is: %d", Long.valueOf(VIEWABLE_INTERVAL));
    }

    public void registerViewabilityInterest() {
        int incrementAndGet = this.viewabilityInterestCount.incrementAndGet();
        this.logger.d("Viewability Interest Registered. Current number of objects interested in viewability: %d", Integer.valueOf(incrementAndGet));
        synchronized (this) {
            addObserversIfNeeded();
        }
    }

    @TargetApi(18)
    private void addObserversIfNeeded() {
        if (this.viewTreeObserver == null || !isViewTreeObserverAlive() || hasViewTreeObserverChanged()) {
            this.viewTreeObserver = this.adController.getAdContainer().getViewTreeObserver();
            this.observersAdded = false;
            this.isScrollListenerAdded.set(false);
            this.firedOnlyOnce = false;
            this.lastTimeViewableEventFired = 0;
        }
        if (this.viewTreeObserver != null && isViewTreeObserverAlive() && !this.observersAdded) {
            this.viewTreeObserver.addOnGlobalLayoutListener(this.onGlobalLayoutListener);
            this.viewTreeObserver.addOnGlobalFocusChangeListener(this.onGlobalFocusChangeListener);
            if (AndroidTargetUtils.isAtLeastAndroidAPI(18)) {
                this.viewTreeObserver.addOnWindowFocusChangeListener(this.onWindowFocusChangeListener);
            }
            this.observersAdded = true;
            fireViewableEvent(false);
        }
    }

    protected void addOnScrollChangedListenerIfNeeded() {
        if (!this.isScrollListenerAdded.get()) {
            if (this.viewTreeObserver == null || !this.viewTreeObserver.isAlive() || hasViewTreeObserverChanged()) {
                this.viewTreeObserver = this.adController.getAdContainer().getViewTreeObserver();
            }
            this.viewTreeObserver.addOnScrollChangedListener(this.onScrollChangedListener);
            this.isScrollListenerAdded.set(true);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void deregisterViewabilityInterest() {
        /*
        r6 = this;
        monitor-enter(r6);
        r0 = r6.viewabilityInterestCount;	 Catch:{ all -> 0x002f }
        r0 = r0.decrementAndGet();	 Catch:{ all -> 0x002f }
        if (r0 >= 0) goto L_0x0017;
    L_0x0009:
        r0 = r6.logger;	 Catch:{ all -> 0x002f }
        r1 = "No Viewability Interest was previously registered. Ignoring request to deregister.";
        r0.w(r1);	 Catch:{ all -> 0x002f }
        r0 = r6.viewabilityInterestCount;	 Catch:{ all -> 0x002f }
        r0.incrementAndGet();	 Catch:{ all -> 0x002f }
        monitor-exit(r6);	 Catch:{ all -> 0x002f }
    L_0x0016:
        return;
    L_0x0017:
        r1 = r6.logger;	 Catch:{ all -> 0x002f }
        r2 = "Viewability Interest Deregistered. Current number of objects interested in viewability: %d";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x002f }
        r4 = 0;
        r5 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x002f }
        r3[r4] = r5;	 Catch:{ all -> 0x002f }
        r1.d(r2, r3);	 Catch:{ all -> 0x002f }
        if (r0 != 0) goto L_0x002d;
    L_0x002a:
        r6.removeObservers();	 Catch:{ all -> 0x002f }
    L_0x002d:
        monitor-exit(r6);	 Catch:{ all -> 0x002f }
        goto L_0x0016;
    L_0x002f:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x002f }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.device.ads.ViewabilityObserver.deregisterViewabilityInterest():void");
    }

    @TargetApi(18)
    private void removeObservers() {
        if (this.viewTreeObserver == null) {
            this.logger.w("Root view tree observer is null");
        } else if (this.viewUtils.removeOnGlobalLayoutListener(this.viewTreeObserver, this.onGlobalLayoutListener)) {
            this.viewTreeObserver.removeOnScrollChangedListener(this.onScrollChangedListener);
            this.viewTreeObserver.removeOnGlobalFocusChangeListener(this.onGlobalFocusChangeListener);
            if (AndroidTargetUtils.isAtLeastAndroidAPI(18)) {
                this.viewTreeObserver.removeOnWindowFocusChangeListener(this.onWindowFocusChangeListener);
            }
            this.observersAdded = false;
            this.isScrollListenerAdded.set(false);
        } else {
            this.logger.w("Root view tree observer is not alive");
        }
    }

    public void fireViewableEvent(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if (!z || currentTimeMillis - this.lastTimeViewableEventFired >= VIEWABLE_INTERVAL) {
            this.lastTimeViewableEventFired = currentTimeMillis;
            ViewabilityInfo viewabilityInfo = this.viewabilityChecker.getViewabilityInfo();
            if (viewabilityInfo == null) {
                this.logger.w("Viewable info is null");
                return;
            }
            JSONObject jsonObject = viewabilityInfo.getJsonObject();
            boolean isAdOnScreen = viewabilityInfo.isAdOnScreen();
            SDKEvent sDKEvent = new SDKEvent(SDKEventType.VIEWABLE);
            sDKEvent.setParameter(VIEWABLE_PARAMS_KEY, jsonObject.toString());
            sDKEvent.setParameter(IS_VIEWABLE_KEY, isAdOnScreen ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : " false");
            if (isAdOnScreen) {
                this.adController.fireSDKEvent(sDKEvent);
                this.firedOnlyOnce = false;
            } else if (!this.firedOnlyOnce) {
                this.adController.fireSDKEvent(sDKEvent);
                this.firedOnlyOnce = true;
            }
        }
    }

    private boolean isViewTreeObserverAlive() {
        if (this.viewTreeObserver.isAlive()) {
            return true;
        }
        this.logger.w("Root view tree observer is not alive");
        return false;
    }

    private boolean hasViewTreeObserverChanged() {
        return this.viewTreeObserver != this.adController.getAdContainer().getViewTreeObserver();
    }

    public boolean isViewable() {
        ViewabilityInfo viewabilityInfo = this.viewabilityChecker.getViewabilityInfo();
        if (viewabilityInfo != null) {
            return viewabilityInfo.isAdOnScreen();
        }
        this.logger.w("Viewable info is null");
        return false;
    }
}
