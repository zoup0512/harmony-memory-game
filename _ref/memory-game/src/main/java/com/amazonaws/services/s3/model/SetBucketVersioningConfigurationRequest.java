package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class SetBucketVersioningConfigurationRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private MultiFactorAuthentication mfa;
    private BucketVersioningConfiguration versioningConfiguration;

    public SetBucketVersioningConfigurationRequest(String bucketName, BucketVersioningConfiguration configuration) {
        this.bucketName = bucketName;
        this.versioningConfiguration = configuration;
    }

    public SetBucketVersioningConfigurationRequest(String bucketName, BucketVersioningConfiguration configuration, MultiFactorAuthentication mfa) {
        this(bucketName, configuration);
        this.mfa = mfa;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public SetBucketVersioningConfigurationRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public BucketVersioningConfiguration getVersioningConfiguration() {
        return this.versioningConfiguration;
    }

    public void setVersioningConfiguration(BucketVersioningConfiguration versioningConfiguration) {
        this.versioningConfiguration = versioningConfiguration;
    }

    public SetBucketVersioningConfigurationRequest withVersioningConfiguration(BucketVersioningConfiguration versioningConfiguration) {
        setVersioningConfiguration(versioningConfiguration);
        return this;
    }

    public MultiFactorAuthentication getMfa() {
        return this.mfa;
    }

    public void setMfa(MultiFactorAuthentication mfa) {
        this.mfa = mfa;
    }

    public SetBucketVersioningConfigurationRequest withMfa(MultiFactorAuthentication mfa) {
        setMfa(mfa);
        return this;
    }
}
