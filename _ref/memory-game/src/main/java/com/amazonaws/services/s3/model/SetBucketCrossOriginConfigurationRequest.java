package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class SetBucketCrossOriginConfigurationRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private BucketCrossOriginConfiguration crossOriginConfiguration;

    public SetBucketCrossOriginConfigurationRequest(String bucketName, BucketCrossOriginConfiguration crossOriginConfiguration) {
        this.bucketName = bucketName;
        this.crossOriginConfiguration = crossOriginConfiguration;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public SetBucketCrossOriginConfigurationRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public BucketCrossOriginConfiguration getCrossOriginConfiguration() {
        return this.crossOriginConfiguration;
    }

    public void setCrossOriginConfiguration(BucketCrossOriginConfiguration crossOriginConfiguration) {
        this.crossOriginConfiguration = crossOriginConfiguration;
    }

    public SetBucketCrossOriginConfigurationRequest withCrossOriginConfiguration(BucketCrossOriginConfiguration crossOriginConfiguration) {
        setCrossOriginConfiguration(crossOriginConfiguration);
        return this;
    }
}
