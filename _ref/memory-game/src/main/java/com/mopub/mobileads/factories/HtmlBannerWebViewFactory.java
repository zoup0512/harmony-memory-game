package com.mopub.mobileads.factories;

import android.content.Context;
import com.mopub.common.AdReport;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import com.mopub.mobileads.HtmlBannerWebView;

public class HtmlBannerWebViewFactory {
    protected static HtmlBannerWebViewFactory instance = new HtmlBannerWebViewFactory();

    public static HtmlBannerWebView create(Context context, AdReport adReport, CustomEventBannerListener customEventBannerListener, boolean z, String str, String str2) {
        return instance.internalCreate(context, adReport, customEventBannerListener, z, str, str2);
    }

    public HtmlBannerWebView internalCreate(Context context, AdReport adReport, CustomEventBannerListener customEventBannerListener, boolean z, String str, String str2) {
        HtmlBannerWebView htmlBannerWebView = new HtmlBannerWebView(context, adReport);
        htmlBannerWebView.init(customEventBannerListener, z, str, str2, adReport.getDspCreativeId());
        return htmlBannerWebView;
    }

    @Deprecated
    public static void setInstance(HtmlBannerWebViewFactory htmlBannerWebViewFactory) {
        instance = htmlBannerWebViewFactory;
    }
}
