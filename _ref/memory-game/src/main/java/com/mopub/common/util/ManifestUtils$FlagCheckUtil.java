package com.mopub.common.util;

class ManifestUtils$FlagCheckUtil {
    ManifestUtils$FlagCheckUtil() {
    }

    public boolean hasFlag(Class cls, int i, int i2) {
        return Utils.bitMaskContainsFlag(i, i2);
    }
}
