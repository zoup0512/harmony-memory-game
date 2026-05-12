package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.event.ProgressListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetObjectRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private ProgressListener generalProgressListener;
    private boolean isRequesterPays;
    private String key;
    private List<String> matchingETagConstraints;
    private Date modifiedSinceConstraint;
    private List<String> nonmatchingEtagConstraints;
    private long[] range;
    private ResponseHeaderOverrides responseHeaders;
    private SSECustomerKey sseCustomerKey;
    private Date unmodifiedSinceConstraint;
    private String versionId;

    public GetObjectRequest(String bucketName, String key) {
        this(bucketName, key, null);
    }

    public GetObjectRequest(String bucketName, String key, String versionId) {
        this.matchingETagConstraints = new ArrayList();
        this.nonmatchingEtagConstraints = new ArrayList();
        setBucketName(bucketName);
        setKey(key);
        setVersionId(versionId);
        setRequesterPays(false);
    }

    public GetObjectRequest(String bucketName, String key, boolean isRequesterPays) {
        this.matchingETagConstraints = new ArrayList();
        this.nonmatchingEtagConstraints = new ArrayList();
        this.bucketName = bucketName;
        this.key = key;
        this.isRequesterPays = isRequesterPays;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public GetObjectRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public GetObjectRequest withKey(String key) {
        setKey(key);
        return this;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public GetObjectRequest withVersionId(String versionId) {
        setVersionId(versionId);
        return this;
    }

    public long[] getRange() {
        return this.range == null ? null : (long[]) this.range.clone();
    }

    public void setRange(long start, long end) {
        this.range = new long[]{start, end};
    }

    public GetObjectRequest withRange(long start, long end) {
        setRange(start, end);
        return this;
    }

    public List<String> getMatchingETagConstraints() {
        return this.matchingETagConstraints;
    }

    public void setMatchingETagConstraints(List<String> eTagList) {
        this.matchingETagConstraints = eTagList;
    }

    public GetObjectRequest withMatchingETagConstraint(String eTag) {
        this.matchingETagConstraints.add(eTag);
        return this;
    }

    public List<String> getNonmatchingETagConstraints() {
        return this.nonmatchingEtagConstraints;
    }

    public void setNonmatchingETagConstraints(List<String> eTagList) {
        this.nonmatchingEtagConstraints = eTagList;
    }

    public GetObjectRequest withNonmatchingETagConstraint(String eTag) {
        this.nonmatchingEtagConstraints.add(eTag);
        return this;
    }

    public Date getUnmodifiedSinceConstraint() {
        return this.unmodifiedSinceConstraint;
    }

    public void setUnmodifiedSinceConstraint(Date date) {
        this.unmodifiedSinceConstraint = date;
    }

    public GetObjectRequest withUnmodifiedSinceConstraint(Date date) {
        setUnmodifiedSinceConstraint(date);
        return this;
    }

    public Date getModifiedSinceConstraint() {
        return this.modifiedSinceConstraint;
    }

    public void setModifiedSinceConstraint(Date date) {
        this.modifiedSinceConstraint = date;
    }

    public GetObjectRequest withModifiedSinceConstraint(Date date) {
        setModifiedSinceConstraint(date);
        return this;
    }

    public ResponseHeaderOverrides getResponseHeaders() {
        return this.responseHeaders;
    }

    public void setResponseHeaders(ResponseHeaderOverrides responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public GetObjectRequest withResponseHeaders(ResponseHeaderOverrides responseHeaders) {
        setResponseHeaders(responseHeaders);
        return this;
    }

    @Deprecated
    public void setProgressListener(ProgressListener progressListener) {
        this.generalProgressListener = new LegacyS3ProgressListener(progressListener);
    }

    @Deprecated
    public ProgressListener getProgressListener() {
        if (this.generalProgressListener instanceof LegacyS3ProgressListener) {
            return ((LegacyS3ProgressListener) this.generalProgressListener).unwrap();
        }
        return null;
    }

    @Deprecated
    public GetObjectRequest withProgressListener(ProgressListener progressListener) {
        setProgressListener(progressListener);
        return this;
    }

    public void setGeneralProgressListener(ProgressListener generalProgressListener) {
        this.generalProgressListener = generalProgressListener;
    }

    public ProgressListener getGeneralProgressListener() {
        return this.generalProgressListener;
    }

    public GetObjectRequest withGeneralProgressListener(ProgressListener progressListener) {
        setGeneralProgressListener(progressListener);
        return this;
    }

    public boolean isRequesterPays() {
        return this.isRequesterPays;
    }

    public void setRequesterPays(boolean isRequesterPays) {
        this.isRequesterPays = isRequesterPays;
    }

    public SSECustomerKey getSSECustomerKey() {
        return this.sseCustomerKey;
    }

    public void setSSECustomerKey(SSECustomerKey sseKey) {
        this.sseCustomerKey = sseKey;
    }

    public GetObjectRequest withSSECustomerKey(SSECustomerKey sseKey) {
        setSSECustomerKey(sseKey);
        return this;
    }
}
