package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class SetBucketTaggingConfigurationRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private BucketTaggingConfiguration taggingConfiguration;

    public SetBucketTaggingConfigurationRequest(String bucketName, BucketTaggingConfiguration taggingConfiguration) {
        this.bucketName = bucketName;
        this.taggingConfiguration = taggingConfiguration;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public SetBucketTaggingConfigurationRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public BucketTaggingConfiguration getTaggingConfiguration() {
        return this.taggingConfiguration;
    }

    public void setTaggingConfiguration(BucketTaggingConfiguration taggingConfiguration) {
        this.taggingConfiguration = taggingConfiguration;
    }

    public SetBucketTaggingConfigurationRequest withTaggingConfiguration(BucketTaggingConfiguration taggingConfiguration) {
        setTaggingConfiguration(taggingConfiguration);
        return this;
    }
}
