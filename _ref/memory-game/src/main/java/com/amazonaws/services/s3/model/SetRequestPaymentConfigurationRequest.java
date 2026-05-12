package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class SetRequestPaymentConfigurationRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private RequestPaymentConfiguration configuration;

    public SetRequestPaymentConfigurationRequest(String bucketName, RequestPaymentConfiguration configuration) {
        setBucketName(bucketName);
        this.configuration = configuration;
    }

    public RequestPaymentConfiguration getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration(RequestPaymentConfiguration configuration) {
        this.configuration = configuration;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
