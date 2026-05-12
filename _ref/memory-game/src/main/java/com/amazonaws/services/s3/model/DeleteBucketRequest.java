package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class DeleteBucketRequest extends AmazonWebServiceRequest implements S3AccelerateUnsupported {
    private String bucketName;

    public DeleteBucketRequest(String bucketName) {
        setBucketName(bucketName);
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return this.bucketName;
    }
}
