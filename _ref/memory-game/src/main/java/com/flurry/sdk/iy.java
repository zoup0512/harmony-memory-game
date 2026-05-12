package com.flurry.sdk;

import com.amazon.device.ads.AdWebViewClient;
import com.applovin.sdk.AppLovinEventTypes;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class iy implements lg<hy> {
    private static final String a = iy.class.getSimpleName();

    public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
        hy hyVar = (hy) obj;
        if (outputStream != null && hyVar != null) {
            DataOutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                final /* synthetic */ iy a;

                public final void close() {
                }
            };
            JSONObject jSONObject = new JSONObject();
            try {
                Object obj2;
                a(jSONObject, "project_key", hyVar.a);
                a(jSONObject, "bundle_id", hyVar.b);
                a(jSONObject, "app_version", hyVar.c);
                jSONObject.put("sdk_version", hyVar.d);
                jSONObject.put("platform", hyVar.e);
                a(jSONObject, "platform_version", hyVar.f);
                jSONObject.put("limit_ad_tracking", hyVar.g);
                if (hyVar.h == null || hyVar.h.a == null) {
                    obj2 = null;
                } else {
                    obj2 = new JSONObject();
                    JSONObject jSONObject2 = new JSONObject();
                    a(jSONObject2, "model", hyVar.h.a.a);
                    a(jSONObject2, "brand", hyVar.h.a.b);
                    a(jSONObject2, "id", hyVar.h.a.c);
                    a(jSONObject2, "device", hyVar.h.a.d);
                    a(jSONObject2, AppLovinEventTypes.USER_VIEWED_PRODUCT, hyVar.h.a.e);
                    a(jSONObject2, "version_release", hyVar.h.a.f);
                    obj2.put("com.flurry.proton.generated.avro.v2.AndroidTags", jSONObject2);
                }
                if (obj2 != null) {
                    jSONObject.put("device_tags", obj2);
                } else {
                    jSONObject.put("device_tags", JSONObject.NULL);
                }
                JSONArray jSONArray = new JSONArray();
                for (ia iaVar : hyVar.i) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("type", iaVar.a);
                    a(jSONObject3, "id", iaVar.b);
                    jSONArray.put(jSONObject3);
                }
                jSONObject.put("device_ids", jSONArray);
                if (hyVar.j == null || hyVar.j.a == null) {
                    obj2 = null;
                } else {
                    obj2 = new JSONObject();
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.putOpt("latitude", Double.valueOf(hyVar.j.a.a));
                    jSONObject4.putOpt("longitude", Double.valueOf(hyVar.j.a.b));
                    jSONObject4.putOpt("accuracy", Float.valueOf(hyVar.j.a.c));
                    obj2.put("com.flurry.proton.generated.avro.v2.Geolocation", jSONObject4);
                }
                if (obj2 != null) {
                    jSONObject.put(AdWebViewClient.GEO, obj2);
                } else {
                    jSONObject.put(AdWebViewClient.GEO, JSONObject.NULL);
                }
                JSONObject jSONObject5 = new JSONObject();
                if (hyVar.k != null) {
                    a(jSONObject5, "string", hyVar.k.a);
                    jSONObject.put("publisher_user_id", jSONObject5);
                } else {
                    jSONObject.put("publisher_user_id", JSONObject.NULL);
                }
                km.a(5, a, "Proton Request String: " + jSONObject.toString());
                anonymousClass1.write(jSONObject.toString().getBytes());
                anonymousClass1.flush();
                anonymousClass1.close();
            } catch (Throwable e) {
                throw new IOException("Invalid Json", e);
            } catch (Throwable th) {
                anonymousClass1.close();
            }
        }
    }

    private static void a(JSONObject jSONObject, String str, String str2) throws IOException, JSONException {
        if (str2 != null) {
            jSONObject.put(str, str2);
        } else {
            jSONObject.put(str, JSONObject.NULL);
        }
    }

    public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
        throw new IOException("Deserialize not supported for request");
    }
}
