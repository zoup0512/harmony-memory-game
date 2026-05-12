package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class DeleteBucketPolicyRequest extends AmazonWebServiceRequest {
    private String bucketName;

    public DeleteBucketPolicyRequest(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public DeleteBucketPolicyRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }
}
