package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.HttpMethod;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GeneratePresignedUrlRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private String contentMd5;
    private String contentType;
    private Date expiration;
    private String key;
    private HttpMethod method;
    private Map<String, String> requestParameters;
    private ResponseHeaderOverrides responseHeaders;
    private SSECustomerKey sseCustomerKey;

    public GeneratePresignedUrlRequest(String bucketName, String key) {
        this(bucketName, key, HttpMethod.GET);
    }

    public GeneratePresignedUrlRequest(String bucketName, String key, HttpMethod method) {
        this.requestParameters = new HashMap();
        this.bucketName = bucketName;
        this.key = key;
        this.method = method;
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public GeneratePresignedUrlRequest withMethod(HttpMethod method) {
        setMethod(method);
        return this;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public GeneratePresignedUrlRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public GeneratePresignedUrlRequest withKey(String key) {
        setKey(key);
        return this;
    }

    public Date getExpiration() {
        return this.expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public GeneratePresignedUrlRequest withExpiration(Date expiration) {
        setExpiration(expiration);
        return this;
    }

    public void addRequestParameter(String key, String value) {
        this.requestParameters.put(key, value);
    }

    public Map<String, String> getRequestParameters() {
        return this.requestParameters;
    }

    public ResponseHeaderOverrides getResponseHeaders() {
        return this.responseHeaders;
    }

    public void setResponseHeaders(ResponseHeaderOverrides responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public GeneratePresignedUrlRequest withResponseHeaders(ResponseHeaderOverrides responseHeaders) {
        setResponseHeaders(responseHeaders);
        return this;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public GeneratePresignedUrlRequest withContentType(String contentType) {
        setContentType(contentType);
        return this;
    }

    public String getContentMd5() {
        return this.contentMd5;
    }

    public void setContentMd5(String contentMd5) {
        this.contentMd5 = contentMd5;
    }

    public GeneratePresignedUrlRequest withContentMd5(String contentMd5) {
        this.contentMd5 = contentMd5;
        return this;
    }

    public SSECustomerKey getSSECustomerKey() {
        return this.sseCustomerKey;
    }

    public void setSSECustomerKey(SSECustomerKey sseKey) {
        this.sseCustomerKey = sseKey;
    }

    public GeneratePresignedUrlRequest withSSECustomerKey(SSECustomerKey sseKey) {
        setSSECustomerKey(sseKey);
        return this;
    }
}
