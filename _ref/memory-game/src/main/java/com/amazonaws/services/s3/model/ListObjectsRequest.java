package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class ListObjectsRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private String delimiter;
    private String encodingType;
    private String marker;
    private Integer maxKeys;
    private String prefix;

    public ListObjectsRequest(String bucketName, String prefix, String marker, String delimiter, Integer maxKeys) {
        setBucketName(bucketName);
        setPrefix(prefix);
        setMarker(marker);
        setDelimiter(delimiter);
        setMaxKeys(maxKeys);
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public ListObjectsRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public ListObjectsRequest withPrefix(String prefix) {
        setPrefix(prefix);
        return this;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public ListObjectsRequest withMarker(String marker) {
        setMarker(marker);
        return this;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public ListObjectsRequest withDelimiter(String delimiter) {
        setDelimiter(delimiter);
        return this;
    }

    public Integer getMaxKeys() {
        return this.maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    public ListObjectsRequest withMaxKeys(Integer maxKeys) {
        setMaxKeys(maxKeys);
        return this;
    }

    public String getEncodingType() {
        return this.encodingType;
    }

    public void setEncodingType(String encodingType) {
        this.encodingType = encodingType;
    }

    public ListObjectsRequest withEncodingType(String encodingType) {
        setEncodingType(encodingType);
        return this;
    }
}
