package com.amazonaws.services.s3.model;

import com.amazonaws.internal.MetricAware;
import com.amazonaws.internal.SdkFilterInputStream;
import com.amazonaws.metrics.AwsSdkMetrics;
import com.amazonaws.metrics.MetricFilterInputStream;
import com.amazonaws.services.s3.metrics.S3ServiceMetric;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpRequestBase;

public class S3ObjectInputStream extends SdkFilterInputStream {
    private final HttpRequestBase httpRequest;

    public S3ObjectInputStream(InputStream in) {
        this(in, null);
    }

    @Deprecated
    public S3ObjectInputStream(InputStream in, HttpRequestBase httpRequest) {
        this(in, httpRequest, wrapWithByteCounting(in));
    }

    @Deprecated
    public S3ObjectInputStream(InputStream in, HttpRequestBase httpRequest, boolean collectMetrics) {
        if (collectMetrics) {
            in = new MetricFilterInputStream(S3ServiceMetric.S3DownloadThroughput, in);
        }
        super(in);
        this.httpRequest = httpRequest;
    }

    private static boolean wrapWithByteCounting(InputStream in) {
        if (!AwsSdkMetrics.isMetricsEnabled()) {
            return false;
        }
        if ((in instanceof MetricAware) && ((MetricAware) in).isMetricActivated()) {
            return false;
        }
        return true;
    }

    public void abort() {
        try {
            close();
        } catch (IOException e) {
            LogFactory.getLog(getClass()).debug("FYI", e);
        }
    }

    @Deprecated
    public HttpRequestBase getHttpRequest() {
        return this.httpRequest;
    }
}
