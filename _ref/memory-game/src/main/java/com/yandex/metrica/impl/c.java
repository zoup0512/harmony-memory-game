package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Base64;
import com.yandex.metrica.impl.utils.e;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    public static final long a = TimeUnit.SECONDS.toMillis(15);
    private final Context b;
    private long c = 0;

    public c(Context context) {
        this.b = context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.HashMap<java.lang.String, java.lang.String> a() {
        /*
        r11 = this;
        r5 = 0;
        r4 = 1;
        r0 = 0;
        monitor-enter(r11);
        r1 = r11.b;	 Catch:{ all -> 0x00de }
        r2 = "b_meta.dat";
        r1 = r1.getFileStreamPath(r2);	 Catch:{ all -> 0x00de }
        r1 = r1.getAbsolutePath();	 Catch:{ all -> 0x00de }
        r6 = new java.io.File;	 Catch:{ Exception -> 0x00d0, all -> 0x00e1 }
        r6.<init>(r1);	 Catch:{ Exception -> 0x00d0, all -> 0x00e1 }
        r2 = r6.exists();	 Catch:{ Exception -> 0x00d0, all -> 0x00e1 }
        if (r2 != 0) goto L_0x003e;
    L_0x001b:
        r6.createNewFile();	 Catch:{ Exception -> 0x00d0, all -> 0x00e1 }
        r2 = 1;
        r3 = 0;
        r6.setReadable(r2, r3);	 Catch:{ Exception -> 0x00d0, all -> 0x00e1 }
        r2 = r11.b;	 Catch:{ Exception -> 0x0107, all -> 0x00e1 }
        r3 = "browsers.dat";
        r2 = r2.getFileStreamPath(r3);	 Catch:{ Exception -> 0x0107, all -> 0x00e1 }
        r2 = r2.getAbsoluteFile();	 Catch:{ Exception -> 0x0107, all -> 0x00e1 }
        r3 = r2.exists();	 Catch:{ Exception -> 0x0107, all -> 0x00e1 }
        if (r3 == 0) goto L_0x003e;
    L_0x0035:
        r3 = r2.canWrite();	 Catch:{ Exception -> 0x0107, all -> 0x00e1 }
        if (r3 == 0) goto L_0x003e;
    L_0x003b:
        r2.delete();	 Catch:{ Exception -> 0x0107, all -> 0x00e1 }
    L_0x003e:
        r3 = new java.io.RandomAccessFile;	 Catch:{ Exception -> 0x00d0, all -> 0x00e1 }
        r2 = "rw";
        r3.<init>(r1, r2);	 Catch:{ Exception -> 0x00d0, all -> 0x00e1 }
        r1 = r3.getChannel();	 Catch:{ Exception -> 0x00fe, all -> 0x00f1 }
        r2 = r1.lock();	 Catch:{ Exception -> 0x0102, all -> 0x00f7 }
        r6 = r6.length();	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r6 = (int) r6;	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r6 = java.nio.ByteBuffer.allocate(r6);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r1.read(r6);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r6.flip();	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r6 = r6.array();	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r6 = r11.a(r6);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r0 = r11.b(r6);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r8 = r11.c;	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r6 = r6 - r8;
        r8 = a;	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r6 <= 0) goto L_0x00ce;
    L_0x0075:
        if (r4 == 0) goto L_0x00c3;
    L_0x0077:
        r11.a(r0);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r11.c = r4;	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r4 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r4.<init>();	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r5 = "browser_open_times";
        r6 = com.yandex.metrica.impl.bg.b(r0);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r4.putOpt(r5, r6);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r5 = "last_sync_time";
        r6 = r11.c;	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r4.putOpt(r5, r6);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r4 = r11.a(r4);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r5 = "UTF-8";
        r4 = r4.getBytes(r5);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r5 = r4.length;	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r5 = java.nio.ByteBuffer.allocate(r5);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r5.put(r4);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r5.flip();	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r6 = 0;
        r1.position(r6);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r6 = 0;
        r1.truncate(r6);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r1.write(r5);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
        r4 = 1;
        r1.force(r4);	 Catch:{ Exception -> 0x0105, all -> 0x00fc }
    L_0x00c3:
        com.yandex.metrica.impl.r.a(r2);	 Catch:{ all -> 0x00de }
        com.yandex.metrica.impl.bg.a(r3);	 Catch:{ all -> 0x00de }
        com.yandex.metrica.impl.bg.a(r1);	 Catch:{ all -> 0x00de }
    L_0x00cc:
        monitor-exit(r11);
        return r0;
    L_0x00ce:
        r4 = r5;
        goto L_0x0075;
    L_0x00d0:
        r1 = move-exception;
        r1 = r0;
        r2 = r0;
        r3 = r0;
    L_0x00d4:
        com.yandex.metrica.impl.r.a(r2);	 Catch:{ all -> 0x00de }
        com.yandex.metrica.impl.bg.a(r3);	 Catch:{ all -> 0x00de }
        com.yandex.metrica.impl.bg.a(r1);	 Catch:{ all -> 0x00de }
        goto L_0x00cc;
    L_0x00de:
        r0 = move-exception;
        monitor-exit(r11);
        throw r0;
    L_0x00e1:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r10 = r1;
        r1 = r0;
        r0 = r10;
    L_0x00e7:
        com.yandex.metrica.impl.r.a(r2);	 Catch:{ all -> 0x00de }
        com.yandex.metrica.impl.bg.a(r3);	 Catch:{ all -> 0x00de }
        com.yandex.metrica.impl.bg.a(r1);	 Catch:{ all -> 0x00de }
        throw r0;	 Catch:{ all -> 0x00de }
    L_0x00f1:
        r1 = move-exception;
        r2 = r0;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x00e7;
    L_0x00f7:
        r2 = move-exception;
        r10 = r2;
        r2 = r0;
        r0 = r10;
        goto L_0x00e7;
    L_0x00fc:
        r0 = move-exception;
        goto L_0x00e7;
    L_0x00fe:
        r1 = move-exception;
        r1 = r0;
        r2 = r0;
        goto L_0x00d4;
    L_0x0102:
        r2 = move-exception;
        r2 = r0;
        goto L_0x00d4;
    L_0x0105:
        r4 = move-exception;
        goto L_0x00d4;
    L_0x0107:
        r2 = move-exception;
        goto L_0x003e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.c.a():java.util.HashMap<java.lang.String, java.lang.String>");
    }

    private HashMap<String, String> b(String str) {
        HashMap<String, String> hashMap = null;
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                hashMap = bg.d(jSONObject.optString("browser_open_times"));
                this.c = jSONObject.optLong("last_sync_time", 0);
            }
        } catch (JSONException e) {
        }
        return hashMap != null ? hashMap : new HashMap();
    }

    private void a(HashMap<String, String> hashMap) {
        for (String file : b()) {
            a(hashMap, a(new File(file)));
        }
    }

    private HashMap<String, String> a(File file) {
        try {
            if (file.exists()) {
                byte[] b = r.b(this.b, file);
                if (b != null) {
                    String a = a(b);
                    file.getName();
                    return b(a);
                }
            }
        } catch (UnsupportedEncodingException e) {
        }
        return new HashMap(0);
    }

    List<String> b() {
        List<ResolveInfo> a = ba.a(this.b, ba.a(this.b));
        List<String> arrayList = new ArrayList();
        String packageName = this.b.getPackageName();
        for (ResolveInfo resolveInfo : a) {
            String str = resolveInfo.serviceInfo.applicationInfo.packageName;
            if (!packageName.equals(str) && ba.a(resolveInfo.serviceInfo) >= 47) {
                try {
                    arrayList.add(this.b.getFileStreamPath("b_meta.dat").getAbsolutePath().replace(this.b.getApplicationInfo().dataDir, this.b.getPackageManager().getApplicationInfo(str, 8192).dataDir));
                } catch (NameNotFoundException e) {
                }
            }
        }
        return arrayList;
    }

    private static boolean a(Map<String, String> map, Map<String, String> map2) {
        boolean z = false;
        for (Entry entry : map2.entrySet()) {
            boolean z2;
            String str = (String) entry.getKey();
            long a = e.a((String) entry.getValue(), 0);
            long a2 = e.a((String) map.get(str), 0);
            if (a <= 0 || a2 >= a) {
                z2 = z;
            } else {
                map.put(str, String.valueOf(a));
                z2 = true;
            }
            z = z2;
        }
        return z;
    }

    String a(String str) throws UnsupportedEncodingException {
        return Base64.encodeToString(b(r.b(str).getBytes("UTF-8")), 0);
    }

    String a(byte[] bArr) throws UnsupportedEncodingException {
        return r.c(new String(b(Base64.decode(bArr, 0)), "UTF-8"));
    }

    private byte[] b(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance(CommonUtils.MD5_INSTANCE);
            instance.reset();
            instance.update(this.b.getPackageName().getBytes("UTF-8"));
            byte[] digest = instance.digest();
            byte[] bArr2 = new byte[bArr.length];
            for (int i = 0; i < bArr.length; i++) {
                bArr2[i] = (byte) (bArr[i] ^ digest[i % digest.length]);
            }
            return bArr2;
        } catch (Exception e) {
            return null;
        }
    }
}
