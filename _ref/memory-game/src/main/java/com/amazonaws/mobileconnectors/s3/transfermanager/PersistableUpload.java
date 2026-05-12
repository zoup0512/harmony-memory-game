package com.amazonaws.mobileconnectors.s3.transfermanager;

import com.amazonaws.util.json.JsonUtils;
import java.io.IOException;
import java.io.StringWriter;

@Deprecated
public final class PersistableUpload extends PersistableTransfer {
    static final String TYPE = "upload";
    private final String bucketName;
    private final String file;
    private final String key;
    private final String multipartUploadId;
    private final long mutlipartUploadThreshold;
    private final long partSize;
    private final String pauseType;

    @Deprecated
    public PersistableUpload() {
        this(null, null, null, null, -1, -1);
    }

    public PersistableUpload(String bucketName, String key, String file, String multipartUploadId, long partSize, long mutlipartUploadThreshold) {
        this.pauseType = TYPE;
        this.bucketName = bucketName;
        this.key = key;
        this.file = file;
        this.multipartUploadId = multipartUploadId;
        this.partSize = partSize;
        this.mutlipartUploadThreshold = mutlipartUploadThreshold;
    }

    String getBucketName() {
        return this.bucketName;
    }

    String getKey() {
        return this.key;
    }

    String getMultipartUploadId() {
        return this.multipartUploadId;
    }

    long getPartSize() {
        return this.partSize;
    }

    long getMutlipartUploadThreshold() {
        return this.mutlipartUploadThreshold;
    }

    String getFile() {
        return this.file;
    }

    String getPauseType() {
        return TYPE;
    }

    public String serialize() {
        StringWriter out = new StringWriter();
        try {
            JsonUtils.getJsonWriter(out).beginObject().name("pauseType").value(TYPE).name("bucketName").value(this.bucketName).name(TransferTable.COLUMN_KEY).value(this.key).name(TransferTable.COLUMN_FILE).value(this.file).name("multipartUploadId").value(this.multipartUploadId).name("partSize").value(this.partSize).name("mutlipartUploadThreshold").value(this.mutlipartUploadThreshold).endObject().close();
            return out.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
