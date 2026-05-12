package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import com.google.android.gms.internal.zzae.zza;
import com.mopub.volley.DefaultRetryPolicy;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zzao implements zzan {
    protected MotionEvent zzafd;
    protected LinkedList<MotionEvent> zzafe = new LinkedList();
    protected long zzaff = 0;
    protected long zzafg = 0;
    protected long zzafh = 0;
    protected long zzafi = 0;
    protected long zzafj = 0;
    private boolean zzafk = false;
    protected DisplayMetrics zzafl;

    protected zzao(Context context) {
        zzak.zzar();
        try {
            this.zzafl = context.getResources().getDisplayMetrics();
        } catch (UnsupportedOperationException e) {
            this.zzafl = new DisplayMetrics();
            this.zzafl.density = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
    }

    private String zza(Context context, String str, boolean z) {
        boolean z2 = true;
        if (z) {
            try {
                zza zzd = zzd(context);
                this.zzafk = true;
            } catch (NoSuchAlgorithmException e) {
                return Integer.toString(7);
            } catch (UnsupportedEncodingException e2) {
                return Integer.toString(7);
            } catch (Throwable th) {
                return Integer.toString(3);
            }
        }
        zzd = zzc(context);
        if (zzd == null || zzd.aM() == 0) {
            return Integer.toString(5);
        }
        if (zzb(z)) {
            z2 = false;
        }
        return zzak.zza(zzd, str, z2);
    }

    private void zzav() {
        if (((Boolean) zzdc.zzbbt.get()).booleanValue()) {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            int i = 0;
            int length = stackTrace.length - 1;
            while (length >= 0) {
                i++;
                if (stackTrace[length].toString().startsWith("com.google.android.ads.") || stackTrace[length].toString().startsWith("com.google.android.gms.")) {
                    break;
                }
                length--;
            }
            this.zzafj = (long) i;
        }
    }

    private static boolean zzb(boolean z) {
        return !((Boolean) zzdc.zzbbl.get()).booleanValue() ? true : ((Boolean) zzdc.zzbbu.get()).booleanValue() && z;
    }

    public void zza(int i, int i2, int i3) {
        if (this.zzafd != null) {
            this.zzafd.recycle();
        }
        this.zzafd = MotionEvent.obtain(0, (long) i3, 1, ((float) i) * this.zzafl.density, ((float) i2) * this.zzafl.density, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
    }

    public void zza(MotionEvent motionEvent) {
        if (this.zzafk) {
            this.zzafi = 0;
            this.zzafh = 0;
            this.zzafg = 0;
            this.zzaff = 0;
            this.zzafj = 0;
            Iterator it = this.zzafe.iterator();
            while (it.hasNext()) {
                ((MotionEvent) it.next()).recycle();
            }
            this.zzafe.clear();
            this.zzafd = null;
            this.zzafk = false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.zzaff++;
                return;
            case 1:
                this.zzafd = MotionEvent.obtain(motionEvent);
                this.zzafe.add(this.zzafd);
                if (this.zzafe.size() > 6) {
                    ((MotionEvent) this.zzafe.remove()).recycle();
                }
                this.zzafh++;
                zzav();
                return;
            case 2:
                this.zzafg += (long) (motionEvent.getHistorySize() + 1);
                return;
            case 3:
                this.zzafi++;
                return;
            default:
                return;
        }
    }

    public String zzb(Context context) {
        return zza(context, null, false);
    }

    public String zzb(Context context, String str) {
        return zza(context, str, true);
    }

    protected abstract zza zzc(Context context);

    protected abstract zza zzd(Context context);
}
