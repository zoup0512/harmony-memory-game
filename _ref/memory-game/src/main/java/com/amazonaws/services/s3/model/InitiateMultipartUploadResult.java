package com.amazonaws.services.s3.model;

import com.amazonaws.services.s3.internal.SSEResultBase;

public class InitiateMultipartUploadResult extends SSEResultBase {
    private String bucketName;
    private String key;
    private String uploadId;

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }
}
