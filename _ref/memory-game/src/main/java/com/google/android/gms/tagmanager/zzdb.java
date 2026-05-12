package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

class zzdb extends zzda {
    private static final Object axN = new Object();
    private static zzdb axZ;
    private Context axO;
    private zzav axP;
    private volatile zzat axQ;
    private int axR = 1800000;
    private boolean axS = true;
    private boolean axT = false;
    private boolean axU = true;
    private zzaw axV = new zzaw(this) {
        final /* synthetic */ zzdb aya;

        {
            this.aya = r1;
        }

        public void zzch(boolean z) {
            this.aya.zze(z, this.aya.connected);
        }
    };
    private zza axW;
    private zzbs axX;
    private boolean axY = false;
    private boolean connected = true;

    public interface zza {
        void cancel();

        void zzcdh();

        void zzv(long j);
    }

    private class zzb implements zza {
        final /* synthetic */ zzdb aya;
        private Handler handler;

        private zzb(zzdb com_google_android_gms_tagmanager_zzdb) {
            this.aya = com_google_android_gms_tagmanager_zzdb;
            this.handler = new Handler(this.aya.axO.getMainLooper(), new Callback(this) {
                final /* synthetic */ zzb ayb;

                {
                    this.ayb = r1;
                }

                public boolean handleMessage(Message message) {
                    if (1 == message.what && zzdb.axN.equals(message.obj)) {
                        this.ayb.aya.dispatch();
                        if (!this.ayb.aya.isPowerSaveMode()) {
                            this.ayb.zzv((long) this.ayb.aya.axR);
                        }
                    }
                    return true;
                }
            });
        }

        private Message obtainMessage() {
            return this.handler.obtainMessage(1, zzdb.axN);
        }

        public void cancel() {
            this.handler.removeMessages(1, zzdb.axN);
        }

        public void zzcdh() {
            this.handler.removeMessages(1, zzdb.axN);
            this.handler.sendMessage(obtainMessage());
        }

        public void zzv(long j) {
            this.handler.removeMessages(1, zzdb.axN);
            this.handler.sendMessageDelayed(obtainMessage(), j);
        }
    }

    private zzdb() {
    }

    private boolean isPowerSaveMode() {
        return this.axY || !this.connected || this.axR <= 0;
    }

    private void zzaam() {
        if (isPowerSaveMode()) {
            this.axW.cancel();
            zzbn.v("PowerSaveMode initiated.");
            return;
        }
        this.axW.zzv((long) this.axR);
        zzbn.v("PowerSaveMode terminated.");
    }

    public static zzdb zzcdc() {
        if (axZ == null) {
            axZ = new zzdb();
        }
        return axZ;
    }

    private void zzcdd() {
        this.axX = new zzbs(this);
        this.axX.zzed(this.axO);
    }

    private void zzcde() {
        this.axW = new zzb();
        if (this.axR > 0) {
            this.axW.zzv((long) this.axR);
        }
    }

    public synchronized void dispatch() {
        if (this.axT) {
            this.axQ.zzp(new Runnable(this) {
                final /* synthetic */ zzdb aya;

                {
                    this.aya = r1;
                }

                public void run() {
                    this.aya.axP.dispatch();
                }
            });
        } else {
            zzbn.v("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.axS = true;
        }
    }

    synchronized void zza(Context context, zzat com_google_android_gms_tagmanager_zzat) {
        if (this.axO == null) {
            this.axO = context.getApplicationContext();
            if (this.axQ == null) {
                this.axQ = com_google_android_gms_tagmanager_zzat;
            }
        }
    }

    synchronized zzav zzcdf() {
        if (this.axP == null) {
            if (this.axO == null) {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
            this.axP = new zzcf(this.axV, this.axO);
        }
        if (this.axW == null) {
            zzcde();
        }
        this.axT = true;
        if (this.axS) {
            dispatch();
            this.axS = false;
        }
        if (this.axX == null && this.axU) {
            zzcdd();
        }
        return this.axP;
    }

    public synchronized void zzci(boolean z) {
        zze(this.axY, z);
    }

    synchronized void zze(boolean z, boolean z2) {
        boolean isPowerSaveMode = isPowerSaveMode();
        this.axY = z;
        this.connected = z2;
        if (isPowerSaveMode() != isPowerSaveMode) {
            zzaam();
        }
    }

    public synchronized void zzys() {
        if (!isPowerSaveMode()) {
            this.axW.zzcdh();
        }
    }
}
