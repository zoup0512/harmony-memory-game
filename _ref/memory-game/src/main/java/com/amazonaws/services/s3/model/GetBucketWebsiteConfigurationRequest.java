package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class GetBucketWebsiteConfigurationRequest extends AmazonWebServiceRequest {
    private String bucketName;

    public GetBucketWebsiteConfigurationRequest(String bucketName) {
        this.bucketName = bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public GetBucketWebsiteConfigurationRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }
}
