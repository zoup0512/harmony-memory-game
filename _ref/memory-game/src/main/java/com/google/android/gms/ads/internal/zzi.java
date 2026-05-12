package com.google.android.gms.ads.internal;

import android.content.Context;
import android.view.MotionEvent;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzan;
import com.google.android.gms.internal.zzar;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzkg;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

@zzin
class zzi implements zzan, Runnable {
    private zzv zzajs;
    private final List<Object[]> zzalc = new Vector();
    private final AtomicReference<zzan> zzald = new AtomicReference();
    CountDownLatch zzale = new CountDownLatch(1);

    public zzi(zzv com_google_android_gms_ads_internal_zzv) {
        this.zzajs = com_google_android_gms_ads_internal_zzv;
        if (zzm.zziw().zztx()) {
            zzkg.zza((Runnable) this);
        } else {
            run();
        }
    }

    private void zzep() {
        if (!this.zzalc.isEmpty()) {
            for (Object[] objArr : this.zzalc) {
                if (objArr.length == 1) {
                    ((zzan) this.zzald.get()).zza((MotionEvent) objArr[0]);
                } else if (objArr.length == 3) {
                    ((zzan) this.zzald.get()).zza(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue());
                }
            }
            this.zzalc.clear();
        }
    }

    private Context zzi(Context context) {
        if (!((Boolean) zzdc.zzayk.get()).booleanValue()) {
            return context;
        }
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }

    public void run() {
        try {
            boolean z = !((Boolean) zzdc.zzayw.get()).booleanValue() || this.zzajs.zzaow.zzcnm;
            zza(zzd(this.zzajs.zzaow.zzcs, zzi(this.zzajs.zzagf), z));
        } finally {
            this.zzale.countDown();
            this.zzajs = null;
        }
    }

    public void zza(int i, int i2, int i3) {
        zzan com_google_android_gms_internal_zzan = (zzan) this.zzald.get();
        if (com_google_android_gms_internal_zzan != null) {
            zzep();
            com_google_android_gms_internal_zzan.zza(i, i2, i3);
            return;
        }
        this.zzalc.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public void zza(MotionEvent motionEvent) {
        zzan com_google_android_gms_internal_zzan = (zzan) this.zzald.get();
        if (com_google_android_gms_internal_zzan != null) {
            zzep();
            com_google_android_gms_internal_zzan.zza(motionEvent);
            return;
        }
        this.zzalc.add(new Object[]{motionEvent});
    }

    protected void zza(zzan com_google_android_gms_internal_zzan) {
        this.zzald.set(com_google_android_gms_internal_zzan);
    }

    public String zzb(Context context) {
        if (zzeo()) {
            zzan com_google_android_gms_internal_zzan = (zzan) this.zzald.get();
            if (com_google_android_gms_internal_zzan != null) {
                zzep();
                return com_google_android_gms_internal_zzan.zzb(zzi(context));
            }
        }
        return "";
    }

    public String zzb(Context context, String str) {
        if (zzeo()) {
            zzan com_google_android_gms_internal_zzan = (zzan) this.zzald.get();
            if (com_google_android_gms_internal_zzan != null) {
                zzep();
                return com_google_android_gms_internal_zzan.zzb(zzi(context), str);
            }
        }
        return "";
    }

    protected zzan zzd(String str, Context context, boolean z) {
        return zzar.zza(str, context, z);
    }

    protected boolean zzeo() {
        try {
            this.zzale.await();
            return true;
        } catch (Throwable e) {
            zzb.zzd("Interrupted during GADSignals creation.", e);
            return false;
        }
    }
}
