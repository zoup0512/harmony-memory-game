package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class GetBucketPolicyRequest extends AmazonWebServiceRequest {
    private String bucketName;

    public GetBucketPolicyRequest(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public GetBucketPolicyRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }
}
