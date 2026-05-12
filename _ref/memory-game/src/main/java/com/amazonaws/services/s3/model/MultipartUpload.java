package com.amazonaws.services.s3.model;

import java.util.Date;

public class MultipartUpload {
    private Date initiated;
    private Owner initiator;
    private String key;
    private Owner owner;
    private String storageClass;
    private String uploadId;

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getInitiator() {
        return this.initiator;
    }

    public void setInitiator(Owner initiator) {
        this.initiator = initiator;
    }

    public String getStorageClass() {
        return this.storageClass;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    public Date getInitiated() {
        return this.initiated;
    }

    public void setInitiated(Date initiated) {
        this.initiated = initiated;
    }
}
