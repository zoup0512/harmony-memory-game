package com.amazon.device.ads;

import android.content.Context;
import android.location.Location;
import com.amazon.device.ads.Configuration.ConfigOption;
import com.amazon.device.ads.Parsers.IntegerParser;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

abstract class AAXParameter<T> {
    static final AAXParameter<String> APP_KEY = new AppKeyParameter();
    static final AAXParameter<String> CHANNEL = new StringParameter("c", DebugProperties.DEBUG_CHANNEL);
    static final AAXParameter<JSONObject> DEVICE_INFO = new DeviceInfoParameter();
    static final AAXParameter<Long> FLOOR_PRICE = new FloorPriceParameter();
    static final AAXParameter<String> GEOLOCATION = new GeoLocationParameter();
    private static final String LOGTAG = AAXParameter.class.getSimpleName();
    static final AAXParameter<String> MAX_SIZE = new MaxSizeParameter();
    static final AAXParameter<Boolean> OPT_OUT = new OptOutParameter();
    static final AAXParameter<JSONObject> PACKAGE_INFO = new PackageInfoParameter();
    static final AAXParameter<String> PAGE_TYPE = new StringParameter("pt", DebugProperties.DEBUG_PT);
    static final AAXParameter<JSONArray> PUBLISHER_ASINS = new JSONArrayParameter("pa", DebugProperties.DEBUG_PA);
    static final PublisherKeywordsParameter PUBLISHER_KEYWORDS = new PublisherKeywordsParameter();
    static final AAXParameter<String> SDK_VERSION = new SDKVersionParameter();
    static final AAXParameter<String> SIZE = new SizeParameter();
    static final AAXParameter<String> SLOT = new SlotParameter();
    static final AAXParameter<JSONArray> SLOTS = new JSONArrayParameter("slots", DebugProperties.DEBUG_SLOTS);
    static final AAXParameter<Integer> SLOT_ID = new SlotIdParameter();
    static final AAXParameter<String> SLOT_POSITION = new StringParameter("sp", DebugProperties.DEBUG_SP);
    static final AAXParameter<JSONArray> SUPPORTED_MEDIA_TYPES = new SupportedMediaTypesParameter();
    static final AAXParameter<Boolean> TEST = new TestParameter();
    static final AAXParameter<String> USER_AGENT = new UserAgentParameter();
    static final AAXParameter<JSONObject> VIDEO_OPTIONS = new VideoOptionsParameter();
    private final String debugName;
    private final String name;

    static class StringParameter extends AAXParameter<String> {
        StringParameter(String str, String str2) {
            super(str, str2);
        }

        protected String parseFromString(String str) {
            return str;
        }

        protected String getFromDebugProperties() {
            return DebugProperties.getInstance().getDebugPropertyAsString(getDebugName(), null);
        }
    }

    static class AppKeyParameter extends StringParameter {
        AppKeyParameter() {
            super("appId", DebugProperties.DEBUG_APPID);
        }

        protected String getDerivedValue(ParameterData parameterData) {
            return MobileAdsInfoStore.getInstance().getRegistrationInfo().getAppKey();
        }
    }

    static class BooleanParameter extends AAXParameter<Boolean> {
        BooleanParameter(String str, String str2) {
            super(str, str2);
        }

        protected Boolean parseFromString(String str) {
            return Boolean.valueOf(Boolean.parseBoolean(str));
        }

        protected Boolean getFromDebugProperties() {
            return DebugProperties.getInstance().getDebugPropertyAsBoolean(getDebugName(), null);
        }
    }

    static class JSONObjectParameter extends AAXParameter<JSONObject> {
        private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(AAXParameter.LOGTAG);

        JSONObjectParameter(String str, String str2) {
            super(str, str2);
        }

        protected JSONObject parseFromString(String str) {
            try {
                return new JSONObject(str);
            } catch (JSONException e) {
                this.logger.e("Unable to parse the following value into a JSONObject: %s", getName());
                return null;
            }
        }

        protected JSONObject getFromDebugProperties() {
            return parseFromString(DebugProperties.getInstance().getDebugPropertyAsString(getDebugName(), null));
        }
    }

    static class DeviceInfoParameter extends JSONObjectParameter {
        DeviceInfoParameter() {
            super("dinfo", DebugProperties.DEBUG_DINFO);
        }

        protected JSONObject getDerivedValue(ParameterData parameterData) {
            return MobileAdsInfoStore.getInstance().getDeviceInfo().toJsonObject(parameterData.adRequest.getOrientation());
        }
    }

    static class LongParameter extends AAXParameter<Long> {
        LongParameter(String str, String str2) {
            super(str, str2);
        }

        protected Long parseFromString(String str) {
            return Long.valueOf(Long.parseLong(str));
        }

        protected Long getFromDebugProperties() {
            return DebugProperties.getInstance().getDebugPropertyAsLong(getDebugName(), null);
        }
    }

    static class FloorPriceParameter extends LongParameter {
        FloorPriceParameter() {
            super("ec", DebugProperties.DEBUG_ECPM);
        }

        protected Long getDerivedValue(ParameterData parameterData) {
            if (parameterData.loiSlot.getAdTargetingOptions().hasFloorPrice()) {
                return Long.valueOf(parameterData.loiSlot.getAdTargetingOptions().getFloorPrice());
            }
            return null;
        }
    }

    static class GeoLocationParameter extends StringParameter {
        private final Configuration configuration;
        private final Context context;

        GeoLocationParameter() {
            this(Configuration.getInstance(), MobileAdsInfoStore.getInstance().getApplicationContext());
        }

        GeoLocationParameter(Configuration configuration, Context context) {
            super("geoloc", DebugProperties.DEBUG_GEOLOC);
            this.configuration = configuration;
            this.context = context;
        }

        protected String getDerivedValue(ParameterData parameterData) {
            if (!this.configuration.getBoolean(ConfigOption.SEND_GEO) || !parameterData.getAdRequest().getAdTargetingOptions().isGeoLocationEnabled()) {
                return null;
            }
            Location location = new AdLocation(this.context).getLocation();
            if (location == null) {
                return null;
            }
            return location.getLatitude() + "," + location.getLongitude();
        }
    }

    static class IntegerParameter extends AAXParameter<Integer> {
        IntegerParameter(String str, String str2) {
            super(str, str2);
        }

        protected Integer parseFromString(String str) {
            return Integer.valueOf(Integer.parseInt(str));
        }

        protected Integer getFromDebugProperties() {
            return DebugProperties.getInstance().getDebugPropertyAsInteger(getDebugName(), null);
        }
    }

    static class JSONArrayParameter extends AAXParameter<JSONArray> {
        private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(AAXParameter.LOGTAG);

        JSONArrayParameter(String str, String str2) {
            super(str, str2);
        }

        protected JSONArray parseFromString(String str) {
            try {
                return new JSONArray(str);
            } catch (JSONException e) {
                this.logger.e("Unable to parse the following value into a JSONArray: %s", getName());
                return null;
            }
        }

        protected JSONArray getFromDebugProperties() {
            return parseFromString(DebugProperties.getInstance().getDebugPropertyAsString(getDebugName(), null));
        }
    }

    static class MaxSizeParameter extends StringParameter {
        MaxSizeParameter() {
            super("mxsz", DebugProperties.DEBUG_MXSZ);
        }

        protected String getDerivedValue(ParameterData parameterData) {
            return parameterData.loiSlot.getAdSlot().getMaxSize();
        }
    }

    static class OptOutParameter extends BooleanParameter {
        OptOutParameter() {
            super("oo", DebugProperties.DEBUG_OPT_OUT);
        }

        protected Boolean getDerivedValue(ParameterData parameterData) {
            if (parameterData.adRequest.getAdvertisingIdentifierInfo().hasAdvertisingIdentifier()) {
                return Boolean.valueOf(parameterData.adRequest.getAdvertisingIdentifierInfo().isLimitAdTrackingEnabled());
            }
            return null;
        }
    }

    static class PackageInfoParameter extends JSONObjectParameter {
        PackageInfoParameter() {
            super("pkg", DebugProperties.DEBUG_PKG);
        }

        protected JSONObject getDerivedValue(ParameterData parameterData) {
            return MobileAdsInfoStore.getInstance().getAppInfo().getPackageInfoJSON();
        }
    }

    static class ParameterData {
        private AdRequest adRequest;
        private AdTargetingOptions adTargetingOptions;
        private Map<String, String> advancedOptions;
        private LOISlot loiSlot;
        private Map<String, String> temporaryOptions = new HashMap();

        ParameterData setAdRequest(AdRequest adRequest) {
            this.adRequest = adRequest;
            return this;
        }

        ParameterData setAdvancedOptions(Map<String, String> map) {
            this.advancedOptions = map;
            return this;
        }

        Map<String, String> getInternalAdvancedOptions() {
            return this.advancedOptions;
        }

        ParameterData setLOISlot(LOISlot lOISlot) {
            this.loiSlot = lOISlot;
            return this;
        }

        AdRequest getAdRequest() {
            return this.adRequest;
        }

        ParameterData setAdTargetingOptions(AdTargetingOptions adTargetingOptions) {
            this.adTargetingOptions = adTargetingOptions;
            return this;
        }
    }

    static class PublisherKeywordsParameter extends JSONArrayParameter {
        PublisherKeywordsParameter() {
            super("pk", DebugProperties.DEBUG_PK);
        }

        protected JSONArray applyPostParameterProcessing(JSONArray jSONArray, ParameterData parameterData) {
            if (jSONArray == null) {
                jSONArray = new JSONArray();
            }
            if (parameterData.adTargetingOptions != null) {
                HashSet internalPublisherKeywords = parameterData.adTargetingOptions.getInternalPublisherKeywords();
                if (internalPublisherKeywords != null) {
                    Iterator it = internalPublisherKeywords.iterator();
                    while (it.hasNext()) {
                        jSONArray.put((String) it.next());
                    }
                }
            }
            return jSONArray;
        }
    }

    static class SDKVersionParameter extends StringParameter {
        SDKVersionParameter() {
            super("adsdk", DebugProperties.DEBUG_VER);
        }

        protected String getDerivedValue(ParameterData parameterData) {
            return Version.getSDKVersion();
        }
    }

    static class SizeParameter extends StringParameter {
        SizeParameter() {
            super("sz", DebugProperties.DEBUG_SIZE);
        }

        protected String getDerivedValue(ParameterData parameterData) {
            return parameterData.loiSlot.getAdSlot().getRequestedAdSize().toString();
        }
    }

    static class SlotIdParameter extends IntegerParameter {
        SlotIdParameter() {
            super("slotId", DebugProperties.DEBUG_SLOT_ID);
        }

        protected Integer getDerivedValue(ParameterData parameterData) {
            return Integer.valueOf(parameterData.loiSlot.getAdSlot().getSlotNumber());
        }
    }

    static class SlotParameter extends StringParameter {
        SlotParameter() {
            super("slot", DebugProperties.DEBUG_SLOT);
        }

        protected String getDerivedValue(ParameterData parameterData) {
            return parameterData.adRequest.getOrientation();
        }
    }

    static class SupportedMediaTypesParameter extends JSONArrayParameter {
        public SupportedMediaTypesParameter() {
            super("supportedMediaTypes", DebugProperties.DEBUG_SUPPORTED_MEDIA_TYPES);
        }

        protected JSONArray getDerivedValue(ParameterData parameterData) {
            JSONArray jSONArray = new JSONArray();
            addDisplay(parameterData, jSONArray);
            addVideo(parameterData, jSONArray);
            return jSONArray;
        }

        private void addDisplay(ParameterData parameterData, JSONArray jSONArray) {
            boolean isDisplayAdsEnabled = parameterData.loiSlot.getAdTargetingOptions().isDisplayAdsEnabled();
            if (parameterData.advancedOptions.containsKey("enableDisplayAds")) {
                isDisplayAdsEnabled = Boolean.parseBoolean((String) parameterData.advancedOptions.remove("enableDisplayAds"));
            }
            if (isDisplayAdsEnabled) {
                jSONArray.put("DISPLAY");
            }
        }

        private void addVideo(ParameterData parameterData, JSONArray jSONArray) {
            if (new VideoAdsEnabledChecker(parameterData).isVideoAdsEnabled()) {
                jSONArray.put(ShareConstants.VIDEO_URL);
            }
        }
    }

    static class TestParameter extends BooleanParameter {
        TestParameter() {
            super("isTest", DebugProperties.DEBUG_TEST);
        }

        protected Boolean getDerivedValue(ParameterData parameterData) {
            return Settings.getInstance().getBoolean("testingEnabled", null);
        }
    }

    static class UserAgentParameter extends StringParameter {
        UserAgentParameter() {
            super("ua", DebugProperties.DEBUG_UA);
        }

        protected String getDerivedValue(ParameterData parameterData) {
            return MobileAdsInfoStore.getInstance().getDeviceInfo().getUserAgentString();
        }
    }

    private static class VideoAdsEnabledChecker {
        private final ParameterData parameterData;

        public VideoAdsEnabledChecker(ParameterData parameterData) {
            this.parameterData = parameterData;
        }

        public boolean isVideoAdsEnabled() {
            if (!this.parameterData.loiSlot.getAdTargetingOptions().isVideoEnabledSettable()) {
                return false;
            }
            if (this.parameterData.advancedOptions.containsKey("enableVideoAds")) {
                String str = (String) this.parameterData.advancedOptions.remove("enableVideoAds");
                this.parameterData.temporaryOptions.put("enableVideoAds", str);
                return Boolean.parseBoolean(str);
            } else if (this.parameterData.temporaryOptions.containsKey("enableVideoAds")) {
                return Boolean.parseBoolean((String) this.parameterData.temporaryOptions.get("enableVideoAds"));
            } else {
                return this.parameterData.loiSlot.getAdTargetingOptions().isVideoAdsEnabled();
            }
        }
    }

    static class VideoOptionsParameter extends JSONObjectParameter {
        private static final int MAXIMUM_DURATION_DEFAULT = 30000;
        private static final int MINIMUM_DURATION_DEFAULT = 0;

        public VideoOptionsParameter() {
            super(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, DebugProperties.DEBUG_VIDEO_OPTIONS);
        }

        protected JSONObject getDerivedValue(ParameterData parameterData) {
            int i = 0;
            if (!new VideoAdsEnabledChecker(parameterData).isVideoAdsEnabled()) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            if (parameterData.advancedOptions.containsKey("minVideoAdDuration")) {
                i = new IntegerParser().setDefaultValue(0).setParseErrorLogTag(AAXParameter.LOGTAG).setParseErrorLogMessage("The minVideoAdDuration advanced option could not be parsed properly.").parse((String) parameterData.advancedOptions.remove("minVideoAdDuration"));
            }
            JSONUtils.put(jSONObject, "minAdDuration", i);
            if (parameterData.advancedOptions.containsKey("maxVideoAdDuration")) {
                i = new IntegerParser().setDefaultValue(30000).setParseErrorLogTag(AAXParameter.LOGTAG).setParseErrorLogMessage("The maxVideoAdDuration advanced option could not be parsed properly.").parse((String) parameterData.advancedOptions.remove("maxVideoAdDuration"));
            } else {
                i = 30000;
            }
            JSONUtils.put(jSONObject, "maxAdDuration", i);
            return jSONObject;
        }
    }

    protected abstract T getFromDebugProperties();

    protected abstract T parseFromString(String str);

    AAXParameter(String str, String str2) {
        this.name = str;
        this.debugName = str2;
    }

    String getName() {
        return this.name;
    }

    protected String getDebugName() {
        return this.debugName;
    }

    protected boolean hasDebugPropertiesValue() {
        return DebugProperties.getInstance().containsDebugProperty(this.debugName);
    }

    T getValueDoNotRemove(ParameterData parameterData) {
        return getValueHelper(parameterData, false);
    }

    T getValue(ParameterData parameterData) {
        return getValueHelper(parameterData, true);
    }

    private T getValueHelper(ParameterData parameterData, boolean z) {
        Object fromDebugProperties;
        Object parseFromString;
        T applyPostParameterProcessing;
        if (hasDebugPropertiesValue()) {
            fromDebugProperties = getFromDebugProperties();
        } else {
            fromDebugProperties = null;
        }
        if (parameterData.advancedOptions != null) {
            String str;
            if (z) {
                str = (String) parameterData.advancedOptions.remove(this.name);
            } else {
                str = (String) parameterData.advancedOptions.get(this.name);
            }
            if (fromDebugProperties == null && !StringUtils.isNullOrEmpty(str)) {
                parseFromString = parseFromString(str);
                if (parseFromString == null) {
                    parseFromString = getDerivedValue(parameterData);
                }
                applyPostParameterProcessing = applyPostParameterProcessing(parseFromString, parameterData);
                if (!(applyPostParameterProcessing instanceof String) && StringUtils.isNullOrWhiteSpace((String) applyPostParameterProcessing)) {
                    return null;
                }
            }
        }
        parseFromString = fromDebugProperties;
        if (parseFromString == null) {
            parseFromString = getDerivedValue(parameterData);
        }
        applyPostParameterProcessing = applyPostParameterProcessing(parseFromString, parameterData);
        return !(applyPostParameterProcessing instanceof String) ? applyPostParameterProcessing : applyPostParameterProcessing;
    }

    protected T getDerivedValue(ParameterData parameterData) {
        return null;
    }

    protected T applyPostParameterProcessing(T t, ParameterData parameterData) {
        return t;
    }
}
