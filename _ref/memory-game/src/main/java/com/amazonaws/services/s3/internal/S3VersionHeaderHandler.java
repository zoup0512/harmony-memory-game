package com.amazonaws.services.s3.internal;

import com.amazonaws.http.HttpResponse;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.transform.XmlResponsesSaxParser.CopyObjectResultHandler;

public class S3VersionHeaderHandler implements HeaderHandler<CopyObjectResultHandler> {
    public void handle(CopyObjectResultHandler result, HttpResponse response) {
        result.setVersionId((String) response.getHeaders().get(Headers.S3_VERSION_ID));
    }
}
