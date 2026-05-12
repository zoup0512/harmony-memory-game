package com.unity3d.ads2.api;

import com.unity3d.ads2.UnityAds;
import com.unity3d.ads2.misc.Utilities;
import com.unity3d.ads2.webview.bridge.WebViewCallback;
import com.unity3d.ads2.webview.bridge.WebViewExposed;

public class Listener {
    @WebViewExposed
    public static void sendReadyEvent(String str, WebViewCallback webViewCallback) {
        if (UnityAds.getListener() != null) {
            Utilities.runOnUiThread(new 1(str));
        }
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendStartEvent(String str, WebViewCallback webViewCallback) {
        if (UnityAds.getListener() != null) {
            Utilities.runOnUiThread(new 2(str));
        }
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendFinishEvent(String str, String str2, WebViewCallback webViewCallback) {
        if (UnityAds.getListener() != null) {
            Utilities.runOnUiThread(new 3(str, str2));
        }
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendErrorEvent(String str, String str2, WebViewCallback webViewCallback) {
        if (UnityAds.getListener() != null) {
            Utilities.runOnUiThread(new 4(str, str2));
        }
        webViewCallback.invoke(new Object[0]);
    }
}
