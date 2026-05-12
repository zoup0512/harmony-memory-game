package com.amazon.device.ads;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import java.util.HashMap;
import org.json.JSONObject;

abstract class SISDeviceRequest extends SISRequest {
    private AdvertisingIdentifier advertisingIdentifier;
    private Info advertisingIdentifierInfo = this.advertisingIdentifier.getAdvertisingIdentifierInfo();

    SISDeviceRequest(MobileAdsLoggerFactory mobileAdsLoggerFactory, String str, MetricType metricType, String str2, AdvertisingIdentifier advertisingIdentifier, MobileAdsInfoStore mobileAdsInfoStore, Configuration configuration) {
        super(mobileAdsLoggerFactory, str, metricType, str2, mobileAdsInfoStore, configuration);
        this.advertisingIdentifier = advertisingIdentifier;
    }

    public QueryStringParameters getQueryParameters() {
        QueryStringParameters queryParameters = super.getQueryParameters();
        DeviceInfo deviceInfo = this.mobileAdsInfoStore.getDeviceInfo();
        queryParameters.putUnencoded("ua", deviceInfo.getUserAgentString());
        queryParameters.putUnencoded("dinfo", deviceInfo.getDInfoProperty().toString());
        if (this.advertisingIdentifierInfo.hasAdvertisingIdentifier()) {
            queryParameters.putUrlEncoded("idfa", this.advertisingIdentifierInfo.getAdvertisingIdentifier());
            queryParameters.putUrlEncoded("oo", convertOptOutBooleanToStringInt(this.advertisingIdentifierInfo.isLimitAdTrackingEnabled()));
        } else {
            queryParameters.putUrlEncoded("sha1_mac", deviceInfo.getMacSha1());
            queryParameters.putUrlEncoded("sha1_serial", deviceInfo.getSerialSha1());
            queryParameters.putUrlEncoded("sha1_udid", deviceInfo.getUdidSha1());
            queryParameters.putUrlEncodedIfTrue("badMac", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE, deviceInfo.isMacBad());
            queryParameters.putUrlEncodedIfTrue("badSerial", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE, deviceInfo.isSerialBad());
            queryParameters.putUrlEncodedIfTrue("badUdid", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE, deviceInfo.isUdidBad());
        }
        String andClearTransition = this.advertisingIdentifier.getAndClearTransition();
        queryParameters.putUrlEncodedIfTrue("aidts", andClearTransition, andClearTransition != null);
        return queryParameters;
    }

    private static String convertOptOutBooleanToStringInt(boolean z) {
        return z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO;
    }

    protected Info getAdvertisingIdentifierInfo() {
        return this.advertisingIdentifierInfo;
    }

    public HashMap<String, String> getPostParameters() {
        return null;
    }

    public void onResponseReceived(JSONObject jSONObject) {
        String stringFromJSON = JSONUtils.getStringFromJSON(jSONObject, "adId", "");
        if (stringFromJSON.length() > 0) {
            this.mobileAdsInfoStore.getRegistrationInfo().putAdId(stringFromJSON, getAdvertisingIdentifierInfo());
        }
    }
}
