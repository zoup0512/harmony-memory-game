package com.amazonaws.services.s3.model;

public class BucketAccelerateConfiguration {
    private String status;

    public BucketAccelerateConfiguration(String status) {
        setStatus(status);
    }

    public BucketAccelerateConfiguration(BucketAccelerateStatus status) {
        setStatus(status);
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatus(BucketAccelerateStatus status) {
        setStatus(status.toString());
    }

    public BucketAccelerateConfiguration withStatus(String status) {
        setStatus(status);
        return this;
    }

    public BucketAccelerateConfiguration withStatus(BucketAccelerateStatus status) {
        setStatus(status);
        return this;
    }

    public boolean isAccelerateEnabled() {
        return BucketAccelerateStatus.Enabled.toString().equals(getStatus());
    }
}
