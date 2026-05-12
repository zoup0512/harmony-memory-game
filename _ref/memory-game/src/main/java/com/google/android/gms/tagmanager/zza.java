package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Process;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;

public class zza {
    private static Object aus = new Object();
    private static zza aut;
    private volatile long aum;
    private volatile long aun;
    private volatile long auo;
    private volatile long aup;
    private final Object auq;
    private zza aur;
    private volatile boolean mClosed;
    private final Context mContext;
    private final zze zzaoc;
    private final Thread zzckk;
    private volatile Info zzcwc;

    public interface zza {
        Info zzcaf();
    }

    private zza(Context context) {
        this(context, null, zzh.zzavm());
    }

    public zza(Context context, zza com_google_android_gms_tagmanager_zza_zza, zze com_google_android_gms_common_util_zze) {
        this.aum = 900000;
        this.aun = 30000;
        this.mClosed = false;
        this.auq = new Object();
        this.aur = new zza(this) {
            final /* synthetic */ zza auu;

            {
                this.auu = r1;
            }

            public Info zzcaf() {
                Info info = null;
                try {
                    info = AdvertisingIdClient.getAdvertisingIdInfo(this.auu.mContext);
                } catch (Throwable e) {
                    zzbn.zzd("IllegalStateException getting Advertising Id Info", e);
                } catch (Throwable e2) {
                    zzbn.zzd("GooglePlayServicesRepairableException getting Advertising Id Info", e2);
                } catch (Throwable e22) {
                    zzbn.zzd("IOException getting Ad Id Info", e22);
                } catch (Throwable e222) {
                    zzbn.zzd("GooglePlayServicesNotAvailableException getting Advertising Id Info", e222);
                } catch (Throwable e2222) {
                    zzbn.zzd("Unknown exception. Could not get the Advertising Id Info.", e2222);
                }
                return info;
            }
        };
        this.zzaoc = com_google_android_gms_common_util_zze;
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        if (com_google_android_gms_tagmanager_zza_zza != null) {
            this.aur = com_google_android_gms_tagmanager_zza_zza;
        }
        this.auo = this.zzaoc.currentTimeMillis();
        this.zzckk = new Thread(new Runnable(this) {
            final /* synthetic */ zza auu;

            {
                this.auu = r1;
            }

            public void run() {
                this.auu.zzcae();
            }
        });
    }

    private void zzcab() {
        synchronized (this) {
            try {
                zzcac();
                wait(500);
            } catch (InterruptedException e) {
            }
        }
    }

    private void zzcac() {
        if (this.zzaoc.currentTimeMillis() - this.auo > this.aun) {
            synchronized (this.auq) {
                this.auq.notify();
            }
            this.auo = this.zzaoc.currentTimeMillis();
        }
    }

    private void zzcad() {
        if (this.zzaoc.currentTimeMillis() - this.aup > 3600000) {
            this.zzcwc = null;
        }
    }

    private void zzcae() {
        Process.setThreadPriority(10);
        while (!this.mClosed) {
            Info zzcaf = this.aur.zzcaf();
            if (zzcaf != null) {
                this.zzcwc = zzcaf;
                this.aup = this.zzaoc.currentTimeMillis();
                zzbn.zzcw("Obtained fresh AdvertisingId info from GmsCore.");
            }
            synchronized (this) {
                notifyAll();
            }
            try {
                synchronized (this.auq) {
                    this.auq.wait(this.aum);
                }
            } catch (InterruptedException e) {
                zzbn.zzcw("sleep interrupted in AdvertiserDataPoller thread; continuing");
            }
        }
    }

    public static zza zzdu(Context context) {
        if (aut == null) {
            synchronized (aus) {
                if (aut == null) {
                    aut = new zza(context);
                    aut.start();
                }
            }
        }
        return aut;
    }

    public boolean isLimitAdTrackingEnabled() {
        if (this.zzcwc == null) {
            zzcab();
        } else {
            zzcac();
        }
        zzcad();
        return this.zzcwc == null ? true : this.zzcwc.isLimitAdTrackingEnabled();
    }

    public void start() {
        this.zzckk.start();
    }

    public String zzcaa() {
        if (this.zzcwc == null) {
            zzcab();
        } else {
            zzcac();
        }
        zzcad();
        return this.zzcwc == null ? null : this.zzcwc.getId();
    }
}
