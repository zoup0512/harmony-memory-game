package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompleteMultipartUploadRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private String key;
    private List<PartETag> partETags = new ArrayList();
    private String uploadId;

    public CompleteMultipartUploadRequest(String bucketName, String key, String uploadId, List<PartETag> partETags) {
        this.bucketName = bucketName;
        this.key = key;
        this.uploadId = uploadId;
        this.partETags = partETags;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public CompleteMultipartUploadRequest withBucketName(String bucketName) {
        this.bucketName = bucketName;
        return this;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public CompleteMultipartUploadRequest withKey(String key) {
        this.key = key;
        return this;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public CompleteMultipartUploadRequest withUploadId(String uploadId) {
        this.uploadId = uploadId;
        return this;
    }

    public List<PartETag> getPartETags() {
        return this.partETags;
    }

    public void setPartETags(List<PartETag> partETags) {
        this.partETags = partETags;
    }

    public CompleteMultipartUploadRequest withPartETags(List<PartETag> partETags) {
        setPartETags(partETags);
        return this;
    }

    public CompleteMultipartUploadRequest withPartETags(UploadPartResult... uploadPartResults) {
        for (UploadPartResult result : uploadPartResults) {
            this.partETags.add(new PartETag(result.getPartNumber(), result.getETag()));
        }
        return this;
    }

    public CompleteMultipartUploadRequest withPartETags(Collection<UploadPartResult> uploadPartResultsCollection) {
        for (UploadPartResult result : uploadPartResultsCollection) {
            this.partETags.add(new PartETag(result.getPartNumber(), result.getETag()));
        }
        return this;
    }
}
