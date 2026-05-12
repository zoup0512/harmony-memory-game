package com.amazon.device.ads;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

class SISRegisterEventRequest extends SISRequest {
    private static final MetricType CALL_METRIC_TYPE = MetricType.SIS_LATENCY_REGISTER_EVENT;
    private static final String LOGTAG = "SISRegisterEventRequest";
    private static final String PATH = "/register_event";
    private final Info advertisingIdentifierInfo;
    private final AppEventRegistrationHandler appEventRegistrationHandler;
    private final JSONArray appEvents;

    public SISRegisterEventRequest(Info info, JSONArray jSONArray) {
        this(info, jSONArray, AppEventRegistrationHandler.getInstance(), new MobileAdsLoggerFactory(), MobileAdsInfoStore.getInstance(), Configuration.getInstance());
    }

    SISRegisterEventRequest(Info info, JSONArray jSONArray, AppEventRegistrationHandler appEventRegistrationHandler, MobileAdsLoggerFactory mobileAdsLoggerFactory, MobileAdsInfoStore mobileAdsInfoStore, Configuration configuration) {
        super(mobileAdsLoggerFactory, LOGTAG, CALL_METRIC_TYPE, PATH, mobileAdsInfoStore, configuration);
        this.advertisingIdentifierInfo = info;
        this.appEvents = jSONArray;
        this.appEventRegistrationHandler = appEventRegistrationHandler;
    }

    public QueryStringParameters getQueryParameters() {
        QueryStringParameters queryParameters = super.getQueryParameters();
        queryParameters.putUrlEncoded("adId", this.advertisingIdentifierInfo.getSISDeviceIdentifier());
        return queryParameters;
    }

    public HashMap<String, String> getPostParameters() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("events", this.appEvents.toString());
        return hashMap;
    }

    public void onResponseReceived(JSONObject jSONObject) {
        int integerFromJSON = JSONUtils.getIntegerFromJSON(jSONObject, "rcode", 0);
        if (integerFromJSON == 1) {
            this.logger.d("Application events registered successfully.");
            this.appEventRegistrationHandler.onAppEventsRegistered();
            return;
        }
        this.logger.d("Application events not registered. rcode:" + integerFromJSON);
    }
}
