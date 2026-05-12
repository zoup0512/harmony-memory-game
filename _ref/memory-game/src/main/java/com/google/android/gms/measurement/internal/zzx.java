package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.internal.Constants;
import com.applovin.sdk.AppLovinErrorCodes;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.internal.zzapo;
import com.google.android.gms.internal.zzug;
import com.google.android.gms.internal.zzuh;
import com.google.android.gms.internal.zzuh.zzb;
import com.google.android.gms.internal.zzuh.zzc;
import com.google.android.gms.internal.zzuh.zzd;
import com.google.android.gms.internal.zzuh.zzg;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class zzx {
    private static volatile zzx akP;
    private final zzd akQ;
    private final zzt akR;
    private final zzp akS;
    private final zzw akT;
    private final zzaf akU;
    private final zzv akV;
    private final AppMeasurement akW;
    private final zzal akX;
    private final zze akY;
    private final zzq akZ;
    private final zzad ala;
    private final zzg alb;
    private final zzac alc;
    private final zzn ald;
    private final zzr ale;
    private final zzai alf;
    private final zzc alg;
    public final FirebaseAnalytics alh = new FirebaseAnalytics(this);
    private boolean ali;
    private Boolean alj;
    private FileLock alk;
    private FileChannel all;
    private List<Long> alm;
    private int aln;
    private int alo;
    private final Context mContext;
    private final zze zzaoc;
    private final boolean zzcwq;

    private class zza implements zzb {
        final /* synthetic */ zzx alp;
        zzuh.zze alq;
        List<Long> alr;
        long als;
        List<zzb> zzalc;

        private zza(zzx com_google_android_gms_measurement_internal_zzx) {
            this.alp = com_google_android_gms_measurement_internal_zzx;
        }

        private long zza(zzb com_google_android_gms_internal_zzuh_zzb) {
            return ((com_google_android_gms_internal_zzuh_zzb.ano.longValue() / 1000) / 60) / 60;
        }

        boolean isEmpty() {
            return this.zzalc == null || this.zzalc.isEmpty();
        }

        public boolean zza(long j, zzb com_google_android_gms_internal_zzuh_zzb) {
            zzab.zzy(com_google_android_gms_internal_zzuh_zzb);
            if (this.zzalc == null) {
                this.zzalc = new ArrayList();
            }
            if (this.alr == null) {
                this.alr = new ArrayList();
            }
            if (this.zzalc.size() > 0 && zza((zzb) this.zzalc.get(0)) != zza(com_google_android_gms_internal_zzuh_zzb)) {
                return false;
            }
            long aM = this.als + ((long) com_google_android_gms_internal_zzuh_zzb.aM());
            if (aM >= ((long) this.alp.zzbsf().zzbri())) {
                return false;
            }
            this.als = aM;
            this.zzalc.add(com_google_android_gms_internal_zzuh_zzb);
            this.alr.add(Long.valueOf(j));
            return this.zzalc.size() < this.alp.zzbsf().zzbrj();
        }

        public void zzc(zzuh.zze com_google_android_gms_internal_zzuh_zze) {
            zzab.zzy(com_google_android_gms_internal_zzuh_zze);
            this.alq = com_google_android_gms_internal_zzuh_zze;
        }
    }

    zzx(zzab com_google_android_gms_measurement_internal_zzab) {
        zzab.zzy(com_google_android_gms_measurement_internal_zzab);
        this.mContext = com_google_android_gms_measurement_internal_zzab.mContext;
        this.zzaoc = com_google_android_gms_measurement_internal_zzab.zzl(this);
        this.akQ = com_google_android_gms_measurement_internal_zzab.zza(this);
        zzt zzb = com_google_android_gms_measurement_internal_zzab.zzb(this);
        zzb.initialize();
        this.akR = zzb;
        zzp zzc = com_google_android_gms_measurement_internal_zzab.zzc(this);
        zzc.initialize();
        this.akS = zzc;
        zzbsd().zzbta().zzj("App measurement is starting up, version", Long.valueOf(zzbsf().zzbpz()));
        zzbsd().zzbta().log("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzbsd().zzbtb().log("Debug logging enabled");
        zzbsd().zzbtb().zzj("AppMeasurement singleton hash", Integer.valueOf(System.identityHashCode(this)));
        this.akX = com_google_android_gms_measurement_internal_zzab.zzi(this);
        zzg zzn = com_google_android_gms_measurement_internal_zzab.zzn(this);
        zzn.initialize();
        this.alb = zzn;
        zzn zzo = com_google_android_gms_measurement_internal_zzab.zzo(this);
        zzo.initialize();
        this.ald = zzo;
        zze zzj = com_google_android_gms_measurement_internal_zzab.zzj(this);
        zzj.initialize();
        this.akY = zzj;
        zzc zzr = com_google_android_gms_measurement_internal_zzab.zzr(this);
        zzr.initialize();
        this.alg = zzr;
        zzq zzk = com_google_android_gms_measurement_internal_zzab.zzk(this);
        zzk.initialize();
        this.akZ = zzk;
        zzad zzm = com_google_android_gms_measurement_internal_zzab.zzm(this);
        zzm.initialize();
        this.ala = zzm;
        zzac zzh = com_google_android_gms_measurement_internal_zzab.zzh(this);
        zzh.initialize();
        this.alc = zzh;
        zzai zzq = com_google_android_gms_measurement_internal_zzab.zzq(this);
        zzq.initialize();
        this.alf = zzq;
        this.ale = com_google_android_gms_measurement_internal_zzab.zzp(this);
        this.akW = com_google_android_gms_measurement_internal_zzab.zzg(this);
        zzaf zze = com_google_android_gms_measurement_internal_zzab.zze(this);
        zze.initialize();
        this.akU = zze;
        zzv zzf = com_google_android_gms_measurement_internal_zzab.zzf(this);
        zzf.initialize();
        this.akV = zzf;
        zzw zzd = com_google_android_gms_measurement_internal_zzab.zzd(this);
        zzd.initialize();
        this.akT = zzd;
        if (this.aln != this.alo) {
            zzbsd().zzbsv().zze("Not all components initialized", Integer.valueOf(this.aln), Integer.valueOf(this.alo));
        }
        this.zzcwq = true;
        if (!(this.akQ.zzabc() || zzbty())) {
            if (!(this.mContext.getApplicationContext() instanceof Application)) {
                zzbsd().zzbsx().log("Application context is not an Application");
            } else if (VERSION.SDK_INT >= 14) {
                zzbru().zzbun();
            } else {
                zzbsd().zzbtb().log("Not tracking deep linking pre-ICS");
            }
        }
        this.akT.zzm(new Runnable(this) {
            final /* synthetic */ zzx alp;

            {
                this.alp = r1;
            }

            public void run() {
                this.alp.start();
            }
        });
    }

    @WorkerThread
    private void zza(int i, Throwable th, byte[] bArr) {
        int i2 = 0;
        zzwu();
        zzzg();
        if (bArr == null) {
            bArr = new byte[0];
        }
        List<Long> list = this.alm;
        this.alm = null;
        if ((i == 200 || i == AppLovinErrorCodes.NO_FILL) && th == null) {
            zzbse().ajY.set(zzyw().currentTimeMillis());
            zzbse().ajZ.set(0);
            zzbue();
            zzbsd().zzbtc().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
            zzbry().beginTransaction();
            try {
                for (Long longValue : list) {
                    zzbry().zzbh(longValue.longValue());
                }
                zzbry().setTransactionSuccessful();
                if (zzbts().zzadj() && zzbud()) {
                    zzbuc();
                } else {
                    zzbue();
                }
            } finally {
                zzbry().endTransaction();
            }
        } else {
            zzbsd().zzbtc().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            zzbse().ajZ.set(zzyw().currentTimeMillis());
            if (i == 503 || i == 429) {
                i2 = 1;
            }
            if (i2 != 0) {
                zzbse().aka.set(zzyw().currentTimeMillis());
            }
            zzbue();
        }
    }

    private void zza(zzaa com_google_android_gms_measurement_internal_zzaa) {
        if (com_google_android_gms_measurement_internal_zzaa == null) {
            throw new IllegalStateException("Component not created");
        } else if (!com_google_android_gms_measurement_internal_zzaa.isInitialized()) {
            throw new IllegalStateException("Component not initialized");
        }
    }

    private void zza(zzz com_google_android_gms_measurement_internal_zzz) {
        if (com_google_android_gms_measurement_internal_zzz == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private com.google.android.gms.internal.zzuh.zza[] zza(String str, zzg[] com_google_android_gms_internal_zzuh_zzgArr, zzb[] com_google_android_gms_internal_zzuh_zzbArr) {
        zzab.zzhr(str);
        return zzbrt().zza(str, com_google_android_gms_internal_zzuh_zzbArr, com_google_android_gms_internal_zzuh_zzgArr);
    }

    private void zzad(List<Long> list) {
        zzab.zzbo(!list.isEmpty());
        if (this.alm != null) {
            zzbsd().zzbsv().log("Set uploading progress before finishing the previous upload");
        } else {
            this.alm = new ArrayList(list);
        }
    }

    @WorkerThread
    private boolean zzbub() {
        zzwu();
        return this.alm != null;
    }

    private boolean zzbud() {
        zzwu();
        zzzg();
        return zzbry().zzbsl() || !TextUtils.isEmpty(zzbry().zzbsg());
    }

    @WorkerThread
    private void zzbue() {
        zzwu();
        zzzg();
        if (!zzbui()) {
            return;
        }
        if (zzbto() && zzbud()) {
            long zzbuf = zzbuf();
            if (zzbuf == 0) {
                zzbtt().unregister();
                zzbtu().cancel();
                return;
            } else if (zzbts().zzadj()) {
                long j = zzbse().aka.get();
                long zzbrm = zzbsf().zzbrm();
                if (!zzbrz().zzg(j, zzbrm)) {
                    zzbuf = Math.max(zzbuf, j + zzbrm);
                }
                zzbtt().unregister();
                zzbuf -= zzyw().currentTimeMillis();
                if (zzbuf <= 0) {
                    zzbtu().zzv(1);
                    return;
                }
                zzbsd().zzbtc().zzj("Upload scheduled in approximately ms", Long.valueOf(zzbuf));
                zzbtu().zzv(zzbuf);
                return;
            } else {
                zzbtt().zzadg();
                zzbtu().cancel();
                return;
            }
        }
        zzbtt().unregister();
        zzbtu().cancel();
    }

    private long zzbuf() {
        long currentTimeMillis = zzyw().currentTimeMillis();
        long zzbrp = zzbsf().zzbrp();
        long zzbrn = zzbsf().zzbrn();
        long j = zzbse().ajY.get();
        long j2 = zzbse().ajZ.get();
        long max = Math.max(zzbry().zzbsj(), zzbry().zzbsk());
        if (max == 0) {
            return 0;
        }
        max = currentTimeMillis - Math.abs(max - currentTimeMillis);
        j2 = currentTimeMillis - Math.abs(j2 - currentTimeMillis);
        currentTimeMillis = Math.max(currentTimeMillis - Math.abs(j - currentTimeMillis), j2);
        zzbrp += max;
        if (!zzbrz().zzg(currentTimeMillis, zzbrn)) {
            zzbrp = currentTimeMillis + zzbrn;
        }
        if (j2 == 0 || j2 < max) {
            return zzbrp;
        }
        for (int i = 0; i < zzbsf().zzbrr(); i++) {
            zzbrp += ((long) (1 << i)) * zzbsf().zzbrq();
            if (zzbrp > j2) {
                return zzbrp;
            }
        }
        return 0;
    }

    public static zzx zzdo(Context context) {
        zzab.zzy(context);
        zzab.zzy(context.getApplicationContext());
        if (akP == null) {
            synchronized (zzx.class) {
                if (akP == null) {
                    akP = new zzab(context).zzbum();
                }
            }
        }
        return akP;
    }

    @WorkerThread
    private void zze(AppMetadata appMetadata) {
        Object obj = 1;
        zzwu();
        zzzg();
        zzab.zzy(appMetadata);
        zzab.zzhr(appMetadata.packageName);
        zza zzln = zzbry().zzln(appMetadata.packageName);
        String zzly = zzbse().zzly(appMetadata.packageName);
        Object obj2 = null;
        if (zzln == null) {
            zza com_google_android_gms_measurement_internal_zza = new zza(this, appMetadata.packageName);
            com_google_android_gms_measurement_internal_zza.zzky(zzbse().zzbtf());
            com_google_android_gms_measurement_internal_zza.zzla(zzly);
            zzln = com_google_android_gms_measurement_internal_zza;
            obj2 = 1;
        } else if (!zzly.equals(zzln.zzbpt())) {
            zzln.zzla(zzly);
            zzln.zzky(zzbse().zzbtf());
            int i = 1;
        }
        if (!(TextUtils.isEmpty(appMetadata.aic) || appMetadata.aic.equals(zzln.zzbps()))) {
            zzln.zzkz(appMetadata.aic);
            obj2 = 1;
        }
        if (!(TextUtils.isEmpty(appMetadata.aik) || appMetadata.aik.equals(zzln.zzbpu()))) {
            zzln.zzlb(appMetadata.aik);
            obj2 = 1;
        }
        if (!(appMetadata.aie == 0 || appMetadata.aie == zzln.zzbpz())) {
            zzln.zzax(appMetadata.aie);
            obj2 = 1;
        }
        if (!(TextUtils.isEmpty(appMetadata.aav) || appMetadata.aav.equals(zzln.zzxc()))) {
            zzln.setAppVersion(appMetadata.aav);
            obj2 = 1;
        }
        if (appMetadata.aij != zzln.zzbpx()) {
            zzln.zzaw(appMetadata.aij);
            obj2 = 1;
        }
        if (!(TextUtils.isEmpty(appMetadata.aid) || appMetadata.aid.equals(zzln.zzbpy()))) {
            zzln.zzlc(appMetadata.aid);
            obj2 = 1;
        }
        if (appMetadata.aif != zzln.zzbqa()) {
            zzln.zzay(appMetadata.aif);
            obj2 = 1;
        }
        if (appMetadata.aih != zzln.zzbqb()) {
            zzln.setMeasurementEnabled(appMetadata.aih);
        } else {
            obj = obj2;
        }
        if (obj != null) {
            zzbry().zza(zzln);
        }
    }

    private boolean zzi(String str, long j) {
        zzbry().beginTransaction();
        try {
            zzx com_google_android_gms_measurement_internal_zzx = this;
            zzb com_google_android_gms_measurement_internal_zzx_zza = new zza();
            zzbry().zza(str, j, com_google_android_gms_measurement_internal_zzx_zza);
            if (com_google_android_gms_measurement_internal_zzx_zza.isEmpty()) {
                zzbry().setTransactionSuccessful();
                zzbry().endTransaction();
                return false;
            }
            int i;
            zzuh.zze com_google_android_gms_internal_zzuh_zze = com_google_android_gms_measurement_internal_zzx_zza.alq;
            com_google_android_gms_internal_zzuh_zze.anv = new zzb[com_google_android_gms_measurement_internal_zzx_zza.zzalc.size()];
            int i2 = 0;
            int i3 = 0;
            while (i3 < com_google_android_gms_measurement_internal_zzx_zza.zzalc.size()) {
                if (zzbsa().zzax(com_google_android_gms_measurement_internal_zzx_zza.alq.zzck, ((zzb) com_google_android_gms_measurement_internal_zzx_zza.zzalc.get(i3)).name)) {
                    zzbsd().zzbsx().zzj("Dropping blacklisted raw event", ((zzb) com_google_android_gms_measurement_internal_zzx_zza.zzalc.get(i3)).name);
                    zzbrz().zze(11, "_ev", ((zzb) com_google_android_gms_measurement_internal_zzx_zza.zzalc.get(i3)).name);
                    i = i2;
                } else {
                    int i4;
                    if (zzbsa().zzay(com_google_android_gms_measurement_internal_zzx_zza.alq.zzck, ((zzb) com_google_android_gms_measurement_internal_zzx_zza.zzalc.get(i3)).name)) {
                        int i5;
                        Object obj;
                        zzc com_google_android_gms_internal_zzuh_zzc;
                        if (((zzb) com_google_android_gms_measurement_internal_zzx_zza.zzalc.get(i3)).ann == null) {
                            ((zzb) com_google_android_gms_measurement_internal_zzx_zza.zzalc.get(i3)).ann = new zzc[0];
                        }
                        for (zzc com_google_android_gms_internal_zzuh_zzc2 : ((zzb) com_google_android_gms_measurement_internal_zzx_zza.zzalc.get(i3)).ann) {
                            if ("_c".equals(com_google_android_gms_internal_zzuh_zzc2.name)) {
                                com_google_android_gms_internal_zzuh_zzc2.anr = Long.valueOf(1);
                                obj = 1;
                                break;
                            }
                        }
                        obj = null;
                        if (obj == null) {
                            zzbsd().zzbtc().zzj("Marking event as conversion", ((zzb) com_google_android_gms_measurement_internal_zzx_zza.zzalc.get(i3)).name);
                            zzc[] com_google_android_gms_internal_zzuh_zzcArr = (zzc[]) Arrays.copyOf(((zzb) com_google_android_gms_measurement_internal_zzx_zza.zzalc.get(i3)).ann, ((zzb) com_google_android_gms_measurement_internal_zzx_zza.zzalc.get(i3)).ann.length + 1);
                            com_google_android_gms_internal_zzuh_zzc = new zzc();
                            com_google_android_gms_internal_zzuh_zzc.name = "_c";
                            com_google_android_gms_internal_zzuh_zzc.anr = Long.valueOf(1);
                            com_google_android_gms_internal_zzuh_zzcArr[com_google_android_gms_internal_zzuh_zzcArr.length - 1] = com_google_android_gms_internal_zzuh_zzc;
                            ((zzb) com_google_android_gms_measurement_internal_zzx_zza.zzalc.get(i3)).ann = com_google_android_gms_internal_zzuh_zzcArr;
                        }
                        boolean zzmj = zzal.zzmj(((zzb) com_google_android_gms_measurement_internal_zzx_zza.zzalc.get(i3)).name);
                        if (zzmj && zzbry().zza(zzbtz(), com_google_android_gms_measurement_internal_zzx_zza.alq.zzck, false, zzmj, false).air - ((long) zzbsf().zzlf(com_google_android_gms_measurement_internal_zzx_zza.alq.zzck)) > 0) {
                            zzbsd().zzbsx().log("Too many conversions. Not logging as conversion.");
                            zzb com_google_android_gms_internal_zzuh_zzb = (zzb) com_google_android_gms_measurement_internal_zzx_zza.zzalc.get(i3);
                            Object obj2 = null;
                            zzc com_google_android_gms_internal_zzuh_zzc3 = null;
                            zzc[] com_google_android_gms_internal_zzuh_zzcArr2 = ((zzb) com_google_android_gms_measurement_internal_zzx_zza.zzalc.get(i3)).ann;
                            int length = com_google_android_gms_internal_zzuh_zzcArr2.length;
                            int i6 = 0;
                            while (i6 < length) {
                                Object obj3;
                                com_google_android_gms_internal_zzuh_zzc = com_google_android_gms_internal_zzuh_zzcArr2[i6];
                                if ("_c".equals(com_google_android_gms_internal_zzuh_zzc.name)) {
                                    obj3 = obj2;
                                } else if ("_err".equals(com_google_android_gms_internal_zzuh_zzc.name)) {
                                    zzc com_google_android_gms_internal_zzuh_zzc4 = com_google_android_gms_internal_zzuh_zzc3;
                                    int i7 = 1;
                                    com_google_android_gms_internal_zzuh_zzc = com_google_android_gms_internal_zzuh_zzc4;
                                } else {
                                    com_google_android_gms_internal_zzuh_zzc = com_google_android_gms_internal_zzuh_zzc3;
                                    obj3 = obj2;
                                }
                                i6++;
                                obj2 = obj3;
                                com_google_android_gms_internal_zzuh_zzc3 = com_google_android_gms_internal_zzuh_zzc;
                            }
                            if (obj2 != null && com_google_android_gms_internal_zzuh_zzc3 != null) {
                                zzc[] com_google_android_gms_internal_zzuh_zzcArr3 = new zzc[(com_google_android_gms_internal_zzuh_zzb.ann.length - 1)];
                                i4 = 0;
                                com_google_android_gms_internal_zzuh_zzcArr2 = com_google_android_gms_internal_zzuh_zzb.ann;
                                length = com_google_android_gms_internal_zzuh_zzcArr2.length;
                                i5 = 0;
                                while (i5 < length) {
                                    zzc com_google_android_gms_internal_zzuh_zzc5 = com_google_android_gms_internal_zzuh_zzcArr2[i5];
                                    if (com_google_android_gms_internal_zzuh_zzc5 != com_google_android_gms_internal_zzuh_zzc3) {
                                        i = i4 + 1;
                                        com_google_android_gms_internal_zzuh_zzcArr3[i4] = com_google_android_gms_internal_zzuh_zzc5;
                                    } else {
                                        i = i4;
                                    }
                                    i5++;
                                    i4 = i;
                                }
                                ((zzb) com_google_android_gms_measurement_internal_zzx_zza.zzalc.get(i3)).ann = com_google_android_gms_internal_zzuh_zzcArr3;
                            } else if (com_google_android_gms_internal_zzuh_zzc3 != null) {
                                com_google_android_gms_internal_zzuh_zzc3.name = "_err";
                                com_google_android_gms_internal_zzuh_zzc3.anr = Long.valueOf(10);
                            } else {
                                zzbsd().zzbsv().log("Did not find conversion parameter. Error not tracked");
                            }
                        }
                    }
                    i4 = i2 + 1;
                    com_google_android_gms_internal_zzuh_zze.anv[i2] = (zzb) com_google_android_gms_measurement_internal_zzx_zza.zzalc.get(i3);
                    i = i4;
                }
                i3++;
                i2 = i;
            }
            if (i2 < com_google_android_gms_measurement_internal_zzx_zza.zzalc.size()) {
                com_google_android_gms_internal_zzuh_zze.anv = (zzb[]) Arrays.copyOf(com_google_android_gms_internal_zzuh_zze.anv, i2);
            }
            com_google_android_gms_internal_zzuh_zze.anO = zza(com_google_android_gms_measurement_internal_zzx_zza.alq.zzck, com_google_android_gms_measurement_internal_zzx_zza.alq.anw, com_google_android_gms_internal_zzuh_zze.anv);
            com_google_android_gms_internal_zzuh_zze.any = com_google_android_gms_internal_zzuh_zze.anv[0].ano;
            com_google_android_gms_internal_zzuh_zze.anz = com_google_android_gms_internal_zzuh_zze.anv[0].ano;
            for (i = 1; i < com_google_android_gms_internal_zzuh_zze.anv.length; i++) {
                zzb com_google_android_gms_internal_zzuh_zzb2 = com_google_android_gms_internal_zzuh_zze.anv[i];
                if (com_google_android_gms_internal_zzuh_zzb2.ano.longValue() < com_google_android_gms_internal_zzuh_zze.any.longValue()) {
                    com_google_android_gms_internal_zzuh_zze.any = com_google_android_gms_internal_zzuh_zzb2.ano;
                }
                if (com_google_android_gms_internal_zzuh_zzb2.ano.longValue() > com_google_android_gms_internal_zzuh_zze.anz.longValue()) {
                    com_google_android_gms_internal_zzuh_zze.anz = com_google_android_gms_internal_zzuh_zzb2.ano;
                }
            }
            String str2 = com_google_android_gms_measurement_internal_zzx_zza.alq.zzck;
            zza zzln = zzbry().zzln(str2);
            if (zzln == null) {
                zzbsd().zzbsv().log("Bundling raw events w/o app info");
            } else {
                long zzbpw = zzln.zzbpw();
                com_google_android_gms_internal_zzuh_zze.anB = zzbpw != 0 ? Long.valueOf(zzbpw) : null;
                long zzbpv = zzln.zzbpv();
                if (zzbpv != 0) {
                    zzbpw = zzbpv;
                }
                com_google_android_gms_internal_zzuh_zze.anA = zzbpw != 0 ? Long.valueOf(zzbpw) : null;
                zzln.zzbqf();
                com_google_android_gms_internal_zzuh_zze.anM = Integer.valueOf((int) zzln.zzbqc());
                zzln.zzau(com_google_android_gms_internal_zzuh_zze.any.longValue());
                zzln.zzav(com_google_android_gms_internal_zzuh_zze.anz.longValue());
                zzbry().zza(zzln);
            }
            com_google_android_gms_internal_zzuh_zze.aig = zzbsd().zzbtd();
            zzbry().zza(com_google_android_gms_internal_zzuh_zze);
            zzbry().zzac(com_google_android_gms_measurement_internal_zzx_zza.alr);
            zzbry().zzlt(str2);
            zzbry().setTransactionSuccessful();
            return true;
        } finally {
            zzbry().endTransaction();
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    @WorkerThread
    public boolean isEnabled() {
        boolean z = false;
        zzwu();
        zzzg();
        if (zzbsf().zzbrd()) {
            return false;
        }
        Boolean zzbre = zzbsf().zzbre();
        if (zzbre != null) {
            z = zzbre.booleanValue();
        } else if (!zzbsf().zzaqp()) {
            z = true;
        }
        return zzbse().zzcc(z);
    }

    @WorkerThread
    protected void start() {
        zzwu();
        if (!zzbty() || (this.akT.isInitialized() && !this.akT.zzbul())) {
            zzbry().zzbsh();
            if (zzbto()) {
                if (!(zzbsf().zzabc() || TextUtils.isEmpty(zzbrv().zzbps()))) {
                    String zzbti = zzbse().zzbti();
                    if (zzbti == null) {
                        zzbse().zzlz(zzbrv().zzbps());
                    } else if (!zzbti.equals(zzbrv().zzbps())) {
                        zzbsd().zzbta().log("Rechecking which service to use due to a GMP App Id change");
                        zzbse().zzbtk();
                        this.ala.disconnect();
                        this.ala.zzaai();
                        zzbse().zzlz(zzbrv().zzbps());
                    }
                }
                if (!(zzbsf().zzabc() || zzbty() || TextUtils.isEmpty(zzbrv().zzbps()))) {
                    zzbru().zzbuo();
                }
            } else if (isEnabled()) {
                if (!zzbrz().zzeo("android.permission.INTERNET")) {
                    zzbsd().zzbsv().log("App is missing INTERNET permission");
                }
                if (!zzbrz().zzeo("android.permission.ACCESS_NETWORK_STATE")) {
                    zzbsd().zzbsv().log("App is missing ACCESS_NETWORK_STATE permission");
                }
                if (!zzu.zzav(getContext())) {
                    zzbsd().zzbsv().log("AppMeasurementReceiver not registered/enabled");
                }
                if (!zzae.zzaw(getContext())) {
                    zzbsd().zzbsv().log("AppMeasurementService not registered/enabled");
                }
                zzbsd().zzbsv().log("Uploading is not possible. App measurement disabled");
            }
            zzbue();
            return;
        }
        zzbsd().zzbsv().log("Scheduler shutting down before Scion.start() called");
    }

    @WorkerThread
    int zza(FileChannel fileChannel) {
        int i = 0;
        zzwu();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzbsd().zzbsv().log("Bad chanel to read from");
        } else {
            ByteBuffer allocate = ByteBuffer.allocate(4);
            try {
                fileChannel.position(0);
                int read = fileChannel.read(allocate);
                if (read != 4) {
                    zzbsd().zzbsx().zzj("Unexpected data length or empty data in channel. Bytes read", Integer.valueOf(read));
                } else {
                    allocate.flip();
                    i = allocate.getInt();
                }
            } catch (IOException e) {
                zzbsd().zzbsv().zzj("Failed to read from channel", e);
            }
        }
        return i;
    }

    @WorkerThread
    void zza(AppMetadata appMetadata, long j) {
        zza zzln = zzbry().zzln(appMetadata.packageName);
        if (!(zzln == null || zzln.zzbps() == null || zzln.zzbps().equals(appMetadata.aic))) {
            zzbsd().zzbsx().log("New GMP App Id passed in. Removing cached database data.");
            zzbry().zzls(zzln.zzsh());
            zzln = null;
        }
        if (zzln != null && zzln.zzxc() != null && !zzln.zzxc().equals(appMetadata.aav)) {
            Bundle bundle = new Bundle();
            bundle.putString("_pv", zzln.zzxc());
            zzb(new EventParcel("_au", new EventParams(bundle), "auto", j), appMetadata);
        }
    }

    void zza(zzh com_google_android_gms_measurement_internal_zzh, AppMetadata appMetadata) {
        zzwu();
        zzzg();
        zzab.zzy(com_google_android_gms_measurement_internal_zzh);
        zzab.zzy(appMetadata);
        zzab.zzhr(com_google_android_gms_measurement_internal_zzh.zzcjf);
        zzab.zzbo(com_google_android_gms_measurement_internal_zzh.zzcjf.equals(appMetadata.packageName));
        zzuh.zze com_google_android_gms_internal_zzuh_zze = new zzuh.zze();
        com_google_android_gms_internal_zzuh_zze.anu = Integer.valueOf(1);
        com_google_android_gms_internal_zzuh_zze.anC = AbstractSpiCall.ANDROID_CLIENT_TYPE;
        com_google_android_gms_internal_zzuh_zze.zzck = appMetadata.packageName;
        com_google_android_gms_internal_zzuh_zze.aid = appMetadata.aid;
        com_google_android_gms_internal_zzuh_zze.aav = appMetadata.aav;
        com_google_android_gms_internal_zzuh_zze.anP = Integer.valueOf((int) appMetadata.aij);
        com_google_android_gms_internal_zzuh_zze.anG = Long.valueOf(appMetadata.aie);
        com_google_android_gms_internal_zzuh_zze.aic = appMetadata.aic;
        com_google_android_gms_internal_zzuh_zze.anL = appMetadata.aif == 0 ? null : Long.valueOf(appMetadata.aif);
        Pair zzlx = zzbse().zzlx(appMetadata.packageName);
        if (zzlx != null && !TextUtils.isEmpty((CharSequence) zzlx.first)) {
            com_google_android_gms_internal_zzuh_zze.anI = (String) zzlx.first;
            com_google_android_gms_internal_zzuh_zze.anJ = (Boolean) zzlx.second;
        } else if (!zzbrw().zzdn(this.mContext)) {
            String string = Secure.getString(this.mContext.getContentResolver(), "android_id");
            if (string == null) {
                zzbsd().zzbsx().log("null secure ID");
                string = Constants.NULL_VERSION_ID;
            } else if (string.isEmpty()) {
                zzbsd().zzbsx().log("empty secure ID");
            }
            com_google_android_gms_internal_zzuh_zze.anS = string;
        }
        com_google_android_gms_internal_zzuh_zze.anD = zzbrw().zztg();
        com_google_android_gms_internal_zzuh_zze.zzct = zzbrw().zzbso();
        com_google_android_gms_internal_zzuh_zze.anF = Integer.valueOf((int) zzbrw().zzbsp());
        com_google_android_gms_internal_zzuh_zze.anE = zzbrw().zzbsq();
        com_google_android_gms_internal_zzuh_zze.anH = null;
        com_google_android_gms_internal_zzuh_zze.anx = null;
        com_google_android_gms_internal_zzuh_zze.any = null;
        com_google_android_gms_internal_zzuh_zze.anz = null;
        zza zzln = zzbry().zzln(appMetadata.packageName);
        if (zzln == null) {
            zzln = new zza(this, appMetadata.packageName);
            zzln.zzky(zzbse().zzbtf());
            zzln.zzlb(appMetadata.aik);
            zzln.zzkz(appMetadata.aic);
            zzln.zzla(zzbse().zzly(appMetadata.packageName));
            zzln.zzaz(0);
            zzln.zzau(0);
            zzln.zzav(0);
            zzln.setAppVersion(appMetadata.aav);
            zzln.zzaw(appMetadata.aij);
            zzln.zzlc(appMetadata.aid);
            zzln.zzax(appMetadata.aie);
            zzln.zzay(appMetadata.aif);
            zzln.setMeasurementEnabled(appMetadata.aih);
            zzbry().zza(zzln);
        }
        com_google_android_gms_internal_zzuh_zze.anK = zzln.zzawo();
        com_google_android_gms_internal_zzuh_zze.aik = zzln.zzbpu();
        List zzlm = zzbry().zzlm(appMetadata.packageName);
        com_google_android_gms_internal_zzuh_zze.anw = new zzg[zzlm.size()];
        for (int i = 0; i < zzlm.size(); i++) {
            zzg com_google_android_gms_internal_zzuh_zzg = new zzg();
            com_google_android_gms_internal_zzuh_zze.anw[i] = com_google_android_gms_internal_zzuh_zzg;
            com_google_android_gms_internal_zzuh_zzg.name = ((zzak) zzlm.get(i)).mName;
            com_google_android_gms_internal_zzuh_zzg.anW = Long.valueOf(((zzak) zzlm.get(i)).amx);
            zzbrz().zza(com_google_android_gms_internal_zzuh_zzg, ((zzak) zzlm.get(i)).zzcnn);
        }
        try {
            zzbry().zza(com_google_android_gms_measurement_internal_zzh, zzbry().zzb(com_google_android_gms_internal_zzuh_zze));
        } catch (IOException e) {
            zzbsd().zzbsv().zzj("Data loss. Failed to insert raw event metadata", e);
        }
    }

    @WorkerThread
    boolean zza(int i, FileChannel fileChannel) {
        zzwu();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzbsd().zzbsv().log("Bad chanel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() == 4) {
                return true;
            }
            zzbsd().zzbsv().zzj("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            return true;
        } catch (IOException e) {
            zzbsd().zzbsv().zzj("Failed to write to channel", e);
            return false;
        }
    }

    @WorkerThread
    public byte[] zza(@NonNull EventParcel eventParcel, @Size(min = 1) String str) {
        zzzg();
        zzwu();
        zzbua();
        zzab.zzy(eventParcel);
        zzab.zzhr(str);
        zzd com_google_android_gms_internal_zzuh_zzd = new zzd();
        zzbry().beginTransaction();
        try {
            zza zzln = zzbry().zzln(str);
            byte[] bArr;
            if (zzln == null) {
                zzbsd().zzbtb().zzj("Log and bundle not available. package_name", str);
                bArr = new byte[0];
                return bArr;
            } else if (zzln.zzbqb()) {
                long j;
                zzuh.zze com_google_android_gms_internal_zzuh_zze = new zzuh.zze();
                com_google_android_gms_internal_zzuh_zzd.ans = new zzuh.zze[]{com_google_android_gms_internal_zzuh_zze};
                com_google_android_gms_internal_zzuh_zze.anu = Integer.valueOf(1);
                com_google_android_gms_internal_zzuh_zze.anC = AbstractSpiCall.ANDROID_CLIENT_TYPE;
                com_google_android_gms_internal_zzuh_zze.zzck = zzln.zzsh();
                com_google_android_gms_internal_zzuh_zze.aid = zzln.zzbpy();
                com_google_android_gms_internal_zzuh_zze.aav = zzln.zzxc();
                com_google_android_gms_internal_zzuh_zze.anP = Integer.valueOf((int) zzln.zzbpx());
                com_google_android_gms_internal_zzuh_zze.anG = Long.valueOf(zzln.zzbpz());
                com_google_android_gms_internal_zzuh_zze.aic = zzln.zzbps();
                com_google_android_gms_internal_zzuh_zze.anL = Long.valueOf(zzln.zzbqa());
                Pair zzlx = zzbse().zzlx(zzln.zzsh());
                if (!(zzlx == null || TextUtils.isEmpty((CharSequence) zzlx.first))) {
                    com_google_android_gms_internal_zzuh_zze.anI = (String) zzlx.first;
                    com_google_android_gms_internal_zzuh_zze.anJ = (Boolean) zzlx.second;
                }
                com_google_android_gms_internal_zzuh_zze.anD = zzbrw().zztg();
                com_google_android_gms_internal_zzuh_zze.zzct = zzbrw().zzbso();
                com_google_android_gms_internal_zzuh_zze.anF = Integer.valueOf((int) zzbrw().zzbsp());
                com_google_android_gms_internal_zzuh_zze.anE = zzbrw().zzbsq();
                com_google_android_gms_internal_zzuh_zze.anK = zzln.zzawo();
                com_google_android_gms_internal_zzuh_zze.aik = zzln.zzbpu();
                List zzlm = zzbry().zzlm(zzln.zzsh());
                com_google_android_gms_internal_zzuh_zze.anw = new zzg[zzlm.size()];
                for (int i = 0; i < zzlm.size(); i++) {
                    zzg com_google_android_gms_internal_zzuh_zzg = new zzg();
                    com_google_android_gms_internal_zzuh_zze.anw[i] = com_google_android_gms_internal_zzuh_zzg;
                    com_google_android_gms_internal_zzuh_zzg.name = ((zzak) zzlm.get(i)).mName;
                    com_google_android_gms_internal_zzuh_zzg.anW = Long.valueOf(((zzak) zzlm.get(i)).amx);
                    zzbrz().zza(com_google_android_gms_internal_zzuh_zzg, ((zzak) zzlm.get(i)).zzcnn);
                }
                Bundle zzbss = eventParcel.aiI.zzbss();
                if ("_iap".equals(eventParcel.name)) {
                    zzbss.putLong("_c", 1);
                }
                zzbss.putString("_o", eventParcel.aiJ);
                zzi zzaq = zzbry().zzaq(str, eventParcel.name);
                if (zzaq == null) {
                    zzbry().zza(new zzi(str, eventParcel.name, 1, 0, eventParcel.aiK));
                    j = 0;
                } else {
                    j = zzaq.aiE;
                    zzbry().zza(zzaq.zzbj(eventParcel.aiK).zzbsr());
                }
                zzh com_google_android_gms_measurement_internal_zzh = new zzh(this, eventParcel.aiJ, str, eventParcel.name, eventParcel.aiK, j, zzbss);
                zzb com_google_android_gms_internal_zzuh_zzb = new zzb();
                com_google_android_gms_internal_zzuh_zze.anv = new zzb[]{com_google_android_gms_internal_zzuh_zzb};
                com_google_android_gms_internal_zzuh_zzb.ano = Long.valueOf(com_google_android_gms_measurement_internal_zzh.pJ);
                com_google_android_gms_internal_zzuh_zzb.name = com_google_android_gms_measurement_internal_zzh.mName;
                com_google_android_gms_internal_zzuh_zzb.anp = Long.valueOf(com_google_android_gms_measurement_internal_zzh.aiA);
                com_google_android_gms_internal_zzuh_zzb.ann = new zzc[com_google_android_gms_measurement_internal_zzh.aiB.size()];
                Iterator it = com_google_android_gms_measurement_internal_zzh.aiB.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    String str2 = (String) it.next();
                    zzc com_google_android_gms_internal_zzuh_zzc = new zzc();
                    int i3 = i2 + 1;
                    com_google_android_gms_internal_zzuh_zzb.ann[i2] = com_google_android_gms_internal_zzuh_zzc;
                    com_google_android_gms_internal_zzuh_zzc.name = str2;
                    zzbrz().zza(com_google_android_gms_internal_zzuh_zzc, com_google_android_gms_measurement_internal_zzh.aiB.get(str2));
                    i2 = i3;
                }
                com_google_android_gms_internal_zzuh_zze.anO = zza(zzln.zzsh(), com_google_android_gms_internal_zzuh_zze.anw, com_google_android_gms_internal_zzuh_zze.anv);
                com_google_android_gms_internal_zzuh_zze.any = com_google_android_gms_internal_zzuh_zzb.ano;
                com_google_android_gms_internal_zzuh_zze.anz = com_google_android_gms_internal_zzuh_zzb.ano;
                long zzbpw = zzln.zzbpw();
                com_google_android_gms_internal_zzuh_zze.anB = zzbpw != 0 ? Long.valueOf(zzbpw) : null;
                long zzbpv = zzln.zzbpv();
                if (zzbpv != 0) {
                    zzbpw = zzbpv;
                }
                com_google_android_gms_internal_zzuh_zze.anA = zzbpw != 0 ? Long.valueOf(zzbpw) : null;
                zzln.zzbqf();
                com_google_android_gms_internal_zzuh_zze.anM = Integer.valueOf((int) zzln.zzbqc());
                com_google_android_gms_internal_zzuh_zze.anH = Long.valueOf(zzbsf().zzbpz());
                com_google_android_gms_internal_zzuh_zze.anx = Long.valueOf(zzyw().currentTimeMillis());
                com_google_android_gms_internal_zzuh_zze.anN = Boolean.TRUE;
                zzln.zzau(com_google_android_gms_internal_zzuh_zze.any.longValue());
                zzln.zzav(com_google_android_gms_internal_zzuh_zze.anz.longValue());
                zzbry().zza(zzln);
                zzbry().setTransactionSuccessful();
                zzbry().endTransaction();
                try {
                    bArr = new byte[com_google_android_gms_internal_zzuh_zzd.aM()];
                    zzapo zzbe = zzapo.zzbe(bArr);
                    com_google_android_gms_internal_zzuh_zzd.zza(zzbe);
                    zzbe.az();
                    return zzbrz().zzj(bArr);
                } catch (IOException e) {
                    zzbsd().zzbsv().zzj("Data loss. Failed to bundle and serialize", e);
                    return null;
                }
            } else {
                zzbsd().zzbtb().zzj("Log and bundle disabled. package_name", str);
                bArr = new byte[0];
                zzbry().endTransaction();
                return bArr;
            }
        } finally {
            zzbry().endTransaction();
        }
    }

    public void zzas(boolean z) {
        zzbue();
    }

    @WorkerThread
    void zzb(AppMetadata appMetadata, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("_c", 1);
        zzb(new EventParcel("_f", new EventParams(bundle), "auto", j), appMetadata);
    }

    @WorkerThread
    void zzb(EventParcel eventParcel, AppMetadata appMetadata) {
        long nanoTime = System.nanoTime();
        zzwu();
        zzzg();
        String str = appMetadata.packageName;
        zzab.zzhr(str);
        if (!TextUtils.isEmpty(appMetadata.aic)) {
            if (!appMetadata.aih) {
                zze(appMetadata);
            } else if (zzbsa().zzax(str, eventParcel.name)) {
                zzbsd().zzbsx().zzj("Dropping blacklisted event", eventParcel.name);
                zzbrz().zze(11, "_ev", eventParcel.name);
            } else {
                if (zzbsd().zzaz(2)) {
                    zzbsd().zzbtc().zzj("Logging event", eventParcel);
                }
                zzbry().beginTransaction();
                try {
                    Bundle zzbss = eventParcel.aiI.zzbss();
                    zze(appMetadata);
                    if ("_iap".equals(eventParcel.name) || Event.ECOMMERCE_PURCHASE.equals(eventParcel.name)) {
                        long round;
                        Object string = zzbss.getString("currency");
                        if (Event.ECOMMERCE_PURCHASE.equals(eventParcel.name)) {
                            double d = zzbss.getDouble(Param.VALUE) * 1000000.0d;
                            if (d == 0.0d) {
                                d = ((double) zzbss.getLong(Param.VALUE)) * 1000000.0d;
                            }
                            if (d > 9.223372036854776E18d || d < -9.223372036854776E18d) {
                                zzbsd().zzbsx().zzj("Data lost. Currency value is too big", Double.valueOf(d));
                                zzbry().setTransactionSuccessful();
                                zzbry().endTransaction();
                                return;
                            }
                            round = Math.round(d);
                        } else {
                            round = zzbss.getLong(Param.VALUE);
                        }
                        if (!TextUtils.isEmpty(string)) {
                            String toUpperCase = string.toUpperCase(Locale.US);
                            if (toUpperCase.matches("[A-Z]{3}")) {
                                zzak com_google_android_gms_measurement_internal_zzak;
                                String valueOf = String.valueOf("_ltv_");
                                toUpperCase = String.valueOf(toUpperCase);
                                String concat = toUpperCase.length() != 0 ? valueOf.concat(toUpperCase) : new String(valueOf);
                                zzak zzas = zzbry().zzas(str, concat);
                                if (zzas == null || !(zzas.zzcnn instanceof Long)) {
                                    zzbry().zzy(str, zzbsf().zzlh(str) - 1);
                                    com_google_android_gms_measurement_internal_zzak = new zzak(str, concat, zzyw().currentTimeMillis(), Long.valueOf(round));
                                } else {
                                    com_google_android_gms_measurement_internal_zzak = new zzak(str, concat, zzyw().currentTimeMillis(), Long.valueOf(round + ((Long) zzas.zzcnn).longValue()));
                                }
                                if (!zzbry().zza(com_google_android_gms_measurement_internal_zzak)) {
                                    zzbsd().zzbsv().zze("Too many unique user properties are set. Ignoring user property.", com_google_android_gms_measurement_internal_zzak.mName, com_google_android_gms_measurement_internal_zzak.zzcnn);
                                    zzbrz().zze(9, null, null);
                                }
                            }
                        }
                    }
                    boolean zzmj = zzal.zzmj(eventParcel.name);
                    zzal.zzam(zzbss);
                    boolean equals = "_err".equals(eventParcel.name);
                    com.google.android.gms.measurement.internal.zze.zza zza = zzbry().zza(zzbtz(), str, zzmj, false, equals);
                    long zzbqv = zza.aiq - zzbsf().zzbqv();
                    if (zzbqv > 0) {
                        if (zzbqv % 1000 == 1) {
                            zzbsd().zzbsv().zzj("Data loss. Too many events logged. count", Long.valueOf(zza.aiq));
                        }
                        zzbrz().zze(16, "_ev", eventParcel.name);
                        zzbry().setTransactionSuccessful();
                        return;
                    }
                    zzi zzbj;
                    if (zzmj) {
                        zzbqv = zza.aip - zzbsf().zzbqw();
                        if (zzbqv > 0) {
                            if (zzbqv % 1000 == 1) {
                                zzbsd().zzbsv().zzj("Data loss. Too many public events logged. count", Long.valueOf(zza.aip));
                            }
                            zzbrz().zze(16, "_ev", eventParcel.name);
                            zzbry().setTransactionSuccessful();
                            zzbry().endTransaction();
                            return;
                        }
                    }
                    if (equals) {
                        zzbqv = zza.ais - zzbsf().zzbqx();
                        if (zzbqv > 0) {
                            if (zzbqv == 1) {
                                zzbsd().zzbsv().zzj("Too many error events logged. count", Long.valueOf(zza.ais));
                            }
                            zzbry().setTransactionSuccessful();
                            zzbry().endTransaction();
                            return;
                        }
                    }
                    zzbrz().zza(zzbss, "_o", eventParcel.aiJ);
                    long zzlo = zzbry().zzlo(str);
                    if (zzlo > 0) {
                        zzbsd().zzbsx().zzj("Data lost. Too many events stored on disk, deleted", Long.valueOf(zzlo));
                    }
                    zzh com_google_android_gms_measurement_internal_zzh = new zzh(this, eventParcel.aiJ, str, eventParcel.name, eventParcel.aiK, 0, zzbss);
                    zzi zzaq = zzbry().zzaq(str, com_google_android_gms_measurement_internal_zzh.mName);
                    if (zzaq != null) {
                        com_google_android_gms_measurement_internal_zzh = com_google_android_gms_measurement_internal_zzh.zza(this, zzaq.aiE);
                        zzbj = zzaq.zzbj(com_google_android_gms_measurement_internal_zzh.pJ);
                    } else if (zzbry().zzlu(str) >= ((long) zzbsf().zzbqu())) {
                        zzbsd().zzbsv().zze("Too many event names used, ignoring event. name, supported count", com_google_android_gms_measurement_internal_zzh.mName, Integer.valueOf(zzbsf().zzbqu()));
                        zzbrz().zze(8, null, null);
                        zzbry().endTransaction();
                        return;
                    } else {
                        zzbj = new zzi(str, com_google_android_gms_measurement_internal_zzh.mName, 0, 0, com_google_android_gms_measurement_internal_zzh.pJ);
                    }
                    zzbry().zza(zzbj);
                    zza(com_google_android_gms_measurement_internal_zzh, appMetadata);
                    zzbry().setTransactionSuccessful();
                    if (zzbsd().zzaz(2)) {
                        zzbsd().zzbtc().zzj("Event recorded", com_google_android_gms_measurement_internal_zzh);
                    }
                    zzbry().endTransaction();
                    zzbue();
                    zzbsd().zzbtc().zzj("Background event processing time, ms", Long.valueOf(((System.nanoTime() - nanoTime) + 500000) / 1000000));
                } finally {
                    zzbry().endTransaction();
                }
            }
        }
    }

    @WorkerThread
    void zzb(EventParcel eventParcel, String str) {
        zza zzln = zzbry().zzln(str);
        if (zzln == null || TextUtils.isEmpty(zzln.zzxc())) {
            zzbsd().zzbtb().zzj("No app data available; dropping event", str);
            return;
        }
        try {
            String str2 = getContext().getPackageManager().getPackageInfo(str, 0).versionName;
            if (!(zzln.zzxc() == null || zzln.zzxc().equals(str2))) {
                zzbsd().zzbsx().zzj("App version does not match; dropping event", str);
                return;
            }
        } catch (NameNotFoundException e) {
            if (!"_ui".equals(eventParcel.name)) {
                zzbsd().zzbsx().zzj("Could not find package", str);
            }
        }
        EventParcel eventParcel2 = eventParcel;
        zzb(eventParcel2, new AppMetadata(str, zzln.zzbps(), zzln.zzxc(), zzln.zzbpx(), zzln.zzbpy(), zzln.zzbpz(), zzln.zzbqa(), null, zzln.zzbqb(), false, zzln.zzbpu()));
    }

    @WorkerThread
    void zzb(UserAttributeParcel userAttributeParcel, AppMetadata appMetadata) {
        zzwu();
        zzzg();
        if (!TextUtils.isEmpty(appMetadata.aic)) {
            if (appMetadata.aih) {
                int zzmn = zzbrz().zzmn(userAttributeParcel.name);
                if (zzmn != 0) {
                    zzbrz().zze(zzmn, "_ev", zzbrz().zza(userAttributeParcel.name, zzbsf().zzbqo(), true));
                    return;
                }
                zzmn = zzbrz().zzm(userAttributeParcel.name, userAttributeParcel.getValue());
                if (zzmn != 0) {
                    zzbrz().zze(zzmn, "_ev", zzbrz().zza(userAttributeParcel.name, zzbsf().zzbqo(), true));
                    return;
                }
                Object zzn = zzbrz().zzn(userAttributeParcel.name, userAttributeParcel.getValue());
                if (zzn != null) {
                    zzak com_google_android_gms_measurement_internal_zzak = new zzak(appMetadata.packageName, userAttributeParcel.name, userAttributeParcel.amt, zzn);
                    zzbsd().zzbtb().zze("Setting user property", com_google_android_gms_measurement_internal_zzak.mName, zzn);
                    zzbry().beginTransaction();
                    try {
                        zze(appMetadata);
                        boolean zza = zzbry().zza(com_google_android_gms_measurement_internal_zzak);
                        zzbry().setTransactionSuccessful();
                        if (zza) {
                            zzbsd().zzbtb().zze("User property set", com_google_android_gms_measurement_internal_zzak.mName, com_google_android_gms_measurement_internal_zzak.zzcnn);
                        } else {
                            zzbsd().zzbsv().zze("Too many unique user properties are set. Ignoring user property.", com_google_android_gms_measurement_internal_zzak.mName, com_google_android_gms_measurement_internal_zzak.zzcnn);
                            zzbrz().zze(9, null, null);
                        }
                        zzbry().endTransaction();
                        return;
                    } catch (Throwable th) {
                        zzbry().endTransaction();
                    }
                } else {
                    return;
                }
            }
            zze(appMetadata);
        }
    }

    void zzb(zzaa com_google_android_gms_measurement_internal_zzaa) {
        this.aln++;
    }

    @WorkerThread
    void zzb(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        int i2 = 0;
        zzwu();
        zzzg();
        zzab.zzhr(str);
        if (bArr == null) {
            bArr = new byte[0];
        }
        zzbry().beginTransaction();
        try {
            zza zzln = zzbry().zzln(str);
            int i3 = ((i == 200 || i == AppLovinErrorCodes.NO_FILL || i == 304) && th == null) ? 1 : 0;
            if (zzln == null) {
                zzbsd().zzbsx().zzj("App does not exist in onConfigFetched", str);
            } else if (i3 != 0 || i == Constants.NO_SUCH_BUCKET_STATUS_CODE) {
                List list = map != null ? (List) map.get("Last-Modified") : null;
                String str2 = (list == null || list.size() <= 0) ? null : (String) list.get(0);
                if (i == Constants.NO_SUCH_BUCKET_STATUS_CODE || i == 304) {
                    if (zzbsa().zzmb(str) == null && !zzbsa().zzb(str, null, null)) {
                        zzbry().endTransaction();
                        return;
                    }
                } else if (!zzbsa().zzb(str, bArr, str2)) {
                    zzbry().endTransaction();
                    return;
                }
                zzln.zzba(zzyw().currentTimeMillis());
                zzbry().zza(zzln);
                if (i == Constants.NO_SUCH_BUCKET_STATUS_CODE) {
                    zzbsd().zzbsx().log("Config not found. Using empty config");
                } else {
                    zzbsd().zzbtc().zze("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                }
                if (zzbts().zzadj() && zzbud()) {
                    zzbuc();
                } else {
                    zzbue();
                }
            } else {
                zzln.zzbb(zzyw().currentTimeMillis());
                zzbry().zza(zzln);
                zzbsd().zzbtc().zze("Fetching config failed. code, error", Integer.valueOf(i), th);
                zzbsa().zzmd(str);
                zzbse().ajZ.set(zzyw().currentTimeMillis());
                if (i == 503 || i == 429) {
                    i2 = 1;
                }
                if (i2 != 0) {
                    zzbse().aka.set(zzyw().currentTimeMillis());
                }
                zzbue();
            }
            zzbry().setTransactionSuccessful();
        } finally {
            zzbry().endTransaction();
        }
    }

    boolean zzbl(long j) {
        return zzi(null, j);
    }

    public zzc zzbrt() {
        zza(this.alg);
        return this.alg;
    }

    public zzac zzbru() {
        zza(this.alc);
        return this.alc;
    }

    public zzn zzbrv() {
        zza(this.ald);
        return this.ald;
    }

    public zzg zzbrw() {
        zza(this.alb);
        return this.alb;
    }

    public zzad zzbrx() {
        zza(this.ala);
        return this.ala;
    }

    public zze zzbry() {
        zza(this.akY);
        return this.akY;
    }

    public zzal zzbrz() {
        zza(this.akX);
        return this.akX;
    }

    public zzv zzbsa() {
        zza(this.akV);
        return this.akV;
    }

    public zzaf zzbsb() {
        zza(this.akU);
        return this.akU;
    }

    public zzw zzbsc() {
        zza(this.akT);
        return this.akT;
    }

    public zzp zzbsd() {
        zza(this.akS);
        return this.akS;
    }

    public zzt zzbse() {
        zza(this.akR);
        return this.akR;
    }

    public zzd zzbsf() {
        return this.akQ;
    }

    @WorkerThread
    protected boolean zzbto() {
        zzzg();
        zzwu();
        if (this.alj == null) {
            boolean z = zzbrz().zzeo("android.permission.INTERNET") && zzbrz().zzeo("android.permission.ACCESS_NETWORK_STATE") && zzu.zzav(getContext()) && zzae.zzaw(getContext());
            this.alj = Boolean.valueOf(z);
            if (this.alj.booleanValue() && !zzbsf().zzabc()) {
                this.alj = Boolean.valueOf(zzbrz().zzmq(zzbrv().zzbps()));
            }
        }
        return this.alj.booleanValue();
    }

    public zzp zzbtp() {
        return (this.akS == null || !this.akS.isInitialized()) ? null : this.akS;
    }

    zzw zzbtq() {
        return this.akT;
    }

    public AppMeasurement zzbtr() {
        return this.akW;
    }

    public zzq zzbts() {
        zza(this.akZ);
        return this.akZ;
    }

    public zzr zzbtt() {
        if (this.ale != null) {
            return this.ale;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    public zzai zzbtu() {
        zza(this.alf);
        return this.alf;
    }

    FileChannel zzbtv() {
        return this.all;
    }

    @WorkerThread
    void zzbtw() {
        zzwu();
        zzzg();
        if (zzbui() && zzbtx()) {
            zzu(zza(zzbtv()), zzbrv().zzbst());
        }
    }

    @WorkerThread
    boolean zzbtx() {
        zzwu();
        try {
            this.all = new RandomAccessFile(new File(getContext().getFilesDir(), this.akY.zzaab()), "rw").getChannel();
            this.alk = this.all.tryLock();
            if (this.alk != null) {
                zzbsd().zzbtc().log("Storage concurrent access okay");
                return true;
            }
            zzbsd().zzbsv().log("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            zzbsd().zzbsv().zzj("Failed to acquire storage lock", e);
        } catch (IOException e2) {
            zzbsd().zzbsv().zzj("Failed to access storage lock file", e2);
        }
    }

    protected boolean zzbty() {
        return false;
    }

    long zzbtz() {
        return ((((zzyw().currentTimeMillis() + zzbse().zzbtg()) / 1000) / 60) / 60) / 24;
    }

    void zzbua() {
        if (!zzbsf().zzabc()) {
            throw new IllegalStateException("Unexpected call on client side");
        }
    }

    @WorkerThread
    public void zzbuc() {
        Map map = null;
        int i = 0;
        zzwu();
        zzzg();
        if (!zzbsf().zzabc()) {
            Boolean zzbtj = zzbse().zzbtj();
            if (zzbtj == null) {
                zzbsd().zzbsx().log("Upload data called on the client side before use of service was decided");
                return;
            } else if (zzbtj.booleanValue()) {
                zzbsd().zzbsv().log("Upload called in the client side when service should be used");
                return;
            }
        }
        if (zzbub()) {
            zzbsd().zzbsx().log("Uploading requested multiple times");
        } else if (zzbts().zzadj()) {
            long currentTimeMillis = zzyw().currentTimeMillis();
            zzbl(currentTimeMillis - zzbsf().zzbrl());
            long j = zzbse().ajY.get();
            if (j != 0) {
                zzbsd().zzbtb().zzj("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(currentTimeMillis - j)));
            }
            String zzbsg = zzbry().zzbsg();
            if (TextUtils.isEmpty(zzbsg)) {
                String zzbi = zzbry().zzbi(currentTimeMillis - zzbsf().zzbrl());
                if (!TextUtils.isEmpty(zzbi)) {
                    zza zzln = zzbry().zzln(zzbi);
                    if (zzln != null) {
                        String zzap = zzbsf().zzap(zzln.zzbps(), zzln.zzawo());
                        try {
                            URL url = new URL(zzap);
                            zzbsd().zzbtc().zzj("Fetching remote configuration", zzln.zzsh());
                            zzug.zzb zzmb = zzbsa().zzmb(zzln.zzsh());
                            CharSequence zzmc = zzbsa().zzmc(zzln.zzsh());
                            if (!(zzmb == null || TextUtils.isEmpty(zzmc))) {
                                map = new ArrayMap();
                                map.put(Headers.GET_OBJECT_IF_MODIFIED_SINCE, zzmc);
                            }
                            zzbts().zza(zzbi, url, map, new zza(this) {
                                final /* synthetic */ zzx alp;

                                {
                                    this.alp = r1;
                                }

                                public void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
                                    this.alp.zzb(str, i, th, bArr, map);
                                }
                            });
                            return;
                        } catch (MalformedURLException e) {
                            zzbsd().zzbsv().zzj("Failed to parse config URL. Not fetching", zzap);
                            return;
                        }
                    }
                    return;
                }
                return;
            }
            List<Pair> zzn = zzbry().zzn(zzbsg, zzbsf().zzlj(zzbsg), zzbsf().zzlk(zzbsg));
            if (!zzn.isEmpty()) {
                zzuh.zze com_google_android_gms_internal_zzuh_zze;
                Object obj;
                List subList;
                for (Pair pair : zzn) {
                    com_google_android_gms_internal_zzuh_zze = (zzuh.zze) pair.first;
                    if (!TextUtils.isEmpty(com_google_android_gms_internal_zzuh_zze.anI)) {
                        obj = com_google_android_gms_internal_zzuh_zze.anI;
                        break;
                    }
                }
                obj = null;
                if (obj != null) {
                    for (int i2 = 0; i2 < zzn.size(); i2++) {
                        com_google_android_gms_internal_zzuh_zze = (zzuh.zze) ((Pair) zzn.get(i2)).first;
                        if (!TextUtils.isEmpty(com_google_android_gms_internal_zzuh_zze.anI) && !com_google_android_gms_internal_zzuh_zze.anI.equals(obj)) {
                            subList = zzn.subList(0, i2);
                            break;
                        }
                    }
                }
                subList = zzn;
                zzd com_google_android_gms_internal_zzuh_zzd = new zzd();
                com_google_android_gms_internal_zzuh_zzd.ans = new zzuh.zze[subList.size()];
                List arrayList = new ArrayList(subList.size());
                while (i < com_google_android_gms_internal_zzuh_zzd.ans.length) {
                    com_google_android_gms_internal_zzuh_zzd.ans[i] = (zzuh.zze) ((Pair) subList.get(i)).first;
                    arrayList.add((Long) ((Pair) subList.get(i)).second);
                    com_google_android_gms_internal_zzuh_zzd.ans[i].anH = Long.valueOf(zzbsf().zzbpz());
                    com_google_android_gms_internal_zzuh_zzd.ans[i].anx = Long.valueOf(currentTimeMillis);
                    com_google_android_gms_internal_zzuh_zzd.ans[i].anN = Boolean.valueOf(zzbsf().zzabc());
                    i++;
                }
                Object zzb = zzbsd().zzaz(2) ? zzal.zzb(com_google_android_gms_internal_zzuh_zzd) : null;
                byte[] zza = zzbrz().zza(com_google_android_gms_internal_zzuh_zzd);
                String zzbrk = zzbsf().zzbrk();
                try {
                    URL url2 = new URL(zzbrk);
                    zzad(arrayList);
                    zzbse().ajZ.set(currentTimeMillis);
                    Object obj2 = "?";
                    if (com_google_android_gms_internal_zzuh_zzd.ans.length > 0) {
                        obj2 = com_google_android_gms_internal_zzuh_zzd.ans[0].zzck;
                    }
                    zzbsd().zzbtc().zzd("Uploading data. app, uncompressed size, data", obj2, Integer.valueOf(zza.length), zzb);
                    zzbts().zza(zzbsg, url2, zza, null, new zza(this) {
                        final /* synthetic */ zzx alp;

                        {
                            this.alp = r1;
                        }

                        public void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
                            this.alp.zza(i, th, bArr);
                        }
                    });
                } catch (MalformedURLException e2) {
                    zzbsd().zzbsv().zzj("Failed to parse upload URL. Not uploading", zzbrk);
                }
            }
        } else {
            zzbsd().zzbsx().log("Network not connected, ignoring upload request");
            zzbue();
        }
    }

    void zzbug() {
        this.alo++;
    }

    @WorkerThread
    void zzbuh() {
        zzwu();
        zzzg();
        if (!this.ali) {
            zzbsd().zzbta().log("This instance being marked as an uploader");
            zzbtw();
        }
        this.ali = true;
    }

    @WorkerThread
    boolean zzbui() {
        zzwu();
        zzzg();
        return this.ali || zzbty();
    }

    void zzc(AppMetadata appMetadata) {
        zzwu();
        zzzg();
        zzab.zzhr(appMetadata.packageName);
        zze(appMetadata);
    }

    @WorkerThread
    void zzc(AppMetadata appMetadata, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("_et", 1);
        zzb(new EventParcel("_e", new EventParams(bundle), "auto", j), appMetadata);
    }

    @WorkerThread
    void zzc(UserAttributeParcel userAttributeParcel, AppMetadata appMetadata) {
        zzwu();
        zzzg();
        if (!TextUtils.isEmpty(appMetadata.aic)) {
            if (appMetadata.aih) {
                zzbsd().zzbtb().zzj("Removing user property", userAttributeParcel.name);
                zzbry().beginTransaction();
                try {
                    zze(appMetadata);
                    zzbry().zzar(appMetadata.packageName, userAttributeParcel.name);
                    zzbry().setTransactionSuccessful();
                    zzbsd().zzbtb().zzj("User property removed", userAttributeParcel.name);
                } finally {
                    zzbry().endTransaction();
                }
            } else {
                zze(appMetadata);
            }
        }
    }

    @WorkerThread
    public void zzd(AppMetadata appMetadata) {
        zzwu();
        zzzg();
        zzab.zzy(appMetadata);
        zzab.zzhr(appMetadata.packageName);
        if (!TextUtils.isEmpty(appMetadata.aic)) {
            if (appMetadata.aih) {
                long currentTimeMillis = zzyw().currentTimeMillis();
                zzbry().beginTransaction();
                try {
                    zza(appMetadata, currentTimeMillis);
                    zze(appMetadata);
                    if (zzbry().zzaq(appMetadata.packageName, "_f") == null) {
                        zzb(new UserAttributeParcel("_fot", currentTimeMillis, Long.valueOf((1 + (currentTimeMillis / 3600000)) * 3600000), "auto"), appMetadata);
                        zzb(appMetadata, currentTimeMillis);
                        zzc(appMetadata, currentTimeMillis);
                    } else if (appMetadata.aii) {
                        zzd(appMetadata, currentTimeMillis);
                    }
                    zzbry().setTransactionSuccessful();
                } finally {
                    zzbry().endTransaction();
                }
            } else {
                zze(appMetadata);
            }
        }
    }

    @WorkerThread
    void zzd(AppMetadata appMetadata, long j) {
        zzb(new EventParcel("_cd", new EventParams(new Bundle()), "auto", j), appMetadata);
    }

    @WorkerThread
    boolean zzu(int i, int i2) {
        zzwu();
        if (i > i2) {
            zzbsd().zzbsv().zze("Panic: can't downgrade version. Previous, current version", Integer.valueOf(i), Integer.valueOf(i2));
            return false;
        }
        if (i < i2) {
            if (zza(i2, zzbtv())) {
                zzbsd().zzbtc().zze("Storage version upgraded. Previous, current version", Integer.valueOf(i), Integer.valueOf(i2));
            } else {
                zzbsd().zzbsv().zze("Storage version upgrade failed. Previous, current version", Integer.valueOf(i), Integer.valueOf(i2));
                return false;
            }
        }
        return true;
    }

    @WorkerThread
    public void zzwu() {
        zzbsc().zzwu();
    }

    void zzyv() {
        if (zzbsf().zzabc()) {
            throw new IllegalStateException("Unexpected call on package side");
        }
    }

    public zze zzyw() {
        return this.zzaoc;
    }

    void zzzg() {
        if (!this.zzcwq) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }
}
