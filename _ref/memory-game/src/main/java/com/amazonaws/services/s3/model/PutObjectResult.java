package com.amazonaws.services.s3.model;

import com.amazonaws.services.s3.internal.ObjectExpirationResult;
import com.amazonaws.services.s3.internal.SSEResultBase;
import java.util.Date;

public class PutObjectResult extends SSEResultBase implements ObjectExpirationResult {
    private String contentMd5;
    private String eTag;
    private Date expirationTime;
    private String expirationTimeRuleId;
    private String versionId;

    public String getVersionId() {
        return this.versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getETag() {
        return this.eTag;
    }

    public void setETag(String eTag) {
        this.eTag = eTag;
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

    public void setContentMd5(String contentMd5) {
        this.contentMd5 = contentMd5;
    }

    public String getContentMd5() {
        return this.contentMd5;
    }
}
