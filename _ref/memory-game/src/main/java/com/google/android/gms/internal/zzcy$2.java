package com.google.android.gms.internal;

import android.content.SharedPreferences;

class zzcy$2 extends zzcy<Integer> {
    zzcy$2(int i, String str, Integer num) {
        super(i, str, num, null);
    }

    public /* synthetic */ Object zza(SharedPreferences sharedPreferences) {
        return zzc(sharedPreferences);
    }

    public Integer zzc(SharedPreferences sharedPreferences) {
        return Integer.valueOf(sharedPreferences.getInt(getKey(), ((Integer) zzjw()).intValue()));
    }
}
