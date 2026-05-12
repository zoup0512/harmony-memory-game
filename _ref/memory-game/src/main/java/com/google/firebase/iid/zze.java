package com.google.firebase.iid;

import android.support.annotation.Nullable;
import android.text.TextUtils;

public class zze {
    private static final Object zzamr = new Object();
    private final zzg baH;

    zze(zzg com_google_firebase_iid_zzg) {
        this.baH = com_google_firebase_iid_zzg;
    }

    @Nullable
    String zzcxa() {
        String str = null;
        synchronized (zzamr) {
            String string = this.baH.zzcxb().getString("topic_operaion_queue", null);
            if (string != null) {
                String[] split = string.split(",");
                if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                    str = split[1];
                }
            }
        }
        return str;
    }

    void zzsv(String str) {
        synchronized (zzamr) {
            String string = this.baH.zzcxb().getString("topic_operaion_queue", "");
            String valueOf = String.valueOf(",");
            this.baH.zzcxb().edit().putString("topic_operaion_queue", new StringBuilder(((String.valueOf(string).length() + 0) + String.valueOf(valueOf).length()) + String.valueOf(str).length()).append(string).append(valueOf).append(str).toString()).apply();
        }
    }

    boolean zzsz(String str) {
        boolean z;
        synchronized (zzamr) {
            String string = this.baH.zzcxb().getString("topic_operaion_queue", "");
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (string.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                valueOf = String.valueOf(",");
                valueOf2 = String.valueOf(str);
                this.baH.zzcxb().edit().putString("topic_operaion_queue", string.substring((valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).length())).apply();
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }
}
