package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.internal.client.zzy.zza;
import com.google.android.gms.internal.zzin;
import java.util.Random;

@zzin
public class zzn extends zza {
    private Object zzail = new Object();
    private final Random zzavp = new Random();
    private long zzavq;

    public zzn() {
        zziy();
    }

    public long getValue() {
        return this.zzavq;
    }

    public void zziy() {
        synchronized (this.zzail) {
            int i = 3;
            long j = 0;
            while (true) {
                i--;
                if (i <= 0) {
                    break;
                }
                j = ((long) this.zzavp.nextInt()) + 2147483648L;
                if (j != this.zzavq && j != 0) {
                    break;
                }
            }
            this.zzavq = j;
        }
    }
}
