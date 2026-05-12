package com.amazon.device.ads;

import com.amazon.device.ads.Configuration.ConfigOption;
import com.amazon.device.ads.WebRequest.HttpMethod;
import com.amazon.device.ads.WebRequest.WebRequestException;
import com.amazon.device.ads.WebRequest.WebRequestFactory;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONObject;

class SISRequestor {
    protected static final String API_LEVEL_ENDPOINT = "/api3";
    private final Configuration configuration;
    private final SISRequestorCallback sisRequestorCallback;
    private final SISRequest[] sisRequests;
    private final WebRequestFactory webRequestFactory;

    static class SISRequestorFactory {
        SISRequestorFactory() {
        }

        public SISRequestor createSISRequestor(SISRequest... sISRequestArr) {
            return createSISRequestor(null, sISRequestArr);
        }

        public SISRequestor createSISRequestor(SISRequestorCallback sISRequestorCallback, SISRequest... sISRequestArr) {
            return new SISRequestor(sISRequestorCallback, sISRequestArr);
        }
    }

    public SISRequestor(SISRequestorCallback sISRequestorCallback, SISRequest... sISRequestArr) {
        this(new WebRequestFactory(), sISRequestorCallback, Configuration.getInstance(), sISRequestArr);
    }

    SISRequestor(WebRequestFactory webRequestFactory, SISRequestorCallback sISRequestorCallback, Configuration configuration, SISRequest... sISRequestArr) {
        this.webRequestFactory = webRequestFactory;
        this.sisRequestorCallback = sISRequestorCallback;
        this.configuration = configuration;
        this.sisRequests = sISRequestArr;
    }

    public void startCallSIS() {
        for (SISRequest callSIS : this.sisRequests) {
            callSIS(callSIS);
        }
        SISRequestorCallback sisRequestorCallback = getSisRequestorCallback();
        if (sisRequestorCallback != null) {
            sisRequestorCallback.onSISCallComplete();
        }
    }

    private void callSIS(SISRequest sISRequest) {
        try {
            JSONObject readAsJSON = getWebRequest(sISRequest).makeCall().getResponseReader().readAsJSON();
            if (readAsJSON != null) {
                int integerFromJSON = JSONUtils.getIntegerFromJSON(readAsJSON, "rcode", 0);
                String stringFromJSON = JSONUtils.getStringFromJSON(readAsJSON, "msg", "");
                if (integerFromJSON == 1) {
                    sISRequest.getLogger().i("Result - code: %d, msg: %s", Integer.valueOf(integerFromJSON), stringFromJSON);
                    sISRequest.onResponseReceived(readAsJSON);
                    return;
                }
                sISRequest.getLogger().w("Result - code: %d, msg: %s", Integer.valueOf(integerFromJSON), stringFromJSON);
            }
        } catch (WebRequestException e) {
        }
    }

    private WebRequest getWebRequest(SISRequest sISRequest) {
        WebRequest createWebRequest = this.webRequestFactory.createWebRequest();
        createWebRequest.setExternalLogTag(sISRequest.getLogTag());
        createWebRequest.setHttpMethod(HttpMethod.POST);
        createWebRequest.setHost(getHostname());
        createWebRequest.setPath(getEndpoint(sISRequest));
        createWebRequest.enableLog(true);
        HashMap postParameters = sISRequest.getPostParameters();
        if (postParameters != null) {
            for (Entry entry : postParameters.entrySet()) {
                createWebRequest.putPostParameter((String) entry.getKey(), (String) entry.getValue());
            }
        }
        createWebRequest.setQueryStringParameters(sISRequest.getQueryParameters());
        createWebRequest.setMetricsCollector(Metrics.getInstance().getMetricsCollector());
        createWebRequest.setServiceCallLatencyMetric(sISRequest.getCallMetricType());
        return createWebRequest;
    }

    private String getHostname() {
        String string = this.configuration.getString(ConfigOption.SIS_URL);
        if (string == null) {
            return string;
        }
        int indexOf = string.indexOf("/");
        if (indexOf > -1) {
            return string.substring(0, indexOf);
        }
        return string;
    }

    private String getEndpoint(SISRequest sISRequest) {
        String string = this.configuration.getString(ConfigOption.SIS_URL);
        if (string != null) {
            int indexOf = string.indexOf("/");
            if (indexOf > -1) {
                string = string.substring(indexOf);
            } else {
                string = "";
            }
        }
        return string + API_LEVEL_ENDPOINT + sISRequest.getPath();
    }

    private SISRequestorCallback getSisRequestorCallback() {
        return this.sisRequestorCallback;
    }
}
