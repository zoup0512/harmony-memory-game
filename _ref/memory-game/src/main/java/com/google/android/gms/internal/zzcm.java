package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzin
public class zzcm {
    private final Object zzail = new Object();
    private int zzash;
    private List<zzcl> zzasi = new LinkedList();

    public boolean zza(zzcl com_google_android_gms_internal_zzcl) {
        boolean z;
        synchronized (this.zzail) {
            if (this.zzasi.contains(com_google_android_gms_internal_zzcl)) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public boolean zzb(zzcl com_google_android_gms_internal_zzcl) {
        boolean z;
        synchronized (this.zzail) {
            Iterator it = this.zzasi.iterator();
            while (it.hasNext()) {
                zzcl com_google_android_gms_internal_zzcl2 = (zzcl) it.next();
                if (com_google_android_gms_internal_zzcl != com_google_android_gms_internal_zzcl2 && com_google_android_gms_internal_zzcl2.zzhr().equals(com_google_android_gms_internal_zzcl.zzhr())) {
                    it.remove();
                    z = true;
                    break;
                }
            }
            z = false;
        }
        return z;
    }

    public void zzc(zzcl com_google_android_gms_internal_zzcl) {
        synchronized (this.zzail) {
            if (this.zzasi.size() >= 10) {
                zzb.zzcv("Queue is full, current size = " + this.zzasi.size());
                this.zzasi.remove(0);
            }
            int i = this.zzash;
            this.zzash = i + 1;
            com_google_android_gms_internal_zzcl.zzl(i);
            this.zzasi.add(com_google_android_gms_internal_zzcl);
        }
    }

    @Nullable
    public zzcl zzhy() {
        zzcl com_google_android_gms_internal_zzcl = null;
        synchronized (this.zzail) {
            if (this.zzasi.size() == 0) {
                zzb.zzcv("Queue empty");
                return null;
            } else if (this.zzasi.size() >= 2) {
                int i = Integer.MIN_VALUE;
                for (zzcl com_google_android_gms_internal_zzcl2 : this.zzasi) {
                    zzcl com_google_android_gms_internal_zzcl3;
                    int i2;
                    int score = com_google_android_gms_internal_zzcl2.getScore();
                    if (score > i) {
                        int i3 = score;
                        com_google_android_gms_internal_zzcl3 = com_google_android_gms_internal_zzcl2;
                        i2 = i3;
                    } else {
                        i2 = i;
                        com_google_android_gms_internal_zzcl3 = com_google_android_gms_internal_zzcl;
                    }
                    i = i2;
                    com_google_android_gms_internal_zzcl = com_google_android_gms_internal_zzcl3;
                }
                this.zzasi.remove(com_google_android_gms_internal_zzcl);
                return com_google_android_gms_internal_zzcl;
            } else {
                com_google_android_gms_internal_zzcl2 = (zzcl) this.zzasi.get(0);
                com_google_android_gms_internal_zzcl2.zzht();
                return com_google_android_gms_internal_zzcl2;
            }
        }
    }
}
