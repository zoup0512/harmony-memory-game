package org.nexage.sourcekit.mraid;

import android.webkit.WebView;

public interface MRAIDNativeFeatureListener {
    void mraidNativeFeatureCallTel(String str);

    void mraidNativeFeatureCreateCalendarEvent(String str);

    void mraidNativeFeatureOpenBrowser(String str, WebView webView);

    void mraidNativeFeaturePlayVideo(String str);

    void mraidNativeFeatureSendSms(String str);

    void mraidNativeFeatureStorePicture(String str);
}
