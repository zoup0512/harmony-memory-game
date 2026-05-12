package com.amazon.device.ads;

class SISGenerateDIDRequest extends SISDeviceRequest {
    private static final MetricType CALL_METRIC_TYPE = MetricType.SIS_LATENCY_REGISTER;
    private static final String LOGTAG = SISGenerateDIDRequest.class.getSimpleName();
    private static final String PATH = "/generate_did";

    public SISGenerateDIDRequest(AdvertisingIdentifier advertisingIdentifier) {
        this(advertisingIdentifier, MobileAdsInfoStore.getInstance(), Configuration.getInstance());
    }

    SISGenerateDIDRequest(AdvertisingIdentifier advertisingIdentifier, MobileAdsInfoStore mobileAdsInfoStore, Configuration configuration) {
        super(new MobileAdsLoggerFactory(), LOGTAG, CALL_METRIC_TYPE, PATH, advertisingIdentifier, mobileAdsInfoStore, configuration);
    }
}
