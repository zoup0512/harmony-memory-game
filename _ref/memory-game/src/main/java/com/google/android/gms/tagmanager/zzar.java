package com.google.android.gms.tagmanager;

import android.text.TextUtils;

class zzar {
    private final long M;
    private final long avW;
    private final long avX;
    private String avY;

    zzar(long j, long j2, long j3) {
        this.avW = j;
        this.M = j2;
        this.avX = j3;
    }

    long zzcbr() {
        return this.avW;
    }

    long zzcbs() {
        return this.avX;
    }

    String zzcbt() {
        return this.avY;
    }

    void zzou(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.avY = str;
        }
    }
}
