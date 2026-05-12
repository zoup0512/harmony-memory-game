package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class SetBucketReplicationConfigurationRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private BucketReplicationConfiguration replicationConfiguration;

    public SetBucketReplicationConfigurationRequest(String bucketName, BucketReplicationConfiguration replicationConfiguration) {
        this.bucketName = bucketName;
        this.replicationConfiguration = replicationConfiguration;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public SetBucketReplicationConfigurationRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public BucketReplicationConfiguration getReplicationConfiguration() {
        return this.replicationConfiguration;
    }

    public void setReplicationConfiguration(BucketReplicationConfiguration replicationConfiguration) {
        this.replicationConfiguration = replicationConfiguration;
    }

    public SetBucketReplicationConfigurationRequest withReplicationConfiguration(BucketReplicationConfiguration replicationConfiguration) {
        setReplicationConfiguration(replicationConfiguration);
        return this;
    }
}
