package com.amazonaws.services.s3.model;

public class ReplicationDestinationConfig {
    private String bucketARN;
    private String storageClass;

    public String getBucketARN() {
        return this.bucketARN;
    }

    public void setBucketARN(String bucketARN) {
        if (bucketARN == null) {
            throw new IllegalArgumentException("Bucket name cannot be null");
        }
        this.bucketARN = bucketARN;
    }

    public ReplicationDestinationConfig withBucketARN(String bucketARN) {
        setBucketARN(bucketARN);
        return this;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    public void setStorageClass(StorageClass storageClass) {
        setStorageClass(storageClass == null ? (String) null : storageClass.toString());
    }

    public ReplicationDestinationConfig withStorageClass(String storageClass) {
        setStorageClass(storageClass);
        return this;
    }

    public ReplicationDestinationConfig withStorageClass(StorageClass storageClass) {
        setStorageClass(storageClass == null ? (String) null : storageClass.toString());
        return this;
    }

    public String getStorageClass() {
        return this.storageClass;
    }
}
