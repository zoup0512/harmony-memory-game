package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class zzq extends zzaa {

    @WorkerThread
    interface zza {
        void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map);
    }

    @WorkerThread
    private static class zzb implements Runnable {
        private final String aM;
        private final zza ajD;
        private final Throwable ajE;
        private final byte[] ajF;
        private final Map<String, List<String>> ajG;
        private final int zzblv;

        private zzb(String str, zza com_google_android_gms_measurement_internal_zzq_zza, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
            zzab.zzy(com_google_android_gms_measurement_internal_zzq_zza);
            this.ajD = com_google_android_gms_measurement_internal_zzq_zza;
            this.zzblv = i;
            this.ajE = th;
            this.ajF = bArr;
            this.aM = str;
            this.ajG = map;
        }

        public void run() {
            this.ajD.zza(this.aM, this.zzblv, this.ajE, this.ajF, this.ajG);
        }
    }

    @WorkerThread
    private class zzc implements Runnable {
        private final String aM;
        private final byte[] ajH;
        private final zza ajI;
        private final Map<String, String> ajJ;
        final /* synthetic */ zzq ajK;
        private final URL zzbij;

        public zzc(zzq com_google_android_gms_measurement_internal_zzq, String str, URL url, byte[] bArr, Map<String, String> map, zza com_google_android_gms_measurement_internal_zzq_zza) {
            this.ajK = com_google_android_gms_measurement_internal_zzq;
            zzab.zzhr(str);
            zzab.zzy(url);
            zzab.zzy(com_google_android_gms_measurement_internal_zzq_zza);
            this.zzbij = url;
            this.ajH = bArr;
            this.ajI = com_google_android_gms_measurement_internal_zzq_zza;
            this.aM = str;
            this.ajJ = map;
        }

        public void run() {
            HttpURLConnection zzc;
            OutputStream outputStream;
            Throwable e;
            Map map;
            int i;
            HttpURLConnection httpURLConnection;
            Throwable th;
            Map map2;
            this.ajK.zzbrs();
            int i2 = 0;
            try {
                this.ajK.zzet(this.aM);
                zzc = this.ajK.zzc(this.zzbij);
                try {
                    if (this.ajJ != null) {
                        for (Entry entry : this.ajJ.entrySet()) {
                            zzc.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                        }
                    }
                    if (this.ajH != null) {
                        byte[] zzj = this.ajK.zzbrz().zzj(this.ajH);
                        this.ajK.zzbsd().zzbtc().zzj("Uploading data. size", Integer.valueOf(zzj.length));
                        zzc.setDoOutput(true);
                        zzc.addRequestProperty("Content-Encoding", HttpRequest.ENCODING_GZIP);
                        zzc.setFixedLengthStreamingMode(zzj.length);
                        zzc.connect();
                        outputStream = zzc.getOutputStream();
                        try {
                            outputStream.write(zzj);
                            outputStream.close();
                        } catch (IOException e2) {
                            e = e2;
                            map = null;
                            i = 0;
                            httpURLConnection = zzc;
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e3) {
                                    this.ajK.zzbsd().zzbsv().zzj("Error closing HTTP compressed POST connection output stream", e3);
                                }
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            this.ajK.zzrp();
                            this.ajK.zzbsc().zzm(new zzb(this.aM, this.ajI, i, e, null, map));
                        } catch (Throwable th2) {
                            th = th2;
                            map2 = null;
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e32) {
                                    this.ajK.zzbsd().zzbsv().zzj("Error closing HTTP compressed POST connection output stream", e32);
                                }
                            }
                            if (zzc != null) {
                                zzc.disconnect();
                            }
                            this.ajK.zzrp();
                            this.ajK.zzbsc().zzm(new zzb(this.aM, this.ajI, i2, null, null, map2));
                            throw th;
                        }
                    }
                    i2 = zzc.getResponseCode();
                    map2 = zzc.getHeaderFields();
                } catch (IOException e4) {
                    e = e4;
                    map = null;
                    i = i2;
                    outputStream = null;
                    httpURLConnection = zzc;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    this.ajK.zzrp();
                    this.ajK.zzbsc().zzm(new zzb(this.aM, this.ajI, i, e, null, map));
                } catch (Throwable th3) {
                    th = th3;
                    map2 = null;
                    outputStream = null;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (zzc != null) {
                        zzc.disconnect();
                    }
                    this.ajK.zzrp();
                    this.ajK.zzbsc().zzm(new zzb(this.aM, this.ajI, i2, null, null, map2));
                    throw th;
                }
                try {
                    byte[] zza = this.ajK.zzc(zzc);
                    if (zzc != null) {
                        zzc.disconnect();
                    }
                    this.ajK.zzrp();
                    this.ajK.zzbsc().zzm(new zzb(this.aM, this.ajI, i2, null, zza, map2));
                } catch (IOException e5) {
                    e = e5;
                    map = map2;
                    i = i2;
                    outputStream = null;
                    httpURLConnection = zzc;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    this.ajK.zzrp();
                    this.ajK.zzbsc().zzm(new zzb(this.aM, this.ajI, i, e, null, map));
                } catch (Throwable th32) {
                    th = th32;
                    outputStream = null;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (zzc != null) {
                        zzc.disconnect();
                    }
                    this.ajK.zzrp();
                    this.ajK.zzbsc().zzm(new zzb(this.aM, this.ajI, i2, null, null, map2));
                    throw th;
                }
            } catch (IOException e6) {
                e = e6;
                map = null;
                i = 0;
                outputStream = null;
                httpURLConnection = null;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                this.ajK.zzrp();
                this.ajK.zzbsc().zzm(new zzb(this.aM, this.ajI, i, e, null, map));
            } catch (Throwable th322) {
                th = th322;
                map2 = null;
                zzc = null;
                outputStream = null;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (zzc != null) {
                    zzc.disconnect();
                }
                this.ajK.zzrp();
                this.ajK.zzbsc().zzm(new zzb(this.aM, this.ajI, i2, null, null, map2));
                throw th;
            }
        }
    }

    public zzq(zzx com_google_android_gms_measurement_internal_zzx) {
        super(com_google_android_gms_measurement_internal_zzx);
    }

    @WorkerThread
    private byte[] zzc(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            inputStream = httpURLConnection.getInputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            return toByteArray;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public void zza(String str, URL url, Map<String, String> map, zza com_google_android_gms_measurement_internal_zzq_zza) {
        zzwu();
        zzzg();
        zzab.zzy(url);
        zzab.zzy(com_google_android_gms_measurement_internal_zzq_zza);
        zzbsc().zzn(new zzc(this, str, url, null, map, com_google_android_gms_measurement_internal_zzq_zza));
    }

    @WorkerThread
    public void zza(String str, URL url, byte[] bArr, Map<String, String> map, zza com_google_android_gms_measurement_internal_zzq_zza) {
        zzwu();
        zzzg();
        zzab.zzy(url);
        zzab.zzy(bArr);
        zzab.zzy(com_google_android_gms_measurement_internal_zzq_zza);
        zzbsc().zzn(new zzc(this, str, url, bArr, map, com_google_android_gms_measurement_internal_zzq_zza));
    }

    public boolean zzadj() {
        NetworkInfo activeNetworkInfo;
        zzzg();
        try {
            activeNetworkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException e) {
            activeNetworkInfo = null;
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
    protected HttpURLConnection zzc(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if (openConnection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setDefaultUseCaches(false);
            httpURLConnection.setConnectTimeout((int) zzbsf().zzbrb());
            httpURLConnection.setReadTimeout((int) zzbsf().zzbrc());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        }
        throw new IOException("Failed to obtain HTTP connection");
    }

    protected void zzet(String str) {
    }

    protected void zzrp() {
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
