package com.google.android.gms.internal;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.internal.zzae.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class zzbh extends zzbp {
    public zzbh(zzax com_google_android_gms_internal_zzax, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        super(com_google_android_gms_internal_zzax, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
    }

    private void zzcv() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zzaha) {
            this.zzaha.zzeg = (String) this.zzahh.invoke(null, new Object[]{this.zzaey.getContext()});
        }
    }

    private void zzcw() {
        AdvertisingIdClient zzcr = this.zzaey.zzcr();
        if (zzcr == null) {
            zzp("E1");
            return;
        }
        try {
            Info info = zzcr.getInfo();
            String zzo = zzay.zzo(info.getId());
            if (zzo != null) {
                synchronized (this.zzaha) {
                    this.zzaha.zzeg = zzo;
                    this.zzaha.zzei = Boolean.valueOf(info.isLimitAdTrackingEnabled());
                    this.zzaha.zzeh = Integer.valueOf(5);
                }
                return;
            }
            zzp("E");
        } catch (IOException e) {
            zzp("E");
        }
    }

    private void zzp(String str) {
    }

    protected void zzcu() throws IllegalAccessException, InvocationTargetException {
        if (this.zzaey.zzci()) {
            zzcw();
        } else {
            zzcv();
        }
    }
}
