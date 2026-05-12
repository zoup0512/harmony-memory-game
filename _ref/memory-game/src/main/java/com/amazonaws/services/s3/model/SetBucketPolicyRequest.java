package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class SetBucketPolicyRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private String policyText;

    public SetBucketPolicyRequest(String bucketName, String policyText) {
        this.bucketName = bucketName;
        this.policyText = policyText;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public SetBucketPolicyRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public String getPolicyText() {
        return this.policyText;
    }

    public void setPolicyText(String policyText) {
        this.policyText = policyText;
    }

    public SetBucketPolicyRequest withPolicyText(String policyText) {
        setPolicyText(policyText);
        return this;
    }
}
