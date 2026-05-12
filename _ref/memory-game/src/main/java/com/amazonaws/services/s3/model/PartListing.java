package com.amazonaws.services.s3.model;

import java.util.ArrayList;
import java.util.List;

public class PartListing {
    private String bucketName;
    private String encodingType;
    private Owner initiator;
    private boolean isTruncated;
    private String key;
    private Integer maxParts;
    private Integer nextPartNumberMarker;
    private Owner owner;
    private Integer partNumberMarker;
    private List<PartSummary> parts;
    private String storageClass;
    private String uploadId;

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

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

    public Integer getPartNumberMarker() {
        return this.partNumberMarker;
    }

    public void setPartNumberMarker(int partNumberMarker) {
        this.partNumberMarker = Integer.valueOf(partNumberMarker);
    }

    public Integer getNextPartNumberMarker() {
        return this.nextPartNumberMarker;
    }

    public void setNextPartNumberMarker(int nextPartNumberMarker) {
        this.nextPartNumberMarker = Integer.valueOf(nextPartNumberMarker);
    }

    public Integer getMaxParts() {
        return this.maxParts;
    }

    public void setMaxParts(int maxParts) {
        this.maxParts = Integer.valueOf(maxParts);
    }

    public String getEncodingType() {
        return this.encodingType;
    }

    public void setEncodingType(String encodingType) {
        this.encodingType = encodingType;
    }

    public boolean isTruncated() {
        return this.isTruncated;
    }

    public void setTruncated(boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    public List<PartSummary> getParts() {
        if (this.parts == null) {
            this.parts = new ArrayList();
        }
        return this.parts;
    }

    public void setParts(List<PartSummary> parts) {
        this.parts = parts;
    }
}
