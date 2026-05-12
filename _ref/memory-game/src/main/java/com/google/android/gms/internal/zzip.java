package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.zzk.zza;
import com.google.android.gms.ads.internal.request.zzl;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzfs.zzb;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
public final class zzip extends zza {
    private static final Object zzamr = new Object();
    private static zzip zzcdx;
    private final Context mContext;
    private final zzio zzcdy;
    private final zzcv zzcdz;
    private final zzfs zzcea;

    zzip(Context context, zzcv com_google_android_gms_internal_zzcv, zzio com_google_android_gms_internal_zzio) {
        this.mContext = context;
        this.zzcdy = com_google_android_gms_internal_zzio;
        this.zzcdz = com_google_android_gms_internal_zzcv;
        this.zzcea = new zzfs(context.getApplicationContext() != null ? context.getApplicationContext() : context, new VersionInfoParcel(9452208, 9452208, true), com_google_android_gms_internal_zzcv.zzjv(), new 4(this), new zzb());
    }

    private static AdResponseParcel zza(Context context, zzfs com_google_android_gms_internal_zzfs, zzcv com_google_android_gms_internal_zzcv, zzio com_google_android_gms_internal_zzio, AdRequestInfoParcel adRequestInfoParcel) {
        Bundle bundle;
        Future future;
        Throwable e;
        AdResponseParcel adResponseParcel;
        com.google.android.gms.ads.internal.util.client.zzb.zzcv("Starting ad request from service using: AFMA_getAd");
        zzdc.initialize(context);
        zzdk com_google_android_gms_internal_zzdk = new zzdk(((Boolean) zzdc.zzaze.get()).booleanValue(), "load_ad", adRequestInfoParcel.zzapa.zzaur);
        if (adRequestInfoParcel.versionCode > 10 && adRequestInfoParcel.zzcbj != -1) {
            com_google_android_gms_internal_zzdk.zza(com_google_android_gms_internal_zzdk.zzc(adRequestInfoParcel.zzcbj), "cts");
        }
        zzdi zzkg = com_google_android_gms_internal_zzdk.zzkg();
        Bundle bundle2 = (adRequestInfoParcel.versionCode < 4 || adRequestInfoParcel.zzcay == null) ? null : adRequestInfoParcel.zzcay;
        if (!((Boolean) zzdc.zzazn.get()).booleanValue() || com_google_android_gms_internal_zzio.zzcdw == null) {
            bundle = bundle2;
            future = null;
        } else {
            if (bundle2 == null && ((Boolean) zzdc.zzazo.get()).booleanValue()) {
                zzkd.v("contentInfo is not present, but we'll still launch the app index task");
                bundle2 = new Bundle();
            }
            if (bundle2 != null) {
                bundle = bundle2;
                future = zzkg.zza(new 1(com_google_android_gms_internal_zzio, context, adRequestInfoParcel, bundle2));
            } else {
                bundle = bundle2;
                future = null;
            }
        }
        zzkw com_google_android_gms_internal_zzkw = new zzkw(null);
        Bundle bundle3 = adRequestInfoParcel.zzcar.extras;
        Object obj = (bundle3 == null || bundle3.getString("_ad") == null) ? null : 1;
        if (adRequestInfoParcel.zzcbq && obj == null) {
            zzky zza = com_google_android_gms_internal_zzio.zzcds.zza(adRequestInfoParcel.applicationInfo);
        } else {
            Object obj2 = com_google_android_gms_internal_zzkw;
        }
        zziv zzy = zzu.zzfw().zzy(context);
        if (zzy.zzcgp == -1) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcv("Device is offline.");
            return new AdResponseParcel(2);
        }
        String uuid = adRequestInfoParcel.versionCode >= 7 ? adRequestInfoParcel.zzcbg : UUID.randomUUID().toString();
        zzir com_google_android_gms_internal_zzir = new zzir(uuid, adRequestInfoParcel.applicationInfo.packageName);
        if (adRequestInfoParcel.zzcar.extras != null) {
            String string = adRequestInfoParcel.zzcar.extras.getString("_ad");
            if (string != null) {
                return zziq.zza(context, adRequestInfoParcel, string);
            }
        }
        List zza2 = com_google_android_gms_internal_zzio.zzcdq.zza(adRequestInfoParcel);
        String zzf = com_google_android_gms_internal_zzio.zzcdt.zzf(adRequestInfoParcel);
        zziz.zza zzz = com_google_android_gms_internal_zzio.zzcdu.zzz(context);
        if (future != null) {
            try {
                zzkd.v("Waiting for app index fetching task.");
                future.get(((Long) zzdc.zzazp.get()).longValue(), TimeUnit.MILLISECONDS);
                zzkd.v("App index fetching task completed.");
            } catch (ExecutionException e2) {
                e = e2;
                com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to fetch app index signal", e);
            } catch (InterruptedException e3) {
                e = e3;
                com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to fetch app index signal", e);
            } catch (TimeoutException e4) {
                com.google.android.gms.ads.internal.util.client.zzb.zzcv("Timed out waiting for app index fetching task");
            }
        }
        String zzck = com_google_android_gms_internal_zzio.zzcdp.zzck(adRequestInfoParcel.zzcas.packageName);
        JSONObject zza3 = zziq.zza(context, adRequestInfoParcel, zzy, zzz, zzb(zza), com_google_android_gms_internal_zzcv, zzf, zza2, bundle, zzck);
        if (zza3 == null) {
            return new AdResponseParcel(0);
        }
        if (adRequestInfoParcel.versionCode < 7) {
            try {
                zza3.put("request_id", uuid);
            } catch (JSONException e5) {
            }
        }
        try {
            zza3.put("prefetch_mode", "url");
        } catch (Throwable e6) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed putting prefetch parameters to ad request.", e6);
        }
        String jSONObject = zza3.toString();
        com_google_android_gms_internal_zzdk.zza(zzkg, "arc");
        zzkh.zzclc.post(new 2(com_google_android_gms_internal_zzfs, com_google_android_gms_internal_zzir, com_google_android_gms_internal_zzdk, com_google_android_gms_internal_zzdk.zzkg(), jSONObject));
        try {
            zziu com_google_android_gms_internal_zziu = (zziu) com_google_android_gms_internal_zzir.zzrh().get(10, TimeUnit.SECONDS);
            if (com_google_android_gms_internal_zziu == null) {
                adResponseParcel = new AdResponseParcel(0);
                return adResponseParcel;
            } else if (com_google_android_gms_internal_zziu.getErrorCode() != -2) {
                adResponseParcel = new AdResponseParcel(com_google_android_gms_internal_zziu.getErrorCode());
                zzkh.zzclc.post(new 3(com_google_android_gms_internal_zzio, context, com_google_android_gms_internal_zzir, adRequestInfoParcel));
                return adResponseParcel;
            } else {
                if (com_google_android_gms_internal_zzdk.zzkj() != null) {
                    com_google_android_gms_internal_zzdk.zza(com_google_android_gms_internal_zzdk.zzkj(), "rur");
                }
                adResponseParcel = null;
                if (!TextUtils.isEmpty(com_google_android_gms_internal_zziu.zzrm())) {
                    adResponseParcel = zziq.zza(context, adRequestInfoParcel, com_google_android_gms_internal_zziu.zzrm());
                }
                if (adResponseParcel == null && !TextUtils.isEmpty(com_google_android_gms_internal_zziu.getUrl())) {
                    adResponseParcel = zza(adRequestInfoParcel, context, adRequestInfoParcel.zzaow.zzcs, com_google_android_gms_internal_zziu.getUrl(), zzck, com_google_android_gms_internal_zziu, com_google_android_gms_internal_zzdk, com_google_android_gms_internal_zzio);
                }
                if (adResponseParcel == null) {
                    adResponseParcel = new AdResponseParcel(0);
                }
                com_google_android_gms_internal_zzdk.zza(zzkg, "tts");
                adResponseParcel.zzccl = com_google_android_gms_internal_zzdk.zzki();
                zzkh.zzclc.post(new 3(com_google_android_gms_internal_zzio, context, com_google_android_gms_internal_zzir, adRequestInfoParcel));
                return adResponseParcel;
            }
        } catch (Exception e7) {
            adResponseParcel = new AdResponseParcel(0);
            return adResponseParcel;
        } finally {
            zzkh.zzclc.post(new 3(com_google_android_gms_internal_zzio, context, com_google_android_gms_internal_zzir, adRequestInfoParcel));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.ads.internal.request.AdResponseParcel zza(com.google.android.gms.ads.internal.request.AdRequestInfoParcel r13, android.content.Context r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, com.google.android.gms.internal.zziu r18, com.google.android.gms.internal.zzdk r19, com.google.android.gms.internal.zzio r20) {
        /*
        if (r19 == 0) goto L_0x00ea;
    L_0x0002:
        r2 = r19.zzkg();
        r3 = r2;
    L_0x0007:
        r8 = new com.google.android.gms.internal.zzis;	 Catch:{ IOException -> 0x00f5 }
        r8.<init>(r13);	 Catch:{ IOException -> 0x00f5 }
        r4 = "AdRequestServiceImpl: Sending request: ";
        r2 = java.lang.String.valueOf(r16);	 Catch:{ IOException -> 0x00f5 }
        r5 = r2.length();	 Catch:{ IOException -> 0x00f5 }
        if (r5 == 0) goto L_0x00ee;
    L_0x0018:
        r2 = r4.concat(r2);	 Catch:{ IOException -> 0x00f5 }
    L_0x001c:
        com.google.android.gms.ads.internal.util.client.zzb.zzcv(r2);	 Catch:{ IOException -> 0x00f5 }
        r4 = new java.net.URL;	 Catch:{ IOException -> 0x00f5 }
        r0 = r16;
        r4.<init>(r0);	 Catch:{ IOException -> 0x00f5 }
        r2 = 0;
        r5 = com.google.android.gms.ads.internal.zzu.zzfu();	 Catch:{ IOException -> 0x00f5 }
        r10 = r5.elapsedRealtime();	 Catch:{ IOException -> 0x00f5 }
        r6 = r2;
        r7 = r4;
    L_0x0031:
        if (r20 == 0) goto L_0x003a;
    L_0x0033:
        r0 = r20;
        r2 = r0.zzcdv;	 Catch:{ IOException -> 0x00f5 }
        r2.zzro();	 Catch:{ IOException -> 0x00f5 }
    L_0x003a:
        r2 = r7.openConnection();	 Catch:{ IOException -> 0x00f5 }
        r2 = (java.net.HttpURLConnection) r2;	 Catch:{ IOException -> 0x00f5 }
        r4 = com.google.android.gms.ads.internal.zzu.zzfq();	 Catch:{ all -> 0x011a }
        r5 = 0;
        r4.zza(r14, r15, r5, r2);	 Catch:{ all -> 0x011a }
        r4 = android.text.TextUtils.isEmpty(r17);	 Catch:{ all -> 0x011a }
        if (r4 != 0) goto L_0x005c;
    L_0x004e:
        r4 = r18.zzrl();	 Catch:{ all -> 0x011a }
        if (r4 == 0) goto L_0x005c;
    L_0x0054:
        r4 = "x-afma-drt-cookie";
        r0 = r17;
        r2.addRequestProperty(r4, r0);	 Catch:{ all -> 0x011a }
    L_0x005c:
        r4 = r13.zzcbr;	 Catch:{ all -> 0x011a }
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x011a }
        if (r5 != 0) goto L_0x006e;
    L_0x0064:
        r5 = "Sending webview cookie in ad request header.";
        com.google.android.gms.ads.internal.util.client.zzb.zzcv(r5);	 Catch:{ all -> 0x011a }
        r5 = "Cookie";
        r2.addRequestProperty(r5, r4);	 Catch:{ all -> 0x011a }
    L_0x006e:
        if (r18 == 0) goto L_0x009a;
    L_0x0070:
        r4 = r18.zzrk();	 Catch:{ all -> 0x011a }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x011a }
        if (r4 != 0) goto L_0x009a;
    L_0x007a:
        r4 = 1;
        r2.setDoOutput(r4);	 Catch:{ all -> 0x011a }
        r4 = r18.zzrk();	 Catch:{ all -> 0x011a }
        r9 = r4.getBytes();	 Catch:{ all -> 0x011a }
        r4 = r9.length;	 Catch:{ all -> 0x011a }
        r2.setFixedLengthStreamingMode(r4);	 Catch:{ all -> 0x011a }
        r5 = 0;
        r4 = new java.io.BufferedOutputStream;	 Catch:{ all -> 0x0114 }
        r12 = r2.getOutputStream();	 Catch:{ all -> 0x0114 }
        r4.<init>(r12);	 Catch:{ all -> 0x0114 }
        r4.write(r9);	 Catch:{ all -> 0x01d1 }
        com.google.android.gms.common.util.zzo.zzb(r4);	 Catch:{ all -> 0x011a }
    L_0x009a:
        r9 = r2.getResponseCode();	 Catch:{ all -> 0x011a }
        r12 = r2.getHeaderFields();	 Catch:{ all -> 0x011a }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r9 < r4) goto L_0x012e;
    L_0x00a6:
        r4 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r9 >= r4) goto L_0x012e;
    L_0x00aa:
        r6 = r7.toString();	 Catch:{ all -> 0x011a }
        r5 = 0;
        r4 = new java.io.InputStreamReader;	 Catch:{ all -> 0x0128 }
        r7 = r2.getInputStream();	 Catch:{ all -> 0x0128 }
        r4.<init>(r7);	 Catch:{ all -> 0x0128 }
        r5 = com.google.android.gms.ads.internal.zzu.zzfq();	 Catch:{ all -> 0x01ce }
        r5 = r5.zza(r4);	 Catch:{ all -> 0x01ce }
        com.google.android.gms.common.util.zzo.zzb(r4);	 Catch:{ all -> 0x011a }
        zza(r6, r12, r5, r9);	 Catch:{ all -> 0x011a }
        r8.zzb(r6, r12, r5);	 Catch:{ all -> 0x011a }
        if (r19 == 0) goto L_0x00d8;
    L_0x00cb:
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ all -> 0x011a }
        r5 = 0;
        r6 = "ufe";
        r4[r5] = r6;	 Catch:{ all -> 0x011a }
        r0 = r19;
        r0.zza(r3, r4);	 Catch:{ all -> 0x011a }
    L_0x00d8:
        r3 = r8.zzj(r10);	 Catch:{ all -> 0x011a }
        r2.disconnect();	 Catch:{ IOException -> 0x00f5 }
        if (r20 == 0) goto L_0x00e8;
    L_0x00e1:
        r0 = r20;
        r2 = r0.zzcdv;	 Catch:{ IOException -> 0x00f5 }
        r2.zzrp();	 Catch:{ IOException -> 0x00f5 }
    L_0x00e8:
        r2 = r3;
    L_0x00e9:
        return r2;
    L_0x00ea:
        r2 = 0;
        r3 = r2;
        goto L_0x0007;
    L_0x00ee:
        r2 = new java.lang.String;	 Catch:{ IOException -> 0x00f5 }
        r2.<init>(r4);	 Catch:{ IOException -> 0x00f5 }
        goto L_0x001c;
    L_0x00f5:
        r2 = move-exception;
        r3 = "Error while connecting to ad server: ";
        r2 = r2.getMessage();
        r2 = java.lang.String.valueOf(r2);
        r4 = r2.length();
        if (r4 == 0) goto L_0x01c7;
    L_0x0106:
        r2 = r3.concat(r2);
    L_0x010a:
        com.google.android.gms.ads.internal.util.client.zzb.zzcx(r2);
        r2 = new com.google.android.gms.ads.internal.request.AdResponseParcel;
        r3 = 2;
        r2.<init>(r3);
        goto L_0x00e9;
    L_0x0114:
        r3 = move-exception;
        r4 = r5;
    L_0x0116:
        com.google.android.gms.common.util.zzo.zzb(r4);	 Catch:{ all -> 0x011a }
        throw r3;	 Catch:{ all -> 0x011a }
    L_0x011a:
        r3 = move-exception;
        r2.disconnect();	 Catch:{ IOException -> 0x00f5 }
        if (r20 == 0) goto L_0x0127;
    L_0x0120:
        r0 = r20;
        r2 = r0.zzcdv;	 Catch:{ IOException -> 0x00f5 }
        r2.zzrp();	 Catch:{ IOException -> 0x00f5 }
    L_0x0127:
        throw r3;	 Catch:{ IOException -> 0x00f5 }
    L_0x0128:
        r3 = move-exception;
        r4 = r5;
    L_0x012a:
        com.google.android.gms.common.util.zzo.zzb(r4);	 Catch:{ all -> 0x011a }
        throw r3;	 Catch:{ all -> 0x011a }
    L_0x012e:
        r4 = r7.toString();	 Catch:{ all -> 0x011a }
        r5 = 0;
        zza(r4, r12, r5, r9);	 Catch:{ all -> 0x011a }
        r4 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r9 < r4) goto L_0x0187;
    L_0x013a:
        r4 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r9 >= r4) goto L_0x0187;
    L_0x013e:
        r4 = "Location";
        r4 = r2.getHeaderField(r4);	 Catch:{ all -> 0x011a }
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x011a }
        if (r5 == 0) goto L_0x0163;
    L_0x014a:
        r3 = "No location header to follow redirect.";
        com.google.android.gms.ads.internal.util.client.zzb.zzcx(r3);	 Catch:{ all -> 0x011a }
        r3 = new com.google.android.gms.ads.internal.request.AdResponseParcel;	 Catch:{ all -> 0x011a }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x011a }
        r2.disconnect();	 Catch:{ IOException -> 0x00f5 }
        if (r20 == 0) goto L_0x0161;
    L_0x015a:
        r0 = r20;
        r2 = r0.zzcdv;	 Catch:{ IOException -> 0x00f5 }
        r2.zzrp();	 Catch:{ IOException -> 0x00f5 }
    L_0x0161:
        r2 = r3;
        goto L_0x00e9;
    L_0x0163:
        r5 = new java.net.URL;	 Catch:{ all -> 0x011a }
        r5.<init>(r4);	 Catch:{ all -> 0x011a }
        r4 = r6 + 1;
        r6 = 5;
        if (r4 <= r6) goto L_0x01b4;
    L_0x016d:
        r3 = "Too many redirects.";
        com.google.android.gms.ads.internal.util.client.zzb.zzcx(r3);	 Catch:{ all -> 0x011a }
        r3 = new com.google.android.gms.ads.internal.request.AdResponseParcel;	 Catch:{ all -> 0x011a }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x011a }
        r2.disconnect();	 Catch:{ IOException -> 0x00f5 }
        if (r20 == 0) goto L_0x0184;
    L_0x017d:
        r0 = r20;
        r2 = r0.zzcdv;	 Catch:{ IOException -> 0x00f5 }
        r2.zzrp();	 Catch:{ IOException -> 0x00f5 }
    L_0x0184:
        r2 = r3;
        goto L_0x00e9;
    L_0x0187:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x011a }
        r4 = 46;
        r3.<init>(r4);	 Catch:{ all -> 0x011a }
        r4 = "Received error HTTP response code: ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x011a }
        r3 = r3.append(r9);	 Catch:{ all -> 0x011a }
        r3 = r3.toString();	 Catch:{ all -> 0x011a }
        com.google.android.gms.ads.internal.util.client.zzb.zzcx(r3);	 Catch:{ all -> 0x011a }
        r3 = new com.google.android.gms.ads.internal.request.AdResponseParcel;	 Catch:{ all -> 0x011a }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x011a }
        r2.disconnect();	 Catch:{ IOException -> 0x00f5 }
        if (r20 == 0) goto L_0x01b1;
    L_0x01aa:
        r0 = r20;
        r2 = r0.zzcdv;	 Catch:{ IOException -> 0x00f5 }
        r2.zzrp();	 Catch:{ IOException -> 0x00f5 }
    L_0x01b1:
        r2 = r3;
        goto L_0x00e9;
    L_0x01b4:
        r8.zzj(r12);	 Catch:{ all -> 0x011a }
        r2.disconnect();	 Catch:{ IOException -> 0x00f5 }
        if (r20 == 0) goto L_0x01c3;
    L_0x01bc:
        r0 = r20;
        r2 = r0.zzcdv;	 Catch:{ IOException -> 0x00f5 }
        r2.zzrp();	 Catch:{ IOException -> 0x00f5 }
    L_0x01c3:
        r6 = r4;
        r7 = r5;
        goto L_0x0031;
    L_0x01c7:
        r2 = new java.lang.String;
        r2.<init>(r3);
        goto L_0x010a;
    L_0x01ce:
        r3 = move-exception;
        goto L_0x012a;
    L_0x01d1:
        r3 = move-exception;
        goto L_0x0116;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzip.zza(com.google.android.gms.ads.internal.request.AdRequestInfoParcel, android.content.Context, java.lang.String, java.lang.String, java.lang.String, com.google.android.gms.internal.zziu, com.google.android.gms.internal.zzdk, com.google.android.gms.internal.zzio):com.google.android.gms.ads.internal.request.AdResponseParcel");
    }

    public static zzip zza(Context context, zzcv com_google_android_gms_internal_zzcv, zzio com_google_android_gms_internal_zzio) {
        zzip com_google_android_gms_internal_zzip;
        synchronized (zzamr) {
            if (zzcdx == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                zzcdx = new zzip(context, com_google_android_gms_internal_zzcv, com_google_android_gms_internal_zzio);
            }
            com_google_android_gms_internal_zzip = zzcdx;
        }
        return com_google_android_gms_internal_zzip;
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (com.google.android.gms.ads.internal.util.client.zzb.zzaz(2)) {
            zzkd.v(new StringBuilder(String.valueOf(str).length() + 39).append("Http Response: {\n  URL:\n    ").append(str).append("\n  Headers:").toString());
            if (map != null) {
                for (String str3 : map.keySet()) {
                    String str32;
                    zzkd.v(new StringBuilder(String.valueOf(str32).length() + 5).append("    ").append(str32).append(":").toString());
                    for (String str322 : (List) map.get(str322)) {
                        String str4 = "      ";
                        str322 = String.valueOf(str322);
                        zzkd.v(str322.length() != 0 ? str4.concat(str322) : new String(str4));
                    }
                }
            }
            zzkd.v("  Body:");
            if (str2 != null) {
                for (int i2 = 0; i2 < Math.min(str2.length(), 100000); i2 += 1000) {
                    zzkd.v(str2.substring(i2, Math.min(str2.length(), i2 + 1000)));
                }
            } else {
                zzkd.v("    null");
            }
            zzkd.v("  Response Code:\n    " + i + "\n}");
        }
    }

    private static Location zzb(zzky<Location> com_google_android_gms_internal_zzky_android_location_Location) {
        try {
            return (Location) com_google_android_gms_internal_zzky_android_location_Location.get(((Long) zzdc.zzbcp.get()).longValue(), TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Exception caught while getting location", e);
            return null;
        }
    }

    public void zza(AdRequestInfoParcel adRequestInfoParcel, zzl com_google_android_gms_ads_internal_request_zzl) {
        zzu.zzft().zzb(this.mContext, adRequestInfoParcel.zzaow);
        zzkg.zza(new 5(this, adRequestInfoParcel, com_google_android_gms_ads_internal_request_zzl));
    }

    public AdResponseParcel zzd(AdRequestInfoParcel adRequestInfoParcel) {
        return zza(this.mContext, this.zzcea, this.zzcdz, this.zzcdy, adRequestInfoParcel);
    }
}
