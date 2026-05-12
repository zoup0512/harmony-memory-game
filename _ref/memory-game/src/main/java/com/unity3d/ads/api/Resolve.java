package com.unity3d.ads.api;

import com.unity3d.ads.request.ResolveHostError;
import com.unity3d.ads.request.WebRequestThread;
import com.unity3d.ads.webview.bridge.WebViewCallback;
import com.unity3d.ads.webview.bridge.WebViewExposed;

public class Resolve {
    @WebViewExposed
    public static void resolve(String str, String str2, WebViewCallback webViewCallback) {
        if (WebRequestThread.resolve(str2, new 1(str))) {
            webViewCallback.invoke(new Object[]{str});
            return;
        }
        webViewCallback.error(ResolveHostError.INVALID_HOST, new Object[]{str});
    }
}
