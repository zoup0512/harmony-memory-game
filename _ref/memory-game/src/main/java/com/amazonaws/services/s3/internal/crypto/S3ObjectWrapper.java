package com.amazonaws.services.s3.internal.crypto;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.StringUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

class S3ObjectWrapper implements Closeable {
    private final S3Object s3obj;

    S3ObjectWrapper(S3Object s3obj) {
        if (s3obj == null) {
            throw new IllegalArgumentException();
        }
        this.s3obj = s3obj;
    }

    ObjectMetadata getObjectMetadata() {
        return this.s3obj.getObjectMetadata();
    }

    void setObjectMetadata(ObjectMetadata metadata) {
        this.s3obj.setObjectMetadata(metadata);
    }

    S3ObjectInputStream getObjectContent() {
        return this.s3obj.getObjectContent();
    }

    void setObjectContent(S3ObjectInputStream objectContent) {
        this.s3obj.setObjectContent(objectContent);
    }

    void setObjectContent(InputStream objectContent) {
        this.s3obj.setObjectContent(objectContent);
    }

    String getBucketName() {
        return this.s3obj.getBucketName();
    }

    void setBucketName(String bucketName) {
        this.s3obj.setBucketName(bucketName);
    }

    String getKey() {
        return this.s3obj.getKey();
    }

    void setKey(String key) {
        this.s3obj.setKey(key);
    }

    String getRedirectLocation() {
        return this.s3obj.getRedirectLocation();
    }

    void setRedirectLocation(String redirectLocation) {
        this.s3obj.setRedirectLocation(redirectLocation);
    }

    public String toString() {
        return this.s3obj.toString();
    }

    final boolean isInstructionFile() {
        Map<String, String> userMeta = this.s3obj.getObjectMetadata().getUserMetadata();
        return userMeta != null && userMeta.containsKey(Headers.CRYPTO_INSTRUCTION_FILE);
    }

    final boolean hasEncryptionInfo() {
        Map<String, String> userMeta = this.s3obj.getObjectMetadata().getUserMetadata();
        return userMeta != null && userMeta.containsKey(Headers.CRYPTO_IV) && (userMeta.containsKey(Headers.CRYPTO_KEY_V2) || userMeta.containsKey(Headers.CRYPTO_KEY));
    }

    String toJsonString() {
        try {
            return from(this.s3obj.getObjectContent());
        } catch (Exception e) {
            throw new AmazonClientException("Error parsing JSON: " + e.getMessage());
        }
    }

    private static String from(InputStream is) throws IOException {
        if (is == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StringUtils.UTF8));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } finally {
            is.close();
        }
    }

    public void close() throws IOException {
        this.s3obj.close();
    }

    S3Object getS3Object() {
        return this.s3obj;
    }

    ContentCryptoScheme encryptionSchemeOf(Map<String, String> instructionFile) {
        if (instructionFile != null) {
            return ContentCryptoScheme.fromCEKAlgo((String) instructionFile.get(Headers.CRYPTO_CEK_ALGORITHM));
        }
        return ContentCryptoScheme.fromCEKAlgo((String) this.s3obj.getObjectMetadata().getUserMetadata().get(Headers.CRYPTO_CEK_ALGORITHM));
    }
}
