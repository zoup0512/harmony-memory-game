package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class ListObjectsV2Request extends AmazonWebServiceRequest {
    private String bucketName;
    private String continuationToken;
    private String delimiter;
    private String encodingType;
    private boolean fetchOwner;
    private Integer maxKeys;
    private String prefix;
    private String startAfter;

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public ListObjectsV2Request withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public ListObjectsV2Request withDelimiter(String delimiter) {
        setDelimiter(delimiter);
        return this;
    }

    public String getEncodingType() {
        return this.encodingType;
    }

    public void setEncodingType(String encodingType) {
        this.encodingType = encodingType;
    }

    public ListObjectsV2Request withEncodingType(String encodingType) {
        setEncodingType(encodingType);
        return this;
    }

    public Integer getMaxKeys() {
        return this.maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    public ListObjectsV2Request withMaxKeys(Integer maxKeys) {
        setMaxKeys(maxKeys);
        return this;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public ListObjectsV2Request withPrefix(String prefix) {
        setPrefix(prefix);
        return this;
    }

    public String getContinuationToken() {
        return this.continuationToken;
    }

    public void setContinuationToken(String continuationToken) {
        this.continuationToken = continuationToken;
    }

    public ListObjectsV2Request withContinuationToken(String continuationToken) {
        setContinuationToken(continuationToken);
        return this;
    }

    public boolean isFetchOwner() {
        return this.fetchOwner;
    }

    public void setFetchOwner(boolean fetchOwner) {
        this.fetchOwner = fetchOwner;
    }

    public ListObjectsV2Request withFetchOwner(boolean fetchOwner) {
        setFetchOwner(fetchOwner);
        return this;
    }

    public String getStartAfter() {
        return this.startAfter;
    }

    public void setStartAfter(String startAfter) {
        this.startAfter = startAfter;
    }

    public ListObjectsV2Request withStartAfter(String startAfter) {
        setStartAfter(startAfter);
        return this;
    }
}
