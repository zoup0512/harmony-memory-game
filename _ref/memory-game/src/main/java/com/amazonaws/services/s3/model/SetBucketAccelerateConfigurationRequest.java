package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class SetBucketAccelerateConfigurationRequest extends AmazonWebServiceRequest implements S3AccelerateUnsupported {
    private BucketAccelerateConfiguration accelerateConfiguration;
    private String bucketName;

    public SetBucketAccelerateConfigurationRequest(String bucketName, BucketAccelerateConfiguration configuration) {
        this.bucketName = bucketName;
        this.accelerateConfiguration = configuration;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public SetBucketAccelerateConfigurationRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public BucketAccelerateConfiguration getAccelerateConfiguration() {
        return this.accelerateConfiguration;
    }

    public void setAccelerateConfiguration(BucketAccelerateConfiguration accelerateConfiguration) {
        this.accelerateConfiguration = accelerateConfiguration;
    }

    public SetBucketAccelerateConfigurationRequest withAccelerateConfiguration(BucketAccelerateConfiguration accelerateConfiguration) {
        setAccelerateConfiguration(accelerateConfiguration);
        return this;
    }
}
