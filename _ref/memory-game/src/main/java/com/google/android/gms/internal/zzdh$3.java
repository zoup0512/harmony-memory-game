package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.text.TextUtils;

class zzdh$3 extends zzdh {
    zzdh$3() {
    }

    @Nullable
    private String zzar(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int i = 0;
        int length = str.length();
        while (i < str.length() && str.charAt(i) == ',') {
            i++;
        }
        while (length > 0 && str.charAt(length - 1) == ',') {
            length--;
        }
        return (i == 0 && length == str.length()) ? str : str.substring(i, length);
    }

    public String zzg(@Nullable String str, String str2) {
        String zzar = zzar(str);
        String zzar2 = zzar(str2);
        return TextUtils.isEmpty(zzar) ? zzar2 : TextUtils.isEmpty(zzar2) ? zzar : new StringBuilder((String.valueOf(zzar).length() + 1) + String.valueOf(zzar2).length()).append(zzar).append(",").append(zzar2).toString();
    }
}
