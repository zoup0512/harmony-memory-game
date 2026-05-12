package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class GetBucketLocationRequest extends AmazonWebServiceRequest {
    private String bucketName;

    public GetBucketLocationRequest(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public GetBucketLocationRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }
}
