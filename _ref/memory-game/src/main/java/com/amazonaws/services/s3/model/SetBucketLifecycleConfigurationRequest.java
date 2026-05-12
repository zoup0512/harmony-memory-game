package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class SetBucketLifecycleConfigurationRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private BucketLifecycleConfiguration lifecycleConfiguration;

    public SetBucketLifecycleConfigurationRequest(String bucketName, BucketLifecycleConfiguration lifecycleConfiguration) {
        this.bucketName = bucketName;
        this.lifecycleConfiguration = lifecycleConfiguration;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public SetBucketLifecycleConfigurationRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public BucketLifecycleConfiguration getLifecycleConfiguration() {
        return this.lifecycleConfiguration;
    }

    public void setLifecycleConfiguration(BucketLifecycleConfiguration lifecycleConfiguration) {
        this.lifecycleConfiguration = lifecycleConfiguration;
    }

    public SetBucketLifecycleConfigurationRequest withLifecycleConfiguration(BucketLifecycleConfiguration lifecycleConfiguration) {
        setLifecycleConfiguration(lifecycleConfiguration);
        return this;
    }
}
