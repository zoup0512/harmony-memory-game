package com.unity3d.ads.api;

import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.request.IWebRequestListener;
import com.unity3d.ads.request.WebRequestEvent;
import com.unity3d.ads.webview.WebViewApp;
import com.unity3d.ads.webview.WebViewEventCategory;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

class Request$1 implements IWebRequestListener {
    final /* synthetic */ String val$id;

    Request$1(String str) {
        this.val$id = str;
    }

    public void onComplete(String str, String str2, int i, Map<String, List<String>> map) {
        try {
            JSONArray responseHeadersMap = Request.getResponseHeadersMap(map);
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.COMPLETE, this.val$id, str, str2, Integer.valueOf(i), responseHeadersMap);
        } catch (Exception e) {
            DeviceLog.exception("Error parsing response headers", e);
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.FAILED, this.val$id, str, "Error parsing response headers");
        }
    }

    public void onFailed(String str, String str2) {
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.FAILED, this.val$id, str, str2);
    }
}
