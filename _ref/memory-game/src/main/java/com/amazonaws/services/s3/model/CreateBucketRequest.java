package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class CreateBucketRequest extends AmazonWebServiceRequest implements S3AccelerateUnsupported {
    private AccessControlList accessControlList;
    private String bucketName;
    private CannedAccessControlList cannedAcl;
    private String region;

    public CreateBucketRequest(String bucketName) {
        this(bucketName, Region.US_Standard);
    }

    public CreateBucketRequest(String bucketName, Region region) {
        this(bucketName, region.toString());
    }

    public CreateBucketRequest(String bucketName, String region) {
        setBucketName(bucketName);
        setRegion(region);
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return this.region;
    }

    public CannedAccessControlList getCannedAcl() {
        return this.cannedAcl;
    }

    public void setCannedAcl(CannedAccessControlList cannedAcl) {
        this.cannedAcl = cannedAcl;
    }

    public CreateBucketRequest withCannedAcl(CannedAccessControlList cannedAcl) {
        setCannedAcl(cannedAcl);
        return this;
    }

    public AccessControlList getAccessControlList() {
        return this.accessControlList;
    }

    public void setAccessControlList(AccessControlList accessControlList) {
        this.accessControlList = accessControlList;
    }

    public CreateBucketRequest withAccessControlList(AccessControlList accessControlList) {
        setAccessControlList(accessControlList);
        return this;
    }
}
