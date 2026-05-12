package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class GetBucketAclRequest extends AmazonWebServiceRequest {
    private String bucketName;

    public GetBucketAclRequest(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return this.bucketName;
    }
}
