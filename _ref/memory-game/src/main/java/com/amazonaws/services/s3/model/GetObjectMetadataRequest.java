package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class GetObjectMetadataRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private String key;
    private SSECustomerKey sseCustomerKey;
    private String versionId;

    public GetObjectMetadataRequest(String bucketName, String key) {
        setBucketName(bucketName);
        setKey(key);
    }

    public GetObjectMetadataRequest(String bucketName, String key, String versionId) {
        this(bucketName, key);
        setVersionId(versionId);
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public GetObjectMetadataRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public GetObjectMetadataRequest withKey(String key) {
        setKey(key);
        return this;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public GetObjectMetadataRequest withVersionId(String versionId) {
        setVersionId(versionId);
        return this;
    }

    public SSECustomerKey getSSECustomerKey() {
        return this.sseCustomerKey;
    }

    public void setSSECustomerKey(SSECustomerKey sseKey) {
        this.sseCustomerKey = sseKey;
    }

    public GetObjectMetadataRequest withSSECustomerKey(SSECustomerKey sseKey) {
        setSSECustomerKey(sseKey);
        return this;
    }
}
