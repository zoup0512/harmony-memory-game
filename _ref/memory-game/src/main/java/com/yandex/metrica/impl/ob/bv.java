package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;
import javax.net.ssl.SSLSocketFactory;

public class bv {
    String a;
    private String b;
    private SSLSocketFactory c;
    private ec d = new ec(this) {
        final /* synthetic */ bv a;

        {
            this.a = r1;
        }

        public String a() {
            return this.a.b;
        }
    };

    private static class a {
        static final bv a = new bv();
    }

    public static bv a() {
        return a.a;
    }

    bv() {
    }

    public synchronized SSLSocketFactory b() {
        return this.c;
    }

    public synchronized boolean c() {
        return this.c != null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.content.Context r5, java.lang.String r6, java.lang.String r7) {
        /*
        r4 = this;
        r1 = 0;
        r0 = 1;
        monitor-enter(r4);
        r2 = android.text.TextUtils.isEmpty(r7);	 Catch:{ all -> 0x0074 }
        if (r2 == 0) goto L_0x005b;
    L_0x0009:
        r2 = "https://certificate.mobile.yandex.net/api/v1/pins";
        r3 = r2;
    L_0x000c:
        r2 = android.text.TextUtils.isEmpty(r6);	 Catch:{ all -> 0x0074 }
        if (r2 != 0) goto L_0x0072;
    L_0x0012:
        r2 = r4.c();	 Catch:{ all -> 0x0074 }
        if (r2 == 0) goto L_0x0070;
    L_0x0018:
        r2 = r4.a;	 Catch:{ all -> 0x0074 }
        r2 = r3.equals(r2);	 Catch:{ all -> 0x0074 }
        if (r2 == 0) goto L_0x0070;
    L_0x0020:
        r2 = r0;
    L_0x0021:
        if (r2 != 0) goto L_0x0072;
    L_0x0023:
        if (r0 == 0) goto L_0x0059;
    L_0x0025:
        r4.b = r6;	 Catch:{ all -> 0x0074 }
        r4.a = r3;	 Catch:{ all -> 0x0074 }
        r0 = new com.yandex.metrica.impl.ob.dw;	 Catch:{ all -> 0x0074 }
        r1 = r4.d;	 Catch:{ all -> 0x0074 }
        r2 = 1;
        r3 = 1;
        r0.<init>(r1, r2, r3);	 Catch:{ all -> 0x0074 }
        r1 = d();	 Catch:{ all -> 0x0074 }
        if (r1 == 0) goto L_0x0059;
    L_0x0038:
        r2 = new java.util.ArrayList;	 Catch:{ all -> 0x0074 }
        r2.<init>();	 Catch:{ all -> 0x0074 }
        r2.add(r1);	 Catch:{ all -> 0x0074 }
        r1 = r4.a;	 Catch:{ all -> 0x0074 }
        r0.a(r1, r2);	 Catch:{ all -> 0x0074 }
        r1 = new com.yandex.metrica.impl.ob.dp;	 Catch:{ all -> 0x0074 }
        r1.<init>(r5, r0);	 Catch:{ all -> 0x0074 }
        r0 = new com.yandex.metrica.impl.ob.ds;	 Catch:{ Exception -> 0x0077 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x0077 }
        r0 = r0.a();	 Catch:{ Exception -> 0x0077 }
        r0 = r0.getSocketFactory();	 Catch:{ Exception -> 0x0077 }
        r4.c = r0;	 Catch:{ Exception -> 0x0077 }
    L_0x0059:
        monitor-exit(r4);
        return;
    L_0x005b:
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0074 }
        r2.<init>();	 Catch:{ all -> 0x0074 }
        r2 = r2.append(r7);	 Catch:{ all -> 0x0074 }
        r3 = "/api/v1/pins";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0074 }
        r2 = r2.toString();	 Catch:{ all -> 0x0074 }
        r3 = r2;
        goto L_0x000c;
    L_0x0070:
        r2 = r1;
        goto L_0x0021;
    L_0x0072:
        r0 = r1;
        goto L_0x0023;
    L_0x0074:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x0077:
        r0 = move-exception;
        goto L_0x0059;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.bv.a(android.content.Context, java.lang.String, java.lang.String):void");
    }

    private static X509Certificate d() {
        try {
            String[] a = a.a();
            if (a != null && a.length > 0) {
                return dq.a(a[0]);
            }
        } catch (Exception e) {
        }
        return null;
    }
}
