package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.yandex.metrica.IMetricaService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class au implements a {
    private final s a;
    private BlockingQueue<b> b = new LinkedBlockingQueue();
    private final ac c;
    private final c d;

    public interface a {
        h a(h hVar);
    }

    public static class b {
        private h a;
        private ar b;
        private boolean c = false;
        private a d;

        b(h hVar, ar arVar) {
            this.a = hVar;
            this.b = arVar;
        }

        b a(a aVar) {
            this.d = aVar;
            return this;
        }

        b a(boolean z) {
            this.c = z;
            return this;
        }

        ar a() {
            return this.b;
        }

        h b() {
            return this.d != null ? this.d.a(this.a) : this.a;
        }
    }

    private class c extends Thread {
        final /* synthetic */ au a;

        private c(au auVar) {
            this.a = auVar;
        }

        public void run() {
            IMetricaService e;
            while (!isInterrupted()) {
                b bVar;
                try {
                    bVar = (b) this.a.b.take();
                    try {
                        if (bVar.c) {
                            this.a.c.b();
                        }
                    } catch (InterruptedException e2) {
                        interrupt();
                        while (bVar != null) {
                            e = this.a.c.e();
                            if (e != null) {
                                if (bVar.c) {
                                    this.a.c.a();
                                    a();
                                } else {
                                    au.a(this.a, bVar);
                                    bVar = null;
                                }
                            } else if (!this.a.a(e, bVar)) {
                                bVar = null;
                            }
                        }
                    }
                } catch (InterruptedException e3) {
                    bVar = null;
                    interrupt();
                    while (bVar != null) {
                        e = this.a.c.e();
                        if (e != null) {
                            if (!this.a.a(e, bVar)) {
                                bVar = null;
                            }
                        } else if (bVar.c) {
                            au.a(this.a, bVar);
                            bVar = null;
                        } else {
                            this.a.c.a();
                            a();
                        }
                    }
                }
                while (bVar != null && !isInterrupted()) {
                    e = this.a.c.e();
                    if (e != null) {
                        if (!this.a.a(e, bVar)) {
                            bVar = null;
                        }
                    } else if (bVar.c) {
                        au.a(this.a, bVar);
                        bVar = null;
                    } else {
                        this.a.c.a();
                        a();
                    }
                }
            }
        }

        private synchronized void a() {
            if (!this.a.c.d()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
        }
    }

    public au(s sVar) {
        this.a = sVar;
        this.d = new c();
        this.c = sVar.a();
        this.c.a((a) this);
        this.d.start();
    }

    public void a(b bVar) {
        this.b.offer(bVar);
    }

    private boolean a(IMetricaService iMetricaService, b bVar) {
        try {
            this.a.a(iMetricaService, bVar.b(), bVar.b);
            return true;
        } catch (RemoteException e) {
            this.c.a();
            return false;
        }
    }

    public void c() {
        synchronized (this.d) {
            this.d.notifyAll();
        }
    }

    public void d() {
    }

    static /* synthetic */ void a(au auVar, b bVar) {
        Context b = auVar.a.b();
        Intent c = ba.c(b);
        c.putExtras(bVar.a.a(bVar.b.c()));
        b.startService(c);
    }
}
