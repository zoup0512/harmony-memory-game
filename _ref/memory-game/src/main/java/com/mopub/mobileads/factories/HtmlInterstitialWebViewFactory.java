package com.mopub.mobileads.factories;

import android.content.Context;
import com.mopub.common.AdReport;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.HtmlInterstitialWebView;

public class HtmlInterstitialWebViewFactory {
    protected static HtmlInterstitialWebViewFactory instance = new HtmlInterstitialWebViewFactory();

    public static HtmlInterstitialWebView create(Context context, AdReport adReport, CustomEventInterstitialListener customEventInterstitialListener, boolean z, String str, String str2) {
        return instance.internalCreate(context, adReport, customEventInterstitialListener, z, str, str2);
    }

    public HtmlInterstitialWebView internalCreate(Context context, AdReport adReport, CustomEventInterstitialListener customEventInterstitialListener, boolean z, String str, String str2) {
        HtmlInterstitialWebView htmlInterstitialWebView = new HtmlInterstitialWebView(context, adReport);
        htmlInterstitialWebView.init(customEventInterstitialListener, z, str, str2, adReport.getDspCreativeId());
        return htmlInterstitialWebView;
    }

    @Deprecated
    public static void setInstance(HtmlInterstitialWebViewFactory htmlInterstitialWebViewFactory) {
        instance = htmlInterstitialWebViewFactory;
    }
}
