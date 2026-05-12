package com.flurry.sdk;

import com.cube.memorygames.games.Game1MemoryGridActivity;
import com.flurry.sdk.ku.a;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;

public class io extends kx<ip> {
    public static long a;
    private static final String e = io.class.getSimpleName();

    protected final /* synthetic */ void a(kw kwVar) {
        final ip ipVar = (ip) kwVar;
        km.a(3, e, "Sending next pulse report to " + ipVar.l + " at: " + ipVar.r);
        jk.a();
        long d = jk.d();
        if (d == 0) {
            d = a;
        }
        jk.a();
        long g = jk.g();
        if (g == 0) {
            g = System.currentTimeMillis() - d;
        }
        final iq iqVar = new iq(ipVar, d, g, ipVar.p);
        mb ksVar = new ks();
        ksVar.g = ipVar.r;
        ksVar.u = 100000;
        if (ipVar.d.equals(iw.POST)) {
            ksVar.c = new lc();
            if (ipVar.k != null) {
                ksVar.b = ipVar.k.getBytes();
            }
            ksVar.h = a.kPost;
        } else {
            ksVar.h = a.kGet;
        }
        ksVar.i = ipVar.i * 1000;
        ksVar.j = ipVar.j * 1000;
        ksVar.m = true;
        ksVar.r = true;
        ksVar.s = (ipVar.i + ipVar.j) * 1000;
        Map map = ipVar.e;
        if (map != null) {
            for (String str : ipVar.e.keySet()) {
                ksVar.a(str, (String) map.get(str));
            }
        }
        ksVar.k = false;
        ksVar.a = new ks.a<byte[], String>(this) {
            final /* synthetic */ io c;

            public final /* synthetic */ void a(ks ksVar, Object obj) {
                Object obj2 = null;
                String str = (String) obj;
                km.a(3, io.e, "Pulse report to " + ipVar.l + " for " + ipVar.g.c + ", HTTP status code is: " + ksVar.q);
                int i = ksVar.q;
                iq iqVar = iqVar;
                int i2 = (int) ksVar.o;
                if (i2 >= 0) {
                    iqVar.k = ((long) i2) + iqVar.k;
                } else if (iqVar.k <= 0) {
                    iqVar.k = 0;
                }
                iqVar.e = i;
                if (!ksVar.b()) {
                    Exception exception = ksVar.p;
                    Object obj3;
                    if (ksVar.p == null || !(ksVar.p instanceof SocketTimeoutException)) {
                        obj3 = null;
                    } else {
                        obj3 = 1;
                    }
                    if (ksVar.t || r0 != null) {
                        obj2 = 1;
                    }
                    if (obj2 != null) {
                        if (ksVar.c()) {
                            km.a(3, io.e, "Timeout occured when trying to connect to: " + ipVar.l + ". Exception: " + ksVar.p.getMessage());
                        } else {
                            km.a(3, io.e, "Manually managed http request timeout occured for: " + ipVar.l);
                        }
                        io.a(this.c, iqVar, ipVar);
                        return;
                    }
                    km.a(3, io.e, "Error occured when trying to connect to: " + ipVar.l + ". Exception: " + exception.getMessage());
                    io.a(this.c, iqVar, ipVar, str);
                } else if (i >= 200 && i < 300) {
                    io.b(this.c, iqVar, ipVar);
                } else if (i < 300 || i >= Game1MemoryGridActivity.START_ANIMATION_DURATION) {
                    km.a(3, io.e, ipVar.g.c + " report failed sending to : " + ipVar.l);
                    io.a(this.c, iqVar, ipVar, str);
                } else {
                    io.a(this.c, iqVar, ipVar, ksVar);
                }
            }
        };
        jw.a().a((Object) this, ksVar);
    }

    public io() {
        kx.b = 30000;
    }

    protected final kf<List<ip>> a() {
        return new kf(jy.a().a.getFileStreamPath(".yflurryanpulsecallbackreporter"), ".yflurryanpulsecallbackreporter", 2, new lj<List<ip>>(this) {
            final /* synthetic */ io a;

            {
                this.a = r1;
            }

            public final lg<List<ip>> a(int i) {
                return new lf(new ip.a());
            }
        });
    }

    protected final synchronized void a(List<ip> list) {
        is.c();
        List<it> d = is.d();
        if (d != null) {
            if (d.size() != 0) {
                km.a(3, e, "Restoring " + d.size() + " from report queue.");
                for (it b : d) {
                    is.c().b(b);
                }
                is.c();
                for (it a : is.b()) {
                    for (ip ipVar : a.a()) {
                        if (!ipVar.m) {
                            km.a(3, e, "Callback for " + ipVar.g.c + " to " + ipVar.l + " not completed.  Adding to reporter queue.");
                            list.add(ipVar);
                        }
                    }
                }
            }
        }
    }

    protected final synchronized void b(List<ip> list) {
        is.c().a();
    }

    static /* synthetic */ void a(io ioVar, iq iqVar, ip ipVar) {
        is.c().b(iqVar);
        ioVar.c((kw) ipVar);
    }

    static /* synthetic */ void a(io ioVar, iq iqVar, ip ipVar, String str) {
        boolean b = is.c().b(iqVar, str);
        km.a(3, e, "Failed report retrying: " + b);
        if (b) {
            ioVar.d(ipVar);
        } else {
            ioVar.c((kw) ipVar);
        }
    }

    static /* synthetic */ void b(io ioVar, iq iqVar, ip ipVar) {
        km.a(3, e, ipVar.g.c + " report sent successfully to : " + ipVar.l);
        is.c().a(iqVar);
        ioVar.c((kw) ipVar);
    }

    static /* synthetic */ void a(io ioVar, iq iqVar, ip ipVar, ks ksVar) {
        String str = null;
        List a = ksVar.a("Location");
        if (a != null && a.size() > 0) {
            str = mc.b((String) a.get(0), ipVar.q);
        }
        boolean a2 = is.c().a(iqVar, str);
        if (a2) {
            km.a(3, e, "Received redirect url. Retrying: " + str);
        } else {
            km.a(3, e, "Received redirect url. Retrying: false");
        }
        if (a2) {
            ipVar.r = str;
            ksVar.g = str;
            str = "Location";
            if (ksVar.f != null && ksVar.f.a.containsKey(str)) {
                ksVar.f.b(str);
            }
            jw.a().a((Object) ioVar, (mb) ksVar);
            return;
        }
        ioVar.c((kw) ipVar);
    }
}
