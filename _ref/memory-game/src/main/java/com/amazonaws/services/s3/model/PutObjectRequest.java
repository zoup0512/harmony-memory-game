package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.event.ProgressListener;
import java.io.File;
import java.io.InputStream;

public class PutObjectRequest extends AmazonWebServiceRequest implements Cloneable {
    private AccessControlList accessControlList;
    private String bucketName;
    private CannedAccessControlList cannedAcl;
    private File file;
    private ProgressListener generalProgressListener;
    private InputStream inputStream;
    private String key;
    private ObjectMetadata metadata;
    private String redirectLocation;
    private SSECustomerKey sseCustomerKey;
    private String storageClass;

    public PutObjectRequest(String bucketName, String key, File file) {
        this.bucketName = bucketName;
        this.key = key;
        this.file = file;
    }

    public PutObjectRequest(String bucketName, String key, String redirectLocation) {
        this.bucketName = bucketName;
        this.key = key;
        this.redirectLocation = redirectLocation;
    }

    public PutObjectRequest(String bucketName, String key, InputStream input, ObjectMetadata metadata) {
        this.bucketName = bucketName;
        this.key = key;
        this.inputStream = input;
        this.metadata = metadata;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public PutObjectRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public PutObjectRequest withKey(String key) {
        setKey(key);
        return this;
    }

    public String getStorageClass() {
        return this.storageClass;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    public PutObjectRequest withStorageClass(String storageClass) {
        setStorageClass(storageClass);
        return this;
    }

    public void setStorageClass(StorageClass storageClass) {
        this.storageClass = storageClass.toString();
    }

    public PutObjectRequest withStorageClass(StorageClass storageClass) {
        setStorageClass(storageClass);
        return this;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public PutObjectRequest withFile(File file) {
        setFile(file);
        return this;
    }

    public ObjectMetadata getMetadata() {
        return this.metadata;
    }

    public void setMetadata(ObjectMetadata metadata) {
        this.metadata = metadata;
    }

    public PutObjectRequest withMetadata(ObjectMetadata metadata) {
        setMetadata(metadata);
        return this;
    }

    public CannedAccessControlList getCannedAcl() {
        return this.cannedAcl;
    }

    public void setCannedAcl(CannedAccessControlList cannedAcl) {
        this.cannedAcl = cannedAcl;
    }

    public PutObjectRequest withCannedAcl(CannedAccessControlList cannedAcl) {
        setCannedAcl(cannedAcl);
        return this;
    }

    public AccessControlList getAccessControlList() {
        return this.accessControlList;
    }

    public void setAccessControlList(AccessControlList accessControlList) {
        this.accessControlList = accessControlList;
    }

    public PutObjectRequest withAccessControlList(AccessControlList accessControlList) {
        setAccessControlList(accessControlList);
        return this;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public PutObjectRequest withInputStream(InputStream inputStream) {
        setInputStream(inputStream);
        return this;
    }

    public void setRedirectLocation(String redirectLocation) {
        this.redirectLocation = redirectLocation;
    }

    public String getRedirectLocation() {
        return this.redirectLocation;
    }

    public PutObjectRequest withRedirectLocation(String redirectLocation) {
        this.redirectLocation = redirectLocation;
        return this;
    }

    public SSECustomerKey getSSECustomerKey() {
        return this.sseCustomerKey;
    }

    public void setSSECustomerKey(SSECustomerKey sseKey) {
        this.sseCustomerKey = sseKey;
    }

    public PutObjectRequest withSSECustomerKey(SSECustomerKey sseKey) {
        setSSECustomerKey(sseKey);
        return this;
    }

    @Deprecated
    public void setProgressListener(ProgressListener progressListener) {
        this.generalProgressListener = new LegacyS3ProgressListener(progressListener);
    }

    @Deprecated
    public ProgressListener getProgressListener() {
        if (this.generalProgressListener instanceof LegacyS3ProgressListener) {
            return ((LegacyS3ProgressListener) this.generalProgressListener).unwrap();
        }
        return null;
    }

    @Deprecated
    public PutObjectRequest withProgressListener(ProgressListener progressListener) {
        setProgressListener(progressListener);
        return this;
    }

    public void setGeneralProgressListener(ProgressListener generalProgressListener) {
        this.generalProgressListener = generalProgressListener;
    }

    public ProgressListener getGeneralProgressListener() {
        return this.generalProgressListener;
    }

    public PutObjectRequest withGeneralProgressListener(ProgressListener generalProgressListener) {
        setGeneralProgressListener(generalProgressListener);
        return this;
    }

    public PutObjectRequest clone() {
        ObjectMetadata objectMetadata;
        PutObjectRequest withInputStream = new PutObjectRequest(this.bucketName, this.key, this.redirectLocation).withAccessControlList(this.accessControlList).withCannedAcl(this.cannedAcl).withFile(this.file).withGeneralProgressListener(this.generalProgressListener).withInputStream(this.inputStream);
        if (this.metadata == null) {
            objectMetadata = null;
        } else {
            objectMetadata = this.metadata.clone();
        }
        return (PutObjectRequest) withInputStream.withMetadata(objectMetadata).withStorageClass(this.storageClass).withRequestMetricCollector(getRequestMetricCollector());
    }
}
