package com.amazon.device.ads;

import org.json.JSONObject;

class SISUpdateDeviceInfoRequest extends SISDeviceRequest {
    private static final MetricType CALL_METRIC_TYPE = MetricType.SIS_LATENCY_UPDATE_DEVICE_INFO;
    private static final String LOGTAG = "SISUpdateDeviceInfoRequest";
    private static final String PATH = "/update_dev_info";
    private final DebugProperties debugPropertes;
    private final Metrics metrics;

    public SISUpdateDeviceInfoRequest(AdvertisingIdentifier advertisingIdentifier) {
        this(advertisingIdentifier, MobileAdsInfoStore.getInstance(), Configuration.getInstance(), DebugProperties.getInstance(), Metrics.getInstance());
    }

    SISUpdateDeviceInfoRequest(AdvertisingIdentifier advertisingIdentifier, MobileAdsInfoStore mobileAdsInfoStore, Configuration configuration, DebugProperties debugProperties, Metrics metrics) {
        super(new MobileAdsLoggerFactory(), LOGTAG, CALL_METRIC_TYPE, PATH, advertisingIdentifier, mobileAdsInfoStore, configuration);
        this.debugPropertes = debugProperties;
        this.metrics = metrics;
    }

    public QueryStringParameters getQueryParameters() {
        String debugPropertyAsString = this.debugPropertes.getDebugPropertyAsString(DebugProperties.DEBUG_ADID, getAdvertisingIdentifierInfo().getSISDeviceIdentifier());
        QueryStringParameters queryParameters = super.getQueryParameters();
        if (!StringUtils.isNullOrEmpty(debugPropertyAsString)) {
            queryParameters.putUrlEncoded("adId", debugPropertyAsString);
        }
        return queryParameters;
    }

    public void onResponseReceived(JSONObject jSONObject) {
        super.onResponseReceived(jSONObject);
        if (JSONUtils.getBooleanFromJSON(jSONObject, "idChanged", false)) {
            this.metrics.getMetricsCollector().incrementMetric(MetricType.SIS_COUNTER_IDENTIFIED_DEVICE_CHANGED);
        }
    }
}
