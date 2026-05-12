package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class AbortMultipartUploadRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private String key;
    private String uploadId;

    public AbortMultipartUploadRequest(String bucketName, String key, String uploadId) {
        this.bucketName = bucketName;
        this.key = key;
        this.uploadId = uploadId;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String value) {
        this.bucketName = value;
    }

    public AbortMultipartUploadRequest withBucketName(String bucketName) {
        this.bucketName = bucketName;
        return this;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public AbortMultipartUploadRequest withKey(String key) {
        this.key = key;
        return this;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public AbortMultipartUploadRequest withUploadId(String uploadId) {
        this.uploadId = uploadId;
        return this;
    }
}
