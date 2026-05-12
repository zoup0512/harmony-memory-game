package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class DeleteObjectRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private String key;

    public DeleteObjectRequest(String bucketName, String key) {
        setBucketName(bucketName);
        setKey(key);
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public DeleteObjectRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public DeleteObjectRequest withKey(String key) {
        setKey(key);
        return this;
    }
}
