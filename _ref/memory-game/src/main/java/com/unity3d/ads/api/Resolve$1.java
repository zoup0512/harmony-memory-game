package com.unity3d.ads.api;

import com.unity3d.ads.request.IResolveHostListener;
import com.unity3d.ads.request.ResolveHostError;
import com.unity3d.ads.request.ResolveHostEvent;
import com.unity3d.ads.webview.WebViewApp;
import com.unity3d.ads.webview.WebViewEventCategory;

class Resolve$1 implements IResolveHostListener {
    final /* synthetic */ String val$id;

    Resolve$1(String str) {
        this.val$id = str;
    }

    public void onResolve(String str, String str2) {
        if (WebViewApp.getCurrentApp() != null) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.RESOLVE, ResolveHostEvent.COMPLETE, this.val$id, str, str2);
        }
    }

    public void onFailed(String str, ResolveHostError resolveHostError, String str2) {
        if (WebViewApp.getCurrentApp() != null) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.RESOLVE, ResolveHostEvent.FAILED, this.val$id, str, resolveHostError.name(), str2);
        }
    }
}
