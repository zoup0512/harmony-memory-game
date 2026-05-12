package com.amazonaws.services.s3.model;

import com.amazonaws.services.s3.internal.ObjectExpirationResult;
import com.amazonaws.services.s3.internal.SSEResultBase;
import java.util.Date;

public class CopyObjectResult extends SSEResultBase implements ObjectExpirationResult {
    private String etag;
    private Date expirationTime;
    private String expirationTimeRuleId;
    private Date lastModifiedDate;
    private String versionId;

    public String getETag() {
        return this.etag;
    }

    public void setETag(String etag) {
        this.etag = etag;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
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
