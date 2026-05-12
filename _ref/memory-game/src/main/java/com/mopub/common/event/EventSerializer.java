package com.mopub.common.event;

import android.support.annotation.NonNull;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.mopub.common.ClientMetadata$MoPubNetworkType;
import com.mopub.common.Preconditions;
import com.mopub.common.event.BaseEvent.AppPlatform;
import com.mopub.common.event.BaseEvent.SdkProduct;
import com.mopub.common.logging.MoPubLog;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class EventSerializer {
    @NonNull
    public JSONArray serializeAsJson(@NonNull List<BaseEvent> list) {
        Preconditions.checkNotNull(list);
        JSONArray jSONArray = new JSONArray();
        for (BaseEvent baseEvent : list) {
            try {
                jSONArray.put(serializeAsJson(baseEvent));
            } catch (Throwable e) {
                MoPubLog.d("Failed to serialize event \"" + baseEvent.getName() + "\" to JSON: ", e);
            }
        }
        return jSONArray;
    }

    @NonNull
    public JSONObject serializeAsJson(@NonNull BaseEvent baseEvent) {
        Object obj;
        Object obj2 = null;
        Preconditions.checkNotNull(baseEvent);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("_category_", baseEvent.getScribeCategory().getCategory());
        jSONObject.put("ts", baseEvent.getTimestampUtcMs());
        jSONObject.put("name", baseEvent.getName().getName());
        jSONObject.put("name_category", baseEvent.getCategory().getCategory());
        SdkProduct sdkProduct = baseEvent.getSdkProduct();
        String str = "sdk_product";
        if (sdkProduct == null) {
            obj = null;
        } else {
            obj = Integer.valueOf(sdkProduct.getType());
        }
        jSONObject.put(str, obj);
        jSONObject.put("sdk_version", baseEvent.getSdkVersion());
        jSONObject.put("ad_unit_id", baseEvent.getAdUnitId());
        jSONObject.put("ad_creative_id", baseEvent.getAdCreativeId());
        jSONObject.put("ad_type", baseEvent.getAdType());
        jSONObject.put("ad_network_type", baseEvent.getAdNetworkType());
        jSONObject.put("ad_width_px", baseEvent.getAdWidthPx());
        jSONObject.put("ad_height_px", baseEvent.getAdHeightPx());
        jSONObject.put("dsp_creative_id", baseEvent.getDspCreativeId());
        AppPlatform appPlatform = baseEvent.getAppPlatform();
        str = "app_platform";
        if (appPlatform == null) {
            obj = null;
        } else {
            obj = Integer.valueOf(appPlatform.getType());
        }
        jSONObject.put(str, obj);
        jSONObject.put(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, baseEvent.getAppName());
        jSONObject.put("app_package_name", baseEvent.getAppPackageName());
        jSONObject.put("app_version", baseEvent.getAppVersion());
        jSONObject.put("client_advertising_id", baseEvent.getObfuscatedClientAdvertisingId());
        jSONObject.put("client_do_not_track", baseEvent.getClientDoNotTrack());
        jSONObject.put("device_manufacturer", baseEvent.getDeviceManufacturer());
        jSONObject.put("device_model", baseEvent.getDeviceModel());
        jSONObject.put("device_product", baseEvent.getDeviceProduct());
        jSONObject.put("device_os_version", baseEvent.getDeviceOsVersion());
        jSONObject.put("device_screen_width_px", baseEvent.getDeviceScreenWidthDip());
        jSONObject.put("device_screen_height_px", baseEvent.getDeviceScreenHeightDip());
        jSONObject.put("geo_lat", baseEvent.getGeoLat());
        jSONObject.put("geo_lon", baseEvent.getGeoLon());
        jSONObject.put("geo_accuracy_radius_meters", baseEvent.getGeoAccuracy());
        jSONObject.put("perf_duration_ms", baseEvent.getPerformanceDurationMs());
        ClientMetadata$MoPubNetworkType networkType = baseEvent.getNetworkType();
        str = "network_type";
        if (networkType != null) {
            obj2 = Integer.valueOf(networkType.getId());
        }
        jSONObject.put(str, obj2);
        jSONObject.put("network_operator_code", baseEvent.getNetworkOperatorCode());
        jSONObject.put("network_operator_name", baseEvent.getNetworkOperatorName());
        jSONObject.put("network_iso_country_code", baseEvent.getNetworkIsoCountryCode());
        jSONObject.put("network_sim_code", baseEvent.getNetworkSimCode());
        jSONObject.put("network_sim_operator_name", baseEvent.getNetworkSimOperatorName());
        jSONObject.put("network_sim_iso_country_code", baseEvent.getNetworkSimIsoCountryCode());
        jSONObject.put("req_id", baseEvent.getRequestId());
        jSONObject.put("req_status_code", baseEvent.getRequestStatusCode());
        jSONObject.put("req_uri", baseEvent.getRequestUri());
        jSONObject.put("req_retries", baseEvent.getRequestRetries());
        jSONObject.put("timestamp_client", baseEvent.getTimestampUtcMs());
        if (baseEvent instanceof ErrorEvent) {
            ErrorEvent errorEvent = (ErrorEvent) baseEvent;
            jSONObject.put("error_exception_class_name", errorEvent.getErrorExceptionClassName());
            jSONObject.put(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE, errorEvent.getErrorMessage());
            jSONObject.put("error_stack_trace", errorEvent.getErrorStackTrace());
            jSONObject.put("error_file_name", errorEvent.getErrorFileName());
            jSONObject.put("error_class_name", errorEvent.getErrorClassName());
            jSONObject.put("error_method_name", errorEvent.getErrorMethodName());
            jSONObject.put("error_line_number", errorEvent.getErrorLineNumber());
        }
        return jSONObject;
    }
}
