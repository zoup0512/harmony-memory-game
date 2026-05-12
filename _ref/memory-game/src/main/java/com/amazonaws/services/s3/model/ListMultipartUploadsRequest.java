package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class ListMultipartUploadsRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private String delimiter;
    private String encodingType;
    private String keyMarker;
    private Integer maxUploads;
    private String prefix;
    private String uploadIdMarker;

    public ListMultipartUploadsRequest(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public ListMultipartUploadsRequest withBucketName(String bucketName) {
        this.bucketName = bucketName;
        return this;
    }

    public Integer getMaxUploads() {
        return this.maxUploads;
    }

    public void setMaxUploads(Integer maxUploads) {
        this.maxUploads = maxUploads;
    }

    public ListMultipartUploadsRequest withMaxUploads(int maxUploadsInt) {
        this.maxUploads = Integer.valueOf(maxUploadsInt);
        return this;
    }

    public String getKeyMarker() {
        return this.keyMarker;
    }

    public void setKeyMarker(String keyMarker) {
        this.keyMarker = keyMarker;
    }

    public ListMultipartUploadsRequest withKeyMarker(String keyMarker) {
        this.keyMarker = keyMarker;
        return this;
    }

    public String getUploadIdMarker() {
        return this.uploadIdMarker;
    }

    public void setUploadIdMarker(String uploadIdMarker) {
        this.uploadIdMarker = uploadIdMarker;
    }

    public ListMultipartUploadsRequest withUploadIdMarker(String uploadIdMarker) {
        this.uploadIdMarker = uploadIdMarker;
        return this;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public ListMultipartUploadsRequest withDelimiter(String delimiter) {
        setDelimiter(delimiter);
        return this;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public ListMultipartUploadsRequest withPrefix(String prefix) {
        setPrefix(prefix);
        return this;
    }

    public String getEncodingType() {
        return this.encodingType;
    }

    public void setEncodingType(String encodingType) {
        this.encodingType = encodingType;
    }

    public ListMultipartUploadsRequest withEncodingType(String encodingType) {
        setEncodingType(encodingType);
        return this;
    }
}
