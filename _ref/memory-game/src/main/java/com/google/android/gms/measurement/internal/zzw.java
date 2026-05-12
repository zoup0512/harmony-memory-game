package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

public class zzw extends zzaa {
    private static final AtomicLong akI = new AtomicLong(Long.MIN_VALUE);
    private zzd akA;
    private final PriorityBlockingQueue<FutureTask<?>> akB = new PriorityBlockingQueue();
    private final BlockingQueue<FutureTask<?>> akC = new LinkedBlockingQueue();
    private final UncaughtExceptionHandler akD = new zzb(this, "Thread death: Uncaught exception on worker thread");
    private final UncaughtExceptionHandler akE = new zzb(this, "Thread death: Uncaught exception on network thread");
    private final Object akF = new Object();
    private final Semaphore akG = new Semaphore(2);
    private volatile boolean akH;
    private zzd akz;

    static class zza extends RuntimeException {
    }

    private final class zzb implements UncaughtExceptionHandler {
        private final String akJ;
        final /* synthetic */ zzw akK;

        public zzb(zzw com_google_android_gms_measurement_internal_zzw, String str) {
            this.akK = com_google_android_gms_measurement_internal_zzw;
            zzab.zzy(str);
            this.akJ = str;
        }

        public synchronized void uncaughtException(Thread thread, Throwable th) {
            this.akK.zzbsd().zzbsv().zzj(this.akJ, th);
        }
    }

    private final class zzc<V> extends FutureTask<V> implements Comparable<zzc> {
        private final String akJ;
        final /* synthetic */ zzw akK;
        private final long akL = zzw.akI.getAndIncrement();
        private final boolean akM;

        zzc(zzw com_google_android_gms_measurement_internal_zzw, Runnable runnable, boolean z, String str) {
            this.akK = com_google_android_gms_measurement_internal_zzw;
            super(runnable, null);
            zzab.zzy(str);
            this.akJ = str;
            this.akM = z;
            if (this.akL == Long.MAX_VALUE) {
                com_google_android_gms_measurement_internal_zzw.zzbsd().zzbsv().log("Tasks index overflow");
            }
        }

        zzc(zzw com_google_android_gms_measurement_internal_zzw, Callable<V> callable, boolean z, String str) {
            this.akK = com_google_android_gms_measurement_internal_zzw;
            super(callable);
            zzab.zzy(str);
            this.akJ = str;
            this.akM = z;
            if (this.akL == Long.MAX_VALUE) {
                com_google_android_gms_measurement_internal_zzw.zzbsd().zzbsv().log("Tasks index overflow");
            }
        }

        public /* synthetic */ int compareTo(Object obj) {
            return zzb((zzc) obj);
        }

        protected void setException(Throwable th) {
            this.akK.zzbsd().zzbsv().zzj(this.akJ, th);
            if (th instanceof zza) {
                Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
            }
            super.setException(th);
        }

        public int zzb(zzc com_google_android_gms_measurement_internal_zzw_zzc) {
            if (this.akM != com_google_android_gms_measurement_internal_zzw_zzc.akM) {
                return this.akM ? -1 : 1;
            } else {
                if (this.akL < com_google_android_gms_measurement_internal_zzw_zzc.akL) {
                    return -1;
                }
                if (this.akL > com_google_android_gms_measurement_internal_zzw_zzc.akL) {
                    return 1;
                }
                this.akK.zzbsd().zzbsw().zzj("Two tasks share the same index. index", Long.valueOf(this.akL));
                return 0;
            }
        }
    }

    private final class zzd extends Thread {
        final /* synthetic */ zzw akK;
        private final Object akN = new Object();
        private final BlockingQueue<FutureTask<?>> akO;

        public zzd(zzw com_google_android_gms_measurement_internal_zzw, String str, BlockingQueue<FutureTask<?>> blockingQueue) {
            this.akK = com_google_android_gms_measurement_internal_zzw;
            zzab.zzy(str);
            zzab.zzy(blockingQueue);
            this.akO = blockingQueue;
            setName(str);
        }

        private void zza(InterruptedException interruptedException) {
            this.akK.zzbsd().zzbsx().zzj(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
        }

        public void run() {
            Object obj = null;
            while (obj == null) {
                try {
                    this.akK.akG.acquire();
                    obj = 1;
                } catch (InterruptedException e) {
                    zza(e);
                }
            }
            while (true) {
                try {
                    FutureTask futureTask = (FutureTask) this.akO.poll();
                    if (futureTask != null) {
                        futureTask.run();
                    } else {
                        synchronized (this.akN) {
                            if (this.akO.peek() == null && !this.akK.akH) {
                                try {
                                    this.akN.wait(30000);
                                } catch (InterruptedException e2) {
                                    zza(e2);
                                }
                            }
                        }
                        synchronized (this.akK.akF) {
                            if (this.akO.peek() == null) {
                                break;
                            }
                        }
                    }
                } catch (Throwable th) {
                    synchronized (this.akK.akF) {
                        this.akK.akG.release();
                        this.akK.akF.notifyAll();
                        if (this == this.akK.akz) {
                            this.akK.akz = null;
                        } else if (this == this.akK.akA) {
                            this.akK.akA = null;
                        } else {
                            this.akK.zzbsd().zzbsv().log("Current scheduler thread is neither worker nor network");
                        }
                    }
                }
            }
            synchronized (this.akK.akF) {
                this.akK.akG.release();
                this.akK.akF.notifyAll();
                if (this == this.akK.akz) {
                    this.akK.akz = null;
                } else if (this == this.akK.akA) {
                    this.akK.akA = null;
                } else {
                    this.akK.zzbsd().zzbsv().log("Current scheduler thread is neither worker nor network");
                }
            }
        }

        public void zznk() {
            synchronized (this.akN) {
                this.akN.notifyAll();
            }
        }
    }

    zzw(zzx com_google_android_gms_measurement_internal_zzx) {
        super(com_google_android_gms_measurement_internal_zzx);
    }

    private void zza(zzc<?> com_google_android_gms_measurement_internal_zzw_zzc_) {
        synchronized (this.akF) {
            this.akB.add(com_google_android_gms_measurement_internal_zzw_zzc_);
            if (this.akz == null) {
                this.akz = new zzd(this, "Measurement Worker", this.akB);
                this.akz.setUncaughtExceptionHandler(this.akD);
                this.akz.start();
            } else {
                this.akz.zznk();
            }
        }
    }

    private void zza(FutureTask<?> futureTask) {
        synchronized (this.akF) {
            this.akC.add(futureTask);
            if (this.akA == null) {
                this.akA = new zzd(this, "Measurement Network", this.akC);
                this.akA.setUncaughtExceptionHandler(this.akE);
                this.akA.start();
            } else {
                this.akA.zznk();
            }
        }
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public void zzbrs() {
        if (Thread.currentThread() != this.akA) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    public /* bridge */ /* synthetic */ zzc zzbrt() {
        return super.zzbrt();
    }

    public /* bridge */ /* synthetic */ zzac zzbru() {
        return super.zzbru();
    }

    public /* bridge */ /* synthetic */ zzn zzbrv() {
        return super.zzbrv();
    }

    public /* bridge */ /* synthetic */ zzg zzbrw() {
        return super.zzbrw();
    }

    public /* bridge */ /* synthetic */ zzad zzbrx() {
        return super.zzbrx();
    }

    public /* bridge */ /* synthetic */ zze zzbry() {
        return super.zzbry();
    }

    public /* bridge */ /* synthetic */ zzal zzbrz() {
        return super.zzbrz();
    }

    public /* bridge */ /* synthetic */ zzv zzbsa() {
        return super.zzbsa();
    }

    public /* bridge */ /* synthetic */ zzaf zzbsb() {
        return super.zzbsb();
    }

    public /* bridge */ /* synthetic */ zzw zzbsc() {
        return super.zzbsc();
    }

    public /* bridge */ /* synthetic */ zzp zzbsd() {
        return super.zzbsd();
    }

    public /* bridge */ /* synthetic */ zzt zzbse() {
        return super.zzbse();
    }

    public /* bridge */ /* synthetic */ zzd zzbsf() {
        return super.zzbsf();
    }

    public <V> Future<V> zzd(Callable<V> callable) throws IllegalStateException {
        zzzg();
        zzab.zzy(callable);
        zzc com_google_android_gms_measurement_internal_zzw_zzc = new zzc(this, (Callable) callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.akz) {
            com_google_android_gms_measurement_internal_zzw_zzc.run();
        } else {
            zza(com_google_android_gms_measurement_internal_zzw_zzc);
        }
        return com_google_android_gms_measurement_internal_zzw_zzc;
    }

    public <V> Future<V> zze(Callable<V> callable) throws IllegalStateException {
        zzzg();
        zzab.zzy(callable);
        zzc com_google_android_gms_measurement_internal_zzw_zzc = new zzc(this, (Callable) callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.akz) {
            com_google_android_gms_measurement_internal_zzw_zzc.run();
        } else {
            zza(com_google_android_gms_measurement_internal_zzw_zzc);
        }
        return com_google_android_gms_measurement_internal_zzw_zzc;
    }

    public void zzm(Runnable runnable) throws IllegalStateException {
        zzzg();
        zzab.zzy(runnable);
        zza(new zzc(this, runnable, false, "Task exception on worker thread"));
    }

    public void zzn(Runnable runnable) throws IllegalStateException {
        zzzg();
        zzab.zzy(runnable);
        zza(new zzc(this, runnable, false, "Task exception on network thread"));
    }

    public void zzwu() {
        if (Thread.currentThread() != this.akz) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    protected void zzwv() {
    }

    public /* bridge */ /* synthetic */ void zzyv() {
        super.zzyv();
    }

    public /* bridge */ /* synthetic */ zze zzyw() {
        return super.zzyw();
    }
}
