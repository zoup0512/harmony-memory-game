package com.amazonaws.mobileconnectors.s3.transfermanager;

import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.IOException;
import java.io.StringWriter;

@Deprecated
public final class PersistableDownload extends PersistableTransfer {
    static final String TYPE = "download";
    private final String bucketName;
    private final String file;
    private final boolean isRequesterPays;
    private final String key;
    private final String pauseType;
    private final long[] range;
    private final ResponseHeaderOverrides responseHeaders;
    private final String versionId;

    @Deprecated
    public PersistableDownload() {
        this(null, null, null, null, null, false, null);
    }

    public PersistableDownload(String bucketName, String key, String versionId, long[] range, ResponseHeaderOverrides responseHeaders, boolean isRequesterPays, String file) {
        this.pauseType = TYPE;
        this.bucketName = bucketName;
        this.key = key;
        this.versionId = versionId;
        this.range = range == null ? null : (long[]) range.clone();
        this.responseHeaders = responseHeaders;
        this.isRequesterPays = isRequesterPays;
        this.file = file;
    }

    String getBucketName() {
        return this.bucketName;
    }

    String getKey() {
        return this.key;
    }

    String getVersionId() {
        return this.versionId;
    }

    long[] getRange() {
        return this.range == null ? null : (long[]) this.range.clone();
    }

    ResponseHeaderOverrides getResponseHeaders() {
        return this.responseHeaders;
    }

    boolean isRequesterPays() {
        return this.isRequesterPays;
    }

    String getFile() {
        return this.file;
    }

    String getPauseType() {
        return TYPE;
    }

    public String serialize() {
        StringWriter out = new StringWriter();
        AwsJsonWriter writer = JsonUtils.getJsonWriter(out);
        try {
            writer.beginObject().name("pauseType").value(TYPE).name("bucketName").value(this.bucketName).name(TransferTable.COLUMN_KEY).value(this.key).name(TransferTable.COLUMN_FILE).value(this.file).name("versionId").value(this.versionId).name("isRequesterPays").value(this.isRequesterPays);
            if (this.range != null) {
                writer.name("range").beginArray();
                for (long r : this.range) {
                    writer.value(r);
                }
                writer.endArray();
            }
            if (this.responseHeaders != null) {
                writer.name("responseHeaders").beginObject().name("contentType").value(this.responseHeaders.getContentType()).name("contentLanguage").value(this.responseHeaders.getContentLanguage()).name("expires").value(this.responseHeaders.getExpires()).name("cacheControl").value(this.responseHeaders.getCacheControl()).name("contentDisposition").value(this.responseHeaders.getContentDisposition()).name("contentEncoding").value(this.responseHeaders.getContentEncoding()).endObject();
            }
            writer.endObject().close();
            return out.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
