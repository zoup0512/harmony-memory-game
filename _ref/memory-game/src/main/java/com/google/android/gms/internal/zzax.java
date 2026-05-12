package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.clearcut.zzb;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.zzae.zza;
import com.google.android.gms.internal.zzae.zzd;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzax {
    private static final String TAG = zzax.class.getSimpleName();
    protected static final Object zzagr = new Object();
    private static zzc zzagt = null;
    private volatile boolean zzafn = false;
    protected Context zzagf;
    private ExecutorService zzagg;
    private DexClassLoader zzagh;
    private zzau zzagi;
    private byte[] zzagj;
    private volatile AdvertisingIdClient zzagk = null;
    private Future zzagl = null;
    private volatile zza zzagm = null;
    private Future zzagn = null;
    private zzam zzago;
    private GoogleApiClient zzagp = null;
    protected boolean zzagq = false;
    protected boolean zzags = false;
    protected boolean zzagu = false;
    private Map<Pair<String, String>, zzbo> zzagv;

    private zzax(Context context) {
        this.zzagf = context;
        this.zzagv = new HashMap();
    }

    public static zzax zza(Context context, String str, String str2, boolean z) {
        zzax com_google_android_gms_internal_zzax = new zzax(context);
        try {
            if (com_google_android_gms_internal_zzax.zzc(str, str2, z)) {
                return com_google_android_gms_internal_zzax;
            }
        } catch (zzaw e) {
        }
        return null;
    }

    @NonNull
    private File zza(String str, File file, String str2) throws zzau.zza, IOException {
        File file2 = new File(String.format("%s/%s.jar", new Object[]{file, str2}));
        if (!file2.exists()) {
            byte[] zzc = this.zzagi.zzc(this.zzagj, str);
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            fileOutputStream.write(zzc, 0, zzc.length);
            fileOutputStream.close();
        }
        return file2;
    }

    private void zza(File file) {
        if (file.exists()) {
            file.delete();
            return;
        }
        Log.d(TAG, String.format("File %s not found. No need for deletion", new Object[]{file.getAbsolutePath()}));
    }

    private void zza(File file, String str) {
        FileInputStream fileInputStream;
        Throwable th;
        File file2 = new File(String.format("%s/%s.tmp", new Object[]{file, str}));
        if (!file2.exists()) {
            File file3 = new File(String.format("%s/%s.dex", new Object[]{file, str}));
            if (file3.exists()) {
                FileInputStream fileInputStream2 = null;
                long length = file3.length();
                if (length > 0) {
                    byte[] bArr = new byte[((int) length)];
                    try {
                        fileInputStream = new FileInputStream(file3);
                        try {
                            if (fileInputStream.read(bArr) <= 0) {
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e) {
                                    }
                                }
                                zza(file3);
                                return;
                            }
                            zzapv com_google_android_gms_internal_zzae_zzd = new zzd();
                            com_google_android_gms_internal_zzae_zzd.zzev = VERSION.SDK.getBytes();
                            com_google_android_gms_internal_zzae_zzd.zzeu = str.getBytes();
                            bArr = this.zzagi.zzd(this.zzagj, bArr).getBytes();
                            com_google_android_gms_internal_zzae_zzd.data = bArr;
                            com_google_android_gms_internal_zzae_zzd.zzet = zzak.zzg(bArr);
                            file2.createNewFile();
                            FileOutputStream fileOutputStream = new FileOutputStream(file2);
                            byte[] zzf = zzapv.zzf(com_google_android_gms_internal_zzae_zzd);
                            fileOutputStream.write(zzf, 0, zzf.length);
                            fileOutputStream.close();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e2) {
                                }
                            }
                            zza(file3);
                        } catch (IOException e3) {
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e4) {
                                }
                            }
                            zza(file3);
                        } catch (NoSuchAlgorithmException e5) {
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            zza(file3);
                        } catch (zzau.zza e6) {
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            zza(file3);
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            fileInputStream2 = fileInputStream;
                            th = th3;
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e7) {
                                }
                            }
                            zza(file3);
                            throw th;
                        }
                    } catch (IOException e8) {
                        fileInputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        zza(file3);
                    } catch (NoSuchAlgorithmException e9) {
                        fileInputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        zza(file3);
                    } catch (zzau.zza e10) {
                        fileInputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        zza(file3);
                    } catch (Throwable th4) {
                        th = th4;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        zza(file3);
                        throw th;
                    }
                }
            }
        }
    }

    private boolean zzb(File file, String str) {
        File file2 = new File(String.format("%s/%s.tmp", new Object[]{file, str}));
        if (!file2.exists()) {
            return false;
        }
        File file3 = new File(String.format("%s/%s.dex", new Object[]{file, str}));
        if (file3.exists()) {
            return false;
        }
        try {
            long length = file2.length();
            if (length <= 0) {
                zza(file2);
                return false;
            }
            byte[] bArr = new byte[((int) length)];
            if (new FileInputStream(file2).read(bArr) <= 0) {
                Log.d(TAG, "Cannot read the cache data.");
                zza(file2);
                return false;
            }
            zzd zzd = zzd.zzd(bArr);
            if (str.equals(new String(zzd.zzeu)) && Arrays.equals(zzd.zzet, zzak.zzg(zzd.data)) && Arrays.equals(zzd.zzev, VERSION.SDK.getBytes())) {
                byte[] zzc = this.zzagi.zzc(this.zzagj, new String(zzd.data));
                file3.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file3);
                fileOutputStream.write(zzc, 0, zzc.length);
                fileOutputStream.close();
                return true;
            }
            zza(file2);
            return false;
        } catch (IOException e) {
            return false;
        } catch (NoSuchAlgorithmException e2) {
            return false;
        } catch (zzau.zza e3) {
            return false;
        }
    }

    private void zzc(boolean z) {
        this.zzafn = z;
        if (z) {
            this.zzagl = this.zzagg.submit(new Runnable(this) {
                final /* synthetic */ zzax zzagw;

                {
                    this.zzagw = r1;
                }

                public void run() {
                    this.zzagw.zzcn();
                }
            });
        }
    }

    private boolean zzc(String str, String str2, boolean z) throws zzaw {
        this.zzagg = Executors.newCachedThreadPool();
        zzc(z);
        zzcq();
        zzco();
        this.zzagi = new zzau(null);
        try {
            this.zzagj = this.zzagi.zzl(str);
            boolean zzm = zzm(str2);
            this.zzago = new zzam(this);
            return zzm;
        } catch (Throwable e) {
            throw new zzaw(e);
        }
    }

    private void zzcn() {
        try {
            if (this.zzagk == null) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zzagf);
                advertisingIdClient.start();
                this.zzagk = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException e) {
            this.zzagk = null;
        } catch (IOException e2) {
            this.zzagk = null;
        } catch (GooglePlayServicesRepairableException e3) {
            this.zzagk = null;
        }
    }

    private void zzcp() {
        if (this.zzags) {
            try {
                this.zzagm = com.google.android.gms.gass.internal.zza.zzg(this.zzagf, this.zzagf.getPackageName(), Integer.toString(this.zzagf.getPackageManager().getPackageInfo(this.zzagf.getPackageName(), 0).versionCode));
            } catch (NameNotFoundException e) {
            }
        }
    }

    private void zzcq() {
        boolean z = true;
        zzagt = zzc.zzang();
        this.zzagq = zzagt.zzbn(this.zzagf) > 0;
        if (zzagt.isGooglePlayServicesAvailable(this.zzagf) != 0) {
            z = false;
        }
        this.zzags = z;
        if (this.zzagf.getApplicationContext() != null) {
            this.zzagp = new Builder(this.zzagf).addApi(zzb.API).build();
        }
        zzdc.initialize(this.zzagf);
    }

    private boolean zzm(String str) throws zzaw {
        File file;
        String zzax;
        File zza;
        try {
            File cacheDir = this.zzagf.getCacheDir();
            if (cacheDir == null) {
                cacheDir = this.zzagf.getDir("dex", 0);
                if (cacheDir == null) {
                    throw new zzaw();
                }
            }
            file = cacheDir;
            zzax = zzav.zzax();
            zza = zza(str, file, zzax);
            zzb(file, zzax);
            this.zzagh = new DexClassLoader(zza.getAbsolutePath(), file.getAbsolutePath(), null, this.zzagf.getClassLoader());
            zza(zza);
            zza(file, zzax);
            zzn(String.format("%s/%s.dex", new Object[]{file, zzax}));
            return true;
        } catch (Throwable e) {
            throw new zzaw(e);
        } catch (Throwable e2) {
            throw new zzaw(e2);
        } catch (Throwable e22) {
            throw new zzaw(e22);
        } catch (Throwable e222) {
            throw new zzaw(e222);
        } catch (Throwable th) {
            zza(zza);
            zza(file, zzax);
            zzn(String.format("%s/%s.dex", new Object[]{file, zzax}));
        }
    }

    private void zzn(String str) {
        zza(new File(str));
    }

    public Context getContext() {
        return this.zzagf;
    }

    public boolean zza(String str, String str2, List<Class> list) {
        if (this.zzagv.containsKey(new Pair(str, str2))) {
            return false;
        }
        this.zzagv.put(new Pair(str, str2), new zzbo(this, str, str2, list));
        return true;
    }

    public int zzat() {
        zzam zzck = zzck();
        return zzck != null ? zzck.zzat() : Integer.MIN_VALUE;
    }

    public Method zzc(String str, String str2) {
        zzbo com_google_android_gms_internal_zzbo = (zzbo) this.zzagv.get(new Pair(str, str2));
        return com_google_android_gms_internal_zzbo == null ? null : com_google_android_gms_internal_zzbo.zzcz();
    }

    public ExecutorService zzcd() {
        return this.zzagg;
    }

    public DexClassLoader zzce() {
        return this.zzagh;
    }

    public zzau zzcf() {
        return this.zzagi;
    }

    public byte[] zzcg() {
        return this.zzagj;
    }

    public GoogleApiClient zzch() {
        return this.zzagp;
    }

    public boolean zzci() {
        return this.zzagq;
    }

    public boolean zzcj() {
        return this.zzagu;
    }

    public zzam zzck() {
        return this.zzago;
    }

    public zza zzcl() {
        return this.zzagm;
    }

    public Future zzcm() {
        return this.zzagn;
    }

    void zzco() {
        if (((Boolean) zzdc.zzbbu.get()).booleanValue()) {
            this.zzagn = this.zzagg.submit(new Runnable(this) {
                final /* synthetic */ zzax zzagw;

                {
                    this.zzagw = r1;
                }

                public void run() {
                    this.zzagw.zzcp();
                }
            });
        }
    }

    public AdvertisingIdClient zzcr() {
        if (!this.zzafn) {
            return null;
        }
        if (this.zzagk != null) {
            return this.zzagk;
        }
        if (this.zzagl != null) {
            try {
                this.zzagl.get(2000, TimeUnit.MILLISECONDS);
                this.zzagl = null;
            } catch (InterruptedException e) {
            } catch (ExecutionException e2) {
            } catch (TimeoutException e3) {
                this.zzagl.cancel(true);
            }
        }
        return this.zzagk;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzcs() {
        /*
        r2 = this;
        r1 = zzagr;
        monitor-enter(r1);
        r0 = r2.zzagu;	 Catch:{ all -> 0x001b }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
    L_0x0008:
        return;
    L_0x0009:
        r0 = r2.zzags;	 Catch:{ all -> 0x001b }
        if (r0 == 0) goto L_0x001e;
    L_0x000d:
        r0 = r2.zzagp;	 Catch:{ all -> 0x001b }
        if (r0 == 0) goto L_0x001e;
    L_0x0011:
        r0 = r2.zzagp;	 Catch:{ all -> 0x001b }
        r0.connect();	 Catch:{ all -> 0x001b }
        r0 = 1;
        r2.zzagu = r0;	 Catch:{ all -> 0x001b }
    L_0x0019:
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        goto L_0x0008;
    L_0x001b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        throw r0;
    L_0x001e:
        r0 = 0;
        r2.zzagu = r0;	 Catch:{ all -> 0x001b }
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzax.zzcs():void");
    }

    public void zzct() {
        synchronized (zzagr) {
            if (this.zzagu && this.zzagp != null) {
                this.zzagp.disconnect();
                this.zzagu = false;
            }
        }
    }
}
