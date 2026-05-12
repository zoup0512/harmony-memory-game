package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.ob.ek.a;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.http.HttpEntity;

public class ej {
    protected final ef a;

    public ej(ef efVar) {
        this.a = efVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.yandex.metrica.impl.ob.em a(com.yandex.metrica.impl.ob.en<?> r12) throws com.yandex.metrica.impl.ob.ek {
        /*
        r11 = this;
        r3 = 0;
        r5 = 0;
    L_0x0002:
        r1 = java.util.Collections.emptyMap();
        r0 = r11.a;	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x008e }
        r4 = r0.a(r12);	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x008e }
        r0 = r4.getStatusLine();	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00ce }
        r6 = r0.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00ce }
        r7 = r4.getAllHeaders();	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00ce }
        r2 = new java.util.TreeMap;	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00ce }
        r0 = java.lang.String.CASE_INSENSITIVE_ORDER;	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00ce }
        r2.<init>(r0);	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00ce }
        r0 = r5;
    L_0x0020:
        r8 = r7.length;	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00ce }
        if (r0 >= r8) goto L_0x0035;
    L_0x0023:
        r8 = r7[r0];	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00ce }
        r8 = r8.getName();	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00ce }
        r9 = r7[r0];	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00ce }
        r9 = r9.getValue();	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00ce }
        r2.put(r8, r9);	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00ce }
        r0 = r0 + 1;
        goto L_0x0020;
    L_0x0035:
        r0 = r4.getEntity();	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d1 }
        if (r0 == 0) goto L_0x005d;
    L_0x003b:
        r0 = r4.getEntity();	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d1 }
        r1 = a(r0);	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d1 }
    L_0x0043:
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r6 < r0) goto L_0x004b;
    L_0x0047:
        r0 = 299; // 0x12b float:4.19E-43 double:1.477E-321;
        if (r6 <= r0) goto L_0x0061;
    L_0x004b:
        r0 = new java.io.IOException;	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d5 }
        r0.<init>();	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d5 }
        throw r0;	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d5 }
    L_0x0051:
        r0 = move-exception;
        r0 = new com.yandex.metrica.impl.ob.ek;
        r1 = com.yandex.metrica.impl.ob.ek.a.TIMEOUT;
        r0.<init>();
        a(r12, r0);
        goto L_0x0002;
    L_0x005d:
        r0 = 0;
        r1 = new byte[r0];	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d1 }
        goto L_0x0043;
    L_0x0061:
        r0 = new com.yandex.metrica.impl.ob.em;	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d5 }
        r6 = 0;
        r0.<init>(r1, r2, r6);	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d5 }
        return r0;
    L_0x0068:
        r0 = move-exception;
        r0 = new com.yandex.metrica.impl.ob.ek;
        r1 = com.yandex.metrica.impl.ob.ek.a.NO_CONNECTION;
        r0.<init>();
        a(r12, r0);
        goto L_0x0002;
    L_0x0074:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r2 = new java.lang.StringBuilder;
        r3 = "Bad URL ";
        r2.<init>(r3);
        r3 = r12.a();
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2, r0);
        throw r1;
    L_0x008e:
        r0 = move-exception;
        r2 = r3;
        r4 = r3;
    L_0x0091:
        if (r4 == 0) goto L_0x00b6;
    L_0x0093:
        r0 = r4.getStatusLine();
        r0 = r0.getStatusCode();
        if (r2 == 0) goto L_0x00c6;
    L_0x009d:
        r4 = new com.yandex.metrica.impl.ob.em;
        r4.<init>(r2, r1, r5);
        r1 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r0 == r1) goto L_0x00aa;
    L_0x00a6:
        r1 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r0 != r1) goto L_0x00be;
    L_0x00aa:
        r0 = new com.yandex.metrica.impl.ob.ek;
        r1 = com.yandex.metrica.impl.ob.ek.a.AUTH;
        r0.<init>(r5);
        a(r12, r0);
        goto L_0x0002;
    L_0x00b6:
        r1 = new com.yandex.metrica.impl.ob.ek;
        r2 = com.yandex.metrica.impl.ob.ek.a.DEFAULT;
        r1.<init>(r0);
        throw r1;
    L_0x00be:
        r0 = new com.yandex.metrica.impl.ob.ek;
        r1 = com.yandex.metrica.impl.ob.ek.a.SERVER;
        r0.<init>(r5);
        throw r0;
    L_0x00c6:
        r0 = new com.yandex.metrica.impl.ob.ek;
        r1 = com.yandex.metrica.impl.ob.ek.a.NETWORK;
        r0.<init>(r5);
        throw r0;
    L_0x00ce:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0091;
    L_0x00d1:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x0091;
    L_0x00d5:
        r0 = move-exception;
        r10 = r2;
        r2 = r1;
        r1 = r10;
        goto L_0x0091;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.ej.a(com.yandex.metrica.impl.ob.en):com.yandex.metrica.impl.ob.em");
    }

    private static void a(en<?> enVar, ek ekVar) throws ek {
        try {
            enVar.o().a(ekVar);
        } catch (ek e) {
            throw e;
        }
    }

    private static byte[] a(HttpEntity httpEntity) throws IOException, ek {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max((int) httpEntity.getContentLength(), 256));
        try {
            InputStream content = httpEntity.getContent();
            if (content == null) {
                a aVar = a.SERVER;
                throw new ek();
            }
            byte[] bArr = new byte[1024];
            while (true) {
                int read = content.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            return toByteArray;
        } finally {
            try {
                httpEntity.consumeContent();
            } catch (IOException e) {
            }
            byteArrayOutputStream.close();
        }
    }

    public static String a(Map<String, String> map, String str) {
        String str2 = (String) map.get("Content-Type");
        if (str2 == null) {
            return str;
        }
        String[] split = str2.split(";");
        for (int i = 1; i < split.length; i++) {
            String[] split2 = split[i].trim().split("=");
            if (split2.length == 2 && split2[0].equals(HttpRequest.PARAM_CHARSET)) {
                return split2[1];
            }
        }
        return str;
    }
}
