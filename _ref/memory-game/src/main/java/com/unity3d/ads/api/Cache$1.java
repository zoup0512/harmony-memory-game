package com.unity3d.ads.api;

import com.unity3d.ads.properties.SdkProperties;
import java.io.File;
import java.io.FilenameFilter;

class Cache$1 implements FilenameFilter {
    Cache$1() {
    }

    public boolean accept(File file, String str) {
        return str.startsWith(SdkProperties.getCacheFilePrefix());
    }
}
