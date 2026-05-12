package com.amazonaws.services.s3.internal.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

class AesGcm extends ContentCryptoScheme {
    AesGcm() {
    }

    String getKeyGeneratorAlgorithm() {
        return "AES";
    }

    String getCipherAlgorithm() {
        return "AES/GCM/NoPadding";
    }

    int getKeyLengthInBits() {
        return 256;
    }

    int getBlockSizeInBytes() {
        return 16;
    }

    int getIVLengthInBytes() {
        return 12;
    }

    long getMaxPlaintextSize() {
        return 68719476704L;
    }

    int getTagLengthInBits() {
        return 128;
    }

    String getSpecificCipherProvider() {
        return "BC";
    }

    CipherLite createAuxillaryCipher(SecretKey cek, byte[] ivOrig, int cipherMode, Provider securityProvider, long startingBytePos) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        return AES_CTR.createCipherLite(cek, AES_CTR.adjustIV(ivOrig, startingBytePos), cipherMode, securityProvider);
    }

    protected CipherLite newCipherLite(Cipher cipher, SecretKey cek, int cipherMode) {
        return new GCMCipherLite(cipher, cek, cipherMode);
    }
}
