package com.google.android.gms.analytics.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.google.android.gms.common.internal.zzab;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.zip.GZIPOutputStream;

class zzah extends zzd {
    private static final byte[] ad = "\n".getBytes();
    private final zzal ac;
    private final String zzbjf = zza("GoogleAnalytics", zze.VERSION, VERSION.RELEASE, zzao.zza(Locale.getDefault()), Build.MODEL, Build.ID);

    private class zza {
        private int ae;
        private ByteArrayOutputStream af = new ByteArrayOutputStream();
        final /* synthetic */ zzah ag;

        public zza(zzah com_google_android_gms_analytics_internal_zzah) {
            this.ag = com_google_android_gms_analytics_internal_zzah;
        }

        public byte[] getPayload() {
            return this.af.toByteArray();
        }

        public int zzadm() {
            return this.ae;
        }

        public boolean zzj(zzab com_google_android_gms_analytics_internal_zzab) {
            zzab.zzy(com_google_android_gms_analytics_internal_zzab);
            if (this.ae + 1 > this.ag.zzyy().zzabo()) {
                return false;
            }
            String zza = this.ag.zza(com_google_android_gms_analytics_internal_zzab, false);
            if (zza == null) {
                this.ag.zzyx().zza(com_google_android_gms_analytics_internal_zzab, "Error formatting hit");
                return true;
            }
            byte[] bytes = zza.getBytes();
            int length = bytes.length;
            if (length > this.ag.zzyy().zzabg()) {
                this.ag.zzyx().zza(com_google_android_gms_analytics_internal_zzab, "Hit size exceeds the maximum size limit");
                return true;
            }
            if (this.af.size() > 0) {
                length++;
            }
            if (length + this.af.size() > this.ag.zzyy().zzabi()) {
                return false;
            }
            try {
                if (this.af.size() > 0) {
                    this.af.write(zzah.ad);
                }
                this.af.write(bytes);
                this.ae++;
                return true;
            } catch (IOException e) {
                this.ag.zze("Failed to write payload when batching hits", e);
                return true;
            }
        }
    }

    zzah(zzf com_google_android_gms_analytics_internal_zzf) {
        super(com_google_android_gms_analytics_internal_zzf);
        this.ac = new zzal(com_google_android_gms_analytics_internal_zzf.zzyw());
    }

    private int zza(URL url, byte[] bArr) {
        Object e;
        Throwable th;
        OutputStream outputStream = null;
        zzab.zzy(url);
        zzab.zzy(bArr);
        zzb("POST bytes, url", Integer.valueOf(bArr.length), url);
        if (zztb()) {
            zza("Post payload\n", new String(bArr));
        }
        HttpURLConnection zzc;
        try {
            zzet(getContext().getPackageName());
            zzc = zzc(url);
            try {
                zzc.setDoOutput(true);
                zzc.setFixedLengthStreamingMode(bArr.length);
                zzc.connect();
                outputStream = zzc.getOutputStream();
                outputStream.write(bArr);
                zzb(zzc);
                int responseCode = zzc.getResponseCode();
                if (responseCode == 200) {
                    zzwd().zzyt();
                }
                zzb("POST status", Integer.valueOf(responseCode));
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e2) {
                        zze("Error closing http post connection output stream", e2);
                    }
                }
                if (zzc != null) {
                    zzc.disconnect();
                }
                zzrp();
                return responseCode;
            } catch (IOException e3) {
                e = e3;
                try {
                    zzd("Network POST connection error", e);
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e4) {
                            zze("Error closing http post connection output stream", e4);
                        }
                    }
                    if (zzc != null) {
                        zzc.disconnect();
                    }
                    zzrp();
                    return 0;
                } catch (Throwable th2) {
                    th = th2;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e22) {
                            zze("Error closing http post connection output stream", e22);
                        }
                    }
                    if (zzc != null) {
                        zzc.disconnect();
                    }
                    zzrp();
                    throw th;
                }
            }
        } catch (IOException e5) {
            e = e5;
            zzc = outputStream;
            zzd("Network POST connection error", e);
            if (outputStream != null) {
                outputStream.close();
            }
            if (zzc != null) {
                zzc.disconnect();
            }
            zzrp();
            return 0;
        } catch (Throwable th3) {
            th = th3;
            zzc = outputStream;
            if (outputStream != null) {
                outputStream.close();
            }
            if (zzc != null) {
                zzc.disconnect();
            }
            zzrp();
            throw th;
        }
    }

    private static String zza(String str, String str2, String str3, String str4, String str5, String str6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{str, str2, str3, str4, str5, str6});
    }

    private void zza(StringBuilder stringBuilder, String str, String str2) throws UnsupportedEncodingException {
        if (stringBuilder.length() != 0) {
            stringBuilder.append('&');
        }
        stringBuilder.append(URLEncoder.encode(str, "UTF-8"));
        stringBuilder.append('=');
        stringBuilder.append(URLEncoder.encode(str2, "UTF-8"));
    }

    private URL zzadk() {
        String valueOf = String.valueOf(zzyy().zzabq());
        String valueOf2 = String.valueOf(zzyy().zzabt());
        try {
            return new URL(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private int zzb(java.net.URL r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0030 in list [B:7:0x002d]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r4 = this;
        com.google.android.gms.common.internal.zzab.zzy(r5);
        r0 = "GET request";
        r4.zzb(r0, r5);
        r1 = 0;
        r1 = r4.zzc(r5);	 Catch:{ IOException -> 0x0031, all -> 0x003e }
        r1.connect();	 Catch:{ IOException -> 0x0031, all -> 0x003e }
        r4.zzb(r1);	 Catch:{ IOException -> 0x0031, all -> 0x003e }
        r0 = r1.getResponseCode();	 Catch:{ IOException -> 0x0031, all -> 0x003e }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ IOException -> 0x0031, all -> 0x003e }
        if (r0 != r2) goto L_0x0022;	 Catch:{ IOException -> 0x0031, all -> 0x003e }
    L_0x001b:
        r2 = r4.zzwd();	 Catch:{ IOException -> 0x0031, all -> 0x003e }
        r2.zzyt();	 Catch:{ IOException -> 0x0031, all -> 0x003e }
    L_0x0022:
        r2 = "GET status";	 Catch:{ IOException -> 0x0031, all -> 0x003e }
        r3 = java.lang.Integer.valueOf(r0);	 Catch:{ IOException -> 0x0031, all -> 0x003e }
        r4.zzb(r2, r3);	 Catch:{ IOException -> 0x0031, all -> 0x003e }
        if (r1 == 0) goto L_0x0030;
    L_0x002d:
        r1.disconnect();
    L_0x0030:
        return r0;
    L_0x0031:
        r0 = move-exception;
        r2 = "Network GET connection error";	 Catch:{ IOException -> 0x0031, all -> 0x003e }
        r4.zzd(r2, r0);	 Catch:{ IOException -> 0x0031, all -> 0x003e }
        if (r1 == 0) goto L_0x003c;
    L_0x0039:
        r1.disconnect();
    L_0x003c:
        r0 = 0;
        goto L_0x0030;
    L_0x003e:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0044;
    L_0x0041:
        r1.disconnect();
    L_0x0044:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzah.zzb(java.net.URL):int");
    }

    private int zzb(URL url, byte[] bArr) {
        HttpURLConnection zzc;
        OutputStream outputStream;
        Object e;
        HttpURLConnection httpURLConnection;
        Throwable th;
        OutputStream outputStream2 = null;
        zzab.zzy(url);
        zzab.zzy(bArr);
        try {
            zzet(getContext().getPackageName());
            byte[] zzj = zzj(bArr);
            zza("POST compressed size, ratio %, url", Integer.valueOf(zzj.length), Long.valueOf((100 * ((long) zzj.length)) / ((long) bArr.length)), url);
            if (zzj.length > bArr.length) {
                zzc("Compressed payload is larger then uncompressed. compressed, uncompressed", Integer.valueOf(zzj.length), Integer.valueOf(bArr.length));
            }
            if (zztb()) {
                String str = "Post payload";
                String str2 = "\n";
                String valueOf = String.valueOf(new String(bArr));
                zza(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            zzc = zzc(url);
            try {
                zzc.setDoOutput(true);
                zzc.addRequestProperty("Content-Encoding", HttpRequest.ENCODING_GZIP);
                zzc.setFixedLengthStreamingMode(zzj.length);
                zzc.connect();
                outputStream = zzc.getOutputStream();
            } catch (IOException e2) {
                e = e2;
                httpURLConnection = zzc;
                try {
                    zzd("Network compressed POST connection error", e);
                    if (outputStream2 != null) {
                        try {
                            outputStream2.close();
                        } catch (IOException e3) {
                            zze("Error closing http compressed post connection output stream", e3);
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    zzrp();
                    return 0;
                } catch (Throwable th2) {
                    th = th2;
                    zzc = httpURLConnection;
                    if (outputStream2 != null) {
                        try {
                            outputStream2.close();
                        } catch (IOException e4) {
                            zze("Error closing http compressed post connection output stream", e4);
                        }
                    }
                    if (zzc != null) {
                        zzc.disconnect();
                    }
                    zzrp();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (outputStream2 != null) {
                    outputStream2.close();
                }
                if (zzc != null) {
                    zzc.disconnect();
                }
                zzrp();
                throw th;
            }
            try {
                outputStream.write(zzj);
                outputStream.close();
                zzb(zzc);
                int responseCode = zzc.getResponseCode();
                if (responseCode == 200) {
                    zzwd().zzyt();
                }
                zzb("POST status", Integer.valueOf(responseCode));
                if (zzc != null) {
                    zzc.disconnect();
                }
                zzrp();
                return responseCode;
            } catch (IOException e5) {
                e = e5;
                outputStream2 = outputStream;
                httpURLConnection = zzc;
                zzd("Network compressed POST connection error", e);
                if (outputStream2 != null) {
                    outputStream2.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                zzrp();
                return 0;
            } catch (Throwable th4) {
                th = th4;
                outputStream2 = outputStream;
                if (outputStream2 != null) {
                    outputStream2.close();
                }
                if (zzc != null) {
                    zzc.disconnect();
                }
                zzrp();
                throw th;
            }
        } catch (IOException e6) {
            e = e6;
            httpURLConnection = null;
            zzd("Network compressed POST connection error", e);
            if (outputStream2 != null) {
                outputStream2.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            zzrp();
            return 0;
        } catch (Throwable th5) {
            th = th5;
            zzc = null;
            if (outputStream2 != null) {
                outputStream2.close();
            }
            if (zzc != null) {
                zzc.disconnect();
            }
            zzrp();
            throw th;
        }
    }

    private URL zzb(zzab com_google_android_gms_analytics_internal_zzab, String str) {
        String valueOf;
        String valueOf2;
        if (com_google_android_gms_analytics_internal_zzab.zzadb()) {
            valueOf2 = String.valueOf(zzyy().zzabq());
            valueOf = String.valueOf(zzyy().zzabs());
            valueOf = new StringBuilder(((String.valueOf(valueOf2).length() + 1) + String.valueOf(valueOf).length()) + String.valueOf(str).length()).append(valueOf2).append(valueOf).append("?").append(str).toString();
        } else {
            valueOf2 = String.valueOf(zzyy().zzabr());
            valueOf = String.valueOf(zzyy().zzabs());
            valueOf = new StringBuilder(((String.valueOf(valueOf2).length() + 1) + String.valueOf(valueOf).length()) + String.valueOf(str).length()).append(valueOf2).append(valueOf).append("?").append(str).toString();
        }
        try {
            return new URL(valueOf);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private void zzb(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = httpURLConnection.getInputStream();
            do {
            } while (inputStream.read(new byte[1024]) > 0);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    zze("Error closing http connection input stream", e);
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    zze("Error closing http connection input stream", e2);
                }
            }
        }
    }

    private boolean zzg(zzab com_google_android_gms_analytics_internal_zzab) {
        zzab.zzy(com_google_android_gms_analytics_internal_zzab);
        String zza = zza(com_google_android_gms_analytics_internal_zzab, !com_google_android_gms_analytics_internal_zzab.zzadb());
        if (zza == null) {
            zzyx().zza(com_google_android_gms_analytics_internal_zzab, "Error formatting hit for upload");
            return true;
        } else if (zza.length() <= zzyy().zzabf()) {
            URL zzb = zzb(com_google_android_gms_analytics_internal_zzab, zza);
            if (zzb != null) {
                return zzb(zzb) == 200;
            } else {
                zzel("Failed to build collect GET endpoint url");
                return false;
            }
        } else {
            zza = zza(com_google_android_gms_analytics_internal_zzab, false);
            if (zza == null) {
                zzyx().zza(com_google_android_gms_analytics_internal_zzab, "Error formatting hit for POST upload");
                return true;
            }
            byte[] bytes = zza.getBytes();
            if (bytes.length > zzyy().zzabh()) {
                zzyx().zza(com_google_android_gms_analytics_internal_zzab, "Hit payload exceeds size limit");
                return true;
            }
            URL zzh = zzh(com_google_android_gms_analytics_internal_zzab);
            if (zzh != null) {
                return zza(zzh, bytes) == 200;
            } else {
                zzel("Failed to build collect POST endpoint url");
                return false;
            }
        }
    }

    private URL zzh(zzab com_google_android_gms_analytics_internal_zzab) {
        String valueOf;
        String valueOf2;
        if (com_google_android_gms_analytics_internal_zzab.zzadb()) {
            valueOf = String.valueOf(zzyy().zzabq());
            valueOf2 = String.valueOf(zzyy().zzabs());
            valueOf = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        } else {
            valueOf = String.valueOf(zzyy().zzabr());
            valueOf2 = String.valueOf(zzyy().zzabs());
            valueOf = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        try {
            return new URL(valueOf);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private String zzi(zzab com_google_android_gms_analytics_internal_zzab) {
        return String.valueOf(com_google_android_gms_analytics_internal_zzab.zzacy());
    }

    private static byte[] zzj(byte[] bArr) throws IOException {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    String zza(zzab com_google_android_gms_analytics_internal_zzab, boolean z) {
        zzab.zzy(com_google_android_gms_analytics_internal_zzab);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : com_google_android_gms_analytics_internal_zzab.zzm().entrySet()) {
                String str = (String) entry.getKey();
                if (!("ht".equals(str) || "qt".equals(str) || "AppUID".equals(str) || "z".equals(str) || "_gmsv".equals(str))) {
                    zza(stringBuilder, str, (String) entry.getValue());
                }
            }
            zza(stringBuilder, "ht", String.valueOf(com_google_android_gms_analytics_internal_zzab.zzacz()));
            zza(stringBuilder, "qt", String.valueOf(zzyw().currentTimeMillis() - com_google_android_gms_analytics_internal_zzab.zzacz()));
            if (zzyy().zzabc()) {
                zza(stringBuilder, "_gmsv", zze.VERSION);
            }
            if (z) {
                long zzadc = com_google_android_gms_analytics_internal_zzab.zzadc();
                zza(stringBuilder, "z", zzadc != 0 ? String.valueOf(zzadc) : zzi(com_google_android_gms_analytics_internal_zzab));
            }
            return stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            zze("Failed to encode name or value", e);
            return null;
        }
    }

    List<Long> zza(List<zzab> list, boolean z) {
        zzab.zzbo(!list.isEmpty());
        zza("Uploading batched hits. compression, count", Boolean.valueOf(z), Integer.valueOf(list.size()));
        zza com_google_android_gms_analytics_internal_zzah_zza = new zza(this);
        List<Long> arrayList = new ArrayList();
        for (zzab com_google_android_gms_analytics_internal_zzab : list) {
            if (!com_google_android_gms_analytics_internal_zzah_zza.zzj(com_google_android_gms_analytics_internal_zzab)) {
                break;
            }
            arrayList.add(Long.valueOf(com_google_android_gms_analytics_internal_zzab.zzacy()));
        }
        if (com_google_android_gms_analytics_internal_zzah_zza.zzadm() == 0) {
            return arrayList;
        }
        URL zzadk = zzadk();
        if (zzadk == null) {
            zzel("Failed to build batching endpoint url");
            return Collections.emptyList();
        }
        int zzb = z ? zzb(zzadk, com_google_android_gms_analytics_internal_zzah_zza.getPayload()) : zza(zzadk, com_google_android_gms_analytics_internal_zzah_zza.getPayload());
        if (200 == zzb) {
            zza("Batched upload completed. Hits batched", Integer.valueOf(com_google_android_gms_analytics_internal_zzah_zza.zzadm()));
            return arrayList;
        }
        zza("Network error uploading hits. status code", Integer.valueOf(zzb));
        if (zzyy().zzabw().contains(Integer.valueOf(zzb))) {
            zzek("Server instructed the client to stop batching");
            this.ac.start();
        }
        return Collections.emptyList();
    }

    public boolean zzadj() {
        NetworkInfo activeNetworkInfo;
        zzwu();
        zzzg();
        try {
            activeNetworkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException e) {
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzeh("No network connectivity");
        return false;
    }

    HttpURLConnection zzc(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if (openConnection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setDefaultUseCaches(false);
            httpURLConnection.setConnectTimeout(zzyy().zzacf());
            httpURLConnection.setReadTimeout(zzyy().zzacg());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestProperty("User-Agent", this.zzbjf);
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        }
        throw new IOException("Failed to obtain http connection");
    }

    protected void zzet(String str) {
    }

    protected void zzrp() {
    }

    public List<Long> zzs(List<zzab> list) {
        boolean z;
        boolean z2 = true;
        zzwu();
        zzzg();
        zzab.zzy(list);
        if (zzyy().zzabw().isEmpty() || !this.ac.zzx(zzyy().zzabp() * 1000)) {
            z2 = false;
            z = false;
        } else {
            z = zzyy().zzabu() != zzm.zzcyn;
            if (zzyy().zzabv() != zzo.GZIP) {
                z2 = false;
            }
        }
        return z ? zza((List) list, z2) : zzt(list);
    }

    List<Long> zzt(List<zzab> list) {
        List<Long> arrayList = new ArrayList(list.size());
        for (zzab com_google_android_gms_analytics_internal_zzab : list) {
            if (!zzg(com_google_android_gms_analytics_internal_zzab)) {
                break;
            }
            arrayList.add(Long.valueOf(com_google_android_gms_analytics_internal_zzab.zzacy()));
            if (arrayList.size() >= zzyy().zzabn()) {
                break;
            }
        }
        return arrayList;
    }

    protected void zzwv() {
        zza("Network initialized. User agent", this.zzbjf);
    }
}
