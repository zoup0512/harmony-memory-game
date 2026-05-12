package com.my.target.core.async.commands;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.my.target.Tracer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: AbstractAsyncCommand */
abstract class a<T> implements b<T> {
    private static final ExecutorService e = Executors.newSingleThreadExecutor();
    private static final ExecutorService f = Executors.newSingleThreadExecutor();
    private static final Handler g = new Handler(Looper.getMainLooper());
    protected boolean a;
    final Context b;
    T c;
    String d;
    private com.my.target.core.async.commands.b.a<T> h;

    protected abstract void c();

    public final synchronized void a(com.my.target.core.async.commands.b.a<T> aVar) {
        this.h = aVar;
    }

    public String a() {
        return this.d;
    }

    a(Context context) {
        this.b = context.getApplicationContext();
    }

    a(Context context, byte b) {
        this.b = context.getApplicationContext();
        this.a = true;
    }

    public final void run() {
        c();
        synchronized (this) {
            if (this.h != null) {
                g.post(new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public final void run() {
                        synchronized (this.a) {
                            if (this.a.h != null) {
                                this.a.h.a(this.a, this.a.c);
                            }
                        }
                    }
                });
            }
        }
    }

    public final void b() {
        Tracer.d("add command to " + (this.a ? "low priority" : "main") + " executor");
        if (this.a) {
            f.execute(this);
        } else {
            e.execute(this);
        }
    }
}
