package com.google.android.gms.internal;

import android.content.SharedPreferences;

class zzcy$1 extends zzcy<Boolean> {
    zzcy$1(int i, String str, Boolean bool) {
        super(i, str, bool, null);
    }

    public /* synthetic */ Object zza(SharedPreferences sharedPreferences) {
        return zzb(sharedPreferences);
    }

    public Boolean zzb(SharedPreferences sharedPreferences) {
        return Boolean.valueOf(sharedPreferences.getBoolean(getKey(), ((Boolean) zzjw()).booleanValue()));
    }
}
