package com.amazon.device.ads;

import com.amazon.device.ads.WebRequest.WebRequestException;

class Metrics {
    private static final String LOGTAG = Metrics.class.getSimpleName();
    private static final boolean TYPED_METRIC = true;
    private static Metrics instance = new Metrics();
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    private MetricsCollector metricsCollector = new MetricsCollector();

    interface MetricsSubmitter {
        String getInstrumentationPixelUrl();

        MetricsCollector getMetricsCollector();

        void resetMetricsCollector();
    }

    enum MetricType {
        AD_LATENCY_TOTAL("tl", true),
        AD_LATENCY_TOTAL_SUCCESS("tsl", true),
        AD_LATENCY_TOTAL_FAILURE("tfl", true),
        AD_LOAD_LATENCY_LOADAD_TO_FETCH_THREAD_REQUEST_START("llfsl", true),
        AD_LOAD_LATENCY_FETCH_THREAD_SPIN_UP("lfsul"),
        AD_LOAD_LATENCY_FETCH_THREAD_START_TO_AAX_GET_AD_START("lfsasl"),
        AD_LOAD_LATENCY_AAX_GET_AD_END_TO_FETCH_THREAD_END("laefel"),
        AD_LOAD_LATENCY_FINALIZE_FETCH_SPIN_UP("lffsul"),
        AD_LOAD_LATENCY_FINALIZE_FETCH_START_TO_RENDER_START("lffsrsl", true),
        AD_LOAD_LATENCY_FINALIZE_FETCH_START_TO_FAILURE("lffsfl", true),
        AD_LOAD_LATENCY_CREATE_AAX_GET_AD_URL("lcaul"),
        ASSETS_CREATED_LATENCY("lacl"),
        ASSETS_ENSURED_LATENCY("lael"),
        ASSETS_FAILED("af"),
        AD_LOADED_TO_AD_SHOW_TIME("alast"),
        AD_SHOW_LATENCY("lsa"),
        AD_SHOW_DURATION("sd", true),
        AD_LAYOUT_INITIALIZATION("ali"),
        AAX_LATENCY_GET_AD("al"),
        AD_LOAD_FAILED("lf"),
        AD_LOAD_FAILED_ON_AAX_CALL_TIMEOUT("lfat"),
        AD_LOAD_FAILED_ON_PRERENDERING_TIMEOUT("lfpt", true),
        AD_LOAD_FAILED_NO_FILL("lfnf"),
        AD_LOAD_FAILED_NETWORK_TIMEOUT("lfnt"),
        AD_LOAD_FAILED_INTERNAL_ERROR("lfie"),
        AD_COUNTER_IDENTIFIED_DEVICE("id"),
        AD_COUNTER_RENDERING_FATAL("rf", true),
        AD_LATENCY_RENDER("rl", true),
        AD_LATENCY_RENDER_FAILED("rlf", true),
        AD_COUNTER_FAILED_DUE_TO_NO_RETRY("nrtf"),
        AD_NO_RETRY_TTL_RECEIVED("nrtr"),
        AD_COUNTER_AUTO_AD_SIZE("aas"),
        AD_COUNTER_PARENT_VIEW_MISSING("pvm"),
        ADLAYOUT_HEIGHT_ZERO("ahz"),
        VIEWPORT_SCALE("vs"),
        AD_COUNTER_RESHOWN("rs", true),
        AD_FAILED_UNKNOWN_WEBVIEW_ISSUE("fuwi"),
        AD_FAILED_NULL_LAYOUT_PARAMS("fnlp"),
        AD_FAILED_LAYOUT_NOT_RUN("flnr"),
        AD_FAILED_INVALID_AUTO_AD_SIZE("faas"),
        SIS_COUNTER_IDENTIFIED_DEVICE_CHANGED("sid"),
        SIS_LATENCY_REGISTER("srl"),
        SIS_LATENCY_UPDATE_DEVICE_INFO("sul"),
        SIS_LATENCY_REGISTER_EVENT("srel"),
        CONFIG_DOWNLOAD_ERROR("cde"),
        CONFIG_DOWNLOAD_LATENCY("cdt"),
        CONFIG_PARSE_ERROR("cpe"),
        AAX_CONFIG_DOWNLOAD_LATENCY("acl"),
        AAX_CONFIG_DOWNLOAD_FAILED("acf"),
        CUSTOM_RENDER_HANDLED("crh"),
        TLS_ENABLED("tls"),
        WIFI_PRESENT("wifi"),
        CARRIER_NAME("car"),
        CONNECTION_TYPE("ct"),
        AD_IS_INTERSTITIAL("i"),
        INTERSTITIAL_AD_ACTIVITY_FAILED("iaaf"),
        RENDER_REQUIREMENT_CHECK_FAILURE("rrcfc", true),
        EXPIRED_AD_CALL("eac", true),
        AD_ASPECT_RATIO_LESS_THAN_SCREEN_ASPECT_RATIO("rarfc", true),
        SET_ORIENTATION_FAILURE("rsofc", true),
        AD_EXPIRED_BEFORE_SHOWING("aebs", true),
        CDN_JAVASCRIPT_DOWLOAD_LATENCY("cjdl"),
        CDN_JAVASCRIPT_DOWNLOAD_FAILED("cjdf"),
        APP_INFO_LABEL_INDEX_OUT_OF_BOUNDS("ailioob");
        
        private final String aaxName;
        private final boolean isAdTypeSpecific;

        private MetricType(String str) {
            this(r2, r3, str, false);
        }

        private MetricType(String str, boolean z) {
            this.aaxName = str;
            this.isAdTypeSpecific = z;
        }

        public String getAaxName() {
            return this.aaxName;
        }

        public boolean isAdTypeSpecific() {
            return this.isAdTypeSpecific;
        }
    }

    Metrics() {
    }

    public static Metrics getInstance() {
        return instance;
    }

    public MetricsCollector getMetricsCollector() {
        return this.metricsCollector;
    }

    private MobileAdsLogger getLogger() {
        return this.logger;
    }

    public void submitAndResetMetrics(MetricsSubmitter metricsSubmitter) {
        getLogger().d("METRIC Submit and Reset");
        AdMetrics adMetrics = new AdMetrics(metricsSubmitter);
        if (adMetrics.canSubmit()) {
            MetricsCollector metricsCollector = this.metricsCollector;
            this.metricsCollector = new MetricsCollector();
            adMetrics.addGlobalMetrics(metricsCollector);
            sendMetrics(adMetrics.getAaxWebRequestAndResetAdMetrics());
            return;
        }
        metricsSubmitter.resetMetricsCollector();
    }

    private void sendMetrics(final WebRequest webRequest) {
        ThreadUtils.scheduleRunnable(new Runnable() {
            public void run() {
                webRequest.enableLog(true);
                try {
                    webRequest.makeCall();
                } catch (WebRequestException e) {
                    switch (e.getStatus()) {
                        case INVALID_CLIENT_PROTOCOL:
                            Metrics.this.getLogger().e("Unable to submit metrics for ad due to an Invalid Client Protocol, msg: %s", e.getMessage());
                            return;
                        case NETWORK_FAILURE:
                            Metrics.this.getLogger().e("Unable to submit metrics for ad due to Network Failure, msg: %s", e.getMessage());
                            return;
                        case MALFORMED_URL:
                            Metrics.this.getLogger().e("Unable to submit metrics for ad due to a Malformed Pixel URL, msg: %s", e.getMessage());
                            break;
                        case UNSUPPORTED_ENCODING:
                            break;
                        default:
                            return;
                    }
                    Metrics.this.getLogger().e("Unable to submit metrics for ad because of unsupported character encoding, msg: %s", e.getMessage());
                }
            }
        });
    }
}
