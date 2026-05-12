package com.amazonaws.services.s3.model;

import com.amazonaws.services.s3.internal.ObjectExpirationResult;
import com.amazonaws.services.s3.internal.SSEResultBase;
import java.util.Date;

public class CompleteMultipartUploadResult extends SSEResultBase implements ObjectExpirationResult {
    private String bucketName;
    private String eTag;
    private Date expirationTime;
    private String expirationTimeRuleId;
    private String key;
    private String location;
    private String versionId;

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

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

    public String getETag() {
        return this.eTag;
    }

    public void setETag(String etag) {
        this.eTag = etag;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public Date getExpirationTime() {
        return this.expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getExpirationTimeRuleId() {
        return this.expirationTimeRuleId;
    }

    public void setExpirationTimeRuleId(String expirationTimeRuleId) {
        this.expirationTimeRuleId = expirationTimeRuleId;
    }
}
