package com.amazonaws.services.s3.internal.crypto;

import java.security.Key;

class S3KeyWrapScheme {
    public static final String AESWrap = "AESWrap";
    static final S3KeyWrapScheme NONE = new S3KeyWrapScheme() {
        String getKeyWrapAlgorithm(Key key) {
            return null;
        }

        public String toString() {
            return "NONE";
        }
    };
    public static final String RSA_ECB_OAEPWithSHA256AndMGF1Padding = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

    S3KeyWrapScheme() {
    }

    String getKeyWrapAlgorithm(Key key) {
        String algorithm = key.getAlgorithm();
        if ("AES".equals(algorithm)) {
            return AESWrap;
        }
        if ("RSA".equals(algorithm) && CryptoRuntime.isRsaKeyWrapAvailable()) {
            return RSA_ECB_OAEPWithSHA256AndMGF1Padding;
        }
        return null;
    }
}
