package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class HeadBucketRequest extends AmazonWebServiceRequest {
    private String bucketName;

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public HeadBucketRequest(String bucketName) {
        this.bucketName = bucketName;
    }
}
