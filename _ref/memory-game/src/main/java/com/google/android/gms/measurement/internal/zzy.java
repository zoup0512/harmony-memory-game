package com.google.android.gms.measurement.internal;

import android.os.Binder;
import android.os.Process;
import android.support.annotation.BinderThread;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.measurement.internal.zzm.zza;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class zzy extends zza {
    private final zzx ahD;
    private final boolean alt;

    public zzy(zzx com_google_android_gms_measurement_internal_zzx) {
        zzab.zzy(com_google_android_gms_measurement_internal_zzx);
        this.ahD = com_google_android_gms_measurement_internal_zzx;
        this.alt = false;
    }

    public zzy(zzx com_google_android_gms_measurement_internal_zzx, boolean z) {
        zzab.zzy(com_google_android_gms_measurement_internal_zzx);
        this.ahD = com_google_android_gms_measurement_internal_zzx;
        this.alt = z;
    }

    @BinderThread
    private void zzf(AppMetadata appMetadata) {
        zzab.zzy(appMetadata);
        zzmf(appMetadata.packageName);
        this.ahD.zzbrz().zzmq(appMetadata.aic);
    }

    @BinderThread
    private void zzmf(String str) throws SecurityException {
        if (TextUtils.isEmpty(str)) {
            this.ahD.zzbsd().zzbsv().log("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        try {
            zzmg(str);
        } catch (SecurityException e) {
            this.ahD.zzbsd().zzbsv().zzj("Measurement Service called with invalid calling package", str);
            throw e;
        }
    }

    @BinderThread
    public List<UserAttributeParcel> zza(final AppMetadata appMetadata, boolean z) {
        Object e;
        zzf(appMetadata);
        try {
            List<zzak> list = (List) this.ahD.zzbsc().zzd(new Callable<List<zzak>>(this) {
                final /* synthetic */ zzy alv;

                public /* synthetic */ Object call() throws Exception {
                    return zzbuk();
                }

                public List<zzak> zzbuk() throws Exception {
                    this.alv.ahD.zzbuh();
                    return this.alv.ahD.zzbry().zzlm(appMetadata.packageName);
                }
            }).get();
            List<UserAttributeParcel> arrayList = new ArrayList(list.size());
            for (zzak com_google_android_gms_measurement_internal_zzak : list) {
                if (z || !zzal.zzmt(com_google_android_gms_measurement_internal_zzak.mName)) {
                    arrayList.add(new UserAttributeParcel(com_google_android_gms_measurement_internal_zzak));
                }
            }
            return arrayList;
        } catch (InterruptedException e2) {
            e = e2;
            this.ahD.zzbsd().zzbsv().zzj("Failed to get user attributes", e);
            return null;
        } catch (ExecutionException e3) {
            e = e3;
            this.ahD.zzbsd().zzbsv().zzj("Failed to get user attributes", e);
            return null;
        }
    }

    @BinderThread
    public void zza(final AppMetadata appMetadata) {
        zzf(appMetadata);
        this.ahD.zzbsc().zzm(new Runnable(this) {
            final /* synthetic */ zzy alv;

            public void run() {
                this.alv.ahD.zzbuh();
                this.alv.zzme(appMetadata.aig);
                this.alv.ahD.zzd(appMetadata);
            }
        });
    }

    @BinderThread
    public void zza(final EventParcel eventParcel, final AppMetadata appMetadata) {
        zzab.zzy(eventParcel);
        zzf(appMetadata);
        this.ahD.zzbsc().zzm(new Runnable(this) {
            final /* synthetic */ zzy alv;

            public void run() {
                this.alv.ahD.zzbuh();
                this.alv.zzme(appMetadata.aig);
                this.alv.ahD.zzb(eventParcel, appMetadata);
            }
        });
    }

    @BinderThread
    public void zza(final EventParcel eventParcel, final String str, final String str2) {
        zzab.zzy(eventParcel);
        zzab.zzhr(str);
        zzmf(str);
        this.ahD.zzbsc().zzm(new Runnable(this) {
            final /* synthetic */ zzy alv;

            public void run() {
                this.alv.ahD.zzbuh();
                this.alv.zzme(str2);
                this.alv.ahD.zzb(eventParcel, str);
            }
        });
    }

    @BinderThread
    public void zza(final UserAttributeParcel userAttributeParcel, final AppMetadata appMetadata) {
        zzab.zzy(userAttributeParcel);
        zzf(appMetadata);
        if (userAttributeParcel.getValue() == null) {
            this.ahD.zzbsc().zzm(new Runnable(this) {
                final /* synthetic */ zzy alv;

                public void run() {
                    this.alv.ahD.zzbuh();
                    this.alv.zzme(appMetadata.aig);
                    this.alv.ahD.zzc(userAttributeParcel, appMetadata);
                }
            });
        } else {
            this.ahD.zzbsc().zzm(new Runnable(this) {
                final /* synthetic */ zzy alv;

                public void run() {
                    this.alv.ahD.zzbuh();
                    this.alv.zzme(appMetadata.aig);
                    this.alv.ahD.zzb(userAttributeParcel, appMetadata);
                }
            });
        }
    }

    @BinderThread
    public byte[] zza(final EventParcel eventParcel, final String str) {
        Object e;
        zzab.zzhr(str);
        zzab.zzy(eventParcel);
        zzmf(str);
        this.ahD.zzbsd().zzbtb().zzj("Log and bundle. event", eventParcel.name);
        long nanoTime = this.ahD.zzyw().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.ahD.zzbsc().zze(new Callable<byte[]>(this) {
                final /* synthetic */ zzy alv;

                public /* synthetic */ Object call() throws Exception {
                    return zzbuj();
                }

                public byte[] zzbuj() throws Exception {
                    this.alv.ahD.zzbuh();
                    return this.alv.ahD.zza(eventParcel, str);
                }
            }).get();
            if (bArr == null) {
                this.ahD.zzbsd().zzbsv().log("Log and bundle returned null");
                bArr = new byte[0];
            }
            this.ahD.zzbsd().zzbtb().zzd("Log and bundle processed. event, size, time_ms", eventParcel.name, Integer.valueOf(bArr.length), Long.valueOf((this.ahD.zzyw().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException e2) {
            e = e2;
            this.ahD.zzbsd().zzbsv().zze("Failed to log and bundle. event, error", eventParcel.name, e);
            return null;
        } catch (ExecutionException e3) {
            e = e3;
            this.ahD.zzbsd().zzbsv().zze("Failed to log and bundle. event, error", eventParcel.name, e);
            return null;
        }
    }

    @BinderThread
    public void zzb(final AppMetadata appMetadata) {
        zzf(appMetadata);
        this.ahD.zzbsc().zzm(new Runnable(this) {
            final /* synthetic */ zzy alv;

            public void run() {
                this.alv.ahD.zzbuh();
                this.alv.zzme(appMetadata.aig);
                this.alv.ahD.zzc(appMetadata);
            }
        });
    }

    @WorkerThread
    void zzme(String str) {
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(":", 2);
            if (split.length == 2) {
                try {
                    long longValue = Long.valueOf(split[0]).longValue();
                    if (longValue > 0) {
                        this.ahD.zzbse().ajX.zzh(split[1], longValue);
                    } else {
                        this.ahD.zzbsd().zzbsx().zzj("Combining sample with a non-positive weight", Long.valueOf(longValue));
                    }
                } catch (NumberFormatException e) {
                    this.ahD.zzbsd().zzbsx().zzj("Combining sample with a non-number weight", split[0]);
                }
            }
        }
    }

    protected void zzmg(String str) throws SecurityException {
        int myUid = this.alt ? Process.myUid() : Binder.getCallingUid();
        if (!com.google.android.gms.common.util.zzy.zzb(this.ahD.getContext(), myUid, str)) {
            if (!com.google.android.gms.common.util.zzy.zze(this.ahD.getContext(), myUid) || this.ahD.zzbty()) {
                throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
            }
        }
    }
}
