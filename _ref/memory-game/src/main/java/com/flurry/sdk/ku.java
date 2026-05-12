package com.flurry.sdk;

import com.cube.memorygames.games.Game1MemoryGridActivity;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map.Entry;

public class ku extends mb {
    static final String e = ku.class.getSimpleName();
    private final kd<String, String> a = new kd();
    private final Object b = new Object();
    private int c;
    private int d;
    public final kd<String, String> f = new kd();
    public String g;
    public a h;
    public int i = 10000;
    public int j = 15000;
    public boolean k = true;
    public c l;
    public boolean m;
    long n = -1;
    public long o = -1;
    public Exception p;
    public int q = -1;
    public boolean r;
    public int s = 25000;
    public boolean t;
    private HttpURLConnection v;
    private boolean w;
    private boolean x;
    private kt y = new kt(this);

    public interface c {
        void a(ku kuVar);

        void a(ku kuVar, InputStream inputStream) throws Exception;

        void a(OutputStream outputStream) throws Exception;
    }

    public enum a {
        kUnknown,
        kGet,
        kPost,
        kPut,
        kDelete,
        kHead;

        public final String toString() {
            switch (this) {
                case kPost:
                    return HttpRequest.METHOD_POST;
                case kPut:
                    return HttpRequest.METHOD_PUT;
                case kDelete:
                    return HttpRequest.METHOD_DELETE;
                case kHead:
                    return HttpRequest.METHOD_HEAD;
                case kGet:
                    return HttpRequest.METHOD_GET;
                default:
                    return null;
            }
        }
    }

    public static class b implements c {
        public final void a(OutputStream outputStream) throws Exception {
        }

        public void a(ku kuVar, InputStream inputStream) throws Exception {
        }

        public void a(ku kuVar) {
        }
    }

    public final void a(String str, String str2) {
        this.a.a((Object) str, (Object) str2);
    }

    public final boolean b() {
        return !c() && d();
    }

    public final boolean c() {
        return this.p != null;
    }

    public final boolean d() {
        return this.q >= 200 && this.q < Game1MemoryGridActivity.START_ANIMATION_DURATION && !this.t;
    }

    public final List<String> a(String str) {
        return this.f.a((Object) str);
    }

    final void e() {
        if (this.l != null && !g()) {
            this.l.a(this);
        }
    }

    public final void f() {
        km.a(3, e, "Cancelling http request: " + this.g);
        synchronized (this.b) {
            this.x = true;
        }
        if (!this.w) {
            this.w = true;
            if (this.v != null) {
                new Thread(this) {
                    final /* synthetic */ ku a;

                    {
                        this.a = r1;
                    }

                    public final void run() {
                        try {
                            if (this.a.v != null) {
                                this.a.v.disconnect();
                            }
                        } catch (Throwable th) {
                        }
                    }
                }.start();
            }
        }
    }

    public final boolean g() {
        boolean z;
        synchronized (this.b) {
            z = this.x;
        }
        return z;
    }

    public void a() {
        try {
            if (this.g != null) {
                if (jr.a().b) {
                    if (this.h == null || a.kUnknown.equals(this.h)) {
                        this.h = a.kGet;
                    }
                    i();
                    km.a(4, e, "HTTP status: " + this.q + " for url: " + this.g);
                    this.y.a();
                    e();
                    return;
                }
                km.a(3, e, "Network not available, aborting http request: " + this.g);
                this.y.a();
                e();
            }
        } catch (Throwable e) {
            km.a(4, e, "HTTP status: " + this.q + " for url: " + this.g);
            km.a(3, e, "Exception during http request: " + this.g, e);
            this.d = this.v.getReadTimeout();
            this.c = this.v.getConnectTimeout();
            this.p = e;
        } finally {
            this.y.a();
            e();
        }
    }

    private void i() throws Exception {
        Closeable bufferedOutputStream;
        Throwable th;
        Closeable closeable = null;
        if (!this.x) {
            this.g = ly.a(this.g);
            this.v = (HttpURLConnection) new URL(this.g).openConnection();
            this.v.setConnectTimeout(this.i);
            this.v.setReadTimeout(this.j);
            this.v.setRequestMethod(this.h.toString());
            this.v.setInstanceFollowRedirects(this.k);
            this.v.setDoOutput(a.kPost.equals(this.h));
            this.v.setDoInput(true);
            for (Entry entry : this.a.b()) {
                this.v.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
            if (!(a.kGet.equals(this.h) || a.kPost.equals(this.h))) {
                this.v.setRequestProperty(HttpRequest.HEADER_ACCEPT_ENCODING, "");
            }
            if (this.x) {
                j();
                return;
            }
            Closeable outputStream;
            if (a.kPost.equals(this.h)) {
                try {
                    outputStream = this.v.getOutputStream();
                    try {
                        bufferedOutputStream = new BufferedOutputStream(outputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream = null;
                        closeable = outputStream;
                        ly.a(bufferedOutputStream);
                        ly.a(closeable);
                        throw th;
                    }
                    try {
                        if (!(this.l == null || g())) {
                            this.l.a((OutputStream) bufferedOutputStream);
                        }
                        ly.a(bufferedOutputStream);
                        ly.a(outputStream);
                    } catch (Throwable th3) {
                        th = th3;
                        closeable = outputStream;
                        ly.a(bufferedOutputStream);
                        ly.a(closeable);
                        throw th;
                    }
                } catch (Throwable th4) {
                    j();
                }
            }
            if (this.m) {
                this.n = System.currentTimeMillis();
            }
            if (this.r) {
                this.y.a((long) this.s);
            }
            this.q = this.v.getResponseCode();
            if (this.m && this.n != -1) {
                this.o = System.currentTimeMillis() - this.n;
            }
            this.y.a();
            for (Entry entry2 : this.v.getHeaderFields().entrySet()) {
                for (Object a : (List) entry2.getValue()) {
                    this.f.a(entry2.getKey(), a);
                }
            }
            if (!a.kGet.equals(this.h) && !a.kPost.equals(this.h)) {
                j();
            } else if (this.x) {
                j();
            } else {
                try {
                    bufferedOutputStream = this.v.getInputStream();
                    try {
                        outputStream = new BufferedInputStream(bufferedOutputStream);
                        try {
                            if (!(this.l == null || g())) {
                                this.l.a(this, outputStream);
                            }
                            ly.a(outputStream);
                            ly.a(bufferedOutputStream);
                            j();
                        } catch (Throwable th5) {
                            th = th5;
                            closeable = bufferedOutputStream;
                            bufferedOutputStream = outputStream;
                            ly.a(bufferedOutputStream);
                            ly.a(closeable);
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        Closeable closeable2 = bufferedOutputStream;
                        bufferedOutputStream = null;
                        closeable = closeable2;
                        ly.a(bufferedOutputStream);
                        ly.a(closeable);
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    bufferedOutputStream = null;
                    ly.a(bufferedOutputStream);
                    ly.a(closeable);
                    throw th;
                }
            }
        }
    }

    private void j() {
        if (!this.w) {
            this.w = true;
            if (this.v != null) {
                this.v.disconnect();
            }
        }
    }

    public final void h() {
        f();
    }
}
