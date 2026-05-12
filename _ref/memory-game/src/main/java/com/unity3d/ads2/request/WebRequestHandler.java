package com.unity3d.ads2.request;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amazonaws.services.s3.internal.Constants;
import com.unity3d.ads2.log.DeviceLog;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WebRequestHandler extends Handler {
    public void handleMessage(Message message) {
        Bundle data = message.getData();
        String string = data.getString("url");
        data.remove("url");
        String string2 = data.getString("type");
        data.remove("type");
        String string3 = data.getString("body");
        data.remove("body");
        WebRequestResultReceiver webRequestResultReceiver = (WebRequestResultReceiver) data.getParcelable("receiver");
        data.remove("receiver");
        int i = data.getInt("connectTimeout");
        data.remove("connectTimeout");
        int i2 = data.getInt("readTimeout");
        data.remove("readTimeout");
        HashMap hashMap = null;
        if (data.size() > 0) {
            DeviceLog.debug("There are headers left in data, reading them");
            hashMap = new HashMap();
            for (String str : data.keySet()) {
                hashMap.put(str, Arrays.asList(data.getStringArray(str)));
            }
        }
        if (message.what == 1) {
            DeviceLog.debug("Handling request message: " + string + " type=" + string2);
            try {
                makeRequest(string, string2, hashMap, string3, i, i2, webRequestResultReceiver);
                return;
            } catch (Exception e) {
                DeviceLog.exception("Malformed URL", e);
                if (webRequestResultReceiver != null) {
                    webRequestResultReceiver.send(2, getBundleForFailResult(string, "Malformed URL", string2, string3));
                    return;
                }
                return;
            }
        }
        DeviceLog.error("No implementation for message: " + message.what);
        if (webRequestResultReceiver != null) {
            webRequestResultReceiver.send(2, getBundleForFailResult(string, "Invalid Thread Message", string2, string3));
        }
    }

    private void makeRequest(String str, String str2, HashMap<String, List<String>> hashMap, String str3, int i, int i2, WebRequestResultReceiver webRequestResultReceiver) {
        WebRequest webRequest = new WebRequest(str, str2, hashMap, i, i2);
        if (str3 != null) {
            webRequest.setBody(str3);
        }
        try {
            String makeRequest = webRequest.makeRequest();
            Bundle bundle = new Bundle();
            bundle.putString("response", makeRequest);
            bundle.putString("url", str);
            bundle.putInt("responseCode", webRequest.getResponseCode());
            for (String makeRequest2 : webRequest.getResponseHeaders().keySet()) {
                if (!(makeRequest2 == null || makeRequest2.contentEquals(Constants.NULL_VERSION_ID))) {
                    String[] strArr = new String[((List) webRequest.getResponseHeaders().get(makeRequest2)).size()];
                    for (int i3 = 0; i3 < ((List) webRequest.getResponseHeaders().get(makeRequest2)).size(); i3++) {
                        strArr[i3] = (String) ((List) webRequest.getResponseHeaders().get(makeRequest2)).get(i3);
                    }
                    bundle.putStringArray(makeRequest2, strArr);
                }
            }
            webRequestResultReceiver.send(1, bundle);
        } catch (Exception e) {
            DeviceLog.exception("Error completing request", e);
            webRequestResultReceiver.send(2, getBundleForFailResult(str, e.getMessage(), str2, str3));
        }
    }

    private Bundle getBundleForFailResult(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        bundle.putString("error", str2);
        bundle.putString("type", str3);
        bundle.putString("body", str4);
        return bundle;
    }
}
