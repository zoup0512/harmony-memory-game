package com.google.android.gms.internal;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class zzkg$5 implements ThreadFactory {
    private final AtomicInteger zzcla = new AtomicInteger(1);
    final /* synthetic */ String zzclb;

    zzkg$5(String str) {
        this.zzclb = str;
    }

    public Thread newThread(Runnable runnable) {
        String str = this.zzclb;
        return new Thread(runnable, new StringBuilder(String.valueOf(str).length() + 23).append("AdWorker(").append(str).append(") #").append(this.zzcla.getAndIncrement()).toString());
    }
}
