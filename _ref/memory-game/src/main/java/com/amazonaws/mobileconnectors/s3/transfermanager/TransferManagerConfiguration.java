package com.amazonaws.mobileconnectors.s3.transfermanager;

@Deprecated
public class TransferManagerConfiguration {
    private static final long DEFAULT_MINIMUM_COPY_PART_SIZE = 104857600;
    private static final int DEFAULT_MINIMUM_UPLOAD_PART_SIZE = 5242880;
    private static final long DEFAULT_MULTIPART_COPY_THRESHOLD = 5368709120L;
    private static final long DEFAULT_MULTIPART_UPLOAD_THRESHOLD = 16777216;
    private long minimumUploadPartSize = 5242880;
    private long multipartCopyPartSize = DEFAULT_MINIMUM_COPY_PART_SIZE;
    private long multipartCopyThreshold = DEFAULT_MULTIPART_COPY_THRESHOLD;
    private long multipartUploadThreshold = DEFAULT_MULTIPART_UPLOAD_THRESHOLD;

    public long getMinimumUploadPartSize() {
        return this.minimumUploadPartSize;
    }

    public void setMinimumUploadPartSize(long minimumUploadPartSize) {
        this.minimumUploadPartSize = minimumUploadPartSize;
    }

    public long getMultipartUploadThreshold() {
        return this.multipartUploadThreshold;
    }

    public void setMultipartUploadThreshold(long multipartUploadThreshold) {
        this.multipartUploadThreshold = multipartUploadThreshold;
    }

    public long getMultipartCopyPartSize() {
        return this.multipartCopyPartSize;
    }

    public void setMultipartCopyPartSize(long multipartCopyPartSize) {
        this.multipartCopyPartSize = multipartCopyPartSize;
    }

    public long getMultipartCopyThreshold() {
        return this.multipartCopyThreshold;
    }

    public void setMultipartCopyThreshold(long multipartCopyThreshold) {
        this.multipartCopyThreshold = multipartCopyThreshold;
    }
}
