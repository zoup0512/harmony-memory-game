package com.chartboost.sdk;

import android.os.CountDownTimer;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Libraries.g.k;
import com.chartboost.sdk.Libraries.h;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.ab;
import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.ah;
import com.chartboost.sdk.impl.bj;
import com.chartboost.sdk.impl.v;
import com.chartboost.sdk.impl.w;
import com.chartboost.sdk.impl.x;
import com.chartboost.sdk.impl.y;
import com.chartboost.sdk.impl.z;
import com.facebook.internal.AnalyticsEvents;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.mopub.common.AdType;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import org.json.JSONArray;

public class b {
    private static final String b = b.class.getSimpleName();
    private boolean A = false;
    private final com.chartboost.sdk.impl.ad.c B = new com.chartboost.sdk.impl.ad.c(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ad adVar) {
            this.a.e = b.kCBInitial;
            try {
                if (aVar.c()) {
                    com.chartboost.sdk.Libraries.e.a a = aVar.a("cache_assets");
                    CBLogging.a(b.b, "Got Asset list for Web Prefetch from server :)" + aVar);
                    this.a.a(e.Low, a);
                }
            } catch (Exception e) {
                com.chartboost.sdk.Tracking.a.a(getClass(), "onSuccess", e);
            }
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ad adVar, CBError cBError) {
            try {
                this.a.e = b.kCBInitial;
            } catch (Exception e) {
                com.chartboost.sdk.Tracking.a.a(getClass(), "onFailure", e);
            }
        }
    };
    public final ConcurrentHashMap<com.chartboost.sdk.Model.a, e> a;
    private final h c = new h(true);
    private final z d;
    private b e = b.kCBInitial;
    private b f = b.kCBInitial;
    private final AtomicInteger g = new AtomicInteger();
    private final AtomicInteger h = new AtomicInteger();
    private ConcurrentHashMap<String, a> i;
    private ConcurrentHashMap<String, JSONArray> j;
    private final ConcurrentHashMap<String, String> k;
    private final ArrayList<String> l;
    private final ConcurrentHashMap<String, Integer> m;
    private com.chartboost.sdk.Libraries.e.a n;
    private final ArrayList<c> o = new ArrayList();
    private final ArrayList<String> p = new ArrayList();
    private HashMap<String, File> q;
    private HashMap<String, String> r;
    private e s;
    private com.chartboost.sdk.Libraries.e.a t;
    private final ArrayList<com.chartboost.sdk.Libraries.e.a> u;
    private final ArrayList<com.chartboost.sdk.Libraries.e.a> v;
    private final ArrayList<com.chartboost.sdk.Libraries.e.a> w;
    private final HashMap<e, ArrayList<com.chartboost.sdk.Libraries.e.a>> x;
    private final Object y = new Object();
    private boolean z = true;

    private static class a {
        public final String a;
        public final String b;
        public final String c;
        public final String d;
        public final com.chartboost.sdk.Libraries.e.a e;
        public final ArrayList<String> f = new ArrayList();
        public final boolean g;

        public a(String str, String str2, String str3, String str4, com.chartboost.sdk.Libraries.e.a aVar) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = aVar;
            this.f.add(this.a);
            this.g = false;
        }
    }

    public enum b {
        kCBInitial,
        kCBInProgress
    }

    private class c extends w<Object> {
        final /* synthetic */ b a;
        private final String e;
        private final long f = System.currentTimeMillis();
        private final String g;
        private final a h;

        public c(b bVar, String str, v vVar, String str2, a aVar) {
            this.a = bVar;
            super(com.chartboost.sdk.impl.w.a.a, str, vVar);
            this.e = str2;
            this.g = str;
            this.h = aVar;
        }

        public Object a() {
            return Integer.valueOf(this.a.hashCode());
        }

        public Map<String, String> b() {
            Map<String, String> hashMap = new HashMap();
            for (Entry entry : ad.b().entrySet()) {
                hashMap.put(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : null);
            }
            return hashMap;
        }

        public void a(Object obj) {
        }

        public y<Object> a(ab abVar) {
            if (abVar != null) {
                if (abVar.a().length > 0) {
                    com.chartboost.sdk.Tracking.a.e(this.g, this.e, Long.valueOf((System.currentTimeMillis() - this.f) / 1000).toString());
                    File d = this.a.c.d(this.h.c);
                    if (d != null) {
                        try {
                            bj.a(new File(d, this.h.b), abVar.a());
                        } catch (Exception e) {
                            com.chartboost.sdk.Tracking.a.a(getClass(), "parseServerResponse writeByteArrayToFile(response data)", e);
                        }
                        try {
                            if (!(TextUtils.isEmpty(this.h.a) || this.h.e == null || !this.h.e.c() || this.h.c.contains("param"))) {
                                File k = this.a.c.k();
                                if (k != null) {
                                    Iterator it = this.h.f.iterator();
                                    while (it.hasNext()) {
                                        File file = new File(k, (String) it.next());
                                        if (!file.exists()) {
                                            file.mkdir();
                                        }
                                        File file2 = new File(file, this.h.b.split("\\.(?=[^\\.]+$)")[0]);
                                        try {
                                            CBLogging.a(b.b, "Asset download Success. Storing asset in cache: " + this.e);
                                            bj.a(file2, this.h.e.toString().getBytes());
                                        } catch (Exception e2) {
                                            com.chartboost.sdk.Tracking.a.a(getClass(), "parseServerResponse writeByteArrayToFile(ad id)", e2);
                                        }
                                    }
                                }
                            }
                        } catch (Exception e22) {
                            com.chartboost.sdk.Tracking.a.a(getClass(), "parseServerResponse", e22);
                        }
                    }
                    if (!TextUtils.isEmpty(this.h.a) && this.h.e != null && this.h.e.c() && this.a.m.containsKey(this.h.a)) {
                        int intValue = ((Integer) this.a.m.get(this.h.a)).intValue() - 1;
                        if (intValue > 0) {
                            this.a.m.put(this.h.a, Integer.valueOf(intValue));
                        } else {
                            CBLogging.a(b.b, "All files for " + this.h.a + "is downloaded");
                            this.a.m.remove(this.h.a);
                            d = h.j().h;
                            ArrayList d2 = h.d(d);
                            if (!(d2 == null || d2.isEmpty() || !d2.contains(this.h.a))) {
                                d2.remove(this.h.a);
                            }
                            CBLogging.e(b.b, "##### Serializing blacklist ad id to " + d);
                            h.a(d2, d, false);
                            if (!this.a.l.contains(this.h.a)) {
                                this.a.l.add(this.h.a);
                            }
                            this.a.a(this.a.s, this.h.a, true);
                        }
                    }
                } else {
                    a aVar = this.h;
                    if (this.a.m.containsKey(aVar.a)) {
                        this.a.m.remove(aVar.a);
                    }
                    this.a.a(this.a.s, aVar.a, false);
                    CBLogging.b(b.b, "Asset downloaded content is empty");
                    com.chartboost.sdk.Tracking.a.c(this.g, this.e, "", "Asset downloaded content is empty");
                }
            }
            CBLogging.a(b.b, "Current Download count:" + this.a.g.get());
            CBLogging.a(b.b, "Total Download count:" + this.a.h.get());
            if (this.a.g.incrementAndGet() == this.a.h.get()) {
                CBLogging.e(b.b, "##### Asset Prefetch Download Complete");
                this.a.g.set(0);
                this.a.h.set(0);
                CBLogging.e(b.b, "##### Calling to notify impression callback");
                this.a.a(this.a.s, "", false);
            }
            this.a.a(this.h);
            return y.b();
        }

        public com.chartboost.sdk.impl.w.b c() {
            return com.chartboost.sdk.impl.w.b.LOW;
        }
    }

    private class d implements v {
        final /* synthetic */ b a;
        private c b;

        private d(b bVar) {
            this.a = bVar;
        }

        public void a(x xVar) {
            synchronized (this.a) {
                try {
                    if ((xVar.b() || xVar.c() || xVar.d()) && this.b != null) {
                        com.chartboost.sdk.Tracking.a.c(this.b.g, this.b.e, Long.valueOf((System.currentTimeMillis() - this.b.f) / 1000).toString(), xVar.a());
                        CBLogging.b(b.b, "Error downloading asset " + xVar.a() + this.b.e);
                    }
                    a d = this.b.h;
                    if (this.a.m.containsKey(d.a)) {
                        this.a.m.remove(d.a);
                    }
                    this.a.a(this.a.s, d.a, false);
                } catch (Exception e) {
                    com.chartboost.sdk.Tracking.a.a(getClass(), "onErrorResponse", e);
                }
                if (this.a.g.incrementAndGet() == this.a.h.get()) {
                    CBLogging.e(b.b, "##### Failure response callback : Asset Prefetch Download Complete");
                    this.a.g.set(0);
                    this.a.h.set(0);
                    this.a.f = b.kCBInitial;
                    if (this.b.h == null || TextUtils.isEmpty(this.b.h.a)) {
                        this.a.a(this.a.s, "", false);
                    } else {
                        this.a.a(this.a.s, this.b.h.a, false);
                    }
                }
                this.a.a(this.b.h);
            }
        }
    }

    public enum e {
        Idle,
        High,
        Medium,
        Low
    }

    public b(z zVar) {
        int i = 0;
        this.d = zVar;
        this.a = new ConcurrentHashMap();
        this.m = new ConcurrentHashMap();
        this.k = new ConcurrentHashMap();
        this.l = new ArrayList();
        this.q = new HashMap();
        this.r = new HashMap();
        this.s = e.Idle;
        this.u = new ArrayList();
        this.v = new ArrayList();
        this.w = new ArrayList();
        this.x = new HashMap();
        this.x.put(e.Low, this.u);
        this.x.put(e.Medium, this.v);
        this.x.put(e.High, this.w);
        this.c.n();
        File a = h.a();
        if (a != null) {
            String[] list = a.list();
            if (list != null && list.length > 0) {
                int length = list.length;
                while (i < length) {
                    this.p.add(list[i]);
                    i++;
                }
            }
        }
    }

    public synchronized void a() {
        if (!c.G().booleanValue()) {
            CBLogging.a(b, "###### WebView is disabled so not performing prefetch");
        } else if (c.w() && !c.S().booleanValue()) {
            CBLogging.a(b, "Webview Prefetching the asset list");
            if (this.e == b.kCBInProgress) {
                CBLogging.a(b, "###### Webview Prefetch is already in progress");
            } else if (this.z) {
                new CountDownTimer(this, (long) ((c.U() * 60) * 1000), 1000) {
                    final /* synthetic */ b a;

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        CBLogging.a(b.b, "###### Webview Prefetch Session expired");
                        this.a.z = true;
                    }
                }.start();
                this.z = false;
                this.e = b.kCBInProgress;
                this.h.set(0);
                this.g.set(0);
                ah ahVar = new ah(c.A());
                ahVar.a("cache_assets", b(), com.chartboost.sdk.impl.ah.a.AD);
                k[] kVarArr = new k[3];
                kVarArr[0] = g.a("status", com.chartboost.sdk.Libraries.a.a);
                kVarArr[1] = g.a("message", g.a());
                k[] kVarArr2 = new k[3];
                k[] kVarArr3 = new k[2];
                kVarArr3[0] = g.a("template", g.a());
                kVarArr3[1] = g.a("elements", g.b(g.a(g.a("type", g.a()), g.a("name", g.a()), g.a(Param.VALUE, g.a()))));
                kVarArr2[0] = g.a("templates", g.a(g.b(g.a(kVarArr3))));
                kVarArr2[1] = g.a("images", g.a(g.b(g.a(g.a("name", g.a()), g.a(Param.VALUE, g.a())))));
                kVarArr2[2] = g.a("videos", g.a(g.b(g.a(g.a("name", g.a()), g.a(Param.VALUE, g.a())))));
                kVarArr[2] = g.a("cache_assets", g.a(kVarArr2));
                ahVar.a(g.a(kVarArr));
                ahVar.b(true);
                ahVar.a(this.B);
                com.chartboost.sdk.Tracking.a.a(h.m().e());
                this.s = e.Low;
            } else {
                CBLogging.a(b, "Webview Prefetch session is still active. Won't be making any new prefetch until the prefetch session expires");
            }
        }
    }

    private void a(com.chartboost.sdk.Libraries.e.a aVar) {
        int i;
        int p = aVar.p();
        this.r = new HashMap();
        int D = c.D();
        if (p > D) {
            i = D;
        } else {
            i = p;
        }
        for (int i2 = 0; i2 < i; i2++) {
            com.chartboost.sdk.Libraries.e.a c = aVar.c(i2);
            String e = c.e("template");
            com.chartboost.sdk.Libraries.e.a a = c.a("elements");
            JSONArray e2 = com.chartboost.sdk.impl.a.a().e();
            if (!(TextUtils.isEmpty(e) || a == null || a.p() <= 0)) {
                for (int i3 = 0; i3 < a.p(); i3++) {
                    com.chartboost.sdk.Libraries.e.a c2 = a.c(i3);
                    String e3 = c2.e("type");
                    String e4 = c2.e("name");
                    String e5 = c2.e(Param.VALUE);
                    CharSequence e6 = c2.e("param");
                    if (!(TextUtils.isEmpty(e3) || this.p.contains(e3))) {
                        this.c.e(e3);
                        this.p.add(e3);
                    }
                    if (this.q.containsKey(e4)) {
                        if (e3.equals(AdType.HTML) && !this.r.containsKey(e4)) {
                            this.r.put(e4, e);
                        }
                        h.g((File) this.q.get(e4));
                        if (!TextUtils.isEmpty(e6)) {
                            e2.put(new a(e, e6, "param", e4, c2));
                        }
                    } else if (!TextUtils.isEmpty(e3) && e3.equals("param")) {
                        e2.put(new a(e, e6, "param", e5, c2));
                    } else if (!(TextUtils.isEmpty(e3) || TextUtils.isEmpty(e4) || TextUtils.isEmpty(e5))) {
                        if (e3.equals(AdType.HTML)) {
                            this.r.put(e4, e);
                        }
                        if (this.i.containsKey(e4)) {
                            a aVar2 = (a) this.i.get(e4);
                            aVar2.f.add(e);
                            this.i.put(e4, aVar2);
                        } else {
                            this.i.put(e4, new a(e, e4, e3, e5, c2));
                        }
                        if (!com.chartboost.sdk.impl.a.a().a(e6)) {
                            e2.put(new a(e, e6, "param", e4, c2));
                        }
                    }
                }
                if (e2.length() > 0 && !this.j.containsKey(e)) {
                    this.j.put(e, e2);
                }
            }
        }
    }

    private void a(com.chartboost.sdk.Libraries.e.a aVar, String str) {
        int p = aVar.p();
        for (int i = 0; i < p; i++) {
            com.chartboost.sdk.Libraries.e.a c = aVar.c(i);
            String e = c.e("name");
            String e2 = c.e(Param.VALUE);
            if (!(TextUtils.isEmpty(str) || this.p.contains(str))) {
                this.c.e(str);
                this.p.add(str);
            }
            if (!this.q.containsKey(e)) {
                this.i.put(e, new a(str, e, str, e2, null));
            }
        }
    }

    public synchronized void a(e eVar, com.chartboost.sdk.Libraries.e.a aVar) {
        CBLogging.a(b, "##### SynchronizeAssets called on state: " + eVar);
        switch (eVar) {
            case Low:
                this.u.add(aVar);
                break;
            case Medium:
                this.v.add(aVar);
                break;
            case High:
                this.w.add(aVar);
                break;
        }
        a(eVar);
    }

    private synchronized void a(e eVar) {
        if (eVar != null) {
            CBLogging.a(b, "##### Flush AdQueue called on state: " + eVar);
            if (e()) {
                CBLogging.a(b, "##### Flush AdQueue Download in progress: ");
                if (eVar == e.High && (this.s == e.Medium || this.s == e.Low)) {
                    CBLogging.a(b, "###### FlushAdQueue: Overrriding the current AdPriority" + this.s + " with a high priority one");
                    ((ArrayList) this.x.get(this.s)).add(this.t);
                    d();
                    this.s = e.High;
                    this.t = (com.chartboost.sdk.Libraries.e.a) this.w.remove(0);
                    a(this.t, false, eVar);
                    this.f = b.kCBInProgress;
                } else if (eVar == e.Medium && this.s == e.Low) {
                    CBLogging.a(b, "###### FlushAdQueue: Overrriding the current AdPriority" + this.s + " with a medium priority one");
                    ((ArrayList) this.x.get(this.s)).add(this.t);
                    d();
                    this.s = e.Medium;
                    a((com.chartboost.sdk.Libraries.e.a) this.v.remove(0), false, eVar);
                    this.f = b.kCBInProgress;
                }
            }
        }
        if (!e()) {
            CBLogging.a(b, "###### FlushAdQueue: Download is not in progress");
            CBLogging.a(b, "###### FlushAdQueue: AdPriorityQueue");
            if (!this.w.isEmpty()) {
                this.s = e.High;
                this.f = b.kCBInProgress;
                this.t = (com.chartboost.sdk.Libraries.e.a) this.w.remove(0);
                CBLogging.a(b, "###### Flush Ad Queue: Synchronizing a high priority Ad");
                a(this.t, false, e.High);
            } else if (!this.v.isEmpty()) {
                this.s = e.Medium;
                this.f = b.kCBInProgress;
                CBLogging.a(b, "###### Flush Ad Queue: Synchronizing a medium priority Ad");
                this.t = (com.chartboost.sdk.Libraries.e.a) this.v.remove(0);
                a(this.t, false, e.Medium);
            } else if (this.u.isEmpty()) {
                CBLogging.a(b, "###### Flush Ad Queue: Nothing avaliable in queue resetting the state to initial and idle");
                if (this.a != null && this.a.size() > 0) {
                    for (com.chartboost.sdk.Model.a aVar : this.a.keySet()) {
                        aVar.q().a(aVar, CBImpressionError.ERROR_LOADING_WEB_VIEW);
                        this.a.remove(aVar);
                    }
                }
                this.f = b.kCBInitial;
                this.s = e.Idle;
                this.t = null;
            } else {
                this.s = e.Low;
                this.f = b.kCBInProgress;
                CBLogging.a(b, "###### Flush Ad Queue: Synchronizing a low priority Ad");
                this.t = (com.chartboost.sdk.Libraries.e.a) this.u.remove(0);
                a(this.t, true, e.Low);
            }
        }
    }

    private synchronized void a(com.chartboost.sdk.Libraries.e.a aVar, boolean z, e eVar) {
        try {
            com.chartboost.sdk.Libraries.e.a a;
            String e;
            this.i = new ConcurrentHashMap();
            this.j = new ConcurrentHashMap();
            if (this.n != null && this.n.c() && this.n.p() > 0) {
                a(this.n);
            }
            if (aVar != null && aVar.c()) {
                a = aVar.a("templates");
                if (a != null && a.c()) {
                    a(a);
                }
                ArrayList arrayList = new ArrayList();
                for (Entry value : this.i.entrySet()) {
                    a aVar2 = (a) value.getValue();
                    if (this.m.containsKey(aVar2.a)) {
                        this.m.put(aVar2.a, Integer.valueOf(((Integer) this.m.get(aVar2.a)).intValue() + 1));
                    } else {
                        if (!arrayList.contains(aVar2.a)) {
                            arrayList.add(aVar2.a);
                        }
                        this.m.put(aVar2.a, Integer.valueOf(1));
                    }
                }
                File file = h.j().h;
                CBLogging.e(b, "##### Serializing blacklist ad id to " + file);
                h.a(arrayList, file, true);
                for (String e2 : aVar.q()) {
                    if (!e2.contains("template")) {
                        com.chartboost.sdk.Libraries.e.a a2 = aVar.a(e2);
                        if (a2 != null && a2.c()) {
                            a(a2, e2);
                        }
                    }
                }
            }
            if (this.i.isEmpty()) {
                this.f = b.kCBInitial;
                CBLogging.a(b, "####### Nothing to download for the given response object");
                if (aVar.c() && aVar.a("templates").c() && aVar.a("templates").p() > 0) {
                    a = aVar.a("templates").c(0);
                    if (a.c()) {
                        e2 = a.e("template");
                        if (eVar != e.Low) {
                            if (TextUtils.isEmpty(e2)) {
                                a(eVar, "", false);
                                CBLogging.a(b, "###### TemplateId Missing for the given response object");
                            } else {
                                a(eVar, e2, true);
                            }
                        }
                    }
                }
            } else {
                this.h.set(this.i.size());
                a(this.i, eVar);
            }
        } catch (Exception e3) {
            CBLogging.b(b, "Error while syncrhonizing assets");
            com.chartboost.sdk.Tracking.a.a(getClass(), "synchronizeAssets", e3);
        }
        return;
    }

    public void a(HashMap<String, File> hashMap) {
        synchronized (this.y) {
            ArrayList arrayList = new ArrayList();
            for (String split : hashMap.keySet()) {
                arrayList.add(split.split("\\.(?=[^\\.]+$)")[0]);
            }
            File k = this.c.k();
            if (k != null) {
                File[] listFiles = k.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (File file : listFiles) {
                        if (!TextUtils.isEmpty(file.getName())) {
                            Object obj = 1;
                            String name = file.getName();
                            File[] listFiles2 = file.listFiles();
                            if (listFiles2 != null && listFiles2.length > 0) {
                                for (File name2 : listFiles2) {
                                    if (!arrayList.contains(name2.getName())) {
                                        obj = null;
                                    }
                                }
                            }
                            if (obj != null) {
                                if (!this.l.contains(name)) {
                                    this.l.add(name);
                                }
                            } else if (this.l.contains(name)) {
                                this.l.remove(name);
                            }
                        }
                    }
                }
            }
        }
    }

    private synchronized void a(ConcurrentHashMap<String, a> concurrentHashMap, e eVar) {
        for (a aVar : concurrentHashMap.values()) {
            Object dVar = new d();
            c cVar = new c(this, aVar.d, dVar, aVar.b, aVar);
            dVar.b = cVar;
            this.o.add(cVar);
            com.chartboost.sdk.Tracking.a.a(aVar.d, aVar.b);
        }
        if (!this.A) {
            if (eVar == this.s) {
                CBLogging.a(b, "##### DownloadAssets:Sending request to volley: " + this.s);
                if (!this.o.isEmpty()) {
                    this.d.a((w) this.o.remove(0));
                }
            } else {
                CBLogging.a(b, "##### DownloadAssets: Priority states are different probably overridden by a high priority one ");
            }
        }
    }

    private String a(String str, String str2) {
        if (this.j.isEmpty() || str.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = (JSONArray) this.j.get(str2);
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    a aVar = (a) jSONArray.get(i);
                    String str3 = aVar.b;
                    str = str.replaceAll(Pattern.quote(str3), aVar.d);
                } catch (Exception e) {
                    com.chartboost.sdk.Tracking.a.a(getClass(), "injectValuesOnToTheFile", e);
                    CBLogging.b(b, "Error while injecting values into the html");
                }
            }
        }
        if (!str.contains("{{")) {
            return str;
        }
        CBLogging.b(b, " Html data still contains mustache injection values, cannot load the web view ad");
        return null;
    }

    private void a(a aVar) {
        if (this.o.isEmpty()) {
            this.f = b.kCBInitial;
            CBLogging.a(b, "######## No request to flush from queue");
        } else if (this.A) {
            CBLogging.a(b, "######## Request download is paused");
            CBLogging.a(b, "######## Current download queue size: " + this.o.size());
        } else {
            CBLogging.a(b, "######## Flushing out next asset download request");
            CBLogging.a(b, "######## Current download queue size: " + this.o.size());
            this.d.a((w) this.o.remove(0));
        }
    }

    public synchronized com.chartboost.sdk.Libraries.e.a b() {
        Object a;
        a = com.chartboost.sdk.Libraries.e.a.a();
        try {
            this.q = h.b();
            a(this.q);
            List<String> l = c.l();
            JSONArray e = com.chartboost.sdk.impl.a.a().e();
            if (this.l.size() > 0) {
                Iterator it = this.l.iterator();
                while (it.hasNext()) {
                    e.put((String) it.next());
                }
            }
            a.a("templates", e);
            if (!(l == null || l.isEmpty())) {
                for (String str : l) {
                    if (!str.contains("template")) {
                        File file = new File(h.a(), str);
                        JSONArray jSONArray = new JSONArray();
                        if (file.exists()) {
                            String[] list = file.list();
                            if (list != null) {
                                for (String str2 : list) {
                                    if (!str2.contains("nomedia")) {
                                        jSONArray.put(str2);
                                    }
                                }
                            }
                        }
                        a.a(str, jSONArray);
                    }
                }
            }
        } catch (Exception e2) {
            CBLogging.b(b, "getAvailableAdIdList(): Error while loading ad id into json array");
            com.chartboost.sdk.Tracking.a.a(getClass(), "getAvailableAdIdList", e2);
        }
        return com.chartboost.sdk.Libraries.e.a.a(a);
    }

    public ConcurrentHashMap<String, String> c() {
        return this.k;
    }

    public synchronized void d() {
        this.d.a(Integer.valueOf(hashCode()));
        if (!(this.o == null || this.o.isEmpty())) {
            this.o.clear();
        }
    }

    public synchronized boolean e() {
        boolean z;
        z = (this.o.isEmpty() && this.h.get() == 0) ? false : true;
        return z;
    }

    private void a(e eVar, String str, boolean z) {
        CBLogging.a(b, "##### notifyCacheImpressionCallback called on state:" + eVar + " for adId:" + (TextUtils.isEmpty(str) ? "Empty" : str));
        if (eVar == e.Low) {
            CBLogging.a(b, "##### No need to notify any impressions as they are prefetch download request");
            return;
        }
        if (!(this.r.isEmpty() || this.j.isEmpty())) {
            File file = new File(this.c.l(), com.chartboost.sdk.Libraries.h.a.Html.toString());
            if (file.exists()) {
                for (String str2 : this.r.keySet()) {
                    File file2 = new File(file, str2);
                    if (file2.exists()) {
                        String str3 = new String(this.c.b(file2), Charset.defaultCharset());
                        CBLogging.a(b, "##### Before html injection file path " + file2);
                        CharSequence a = a(str3, (String) this.r.get(str2));
                        if (TextUtils.isEmpty(a)) {
                            CBLogging.b(b, "Error happened while injection on updating the html file in cache " + file2);
                        } else {
                            this.k.put(this.r.get(str2), a);
                        }
                    } else {
                        CBLogging.b(b, "Error happened, cannot able to find html file in the directory for some reason:" + str2);
                    }
                }
            } else {
                CBLogging.b(b, "Error happened, cannot able to find html directory for some reason");
            }
        }
        if (this.a.size() > 0) {
            for (final com.chartboost.sdk.Model.a aVar : this.a.keySet()) {
                if (!TextUtils.isEmpty(str) && aVar.i.equals(str)) {
                    if (z) {
                        CBUtility.c().post(new Runnable(this) {
                            final /* synthetic */ b b;

                            public void run() {
                                CBLogging.a(b.b, "######## Impression found and is read to be notified.");
                                aVar.q().q(aVar);
                            }
                        });
                        this.a.remove(aVar);
                    } else {
                        aVar.q().a(aVar, CBImpressionError.ASSETS_DOWNLOAD_FAILURE);
                        this.a.remove(aVar);
                    }
                }
            }
        }
        this.f = b.kCBInitial;
        a(null);
    }

    public synchronized void f() {
        try {
            CBLogging.a(b, "########### Invalidating the disk cache");
            this.q = h.b();
            if (!(this.q == null || this.q.isEmpty())) {
                File[] fileArr = new File[this.q.size()];
                int i = 0;
                for (File file : this.q.values()) {
                    fileArr[i] = file;
                    i++;
                }
                if (fileArr.length > 1) {
                    Arrays.sort(fileArr, new Comparator<File>(this) {
                        final /* synthetic */ b a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ int compare(Object x0, Object x1) {
                            return a((File) x0, (File) x1);
                        }

                        public int a(File file, File file2) {
                            return Long.valueOf(file.lastModified()).compareTo(Long.valueOf(file2.lastModified()));
                        }
                    });
                }
                ArrayList arrayList = new ArrayList();
                if (fileArr.length > 0) {
                    long F = (long) c.F();
                    long f = h.f(this.c.e());
                    int C = c.C();
                    CBLogging.a(b, "Total local file count:" + fileArr.length);
                    CBLogging.a(b, "Video Folder Size in bytes :" + f);
                    CBLogging.a(b, "Max Bytes allowed:" + F);
                    File k = this.c.k();
                    long j = f;
                    for (File file2 : fileArr) {
                        if ((j > F || file2.length() == 0) && ((file2 != null && (file2.getPath().contains(com.chartboost.sdk.Libraries.h.a.Videos.toString()) || file2.getPath().contains(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO))) || file2.length() == 0)) {
                            CBLogging.a(b, "Deleting file at path:" + file2.getPath());
                            j -= file2.length();
                            CBLogging.a(b, "Current Video Size:" + j);
                            file2.delete();
                            a(file2, k, arrayList);
                        }
                    }
                    for (File file3 : fileArr) {
                        boolean a = h.a(file3, C);
                        if (file3.length() == 0 || (a && file3.exists())) {
                            CBLogging.a(b, "Deleting file at path:" + file3.getPath());
                            file3.delete();
                            a(file3, k, arrayList);
                        }
                    }
                }
                h.a(arrayList, h.j().h, true);
            }
        } catch (Exception e) {
            com.chartboost.sdk.Tracking.a.a(getClass(), "reduceCacheSize", e);
        }
    }

    public void g() {
        if (!this.A) {
            CBLogging.a(b, "######## Pause any downloads for webview, as a ad is about to be shown");
            CBLogging.a(b, "######## Current dowload Queue size: " + this.o.size());
            CBLogging.a(b, "######## CurrentAdPriority: " + this.s);
            this.A = true;
        }
    }

    public void h() {
        if (this.A) {
            CBLogging.a(b, "######## Resuming any downloads for webview, as a ad is about to be dismissed");
            CBLogging.a(b, "######## Current dowload Queue size" + this.o.size());
            CBLogging.a(b, "######## CurrentAdPriority: " + this.s);
            this.A = false;
            if (this.o.isEmpty()) {
                a(null);
            } else {
                a(null);
            }
        }
    }

    private void a(File file, File file2, ArrayList<String> arrayList) {
        if (file2 != null) {
            File[] listFiles = file2.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file3 : listFiles) {
                    arrayList.add(file3.getName());
                    if (this.k.containsKey(arrayList)) {
                        this.k.remove(arrayList);
                    }
                    if (this.l.contains(arrayList)) {
                        this.l.remove(arrayList);
                    }
                    File[] listFiles2 = file3.listFiles();
                    if (listFiles2 != null && listFiles2.length > 0) {
                        for (File file4 : listFiles2) {
                            if (file4.getName().equalsIgnoreCase(file.getName().split("\\.(?=[^\\.]+$)")[0])) {
                                CBLogging.a(b, "Deleting log file info:" + file4);
                                file4.delete();
                            }
                        }
                    }
                }
            }
        }
    }
}
