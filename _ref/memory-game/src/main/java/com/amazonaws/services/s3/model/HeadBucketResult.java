package com.amazonaws.services.s3.model;

public class HeadBucketResult {
    private String bucketRegion;

    public String getBucketRegion() {
        return this.bucketRegion;
    }

    public void setBucketRegion(String bucketRegion) {
        this.bucketRegion = bucketRegion;
    }

    public HeadBucketResult withBucketRegion(String bucketRegion) {
        setBucketRegion(bucketRegion);
        return this;
    }
}
