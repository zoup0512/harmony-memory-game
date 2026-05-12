package com.chartboost.sdk.impl;

import android.os.Handler;
import com.applovin.sdk.AppLovinErrorCodes;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.concurrent.PriorityBlockingQueue;
import org.nexage.sourcekit.vast.model.VASTModel;

class ak extends Thread {
    private final an a;
    private final al b;
    private final PriorityBlockingQueue<w<?>> c;
    private final Handler d = a.a().a;
    private volatile boolean e = false;

    private class a<T> implements Runnable {
        final /* synthetic */ ak a;
        private final w<T> b;
        private final ab c;
        private final y<T> d;

        public a(ak akVar, w<T> wVar, ab abVar, y<T> yVar) {
            this.a = akVar;
            this.b = wVar;
            this.c = abVar;
            this.d = yVar;
        }

        public void run() {
            try {
                if (!this.b.g()) {
                    if (this.d.a()) {
                        this.b.a(this.d.a);
                    } else {
                        this.b.a(new am(this.c, this.d.b));
                    }
                }
                this.a.a.b(this.b);
            } catch (Throwable th) {
                this.a.a.b(this.b);
            }
        }
    }

    public ak(an anVar, al alVar, PriorityBlockingQueue<w<?>> priorityBlockingQueue) {
        this.a = anVar;
        this.b = alVar;
        this.c = priorityBlockingQueue;
    }

    public void run() {
        Exception e;
        Throwable th;
        a.a().b(10);
        while (!this.e) {
            w wVar;
            ab a;
            try {
                wVar = (w) this.c.take();
                if (wVar.g()) {
                    this.a.b(wVar);
                } else {
                    try {
                        a = a(wVar);
                        try {
                            y a2;
                            int b = a.b();
                            if (b >= 200 && b < 300) {
                                a2 = wVar.a(a);
                                a = null;
                            } else if (b == VASTModel.ERROR_CODE_NO_FILE || b == 403) {
                                a2 = y.a(new ai());
                            } else {
                                a2 = y.a(new ar());
                            }
                            a(wVar, a, a2);
                        } catch (Exception e2) {
                            e = e2;
                            try {
                                a(wVar, a, y.a(e));
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        a = null;
                        a(wVar, a, y.a(e));
                    } catch (Throwable th3) {
                        th = th3;
                        a = null;
                    }
                }
            } catch (InterruptedException e4) {
            }
        }
        return;
        a(wVar, a, null);
        throw th;
    }

    private ab a(w<?> wVar) throws IOException {
        int i = 10000;
        int i2 = 0;
        while (true) {
            try {
                return a(wVar, i);
            } catch (MalformedURLException e) {
                throw e;
            } catch (SocketTimeoutException e2) {
                if (i2 < 1) {
                    i *= 2;
                    i2++;
                } else {
                    throw e2;
                }
            } catch (IOException e3) {
                throw new ap(e3);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.chartboost.sdk.impl.ab a(com.chartboost.sdk.impl.w<?> r6, int r7) throws java.io.IOException {
        /*
        r5 = this;
        r4 = 1;
        r1 = 0;
        r2 = r6.b();
        r0 = r5.b;
        r3 = r0.a(r6);
        r3.setConnectTimeout(r7);
        r3.setReadTimeout(r7);
        r3.setUseCaches(r1);
        r3.setDoInput(r4);
        r0 = r2.keySet();	 Catch:{ all -> 0x0036 }
        r4 = r0.iterator();	 Catch:{ all -> 0x0036 }
    L_0x0020:
        r0 = r4.hasNext();	 Catch:{ all -> 0x0036 }
        if (r0 == 0) goto L_0x003b;
    L_0x0026:
        r0 = r4.next();	 Catch:{ all -> 0x0036 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0036 }
        r1 = r2.get(r0);	 Catch:{ all -> 0x0036 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0036 }
        r3.addRequestProperty(r0, r1);	 Catch:{ all -> 0x0036 }
        goto L_0x0020;
    L_0x0036:
        r0 = move-exception;
        r3.disconnect();
        throw r0;
    L_0x003b:
        r0 = r6.b;	 Catch:{ all -> 0x0036 }
        r0 = r0.toString();	 Catch:{ all -> 0x0036 }
        r3.setRequestMethod(r0);	 Catch:{ all -> 0x0036 }
        r0 = r6.b;	 Catch:{ all -> 0x0036 }
        r1 = com.chartboost.sdk.impl.w.a.b;	 Catch:{ all -> 0x0036 }
        if (r0 != r1) goto L_0x0071;
    L_0x004a:
        r0 = r6.e();	 Catch:{ all -> 0x0036 }
        if (r0 == 0) goto L_0x0071;
    L_0x0050:
        r1 = 1;
        r3.setDoOutput(r1);	 Catch:{ all -> 0x0036 }
        r1 = "Content-Type";
        r2 = r6.d();	 Catch:{ all -> 0x0036 }
        r3.addRequestProperty(r1, r2);	 Catch:{ all -> 0x0036 }
        r2 = new java.io.DataOutputStream;	 Catch:{ all -> 0x0036 }
        r1 = r3.getOutputStream();	 Catch:{ all -> 0x0036 }
        r2.<init>(r1);	 Catch:{ all -> 0x0036 }
        r1 = 0;
        r2.write(r0);	 Catch:{ Throwable -> 0x0089 }
        if (r2 == 0) goto L_0x0071;
    L_0x006c:
        if (r1 == 0) goto L_0x0085;
    L_0x006e:
        r2.close();	 Catch:{ Throwable -> 0x0080 }
    L_0x0071:
        r1 = r3.getResponseCode();	 Catch:{ all -> 0x0036 }
        r0 = -1;
        if (r1 != r0) goto L_0x009d;
    L_0x0078:
        r0 = new com.chartboost.sdk.impl.ap;	 Catch:{ all -> 0x0036 }
        r1 = "Could not retrieve response code from HttpUrlConnection.";
        r0.<init>(r1);	 Catch:{ all -> 0x0036 }
        throw r0;	 Catch:{ all -> 0x0036 }
    L_0x0080:
        r0 = move-exception;
        r1.addSuppressed(r0);	 Catch:{ all -> 0x0036 }
        goto L_0x0071;
    L_0x0085:
        r2.close();	 Catch:{ all -> 0x0036 }
        goto L_0x0071;
    L_0x0089:
        r1 = move-exception;
        throw r1;	 Catch:{ all -> 0x008b }
    L_0x008b:
        r0 = move-exception;
        if (r2 == 0) goto L_0x0093;
    L_0x008e:
        if (r1 == 0) goto L_0x0099;
    L_0x0090:
        r2.close();	 Catch:{ Throwable -> 0x0094 }
    L_0x0093:
        throw r0;	 Catch:{ all -> 0x0036 }
    L_0x0094:
        r2 = move-exception;
        r1.addSuppressed(r2);	 Catch:{ all -> 0x0036 }
        goto L_0x0093;
    L_0x0099:
        r2.close();	 Catch:{ all -> 0x0036 }
        goto L_0x0093;
    L_0x009d:
        r0 = a(r1);	 Catch:{ all -> 0x0036 }
        if (r0 == 0) goto L_0x00c5;
    L_0x00a3:
        r0 = r3.getInputStream();	 Catch:{ IOException -> 0x00bb }
    L_0x00a7:
        if (r0 == 0) goto L_0x00c1;
    L_0x00a9:
        r2 = new java.io.BufferedInputStream;	 Catch:{ all -> 0x0036 }
        r2.<init>(r0);	 Catch:{ all -> 0x0036 }
        r0 = com.chartboost.sdk.impl.bk.b(r2);	 Catch:{ all -> 0x0036 }
    L_0x00b2:
        r2 = new com.chartboost.sdk.impl.ao;	 Catch:{ all -> 0x0036 }
        r2.<init>(r1, r0);	 Catch:{ all -> 0x0036 }
        r3.disconnect();
        return r2;
    L_0x00bb:
        r0 = move-exception;
        r0 = r3.getErrorStream();	 Catch:{ all -> 0x0036 }
        goto L_0x00a7;
    L_0x00c1:
        r0 = 0;
        r0 = new byte[r0];	 Catch:{ all -> 0x0036 }
        goto L_0x00b2;
    L_0x00c5:
        r0 = 0;
        r0 = new byte[r0];	 Catch:{ all -> 0x0036 }
        goto L_0x00b2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.impl.ak.a(com.chartboost.sdk.impl.w, int):com.chartboost.sdk.impl.ab");
    }

    private static boolean a(int i) {
        boolean z;
        if (100 > i || i >= 200) {
            z = false;
        } else {
            z = true;
        }
        return (z || i == AppLovinErrorCodes.NO_FILL || i == 304) ? false : true;
    }

    private <T> void a(w<T> wVar, ab abVar, y<T> yVar) {
        this.d.post(new a(this, wVar, abVar, yVar));
    }
}
