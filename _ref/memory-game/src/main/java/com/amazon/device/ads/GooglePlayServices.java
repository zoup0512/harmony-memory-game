package com.amazon.device.ads;

class GooglePlayServices {
    private static final String GPS_AVAILABLE_SETTING = "gps-available";
    private static final String LOGTAG = GooglePlayServices.class.getSimpleName();
    private final MobileAdsLogger logger;
    private final ReflectionUtils reflectionUtils;

    static class AdvertisingInfo {
        private String advertisingIdentifier;
        private boolean gpsAvailable = true;
        private boolean limitAdTrackingEnabled;

        protected AdvertisingInfo() {
        }

        static AdvertisingInfo createNotAvailable() {
            return new AdvertisingInfo().setGPSAvailable(false);
        }

        boolean isGPSAvailable() {
            return this.gpsAvailable;
        }

        private AdvertisingInfo setGPSAvailable(boolean z) {
            this.gpsAvailable = z;
            return this;
        }

        String getAdvertisingIdentifier() {
            return this.advertisingIdentifier;
        }

        AdvertisingInfo setAdvertisingIdentifier(String str) {
            this.advertisingIdentifier = str;
            return this;
        }

        boolean hasAdvertisingIdentifier() {
            return getAdvertisingIdentifier() != null;
        }

        boolean isLimitAdTrackingEnabled() {
            return this.limitAdTrackingEnabled;
        }

        AdvertisingInfo setLimitAdTrackingEnabled(boolean z) {
            this.limitAdTrackingEnabled = z;
            return this;
        }
    }

    public GooglePlayServices() {
        this(new MobileAdsLoggerFactory(), new ReflectionUtils());
    }

    GooglePlayServices(MobileAdsLoggerFactory mobileAdsLoggerFactory, ReflectionUtils reflectionUtils) {
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.reflectionUtils = reflectionUtils;
    }

    public AdvertisingInfo getAdvertisingIdentifierInfo() {
        if (!isGPSAvailable()) {
            this.logger.v("The Google Play Services Advertising Identifier feature is not available.");
            return AdvertisingInfo.createNotAvailable();
        } else if (isGPSAvailableSet() || this.reflectionUtils.isClassAvailable("com.google.android.gms.ads.identifier.AdvertisingIdClient")) {
            AdvertisingInfo advertisingIdentifierInfo = createGooglePlayServicesAdapter().getAdvertisingIdentifierInfo();
            setGooglePlayServicesAvailable(advertisingIdentifierInfo.isGPSAvailable());
            return advertisingIdentifierInfo;
        } else {
            this.logger.v("The Google Play Services Advertising Identifier feature is not available.");
            setGooglePlayServicesAvailable(false);
            return AdvertisingInfo.createNotAvailable();
        }
    }

    protected GooglePlayServicesAdapter createGooglePlayServicesAdapter() {
        return new GooglePlayServicesAdapter();
    }

    private boolean isGPSAvailable() {
        return Settings.getInstance().getBoolean(GPS_AVAILABLE_SETTING, true);
    }

    private boolean isGPSAvailableSet() {
        return Settings.getInstance().containsKey(GPS_AVAILABLE_SETTING);
    }

    private void setGooglePlayServicesAvailable(boolean z) {
        Settings.getInstance().putTransientBoolean(GPS_AVAILABLE_SETTING, z);
    }
}
