package com.flurry.sdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class kx<ReportInfo extends kw> {
    private static final String a = kx.class.getSimpleName();
    public static long b = 10000;
    public boolean c;
    public long d = b;
    private final int e = Integer.MAX_VALUE;
    private final kf<List<ReportInfo>> f;
    private final List<ReportInfo> g = new ArrayList();
    private int h;
    private final Runnable i = new ma(this) {
        final /* synthetic */ kx a;

        {
            this.a = r1;
        }

        public final void a() {
            this.a.b();
        }
    };
    private final kh<jq> j = new kh<jq>(this) {
        final /* synthetic */ kx a;

        {
            this.a = r1;
        }

        public final /* bridge */ /* synthetic */ void a(kg kgVar) {
            if (((jq) kgVar).a) {
                this.a.b();
            }
        }
    };

    public abstract kf<List<ReportInfo>> a();

    public abstract void a(ReportInfo reportInfo);

    public kx() {
        ki.a().a("com.flurry.android.sdk.NetworkStateEvent", this.j);
        this.f = a();
        this.d = b;
        this.h = -1;
        jy.a().b(new ma(this) {
            final /* synthetic */ kx a;

            {
                this.a = r1;
            }

            public final void a() {
                this.a.a(this.a.g);
                this.a.b();
            }
        });
    }

    public synchronized void a(List<ReportInfo> list) {
        ly.b();
        List list2 = (List) this.f.a();
        if (list2 != null) {
            list.addAll(list2);
        }
    }

    private synchronized void b() {
        if (!this.c) {
            if (this.h >= 0) {
                km.a(3, a, "Transmit is in progress");
            } else {
                d();
                if (this.g.isEmpty()) {
                    this.d = b;
                    this.h = -1;
                } else {
                    this.h = 0;
                    jy.a().b(new ma(this) {
                        final /* synthetic */ kx a;

                        {
                            this.a = r1;
                        }

                        public final void a() {
                            this.a.e();
                        }
                    });
                }
            }
        }
    }

    private synchronized void d() {
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            kw kwVar = (kw) it.next();
            if (kwVar.o) {
                km.a(3, a, "Url transmitted - " + kwVar.q + " Attempts: " + kwVar.p);
                it.remove();
            } else if (kwVar.p > kwVar.a()) {
                km.a(3, a, "Exceeded max no of attempts - " + kwVar.q + " Attempts: " + kwVar.p);
                it.remove();
            } else if (System.currentTimeMillis() > kwVar.n && kwVar.p > 0) {
                km.a(3, a, "Expired: Time expired - " + kwVar.q + " Attempts: " + kwVar.p);
                it.remove();
            }
        }
    }

    private synchronized void e() {
        kw kwVar;
        ly.b();
        if (jr.a().b) {
            while (this.h < this.g.size()) {
                List list = this.g;
                int i = this.h;
                this.h = i + 1;
                kwVar = (kw) list.get(i);
                if (!kwVar.o) {
                    break;
                }
            }
            kwVar = null;
        } else {
            km.a(3, a, "Network is not available, aborting transmission");
            kwVar = null;
        }
        if (kwVar == null) {
            f();
        } else {
            a(kwVar);
        }
    }

    private synchronized void f() {
        d();
        b(this.g);
        if (this.c) {
            km.a(3, a, "Reporter paused");
            this.d = b;
        } else if (this.g.isEmpty()) {
            km.a(3, a, "All reports sent successfully");
            this.d = b;
        } else {
            this.d <<= 1;
            km.a(3, a, "One or more reports failed to send, backing off: " + this.d + "ms");
            jy.a().a(this.i, this.d);
        }
        this.h = -1;
    }

    public synchronized void b(List<ReportInfo> list) {
        ly.b();
        this.f.a(new ArrayList(list));
    }

    public final void c() {
        this.c = false;
        jy.a().b(new ma(this) {
            final /* synthetic */ kx a;

            {
                this.a = r1;
            }

            public final void a() {
                this.a.b();
            }
        });
    }

    public final synchronized void b(ReportInfo reportInfo) {
        if (reportInfo != null) {
            this.g.add(reportInfo);
            jy.a().b(new ma(this) {
                final /* synthetic */ kx a;

                {
                    this.a = r1;
                }

                public final void a() {
                    this.a.b();
                }
            });
        }
    }

    public final synchronized void c(ReportInfo reportInfo) {
        reportInfo.o = true;
        jy.a().b(new ma(this) {
            final /* synthetic */ kx a;

            {
                this.a = r1;
            }

            public final void a() {
                this.a.e();
            }
        });
    }

    public final synchronized void d(ReportInfo reportInfo) {
        reportInfo.a_();
        jy.a().b(new ma(this) {
            final /* synthetic */ kx a;

            {
                this.a = r1;
            }

            public final void a() {
                this.a.e();
            }
        });
    }
}
