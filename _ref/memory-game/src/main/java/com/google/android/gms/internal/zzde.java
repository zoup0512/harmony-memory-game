package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@zzin
public class zzde {
    final Context mContext;
    final String zzarj;
    String zzbdp;
    BlockingQueue<zzdk> zzbdr;
    ExecutorService zzbds;
    LinkedHashMap<String, String> zzbdt = new LinkedHashMap();
    Map<String, zzdh> zzbdu = new HashMap();
    private AtomicBoolean zzbdv;
    private File zzbdw;

    public zzde(Context context, String str, String str2, Map<String, String> map) {
        this.mContext = context;
        this.zzarj = str;
        this.zzbdp = str2;
        this.zzbdv = new AtomicBoolean(false);
        this.zzbdv.set(((Boolean) zzdc.zzazg.get()).booleanValue());
        if (this.zzbdv.get()) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory != null) {
                this.zzbdw = new File(externalStorageDirectory, "sdk_csi_data.txt");
            }
        }
        for (Entry entry : map.entrySet()) {
            this.zzbdt.put((String) entry.getKey(), (String) entry.getValue());
        }
        this.zzbdr = new ArrayBlockingQueue(30);
        this.zzbds = Executors.newSingleThreadExecutor();
        this.zzbds.execute(new 1(this));
        this.zzbdu.put(NativeProtocol.WEB_DIALOG_ACTION, zzdh.zzbdz);
        this.zzbdu.put("ad_format", zzdh.zzbdz);
        this.zzbdu.put("e", zzdh.zzbea);
    }

    private void zzc(@Nullable File file, String str) {
        FileOutputStream fileOutputStream;
        Throwable e;
        if (file != null) {
            try {
                fileOutputStream = new FileOutputStream(file, true);
                try {
                    fileOutputStream.write(str.getBytes());
                    fileOutputStream.write(10);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                            return;
                        } catch (Throwable e2) {
                            zzb.zzd("CsiReporter: Cannot close file: sdk_csi_data.txt.", e2);
                            return;
                        }
                    }
                    return;
                } catch (IOException e3) {
                    e2 = e3;
                    try {
                        zzb.zzd("CsiReporter: Cannot write to file: sdk_csi_data.txt.", e2);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                                return;
                            } catch (Throwable e22) {
                                zzb.zzd("CsiReporter: Cannot close file: sdk_csi_data.txt.", e22);
                                return;
                            }
                        }
                        return;
                    } catch (Throwable th) {
                        e22 = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable e4) {
                                zzb.zzd("CsiReporter: Cannot close file: sdk_csi_data.txt.", e4);
                            }
                        }
                        throw e22;
                    }
                }
            } catch (IOException e5) {
                e22 = e5;
                fileOutputStream = null;
                zzb.zzd("CsiReporter: Cannot write to file: sdk_csi_data.txt.", e22);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                    return;
                }
                return;
            } catch (Throwable th2) {
                e22 = th2;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw e22;
            }
        }
        zzb.zzcx("CsiReporter: File doesn't exists. Cannot write CSI data to file.");
    }

    private void zzc(Map<String, String> map, String str) {
        String zza = zza(this.zzbdp, map, str);
        if (this.zzbdv.get()) {
            zzc(this.zzbdw, zza);
        } else {
            zzu.zzfq().zzc(this.mContext, this.zzarj, zza);
        }
    }

    private void zzkc() {
        while (true) {
            try {
                zzdk com_google_android_gms_internal_zzdk = (zzdk) this.zzbdr.take();
                String zzki = com_google_android_gms_internal_zzdk.zzki();
                if (!TextUtils.isEmpty(zzki)) {
                    zzc(zza(this.zzbdt, com_google_android_gms_internal_zzdk.zzm()), zzki);
                }
            } catch (Throwable e) {
                zzb.zzd("CsiReporter:reporter interrupted", e);
                return;
            }
        }
    }

    String zza(String str, Map<String, String> map, @NonNull String str2) {
        Builder buildUpon = Uri.parse(str).buildUpon();
        for (Entry entry : map.entrySet()) {
            buildUpon.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        StringBuilder stringBuilder = new StringBuilder(buildUpon.build().toString());
        stringBuilder.append("&").append("it").append("=").append(str2);
        return stringBuilder.toString();
    }

    Map<String, String> zza(Map<String, String> map, @Nullable Map<String, String> map2) {
        Map<String, String> linkedHashMap = new LinkedHashMap(map);
        if (map2 == null) {
            return linkedHashMap;
        }
        for (Entry entry : map2.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) linkedHashMap.get(str);
            linkedHashMap.put(str, zzaq(str).zzg(str2, (String) entry.getValue()));
        }
        return linkedHashMap;
    }

    public boolean zza(zzdk com_google_android_gms_internal_zzdk) {
        return this.zzbdr.offer(com_google_android_gms_internal_zzdk);
    }

    public zzdh zzaq(String str) {
        zzdh com_google_android_gms_internal_zzdh = (zzdh) this.zzbdu.get(str);
        return com_google_android_gms_internal_zzdh != null ? com_google_android_gms_internal_zzdh : zzdh.zzbdy;
    }

    public void zzc(@Nullable List<String> list) {
        if (list != null && !list.isEmpty()) {
            this.zzbdt.put("e", TextUtils.join(",", list));
        }
    }
}
