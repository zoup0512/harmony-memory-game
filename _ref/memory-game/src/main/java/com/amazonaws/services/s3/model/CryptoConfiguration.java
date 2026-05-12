package com.amazonaws.services.s3.model;

import com.amazonaws.services.s3.internal.crypto.CryptoRuntime;
import java.security.Provider;

public class CryptoConfiguration {
    private CryptoMode cryptoMode;
    private Provider cryptoProvider;
    private CryptoStorageMode storageMode;

    public CryptoConfiguration() {
        this(CryptoMode.EncryptionOnly);
    }

    public CryptoConfiguration(CryptoMode cryptoMode) {
        check(cryptoMode);
        this.storageMode = CryptoStorageMode.ObjectMetadata;
        this.cryptoProvider = null;
        this.cryptoMode = cryptoMode;
    }

    public void setStorageMode(CryptoStorageMode storageMode) {
        this.storageMode = storageMode;
    }

    public CryptoConfiguration withStorageMode(CryptoStorageMode storageMode) {
        this.storageMode = storageMode;
        return this;
    }

    public CryptoStorageMode getStorageMode() {
        return this.storageMode;
    }

    public void setCryptoProvider(Provider cryptoProvider) {
        this.cryptoProvider = cryptoProvider;
    }

    public CryptoConfiguration withCryptoProvider(Provider cryptoProvider) {
        this.cryptoProvider = cryptoProvider;
        return this;
    }

    public Provider getCryptoProvider() {
        return this.cryptoProvider;
    }

    public CryptoMode getCryptoMode() {
        return this.cryptoMode;
    }

    public void setCryptoMode(CryptoMode cryptoMode) {
        check(cryptoMode);
        this.cryptoMode = cryptoMode;
    }

    public CryptoConfiguration withCryptoMode(CryptoMode cryptoMode) {
        check(cryptoMode);
        this.cryptoMode = cryptoMode;
        return this;
    }

    private void check(CryptoMode cryptoMode) {
        if (cryptoMode == CryptoMode.AuthenticatedEncryption || cryptoMode == CryptoMode.StrictAuthenticatedEncryption) {
            if (!CryptoRuntime.isBouncyCastleAvailable()) {
                CryptoRuntime.enableBouncyCastle();
                if (!CryptoRuntime.isBouncyCastleAvailable()) {
                    throw new UnsupportedOperationException("The Bouncy castle library jar is required on the classpath to enable authenticated encryption");
                }
            }
            if (!CryptoRuntime.isAesGcmAvailable()) {
                throw new UnsupportedOperationException("More recent version of the Bouncy castle library is required to enable authenticated encryption");
            }
        }
    }
}
