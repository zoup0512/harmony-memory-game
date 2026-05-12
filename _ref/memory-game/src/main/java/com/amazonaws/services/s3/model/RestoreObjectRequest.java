package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class RestoreObjectRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private int expirationInDays;
    private String key;
    private String versionId;

    public RestoreObjectRequest(String bucketName, String key) {
        this(bucketName, key, -1);
    }

    public RestoreObjectRequest(String bucketName, String key, int expirationInDays) {
        this.bucketName = bucketName;
        this.key = key;
        this.expirationInDays = expirationInDays;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public RestoreObjectRequest withBucketName(String bucketName) {
        this.bucketName = bucketName;
        return this;
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

    public RestoreObjectRequest withKey(String key) {
        this.key = key;
        return this;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public RestoreObjectRequest withVersionId(String versionId) {
        this.versionId = versionId;
        return this;
    }

    public void setExpirationInDays(int expirationInDays) {
        this.expirationInDays = expirationInDays;
    }

    public int getExpirationInDays() {
        return this.expirationInDays;
    }

    public RestoreObjectRequest withExpirationInDays(int expirationInDays) {
        this.expirationInDays = expirationInDays;
        return this;
    }
}
