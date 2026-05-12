package com.amazon.device.ads;

import com.amazon.device.ads.ThreadUtils.ExecutionStyle;
import com.amazon.device.ads.ThreadUtils.ExecutionThread;
import com.amazon.device.ads.ThreadUtils.ThreadRunner;
import com.amazon.device.ads.WebRequest.WebRequestException;
import com.amazon.device.ads.WebRequest.WebRequestFactory;
import com.amazon.device.ads.WebRequest.WebResponse;
import com.mopub.common.Constants;

class AdUrlLoader {
    private static final String LOGTAG = AdUrlLoader.class.getSimpleName();
    private final AdControlAccessor adControlAccessor;
    private final AdWebViewClient adWebViewClient;
    private final DeviceInfo deviceInfo;
    private final MobileAdsLogger logger;
    private final ThreadRunner threadRunner;
    private final WebRequestFactory webRequestFactory;
    private final WebUtils2 webUtils;

    public AdUrlLoader(ThreadRunner threadRunner, AdWebViewClient adWebViewClient, WebRequestFactory webRequestFactory, AdControlAccessor adControlAccessor, WebUtils2 webUtils2, MobileAdsLoggerFactory mobileAdsLoggerFactory, DeviceInfo deviceInfo) {
        this.threadRunner = threadRunner;
        this.adWebViewClient = adWebViewClient;
        this.webRequestFactory = webRequestFactory;
        this.adControlAccessor = adControlAccessor;
        this.webUtils = webUtils2;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.deviceInfo = deviceInfo;
    }

    public void putUrlExecutorInAdWebViewClient(String str, UrlExecutor urlExecutor) {
        this.adWebViewClient.putUrlExecutor(str, urlExecutor);
    }

    public void setAdWebViewClientListener(AdWebViewClientListener adWebViewClientListener) {
        this.adWebViewClient.setListener(adWebViewClientListener);
    }

    public AdWebViewClient getAdWebViewClient() {
        return this.adWebViewClient;
    }

    public void loadUrl(final String str, final boolean z, final PreloadCallback preloadCallback) {
        String scheme = this.webUtils.getScheme(str);
        if (scheme.equals(Constants.HTTP) || scheme.equals(Constants.HTTPS)) {
            this.threadRunner.execute(new Runnable() {
                public void run() {
                    AdUrlLoader.this.loadUrlInThread(str, z, preloadCallback);
                }
            }, ExecutionStyle.RUN_ASAP, ExecutionThread.BACKGROUND_THREAD);
        } else {
            openUrl(str);
        }
    }

    private void loadUrlInThread(String str, boolean z, PreloadCallback preloadCallback) {
        WebRequest createWebRequest = this.webRequestFactory.createWebRequest();
        createWebRequest.setExternalLogTag(LOGTAG);
        createWebRequest.enableLogUrl(true);
        createWebRequest.setUrlString(str);
        createWebRequest.putHeader("User-Agent", this.deviceInfo.getUserAgentString());
        WebResponse webResponse = null;
        try {
            webResponse = createWebRequest.makeCall();
        } catch (WebRequestException e) {
            this.logger.e("Could not load URL (%s) into AdContainer: %s", str, e.getMessage());
        }
        if (webResponse != null) {
            final String readAsString = webResponse.getResponseReader().readAsString();
            if (readAsString != null) {
                final String str2 = str;
                final boolean z2 = z;
                final PreloadCallback preloadCallback2 = preloadCallback;
                this.threadRunner.execute(new Runnable() {
                    public void run() {
                        AdUrlLoader.this.adControlAccessor.loadHtml(str2, readAsString, z2, preloadCallback2);
                    }
                }, ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD);
                return;
            }
            this.logger.e("Could not load URL (%s) into AdContainer.", str);
        }
    }

    public void openUrl(String str) {
        this.adWebViewClient.openUrl(str);
    }
}
