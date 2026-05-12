package com.amazonaws.services.s3.internal.crypto;

import javax.crypto.SecretKey;

public class EncryptedUploadContext extends MultipartUploadContext {
    private final SecretKey envelopeEncryptionKey;
    private byte[] firstIV;
    private byte[] nextIV;

    public EncryptedUploadContext(String bucketName, String key, SecretKey envelopeEncryptionKey) {
        super(bucketName, key);
        this.envelopeEncryptionKey = envelopeEncryptionKey;
    }

    public SecretKey getEnvelopeEncryptionKey() {
        return this.envelopeEncryptionKey;
    }

    public void setNextInitializationVector(byte[] nextIV) {
        this.nextIV = nextIV;
    }

    public byte[] getNextInitializationVector() {
        return this.nextIV;
    }

    public void setFirstInitializationVector(byte[] firstIV) {
        this.firstIV = firstIV;
    }

    public byte[] getFirstInitializationVector() {
        return this.firstIV;
    }
}
