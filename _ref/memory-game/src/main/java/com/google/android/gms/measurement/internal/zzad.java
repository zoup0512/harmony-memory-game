package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzd.zzb;
import com.google.android.gms.common.internal.zzd.zzc;
import com.google.android.gms.common.util.zze;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class zzad extends zzaa {
    private final zza alO;
    private zzm alP;
    private Boolean alQ;
    private final zzf alR;
    private final zzah alS;
    private final List<Runnable> alT = new ArrayList();
    private final zzf alU;

    protected class zza implements ServiceConnection, zzb, zzc {
        final /* synthetic */ zzad alV;
        private volatile boolean alX;
        private volatile zzo alY;

        protected zza(zzad com_google_android_gms_measurement_internal_zzad) {
            this.alV = com_google_android_gms_measurement_internal_zzad;
        }

        @MainThread
        public void onConnected(@Nullable Bundle bundle) {
            zzab.zzhi("MeasurementServiceConnection.onConnected");
            synchronized (this) {
                try {
                    final zzm com_google_android_gms_measurement_internal_zzm = (zzm) this.alY.zzasa();
                    this.alY = null;
                    this.alV.zzbsc().zzm(new Runnable(this) {
                        final /* synthetic */ zza ama;

                        public void run() {
                            synchronized (this.ama) {
                                this.ama.alX = false;
                                if (!this.ama.alV.isConnected()) {
                                    this.ama.alV.zzbsd().zzbtb().log("Connected to remote service");
                                    this.ama.alV.zza(com_google_android_gms_measurement_internal_zzm);
                                }
                            }
                        }
                    });
                } catch (DeadObjectException e) {
                    this.alY = null;
                    this.alX = false;
                } catch (IllegalStateException e2) {
                    this.alY = null;
                    this.alX = false;
                }
            }
        }

        @MainThread
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            zzab.zzhi("MeasurementServiceConnection.onConnectionFailed");
            zzp zzbtp = this.alV.ahD.zzbtp();
            if (zzbtp != null) {
                zzbtp.zzbsx().zzj("Service connection failed", connectionResult);
            }
            synchronized (this) {
                this.alX = false;
                this.alY = null;
            }
        }

        @MainThread
        public void onConnectionSuspended(int i) {
            zzab.zzhi("MeasurementServiceConnection.onConnectionSuspended");
            this.alV.zzbsd().zzbtb().log("Service connection suspended");
            this.alV.zzbsc().zzm(new Runnable(this) {
                final /* synthetic */ zza ama;

                {
                    this.ama = r1;
                }

                public void run() {
                    this.ama.alV.onServiceDisconnected(new ComponentName(this.ama.alV.getContext(), "com.google.android.gms.measurement.AppMeasurementService"));
                }
            });
        }

        @MainThread
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            zzab.zzhi("MeasurementServiceConnection.onServiceConnected");
            synchronized (this) {
                if (iBinder == null) {
                    this.alX = false;
                    this.alV.zzbsd().zzbsv().log("Service connected with null binder");
                    return;
                }
                zzm com_google_android_gms_measurement_internal_zzm = null;
                try {
                    String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                    if ("com.google.android.gms.measurement.internal.IMeasurementService".equals(interfaceDescriptor)) {
                        com_google_android_gms_measurement_internal_zzm = com.google.android.gms.measurement.internal.zzm.zza.zzjf(iBinder);
                        this.alV.zzbsd().zzbtc().log("Bound to IMeasurementService interface");
                    } else {
                        this.alV.zzbsd().zzbsv().zzj("Got binder with a wrong descriptor", interfaceDescriptor);
                    }
                } catch (RemoteException e) {
                    this.alV.zzbsd().zzbsv().log("Service connect failed to get IMeasurementService");
                }
                if (com_google_android_gms_measurement_internal_zzm == null) {
                    this.alX = false;
                    try {
                        com.google.android.gms.common.stats.zzb.zzaux().zza(this.alV.getContext(), this.alV.alO);
                    } catch (IllegalArgumentException e2) {
                    }
                } else {
                    this.alV.zzbsc().zzm(new Runnable(this) {
                        final /* synthetic */ zza ama;

                        public void run() {
                            synchronized (this.ama) {
                                this.ama.alX = false;
                                if (!this.ama.alV.isConnected()) {
                                    this.ama.alV.zzbsd().zzbtc().log("Connected to service");
                                    this.ama.alV.zza(com_google_android_gms_measurement_internal_zzm);
                                }
                            }
                        }
                    });
                }
            }
        }

        @MainThread
        public void onServiceDisconnected(final ComponentName componentName) {
            zzab.zzhi("MeasurementServiceConnection.onServiceDisconnected");
            this.alV.zzbsd().zzbtb().log("Service disconnected");
            this.alV.zzbsc().zzm(new Runnable(this) {
                final /* synthetic */ zza ama;

                public void run() {
                    this.ama.alV.onServiceDisconnected(componentName);
                }
            });
        }

        @WorkerThread
        public void zzbuw() {
            this.alV.zzwu();
            Context context = this.alV.getContext();
            synchronized (this) {
                if (this.alX) {
                    this.alV.zzbsd().zzbtc().log("Connection attempt already in progress");
                } else if (this.alY != null) {
                    this.alV.zzbsd().zzbtc().log("Already awaiting connection attempt");
                } else {
                    this.alY = new zzo(context, Looper.getMainLooper(), this, this);
                    this.alV.zzbsd().zzbtc().log("Connecting to remote service");
                    this.alX = true;
                    this.alY.zzarx();
                }
            }
        }

        @WorkerThread
        public void zzy(Intent intent) {
            this.alV.zzwu();
            Context context = this.alV.getContext();
            com.google.android.gms.common.stats.zzb zzaux = com.google.android.gms.common.stats.zzb.zzaux();
            synchronized (this) {
                if (this.alX) {
                    this.alV.zzbsd().zzbtc().log("Connection attempt already in progress");
                    return;
                }
                this.alX = true;
                zzaux.zza(context, intent, this.alV.alO, 129);
            }
        }
    }

    protected zzad(zzx com_google_android_gms_measurement_internal_zzx) {
        super(com_google_android_gms_measurement_internal_zzx);
        this.alS = new zzah(com_google_android_gms_measurement_internal_zzx.zzyw());
        this.alO = new zza(this);
        this.alR = new zzf(this, com_google_android_gms_measurement_internal_zzx) {
            final /* synthetic */ zzad alV;

            public void run() {
                this.alV.zzzu();
            }
        };
        this.alU = new zzf(this, com_google_android_gms_measurement_internal_zzx) {
            final /* synthetic */ zzad alV;

            public void run() {
                this.alV.zzbsd().zzbsx().log("Tasks have been queued for a long time");
            }
        };
    }

    @WorkerThread
    private void onServiceDisconnected(ComponentName componentName) {
        zzwu();
        if (this.alP != null) {
            this.alP = null;
            zzbsd().zzbtc().zzj("Disconnected from device MeasurementService", componentName);
            zzbuu();
        }
    }

    @WorkerThread
    private void zza(zzm com_google_android_gms_measurement_internal_zzm) {
        zzwu();
        zzab.zzy(com_google_android_gms_measurement_internal_zzm);
        this.alP = com_google_android_gms_measurement_internal_zzm;
        zzzt();
        zzbuv();
    }

    private boolean zzbus() {
        List queryIntentServices = getContext().getPackageManager().queryIntentServices(new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
        return queryIntentServices != null && queryIntentServices.size() > 0;
    }

    @WorkerThread
    private void zzbuu() {
        zzwu();
        zzaai();
    }

    @WorkerThread
    private void zzbuv() {
        zzwu();
        zzbsd().zzbtc().zzj("Processing queued up service tasks", Integer.valueOf(this.alT.size()));
        for (Runnable zzm : this.alT) {
            zzbsc().zzm(zzm);
        }
        this.alT.clear();
        this.alU.cancel();
    }

    @WorkerThread
    private void zzo(Runnable runnable) throws IllegalStateException {
        zzwu();
        if (isConnected()) {
            runnable.run();
        } else if (((long) this.alT.size()) >= zzbsf().zzbrh()) {
            zzbsd().zzbsv().log("Discarding data. Max runnable queue size reached");
        } else {
            this.alT.add(runnable);
            if (!this.ahD.zzbty()) {
                this.alU.zzv(60000);
            }
            zzaai();
        }
    }

    @WorkerThread
    private void zzzt() {
        zzwu();
        this.alS.start();
        if (!this.ahD.zzbty()) {
            this.alR.zzv(zzbsf().zzabx());
        }
    }

    @WorkerThread
    private void zzzu() {
        zzwu();
        if (isConnected()) {
            zzbsd().zzbtc().log("Inactivity, disconnecting from AppMeasurementService");
            disconnect();
        }
    }

    @WorkerThread
    public void disconnect() {
        zzwu();
        zzzg();
        try {
            com.google.android.gms.common.stats.zzb.zzaux().zza(getContext(), this.alO);
        } catch (IllegalStateException e) {
        } catch (IllegalArgumentException e2) {
        }
        this.alP = null;
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public boolean isConnected() {
        zzwu();
        zzzg();
        return this.alP != null;
    }

    @WorkerThread
    protected void zza(final UserAttributeParcel userAttributeParcel) {
        zzwu();
        zzzg();
        zzo(new Runnable(this) {
            final /* synthetic */ zzad alV;

            public void run() {
                zzm zzc = this.alV.alP;
                if (zzc == null) {
                    this.alV.zzbsd().zzbsv().log("Discarding data. Failed to set user attribute");
                    return;
                }
                try {
                    zzc.zza(userAttributeParcel, this.alV.zzbrv().zzlv(this.alV.zzbsd().zzbtd()));
                    this.alV.zzzt();
                } catch (RemoteException e) {
                    this.alV.zzbsd().zzbsv().zzj("Failed to send attribute to AppMeasurementService", e);
                }
            }
        });
    }

    @WorkerThread
    protected void zza(final AtomicReference<List<UserAttributeParcel>> atomicReference, final boolean z) {
        zzwu();
        zzzg();
        zzo(new Runnable(this) {
            final /* synthetic */ zzad alV;

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                r5 = this;
                r1 = r2;
                monitor-enter(r1);
                r0 = r5.alV;	 Catch:{ RemoteException -> 0x0046 }
                r0 = r0.alP;	 Catch:{ RemoteException -> 0x0046 }
                if (r0 != 0) goto L_0x0021;
            L_0x000b:
                r0 = r5.alV;	 Catch:{ RemoteException -> 0x0046 }
                r0 = r0.zzbsd();	 Catch:{ RemoteException -> 0x0046 }
                r0 = r0.zzbsv();	 Catch:{ RemoteException -> 0x0046 }
                r2 = "Failed to get user properties";
                r0.log(r2);	 Catch:{ RemoteException -> 0x0046 }
                r0 = r2;	 Catch:{ all -> 0x0043 }
                r0.notify();	 Catch:{ all -> 0x0043 }
                monitor-exit(r1);	 Catch:{ all -> 0x0043 }
            L_0x0020:
                return;
            L_0x0021:
                r2 = r2;	 Catch:{ RemoteException -> 0x0046 }
                r3 = r5.alV;	 Catch:{ RemoteException -> 0x0046 }
                r3 = r3.zzbrv();	 Catch:{ RemoteException -> 0x0046 }
                r4 = 0;
                r3 = r3.zzlv(r4);	 Catch:{ RemoteException -> 0x0046 }
                r4 = r3;	 Catch:{ RemoteException -> 0x0046 }
                r0 = r0.zza(r3, r4);	 Catch:{ RemoteException -> 0x0046 }
                r2.set(r0);	 Catch:{ RemoteException -> 0x0046 }
                r0 = r5.alV;	 Catch:{ RemoteException -> 0x0046 }
                r0.zzzt();	 Catch:{ RemoteException -> 0x0046 }
                r0 = r2;	 Catch:{ all -> 0x0043 }
                r0.notify();	 Catch:{ all -> 0x0043 }
            L_0x0041:
                monitor-exit(r1);	 Catch:{ all -> 0x0043 }
                goto L_0x0020;
            L_0x0043:
                r0 = move-exception;
                monitor-exit(r1);	 Catch:{ all -> 0x0043 }
                throw r0;
            L_0x0046:
                r0 = move-exception;
                r2 = r5.alV;	 Catch:{ all -> 0x005c }
                r2 = r2.zzbsd();	 Catch:{ all -> 0x005c }
                r2 = r2.zzbsv();	 Catch:{ all -> 0x005c }
                r3 = "Failed to get user properties";
                r2.zzj(r3, r0);	 Catch:{ all -> 0x005c }
                r0 = r2;	 Catch:{ all -> 0x0043 }
                r0.notify();	 Catch:{ all -> 0x0043 }
                goto L_0x0041;
            L_0x005c:
                r0 = move-exception;
                r2 = r2;	 Catch:{ all -> 0x0043 }
                r2.notify();	 Catch:{ all -> 0x0043 }
                throw r0;	 Catch:{ all -> 0x0043 }
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzad.6.run():void");
            }
        });
    }

    @WorkerThread
    void zzaai() {
        zzwu();
        zzzg();
        if (!isConnected()) {
            if (this.alQ == null) {
                this.alQ = zzbse().zzbtj();
                if (this.alQ == null) {
                    zzbsd().zzbtc().log("State of service unknown");
                    this.alQ = Boolean.valueOf(zzbut());
                    zzbse().zzcb(this.alQ.booleanValue());
                }
            }
            if (this.alQ.booleanValue()) {
                zzbsd().zzbtc().log("Using measurement service");
                this.alO.zzbuw();
            } else if (!this.ahD.zzbty() && zzbus()) {
                zzbsd().zzbtc().log("Using local app measurement service");
                Intent intent = new Intent("com.google.android.gms.measurement.START");
                intent.setComponent(new ComponentName(getContext(), "com.google.android.gms.measurement.AppMeasurementService"));
                this.alO.zzy(intent);
            } else if (zzbsf().zzabd()) {
                zzbsd().zzbtc().log("Using direct local measurement implementation");
                zza(new zzy(this.ahD, true));
            } else {
                zzbsd().zzbsv().log("Not in main process. Unable to use local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            }
        }
    }

    public /* bridge */ /* synthetic */ void zzbrs() {
        super.zzbrs();
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

    @WorkerThread
    protected void zzbuo() {
        zzwu();
        zzzg();
        zzo(new Runnable(this) {
            final /* synthetic */ zzad alV;

            {
                this.alV = r1;
            }

            public void run() {
                zzm zzc = this.alV.alP;
                if (zzc == null) {
                    this.alV.zzbsd().zzbsv().log("Discarding data. Failed to send app launch");
                    return;
                }
                try {
                    zzc.zza(this.alV.zzbrv().zzlv(this.alV.zzbsd().zzbtd()));
                    this.alV.zzzt();
                } catch (RemoteException e) {
                    this.alV.zzbsd().zzbsv().zzj("Failed to send app launch to AppMeasurementService", e);
                }
            }
        });
    }

    @WorkerThread
    protected void zzbur() {
        zzwu();
        zzzg();
        zzo(new Runnable(this) {
            final /* synthetic */ zzad alV;

            {
                this.alV = r1;
            }

            public void run() {
                zzm zzc = this.alV.alP;
                if (zzc == null) {
                    this.alV.zzbsd().zzbsv().log("Failed to send measurementEnabled to service");
                    return;
                }
                try {
                    zzc.zzb(this.alV.zzbrv().zzlv(this.alV.zzbsd().zzbtd()));
                    this.alV.zzzt();
                } catch (RemoteException e) {
                    this.alV.zzbsd().zzbsv().zzj("Failed to send measurementEnabled to AppMeasurementService", e);
                }
            }
        });
    }

    @WorkerThread
    protected boolean zzbut() {
        zzwu();
        zzzg();
        if (zzbsf().zzabc()) {
            return true;
        }
        zzbsd().zzbtc().log("Checking service availability");
        switch (com.google.android.gms.common.zzc.zzang().isGooglePlayServicesAvailable(getContext())) {
            case 0:
                zzbsd().zzbtc().log("Service available");
                return true;
            case 1:
                zzbsd().zzbtc().log("Service missing");
                return false;
            case 2:
                zzbsd().zzbtb().log("Service container out of date");
                return true;
            case 3:
                zzbsd().zzbsx().log("Service disabled");
                return false;
            case 9:
                zzbsd().zzbsx().log("Service invalid");
                return false;
            case 18:
                zzbsd().zzbsx().log("Service updating");
                return true;
            default:
                return false;
        }
    }

    @WorkerThread
    protected void zzc(final EventParcel eventParcel, final String str) {
        zzab.zzy(eventParcel);
        zzwu();
        zzzg();
        zzo(new Runnable(this) {
            final /* synthetic */ zzad alV;

            public void run() {
                zzm zzc = this.alV.alP;
                if (zzc == null) {
                    this.alV.zzbsd().zzbsv().log("Discarding data. Failed to send event to service");
                    return;
                }
                try {
                    if (TextUtils.isEmpty(str)) {
                        zzc.zza(eventParcel, this.alV.zzbrv().zzlv(this.alV.zzbsd().zzbtd()));
                    } else {
                        zzc.zza(eventParcel, str, this.alV.zzbsd().zzbtd());
                    }
                    this.alV.zzzt();
                } catch (RemoteException e) {
                    this.alV.zzbsd().zzbsv().zzj("Failed to send event to AppMeasurementService", e);
                }
            }
        });
    }

    public /* bridge */ /* synthetic */ void zzwu() {
        super.zzwu();
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
