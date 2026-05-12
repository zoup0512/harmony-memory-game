package com.my.target.core.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.amazonaws.services.s3.internal.Constants;
import com.applovin.sdk.AppLovinTargetingData;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.my.target.SDKVersion;
import com.yalantis.ucrop.util.FileUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.security.Key;
import java.security.SignatureException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.UUID;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LoggerUtils */
public final class h {
    private static String a;
    private static final TimeZone b = TimeZone.getTimeZone("UTC");
    private static final DateFormat c;
    private static JSONObject d;

    static {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        c = simpleDateFormat;
        simpleDateFormat.setTimeZone(b);
    }

    public static void a(Map<String, String> map) {
        if (map != null) {
            try {
                d = new JSONObject();
                for (Entry entry : map.entrySet()) {
                    d.put((String) entry.getKey(), entry.getValue());
                }
                d.put("adman_ver", SDKVersion.VERSION);
                return;
            } catch (JSONException e) {
            }
        }
        d = null;
    }

    public static boolean a() {
        return d != null;
    }

    public static String a(String str, String str2, String str3, int i, String str4, Throwable th, String str5, String str6, g gVar) {
        return Base64.encodeToString(b(str, str2, str3, i, str4, th, str5, str6, gVar).getBytes(), 0);
    }

    private static String b(String str, String str2, String str3, int i, String str4, Throwable th, String str5, String str6, g gVar) {
        JSONObject jSONObject = new JSONObject();
        String replaceAll = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            jSONObject.put("event_id", replaceAll);
            byte[] bytes = str.getBytes();
            Checksum crc32 = new CRC32();
            crc32.update(bytes, 0, bytes.length);
            jSONObject.put("checksum", String.valueOf(crc32.getValue()));
            if (th == null) {
                jSONObject.put("culprit", str4);
            } else {
                jSONObject.put("culprit", a(th));
                jSONObject.put("sentry.interfaces.Exception", b(th));
                jSONObject.put("sentry.interfaces.Stacktrace", c(th));
            }
            jSONObject.put("timestamp", str2);
            jSONObject.put("message", str);
            jSONObject.put("project", gVar.f());
            jSONObject.put("level", i);
            jSONObject.put("logger", str3);
            jSONObject.put("server_name", "localhost");
            if (str6 != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("url", str6);
                jSONObject2.put("method", HttpRequest.METHOD_GET);
                jSONObject.put("sentry.interfaces.Http", jSONObject2);
            }
            if (d != null) {
                if (d.has("response_body")) {
                    d.remove("response_body");
                }
                if (!TextUtils.isEmpty(str5)) {
                    d.put("response_body", str5);
                }
                jSONObject.put("extra", d);
            }
        } catch (JSONException e) {
        }
        a = replaceAll;
        return jSONObject.toString();
    }

    private static String a(Throwable th) {
        String str = null;
        while (th != null) {
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace.length > 0) {
                StackTraceElement stackTraceElement = stackTrace[0];
                str = stackTraceElement.getClassName() + FileUtils.HIDDEN_PREFIX + stackTraceElement.getMethodName();
            }
            th = th.getCause();
        }
        return str;
    }

    private static JSONObject b(Throwable th) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", th.getClass().getSimpleName());
            jSONObject.put(Param.VALUE, th.getMessage());
            jSONObject.put("module", th.getClass().getPackage().getName());
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    private static JSONObject c(Throwable th) {
        JSONArray jSONArray = new JSONArray();
        while (th != null) {
            StackTraceElement[] stackTrace = th.getStackTrace();
            for (int i = 0; i < stackTrace.length; i++) {
                JSONObject jSONObject;
                if (i == 0) {
                    try {
                        jSONObject = new JSONObject();
                        Object obj = "Caused by: " + th.getClass().getName();
                        if (th.getMessage() != null) {
                            obj = obj + " (\"" + th.getMessage() + "\")";
                        }
                        jSONObject.put("filename", obj);
                        jSONObject.put("lineno", -1);
                        jSONArray.put(jSONObject);
                    } catch (JSONException e) {
                    }
                }
                StackTraceElement stackTraceElement = stackTrace[i];
                jSONObject = new JSONObject();
                jSONObject.put("filename", stackTraceElement.getClassName());
                jSONObject.put("function", stackTraceElement.getMethodName());
                jSONObject.put("lineno", stackTraceElement.getLineNumber());
                jSONArray.put(jSONObject);
            }
            th = th.getCause();
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("frames", jSONArray);
        } catch (JSONException e2) {
        }
        return jSONObject2;
    }

    public static String a(String str, long j, String str2) {
        return "Sentry sentry_version=2.0,sentry_signature=" + str + ",sentry_timestamp=" + j + ",sentry_key=" + str2 + ",sentry_client=Raven-myTarget";
    }

    private static String a(String str, String str2) throws SignatureException {
        try {
            Key secretKeySpec = new SecretKeySpec(str2.getBytes(), Constants.HMAC_SHA1_ALGORITHM);
            Mac instance = Mac.getInstance(Constants.HMAC_SHA1_ALGORITHM);
            instance.init(secretKeySpec);
            return a(instance.doFinal(str.getBytes()));
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
    }

    public static String a(long j) {
        return c.format(new Date(j));
    }

    public static String b(String str, long j, String str2) {
        String str3 = j + " " + str;
        String str4 = null;
        try {
            str4 = a(str3, str2);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return str4;
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', AppLovinTargetingData.GENDER_FEMALE};
        for (byte b : bArr) {
            stringBuilder.append(cArr[(b & 240) >> 4]);
            stringBuilder.append(cArr[b & 15]);
        }
        return stringBuilder.toString();
    }
}
