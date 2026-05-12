package com.amazonaws.mobileconnectors.s3.transfermanager.internal;

import com.amazonaws.services.s3.internal.InputSubstream;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.SSECustomerKey;
import com.amazonaws.services.s3.model.UploadPartRequest;
import java.io.File;

public class UploadPartRequestFactory {
    private final String bucketName;
    private final File file;
    private final String key;
    private long offset = 0;
    private final long optimalPartSize;
    private int partNumber = 1;
    private final PutObjectRequest putObjectRequest;
    private long remainingBytes;
    private SSECustomerKey sseCustomerKey;
    private final String uploadId;

    public UploadPartRequestFactory(PutObjectRequest putObjectRequest, String uploadId, long optimalPartSize) {
        this.putObjectRequest = putObjectRequest;
        this.uploadId = uploadId;
        this.optimalPartSize = optimalPartSize;
        this.bucketName = putObjectRequest.getBucketName();
        this.key = putObjectRequest.getKey();
        this.file = TransferManagerUtils.getRequestFile(putObjectRequest);
        this.remainingBytes = TransferManagerUtils.getContentLength(putObjectRequest);
        this.sseCustomerKey = putObjectRequest.getSSECustomerKey();
    }

    public synchronized boolean hasMoreRequests() {
        return this.remainingBytes > 0;
    }

    public synchronized UploadPartRequest getNextUploadPartRequest() {
        UploadPartRequest request;
        long partSize = Math.min(this.optimalPartSize, this.remainingBytes);
        boolean isLastPart = this.remainingBytes - partSize <= 0;
        UploadPartRequest withInputStream;
        int i;
        if (this.putObjectRequest.getInputStream() != null) {
            withInputStream = new UploadPartRequest().withBucketName(this.bucketName).withKey(this.key).withUploadId(this.uploadId).withInputStream(new InputSubstream(this.putObjectRequest.getInputStream(), 0, partSize, isLastPart));
            i = this.partNumber;
            this.partNumber = i + 1;
            request = withInputStream.withPartNumber(i).withPartSize(partSize);
        } else {
            withInputStream = new UploadPartRequest().withBucketName(this.bucketName).withKey(this.key).withUploadId(this.uploadId).withFile(this.file).withFileOffset(this.offset);
            i = this.partNumber;
            this.partNumber = i + 1;
            request = withInputStream.withPartNumber(i).withPartSize(partSize);
        }
        if (this.sseCustomerKey != null) {
            request.setSSECustomerKey(this.sseCustomerKey);
        }
        this.offset += partSize;
        this.remainingBytes -= partSize;
        request.setLastPart(isLastPart);
        request.setGeneralProgressListener(this.putObjectRequest.getGeneralProgressListener());
        return request;
    }
}
