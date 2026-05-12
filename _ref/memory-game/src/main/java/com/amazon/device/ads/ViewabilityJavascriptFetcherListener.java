package com.amazon.device.ads;

class ViewabilityJavascriptFetcherListener implements ConfigurationListener {
    private static final String LOGTAG = ViewabilityJavascriptFetcherListener.class.getSimpleName();
    private final MobileAdsLogger logger;
    private ViewabilityJavascriptFetcher viewabilityJavascriptFetcher;

    ViewabilityJavascriptFetcherListener() {
        this(new ViewabilityJavascriptFetcher(), new MobileAdsLoggerFactory());
    }

    ViewabilityJavascriptFetcherListener(ViewabilityJavascriptFetcher viewabilityJavascriptFetcher, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this.viewabilityJavascriptFetcher = viewabilityJavascriptFetcher;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
    }

    public void onConfigurationReady() {
        this.viewabilityJavascriptFetcher.fetchJavascript();
    }

    public void onConfigurationFailure() {
        this.logger.w("Configuration fetching failed so Viewability Javascript fetch will not proceed.");
    }
}
