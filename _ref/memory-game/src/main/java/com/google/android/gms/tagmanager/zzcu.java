package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import com.google.android.gms.internal.zzadu.zza;
import com.google.android.gms.internal.zzadw;
import com.google.android.gms.internal.zzadw.zzc;
import com.google.android.gms.internal.zzadw.zzg;
import com.google.android.gms.internal.zzah.zzf;
import com.google.android.gms.internal.zzapu;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

class zzcu implements zzf {
    private final String auF;
    private zzbm<zza> axf;
    private final ExecutorService axl = Executors.newSingleThreadExecutor();
    private final Context mContext;

    zzcu(Context context, String str) {
        this.mContext = context;
        this.auF = str;
    }

    private zzc zza(ByteArrayOutputStream byteArrayOutputStream) {
        zzc com_google_android_gms_internal_zzadw_zzc = null;
        try {
            com_google_android_gms_internal_zzadw_zzc = zzbg.zzox(byteArrayOutputStream.toString("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            zzbn.zzcv("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
        } catch (JSONException e2) {
            zzbn.zzcx("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
        }
        return com_google_android_gms_internal_zzadw_zzc;
    }

    private zzc zzak(byte[] bArr) {
        try {
            zzc zzb = zzadw.zzb(zzf.zze(bArr));
            if (zzb == null) {
                return zzb;
            }
            zzbn.v("The container was successfully loaded from the resource (using binary file)");
            return zzb;
        } catch (zzapu e) {
            zzbn.e("The resource file is corrupted. The container cannot be extracted from the binary file");
            return null;
        } catch (zzg e2) {
            zzbn.zzcx("The resource file is invalid. The container from the binary file is invalid");
            return null;
        }
    }

    private void zzd(zza com_google_android_gms_internal_zzadu_zza) throws IllegalArgumentException {
        if (com_google_android_gms_internal_zzadu_zza.zzwr == null && com_google_android_gms_internal_zzadu_zza.aCW == null) {
            throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
        }
    }

    public synchronized void release() {
        this.axl.shutdown();
    }

    public void zza(zzbm<zza> com_google_android_gms_tagmanager_zzbm_com_google_android_gms_internal_zzadu_zza) {
        this.axf = com_google_android_gms_tagmanager_zzbm_com_google_android_gms_internal_zzadu_zza;
    }

    public void zzb(final zza com_google_android_gms_internal_zzadu_zza) {
        this.axl.execute(new Runnable(this) {
            final /* synthetic */ zzcu axm;

            public void run() {
                this.axm.zzc(com_google_android_gms_internal_zzadu_zza);
            }
        });
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean zzc(com.google.android.gms.internal.zzadu.zza r5) {
        /*
        r4 = this;
        r0 = 0;
        r1 = r4.zzccr();
        r2 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0016 }
        r2.<init>(r1);	 Catch:{ FileNotFoundException -> 0x0016 }
        r3 = com.google.android.gms.internal.zzapv.zzf(r5);	 Catch:{ IOException -> 0x0024 }
        r2.write(r3);	 Catch:{ IOException -> 0x0024 }
        r2.close();	 Catch:{ IOException -> 0x001d }
    L_0x0014:
        r0 = 1;
    L_0x0015:
        return r0;
    L_0x0016:
        r1 = move-exception;
        r1 = "Error opening resource file for writing";
        com.google.android.gms.tagmanager.zzbn.e(r1);
        goto L_0x0015;
    L_0x001d:
        r0 = move-exception;
        r0 = "error closing stream for writing resource to disk";
        com.google.android.gms.tagmanager.zzbn.zzcx(r0);
        goto L_0x0014;
    L_0x0024:
        r3 = move-exception;
        r3 = "Error writing resource to disk. Removing resource from disk.";
        com.google.android.gms.tagmanager.zzbn.zzcx(r3);	 Catch:{ all -> 0x0038 }
        r1.delete();	 Catch:{ all -> 0x0038 }
        r2.close();	 Catch:{ IOException -> 0x0031 }
        goto L_0x0015;
    L_0x0031:
        r1 = move-exception;
        r1 = "error closing stream for writing resource to disk";
        com.google.android.gms.tagmanager.zzbn.zzcx(r1);
        goto L_0x0015;
    L_0x0038:
        r0 = move-exception;
        r2.close();	 Catch:{ IOException -> 0x003d }
    L_0x003c:
        throw r0;
    L_0x003d:
        r1 = move-exception;
        r1 = "error closing stream for writing resource to disk";
        com.google.android.gms.tagmanager.zzbn.zzcx(r1);
        goto L_0x003c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcu.zzc(com.google.android.gms.internal.zzadu$zza):boolean");
    }

    public void zzcav() {
        this.axl.execute(new Runnable(this) {
            final /* synthetic */ zzcu axm;

            {
                this.axm = r1;
            }

            public void run() {
                this.axm.zzccq();
            }
        });
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void zzccq() {
        /*
        r3 = this;
        r0 = r3.axf;
        if (r0 != 0) goto L_0x000c;
    L_0x0004:
        r0 = new java.lang.IllegalStateException;
        r1 = "Callback must be set before execute";
        r0.<init>(r1);
        throw r0;
    L_0x000c:
        r0 = r3.axf;
        r0.zzcau();
        r0 = "Attempting to load resource from disk";
        com.google.android.gms.tagmanager.zzbn.v(r0);
        r0 = com.google.android.gms.tagmanager.zzci.zzcci();
        r0 = r0.zzccj();
        r1 = com.google.android.gms.tagmanager.zzci.zza.CONTAINER;
        if (r0 == r1) goto L_0x002e;
    L_0x0022:
        r0 = com.google.android.gms.tagmanager.zzci.zzcci();
        r0 = r0.zzccj();
        r1 = com.google.android.gms.tagmanager.zzci.zza.CONTAINER_DEBUG;
        if (r0 != r1) goto L_0x0046;
    L_0x002e:
        r0 = r3.auF;
        r1 = com.google.android.gms.tagmanager.zzci.zzcci();
        r1 = r1.getContainerId();
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0046;
    L_0x003e:
        r0 = r3.axf;
        r1 = com.google.android.gms.tagmanager.zzbm.zza.NOT_AVAILABLE;
        r0.zza(r1);
    L_0x0045:
        return;
    L_0x0046:
        r1 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0070 }
        r0 = r3.zzccr();	 Catch:{ FileNotFoundException -> 0x0070 }
        r1.<init>(r0);	 Catch:{ FileNotFoundException -> 0x0070 }
        r0 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x0085, IllegalArgumentException -> 0x009d }
        r0.<init>();	 Catch:{ IOException -> 0x0085, IllegalArgumentException -> 0x009d }
        com.google.android.gms.internal.zzadw.zzc(r1, r0);	 Catch:{ IOException -> 0x0085, IllegalArgumentException -> 0x009d }
        r0 = r0.toByteArray();	 Catch:{ IOException -> 0x0085, IllegalArgumentException -> 0x009d }
        r0 = com.google.android.gms.internal.zzadu.zza.zzao(r0);	 Catch:{ IOException -> 0x0085, IllegalArgumentException -> 0x009d }
        r3.zzd(r0);	 Catch:{ IOException -> 0x0085, IllegalArgumentException -> 0x009d }
        r2 = r3.axf;	 Catch:{ IOException -> 0x0085, IllegalArgumentException -> 0x009d }
        r2.onSuccess(r0);	 Catch:{ IOException -> 0x0085, IllegalArgumentException -> 0x009d }
        r1.close();	 Catch:{ IOException -> 0x007e }
    L_0x006a:
        r0 = "The Disk resource was successfully read.";
        com.google.android.gms.tagmanager.zzbn.v(r0);
        goto L_0x0045;
    L_0x0070:
        r0 = move-exception;
        r0 = "Failed to find the resource in the disk";
        com.google.android.gms.tagmanager.zzbn.zzcv(r0);
        r0 = r3.axf;
        r1 = com.google.android.gms.tagmanager.zzbm.zza.NOT_AVAILABLE;
        r0.zza(r1);
        goto L_0x0045;
    L_0x007e:
        r0 = move-exception;
        r0 = "Error closing stream for reading resource from disk";
        com.google.android.gms.tagmanager.zzbn.zzcx(r0);
        goto L_0x006a;
    L_0x0085:
        r0 = move-exception;
        r0 = r3.axf;	 Catch:{ all -> 0x00b5 }
        r2 = com.google.android.gms.tagmanager.zzbm.zza.IO_ERROR;	 Catch:{ all -> 0x00b5 }
        r0.zza(r2);	 Catch:{ all -> 0x00b5 }
        r0 = "Failed to read the resource from disk";
        com.google.android.gms.tagmanager.zzbn.zzcx(r0);	 Catch:{ all -> 0x00b5 }
        r1.close();	 Catch:{ IOException -> 0x0096 }
        goto L_0x006a;
    L_0x0096:
        r0 = move-exception;
        r0 = "Error closing stream for reading resource from disk";
        com.google.android.gms.tagmanager.zzbn.zzcx(r0);
        goto L_0x006a;
    L_0x009d:
        r0 = move-exception;
        r0 = r3.axf;	 Catch:{ all -> 0x00b5 }
        r2 = com.google.android.gms.tagmanager.zzbm.zza.IO_ERROR;	 Catch:{ all -> 0x00b5 }
        r0.zza(r2);	 Catch:{ all -> 0x00b5 }
        r0 = "Failed to read the resource from disk. The resource is inconsistent";
        com.google.android.gms.tagmanager.zzbn.zzcx(r0);	 Catch:{ all -> 0x00b5 }
        r1.close();	 Catch:{ IOException -> 0x00ae }
        goto L_0x006a;
    L_0x00ae:
        r0 = move-exception;
        r0 = "Error closing stream for reading resource from disk";
        com.google.android.gms.tagmanager.zzbn.zzcx(r0);
        goto L_0x006a;
    L_0x00b5:
        r0 = move-exception;
        r1.close();	 Catch:{ IOException -> 0x00ba }
    L_0x00b9:
        throw r0;
    L_0x00ba:
        r1 = move-exception;
        r1 = "Error closing stream for reading resource from disk";
        com.google.android.gms.tagmanager.zzbn.zzcx(r1);
        goto L_0x00b9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcu.zzccq():void");
    }

    File zzccr() {
        String valueOf = String.valueOf("resource_");
        String valueOf2 = String.valueOf(this.auF);
        return new File(this.mContext.getDir("google_tagmanager", 0), valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    public zzc zzze(int i) {
        try {
            InputStream openRawResource = this.mContext.getResources().openRawResource(i);
            String valueOf = String.valueOf(this.mContext.getResources().getResourceName(i));
            zzbn.v(new StringBuilder(String.valueOf(valueOf).length() + 66).append("Attempting to load a container from the resource ID ").append(i).append(" (").append(valueOf).append(")").toString());
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzadw.zzc(openRawResource, byteArrayOutputStream);
                zzc zza = zza(byteArrayOutputStream);
                if (zza == null) {
                    return zzak(byteArrayOutputStream.toByteArray());
                }
                zzbn.v("The container was successfully loaded from the resource (using JSON file format)");
                return zza;
            } catch (IOException e) {
                String valueOf2 = String.valueOf(this.mContext.getResources().getResourceName(i));
                zzbn.zzcx(new StringBuilder(String.valueOf(valueOf2).length() + 67).append("Error reading the default container with resource ID ").append(i).append(" (").append(valueOf2).append(")").toString());
                return null;
            }
        } catch (NotFoundException e2) {
            zzbn.zzcx("Failed to load the container. No default container resource found with the resource ID " + i);
            return null;
        }
    }
}
