package com.amazonaws.services.s3.internal.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.NullCipher;
import javax.crypto.SecretKey;

class CipherLite {
    static final CipherLite Null = new CipherLite() {
        CipherLite createAuxiliary(long startingBytePos) {
            return this;
        }

        CipherLite createInverse() {
            return this;
        }
    };
    private final Cipher cipher;
    private final int cipherMode;
    private final ContentCryptoScheme scheme;
    private final SecretKey secreteKey;

    private CipherLite() {
        this.cipher = new NullCipher();
        this.scheme = null;
        this.secreteKey = null;
        this.cipherMode = -1;
    }

    CipherLite(Cipher cipher, ContentCryptoScheme scheme, SecretKey secreteKey, int cipherMode) {
        this.cipher = cipher;
        this.scheme = scheme;
        this.secreteKey = secreteKey;
        this.cipherMode = cipherMode;
    }

    CipherLite createAuxiliary(long startingBytePos) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        return this.scheme.createAuxillaryCipher(this.secreteKey, this.cipher.getIV(), this.cipherMode, this.cipher.getProvider(), startingBytePos);
    }

    CipherLite createInverse() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        int inversedMode;
        if (this.cipherMode == 2) {
            inversedMode = 1;
        } else if (this.cipherMode == 1) {
            inversedMode = 2;
        } else {
            throw new UnsupportedOperationException();
        }
        return this.scheme.createCipherLite(this.secreteKey, this.cipher.getIV(), inversedMode, this.cipher.getProvider());
    }

    byte[] doFinal() throws IllegalBlockSizeException, BadPaddingException {
        return this.cipher.doFinal();
    }

    byte[] doFinal(byte[] input) throws IllegalBlockSizeException, BadPaddingException {
        return this.cipher.doFinal(input);
    }

    byte[] doFinal(byte[] input, int inputOffset, int inputLen) throws IllegalBlockSizeException, BadPaddingException {
        return this.cipher.doFinal(input, inputOffset, inputLen);
    }

    byte[] update(byte[] input, int inputOffset, int inputLen) {
        return this.cipher.update(input, inputOffset, inputLen);
    }

    final String getCipherAlgorithm() {
        return this.cipher.getAlgorithm();
    }

    final Provider getCipherProvider() {
        return this.cipher.getProvider();
    }

    final String getSecretKeyAlgorithm() {
        return this.secreteKey.getAlgorithm();
    }

    final Cipher getCipher() {
        return this.cipher;
    }

    final ContentCryptoScheme getContentCryptoScheme() {
        return this.scheme;
    }

    final byte[] getIV() {
        return this.cipher.getIV();
    }

    final int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    final int getCipherMode() {
        return this.cipherMode;
    }

    boolean markSupported() {
        return false;
    }

    long mark() {
        return -1;
    }

    void reset() {
        throw new IllegalStateException("mark/reset not supported");
    }

    int getOutputSize(int inputLen) {
        return this.cipher.getOutputSize(inputLen);
    }
}
