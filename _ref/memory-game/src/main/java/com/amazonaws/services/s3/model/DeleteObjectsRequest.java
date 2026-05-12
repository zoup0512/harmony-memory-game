package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.util.ArrayList;
import java.util.List;

public class DeleteObjectsRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private final List<KeyVersion> keys = new ArrayList();
    private MultiFactorAuthentication mfa;
    private boolean quiet;

    public static class KeyVersion {
        private final String key;
        private final String version;

        public KeyVersion(String key) {
            this(key, null);
        }

        public KeyVersion(String key, String version) {
            this.key = key;
            this.version = version;
        }

        public String getKey() {
            return this.key;
        }

        public String getVersion() {
            return this.version;
        }
    }

    public DeleteObjectsRequest(String bucketName) {
        setBucketName(bucketName);
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public DeleteObjectsRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public MultiFactorAuthentication getMfa() {
        return this.mfa;
    }

    public void setMfa(MultiFactorAuthentication mfa) {
        this.mfa = mfa;
    }

    public DeleteObjectsRequest withMfa(MultiFactorAuthentication mfa) {
        setMfa(mfa);
        return this;
    }

    public void setQuiet(boolean quiet) {
        this.quiet = quiet;
    }

    public boolean getQuiet() {
        return this.quiet;
    }

    public DeleteObjectsRequest withQuiet(boolean quiet) {
        setQuiet(quiet);
        return this;
    }

    public void setKeys(List<KeyVersion> keys) {
        this.keys.clear();
        this.keys.addAll(keys);
    }

    public DeleteObjectsRequest withKeys(List<KeyVersion> keys) {
        setKeys(keys);
        return this;
    }

    public List<KeyVersion> getKeys() {
        return this.keys;
    }

    public DeleteObjectsRequest withKeys(String... keys) {
        List<KeyVersion> keyVersions = new ArrayList(keys.length);
        for (String key : keys) {
            keyVersions.add(new KeyVersion(key));
        }
        setKeys(keyVersions);
        return this;
    }
}
