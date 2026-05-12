package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CopyPartRequest extends AmazonWebServiceRequest implements S3AccelerateUnsupported {
    private String destinationBucketName;
    private String destinationKey;
    private SSECustomerKey destinationSSECustomerKey;
    private Long firstByte;
    private Long lastByte;
    private final List<String> matchingETagConstraints = new ArrayList();
    private Date modifiedSinceConstraint;
    private final List<String> nonmatchingEtagConstraints = new ArrayList();
    private int partNumber;
    private String sourceBucketName;
    private String sourceKey;
    private SSECustomerKey sourceSSECustomerKey;
    private String sourceVersionId;
    private Date unmodifiedSinceConstraint;
    private String uploadId;

    public String getUploadId() {
        return this.uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public CopyPartRequest withUploadId(String uploadId) {
        this.uploadId = uploadId;
        return this;
    }

    public int getPartNumber() {
        return this.partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public CopyPartRequest withPartNumber(int partNumber) {
        this.partNumber = partNumber;
        return this;
    }

    public String getSourceBucketName() {
        return this.sourceBucketName;
    }

    public void setSourceBucketName(String sourceBucketName) {
        this.sourceBucketName = sourceBucketName;
    }

    public CopyPartRequest withSourceBucketName(String sourceBucketName) {
        this.sourceBucketName = sourceBucketName;
        return this;
    }

    public String getSourceKey() {
        return this.sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    public CopyPartRequest withSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
        return this;
    }

    public String getSourceVersionId() {
        return this.sourceVersionId;
    }

    public void setSourceVersionId(String sourceVersionId) {
        this.sourceVersionId = sourceVersionId;
    }

    public CopyPartRequest withSourceVersionId(String sourceVersionId) {
        this.sourceVersionId = sourceVersionId;
        return this;
    }

    public String getDestinationBucketName() {
        return this.destinationBucketName;
    }

    public void setDestinationBucketName(String destinationBucketName) {
        this.destinationBucketName = destinationBucketName;
    }

    public CopyPartRequest withDestinationBucketName(String destinationBucketName) {
        setDestinationBucketName(destinationBucketName);
        return this;
    }

    public String getDestinationKey() {
        return this.destinationKey;
    }

    public void setDestinationKey(String destinationKey) {
        this.destinationKey = destinationKey;
    }

    public CopyPartRequest withDestinationKey(String destinationKey) {
        setDestinationKey(destinationKey);
        return this;
    }

    public Long getFirstByte() {
        return this.firstByte;
    }

    public void setFirstByte(Long firstByte) {
        this.firstByte = firstByte;
    }

    public CopyPartRequest withFirstByte(Long firstByte) {
        this.firstByte = firstByte;
        return this;
    }

    public Long getLastByte() {
        return this.lastByte;
    }

    public void setLastByte(Long lastByte) {
        this.lastByte = lastByte;
    }

    public CopyPartRequest withLastByte(Long lastByte) {
        this.lastByte = lastByte;
        return this;
    }

    public List<String> getMatchingETagConstraints() {
        return this.matchingETagConstraints;
    }

    public void setMatchingETagConstraints(List<String> eTagList) {
        this.matchingETagConstraints.clear();
        this.matchingETagConstraints.addAll(eTagList);
    }

    public CopyPartRequest withMatchingETagConstraint(String eTag) {
        this.matchingETagConstraints.add(eTag);
        return this;
    }

    public List<String> getNonmatchingETagConstraints() {
        return this.nonmatchingEtagConstraints;
    }

    public void setNonmatchingETagConstraints(List<String> eTagList) {
        this.nonmatchingEtagConstraints.clear();
        this.nonmatchingEtagConstraints.addAll(eTagList);
    }

    public CopyPartRequest withNonmatchingETagConstraint(String eTag) {
        this.nonmatchingEtagConstraints.add(eTag);
        return this;
    }

    public Date getUnmodifiedSinceConstraint() {
        return this.unmodifiedSinceConstraint;
    }

    public void setUnmodifiedSinceConstraint(Date date) {
        this.unmodifiedSinceConstraint = date;
    }

    public CopyPartRequest withUnmodifiedSinceConstraint(Date date) {
        setUnmodifiedSinceConstraint(date);
        return this;
    }

    public Date getModifiedSinceConstraint() {
        return this.modifiedSinceConstraint;
    }

    public void setModifiedSinceConstraint(Date date) {
        this.modifiedSinceConstraint = date;
    }

    public CopyPartRequest withModifiedSinceConstraint(Date date) {
        setModifiedSinceConstraint(date);
        return this;
    }

    public SSECustomerKey getSourceSSECustomerKey() {
        return this.sourceSSECustomerKey;
    }

    public void setSourceSSECustomerKey(SSECustomerKey sseKey) {
        this.sourceSSECustomerKey = sseKey;
    }

    public CopyPartRequest withSourceSSECustomerKey(SSECustomerKey sseKey) {
        setSourceSSECustomerKey(sseKey);
        return this;
    }

    public SSECustomerKey getDestinationSSECustomerKey() {
        return this.destinationSSECustomerKey;
    }

    public void setDestinationSSECustomerKey(SSECustomerKey sseKey) {
        this.destinationSSECustomerKey = sseKey;
    }

    public CopyPartRequest withDestinationSSECustomerKey(SSECustomerKey sseKey) {
        setDestinationSSECustomerKey(sseKey);
        return this;
    }
}
