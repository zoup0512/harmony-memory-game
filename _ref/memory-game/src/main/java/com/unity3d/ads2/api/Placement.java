package com.unity3d.ads2.api;

import com.unity3d.ads2.webview.bridge.WebViewCallback;
import com.unity3d.ads2.webview.bridge.WebViewExposed;

public class Placement {
    @WebViewExposed
    public static void setDefaultPlacement(String str, WebViewCallback webViewCallback) {
        com.unity3d.ads2.placement.Placement.setDefaultPlacement(str);
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void setPlacementState(String str, String str2, WebViewCallback webViewCallback) {
        com.unity3d.ads2.placement.Placement.setPlacementState(str, str2);
        webViewCallback.invoke(new Object[0]);
    }
}
