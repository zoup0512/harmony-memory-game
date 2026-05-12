package com.yandex.metrica.impl;

import com.cube.memorygames.games.Game1MemoryGridActivity;
import com.yandex.metrica.impl.ob.bu;
import com.yandex.metrica.impl.ob.bv;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import javax.net.ssl.HttpsURLConnection;

class af extends Thread {
    private final Executor a;
    private Executor b;
    private final BlockingQueue<b> c = new LinkedBlockingQueue();
    private final Object d = new Object();
    private volatile b e;

    private class a implements Runnable {
        final /* synthetic */ af a;
        private final ag b;

        private a(af afVar, ag agVar) {
            this.a = afVar;
            this.b = agVar;
        }

        public void run() {
            try {
                this.a.c(this.b);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static class b {
        private final ag a;
        private final String b;

        private b(ag agVar) {
            this.a = agVar;
            this.b = agVar.a();
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return this.b.equals(((b) o).b);
        }

        public int hashCode() {
            return this.b.hashCode();
        }
    }

    public af(Executor executor) {
        this.a = executor;
        this.b = new bu();
    }

    public void a(ag agVar) {
        synchronized (this.d) {
            b bVar = new b(agVar);
            if (!a(bVar)) {
                this.c.offer(bVar);
            }
        }
    }

    public void a() {
        this.e = null;
        this.c.clear();
        interrupt();
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Executor executor;
                this.e = (b) this.c.take();
                ag a = this.e.a;
                if (a.m()) {
                    executor = this.a;
                } else {
                    executor = this.b;
                }
                executor.execute(new a(a));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                this.e = null;
            }
        }
    }

    public boolean b(ag agVar) {
        return a(new b(agVar));
    }

    private boolean a(b bVar) {
        return this.c.contains(bVar) || bVar.equals(this.e);
    }

    void c(ag agVar) throws InterruptedException {
        int i;
        HttpURLConnection httpURLConnection;
        Closeable closeable;
        Throwable th;
        Closeable closeable2;
        Throwable th2;
        Closeable closeable3 = null;
        int b = agVar.b();
        if (b == 0 || !agVar.d() || bv.a().c()) {
            i = 0;
        } else {
            b = 0;
            i = 0;
        }
        while (!Thread.currentThread().isInterrupted() && r0 != 0) {
            Closeable closeable4;
            Closeable closeable5;
            int responseCode;
            i++;
            Closeable outputStream;
            try {
                Object obj;
                Closeable bufferedInputStream;
                HttpURLConnection httpURLConnection2 = (HttpsURLConnection) new URL(agVar.g()).openConnection();
                httpURLConnection2.setConnectTimeout(a.a);
                httpURLConnection2.setReadTimeout(a.a);
                httpURLConnection2.setDoInput(true);
                httpURLConnection2.setRequestProperty("Accept", "application/json");
                httpURLConnection2.setRequestProperty("User-Agent", bg.a("com.yandex.mobile.metrica.sdk"));
                bv a = bv.a();
                if (agVar.d()) {
                    httpURLConnection2.setSSLSocketFactory(a.b());
                }
                try {
                    if (2 == agVar.h()) {
                        byte[] i2 = agVar.i();
                        if (i2 != null && i2.length > 0) {
                            String l = agVar.l();
                            httpURLConnection2.setDoOutput(true);
                            httpURLConnection2.setRequestProperty(HttpRequest.HEADER_ACCEPT_ENCODING, l);
                            httpURLConnection2.setRequestProperty("Content-Encoding", l);
                            outputStream = httpURLConnection2.getOutputStream();
                            try {
                                OutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, i2.length);
                                try {
                                    bufferedOutputStream.write(agVar.i());
                                    bufferedOutputStream.flush();
                                    bg.a(outputStream);
                                    closeable4 = outputStream;
                                    closeable5 = bufferedOutputStream;
                                    responseCode = httpURLConnection2.getResponseCode();
                                    agVar.a(responseCode);
                                    agVar.a(httpURLConnection2.getHeaderFields());
                                    if (responseCode != Game1MemoryGridActivity.START_ANIMATION_DURATION || responseCode == 500) {
                                        obj = null;
                                    } else {
                                        obj = 1;
                                    }
                                    if (obj == null) {
                                        outputStream = httpURLConnection2.getInputStream();
                                        try {
                                            bufferedInputStream = new BufferedInputStream(outputStream, 8000);
                                            try {
                                                agVar.b(r.b((InputStream) bufferedInputStream));
                                                bg.a(outputStream);
                                            } catch (Throwable th3) {
                                                closeable3 = outputStream;
                                                th = th3;
                                                closeable2 = bufferedInputStream;
                                                httpURLConnection = httpURLConnection2;
                                                th2 = th;
                                            }
                                        } catch (Throwable th32) {
                                            httpURLConnection = httpURLConnection2;
                                            th2 = th32;
                                            closeable2 = null;
                                            closeable3 = outputStream;
                                        }
                                    } else {
                                        outputStream = null;
                                        bufferedInputStream = null;
                                    }
                                    bg.a(closeable5);
                                    bg.a(bufferedInputStream);
                                    bg.a(closeable4);
                                    bg.a(outputStream);
                                    bg.a(httpURLConnection2);
                                } catch (Throwable th322) {
                                    closeable4 = outputStream;
                                    Object obj2 = bufferedOutputStream;
                                    httpURLConnection = httpURLConnection2;
                                    th2 = th322;
                                    closeable2 = null;
                                }
                            } catch (Throwable th3222) {
                                closeable4 = outputStream;
                                httpURLConnection = httpURLConnection2;
                                closeable5 = null;
                                th2 = th3222;
                                closeable2 = null;
                            }
                            b = (i <= 3 || agVar.c()) ? 0 : 1;
                            if (Game1MemoryGridActivity.START_ANIMATION_DURATION != agVar.j()) {
                                responseCode = 1;
                            } else {
                                responseCode = 0;
                            }
                            b &= responseCode;
                            if (b != 0) {
                                if (i % 3 != 0) {
                                }
                                Thread.sleep(i % 3 != 0 ? b.a : b.b);
                            }
                        }
                    }
                    closeable4 = null;
                    closeable5 = null;
                } catch (Throwable th32222) {
                    closeable4 = null;
                    httpURLConnection = httpURLConnection2;
                    closeable5 = null;
                    th2 = th32222;
                    closeable2 = null;
                }
                try {
                    responseCode = httpURLConnection2.getResponseCode();
                    agVar.a(responseCode);
                    agVar.a(httpURLConnection2.getHeaderFields());
                    if (responseCode != Game1MemoryGridActivity.START_ANIMATION_DURATION) {
                    }
                    obj = null;
                    if (obj == null) {
                        outputStream = null;
                        bufferedInputStream = null;
                    } else {
                        outputStream = httpURLConnection2.getInputStream();
                        bufferedInputStream = new BufferedInputStream(outputStream, 8000);
                        agVar.b(r.b((InputStream) bufferedInputStream));
                        bg.a(outputStream);
                    }
                    bg.a(closeable5);
                    bg.a(bufferedInputStream);
                    bg.a(closeable4);
                    bg.a(outputStream);
                    bg.a(httpURLConnection2);
                } catch (Throwable th322222) {
                    httpURLConnection = httpURLConnection2;
                    th2 = th322222;
                    closeable2 = null;
                }
            } catch (Throwable th4) {
                th2 = th4;
                closeable4 = null;
                httpURLConnection = null;
                closeable5 = null;
                closeable2 = null;
            }
            if (i <= 3) {
            }
            if (Game1MemoryGridActivity.START_ANIMATION_DURATION != agVar.j()) {
                responseCode = 0;
            } else {
                responseCode = 1;
            }
            b &= responseCode;
            if (b != 0) {
                if (i % 3 != 0) {
                }
                Thread.sleep(i % 3 != 0 ? b.a : b.b);
            }
        }
        agVar.e();
        return;
        bg.a(closeable5);
        bg.a(closeable2);
        bg.a(closeable4);
        bg.a(closeable3);
        bg.a(httpURLConnection);
        throw th2;
    }
}
