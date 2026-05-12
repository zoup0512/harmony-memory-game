package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class ListVersionsRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private String delimiter;
    private String encodingType;
    private String keyMarker;
    private Integer maxResults;
    private String prefix;
    private String versionIdMarker;

    public ListVersionsRequest(String bucketName, String prefix, String keyMarker, String versionIdMarker, String delimiter, Integer maxResults) {
        setBucketName(bucketName);
        setPrefix(prefix);
        setKeyMarker(keyMarker);
        setVersionIdMarker(versionIdMarker);
        setDelimiter(delimiter);
        setMaxResults(maxResults);
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public ListVersionsRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public ListVersionsRequest withPrefix(String prefix) {
        setPrefix(prefix);
        return this;
    }

    public String getKeyMarker() {
        return this.keyMarker;
    }

    public void setKeyMarker(String keyMarker) {
        this.keyMarker = keyMarker;
    }

    public ListVersionsRequest withKeyMarker(String keyMarker) {
        setKeyMarker(keyMarker);
        return this;
    }

    public String getVersionIdMarker() {
        return this.versionIdMarker;
    }

    public void setVersionIdMarker(String versionIdMarker) {
        this.versionIdMarker = versionIdMarker;
    }

    public ListVersionsRequest withVersionIdMarker(String versionIdMarker) {
        setVersionIdMarker(versionIdMarker);
        return this;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public ListVersionsRequest withDelimiter(String delimiter) {
        setDelimiter(delimiter);
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public ListVersionsRequest withMaxResults(Integer maxResults) {
        setMaxResults(maxResults);
        return this;
    }

    public String getEncodingType() {
        return this.encodingType;
    }

    public void setEncodingType(String encodingType) {
        this.encodingType = encodingType;
    }

    public ListVersionsRequest withEncodingType(String encodingType) {
        setEncodingType(encodingType);
        return this;
    }
}
