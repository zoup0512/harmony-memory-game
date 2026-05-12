package com.amazon.device.ads;

import com.amazon.device.ads.WebRequest.WebRequestFactory;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONObject;

class AdMetrics {
    public static final String LOGTAG = AdMetrics.class.getSimpleName();
    private MetricsCollector globalMetrics;
    private final MobileAdsLogger logger;
    private final MobileAdsInfoStore mobileAdsInfoStore;
    private final MetricsSubmitter submitter;
    private final WebRequestFactory webRequestFactory;

    public AdMetrics(MetricsSubmitter metricsSubmitter) {
        this(metricsSubmitter, MobileAdsInfoStore.getInstance());
    }

    AdMetrics(MetricsSubmitter metricsSubmitter, MobileAdsInfoStore mobileAdsInfoStore) {
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        this.webRequestFactory = new WebRequestFactory();
        this.submitter = metricsSubmitter;
        this.mobileAdsInfoStore = mobileAdsInfoStore;
    }

    private String getAaxUrlAndResetAdMetrics() {
        String str = this.submitter.getInstrumentationPixelUrl() + WebUtils.getURLEncodedString(getAaxJson());
        this.submitter.resetMetricsCollector();
        return str;
    }

    public WebRequest getAaxWebRequestAndResetAdMetrics() {
        WebRequest createWebRequest = this.webRequestFactory.createWebRequest();
        createWebRequest.setUrlString(getAaxUrlAndResetAdMetrics());
        return createWebRequest;
    }

    public boolean canSubmit() {
        String instrumentationPixelUrl = this.submitter.getInstrumentationPixelUrl();
        if (instrumentationPixelUrl == null || instrumentationPixelUrl.equals("")) {
            return false;
        }
        if (this.mobileAdsInfoStore.getRegistrationInfo().getAppKey() != null) {
            return true;
        }
        this.logger.d("Not submitting metrics because the AppKey is not set.");
        return false;
    }

    public void addGlobalMetrics(MetricsCollector metricsCollector) {
        this.globalMetrics = metricsCollector;
    }

    protected String getAaxJson() {
        JSONObject jSONObject = new JSONObject();
        JSONUtils.put(jSONObject, "c", "msdk");
        JSONUtils.put(jSONObject, "v", Version.getRawSDKVersion());
        addMetricsToJSON(jSONObject, this.submitter.getMetricsCollector());
        addMetricsToJSON(jSONObject, this.globalMetrics);
        String jSONObject2 = jSONObject.toString();
        return jSONObject2.substring(1, jSONObject2.length() - 1);
    }

    protected static void addMetricsToJSON(JSONObject jSONObject, MetricsCollector metricsCollector) {
        if (metricsCollector != null) {
            String str;
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            String adTypeMetricTag = metricsCollector.getAdTypeMetricTag();
            if (adTypeMetricTag != null) {
                str = adTypeMetricTag + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR;
            } else {
                str = adTypeMetricTag;
            }
            for (MetricHit metricHit : (MetricHit[]) metricsCollector.getMetricHits().toArray(new MetricHit[metricsCollector.getMetricHits().size()])) {
                String str2;
                String aaxName = metricHit.metric.getAaxName();
                if (str == null || !metricHit.metric.isAdTypeSpecific()) {
                    str2 = aaxName;
                } else {
                    str2 = str + aaxName;
                }
                if (metricHit instanceof MetricHitStartTime) {
                    hashMap.put(metricHit.metric, Long.valueOf(((MetricHitStartTime) metricHit).startTime));
                } else if (metricHit instanceof MetricHitStopTime) {
                    MetricHitStopTime metricHitStopTime = (MetricHitStopTime) metricHit;
                    Long l = (Long) hashMap.remove(metricHit.metric);
                    if (l != null) {
                        JSONUtils.put(jSONObject, str2, (JSONUtils.getLongFromJSON(jSONObject, str2, 0) + metricHitStopTime.stopTime) - l.longValue());
                    }
                } else if (metricHit instanceof MetricHitTotalTime) {
                    JSONUtils.put(jSONObject, str2, ((MetricHitTotalTime) metricHit).totalTime);
                } else if (metricHit instanceof MetricHitIncrement) {
                    int i;
                    MetricHitIncrement metricHitIncrement = (MetricHitIncrement) metricHit;
                    Integer num = (Integer) hashMap2.get(metricHit.metric);
                    if (num == null) {
                        i = metricHitIncrement.increment;
                    } else {
                        i = metricHitIncrement.increment + num.intValue();
                    }
                    hashMap2.put(metricHit.metric, Integer.valueOf(i));
                } else if (metricHit instanceof MetricHitString) {
                    JSONUtils.put(jSONObject, str2, ((MetricHitString) metricHit).text);
                }
            }
            for (Entry entry : hashMap2.entrySet()) {
                String aaxName2 = ((MetricType) entry.getKey()).getAaxName();
                if (str != null && ((MetricType) entry.getKey()).isAdTypeSpecific()) {
                    aaxName2 = str + aaxName2;
                }
                JSONUtils.put(jSONObject, aaxName2, ((Integer) entry.getValue()).intValue());
            }
        }
    }
}
