package com.amazon.device.ads;

import com.amazon.device.ads.Configuration.ConfigOption;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

abstract class SISRequest {
    private final MetricType callMetricType;
    private final Configuration configuration;
    private final String logTag;
    protected final MobileAdsLogger logger;
    protected MobileAdsInfoStore mobileAdsInfoStore;
    private final String path;

    enum SISDeviceRequestType {
        GENERATE_DID,
        UPDATE_DEVICE_INFO
    }

    static class SISRequestFactory {
        SISRequestFactory() {
        }

        public SISDeviceRequest createDeviceRequest(SISDeviceRequestType sISDeviceRequestType, AdvertisingIdentifier advertisingIdentifier) {
            switch (sISDeviceRequestType) {
                case GENERATE_DID:
                    return new SISGenerateDIDRequest(advertisingIdentifier);
                case UPDATE_DEVICE_INFO:
                    return new SISUpdateDeviceInfoRequest(advertisingIdentifier);
                default:
                    throw new IllegalArgumentException("SISRequestType " + sISDeviceRequestType + " is not a SISDeviceRequest");
            }
        }

        public SISRegisterEventRequest createRegisterEventRequest(Info info, JSONArray jSONArray) {
            return new SISRegisterEventRequest(info, jSONArray);
        }
    }

    abstract HashMap<String, String> getPostParameters();

    abstract void onResponseReceived(JSONObject jSONObject);

    SISRequest(MobileAdsLoggerFactory mobileAdsLoggerFactory, String str, MetricType metricType, String str2, MobileAdsInfoStore mobileAdsInfoStore, Configuration configuration) {
        this.logTag = str;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(this.logTag);
        this.callMetricType = metricType;
        this.path = str2;
        this.mobileAdsInfoStore = mobileAdsInfoStore;
        this.configuration = configuration;
    }

    MobileAdsLogger getLogger() {
        return this.logger;
    }

    String getLogTag() {
        return this.logTag;
    }

    MetricType getCallMetricType() {
        return this.callMetricType;
    }

    String getPath() {
        return this.path;
    }

    QueryStringParameters getQueryParameters() {
        QueryStringParameters queryStringParameters = new QueryStringParameters();
        queryStringParameters.putUrlEncoded("dt", this.mobileAdsInfoStore.getDeviceInfo().getDeviceType());
        queryStringParameters.putUrlEncoded(SettingsJsonConstants.APP_KEY, this.mobileAdsInfoStore.getRegistrationInfo().getAppName());
        queryStringParameters.putUrlEncoded("appId", this.mobileAdsInfoStore.getRegistrationInfo().getAppKey());
        queryStringParameters.putUrlEncoded("sdkVer", Version.getSDKVersion());
        queryStringParameters.putUrlEncoded("aud", this.configuration.getString(ConfigOption.SIS_DOMAIN));
        queryStringParameters.putUnencoded("pkg", this.mobileAdsInfoStore.getAppInfo().getPackageInfoJSONString());
        return queryStringParameters;
    }
}
