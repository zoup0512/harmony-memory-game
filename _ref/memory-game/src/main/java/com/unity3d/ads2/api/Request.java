package com.unity3d.ads2.api;

import com.unity3d.ads2.log.DeviceLog;
import com.unity3d.ads2.request.WebRequest.RequestType;
import com.unity3d.ads2.request.WebRequestError;
import com.unity3d.ads2.request.WebRequestThread;
import com.unity3d.ads2.webview.bridge.WebViewCallback;
import com.unity3d.ads2.webview.bridge.WebViewExposed;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

public class Request {
    @WebViewExposed
    public static void get(String str, String str2, JSONArray jSONArray, Integer num, Integer num2, WebViewCallback webViewCallback) {
        if (jSONArray != null && jSONArray.length() == 0) {
            jSONArray = null;
        }
        try {
            WebRequestThread.request(str2, RequestType.GET, getHeadersMap(jSONArray), null, num, num2, new 1(str));
            webViewCallback.invoke(new Object[]{str});
        } catch (Exception e) {
            DeviceLog.exception("Error mapping headers for the request", e);
            webViewCallback.error(WebRequestError.MAPPING_HEADERS_FAILED, new Object[]{str});
        }
    }

    @WebViewExposed
    public static void post(String str, String str2, String str3, JSONArray jSONArray, Integer num, Integer num2, WebViewCallback webViewCallback) {
        String str4;
        if (str3 == null || str3.length() != 0) {
            str4 = str3;
        } else {
            str4 = null;
        }
        if (jSONArray != null && jSONArray.length() == 0) {
            jSONArray = null;
        }
        try {
            WebRequestThread.request(str2, RequestType.POST, getHeadersMap(jSONArray), str4, num, num2, new 2(str));
            webViewCallback.invoke(new Object[]{str});
        } catch (Exception e) {
            DeviceLog.exception("Error mapping headers for the request", e);
            webViewCallback.error(WebRequestError.MAPPING_HEADERS_FAILED, new Object[]{str});
        }
    }

    @WebViewExposed
    public static void head(String str, String str2, JSONArray jSONArray, Integer num, Integer num2, WebViewCallback webViewCallback) {
        if (jSONArray != null && jSONArray.length() == 0) {
            jSONArray = null;
        }
        try {
            WebRequestThread.request(str2, RequestType.HEAD, getHeadersMap(jSONArray), num, num2, new 3(str));
            webViewCallback.invoke(new Object[]{str});
        } catch (Exception e) {
            DeviceLog.exception("Error mapping headers for the request", e);
            webViewCallback.error(WebRequestError.MAPPING_HEADERS_FAILED, new Object[]{str});
        }
    }

    public static JSONArray getResponseHeadersMap(Map<String, List<String>> map) {
        JSONArray jSONArray = new JSONArray();
        if (map != null && map.size() > 0) {
            for (String str : map.keySet()) {
                Object obj = null;
                for (String str2 : (List) map.get(str)) {
                    JSONArray jSONArray2 = new JSONArray();
                    jSONArray2.put(str);
                    jSONArray2.put(str2);
                    JSONArray jSONArray3 = jSONArray2;
                }
                jSONArray.put(obj);
            }
        }
        return jSONArray;
    }

    private static HashMap<String, List<String>> getHeadersMap(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        HashMap<String, List<String>> hashMap = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONArray jSONArray2 = (JSONArray) jSONArray.get(i);
            List list = (List) hashMap.get(jSONArray2.getString(0));
            if (list == null) {
                list = new ArrayList();
            }
            list.add(jSONArray2.getString(1));
            hashMap.put(jSONArray2.getString(0), list);
        }
        return hashMap;
    }
}
