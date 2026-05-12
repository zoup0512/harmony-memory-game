package com.amazonaws.internal.config;

public class SignerConfig {
    private final String signerType;

    SignerConfig(String signerType) {
        this.signerType = signerType;
    }

    SignerConfig(SignerConfig from) {
        this.signerType = from.getSignerType();
    }

    public String getSignerType() {
        return this.signerType;
    }

    public String toString() {
        return this.signerType;
    }
}
