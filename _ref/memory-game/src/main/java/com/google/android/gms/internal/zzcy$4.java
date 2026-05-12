package com.google.android.gms.internal;

import android.content.SharedPreferences;

class zzcy$4 extends zzcy<String> {
    zzcy$4(int i, String str, String str2) {
        super(i, str, str2, null);
    }

    public /* synthetic */ Object zza(SharedPreferences sharedPreferences) {
        return zze(sharedPreferences);
    }

    public String zze(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(getKey(), (String) zzjw());
    }
}
