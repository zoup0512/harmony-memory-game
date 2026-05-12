package com.amazonaws.http.protocol;

import com.amazonaws.util.AWSRequestMetrics;
import com.amazonaws.util.AWSRequestMetrics.Field;
import java.io.IOException;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestExecutor;

public class SdkHttpRequestExecutor extends HttpRequestExecutor {
    protected HttpResponse doSendRequest(HttpRequest request, HttpClientConnection conn, HttpContext context) throws IOException, HttpException {
        AWSRequestMetrics awsRequestMetrics = (AWSRequestMetrics) context.getAttribute(AWSRequestMetrics.class.getSimpleName());
        if (awsRequestMetrics == null) {
            return super.doSendRequest(request, conn, context);
        }
        awsRequestMetrics.startEvent(Field.HttpClientSendRequestTime);
        try {
            HttpResponse doSendRequest = super.doSendRequest(request, conn, context);
            return doSendRequest;
        } finally {
            awsRequestMetrics.endEvent(Field.HttpClientSendRequestTime);
        }
    }

    protected HttpResponse doReceiveResponse(HttpRequest request, HttpClientConnection conn, HttpContext context) throws HttpException, IOException {
        AWSRequestMetrics awsRequestMetrics = (AWSRequestMetrics) context.getAttribute(AWSRequestMetrics.class.getSimpleName());
        if (awsRequestMetrics == null) {
            return super.doReceiveResponse(request, conn, context);
        }
        awsRequestMetrics.startEvent(Field.HttpClientReceiveResponseTime);
        try {
            HttpResponse doReceiveResponse = super.doReceiveResponse(request, conn, context);
            return doReceiveResponse;
        } finally {
            awsRequestMetrics.endEvent(Field.HttpClientReceiveResponseTime);
        }
    }
}
