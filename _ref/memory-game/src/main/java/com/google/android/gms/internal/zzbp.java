package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public abstract class zzbp implements Callable {
    protected final String TAG = getClass().getSimpleName();
    protected final String className;
    protected final zzax zzaey;
    protected final zza zzaha;
    protected final String zzahf;
    protected Method zzahh;
    protected final int zzahl;
    protected final int zzahm;

    public zzbp(zzax com_google_android_gms_internal_zzax, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        this.zzaey = com_google_android_gms_internal_zzax;
        this.className = str;
        this.zzahf = str2;
        this.zzaha = com_google_android_gms_internal_zzae_zza;
        this.zzahl = i;
        this.zzahm = i2;
    }

    public /* synthetic */ Object call() throws Exception {
        return zzcx();
    }

    protected abstract void zzcu() throws IllegalAccessException, InvocationTargetException;

    public Void zzcx() throws Exception {
        try {
            long nanoTime = System.nanoTime();
            this.zzahh = this.zzaey.zzc(this.className, this.zzahf);
            if (this.zzahh != null) {
                zzcu();
                zzam zzck = this.zzaey.zzck();
                if (!(zzck == null || this.zzahl == Integer.MIN_VALUE)) {
                    zzck.zza(this.zzahm, this.zzahl, (System.nanoTime() - nanoTime) / 1000);
                }
            }
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e2) {
        }
        return null;
    }
}
