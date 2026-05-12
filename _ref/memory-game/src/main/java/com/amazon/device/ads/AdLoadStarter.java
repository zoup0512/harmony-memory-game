package com.amazon.device.ads;

import android.annotation.SuppressLint;
import com.amazon.device.ads.AdError.ErrorCode;
import com.amazon.device.ads.ThreadUtils.ExecutionStyle;
import com.amazon.device.ads.ThreadUtils.ExecutionThread;
import com.amazon.device.ads.ThreadUtils.ThreadRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AdLoadStarter {
    private static final String LOGTAG = AdLoadStarter.class.getSimpleName();
    private final AdLoaderFactory adLoaderFactory;
    private final AdRequestBuilder adRequestBuilder;
    private final AdvertisingIdentifier advertisingIdentifier;
    private final Configuration configuration;
    private final MobileAdsInfoStore infoStore;
    private final MobileAdsLogger logger;
    private final PermissionChecker permissionChecker;
    private final Settings settings;
    private final SystemTime systemTime;
    private final ThreadRunner threadRunner;
    private final ViewabilityJavascriptFetcherListener viewabilityJavascriptFetcherListener;

    public AdLoadStarter() {
        this(new AdLoaderFactory(), new AdvertisingIdentifier(), ThreadUtils.getThreadRunner(), MobileAdsInfoStore.getInstance(), Settings.getInstance(), Configuration.getInstance(), new MobileAdsLoggerFactory(), new SystemTime(), new AdRequestBuilder(), new PermissionChecker(), new ViewabilityJavascriptFetcherListener());
    }

    AdLoadStarter(AdLoaderFactory adLoaderFactory, AdvertisingIdentifier advertisingIdentifier, ThreadRunner threadRunner, MobileAdsInfoStore mobileAdsInfoStore, Settings settings, Configuration configuration, MobileAdsLoggerFactory mobileAdsLoggerFactory, SystemTime systemTime, AdRequestBuilder adRequestBuilder, PermissionChecker permissionChecker, ViewabilityJavascriptFetcherListener viewabilityJavascriptFetcherListener) {
        this.adLoaderFactory = adLoaderFactory;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.advertisingIdentifier = advertisingIdentifier;
        this.infoStore = mobileAdsInfoStore;
        this.settings = settings;
        this.configuration = configuration;
        this.threadRunner = threadRunner;
        this.systemTime = systemTime;
        this.adRequestBuilder = adRequestBuilder;
        this.permissionChecker = permissionChecker;
        this.viewabilityJavascriptFetcherListener = viewabilityJavascriptFetcherListener;
    }

    public void loadAds(int i, AdTargetingOptions adTargetingOptions, AdSlot... adSlotArr) {
        if (!isNoRetry(adSlotArr)) {
            if (!(adTargetingOptions == null || !adTargetingOptions.isGeoLocationEnabled() || this.permissionChecker.hasLocationPermission(this.infoStore.getApplicationContext()))) {
                this.logger.w("Geolocation for ad targeting has been disabled. To enable geolocation, add at least one of the following permissions to the app manifest: 1. ACCESS_FINE_LOCATION; 2. ACCESS_COARSE_LOCATION.");
            }
            long nanoTime = this.systemTime.nanoTime();
            final ArrayList arrayList = new ArrayList();
            for (AdSlot adSlot : adSlotArr) {
                if (adSlot.prepareForAdLoad(nanoTime)) {
                    arrayList.add(adSlot);
                }
            }
            this.configuration.queueConfigurationListener(this.viewabilityJavascriptFetcherListener);
            final int i2 = i;
            final AdTargetingOptions adTargetingOptions2 = adTargetingOptions;
            new StartUpWaiter(this.settings, this.configuration) {
                protected void startUpReady() {
                    AdLoadStarter.this.infoStore.register();
                    AdLoadStarter.this.beginFetchAds(i2, adTargetingOptions2, arrayList);
                }

                protected void startUpFailed() {
                    AdLoadStarter.this.threadRunner.execute(new Runnable() {
                        public void run() {
                            AdLoadStarter.this.failAds(new AdError(ErrorCode.NETWORK_ERROR, "The configuration was unable to be loaded"), arrayList);
                        }
                    }, ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD);
                }
            }.start();
        }
    }

    @SuppressLint({"UseSparseArrays"})
    private void beginFetchAds(int i, AdTargetingOptions adTargetingOptions, List<AdSlot> list) {
        Info advertisingIdentifierInfo = this.advertisingIdentifier.getAdvertisingIdentifierInfo();
        if (advertisingIdentifierInfo.canDo()) {
            if (adTargetingOptions == null) {
                adTargetingOptions = new AdTargetingOptions();
            }
            AdRequest build = this.adRequestBuilder.withAdTargetingOptions(adTargetingOptions).withAdvertisingIdentifierInfo(advertisingIdentifierInfo).build();
            Map hashMap = new HashMap();
            int i2 = 1;
            for (AdSlot adSlot : list) {
                int i3;
                if (adSlot.isValid()) {
                    adSlot.setSlotNumber(i2);
                    hashMap.put(Integer.valueOf(i2), adSlot);
                    build.putSlot(adSlot);
                    i3 = i2 + 1;
                } else {
                    i3 = i2;
                }
                i2 = i3;
            }
            if (hashMap.size() > 0) {
                AdLoader createAdLoader = this.adLoaderFactory.createAdLoader(build, hashMap);
                createAdLoader.setTimeout(i);
                createAdLoader.beginFetchAd();
                return;
            }
            return;
        }
        failAds(new AdError(ErrorCode.INTERNAL_ERROR, "An internal request was not made on a background thread."), list);
    }

    private void failAds(AdError adError, List<AdSlot> list) {
        int i = 0;
        for (AdSlot adSlot : list) {
            int i2;
            if (adSlot.getSlotNumber() != -1) {
                adSlot.adFailed(adError);
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        if (i > 0) {
            this.logger.e("%s; code: %s", adError.getMessage(), adError.getCode());
        }
    }

    private boolean isNoRetry(AdSlot[] adSlotArr) {
        int noRetryTtlRemainingMillis = this.infoStore.getNoRetryTtlRemainingMillis();
        if (noRetryTtlRemainingMillis <= 0) {
            return false;
        }
        ErrorCode errorCode;
        noRetryTtlRemainingMillis /= 1000;
        String str = "SDK Message: ";
        if (this.infoStore.getIsAppDisabled()) {
            str = str + AdLoader.DISABLED_APP_SERVER_MESSAGE;
            errorCode = ErrorCode.INTERNAL_ERROR;
        } else {
            str = str + "no results. Try again in " + noRetryTtlRemainingMillis + " seconds.";
            errorCode = ErrorCode.NO_FILL;
        }
        failAds(new AdError(errorCode, str), new ArrayList(Arrays.asList(adSlotArr)));
        return true;
    }
}
