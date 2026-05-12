package com.google.android.gms.internal;

import android.graphics.Bitmap;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@zzin
public class zzkp {
    Map<Integer, Bitmap> zzcmp = new ConcurrentHashMap();
    private AtomicInteger zzcmq = new AtomicInteger(0);

    public Bitmap zza(Integer num) {
        return (Bitmap) this.zzcmp.get(num);
    }

    public int zzb(Bitmap bitmap) {
        if (bitmap == null) {
            zzb.zzcv("Bitmap is null. Skipping putting into the Memory Map.");
            return -1;
        }
        this.zzcmp.put(Integer.valueOf(this.zzcmq.get()), bitmap);
        return this.zzcmq.getAndIncrement();
    }

    public void zzb(Integer num) {
        this.zzcmp.remove(num);
    }
}
