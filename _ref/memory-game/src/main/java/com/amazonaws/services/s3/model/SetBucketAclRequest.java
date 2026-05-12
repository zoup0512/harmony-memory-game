package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class SetBucketAclRequest extends AmazonWebServiceRequest {
    private AccessControlList acl;
    private String bucketName;
    private CannedAccessControlList cannedAcl;

    public SetBucketAclRequest(String bucketName, AccessControlList acl) {
        this.bucketName = bucketName;
        this.acl = acl;
        this.cannedAcl = null;
    }

    public SetBucketAclRequest(String bucketName, CannedAccessControlList acl) {
        this.bucketName = bucketName;
        this.acl = null;
        this.cannedAcl = acl;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public AccessControlList getAcl() {
        return this.acl;
    }

    public CannedAccessControlList getCannedAcl() {
        return this.cannedAcl;
    }
}
