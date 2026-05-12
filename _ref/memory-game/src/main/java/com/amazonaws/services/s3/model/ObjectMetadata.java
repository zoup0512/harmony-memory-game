package com.amazonaws.services.s3.model;

import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.internal.ObjectExpirationResult;
import com.amazonaws.services.s3.internal.ObjectRestoreResult;
import com.amazonaws.services.s3.internal.ServerSideEncryptionResult;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ObjectMetadata implements ServerSideEncryptionResult, ObjectExpirationResult, ObjectRestoreResult, Cloneable {
    public static final String AES_256_SERVER_SIDE_ENCRYPTION = "AES256";
    public static final String KMS_SERVER_SIDE_ENCRYPTION = "aws:kms";
    private Date expirationTime;
    private String expirationTimeRuleId;
    private Date httpExpiresDate;
    private Map<String, Object> metadata;
    private Boolean ongoingRestore;
    private Date restoreExpirationTime;
    private Map<String, String> userMetadata;

    public Map<String, String> getUserMetadata() {
        return this.userMetadata;
    }

    public void setUserMetadata(Map<String, String> userMetadata) {
        this.userMetadata = userMetadata;
    }

    public void setHeader(String key, Object value) {
        this.metadata.put(key, value);
    }

    public void addUserMetadata(String key, String value) {
        this.userMetadata.put(key, value);
    }

    public Map<String, Object> getRawMetadata() {
        return Collections.unmodifiableMap(new HashMap(this.metadata));
    }

    public Object getRawMetadataValue(String key) {
        return this.metadata.get(key);
    }

    public Date getLastModified() {
        return (Date) this.metadata.get("Last-Modified");
    }

    public void setLastModified(Date lastModified) {
        this.metadata.put("Last-Modified", lastModified);
    }

    public long getContentLength() {
        Long contentLength = (Long) this.metadata.get("Content-Length");
        if (contentLength == null) {
            return 0;
        }
        return contentLength.longValue();
    }

    public long getInstanceLength() {
        String contentRange = (String) this.metadata.get(Headers.CONTENT_RANGE);
        if (contentRange != null) {
            int pos = contentRange.lastIndexOf("/");
            if (pos >= 0) {
                return Long.parseLong(contentRange.substring(pos + 1));
            }
        }
        return getContentLength();
    }

    public void setContentLength(long contentLength) {
        this.metadata.put("Content-Length", Long.valueOf(contentLength));
    }

    public String getContentType() {
        return (String) this.metadata.get("Content-Type");
    }

    public void setContentType(String contentType) {
        this.metadata.put("Content-Type", contentType);
    }

    public String getContentEncoding() {
        return (String) this.metadata.get("Content-Encoding");
    }

    public void setContentEncoding(String encoding) {
        this.metadata.put("Content-Encoding", encoding);
    }

    public String getCacheControl() {
        return (String) this.metadata.get("Cache-Control");
    }

    public void setCacheControl(String cacheControl) {
        this.metadata.put("Cache-Control", cacheControl);
    }

    public void setContentMD5(String md5Base64) {
        if (md5Base64 == null) {
            this.metadata.remove(Headers.CONTENT_MD5);
        } else {
            this.metadata.put(Headers.CONTENT_MD5, md5Base64);
        }
    }

    public String getContentMD5() {
        return (String) this.metadata.get(Headers.CONTENT_MD5);
    }

    public void setContentDisposition(String disposition) {
        this.metadata.put(Headers.CONTENT_DISPOSITION, disposition);
    }

    public String getContentDisposition() {
        return (String) this.metadata.get(Headers.CONTENT_DISPOSITION);
    }

    public String getETag() {
        return (String) this.metadata.get("ETag");
    }

    public String getVersionId() {
        return (String) this.metadata.get(Headers.S3_VERSION_ID);
    }

    public String getSSEAlgorithm() {
        return (String) this.metadata.get(Headers.SERVER_SIDE_ENCRYPTION);
    }

    public String getSSEKMSKeyId() {
        return (String) this.metadata.get(Headers.SERVER_SIDE_ENCRYPTION_KMS_KEY_ID);
    }

    @Deprecated
    public String getServerSideEncryption() {
        return (String) this.metadata.get(Headers.SERVER_SIDE_ENCRYPTION);
    }

    public void setSSEAlgorithm(String algorithm) {
        this.metadata.put(Headers.SERVER_SIDE_ENCRYPTION, algorithm);
    }

    public void setSSEKMSKeyId(String kmsKeyId) {
        this.metadata.put(Headers.SERVER_SIDE_ENCRYPTION_KMS_KEY_ID, kmsKeyId);
    }

    @Deprecated
    public void setServerSideEncryption(String algorithm) {
        this.metadata.put(Headers.SERVER_SIDE_ENCRYPTION, algorithm);
    }

    public String getSSECustomerAlgorithm() {
        return (String) this.metadata.get(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_ALGORITHM);
    }

    public void setSSECustomerAlgorithm(String algorithm) {
        this.metadata.put(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_ALGORITHM, algorithm);
    }

    public String getSSECustomerKeyMd5() {
        return (String) this.metadata.get(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_KEY_MD5);
    }

    public void setSSECustomerKeyMd5(String md5Digest) {
        this.metadata.put(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_KEY_MD5, md5Digest);
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

    public Date getRestoreExpirationTime() {
        return this.restoreExpirationTime;
    }

    public void setRestoreExpirationTime(Date restoreExpirationTime) {
        this.restoreExpirationTime = restoreExpirationTime;
    }

    public void setOngoingRestore(boolean ongoingRestore) {
        this.ongoingRestore = Boolean.valueOf(ongoingRestore);
    }

    public Boolean getOngoingRestore() {
        return this.ongoingRestore;
    }

    public void setHttpExpiresDate(Date httpExpiresDate) {
        this.httpExpiresDate = httpExpiresDate;
    }

    public Date getHttpExpiresDate() {
        return this.httpExpiresDate;
    }

    public String getUserMetaDataOf(String key) {
        return this.userMetadata == null ? null : (String) this.userMetadata.get(key);
    }

    public ObjectMetadata() {
        this.userMetadata = new HashMap();
        this.metadata = new HashMap();
    }

    private ObjectMetadata(ObjectMetadata from) {
        Map map = null;
        this.userMetadata = from.userMetadata == null ? null : new HashMap(from.userMetadata);
        if (from.metadata != null) {
            map = new HashMap(from.metadata);
        }
        this.metadata = map;
        this.expirationTime = from.expirationTime;
        this.expirationTimeRuleId = from.expirationTimeRuleId;
        this.httpExpiresDate = from.httpExpiresDate;
        this.ongoingRestore = from.ongoingRestore;
        this.restoreExpirationTime = from.restoreExpirationTime;
    }

    public ObjectMetadata clone() {
        return new ObjectMetadata(this);
    }
}
