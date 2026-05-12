package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CopyObjectRequest extends AmazonWebServiceRequest implements S3AccelerateUnsupported {
    private AccessControlList accessControlList;
    private CannedAccessControlList cannedACL;
    private String destinationBucketName;
    private String destinationKey;
    private SSECustomerKey destinationSSECustomerKey;
    private List<String> matchingETagConstraints;
    private Date modifiedSinceConstraint;
    private ObjectMetadata newObjectMetadata;
    private List<String> nonmatchingEtagConstraints;
    private String redirectLocation;
    private String sourceBucketName;
    private String sourceKey;
    private SSECustomerKey sourceSSECustomerKey;
    private String sourceVersionId;
    private String storageClass;
    private Date unmodifiedSinceConstraint;

    public CopyObjectRequest(String sourceBucketName, String sourceKey, String destinationBucketName, String destinationKey) {
        this(sourceBucketName, sourceKey, null, destinationBucketName, destinationKey);
    }

    public CopyObjectRequest(String sourceBucketName, String sourceKey, String sourceVersionId, String destinationBucketName, String destinationKey) {
        this.matchingETagConstraints = new ArrayList();
        this.nonmatchingEtagConstraints = new ArrayList();
        this.sourceBucketName = sourceBucketName;
        this.sourceKey = sourceKey;
        this.sourceVersionId = sourceVersionId;
        this.destinationBucketName = destinationBucketName;
        this.destinationKey = destinationKey;
    }

    public String getSourceBucketName() {
        return this.sourceBucketName;
    }

    public void setSourceBucketName(String sourceBucketName) {
        this.sourceBucketName = sourceBucketName;
    }

    public CopyObjectRequest withSourceBucketName(String sourceBucketName) {
        setSourceBucketName(sourceBucketName);
        return this;
    }

    public String getSourceKey() {
        return this.sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    public CopyObjectRequest withSourceKey(String sourceKey) {
        setSourceKey(sourceKey);
        return this;
    }

    public String getSourceVersionId() {
        return this.sourceVersionId;
    }

    public void setSourceVersionId(String sourceVersionId) {
        this.sourceVersionId = sourceVersionId;
    }

    public CopyObjectRequest withSourceVersionId(String sourceVersionId) {
        setSourceVersionId(sourceVersionId);
        return this;
    }

    public String getDestinationBucketName() {
        return this.destinationBucketName;
    }

    public void setDestinationBucketName(String destinationBucketName) {
        this.destinationBucketName = destinationBucketName;
    }

    public CopyObjectRequest withDestinationBucketName(String destinationBucketName) {
        setDestinationBucketName(destinationBucketName);
        return this;
    }

    public String getDestinationKey() {
        return this.destinationKey;
    }

    public void setDestinationKey(String destinationKey) {
        this.destinationKey = destinationKey;
    }

    public CopyObjectRequest withDestinationKey(String destinationKey) {
        setDestinationKey(destinationKey);
        return this;
    }

    public String getStorageClass() {
        return this.storageClass;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    public CopyObjectRequest withStorageClass(String storageClass) {
        setStorageClass(storageClass);
        return this;
    }

    public void setStorageClass(StorageClass storageClass) {
        this.storageClass = storageClass.toString();
    }

    public CopyObjectRequest withStorageClass(StorageClass storageClass) {
        setStorageClass(storageClass);
        return this;
    }

    public CannedAccessControlList getCannedAccessControlList() {
        return this.cannedACL;
    }

    public void setCannedAccessControlList(CannedAccessControlList cannedACL) {
        this.cannedACL = cannedACL;
    }

    public CopyObjectRequest withCannedAccessControlList(CannedAccessControlList cannedACL) {
        setCannedAccessControlList(cannedACL);
        return this;
    }

    public AccessControlList getAccessControlList() {
        return this.accessControlList;
    }

    public void setAccessControlList(AccessControlList accessControlList) {
        this.accessControlList = accessControlList;
    }

    public CopyObjectRequest withAccessControlList(AccessControlList accessControlList) {
        setAccessControlList(accessControlList);
        return this;
    }

    public ObjectMetadata getNewObjectMetadata() {
        return this.newObjectMetadata;
    }

    public void setNewObjectMetadata(ObjectMetadata newObjectMetadata) {
        this.newObjectMetadata = newObjectMetadata;
    }

    public CopyObjectRequest withNewObjectMetadata(ObjectMetadata newObjectMetadata) {
        setNewObjectMetadata(newObjectMetadata);
        return this;
    }

    public List<String> getMatchingETagConstraints() {
        return this.matchingETagConstraints;
    }

    public void setMatchingETagConstraints(List<String> eTagList) {
        this.matchingETagConstraints = eTagList;
    }

    public CopyObjectRequest withMatchingETagConstraint(String eTag) {
        this.matchingETagConstraints.add(eTag);
        return this;
    }

    public List<String> getNonmatchingETagConstraints() {
        return this.nonmatchingEtagConstraints;
    }

    public void setNonmatchingETagConstraints(List<String> eTagList) {
        this.nonmatchingEtagConstraints = eTagList;
    }

    public CopyObjectRequest withNonmatchingETagConstraint(String eTag) {
        this.nonmatchingEtagConstraints.add(eTag);
        return this;
    }

    public Date getUnmodifiedSinceConstraint() {
        return this.unmodifiedSinceConstraint;
    }

    public void setUnmodifiedSinceConstraint(Date date) {
        this.unmodifiedSinceConstraint = date;
    }

    public CopyObjectRequest withUnmodifiedSinceConstraint(Date date) {
        setUnmodifiedSinceConstraint(date);
        return this;
    }

    public Date getModifiedSinceConstraint() {
        return this.modifiedSinceConstraint;
    }

    public void setModifiedSinceConstraint(Date date) {
        this.modifiedSinceConstraint = date;
    }

    public CopyObjectRequest withModifiedSinceConstraint(Date date) {
        setModifiedSinceConstraint(date);
        return this;
    }

    public void setRedirectLocation(String redirectLocation) {
        this.redirectLocation = redirectLocation;
    }

    public String getRedirectLocation() {
        return this.redirectLocation;
    }

    public CopyObjectRequest withRedirectLocation(String redirectLocation) {
        this.redirectLocation = redirectLocation;
        return this;
    }

    public SSECustomerKey getSourceSSECustomerKey() {
        return this.sourceSSECustomerKey;
    }

    public void setSourceSSECustomerKey(SSECustomerKey sseKey) {
        this.sourceSSECustomerKey = sseKey;
    }

    public CopyObjectRequest withSourceSSECustomerKey(SSECustomerKey sseKey) {
        setSourceSSECustomerKey(sseKey);
        return this;
    }

    public SSECustomerKey getDestinationSSECustomerKey() {
        return this.destinationSSECustomerKey;
    }

    public void setDestinationSSECustomerKey(SSECustomerKey sseKey) {
        this.destinationSSECustomerKey = sseKey;
    }

    public CopyObjectRequest withDestinationSSECustomerKey(SSECustomerKey sseKey) {
        setDestinationSSECustomerKey(sseKey);
        return this;
    }
}
