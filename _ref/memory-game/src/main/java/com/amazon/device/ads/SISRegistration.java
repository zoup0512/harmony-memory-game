package com.amazon.device.ads;

import com.amazon.device.ads.ThreadUtils.RunnableExecutor;
import com.amazon.device.ads.ThreadUtils.SingleThreadScheduler;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;

class SISRegistration {
    protected static final long DEFAULT_SIS_CHECKIN_INTERVAL = 86400000;
    private static final String LOGTAG = SISRegistration.class.getSimpleName();
    private static final String SIS_LAST_CHECKIN_PREF_NAME = "amzn-ad-sis-last-checkin";
    private static final SingleThreadScheduler singleThreadScheduler = new SingleThreadScheduler();
    private final AdvertisingIdentifier advertisingIdentifier;
    private final AppEventRegistrationHandler appEventRegistrationHandler;
    private final Configuration configuration;
    private final DebugProperties debugProperties;
    private final RunnableExecutor executor;
    private final MobileAdsInfoStore infoStore;
    private final MobileAdsLogger logger;
    private final Settings settings;
    private final SISRequestFactory sisRequestFactory;
    private final SISRequestorFactory sisRequestorFactory;
    private final SystemTime systemTime;
    private final ThreadVerify threadVerify;

    protected static class RegisterEventsSISRequestorCallback implements SISRequestorCallback {
        private final SISRegistration sisRegistration;

        public RegisterEventsSISRequestorCallback(SISRegistration sISRegistration) {
            this.sisRegistration = sISRegistration;
        }

        public void onSISCallComplete() {
            this.sisRegistration.registerEvents();
        }
    }

    public SISRegistration() {
        this(new SISRequestFactory(), new SISRequestorFactory(), new AdvertisingIdentifier(), MobileAdsInfoStore.getInstance(), Configuration.getInstance(), Settings.getInstance(), AppEventRegistrationHandler.getInstance(), new SystemTime(), singleThreadScheduler, new ThreadVerify(), new MobileAdsLoggerFactory(), DebugProperties.getInstance());
    }

    SISRegistration(SISRequestFactory sISRequestFactory, SISRequestorFactory sISRequestorFactory, AdvertisingIdentifier advertisingIdentifier, MobileAdsInfoStore mobileAdsInfoStore, Configuration configuration, Settings settings, AppEventRegistrationHandler appEventRegistrationHandler, SystemTime systemTime, RunnableExecutor runnableExecutor, ThreadVerify threadVerify, MobileAdsLoggerFactory mobileAdsLoggerFactory, DebugProperties debugProperties) {
        this.sisRequestFactory = sISRequestFactory;
        this.sisRequestorFactory = sISRequestorFactory;
        this.advertisingIdentifier = advertisingIdentifier;
        this.infoStore = mobileAdsInfoStore;
        this.configuration = configuration;
        this.settings = settings;
        this.appEventRegistrationHandler = appEventRegistrationHandler;
        this.systemTime = systemTime;
        this.executor = runnableExecutor;
        this.threadVerify = threadVerify;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.debugProperties = debugProperties;
    }

    private MobileAdsLogger getLogger() {
        return this.logger;
    }

    protected boolean canRegister(long j) {
        RegistrationInfo registrationInfo = this.infoStore.getRegistrationInfo();
        if (exceededCheckinInterval(j) || registrationInfo.shouldGetNewSISDeviceIdentifer() || registrationInfo.shouldGetNewSISRegistration() || this.debugProperties.getDebugPropertyAsBoolean(DebugProperties.DEBUG_SHOULD_REGISTER_SIS, Boolean.valueOf(false)).booleanValue()) {
            return true;
        }
        return false;
    }

    protected boolean shouldUpdateDeviceInfo() {
        return this.infoStore.getRegistrationInfo().isRegisteredWithSIS();
    }

    public void registerApp() {
        this.executor.execute(new Runnable() {
            public void run() {
                SISRegistration.this.waitForConfigurationThenBeginRegistration();
            }
        });
    }

    void waitForConfigurationThenBeginRegistration() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.configuration.queueConfigurationListener(new ConfigurationListener() {
            public void onConfigurationReady() {
                atomicBoolean.set(true);
                countDownLatch.countDown();
            }

            public void onConfigurationFailure() {
                SISRegistration.this.getLogger().w("Configuration fetching failed so device registration will not proceed.");
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
        }
        if (atomicBoolean.get()) {
            registerAppWorker();
        }
    }

    void registerAppWorker() {
        long currentTimeMillis = this.systemTime.currentTimeMillis();
        if (this.advertisingIdentifier.getAdvertisingIdentifierInfo().canDo() && canRegister(currentTimeMillis)) {
            putLastSISCheckin(currentTimeMillis);
            if (shouldUpdateDeviceInfo()) {
                updateDeviceInfo(this.advertisingIdentifier);
            } else {
                register(this.advertisingIdentifier);
            }
        }
    }

    protected boolean exceededCheckinInterval(long j) {
        return j - getLastSISCheckin() > this.debugProperties.getDebugPropertyAsLong(DebugProperties.DEBUG_SIS_CHECKIN_INTERVAL, Long.valueOf(86400000)).longValue();
    }

    protected void register(AdvertisingIdentifier advertisingIdentifier) {
        SISDeviceRequest createDeviceRequest = this.sisRequestFactory.createDeviceRequest(SISDeviceRequestType.GENERATE_DID, advertisingIdentifier);
        SISRequestorCallback registerEventsSISRequestorCallback = new RegisterEventsSISRequestorCallback(this);
        this.sisRequestorFactory.createSISRequestor(registerEventsSISRequestorCallback, createDeviceRequest).startCallSIS();
    }

    protected void updateDeviceInfo(AdvertisingIdentifier advertisingIdentifier) {
        SISDeviceRequest createDeviceRequest = this.sisRequestFactory.createDeviceRequest(SISDeviceRequestType.UPDATE_DEVICE_INFO, advertisingIdentifier);
        SISRequestorCallback registerEventsSISRequestorCallback = new RegisterEventsSISRequestorCallback(this);
        this.sisRequestorFactory.createSISRequestor(registerEventsSISRequestorCallback, createDeviceRequest).startCallSIS();
    }

    protected long getLastSISCheckin() {
        return this.settings.getLong(SIS_LAST_CHECKIN_PREF_NAME, 0);
    }

    private void putLastSISCheckin(long j) {
        this.settings.putLong(SIS_LAST_CHECKIN_PREF_NAME, j);
    }

    protected void registerEvents() {
        if (this.threadVerify.isOnMainThread()) {
            getLogger().e("Registering events must be done on a background thread.");
            return;
        }
        Info advertisingIdentifierInfo = this.advertisingIdentifier.getAdvertisingIdentifierInfo();
        if (advertisingIdentifierInfo.hasSISDeviceIdentifier()) {
            JSONArray appEventsJSONArray = this.appEventRegistrationHandler.getAppEventsJSONArray();
            if (appEventsJSONArray != null) {
                SISRegisterEventRequest createRegisterEventRequest = this.sisRequestFactory.createRegisterEventRequest(advertisingIdentifierInfo, appEventsJSONArray);
                this.sisRequestorFactory.createSISRequestor(createRegisterEventRequest).startCallSIS();
            }
        }
    }
}
