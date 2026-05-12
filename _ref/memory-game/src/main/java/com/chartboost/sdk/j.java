package com.chartboost.sdk;

import android.os.CountDownTimer;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Libraries.g.k;
import com.chartboost.sdk.Libraries.h;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.ab;
import com.chartboost.sdk.impl.ac;
import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.v;
import com.chartboost.sdk.impl.w;
import com.chartboost.sdk.impl.x;
import com.chartboost.sdk.impl.y;
import com.chartboost.sdk.impl.z;
import com.facebook.internal.AnalyticsEvents;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class j {
    private final String a = j.class.getSimpleName();
    private final h b = new h(true);
    private final z c;
    private final ArrayList<b> d;
    private final ConcurrentHashMap<Integer, b> e = new ConcurrentHashMap();
    private a f = a.kCBIntial;
    private a g = a.kCBIntial;
    private final AtomicInteger h = new AtomicInteger();
    private final AtomicInteger i = new AtomicInteger();
    private boolean j = true;
    private boolean k = false;
    private boolean l = true;
    private final Observer m = new Observer(this) {
        final /* synthetic */ j a;

        {
            this.a = r1;
        }

        public void update(Observable observable, Object data) {
            this.a.g();
        }
    };
    private final com.chartboost.sdk.impl.ad.c n = new com.chartboost.sdk.impl.ad.c(this) {
        final /* synthetic */ j a;

        {
            this.a = r1;
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ad adVar) {
            try {
                synchronized (this.a) {
                    this.a.f = a.kCBIntial;
                    if (aVar.c()) {
                        this.a.a(aVar.a("videos"));
                    }
                }
            } catch (Exception e) {
                com.chartboost.sdk.Tracking.a.a(j.class, "videoRequestCallback onSuccess", e);
            }
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ad adVar, CBError cBError) {
            this.a.f = a.kCBIntial;
        }
    };

    public enum a {
        kCBIntial,
        kCBInProgress
    }

    private class b extends w<Object> {
        final /* synthetic */ j a;
        private final String e;
        private final long f = System.currentTimeMillis();
        private final String g;

        public b(j jVar, com.chartboost.sdk.impl.w.a aVar, String str, v vVar, String str2) {
            this.a = jVar;
            super(aVar, str, vVar);
            this.e = str2;
            this.g = str;
        }

        public Object a() {
            return Integer.valueOf(this.a.m.hashCode());
        }

        public void a(Object obj) {
        }

        public y<Object> a(ab abVar) {
            if (abVar != null) {
                if (abVar.a().length > 0) {
                    com.chartboost.sdk.Tracking.a.e(this.g, this.e, Long.valueOf((System.currentTimeMillis() - this.f) / 1000).toString());
                    CBLogging.a(this.a.a, "Video download Success. Storing video in cache " + this.a.b.e() + this.e);
                    this.a.b.a(this.a.b.e(), this.e, abVar.a());
                } else {
                    CBLogging.b(this.a.a, "Video downloaded content is empty");
                    com.chartboost.sdk.Tracking.a.c(this.g, this.e, "", "Video downloaded content is empty");
                }
            }
            synchronized (this.a) {
                this.a.b();
                if (this.a.h.incrementAndGet() == this.a.i.get()) {
                    CBLogging.a(this.a.a, "Video Prefetcher downloads completed");
                    this.a.h.set(0);
                    this.a.i.set(0);
                    this.a.g = a.kCBIntial;
                    this.a.e.clear();
                }
            }
            return y.b();
        }

        public com.chartboost.sdk.impl.w.b c() {
            return com.chartboost.sdk.impl.w.b.LOW;
        }

        public Map<String, String> b() {
            Map<String, String> hashMap = new HashMap();
            for (Entry entry : ad.b().entrySet()) {
                hashMap.put(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : null);
            }
            return hashMap;
        }
    }

    private class c implements v {
        final /* synthetic */ j a;
        private b b;

        private c(j jVar) {
            this.a = jVar;
        }

        public void a(x xVar) {
            this.a.b();
            if (xVar.b() || xVar.c() || xVar.d()) {
                if (this.b != null) {
                    com.chartboost.sdk.Tracking.a.c(this.b.g, this.b.e, Long.valueOf((System.currentTimeMillis() - this.b.f) / 1000).toString(), xVar.a());
                }
                this.a.e.put(Integer.valueOf(this.b.hashCode()), this.b);
                CBLogging.b(this.a.a, "Error downloading video " + xVar.a() + this.b.e);
            }
        }
    }

    public j(z zVar, ac acVar) {
        this.c = zVar;
        this.d = new ArrayList();
        acVar.addObserver(this.m);
    }

    public synchronized void a() {
        if (!c.M()) {
            CBLogging.a(this.a, "###### Native is disabled so not performing prefetch");
        } else if (c.w() && !c.S().booleanValue()) {
            CBLogging.a(this.a, "Native Prefetching the Video list");
            if (!(a.kCBInProgress == this.f || a.kCBInProgress == this.g)) {
                if (this.l) {
                    new CountDownTimer(this, (long) ((c.V() * 60) * 1000), 1000) {
                        final /* synthetic */ j a;

                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            CBLogging.a(this.a.a, "###### Native Prefetch Session expired");
                            this.a.l = true;
                        }
                    }.start();
                    this.l = false;
                    if (!(this.e == null || this.e.isEmpty())) {
                        this.e.clear();
                        this.c.a(Integer.valueOf(this.m.hashCode()));
                        this.g = a.kCBIntial;
                        CBLogging.a(this.a, "prefetchVideo: Clearing all volley request for new start");
                    }
                    this.f = a.kCBInProgress;
                    Object e = com.chartboost.sdk.impl.a.a().e();
                    if (c() != null) {
                        for (Object put : c()) {
                            e.put(put);
                        }
                    }
                    this.i.set(0);
                    this.h.set(0);
                    ad adVar = new ad("/api/video-prefetch");
                    adVar.a("local-videos", e);
                    k[] kVarArr = new k[2];
                    kVarArr[0] = g.a("status", com.chartboost.sdk.Libraries.a.a);
                    kVarArr[1] = g.a("videos", g.b(g.a(g.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, g.a(g.a())), g.a("id", g.a()))));
                    adVar.a(g.a(kVarArr));
                    adVar.b(true);
                    adVar.a(this.n);
                } else {
                    CBLogging.a(this.a, "Native Prefetch session is still active. Won't be making any new prefetch until the prefetch session expires");
                }
            }
        }
    }

    public synchronized void a(com.chartboost.sdk.Libraries.e.a aVar) {
        synchronized (this) {
            if (c.w()) {
                if (aVar.c()) {
                    HashMap hashMap = new HashMap();
                    for (int i = 0; i < aVar.p(); i++) {
                        com.chartboost.sdk.Libraries.e.a c = aVar.c(i);
                        if (!(c.b("id") || c.b(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO))) {
                            String e = c.e("id");
                            CharSequence e2 = c.e(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
                            if (!(TextUtils.isEmpty(e) || TextUtils.isEmpty(e2))) {
                                if (this.b.c(e)) {
                                    h.g(new File(this.b.e(), e));
                                } else {
                                    hashMap.put(e, e2);
                                    this.i.incrementAndGet();
                                }
                            }
                        }
                    }
                    if (this.j) {
                        this.j = false;
                    }
                    CBLogging.a(this.a, "Synchronizing videos with the list from the server");
                    if (!hashMap.isEmpty()) {
                        a(hashMap);
                        this.g = a.kCBInProgress;
                    }
                }
            }
        }
    }

    private synchronized void a(HashMap<String, String> hashMap) {
        for (String str : hashMap.keySet()) {
            Object cVar = new c();
            b bVar = new b(this, com.chartboost.sdk.impl.w.a.a, (String) hashMap.get(str), cVar, str);
            cVar.b = bVar;
            com.chartboost.sdk.Tracking.a.a((String) hashMap.get(str), str);
            this.d.add(bVar);
            CBLogging.a(this.a, "Downloading video:" + ((String) hashMap.get(str)));
        }
        if (this.k) {
            CBLogging.a(this.a, "##### Video Download is put on hold, it seems an ad is shown, it will resume once the ad is closed");
        } else if (!this.d.isEmpty()) {
            this.c.a((w) this.d.remove(0));
        }
    }

    public void b() {
        if (!this.k && !this.d.isEmpty()) {
            CBLogging.a(this.a, "##### Flushing out next request to download");
            this.c.a((w) this.d.remove(0));
        }
    }

    public String[] c() {
        return this.b.c(this.b.e());
    }

    public synchronized void d() {
        this.c.a(Integer.valueOf(this.m.hashCode()));
    }

    public String a(String str) {
        if (this.b.c(str)) {
            return this.b.c(this.b.e(), str).getPath();
        }
        return null;
    }

    public String b(com.chartboost.sdk.Libraries.e.a aVar) {
        if (aVar == null) {
            return null;
        }
        com.chartboost.sdk.Libraries.e.a a = aVar.a("assets");
        if (a.b()) {
            return null;
        }
        com.chartboost.sdk.Libraries.e.a a2 = a.a(CBUtility.a().a() ? "video-portrait" : "video-landscape");
        if (a2.b()) {
            return null;
        }
        String e = a2.e("id");
        if (TextUtils.isEmpty(e)) {
            return null;
        }
        return a(e);
    }

    public boolean c(com.chartboost.sdk.Libraries.e.a aVar) {
        return !com.chartboost.sdk.impl.a.a().a(b(aVar));
    }

    public void e() {
        if (!this.k) {
            CBLogging.a(this.a, "##### Pause Video Downloads if its in progress.");
            CBLogging.a(this.a, "##### Current Queue size: " + this.d.size());
            this.k = true;
        }
    }

    public void f() {
        if (this.k) {
            CBLogging.a(this.a, "##### Resume video download if its in progress");
            CBLogging.a(this.a, "##### Current Queue size: " + this.d.size());
            this.k = false;
            b();
        }
    }

    private synchronized void g() {
        CBLogging.a(this.a, "Process Request called");
        if (!(this.f == a.kCBInProgress || this.g == a.kCBInProgress)) {
            if ((this.g == a.kCBIntial && this.e != null) || this.e.size() > 0) {
                for (Integer num : this.e.keySet()) {
                    this.g = a.kCBInProgress;
                    this.c.a((w) this.e.get(num));
                    this.e.remove(num);
                }
            }
        }
    }
}
