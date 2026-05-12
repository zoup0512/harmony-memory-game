package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class SetBucketWebsiteConfigurationRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private BucketWebsiteConfiguration configuration;

    public SetBucketWebsiteConfigurationRequest(String bucketName, BucketWebsiteConfiguration configuration) {
        this.bucketName = bucketName;
        this.configuration = configuration;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public SetBucketWebsiteConfigurationRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public void setConfiguration(BucketWebsiteConfiguration configuration) {
        this.configuration = configuration;
    }

    public BucketWebsiteConfiguration getConfiguration() {
        return this.configuration;
    }

    public SetBucketWebsiteConfigurationRequest withConfiguration(BucketWebsiteConfiguration configuration) {
        setConfiguration(configuration);
        return this;
    }
}
