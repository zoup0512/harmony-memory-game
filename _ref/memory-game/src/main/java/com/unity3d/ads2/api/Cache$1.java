package com.unity3d.ads2.api;

import com.unity3d.ads2.properties.SdkProperties;
import java.io.File;
import java.io.FilenameFilter;

class Cache$1 implements FilenameFilter {
    Cache$1() {
    }

    public boolean accept(File file, String str) {
        return str.startsWith(SdkProperties.getCacheFilePrefix());
    }
}
