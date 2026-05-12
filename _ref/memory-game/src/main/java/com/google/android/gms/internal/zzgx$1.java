package com.google.android.gms.internal;

import com.google.ads.AdRequest$ErrorCode;
import com.google.ads.AdRequest$Gender;

/* synthetic */ class zzgx$1 {
    static final /* synthetic */ int[] zzbps = new int[AdRequest$Gender.values().length];
    static final /* synthetic */ int[] zzbpt = new int[AdRequest$ErrorCode.values().length];

    static {
        try {
            zzbpt[AdRequest$ErrorCode.INTERNAL_ERROR.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            zzbpt[AdRequest$ErrorCode.INVALID_REQUEST.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            zzbpt[AdRequest$ErrorCode.NETWORK_ERROR.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            zzbpt[AdRequest$ErrorCode.NO_FILL.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            zzbps[AdRequest$Gender.FEMALE.ordinal()] = 1;
        } catch (NoSuchFieldError e5) {
        }
        try {
            zzbps[AdRequest$Gender.MALE.ordinal()] = 2;
        } catch (NoSuchFieldError e6) {
        }
        try {
            zzbps[AdRequest$Gender.UNKNOWN.ordinal()] = 3;
        } catch (NoSuchFieldError e7) {
        }
    }
}
