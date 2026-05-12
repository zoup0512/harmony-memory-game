package com.google.android.gms.internal;

import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zzh;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class zzpb implements com.google.android.gms.clearcut.zzc {
    private static final Object qE = new Object();
    private static ScheduledExecutorService qF;
    private static final zze qG = new zze();
    private static final long qH = TimeUnit.MILLISECONDS.convert(2, TimeUnit.MINUTES);
    private GoogleApiClient gY;
    private final zza qI;
    private final Object qJ;
    private long qK;
    private final long qL;
    private ScheduledFuture<?> qM;
    private final Runnable qN;
    private final com.google.android.gms.common.util.zze zzaoc;

    public interface zza {
    }

    public static class zzb implements zza {
    }

    static abstract class zzc<R extends Result> extends com.google.android.gms.internal.zzpm.zza<R, zzpc> {
        public zzc(GoogleApiClient googleApiClient) {
            super(com.google.android.gms.clearcut.zzb.API, googleApiClient);
        }
    }

    static final class zzd extends zzc<Status> {
        private final LogEventParcelable qS;

        zzd(LogEventParcelable logEventParcelable, GoogleApiClient googleApiClient) {
            super(googleApiClient);
            this.qS = logEventParcelable;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zzd)) {
                return false;
            }
            return this.qS.equals(((zzd) obj).qS);
        }

        public String toString() {
            String valueOf = String.valueOf(this.qS);
            return new StringBuilder(String.valueOf(valueOf).length() + 12).append("MethodImpl(").append(valueOf).append(")").toString();
        }

        protected void zza(zzpc com_google_android_gms_internal_zzpc) throws RemoteException {
            zzpe anonymousClass1 = new com.google.android.gms.internal.zzpe.zza(this) {
                final /* synthetic */ zzd qT;

                {
                    this.qT = r1;
                }

                public void zzw(Status status) {
                    this.qT.zzc(status);
                }
            };
            try {
                zzpb.zza(this.qS);
                com_google_android_gms_internal_zzpc.zza(anonymousClass1, this.qS);
            } catch (Throwable e) {
                Log.e("ClearcutLoggerApiImpl", "derived ClearcutLogger.MessageProducer ", e);
                zzz(new Status(10, "MessageProducer"));
            }
        }

        protected Status zzb(Status status) {
            return status;
        }

        protected /* synthetic */ Result zzc(Status status) {
            return zzb(status);
        }
    }

    private static final class zze {
        private int mSize;

        private zze() {
            this.mSize = 0;
        }

        public synchronized void decrement() {
            if (this.mSize == 0) {
                throw new RuntimeException("too many decrements");
            }
            this.mSize--;
            if (this.mSize == 0) {
                notifyAll();
            }
        }

        public synchronized void increment() {
            this.mSize++;
        }
    }

    public zzpb() {
        this(new zzh(), qH, new zzb());
    }

    public zzpb(com.google.android.gms.common.util.zze com_google_android_gms_common_util_zze, long j, zza com_google_android_gms_internal_zzpb_zza) {
        this.qJ = new Object();
        this.qK = 0;
        this.qM = null;
        this.gY = null;
        this.qN = new Runnable(this) {
            final /* synthetic */ zzpb qO;

            {
                this.qO = r1;
            }

            public void run() {
                synchronized (this.qO.qJ) {
                    if (0 <= this.qO.zzaoc.elapsedRealtime() && this.qO.gY != null) {
                        Log.i("ClearcutLoggerApiImpl", "disconnect managed GoogleApiClient");
                        this.qO.gY.disconnect();
                        this.qO.gY = null;
                    }
                }
            }
        };
        this.zzaoc = com_google_android_gms_common_util_zze;
        this.qL = j;
        this.qI = com_google_android_gms_internal_zzpb_zza;
    }

    private PendingResult<Status> zza(final GoogleApiClient googleApiClient, final zzc<Status> com_google_android_gms_internal_zzpb_zzc_com_google_android_gms_common_api_Status) {
        zzanc().execute(new Runnable(this) {
            final /* synthetic */ zzpb qO;

            public void run() {
                googleApiClient.zzc(com_google_android_gms_internal_zzpb_zzc_com_google_android_gms_common_api_Status);
            }
        });
        return com_google_android_gms_internal_zzpb_zzc_com_google_android_gms_common_api_Status;
    }

    private static void zza(LogEventParcelable logEventParcelable) {
        if (logEventParcelable.qC != null && logEventParcelable.qB.bkh.length == 0) {
            logEventParcelable.qB.bkh = logEventParcelable.qC.zzanb();
        }
        if (logEventParcelable.qD != null && logEventParcelable.qB.bko.length == 0) {
            logEventParcelable.qB.bko = logEventParcelable.qD.zzanb();
        }
        logEventParcelable.qv = zzapv.zzf(logEventParcelable.qB);
    }

    private ScheduledExecutorService zzanc() {
        synchronized (qE) {
            if (qF == null) {
                qF = Executors.newSingleThreadScheduledExecutor(new ThreadFactory(this) {
                    final /* synthetic */ zzpb qO;

                    {
                        this.qO = r1;
                    }

                    public Thread newThread(final Runnable runnable) {
                        return new Thread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 qQ;

                            public void run() {
                                Process.setThreadPriority(10);
                                runnable.run();
                            }
                        }, "ClearcutLoggerApiImpl");
                    }
                });
            }
        }
        return qF;
    }

    private zzd zzb(GoogleApiClient googleApiClient, LogEventParcelable logEventParcelable) {
        qG.increment();
        zzd com_google_android_gms_internal_zzpb_zzd = new zzd(logEventParcelable, googleApiClient);
        com_google_android_gms_internal_zzpb_zzd.zza(new com.google.android.gms.common.api.PendingResult.zza(this) {
            final /* synthetic */ zzpb qO;

            {
                this.qO = r1;
            }

            public void zzv(Status status) {
                zzpb.qG.decrement();
            }
        });
        return com_google_android_gms_internal_zzpb_zzd;
    }

    public PendingResult<Status> zza(GoogleApiClient googleApiClient, LogEventParcelable logEventParcelable) {
        return zza(googleApiClient, zzb(googleApiClient, logEventParcelable));
    }
}
