package com.amazonaws.services.s3.internal.crypto;

import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class CipherFactory {
    private final int cipherMode;
    private final Provider cryptoProvider;
    private byte[] initVectorBytes;
    private final SecretKey symmetricKey;

    public CipherFactory(SecretKey symmetricKey, int cipherMode, byte[] initVectorBytes, Provider cryptoProvider) {
        this.symmetricKey = symmetricKey;
        this.cipherMode = cipherMode;
        this.initVectorBytes = initVectorBytes;
        this.cryptoProvider = cryptoProvider;
    }

    public Cipher createCipher() {
        Cipher cipher = EncryptionUtils.createSymmetricCipher(this.symmetricKey, this.cipherMode, this.cryptoProvider, this.initVectorBytes);
        if (this.initVectorBytes == null) {
            this.initVectorBytes = cipher.getIV();
        }
        return cipher;
    }

    public Provider getCryptoProvider() {
        return this.cryptoProvider;
    }

    public int getCipherMode() {
        return this.cipherMode;
    }

    public byte[] getIV() {
        return this.initVectorBytes == null ? null : (byte[]) this.initVectorBytes.clone();
    }
}
