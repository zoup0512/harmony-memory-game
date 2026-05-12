package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class ListPartsRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private String encodingType;
    private String key;
    private Integer maxParts;
    private Integer partNumberMarker;
    private String uploadId;

    public ListPartsRequest(String bucketName, String key, String uploadId) {
        this.bucketName = bucketName;
        this.key = key;
        this.uploadId = uploadId;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public ListPartsRequest withBucketName(String bucketName) {
        this.bucketName = bucketName;
        return this;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ListPartsRequest withKey(String key) {
        this.key = key;
        return this;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public ListPartsRequest withUploadId(String uploadId) {
        this.uploadId = uploadId;
        return this;
    }

    public Integer getMaxParts() {
        return this.maxParts;
    }

    public void setMaxParts(int maxParts) {
        this.maxParts = Integer.valueOf(maxParts);
    }

    public ListPartsRequest withMaxParts(int maxParts) {
        this.maxParts = Integer.valueOf(maxParts);
        return this;
    }

    public Integer getPartNumberMarker() {
        return this.partNumberMarker;
    }

    public void setPartNumberMarker(Integer partNumberMarker) {
        this.partNumberMarker = partNumberMarker;
    }

    public ListPartsRequest withPartNumberMarker(Integer partNumberMarker) {
        this.partNumberMarker = partNumberMarker;
        return this;
    }

    public String getEncodingType() {
        return this.encodingType;
    }

    public void setEncodingType(String encodingType) {
        this.encodingType = encodingType;
    }

    public ListPartsRequest withEncodingType(String encodingType) {
        setEncodingType(encodingType);
        return this;
    }
}
