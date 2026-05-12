package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.event.ProgressListener;
import java.io.File;
import java.io.InputStream;

public class UploadPartRequest extends AmazonWebServiceRequest {
    private String bucketName;
    private File file;
    private long fileOffset;
    private ProgressListener generalProgressListener;
    private int id;
    private InputStream inputStream;
    private boolean isLastPart;
    private String key;
    private int mainUploadId;
    private String md5Digest;
    private int partNumber;
    private long partSize;
    private SSECustomerKey sseCustomerKey;
    private String uploadId;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public UploadPartRequest withId(int id) {
        this.id = id;
        return this;
    }

    public void setMainUploadId(int id) {
        this.mainUploadId = id;
    }

    public int getMainUploadId() {
        return this.mainUploadId;
    }

    public UploadPartRequest withMainUploadId(int id) {
        this.mainUploadId = id;
        return this;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public UploadPartRequest withInputStream(InputStream inputStream) {
        setInputStream(inputStream);
        return this;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public UploadPartRequest withBucketName(String bucketName) {
        this.bucketName = bucketName;
        return this;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public UploadPartRequest withKey(String key) {
        this.key = key;
        return this;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public UploadPartRequest withUploadId(String uploadId) {
        this.uploadId = uploadId;
        return this;
    }

    public int getPartNumber() {
        return this.partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public UploadPartRequest withPartNumber(int partNumber) {
        this.partNumber = partNumber;
        return this;
    }

    public long getPartSize() {
        return this.partSize;
    }

    public void setPartSize(long partSize) {
        this.partSize = partSize;
    }

    public UploadPartRequest withPartSize(long partSize) {
        this.partSize = partSize;
        return this;
    }

    public String getMd5Digest() {
        return this.md5Digest;
    }

    public void setMd5Digest(String md5Digest) {
        this.md5Digest = md5Digest;
    }

    public UploadPartRequest withMD5Digest(String md5Digest) {
        this.md5Digest = md5Digest;
        return this;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public UploadPartRequest withFile(File file) {
        setFile(file);
        return this;
    }

    public long getFileOffset() {
        return this.fileOffset;
    }

    public void setFileOffset(long fileOffset) {
        this.fileOffset = fileOffset;
    }

    public UploadPartRequest withFileOffset(long fileOffset) {
        setFileOffset(fileOffset);
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
    public UploadPartRequest withProgressListener(ProgressListener progressListener) {
        setProgressListener(progressListener);
        return this;
    }

    public boolean isLastPart() {
        return this.isLastPart;
    }

    public void setLastPart(boolean isLastPart) {
        this.isLastPart = isLastPart;
    }

    public UploadPartRequest withLastPart(boolean isLastPart) {
        setLastPart(isLastPart);
        return this;
    }

    public SSECustomerKey getSSECustomerKey() {
        return this.sseCustomerKey;
    }

    public void setSSECustomerKey(SSECustomerKey sseKey) {
        this.sseCustomerKey = sseKey;
    }

    public UploadPartRequest withSSECustomerKey(SSECustomerKey sseKey) {
        setSSECustomerKey(sseKey);
        return this;
    }

    public void setGeneralProgressListener(ProgressListener generalProgressListener) {
        this.generalProgressListener = generalProgressListener;
    }

    public ProgressListener getGeneralProgressListener() {
        return this.generalProgressListener;
    }

    public UploadPartRequest withGeneralProgressListener(ProgressListener progressListener) {
        setGeneralProgressListener(progressListener);
        return this;
    }
}
