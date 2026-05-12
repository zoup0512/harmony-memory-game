package com.amazonaws.services.s3.internal.crypto;

import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class EncryptionInstruction {
    private final byte[] encryptedSymmetricKey;
    private final Map<String, String> materialsDescription;
    private final Cipher symmetricCipher;
    private final CipherFactory symmetricCipherFactory;

    public EncryptionInstruction(Map<String, String> materialsDescription, byte[] encryptedSymmetricKey, SecretKey symmetricKey, Cipher symmetricCipher) {
        this.materialsDescription = materialsDescription;
        this.encryptedSymmetricKey = encryptedSymmetricKey;
        this.symmetricCipher = symmetricCipher;
        this.symmetricCipherFactory = null;
    }

    public EncryptionInstruction(Map<String, String> materialsDescription, byte[] encryptedSymmetricKey, SecretKey symmetricKey, CipherFactory symmetricCipherFactory) {
        this.materialsDescription = materialsDescription;
        this.encryptedSymmetricKey = encryptedSymmetricKey;
        this.symmetricCipherFactory = symmetricCipherFactory;
        this.symmetricCipher = symmetricCipherFactory.createCipher();
    }

    public CipherFactory getCipherFactory() {
        return this.symmetricCipherFactory;
    }

    public Map<String, String> getMaterialsDescription() {
        return this.materialsDescription;
    }

    public byte[] getEncryptedSymmetricKey() {
        return this.encryptedSymmetricKey;
    }

    public Cipher getSymmetricCipher() {
        return this.symmetricCipher;
    }
}
