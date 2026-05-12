package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzin
public class zzfg extends zzfd {
    private static final Set<String> zzbjp = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat zzbjq = new DecimalFormat("#,###");
    private File zzbjr;
    private boolean zzbjs;

    public zzfg(zzlh com_google_android_gms_internal_zzlh) {
        super(com_google_android_gms_internal_zzlh);
        File cacheDir = this.mContext.getCacheDir();
        if (cacheDir == null) {
            zzb.zzcx("Context.getCacheDir() returned null");
            return;
        }
        this.zzbjr = new File(cacheDir, "admobVideoStreams");
        String str;
        String valueOf;
        if (!this.zzbjr.isDirectory() && !this.zzbjr.mkdirs()) {
            str = "Could not create preload cache directory at ";
            valueOf = String.valueOf(this.zzbjr.getAbsolutePath());
            zzb.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzbjr = null;
        } else if (!this.zzbjr.setReadable(true, false) || !this.zzbjr.setExecutable(true, false)) {
            str = "Could not set cache file permissions at ";
            valueOf = String.valueOf(this.zzbjr.getAbsolutePath());
            zzb.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzbjr = null;
        }
    }

    private File zzb(File file) {
        return new File(this.zzbjr, String.valueOf(file.getName()).concat(".done"));
    }

    private static void zzc(File file) {
        if (file.isFile()) {
            file.setLastModified(System.currentTimeMillis());
            return;
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
        }
    }

    public void abort() {
        this.zzbjs = true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean zzaz(java.lang.String r27) {
        /*
        r26 = this;
        r0 = r26;
        r2 = r0.zzbjr;
        if (r2 != 0) goto L_0x0013;
    L_0x0006:
        r2 = 0;
        r3 = "noCacheDir";
        r4 = 0;
        r0 = r26;
        r1 = r27;
        r0.zza(r1, r2, r3, r4);
        r2 = 0;
    L_0x0012:
        return r2;
    L_0x0013:
        r3 = r26.zzll();
        r2 = com.google.android.gms.internal.zzdc.zzaym;
        r2 = r2.get();
        r2 = (java.lang.Integer) r2;
        r2 = r2.intValue();
        if (r3 <= r2) goto L_0x003d;
    L_0x0025:
        r2 = r26.zzlm();
        if (r2 != 0) goto L_0x0013;
    L_0x002b:
        r2 = "Unable to expire stream cache";
        com.google.android.gms.ads.internal.util.client.zzb.zzcx(r2);
        r2 = 0;
        r3 = "expireFailed";
        r4 = 0;
        r0 = r26;
        r1 = r27;
        r0.zza(r1, r2, r3, r4);
        r2 = 0;
        goto L_0x0012;
    L_0x003d:
        r2 = r26.zzba(r27);
        r12 = new java.io.File;
        r0 = r26;
        r3 = r0.zzbjr;
        r12.<init>(r3, r2);
        r0 = r26;
        r13 = r0.zzb(r12);
        r2 = r12.isFile();
        if (r2 == 0) goto L_0x0087;
    L_0x0056:
        r2 = r13.isFile();
        if (r2 == 0) goto L_0x0087;
    L_0x005c:
        r2 = r12.length();
        r3 = (int) r2;
        r4 = "Stream cache hit at ";
        r2 = java.lang.String.valueOf(r27);
        r5 = r2.length();
        if (r5 == 0) goto L_0x0081;
    L_0x006d:
        r2 = r4.concat(r2);
    L_0x0071:
        com.google.android.gms.ads.internal.util.client.zzb.zzcv(r2);
        r2 = r12.getAbsolutePath();
        r0 = r26;
        r1 = r27;
        r0.zza(r1, r2, r3);
        r2 = 1;
        goto L_0x0012;
    L_0x0081:
        r2 = new java.lang.String;
        r2.<init>(r4);
        goto L_0x0071;
    L_0x0087:
        r0 = r26;
        r2 = r0.zzbjr;
        r2 = r2.getAbsolutePath();
        r3 = java.lang.String.valueOf(r2);
        r2 = java.lang.String.valueOf(r27);
        r4 = r2.length();
        if (r4 == 0) goto L_0x00d5;
    L_0x009d:
        r2 = r3.concat(r2);
        r8 = r2;
    L_0x00a2:
        r3 = zzbjp;
        monitor-enter(r3);
        r2 = zzbjp;	 Catch:{ all -> 0x00d2 }
        r2 = r2.contains(r8);	 Catch:{ all -> 0x00d2 }
        if (r2 == 0) goto L_0x00e2;
    L_0x00ad:
        r4 = "Stream cache already in progress at ";
        r2 = java.lang.String.valueOf(r27);	 Catch:{ all -> 0x00d2 }
        r5 = r2.length();	 Catch:{ all -> 0x00d2 }
        if (r5 == 0) goto L_0x00dc;
    L_0x00b9:
        r2 = r4.concat(r2);	 Catch:{ all -> 0x00d2 }
    L_0x00bd:
        com.google.android.gms.ads.internal.util.client.zzb.zzcx(r2);	 Catch:{ all -> 0x00d2 }
        r2 = r12.getAbsolutePath();	 Catch:{ all -> 0x00d2 }
        r4 = "inProgress";
        r5 = 0;
        r0 = r26;
        r1 = r27;
        r0.zza(r1, r2, r4, r5);	 Catch:{ all -> 0x00d2 }
        r2 = 0;
        monitor-exit(r3);	 Catch:{ all -> 0x00d2 }
        goto L_0x0012;
    L_0x00d2:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x00d2 }
        throw r2;
    L_0x00d5:
        r2 = new java.lang.String;
        r2.<init>(r3);
        r8 = r2;
        goto L_0x00a2;
    L_0x00dc:
        r2 = new java.lang.String;	 Catch:{ all -> 0x00d2 }
        r2.<init>(r4);	 Catch:{ all -> 0x00d2 }
        goto L_0x00bd;
    L_0x00e2:
        r2 = zzbjp;	 Catch:{ all -> 0x00d2 }
        r2.add(r8);	 Catch:{ all -> 0x00d2 }
        monitor-exit(r3);	 Catch:{ all -> 0x00d2 }
        r5 = 0;
        r10 = "error";
        r9 = 0;
        r2 = new java.net.URL;	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r0 = r27;
        r2.<init>(r0);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r3 = r2.openConnection();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = com.google.android.gms.internal.zzdc.zzayr;	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = r2.get();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = (java.lang.Integer) r2;	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = r2.intValue();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r3.setConnectTimeout(r2);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r3.setReadTimeout(r2);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = r3 instanceof java.net.HttpURLConnection;	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        if (r2 == 0) goto L_0x01dc;
    L_0x010d:
        r0 = r3;
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = r0;
        r2 = r2.getResponseCode();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r4 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r2 < r4) goto L_0x01dc;
    L_0x0119:
        r4 = "badUrl";
        r6 = "HTTP request failed. Code: ";
        r3 = java.lang.Integer.toString(r2);	 Catch:{ IOException -> 0x01d9, RuntimeException -> 0x0456 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ IOException -> 0x01d9, RuntimeException -> 0x0456 }
        r7 = r3.length();	 Catch:{ IOException -> 0x01d9, RuntimeException -> 0x0456 }
        if (r7 == 0) goto L_0x01d2;
    L_0x012b:
        r3 = r6.concat(r3);	 Catch:{ IOException -> 0x01d9, RuntimeException -> 0x0456 }
    L_0x012f:
        r6 = new java.io.IOException;	 Catch:{ IOException -> 0x015e, RuntimeException -> 0x045a }
        r7 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x015e, RuntimeException -> 0x045a }
        r9 = java.lang.String.valueOf(r27);	 Catch:{ IOException -> 0x015e, RuntimeException -> 0x045a }
        r9 = r9.length();	 Catch:{ IOException -> 0x015e, RuntimeException -> 0x045a }
        r9 = r9 + 32;
        r7.<init>(r9);	 Catch:{ IOException -> 0x015e, RuntimeException -> 0x045a }
        r9 = "HTTP status code ";
        r7 = r7.append(r9);	 Catch:{ IOException -> 0x015e, RuntimeException -> 0x045a }
        r2 = r7.append(r2);	 Catch:{ IOException -> 0x015e, RuntimeException -> 0x045a }
        r7 = " at ";
        r2 = r2.append(r7);	 Catch:{ IOException -> 0x015e, RuntimeException -> 0x045a }
        r0 = r27;
        r2 = r2.append(r0);	 Catch:{ IOException -> 0x015e, RuntimeException -> 0x045a }
        r2 = r2.toString();	 Catch:{ IOException -> 0x015e, RuntimeException -> 0x045a }
        r6.<init>(r2);	 Catch:{ IOException -> 0x015e, RuntimeException -> 0x045a }
        throw r6;	 Catch:{ IOException -> 0x015e, RuntimeException -> 0x045a }
    L_0x015e:
        r2 = move-exception;
    L_0x015f:
        r6 = r2 instanceof java.lang.RuntimeException;
        if (r6 == 0) goto L_0x016b;
    L_0x0163:
        r6 = com.google.android.gms.ads.internal.zzu.zzft();
        r7 = 1;
        r6.zzb(r2, r7);
    L_0x016b:
        r5.close();	 Catch:{ IOException -> 0x0450, NullPointerException -> 0x0453 }
    L_0x016e:
        r0 = r26;
        r5 = r0.zzbjs;
        if (r5 == 0) goto L_0x041f;
    L_0x0174:
        r2 = new java.lang.StringBuilder;
        r5 = java.lang.String.valueOf(r27);
        r5 = r5.length();
        r5 = r5 + 26;
        r2.<init>(r5);
        r5 = "Preload aborted for URL \"";
        r2 = r2.append(r5);
        r0 = r27;
        r2 = r2.append(r0);
        r5 = "\"";
        r2 = r2.append(r5);
        r2 = r2.toString();
        com.google.android.gms.ads.internal.util.client.zzb.zzcw(r2);
    L_0x019c:
        r2 = r12.exists();
        if (r2 == 0) goto L_0x01bf;
    L_0x01a2:
        r2 = r12.delete();
        if (r2 != 0) goto L_0x01bf;
    L_0x01a8:
        r5 = "Could not delete partial cache file at ";
        r2 = r12.getAbsolutePath();
        r2 = java.lang.String.valueOf(r2);
        r6 = r2.length();
        if (r6 == 0) goto L_0x0449;
    L_0x01b8:
        r2 = r5.concat(r2);
    L_0x01bc:
        com.google.android.gms.ads.internal.util.client.zzb.zzcx(r2);
    L_0x01bf:
        r2 = r12.getAbsolutePath();
        r0 = r26;
        r1 = r27;
        r0.zza(r1, r2, r4, r3);
        r2 = zzbjp;
        r2.remove(r8);
        r2 = 0;
        goto L_0x0012;
    L_0x01d2:
        r3 = new java.lang.String;	 Catch:{ IOException -> 0x01d9, RuntimeException -> 0x0456 }
        r3.<init>(r6);	 Catch:{ IOException -> 0x01d9, RuntimeException -> 0x0456 }
        goto L_0x012f;
    L_0x01d9:
        r2 = move-exception;
        r3 = r9;
        goto L_0x015f;
    L_0x01dc:
        r6 = r3.getContentLength();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        if (r6 >= 0) goto L_0x0216;
    L_0x01e2:
        r3 = "Stream cache aborted, missing content-length header at ";
        r2 = java.lang.String.valueOf(r27);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r4 = r2.length();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        if (r4 == 0) goto L_0x020b;
    L_0x01ee:
        r2 = r3.concat(r2);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
    L_0x01f2:
        com.google.android.gms.ads.internal.util.client.zzb.zzcx(r2);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = r12.getAbsolutePath();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r3 = "contentLengthMissing";
        r4 = 0;
        r0 = r26;
        r1 = r27;
        r0.zza(r1, r2, r3, r4);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = zzbjp;	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2.remove(r8);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = 0;
        goto L_0x0012;
    L_0x020b:
        r2 = new java.lang.String;	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2.<init>(r3);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        goto L_0x01f2;
    L_0x0211:
        r2 = move-exception;
        r3 = r9;
        r4 = r10;
        goto L_0x015f;
    L_0x0216:
        r2 = zzbjq;	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r14 = (long) r6;	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r4 = r2.format(r14);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = com.google.android.gms.internal.zzdc.zzayn;	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = r2.get();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = (java.lang.Integer) r2;	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r14 = r2.intValue();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        if (r6 <= r14) goto L_0x0290;
    L_0x022b:
        r2 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r3 = java.lang.String.valueOf(r4);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r3 = r3.length();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r3 = r3 + 33;
        r6 = java.lang.String.valueOf(r27);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r6 = r6.length();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r3 = r3 + r6;
        r2.<init>(r3);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r3 = "Content length ";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = r2.append(r4);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r3 = " exceeds limit at ";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r0 = r27;
        r2 = r2.append(r0);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = r2.toString();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        com.google.android.gms.ads.internal.util.client.zzb.zzcx(r2);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r3 = "File too big for full file cache. Size: ";
        r2 = java.lang.String.valueOf(r4);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r4 = r2.length();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        if (r4 == 0) goto L_0x0285;
    L_0x026c:
        r2 = r3.concat(r2);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
    L_0x0270:
        r3 = r12.getAbsolutePath();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r4 = "sizeExceeded";
        r0 = r26;
        r1 = r27;
        r0.zza(r1, r3, r4, r2);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = zzbjp;	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2.remove(r8);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = 0;
        goto L_0x0012;
    L_0x0285:
        r2 = new java.lang.String;	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2.<init>(r3);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        goto L_0x0270;
    L_0x028b:
        r2 = move-exception;
        r3 = r9;
        r4 = r10;
        goto L_0x015f;
    L_0x0290:
        r2 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r7 = java.lang.String.valueOf(r4);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r7 = r7.length();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r7 = r7 + 20;
        r11 = java.lang.String.valueOf(r27);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r11 = r11.length();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r7 = r7 + r11;
        r2.<init>(r7);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r7 = "Caching ";
        r2 = r2.append(r7);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = r2.append(r4);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r4 = " bytes from ";
        r2 = r2.append(r4);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r0 = r27;
        r2 = r2.append(r0);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = r2.toString();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        com.google.android.gms.ads.internal.util.client.zzb.zzcv(r2);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r2 = r3.getInputStream();	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r15 = java.nio.channels.Channels.newChannel(r2);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r11 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r11.<init>(r12);	 Catch:{ IOException -> 0x0211, RuntimeException -> 0x028b }
        r16 = r11.getChannel();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = 1048576; // 0x100000 float:1.469368E-39 double:5.180654E-318;
        r17 = java.nio.ByteBuffer.allocate(r2);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r18 = com.google.android.gms.ads.internal.zzu.zzfu();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r5 = 0;
        r20 = r18.currentTimeMillis();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = com.google.android.gms.internal.zzdc.zzayq;	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = r2.get();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = (java.lang.Long) r2;	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = r2.longValue();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r19 = new com.google.android.gms.internal.zzkr;	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r0 = r19;
        r0.<init>(r2);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = com.google.android.gms.internal.zzdc.zzayp;	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = r2.get();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = (java.lang.Long) r2;	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r22 = r2.longValue();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
    L_0x0304:
        r0 = r17;
        r2 = r15.read(r0);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        if (r2 < 0) goto L_0x03be;
    L_0x030c:
        r5 = r5 + r2;
        if (r5 <= r14) goto L_0x033c;
    L_0x030f:
        r4 = "sizeExceeded";
        r2 = "File too big for full file cache. Size: ";
        r3 = java.lang.Integer.toString(r5);	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        r5 = r3.length();	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        if (r5 == 0) goto L_0x0331;
    L_0x0321:
        r3 = r2.concat(r3);	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
    L_0x0325:
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x032d, RuntimeException -> 0x038b }
        r5 = "stream cache file size limit exceeded";
        r2.<init>(r5);	 Catch:{ IOException -> 0x032d, RuntimeException -> 0x038b }
        throw r2;	 Catch:{ IOException -> 0x032d, RuntimeException -> 0x038b }
    L_0x032d:
        r2 = move-exception;
        r5 = r11;
        goto L_0x015f;
    L_0x0331:
        r3 = new java.lang.String;	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        r3.<init>(r2);	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        goto L_0x0325;
    L_0x0337:
        r2 = move-exception;
        r3 = r9;
        r5 = r11;
        goto L_0x015f;
    L_0x033c:
        r17.flip();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
    L_0x033f:
        r2 = r16.write(r17);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        if (r2 > 0) goto L_0x033f;
    L_0x0345:
        r17.clear();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = r18.currentTimeMillis();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = r2 - r20;
        r24 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r24 = r24 * r22;
        r2 = (r2 > r24 ? 1 : (r2 == r24 ? 0 : -1));
        if (r2 <= 0) goto L_0x038f;
    L_0x0356:
        r4 = "downloadTimeout";
        r2 = java.lang.Long.toString(r22);	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        r5 = java.lang.String.valueOf(r2);	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        r5 = r5.length();	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        r5 = r5 + 29;
        r3.<init>(r5);	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        r5 = "Timeout exceeded. Limit: ";
        r3 = r3.append(r5);	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        r2 = r3.append(r2);	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        r3 = " sec";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        r3 = r2.toString();	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x032d, RuntimeException -> 0x038b }
        r5 = "stream cache time limit exceeded";
        r2.<init>(r5);	 Catch:{ IOException -> 0x032d, RuntimeException -> 0x038b }
        throw r2;	 Catch:{ IOException -> 0x032d, RuntimeException -> 0x038b }
    L_0x038b:
        r2 = move-exception;
        r5 = r11;
        goto L_0x015f;
    L_0x038f:
        r0 = r26;
        r2 = r0.zzbjs;	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        if (r2 == 0) goto L_0x03a4;
    L_0x0395:
        r4 = "externalAbort";
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        r3 = "abort requested";
        r2.<init>(r3);	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
        throw r2;	 Catch:{ IOException -> 0x0337, RuntimeException -> 0x039f }
    L_0x039f:
        r2 = move-exception;
        r3 = r9;
        r5 = r11;
        goto L_0x015f;
    L_0x03a4:
        r2 = r19.tryAcquire();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        if (r2 == 0) goto L_0x0304;
    L_0x03aa:
        r4 = r12.getAbsolutePath();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r7 = 0;
        r2 = r26;
        r3 = r27;
        r2.zza(r3, r4, r5, r6, r7);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        goto L_0x0304;
    L_0x03b8:
        r2 = move-exception;
        r3 = r9;
        r4 = r10;
        r5 = r11;
        goto L_0x015f;
    L_0x03be:
        r11.close();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = 3;
        r2 = com.google.android.gms.ads.internal.util.client.zzb.zzaz(r2);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        if (r2 == 0) goto L_0x0404;
    L_0x03c8:
        r2 = zzbjq;	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r6 = (long) r5;	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = r2.format(r6);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r4 = java.lang.String.valueOf(r2);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r4 = r4.length();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r4 = r4 + 22;
        r6 = java.lang.String.valueOf(r27);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r6 = r6.length();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r4 = r4 + r6;
        r3.<init>(r4);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r4 = "Preloaded ";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = r3.append(r2);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r3 = " bytes from ";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r0 = r27;
        r2 = r2.append(r0);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = r2.toString();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        com.google.android.gms.ads.internal.util.client.zzb.zzcv(r2);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
    L_0x0404:
        r2 = 1;
        r3 = 0;
        r12.setReadable(r2, r3);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        zzc(r13);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = r12.getAbsolutePath();	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r0 = r26;
        r1 = r27;
        r0.zza(r1, r2, r5);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = zzbjp;	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2.remove(r8);	 Catch:{ IOException -> 0x03b8, RuntimeException -> 0x045d }
        r2 = 1;
        goto L_0x0012;
    L_0x041f:
        r5 = new java.lang.StringBuilder;
        r6 = java.lang.String.valueOf(r27);
        r6 = r6.length();
        r6 = r6 + 25;
        r5.<init>(r6);
        r6 = "Preload failed for URL \"";
        r5 = r5.append(r6);
        r0 = r27;
        r5 = r5.append(r0);
        r6 = "\"";
        r5 = r5.append(r6);
        r5 = r5.toString();
        com.google.android.gms.ads.internal.util.client.zzb.zzd(r5, r2);
        goto L_0x019c;
    L_0x0449:
        r2 = new java.lang.String;
        r2.<init>(r5);
        goto L_0x01bc;
    L_0x0450:
        r5 = move-exception;
        goto L_0x016e;
    L_0x0453:
        r5 = move-exception;
        goto L_0x016e;
    L_0x0456:
        r2 = move-exception;
        r3 = r9;
        goto L_0x015f;
    L_0x045a:
        r2 = move-exception;
        goto L_0x015f;
    L_0x045d:
        r2 = move-exception;
        r3 = r9;
        r4 = r10;
        r5 = r11;
        goto L_0x015f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfg.zzaz(java.lang.String):boolean");
    }

    public int zzll() {
        int i = 0;
        if (this.zzbjr != null) {
            for (File name : this.zzbjr.listFiles()) {
                if (!name.getName().endsWith(".done")) {
                    i++;
                }
            }
        }
        return i;
    }

    public boolean zzlm() {
        if (this.zzbjr == null) {
            return false;
        }
        boolean delete;
        File file = null;
        long j = Long.MAX_VALUE;
        File[] listFiles = this.zzbjr.listFiles();
        int length = listFiles.length;
        int i = 0;
        while (i < length) {
            long lastModified;
            File file2;
            File file3 = listFiles[i];
            if (!file3.getName().endsWith(".done")) {
                lastModified = file3.lastModified();
                if (lastModified < j) {
                    file2 = file3;
                    i++;
                    file = file2;
                    j = lastModified;
                }
            }
            lastModified = j;
            file2 = file;
            i++;
            file = file2;
            j = lastModified;
        }
        if (file != null) {
            delete = file.delete();
            File zzb = zzb(file);
            if (zzb.isFile()) {
                delete &= zzb.delete();
            }
        } else {
            delete = false;
        }
        return delete;
    }
}
