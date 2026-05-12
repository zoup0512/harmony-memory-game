package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class InitiateMultipartUploadRequest extends AmazonWebServiceRequest {
    private AccessControlList accessControlList;
    private String bucketName;
    private CannedAccessControlList cannedACL;
    private String key;
    public ObjectMetadata objectMetadata;
    private String redirectLocation;
    private SSECustomerKey sseCustomerKey;
    private StorageClass storageClass;

    public InitiateMultipartUploadRequest(String bucketName, String key) {
        this.bucketName = bucketName;
        this.key = key;
    }

    public InitiateMultipartUploadRequest(String bucketName, String key, ObjectMetadata objectMetadata) {
        this.bucketName = bucketName;
        this.key = key;
        this.objectMetadata = objectMetadata;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public InitiateMultipartUploadRequest withBucketName(String bucketName) {
        this.bucketName = bucketName;
        return this;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public InitiateMultipartUploadRequest withKey(String key) {
        this.key = key;
        return this;
    }

    public CannedAccessControlList getCannedACL() {
        return this.cannedACL;
    }

    public void setCannedACL(CannedAccessControlList cannedACL) {
        this.cannedACL = cannedACL;
    }

    public InitiateMultipartUploadRequest withCannedACL(CannedAccessControlList acl) {
        this.cannedACL = acl;
        return this;
    }

    public AccessControlList getAccessControlList() {
        return this.accessControlList;
    }

    public void setAccessControlList(AccessControlList accessControlList) {
        this.accessControlList = accessControlList;
    }

    public InitiateMultipartUploadRequest withAccessControlList(AccessControlList accessControlList) {
        setAccessControlList(accessControlList);
        return this;
    }

    public StorageClass getStorageClass() {
        return this.storageClass;
    }

    public void setStorageClass(StorageClass storageClass) {
        this.storageClass = storageClass;
    }

    public InitiateMultipartUploadRequest withStorageClass(StorageClass storageClass) {
        this.storageClass = storageClass;
        return this;
    }

    public ObjectMetadata getObjectMetadata() {
        return this.objectMetadata;
    }

    public void setObjectMetadata(ObjectMetadata objectMetadata) {
        this.objectMetadata = objectMetadata;
    }

    public InitiateMultipartUploadRequest withObjectMetadata(ObjectMetadata objectMetadata) {
        setObjectMetadata(objectMetadata);
        return this;
    }

    public void setRedirectLocation(String redirectLocation) {
        this.redirectLocation = redirectLocation;
    }

    public String getRedirectLocation() {
        return this.redirectLocation;
    }

    public InitiateMultipartUploadRequest withRedirectLocation(String redirectLocation) {
        this.redirectLocation = redirectLocation;
        return this;
    }

    public SSECustomerKey getSSECustomerKey() {
        return this.sseCustomerKey;
    }

    public void setSSECustomerKey(SSECustomerKey sseKey) {
        this.sseCustomerKey = sseKey;
    }

    public InitiateMultipartUploadRequest withSSECustomerKey(SSECustomerKey sseKey) {
        setSSECustomerKey(sseKey);
        return this;
    }
}
