package com.amazonaws.services.s3.internal.crypto;

final class MultipartUploadCryptoContext extends MultipartUploadContext {
    private final ContentCryptoMaterial cekMaterial;

    MultipartUploadCryptoContext(String bucketName, String key, ContentCryptoMaterial cekMaterial) {
        super(bucketName, key);
        this.cekMaterial = cekMaterial;
    }

    CipherLite getCipherLite() {
        return this.cekMaterial.getCipherLite();
    }

    ContentCryptoMaterial getContentCryptoMaterial() {
        return this.cekMaterial;
    }
}
