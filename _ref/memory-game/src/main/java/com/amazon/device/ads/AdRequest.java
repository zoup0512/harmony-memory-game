package com.amazon.device.ads;

import android.annotation.SuppressLint;
import com.amazon.device.ads.Configuration.ConfigOption;
import com.amazon.device.ads.JSONUtils.JSONUtilities;
import com.amazon.device.ads.WebRequest.HttpMethod;
import com.amazon.device.ads.WebRequest.WebRequestFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class AdRequest {
    private static final String LOGTAG = AdRequest.class.getSimpleName();
    private static final AAXParameter<?>[] PARAMETERS = new AAXParameter[]{AAXParameter.APP_KEY, AAXParameter.CHANNEL, AAXParameter.PUBLISHER_KEYWORDS, AAXParameter.PUBLISHER_ASINS, AAXParameter.USER_AGENT, AAXParameter.SDK_VERSION, AAXParameter.GEOLOCATION, AAXParameter.DEVICE_INFO, AAXParameter.PACKAGE_INFO, AAXParameter.TEST, AAXParameter.OPT_OUT};
    private static final AAXParameterGroup[] PARAMETER_GROUPS = new AAXParameterGroup[]{AAXParameterGroup.USER_ID, AAXParameterGroup.PUBLISHER_EXTRA_PARAMETERS};
    private Info advertisingIdentifierInfo;
    private final Configuration configuration;
    private final ConnectionInfo connectionInfo;
    private final DebugProperties debugProperties;
    private String instrPixelUrl;
    private final JSONObjectBuilder jsonObjectBuilder;
    private final JSONUtilities jsonUtilities;
    private final MobileAdsLogger logger;
    private final AdTargetingOptions opt;
    private final String orientation;
    protected final Map<Integer, LOISlot> slots;
    private final WebRequestFactory webRequestFactory;

    static class AdRequestBuilder {
        private AdTargetingOptions adTargetingOptions;
        private Info advertisingIdentifierInfo;

        AdRequestBuilder() {
        }

        public AdRequestBuilder withAdTargetingOptions(AdTargetingOptions adTargetingOptions) {
            this.adTargetingOptions = adTargetingOptions;
            return this;
        }

        public AdRequestBuilder withAdvertisingIdentifierInfo(Info info) {
            this.advertisingIdentifierInfo = info;
            return this;
        }

        public AdRequest build() {
            return new AdRequest(this.adTargetingOptions).setAdvertisingIdentifierInfo(this.advertisingIdentifierInfo);
        }
    }

    static class JSONObjectBuilder {
        private AAXParameterGroup[] aaxParameterGroups;
        private AAXParameter<?>[] aaxParameters;
        private Map<String, String> advancedOptions;
        private final JSONObject json;
        private final MobileAdsLogger logger;
        private ParameterData parameterData;

        JSONObjectBuilder(MobileAdsLogger mobileAdsLogger) {
            this(mobileAdsLogger, new JSONObject());
        }

        JSONObjectBuilder(MobileAdsLogger mobileAdsLogger, JSONObject jSONObject) {
            this.logger = mobileAdsLogger;
            this.json = jSONObject;
        }

        JSONObjectBuilder setAAXParameters(AAXParameter<?>[] aAXParameterArr) {
            this.aaxParameters = aAXParameterArr;
            return this;
        }

        JSONObjectBuilder setAAXParameterGroups(AAXParameterGroup[] aAXParameterGroupArr) {
            this.aaxParameterGroups = aAXParameterGroupArr;
            return this;
        }

        JSONObjectBuilder setAdvancedOptions(Map<String, String> map) {
            this.advancedOptions = map;
            return this;
        }

        JSONObjectBuilder setParameterData(ParameterData parameterData) {
            this.parameterData = parameterData;
            return this;
        }

        ParameterData getParameterData() {
            return this.parameterData;
        }

        JSONObject getJSON() {
            return this.json;
        }

        void build() {
            int i = 0;
            if (this.aaxParameterGroups != null) {
                for (AAXParameterGroup evaluate : this.aaxParameterGroups) {
                    evaluate.evaluate(this.parameterData, this.json);
                }
            }
            AAXParameter[] aAXParameterArr = this.aaxParameters;
            int length = aAXParameterArr.length;
            while (i < length) {
                AAXParameter aAXParameter = aAXParameterArr[i];
                putIntoJSON(aAXParameter, aAXParameter.getValue(this.parameterData));
                i++;
            }
            if (this.advancedOptions != null) {
                for (Entry entry : this.advancedOptions.entrySet()) {
                    if (!StringUtils.isNullOrWhiteSpace((String) entry.getValue())) {
                        putIntoJSON((String) entry.getKey(), entry.getValue());
                    }
                }
            }
        }

        void putIntoJSON(AAXParameter<?> aAXParameter, Object obj) {
            putIntoJSON(aAXParameter.getName(), obj);
        }

        void putIntoJSON(String str, Object obj) {
            if (obj != null) {
                try {
                    this.json.put(str, obj);
                } catch (JSONException e) {
                    this.logger.d("Could not add parameter to JSON %s: %s", str, obj);
                }
            }
        }
    }

    static class LOISlot {
        static final AAXParameter<?>[] PARAMETERS = new AAXParameter[]{AAXParameter.SIZE, AAXParameter.PAGE_TYPE, AAXParameter.SLOT, AAXParameter.SLOT_POSITION, AAXParameter.MAX_SIZE, AAXParameter.SLOT_ID, AAXParameter.FLOOR_PRICE, AAXParameter.SUPPORTED_MEDIA_TYPES, AAXParameter.VIDEO_OPTIONS};
        private final AdSlot adSlot;
        private final DebugProperties debugProperties;
        private final JSONObjectBuilder jsonObjectBuilder;
        private final JSONUtilities jsonUtilities;
        private final AdTargetingOptions opt;

        LOISlot(AdSlot adSlot, AdRequest adRequest, MobileAdsLogger mobileAdsLogger) {
            this(adSlot, adRequest, mobileAdsLogger, new JSONObjectBuilder(mobileAdsLogger), DebugProperties.getInstance(), new JSONUtilities());
        }

        LOISlot(AdSlot adSlot, AdRequest adRequest, MobileAdsLogger mobileAdsLogger, JSONObjectBuilder jSONObjectBuilder, DebugProperties debugProperties, JSONUtilities jSONUtilities) {
            this.opt = adSlot.getAdTargetingOptions();
            this.adSlot = adSlot;
            this.debugProperties = debugProperties;
            this.jsonUtilities = jSONUtilities;
            Map copyOfAdvancedOptions = this.opt.getCopyOfAdvancedOptions();
            if (this.debugProperties.containsDebugProperty(DebugProperties.DEBUG_ADVTARGETING)) {
                JSONObject debugPropertyAsJSONObject = this.debugProperties.getDebugPropertyAsJSONObject(DebugProperties.DEBUG_ADVTARGETING, null);
                if (debugPropertyAsJSONObject != null) {
                    copyOfAdvancedOptions.putAll(this.jsonUtilities.createMapFromJSON(debugPropertyAsJSONObject));
                }
            }
            this.jsonObjectBuilder = jSONObjectBuilder.setAAXParameters(PARAMETERS).setAdvancedOptions(copyOfAdvancedOptions).setParameterData(new ParameterData().setAdTargetingOptions(this.opt).setAdvancedOptions(copyOfAdvancedOptions).setLOISlot(this).setAdRequest(adRequest));
        }

        AdTargetingOptions getAdTargetingOptions() {
            return this.opt;
        }

        JSONObject getJSON() {
            this.jsonObjectBuilder.build();
            return this.jsonObjectBuilder.getJSON();
        }

        AdSlot getAdSlot() {
            return this.adSlot;
        }
    }

    public AdRequest(AdTargetingOptions adTargetingOptions) {
        this(adTargetingOptions, new WebRequestFactory(), MobileAdsInfoStore.getInstance(), Configuration.getInstance(), DebugProperties.getInstance(), new MobileAdsLoggerFactory(), new JSONUtilities());
    }

    @SuppressLint({"UseSparseArrays"})
    AdRequest(AdTargetingOptions adTargetingOptions, WebRequestFactory webRequestFactory, MobileAdsInfoStore mobileAdsInfoStore, Configuration configuration, DebugProperties debugProperties, MobileAdsLoggerFactory mobileAdsLoggerFactory, JSONUtilities jSONUtilities) {
        this.opt = adTargetingOptions;
        this.webRequestFactory = webRequestFactory;
        this.jsonUtilities = jSONUtilities;
        this.slots = new HashMap();
        this.orientation = mobileAdsInfoStore.getDeviceInfo().getOrientation();
        this.connectionInfo = new ConnectionInfo(mobileAdsInfoStore);
        this.configuration = configuration;
        this.debugProperties = debugProperties;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        Map copyOfAdvancedOptions = this.opt.getCopyOfAdvancedOptions();
        if (this.debugProperties.containsDebugProperty(DebugProperties.DEBUG_ADVTARGETING)) {
            JSONObject debugPropertyAsJSONObject = this.debugProperties.getDebugPropertyAsJSONObject(DebugProperties.DEBUG_ADVTARGETING, null);
            if (debugPropertyAsJSONObject != null) {
                copyOfAdvancedOptions.putAll(this.jsonUtilities.createMapFromJSON(debugPropertyAsJSONObject));
            }
        }
        this.jsonObjectBuilder = new JSONObjectBuilder(this.logger).setAAXParameters(PARAMETERS).setAAXParameterGroups(PARAMETER_GROUPS).setAdvancedOptions(copyOfAdvancedOptions).setParameterData(new ParameterData().setAdTargetingOptions(this.opt).setAdvancedOptions(copyOfAdvancedOptions).setAdRequest(this));
    }

    public void setInstrumentationPixelURL(String str) {
        this.instrPixelUrl = str;
    }

    public String getInstrumentationPixelURL() {
        return this.instrPixelUrl;
    }

    AdTargetingOptions getAdTargetingOptions() {
        return this.opt;
    }

    String getOrientation() {
        return this.orientation;
    }

    Info getAdvertisingIdentifierInfo() {
        return this.advertisingIdentifierInfo;
    }

    AdRequest setAdvertisingIdentifierInfo(Info info) {
        this.advertisingIdentifierInfo = info;
        return this;
    }

    public void putSlot(AdSlot adSlot) {
        if (getAdvertisingIdentifierInfo().hasSISDeviceIdentifier()) {
            adSlot.getMetricsCollector().incrementMetric(MetricType.AD_COUNTER_IDENTIFIED_DEVICE);
        }
        adSlot.setConnectionInfo(this.connectionInfo);
        this.slots.put(Integer.valueOf(adSlot.getSlotNumber()), new LOISlot(adSlot, this, this.logger));
    }

    protected JSONArray getSlots() {
        JSONArray jSONArray = new JSONArray();
        for (LOISlot json : this.slots.values()) {
            jSONArray.put(json.getJSON());
        }
        return jSONArray;
    }

    public WebRequest getWebRequest() {
        WebRequest createWebRequest = this.webRequestFactory.createWebRequest();
        boolean z = isSSLRequired() || createWebRequest.getUseSecure();
        createWebRequest.setUseSecure(z);
        createWebRequest.setExternalLogTag(LOGTAG);
        createWebRequest.setHttpMethod(HttpMethod.POST);
        createWebRequest.setHost(this.configuration.getString(ConfigOption.AAX_HOSTNAME));
        createWebRequest.setPath(this.configuration.getString(ConfigOption.AD_RESOURCE_PATH));
        createWebRequest.enableLog(true);
        createWebRequest.setContentType("application/json");
        createWebRequest.setDisconnectEnabled(false);
        setParametersInWebRequest(createWebRequest);
        return createWebRequest;
    }

    private boolean isSSLRequired() {
        return !Configuration.getInstance().getBoolean(ConfigOption.TRUNCATE_LAT_LON) && Configuration.getInstance().getBoolean(ConfigOption.SEND_GEO) && getAdTargetingOptions().isGeoLocationEnabled();
    }

    protected void setParametersInWebRequest(WebRequest webRequest) {
        this.jsonObjectBuilder.build();
        Object value = AAXParameter.SLOTS.getValue(this.jsonObjectBuilder.getParameterData());
        if (value == null) {
            value = getSlots();
        }
        this.jsonObjectBuilder.putIntoJSON(AAXParameter.SLOTS, value);
        JSONObject json = this.jsonObjectBuilder.getJSON();
        String debugPropertyAsString = this.debugProperties.getDebugPropertyAsString(DebugProperties.DEBUG_AAX_AD_PARAMS, null);
        if (!StringUtils.isNullOrEmpty(debugPropertyAsString)) {
            webRequest.setAdditionalQueryParamsString(debugPropertyAsString);
        }
        setRequestBodyString(webRequest, json);
    }

    protected void setRequestBodyString(WebRequest webRequest, JSONObject jSONObject) {
        webRequest.setRequestBodyString(jSONObject.toString());
    }
}
