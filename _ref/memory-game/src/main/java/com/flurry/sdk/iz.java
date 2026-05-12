package com.flurry.sdk;

import android.text.TextUtils;
import bolts.MeasurementEvent;
import com.amazonaws.services.s3.internal.Constants;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class iz implements lg<hz> {
    private static final String a = iz.class.getSimpleName();

    public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
        return b(inputStream);
    }

    private static hz b(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        String str = new String(ly.a(inputStream));
        km.a(5, a, "Proton response string: " + str);
        hz hzVar = new hz();
        try {
            JSONObject jSONObject = new JSONObject(str);
            hzVar.a = jSONObject.optLong("issued_at", -1);
            hzVar.b = jSONObject.optLong("refresh_ttl", 3600);
            hzVar.c = jSONObject.optLong("expiration_ttl", 86400);
            JSONObject optJSONObject = jSONObject.optJSONObject("global_settings");
            hzVar.d = new ig();
            if (optJSONObject != null) {
                hzVar.d.a = a(optJSONObject.optString("log_level"));
            }
            optJSONObject = jSONObject.optJSONObject("pulse");
            hx hxVar = new hx();
            if (optJSONObject != null) {
                a(hxVar, optJSONObject.optJSONArray("callbacks"));
                hxVar.b = optJSONObject.optInt("max_callback_retries", 3);
                hxVar.c = optJSONObject.optInt("max_callback_attempts_per_report", 15);
                hxVar.d = optJSONObject.optInt("max_report_delay_seconds", SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_DEFAULT);
                hxVar.e = optJSONObject.optString("agent_report_url", "");
            }
            hzVar.e = hxVar;
            optJSONObject = jSONObject.optJSONObject(SettingsJsonConstants.ANALYTICS_KEY);
            hzVar.f = new ij();
            if (optJSONObject == null) {
                return hzVar;
            }
            hzVar.f.b = optJSONObject.optBoolean("analytics_enabled", true);
            hzVar.f.a = optJSONObject.optInt("max_session_properties", 10);
            return hzVar;
        } catch (Throwable e) {
            throw new IOException("Exception while deserialize: ", e);
        }
    }

    private static ih a(String str) {
        ih ihVar = ih.OFF;
        try {
            if (!TextUtils.isEmpty(str)) {
                return (ih) Enum.valueOf(ih.class, str);
            }
        } catch (Exception e) {
        }
        return ihVar;
    }

    private static void a(hx hxVar, JSONArray jSONArray) throws JSONException {
        if (jSONArray != null) {
            List arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    hw hwVar = new hw();
                    hwVar.b = optJSONObject.optString("partner", "");
                    a(hwVar, optJSONObject.optJSONArray("events"));
                    hwVar.d = b(optJSONObject.optString("method"));
                    hwVar.e = optJSONObject.optString("uri_template", "");
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("body_template");
                    if (optJSONObject2 != null) {
                        String optString = optJSONObject2.optString("string", Constants.NULL_VERSION_ID);
                        if (!optString.equals(Constants.NULL_VERSION_ID)) {
                            hwVar.f = optString;
                        }
                    }
                    hwVar.g = optJSONObject.optInt("max_redirects", 5);
                    hwVar.h = optJSONObject.optInt("connect_timeout", 20);
                    hwVar.i = optJSONObject.optInt("request_timeout", 20);
                    hwVar.a = optJSONObject.optLong("callback_id", -1);
                    optJSONObject = optJSONObject.optJSONObject("headers");
                    if (optJSONObject != null) {
                        optJSONObject = optJSONObject.optJSONObject("map");
                        if (optJSONObject != null) {
                            hwVar.j = lz.a(optJSONObject);
                        }
                    }
                    arrayList.add(hwVar);
                }
            }
            hxVar.a = arrayList;
        }
    }

    private static void a(hw hwVar, JSONArray jSONArray) {
        if (jSONArray != null) {
            List list = null;
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    if (optJSONObject.has("string")) {
                        if (list == null) {
                            list = new ArrayList();
                        }
                        ic icVar = new ic();
                        icVar.a = optJSONObject.optString("string", "");
                        list.add(icVar);
                    } else if (optJSONObject.has("com.flurry.proton.generated.avro.v2.EventParameterCallbackTrigger")) {
                        if (list == null) {
                            list = new ArrayList();
                        }
                        optJSONObject = optJSONObject.optJSONObject("com.flurry.proton.generated.avro.v2.EventParameterCallbackTrigger");
                        if (optJSONObject != null) {
                            String[] strArr;
                            id idVar = new id();
                            idVar.a = optJSONObject.optString(MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY, "");
                            idVar.c = optJSONObject.optString("event_parameter_name", "");
                            JSONArray optJSONArray = optJSONObject.optJSONArray("event_parameter_values");
                            if (optJSONArray != null) {
                                String[] strArr2 = new String[optJSONArray.length()];
                                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                    strArr2[i2] = optJSONArray.optString(i2, "");
                                }
                                strArr = strArr2;
                            } else {
                                strArr = new String[0];
                            }
                            idVar.d = strArr;
                            list.add(idVar);
                        }
                    }
                }
            }
            hwVar.c = list;
        }
    }

    private static iw b(String str) {
        iw iwVar = iw.GET;
        try {
            if (!TextUtils.isEmpty(str)) {
                return (iw) Enum.valueOf(iw.class, str);
            }
        } catch (Exception e) {
        }
        return iwVar;
    }

    public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
        throw new IOException("Serialize not supported for response");
    }
}
