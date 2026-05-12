package com.amazonaws.services.s3.internal.crypto;

class AesCbc extends ContentCryptoScheme {
    AesCbc() {
    }

    String getKeyGeneratorAlgorithm() {
        return "AES";
    }

    String getCipherAlgorithm() {
        return "AES/CBC/PKCS5Padding";
    }

    int getKeyLengthInBits() {
        return 256;
    }

    int getBlockSizeInBytes() {
        return 16;
    }

    int getIVLengthInBytes() {
        return 16;
    }

    long getMaxPlaintextSize() {
        return 4503599627370496L;
    }
}
