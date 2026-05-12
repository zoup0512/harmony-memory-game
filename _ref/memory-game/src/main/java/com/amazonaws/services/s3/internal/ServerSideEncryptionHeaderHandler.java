package com.amazonaws.services.s3.internal;

import com.amazonaws.http.HttpResponse;
import com.amazonaws.services.s3.Headers;

public class ServerSideEncryptionHeaderHandler<T extends ServerSideEncryptionResult> implements HeaderHandler<T> {
    public void handle(T result, HttpResponse response) {
        result.setSSEAlgorithm((String) response.getHeaders().get(Headers.SERVER_SIDE_ENCRYPTION));
        result.setSSECustomerAlgorithm((String) response.getHeaders().get(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_ALGORITHM));
        result.setSSECustomerKeyMd5((String) response.getHeaders().get(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_KEY_MD5));
        result.setSSEKMSKeyId((String) response.getHeaders().get(Headers.SERVER_SIDE_ENCRYPTION_KMS_KEY_ID));
    }
}
