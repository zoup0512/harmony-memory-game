package com.amazon.device.ads;

import com.amazon.device.ads.AdError.ErrorCode;
import com.amazon.device.ads.ThreadUtils.ExecutionStyle;
import com.amazon.device.ads.ThreadUtils.ExecutionThread;
import com.amazon.device.ads.ThreadUtils.ThreadRunner;
import com.amazon.device.ads.WebRequest.WebRequestException;
import com.amazon.device.ads.WebRequest.WebRequestStatus;
import com.amazon.device.ads.WebRequest.WebResponse;
import com.mopub.common.AdType;
import com.my.target.ads.MyTargetVideoView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

class AdLoader {
    public static final int AD_FAILED = -1;
    public static final int AD_LOAD_DEFERRED = 1;
    public static final int AD_READY_TO_LOAD = 0;
    public static final String DISABLED_APP_SERVER_MESSAGE = "DISABLED_APP";
    private static final String LOGTAG = AdLoader.class.getSimpleName();
    private final AdRequest adRequest;
    private final Assets assets;
    private CompositeMetricsCollector compositeMetricsCollector;
    private final DebugProperties debugProperties;
    private AdError error;
    private final MobileAdsInfoStore infoStore;
    private final MobileAdsLogger logger;
    private final Map<Integer, AdSlot> slots;
    private final SystemTime systemTime;
    private final ThreadRunner threadRunner;
    private int timeout;

    protected class AdFetchException extends Exception {
        private static final long serialVersionUID = 1;
        private final AdError adError;

        public AdFetchException(AdError adError) {
            this.adError = adError;
        }

        public AdFetchException(AdError adError, Throwable th) {
            super(th);
            this.adError = adError;
        }

        public AdError getAdError() {
            return this.adError;
        }
    }

    protected static class AdLoaderFactory {
        protected AdLoaderFactory() {
        }

        public AdLoader createAdLoader(AdRequest adRequest, Map<Integer, AdSlot> map) {
            return new AdLoader(adRequest, map);
        }
    }

    public AdLoader(AdRequest adRequest, Map<Integer, AdSlot> map) {
        this(adRequest, map, ThreadUtils.getThreadRunner(), new SystemTime(), Assets.getInstance(), MobileAdsInfoStore.getInstance(), new MobileAdsLoggerFactory(), DebugProperties.getInstance());
    }

    AdLoader(AdRequest adRequest, Map<Integer, AdSlot> map, ThreadRunner threadRunner, SystemTime systemTime, Assets assets, MobileAdsInfoStore mobileAdsInfoStore, MobileAdsLoggerFactory mobileAdsLoggerFactory, DebugProperties debugProperties) {
        this.timeout = 20000;
        this.error = null;
        this.compositeMetricsCollector = null;
        this.adRequest = adRequest;
        this.slots = map;
        this.threadRunner = threadRunner;
        this.systemTime = systemTime;
        this.assets = assets;
        this.infoStore = mobileAdsInfoStore;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.debugProperties = debugProperties;
    }

    public void setTimeout(int i) {
        this.timeout = i;
    }

    public void beginFetchAd() {
        getCompositeMetricsCollector().stopMetric(MetricType.AD_LOAD_LATENCY_LOADAD_TO_FETCH_THREAD_REQUEST_START);
        getCompositeMetricsCollector().startMetric(MetricType.AD_LOAD_LATENCY_FETCH_THREAD_SPIN_UP);
        startFetchAdThread();
    }

    protected void startFetchAdThread() {
        this.threadRunner.execute(new Runnable() {
            public void run() {
                AdLoader.this.fetchAd();
                AdLoader.this.beginFinalizeFetchAd();
            }
        }, ExecutionStyle.SCHEDULE, ExecutionThread.BACKGROUND_THREAD);
    }

    private void beginFinalizeFetchAd() {
        this.threadRunner.execute(new Runnable() {
            public void run() {
                AdLoader.this.finalizeFetchAd();
            }
        }, ExecutionStyle.SCHEDULE, ExecutionThread.MAIN_THREAD);
    }

    protected void fetchAd() {
        getCompositeMetricsCollector().stopMetric(MetricType.AD_LOAD_LATENCY_FETCH_THREAD_SPIN_UP);
        getCompositeMetricsCollector().startMetric(MetricType.AD_LOAD_LATENCY_FETCH_THREAD_START_TO_AAX_GET_AD_START);
        String str;
        if (this.assets.ensureAssetsCreated()) {
            try {
                WebResponse fetchResponseFromNetwork = fetchResponseFromNetwork();
                if (fetchResponseFromNetwork.isHttpStatusCodeOK()) {
                    JSONObject readAsJSON = fetchResponseFromNetwork.getResponseReader().readAsJSON();
                    if (readAsJSON == null) {
                        str = "Unable to parse response";
                        this.error = new AdError(ErrorCode.INTERNAL_ERROR, "Unable to parse response");
                        this.logger.e("Unable to parse response");
                        setErrorForAllSlots(this.error);
                        return;
                    }
                    parseResponse(readAsJSON);
                    getCompositeMetricsCollector().stopMetric(MetricType.AD_LOAD_LATENCY_AAX_GET_AD_END_TO_FETCH_THREAD_END);
                    getCompositeMetricsCollector().startMetric(MetricType.AD_LOAD_LATENCY_FINALIZE_FETCH_SPIN_UP);
                    return;
                }
                str = fetchResponseFromNetwork.getHttpStatusCode() + " - " + fetchResponseFromNetwork.getHttpStatus();
                this.error = new AdError(ErrorCode.NETWORK_ERROR, str);
                this.logger.e(str);
                setErrorForAllSlots(this.error);
                return;
            } catch (AdFetchException e) {
                this.error = e.getAdError();
                this.logger.e(e.getAdError().getMessage());
                setErrorForAllSlots(this.error);
                return;
            }
        }
        str = "Unable to create the assets needed to display ads";
        this.error = new AdError(ErrorCode.REQUEST_ERROR, "Unable to create the assets needed to display ads");
        this.logger.e("Unable to create the assets needed to display ads");
        setErrorForAllSlots(this.error);
    }

    private WebRequest getAdRequest() {
        getCompositeMetricsCollector().startMetric(MetricType.AD_LOAD_LATENCY_CREATE_AAX_GET_AD_URL);
        WebRequest webRequest = this.adRequest.getWebRequest();
        getCompositeMetricsCollector().stopMetric(MetricType.AD_LOAD_LATENCY_CREATE_AAX_GET_AD_URL);
        return webRequest;
    }

    private void parseResponse(JSONObject jSONObject) {
        long currentTimeMillis = this.systemTime.currentTimeMillis();
        String stringFromJSON = JSONUtils.getStringFromJSON(jSONObject, "status", null);
        HashSet hashSet = new HashSet(this.slots.keySet());
        AdError adError = getAdError(jSONObject);
        String stringFromJSON2 = JSONUtils.getStringFromJSON(jSONObject, "errorCode", "No Ad Received");
        this.adRequest.setInstrumentationPixelURL(JSONUtils.getStringFromJSON(jSONObject, "instrPixelURL", null));
        if (stringFromJSON != null && stringFromJSON.equals(MyTargetVideoView.COMPLETE_STATUS_OK)) {
            JSONArray jSONArrayFromJSON = JSONUtils.getJSONArrayFromJSON(jSONObject, "ads");
            for (int i = 0; i < jSONArrayFromJSON.length(); i++) {
                JSONObject jSONObjectFromJSONArray = JSONUtils.getJSONObjectFromJSONArray(jSONArrayFromJSON, i);
                if (jSONObjectFromJSONArray != null) {
                    int integerFromJSON = JSONUtils.getIntegerFromJSON(jSONObjectFromJSONArray, "slotId", -1);
                    AdSlot adSlot = (AdSlot) this.slots.get(Integer.valueOf(integerFromJSON));
                    if (adSlot != null) {
                        int i2;
                        hashSet.remove(Integer.valueOf(integerFromJSON));
                        String stringFromJSON3 = JSONUtils.getStringFromJSON(jSONObjectFromJSONArray, "instrPixelURL", this.adRequest.getInstrumentationPixelURL());
                        AdData adData = new AdData();
                        adData.setInstrumentationPixelUrl(stringFromJSON3);
                        adData.setImpressionPixelUrl(JSONUtils.getStringFromJSON(jSONObjectFromJSONArray, "impPixelURL", null));
                        if (adSlot.getRequestedAdSize().isAuto()) {
                            adSlot.getMetricsCollector().incrementMetric(MetricType.AD_COUNTER_AUTO_AD_SIZE);
                        }
                        String stringFromJSON4 = JSONUtils.getStringFromJSON(jSONObjectFromJSONArray, AdType.HTML, "");
                        JSONArray jSONArrayFromJSON2 = JSONUtils.getJSONArrayFromJSON(jSONObjectFromJSONArray, "creativeTypes");
                        Set hashSet2 = new HashSet();
                        if (jSONArrayFromJSON2 != null) {
                            for (integerFromJSON = 0; integerFromJSON < jSONArrayFromJSON2.length(); integerFromJSON++) {
                                AAXCreative creativeType = AAXCreative.getCreativeType(JSONUtils.getIntegerFromJSONArray(jSONArrayFromJSON2, integerFromJSON, 0));
                                if (creativeType != null) {
                                    hashSet2.add(creativeType);
                                } else {
                                    this.logger.w("%d is not a recognized creative type.", Integer.valueOf(i2));
                                }
                            }
                        }
                        if (AAXCreative.containsPrimaryCreativeType(hashSet2)) {
                            String stringFromJSON5 = JSONUtils.getStringFromJSON(jSONObjectFromJSONArray, "size", "");
                            if (stringFromJSON5 != null && (stringFromJSON5.equals("9999x9999") || stringFromJSON5.equals(AdType.INTERSTITIAL))) {
                                if (!hashSet2.contains(AAXCreative.INTERSTITIAL)) {
                                    hashSet2.add(AAXCreative.INTERSTITIAL);
                                }
                            }
                            i2 = 0;
                            integerFromJSON = 0;
                            if (!hashSet2.contains(AAXCreative.INTERSTITIAL)) {
                                Object obj = null;
                                String[] split = stringFromJSON5 != null ? stringFromJSON5.split("x") : null;
                                if (split == null || split.length != 2) {
                                    obj = 1;
                                } else {
                                    try {
                                        i2 = Integer.parseInt(split[0]);
                                        integerFromJSON = Integer.parseInt(split[1]);
                                    } catch (NumberFormatException e) {
                                        obj = 1;
                                    }
                                }
                                if (obj != null) {
                                    stringFromJSON3 = "Server returned an invalid ad size";
                                    adSlot.setAdError(new AdError(ErrorCode.INTERNAL_ERROR, "Server returned an invalid ad size"));
                                    this.logger.e("Server returned an invalid ad size");
                                }
                            }
                            long longFromJSON = JSONUtils.getLongFromJSON(jSONObjectFromJSONArray, "cacheTTL", -1);
                            if (longFromJSON > -1) {
                                adData.setExpirationTimeMillis((longFromJSON * 1000) + currentTimeMillis);
                            }
                            AdProperties adProperties = new AdProperties(jSONArrayFromJSON2);
                            adData.setHeight(integerFromJSON);
                            adData.setWidth(i2);
                            adData.setCreative(stringFromJSON4);
                            adData.setCreativeTypes(hashSet2);
                            adData.setProperties(adProperties);
                            adData.setFetched(true);
                            adSlot.setAdData(adData);
                        } else {
                            stringFromJSON3 = "No valid creative types found";
                            adSlot.setAdError(new AdError(ErrorCode.INTERNAL_ERROR, "No valid creative types found"));
                            this.logger.e("No valid creative types found");
                        }
                    }
                }
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            ((AdSlot) this.slots.get(num)).setAdError(adError);
            AdData adData2 = new AdData();
            adData2.setInstrumentationPixelUrl(this.adRequest.getInstrumentationPixelURL());
            ((AdSlot) this.slots.get(num)).setAdData(adData2);
            this.logger.w("%s; code: %s", adError.getMessage(), stringFromJSON2);
        }
    }

    protected AdError getAdError(JSONObject jSONObject) {
        int retrieveNoRetryTtlSeconds = retrieveNoRetryTtlSeconds(jSONObject);
        this.infoStore.setNoRetryTtl(retrieveNoRetryTtlSeconds);
        String stringFromJSON = JSONUtils.getStringFromJSON(jSONObject, "errorMessage", "No Ad Received");
        this.infoStore.setIsAppDisabled(stringFromJSON.equalsIgnoreCase(DISABLED_APP_SERVER_MESSAGE));
        String str = "Server Message: " + stringFromJSON;
        if (retrieveNoRetryTtlSeconds > 0) {
            getCompositeMetricsCollector().publishMetricInMilliseconds(MetricType.AD_NO_RETRY_TTL_RECEIVED, (long) (retrieveNoRetryTtlSeconds * 1000));
        }
        if (retrieveNoRetryTtlSeconds > 0 && !this.infoStore.getIsAppDisabled()) {
            return new AdError(ErrorCode.NO_FILL, str + ". Try again in " + retrieveNoRetryTtlSeconds + " seconds");
        } else if (stringFromJSON.equals("no results")) {
            return new AdError(ErrorCode.NO_FILL, str);
        } else {
            return new AdError(ErrorCode.INTERNAL_ERROR, str);
        }
    }

    private void setErrorForAllSlots(AdError adError) {
        for (AdSlot adError2 : this.slots.values()) {
            adError2.setAdError(adError);
        }
    }

    protected int retrieveNoRetryTtlSeconds(JSONObject jSONObject) {
        return this.debugProperties.getDebugPropertyAsInteger(DebugProperties.DEBUG_NORETRYTTL, Integer.valueOf(JSONUtils.getIntegerFromJSON(jSONObject, "noretryTTL", 0))).intValue();
    }

    protected void finalizeFetchAd() {
        for (Entry value : this.slots.entrySet()) {
            AdSlot adSlot = (AdSlot) value.getValue();
            if (adSlot.canBeUsed()) {
                adSlot.getMetricsCollector().stopMetric(MetricType.AD_LOAD_LATENCY_FINALIZE_FETCH_SPIN_UP);
                if (adSlot.isFetched()) {
                    adSlot.getMetricsCollector().startMetric(MetricType.AD_LOAD_LATENCY_FINALIZE_FETCH_START_TO_RENDER_START);
                    adSlot.initializeAd();
                } else {
                    adSlot.getMetricsCollector().startMetric(MetricType.AD_LOAD_LATENCY_FINALIZE_FETCH_START_TO_FAILURE);
                    if (adSlot.getAdError() != null) {
                        adSlot.adFailed(adSlot.getAdError());
                    } else {
                        adSlot.adFailed(new AdError(ErrorCode.INTERNAL_ERROR, "Unknown error occurred."));
                    }
                }
            } else {
                this.logger.w("Ad object was destroyed before ad fetching could be finalized. Ad fetching has been aborted.");
            }
        }
    }

    protected WebResponse fetchResponseFromNetwork() {
        WebRequest adRequest = getAdRequest();
        adRequest.setMetricsCollector(getCompositeMetricsCollector());
        adRequest.setServiceCallLatencyMetric(MetricType.AAX_LATENCY_GET_AD);
        adRequest.setTimeout(this.timeout);
        adRequest.setDisconnectEnabled(false);
        getCompositeMetricsCollector().stopMetric(MetricType.AD_LOAD_LATENCY_FETCH_THREAD_START_TO_AAX_GET_AD_START);
        getCompositeMetricsCollector().incrementMetric(MetricType.TLS_ENABLED);
        try {
            WebResponse makeCall = adRequest.makeCall();
            getCompositeMetricsCollector().startMetric(MetricType.AD_LOAD_LATENCY_AAX_GET_AD_END_TO_FETCH_THREAD_END);
            return makeCall;
        } catch (WebRequestException e) {
            AdError adError;
            if (e.getStatus() == WebRequestStatus.NETWORK_FAILURE) {
                adError = new AdError(ErrorCode.NETWORK_ERROR, "Could not contact Ad Server");
            } else if (e.getStatus() == WebRequestStatus.NETWORK_TIMEOUT) {
                adError = new AdError(ErrorCode.NETWORK_TIMEOUT, "Connection to Ad Server timed out");
            } else {
                adError = new AdError(ErrorCode.INTERNAL_ERROR, e.getMessage());
            }
            throw new AdFetchException(adError);
        }
    }

    private MetricsCollector getCompositeMetricsCollector() {
        if (this.compositeMetricsCollector == null) {
            ArrayList arrayList = new ArrayList();
            for (Entry value : this.slots.entrySet()) {
                arrayList.add(((AdSlot) value.getValue()).getMetricsCollector());
            }
            this.compositeMetricsCollector = new CompositeMetricsCollector(arrayList);
        }
        return this.compositeMetricsCollector;
    }
}
