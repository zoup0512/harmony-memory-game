package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.zze;

@zzin
public class zzdb {
    private final Object zzail = new Object();
    private boolean zzamt = false;
    @Nullable
    private SharedPreferences zzaxu = null;

    public void initialize(Context context) {
        synchronized (this.zzail) {
            if (this.zzamt) {
                return;
            }
            Context remoteContext = zze.getRemoteContext(context);
            if (remoteContext == null) {
                return;
            }
            this.zzaxu = zzu.zzfx().zzn(remoteContext);
            this.zzamt = true;
        }
    }

    public <T> T zzd(zzcy<T> com_google_android_gms_internal_zzcy_T) {
        synchronized (this.zzail) {
            if (this.zzamt) {
                return zzkt.zzb(new 1(this, com_google_android_gms_internal_zzcy_T));
            }
            T zzjw = com_google_android_gms_internal_zzcy_T.zzjw();
            return zzjw;
        }
    }
}
