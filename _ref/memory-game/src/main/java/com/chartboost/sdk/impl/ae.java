package com.chartboost.sdk.impl;

import android.os.CountDownTimer;
import android.text.TextUtils;
import com.amazonaws.services.s3.internal.Constants;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.h;
import com.chartboost.sdk.Model.CBError;
import com.facebook.GraphResponse;
import com.facebook.share.internal.ShareConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;

public class ae implements Observer {
    private static ConcurrentHashMap<ad, File> d;
    private static final List<Runnable> f = new ArrayList();
    private final ac a;
    private final z b;
    private final h c = new h(false);
    private final ConcurrentHashMap<String, b> e;
    private CountDownTimer g;

    public enum a {
        ARRAY_OF_DICTIONARY
    }

    private class b {
        final /* synthetic */ ae a;
        private String b;
        private final int c;
        private String d;
        private final a e;
        private boolean f;
        private JSONArray g;
        private ad h;

        public b(ae aeVar) {
            this.a = aeVar;
            this.b = null;
            this.c = 50;
            this.f = false;
            this.g = null;
            this.d = Long.toString(System.nanoTime());
            this.e = a.ARRAY_OF_DICTIONARY;
            this.g = new JSONArray();
        }

        public String a() {
            return this.d;
        }

        public void a(String str) {
            this.b = str;
        }

        public String b() {
            return this.b;
        }

        public boolean c() {
            return this.f;
        }

        public void a(boolean z) {
            this.f = z;
        }

        public void d() {
            this.g = new JSONArray();
        }

        public synchronized ad a(ad adVar) {
            com.chartboost.sdk.Libraries.e.a j = adVar.j();
            if (j.c()) {
                j = j.a(this.b);
                if (!j.b()) {
                    if (this.e == a.ARRAY_OF_DICTIONARY) {
                        if (this.a.a.b() || (this.h != null && this.h.r())) {
                            this.d = Long.toString(System.nanoTime());
                            adVar.a(this.b, new JSONArray().put(j.e()));
                        } else {
                            int length = this.g.length();
                            getClass();
                            if (length == 50) {
                                this.d = Long.toString(System.nanoTime());
                                this.g = new JSONArray();
                            }
                            this.g.put(j.e());
                            if (this.h != null) {
                                ae.d.remove(this.h);
                            }
                            adVar.a(this.b, this.g);
                            this.h = adVar;
                            adVar = this.h;
                        }
                    }
                }
            }
            return adVar;
        }

        public void b(ad adVar) {
            this.h = adVar;
        }
    }

    public static class c extends RuntimeException {
        private final CBError a;

        public c(CBError cBError) {
            this.a = cBError;
        }
    }

    public static class d {
        private final com.chartboost.sdk.Libraries.e.a a;
        private final ab b;

        public d(com.chartboost.sdk.Libraries.e.a aVar, ab abVar) {
            this.a = aVar;
            this.b = abVar;
        }
    }

    private class e implements Runnable {
        final /* synthetic */ ae a;
        private final ad b;

        private class a extends w<d> {
            final /* synthetic */ e a;
            private final ad e;

            public a(e eVar, com.chartboost.sdk.impl.w.a aVar, String str, ad adVar) {
                this.a = eVar;
                super(aVar, str, null);
                this.e = adVar;
            }

            public String d() {
                String c = this.e.c();
                if (c == null) {
                    return "application/json; charset=utf-8";
                }
                return c;
            }

            public byte[] e() {
                return (this.e.j() == null ? "" : this.e.j().toString()).getBytes();
            }

            public com.chartboost.sdk.impl.w.b c() {
                return this.e.o();
            }

            public Map<String, String> b() {
                Map<String, String> hashMap = new HashMap();
                for (Entry entry : this.e.k().entrySet()) {
                    hashMap.put(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : null);
                }
                return hashMap;
            }

            public y<d> a(ab abVar) {
                CBError cBError = null;
                com.chartboost.sdk.Libraries.e.a aVar = com.chartboost.sdk.Libraries.e.a.a;
                int b = abVar.b();
                if (b <= 300 || b >= 200) {
                    try {
                        String str;
                        com.chartboost.sdk.Libraries.e.a aVar2;
                        CBError cBError2;
                        com.chartboost.sdk.Libraries.e.a aVar3;
                        byte[] a = abVar.a();
                        if (a != null) {
                            str = new String(a);
                        } else {
                            str = null;
                        }
                        if (str != null) {
                            Object a2 = a.a().a(str);
                            aVar = com.chartboost.sdk.Libraries.e.a.a(a2);
                            com.chartboost.sdk.Libraries.g.a m = this.e.m();
                            CBLogging.c("CBRequestManager", "Request " + this.e.h() + " succeeded. Response code: " + b + ", body: " + a2.toString(4));
                            if (aVar.f("status") == Constants.NO_SUCH_BUCKET_STATUS_CODE) {
                                cBError = new CBError(com.chartboost.sdk.Model.CBError.a.HTTP_NOT_FOUND, "404 error from server");
                            } else {
                                StringBuilder stringBuilder = new StringBuilder();
                                if (!(m == null || m.a(aVar, stringBuilder))) {
                                    cBError = new CBError(com.chartboost.sdk.Model.CBError.a.UNEXPECTED_RESPONSE, "Json response failed validation");
                                    CBLogging.b("CBRequestManager", "Json response failed validation: " + stringBuilder.toString());
                                }
                            }
                            aVar2 = aVar;
                            cBError2 = cBError;
                            aVar3 = aVar2;
                        } else {
                            aVar2 = aVar;
                            cBError2 = new CBError(com.chartboost.sdk.Model.CBError.a.INVALID_RESPONSE, "Response is not a valid json object");
                            aVar3 = aVar2;
                        }
                        aVar2 = aVar3;
                        cBError = cBError2;
                        aVar = aVar2;
                    } catch (Exception e) {
                        Exception exception = e;
                        cBError = new CBError(com.chartboost.sdk.Model.CBError.a.MISCELLANEOUS, exception.getLocalizedMessage());
                        com.chartboost.sdk.Tracking.a.a(getClass(), "parseServerResponse", exception);
                    }
                } else {
                    CBLogging.b("CBRequestManager", "Request " + this.e.h() + " failed. Response code: " + b);
                    cBError = new CBError(com.chartboost.sdk.Model.CBError.a.NETWORK_FAILURE, "Request failed. Response code: " + b + " is not valid ");
                }
                if (r1.c() && cBError == null) {
                    return y.a(new d(com.chartboost.sdk.Libraries.e.a.a(r1), abVar));
                }
                return y.a(new c(cBError));
            }

            public void a(d dVar) {
                if (!(this.a.b.s() == null || dVar == null)) {
                    this.a.b.s().a(dVar.a, this.a.b);
                }
                if (!this.a.b.i()) {
                    this.a.a.c.e((File) ae.d.get(this.a.b));
                    ae.d.remove(this.a.b);
                    b bVar = (b) this.a.a.e.get(this.a.b.h());
                    if (bVar != null && !TextUtils.isEmpty(bVar.b()) && bVar.c() && bVar.h == this.a.b) {
                        bVar.d();
                        bVar.b(null);
                    }
                    this.a.b.d(false);
                    this.a.a.a(this.a.b, dVar.b, null, true);
                }
            }

            public void a(x xVar) {
                if (!(this.a.b == null || com.chartboost.sdk.c.n() || this.a.b.i() || !ae.d.containsKey(this.a.b))) {
                    this.a.a.c.e((File) ae.d.get(this.a.b));
                    ae.d.remove(this.a.b);
                }
                if (xVar != null) {
                    CBError a;
                    if (xVar.b instanceof c) {
                        a = ((c) xVar.b).a;
                    } else if (xVar.b == null || !(xVar.b.getCause() instanceof c)) {
                        a = new CBError(com.chartboost.sdk.Model.CBError.a.NETWORK_FAILURE, xVar.a());
                    } else {
                        a = ((c) xVar.b.getCause()).a;
                    }
                    com.chartboost.sdk.Libraries.e.a aVar = com.chartboost.sdk.Libraries.e.a.a;
                    try {
                        if (!(xVar.a == null || xVar.a.a() == null || xVar.a.a().length <= 0)) {
                            aVar = com.chartboost.sdk.Libraries.e.a.k(new String(xVar.a.a()));
                        }
                    } catch (Throwable e) {
                        CBLogging.d("CBRequestManager", "unable to read error json", e);
                    }
                    if (xVar.a == null || xVar.a.b() != 200) {
                        if (this.a.b.s() != null) {
                            this.a.b.s().a(aVar, this.a.b, a);
                        }
                        if (!this.a.b.i()) {
                            this.a.b.d(false);
                            this.a.a.a(this.a.b, xVar.a, a, false);
                            return;
                        }
                        return;
                    }
                    a(new d(aVar, xVar.a));
                }
            }
        }

        public e(ae aeVar, ad adVar) {
            this.a = aeVar;
            this.b = adVar;
        }

        public void run() {
            this.b.d();
            this.b.e();
            String format = String.format("%s%s", new Object[]{"https://live.chartboost.com", this.b.f()});
            this.b.a();
            this.a.b.a(new a(this, com.chartboost.sdk.impl.w.a.b, format, this.b));
        }
    }

    public ae(z zVar, ac acVar) {
        this.b = zVar;
        this.a = acVar;
        d = new ConcurrentHashMap();
        this.e = new ConcurrentHashMap();
        i();
        this.a.addObserver(this);
    }

    private void a(ad adVar, ab abVar, CBError cBError, boolean z) {
        if (adVar != null) {
            String str;
            com.chartboost.sdk.Libraries.e.b[] bVarArr = new com.chartboost.sdk.Libraries.e.b[5];
            bVarArr[0] = com.chartboost.sdk.Libraries.e.a("endpoint", adVar.h());
            bVarArr[1] = com.chartboost.sdk.Libraries.e.a("statuscode", abVar == null ? "None" : Integer.valueOf(abVar.b()));
            bVarArr[2] = com.chartboost.sdk.Libraries.e.a("error", cBError == null ? "None" : cBError.a());
            bVarArr[3] = com.chartboost.sdk.Libraries.e.a("errorDescription", cBError == null ? "None" : cBError.b());
            bVarArr[4] = com.chartboost.sdk.Libraries.e.a("retryCount", Integer.valueOf(adVar.p()));
            com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a(bVarArr);
            String str2 = "request_manager";
            String str3 = ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID;
            if (z) {
                str = GraphResponse.SUCCESS_KEY;
            } else {
                str = "failure";
            }
            com.chartboost.sdk.Tracking.a.a(str2, str3, str, null, null, null, a.e());
        }
    }

    protected void a(ad adVar, com.chartboost.sdk.impl.ad.c cVar) {
        if (adVar != null) {
            if (this.a.b()) {
                if (!adVar.i() && adVar.q()) {
                    adVar.c(false);
                    a(adVar);
                }
                a(new e(this, adVar));
                return;
            }
            CBError cBError = new CBError(com.chartboost.sdk.Model.CBError.a.INTERNET_UNAVAILABLE, "Internet Unavailable");
            adVar.d(false);
            if (!adVar.i()) {
                if (adVar.q()) {
                    adVar.c(false);
                    a(adVar);
                }
                a(adVar, null, cBError, false);
                if (cVar != null) {
                    CBLogging.b("Network failure", String.format("request %s failed with error : %s", new Object[]{adVar.h(), cBError.b()}));
                    cVar.a(com.chartboost.sdk.Libraries.e.a.a, adVar, cBError);
                }
            }
        }
    }

    public void a(Runnable runnable) {
        Object obj = null;
        synchronized (com.chartboost.sdk.Libraries.c.class) {
            com.chartboost.sdk.Libraries.c.a c = com.chartboost.sdk.Libraries.c.c();
            if (c == com.chartboost.sdk.Libraries.c.a.PRELOAD || c == com.chartboost.sdk.Libraries.c.a.LOADING) {
                f.add(runnable);
            } else {
                obj = 1;
            }
        }
        if (obj != null) {
            runnable.run();
        }
    }

    public static void a() {
        List<Runnable> arrayList = new ArrayList();
        synchronized (com.chartboost.sdk.Libraries.c.class) {
            arrayList.addAll(f);
            f.clear();
        }
        for (Runnable run : arrayList) {
            run.run();
        }
    }

    public synchronized void b() {
        synchronized (this) {
            if (d != null && !d.isEmpty()) {
                for (ad adVar : d.keySet()) {
                    if (!(adVar == null || adVar.r())) {
                        adVar.a(adVar.p() + 1);
                        adVar.a(adVar.s());
                    }
                }
                d();
            } else if (this.c.f() != null) {
                String[] list = this.c.f().list();
                if (list != null) {
                    for (String str : list) {
                        ad a = a(str);
                        if (a != null) {
                            d.put(a, this.c.c(this.c.f(), str));
                            a.c(false);
                            a.a(a.p() + 1);
                            a.a(a.s());
                        }
                    }
                }
                d();
            }
        }
    }

    public synchronized void c() {
        try {
            String[] c;
            if (this.c != null) {
                c = this.c.c(this.c.f());
            } else {
                c = null;
            }
            if (c != null && c.length > 0) {
                for (String str : c) {
                    com.chartboost.sdk.Libraries.e.a a = this.c.a(this.c.f(), str);
                    if (a.c()) {
                        this.c.b(this.c.f(), str);
                        ad a2 = ad.a(a);
                        if (a2 != null) {
                            a2.a(true);
                            a2.t();
                        } else {
                            CBLogging.b("CBRequestManager", "Error processing video completion event");
                        }
                    }
                }
            }
        } catch (Exception e) {
            CBLogging.b("CBRequestManager", "Error executing saved requests", e);
            com.chartboost.sdk.Tracking.a.a(getClass(), "flushVideoCompletionEvents", e);
        }
        return;
    }

    private void a(ad adVar) {
        if (adVar != null) {
            Object a;
            if (adVar.l()) {
                b bVar = (b) this.e.get(adVar.h());
                if (bVar == null || TextUtils.isEmpty(bVar.b()) || !bVar.c()) {
                    a = this.c.a(this.c.f(), null, adVar.u());
                } else {
                    adVar = bVar.a(adVar);
                    a = this.c.a(this.c.f(), bVar.a(), adVar.u());
                }
            } else {
                a = null;
            }
            if ((adVar.l() || adVar.n()) && a != null) {
                d.put(adVar, a);
            }
        }
    }

    private ad a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        com.chartboost.sdk.Libraries.e.a a = this.c.a(this.c.f(), str);
        if (a.c()) {
            return ad.a(a);
        }
        return null;
    }

    public void d() {
        if (this.g == null) {
            this.g = new CountDownTimer(this, 240000, 1000) {
                final /* synthetic */ ae a;

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    this.a.b();
                }
            }.start();
        }
    }

    public void e() {
        CBLogging.a("CBRequestManager", "Timer stopped:");
        if (this.g != null) {
            this.g.cancel();
            this.g = null;
        }
    }

    public void update(Observable observable, Object data) {
        if (this.g != null) {
            e();
        }
        b();
    }

    public ConcurrentHashMap<ad, File> f() {
        return d;
    }

    public h g() {
        return this.c;
    }

    private void i() {
        b bVar = new b(this);
        bVar.a("track_info");
        bVar.a(true);
        this.e.put("/post-install-event/".concat("tracking"), bVar);
    }
}
