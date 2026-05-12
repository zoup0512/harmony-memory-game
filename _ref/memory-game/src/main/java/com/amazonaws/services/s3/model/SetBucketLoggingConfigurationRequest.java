package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class SetBucketLoggingConfigurationRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private BucketLoggingConfiguration loggingConfiguration;

    public SetBucketLoggingConfigurationRequest(String bucketName, BucketLoggingConfiguration loggingConfiguration) {
        this.bucketName = bucketName;
        this.loggingConfiguration = loggingConfiguration;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public SetBucketLoggingConfigurationRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public BucketLoggingConfiguration getLoggingConfiguration() {
        return this.loggingConfiguration;
    }

    public void setLoggingConfiguration(BucketLoggingConfiguration loggingConfiguration) {
        this.loggingConfiguration = loggingConfiguration;
    }

    public SetBucketLoggingConfigurationRequest withLoggingConfiguration(BucketLoggingConfiguration loggingConfiguration) {
        setLoggingConfiguration(loggingConfiguration);
        return this;
    }
}
