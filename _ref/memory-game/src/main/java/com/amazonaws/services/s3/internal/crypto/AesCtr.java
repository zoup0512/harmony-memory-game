package com.amazonaws.services.s3.internal.crypto;

class AesCtr extends ContentCryptoScheme {
    AesCtr() {
    }

    String getKeyGeneratorAlgorithm() {
        return AES_GCM.getKeyGeneratorAlgorithm();
    }

    String getCipherAlgorithm() {
        return "AES/CTR/NoPadding";
    }

    int getKeyLengthInBits() {
        return AES_GCM.getKeyLengthInBits();
    }

    int getBlockSizeInBytes() {
        return AES_GCM.getBlockSizeInBytes();
    }

    int getIVLengthInBytes() {
        return 16;
    }

    long getMaxPlaintextSize() {
        return -1;
    }

    byte[] adjustIV(byte[] iv, long byteOffset) {
        if (iv.length != 12) {
            throw new UnsupportedOperationException();
        }
        int blockSize = getBlockSizeInBytes();
        long blockOffset = byteOffset / ((long) blockSize);
        if (((long) blockSize) * blockOffset == byteOffset) {
            return ContentCryptoScheme.incrementBlocks(computeJ0(iv), blockOffset);
        }
        throw new IllegalArgumentException("Expecting byteOffset to be multiple of 16, but got blockOffset=" + blockOffset + ", blockSize=" + blockSize + ", byteOffset=" + byteOffset);
    }

    private byte[] computeJ0(byte[] nonce) {
        int blockSize = getBlockSizeInBytes();
        byte[] J0 = new byte[blockSize];
        System.arraycopy(nonce, 0, J0, 0, nonce.length);
        J0[blockSize - 1] = (byte) 1;
        return ContentCryptoScheme.incrementBlocks(J0, 1);
    }
}
