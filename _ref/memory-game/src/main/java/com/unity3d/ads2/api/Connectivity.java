package com.unity3d.ads2.api;

import com.unity3d.ads2.connectivity.ConnectivityMonitor;
import com.unity3d.ads2.webview.bridge.WebViewCallback;
import com.unity3d.ads2.webview.bridge.WebViewExposed;

public class Connectivity {
    @WebViewExposed
    public static void setConnectionMonitoring(Boolean bool, WebViewCallback webViewCallback) {
        ConnectivityMonitor.setConnectionMonitoring(bool.booleanValue());
        webViewCallback.invoke(new Object[0]);
    }
}
