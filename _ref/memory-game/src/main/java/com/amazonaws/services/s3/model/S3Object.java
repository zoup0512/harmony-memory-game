package com.amazonaws.services.s3.model;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class S3Object implements Closeable {
    private String bucketName = null;
    private boolean isRequesterCharged;
    private String key = null;
    private ObjectMetadata metadata = new ObjectMetadata();
    private S3ObjectInputStream objectContent;
    private String redirectLocation;

    public ObjectMetadata getObjectMetadata() {
        return this.metadata;
    }

    public void setObjectMetadata(ObjectMetadata metadata) {
        this.metadata = metadata;
    }

    public S3ObjectInputStream getObjectContent() {
        return this.objectContent;
    }

    public void setObjectContent(S3ObjectInputStream objectContent) {
        this.objectContent = objectContent;
    }

    public void setObjectContent(InputStream objectContent) {
        setObjectContent(new S3ObjectInputStream(objectContent, this.objectContent != null ? this.objectContent.getHttpRequest() : null));
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

    public String getRedirectLocation() {
        return this.redirectLocation;
    }

    public void setRedirectLocation(String redirectLocation) {
        this.redirectLocation = redirectLocation;
    }

    public String toString() {
        return "S3Object [key=" + getKey() + ",bucket=" + (this.bucketName == null ? "<Unknown>" : this.bucketName) + "]";
    }

    public void close() throws IOException {
        if (getObjectContent() != null) {
            getObjectContent().close();
        }
    }

    public boolean isRequesterCharged() {
        return this.isRequesterCharged;
    }

    public void setRequesterCharged(boolean isRequesterCharged) {
        this.isRequesterCharged = isRequesterCharged;
    }
}
