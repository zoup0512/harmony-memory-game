package com.google.android.gms.ads.internal.purchase;

import android.content.Intent;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzin;

@zzin
public class zzk {
    private final String zzawl;

    public zzk(String str) {
        this.zzawl = str;
    }

    public boolean zza(String str, int i, Intent intent) {
        if (str == null || intent == null) {
            return false;
        }
        String zze = zzu.zzga().zze(intent);
        String zzf = zzu.zzga().zzf(intent);
        if (zze == null || zzf == null) {
            return false;
        }
        if (!str.equals(zzu.zzga().zzby(zze))) {
            zzb.zzcx("Developer payload not match.");
            return false;
        } else if (this.zzawl == null || zzl.zzc(this.zzawl, zze, zzf)) {
            return true;
        } else {
            zzb.zzcx("Fail to verify signature.");
            return false;
        }
    }

    public String zzpu() {
        return zzu.zzfq().zztf();
    }
}
