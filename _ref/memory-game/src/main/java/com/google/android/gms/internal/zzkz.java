package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zza;
import java.util.ArrayList;
import java.util.List;

@zzin
class zzkz {
    private final Object zzcnx = new Object();
    private final List<Runnable> zzcny = new ArrayList();
    private final List<Runnable> zzcnz = new ArrayList();
    private boolean zzcoa = false;

    private void zze(Runnable runnable) {
        zzkg.zza(runnable);
    }

    private void zzf(Runnable runnable) {
        zza.zzcnb.post(runnable);
    }

    public void zzc(Runnable runnable) {
        synchronized (this.zzcnx) {
            if (this.zzcoa) {
                zze(runnable);
            } else {
                this.zzcny.add(runnable);
            }
        }
    }

    public void zzd(Runnable runnable) {
        synchronized (this.zzcnx) {
            if (this.zzcoa) {
                zzf(runnable);
            } else {
                this.zzcnz.add(runnable);
            }
        }
    }

    public void zztz() {
        synchronized (this.zzcnx) {
            if (this.zzcoa) {
                return;
            }
            for (Runnable zze : this.zzcny) {
                zze(zze);
            }
            for (Runnable zze2 : this.zzcnz) {
                zzf(zze2);
            }
            this.zzcny.clear();
            this.zzcnz.clear();
            this.zzcoa = true;
        }
    }
}
