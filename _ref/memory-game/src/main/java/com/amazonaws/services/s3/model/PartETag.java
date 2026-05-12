package com.amazonaws.services.s3.model;

public class PartETag {
    private String eTag;
    private int partNumber;

    public PartETag(int partNumber, String eTag) {
        this.partNumber = partNumber;
        this.eTag = eTag;
    }

    public int getPartNumber() {
        return this.partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public PartETag withPartNumber(int partNumber) {
        this.partNumber = partNumber;
        return this;
    }

    public String getETag() {
        return this.eTag;
    }

    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    public PartETag withETag(String eTag) {
        this.eTag = eTag;
        return this;
    }
}
