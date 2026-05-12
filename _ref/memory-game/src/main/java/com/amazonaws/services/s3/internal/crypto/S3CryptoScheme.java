package com.amazonaws.services.s3.internal.crypto;

import com.amazonaws.services.s3.model.CryptoMode;
import java.security.SecureRandom;

final class S3CryptoScheme {
    static final String AES = "AES";
    static final String RSA = "RSA";
    private static final SecureRandom srand = new SecureRandom();
    private final ContentCryptoScheme contentCryptoScheme;
    private final S3KeyWrapScheme kwScheme;

    S3CryptoScheme(ContentCryptoScheme contentCryptoScheme) {
        this.contentCryptoScheme = contentCryptoScheme;
        this.kwScheme = new S3KeyWrapScheme();
    }

    private S3CryptoScheme(ContentCryptoScheme contentCryptoScheme, S3KeyWrapScheme kwScheme) {
        this.contentCryptoScheme = contentCryptoScheme;
        this.kwScheme = kwScheme;
    }

    SecureRandom getSecureRandom() {
        return srand;
    }

    ContentCryptoScheme getContentCryptoScheme() {
        return this.contentCryptoScheme;
    }

    S3KeyWrapScheme getKeyWrapScheme() {
        return this.kwScheme;
    }

    static boolean isAesGcm(String cipherAlgorithm) {
        return ContentCryptoScheme.AES_GCM.getCipherAlgorithm().equals(cipherAlgorithm);
    }

    static S3CryptoScheme from(CryptoMode mode) {
        switch (mode) {
            case EncryptionOnly:
                return new S3CryptoScheme(ContentCryptoScheme.AES_CBC, S3KeyWrapScheme.NONE);
            case AuthenticatedEncryption:
            case StrictAuthenticatedEncryption:
                return new S3CryptoScheme(ContentCryptoScheme.AES_GCM, new S3KeyWrapScheme());
            default:
                throw new IllegalStateException();
        }
    }
}
