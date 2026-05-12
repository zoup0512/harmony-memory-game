package com.google.android.gms.internal;

import android.content.SharedPreferences;

class zzcy$3 extends zzcy<Long> {
    zzcy$3(int i, String str, Long l) {
        super(i, str, l, null);
    }

    public /* synthetic */ Object zza(SharedPreferences sharedPreferences) {
        return zzd(sharedPreferences);
    }

    public Long zzd(SharedPreferences sharedPreferences) {
        return Long.valueOf(sharedPreferences.getLong(getKey(), ((Long) zzjw()).longValue()));
    }
}
