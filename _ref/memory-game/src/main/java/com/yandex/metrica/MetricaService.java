package com.yandex.metrica;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.yandex.metrica.IMetricaService.Stub;
import com.yandex.metrica.impl.GoogleAdvertisingIdGetter;
import com.yandex.metrica.impl.az;
import com.yandex.metrica.impl.be;
import com.yandex.metrica.impl.bg;
import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.ob.bc;
import com.yandex.metrica.impl.ob.bm;
import com.yandex.metrica.impl.ob.br;
import com.yandex.metrica.impl.ob.bv;
import com.yandex.metrica.impl.ob.bw;
import com.yandex.metrica.impl.ob.cy;
import com.yandex.metrica.impl.ob.g;
import com.yandex.metrica.impl.ob.j;
import com.yandex.metrica.impl.p;
import com.yandex.metrica.impl.utils.f;
import com.yandex.metrica.impl.y;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MetricaService extends Service {
    private static final Executor a = new bw();
    private static final ExecutorService b = Executors.newSingleThreadExecutor();
    private static final Map<String, j> c = new HashMap();
    private static final g d = new g();
    private final Stub e = new Stub(this) {
        final /* synthetic */ MetricaService a;

        {
            this.a = this$0;
        }

        public void reportEvent(String event, int type, String value, Bundle data) throws RemoteException {
            data.setClassLoader(CounterConfiguration.class.getClassLoader());
            a(new h(value, event, type), data);
        }

        public void reportData(Bundle data) throws RemoteException {
            data.setClassLoader(CounterConfiguration.class.getClassLoader());
            a(h.b(data), data);
        }

        private void a(h hVar, Bundle bundle) {
            if (!hVar.n()) {
                MetricaService.b.execute(new a(this.a, this.a, hVar, bundle, AnonymousClass1.getCallingUid()));
            }
        }
    };

    private final class a implements Runnable {
        final /* synthetic */ MetricaService a;
        private final int b;
        private final h c;
        private final Bundle d;
        private final Context e;

        a(MetricaService metricaService, Context context, h hVar, Bundle bundle, int i) {
            this.a = metricaService;
            this.e = context.getApplicationContext();
            this.b = i;
            this.c = hVar;
            this.d = bundle;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r8 = this;
            r0 = r8.d;
            r0 = com.yandex.metrica.impl.bg.a(r0);
            r1 = com.yandex.metrica.MetricaService.a(r0);
            if (r1 == 0) goto L_0x000d;
        L_0x000c:
            return;
        L_0x000d:
            r1 = com.yandex.metrica.MetricaService.c;
            monitor-enter(r1);
            r2 = r8.a;	 Catch:{ all -> 0x009d }
            r3 = r8.c;	 Catch:{ all -> 0x009d }
            r4 = r8.b;	 Catch:{ all -> 0x009d }
            r2 = r2.a(r3, r0, r4);	 Catch:{ all -> 0x009d }
            r3 = r2.b();	 Catch:{ all -> 0x009d }
            com.yandex.metrica.MetricaService.b(r0, r3);	 Catch:{ all -> 0x009d }
            r3 = r8.a;	 Catch:{ all -> 0x009d }
            r3.b(r0);	 Catch:{ all -> 0x009d }
            r3 = r8.a;	 Catch:{ all -> 0x009d }
            r4 = r8.a;	 Catch:{ all -> 0x009d }
            r4 = r4.getPackageName();	 Catch:{ all -> 0x009d }
            r5 = r0.f();	 Catch:{ all -> 0x009d }
            r4 = r4.equals(r5);	 Catch:{ all -> 0x009d }
            r5 = r0.m();	 Catch:{ all -> 0x009d }
            com.yandex.metrica.impl.y.a((android.content.Context) r3).a(r3, r4, r5);	 Catch:{ all -> 0x009d }
            r3 = r0.j();	 Catch:{ all -> 0x009d }
            r4 = r8.d;	 Catch:{ all -> 0x009d }
            r5 = "COUNTER_MIGRATION_CFG_OBJ";
            r4 = r4.containsKey(r5);	 Catch:{ all -> 0x009d }
            if (r4 == 0) goto L_0x008c;
        L_0x004d:
            r4 = r8.d;	 Catch:{ all -> 0x009d }
            r4 = com.yandex.metrica.impl.bg.b(r4);	 Catch:{ all -> 0x009d }
            if (r4 == 0) goto L_0x008c;
        L_0x0055:
            r5 = r4.B();	 Catch:{ all -> 0x009d }
            if (r5 == 0) goto L_0x008c;
        L_0x005b:
            r5 = r8.a;	 Catch:{ all -> 0x009d }
            r5 = r5.getApplicationContext();	 Catch:{ all -> 0x009d }
            r6 = r8.b;	 Catch:{ all -> 0x009d }
            r6 = java.lang.Integer.valueOf(r6);	 Catch:{ all -> 0x009d }
            r7 = 0;
            r5 = com.yandex.metrica.impl.ob.h.a(r5, r4, r6, r7);	 Catch:{ all -> 0x009d }
            r6 = com.yandex.metrica.MetricaService.c;	 Catch:{ all -> 0x009d }
            r7 = r5.toString();	 Catch:{ all -> 0x009d }
            r6 = r6.containsKey(r7);	 Catch:{ all -> 0x009d }
            if (r6 != 0) goto L_0x008c;
        L_0x007a:
            r6 = new com.yandex.metrica.CounterConfiguration;	 Catch:{ all -> 0x009d }
            r6.<init>(r4);	 Catch:{ all -> 0x009d }
            r6.a(r3);	 Catch:{ all -> 0x009d }
            r3 = r8.a;	 Catch:{ all -> 0x009d }
            r4 = 0;
            r3 = com.yandex.metrica.MetricaService.a(r3, r5, r6, r4);	 Catch:{ all -> 0x009d }
            r3.f();	 Catch:{ all -> 0x009d }
        L_0x008c:
            r3 = r8.a;	 Catch:{ all -> 0x009d }
            r4 = r8.c;	 Catch:{ all -> 0x009d }
            r2 = com.yandex.metrica.MetricaService.a(r3, r2, r0, r4);	 Catch:{ all -> 0x009d }
            r3 = com.yandex.metrica.MetricaService.a(r2);	 Catch:{ all -> 0x009d }
            if (r3 == 0) goto L_0x00a0;
        L_0x009a:
            monitor-exit(r1);	 Catch:{ all -> 0x009d }
            goto L_0x000c;
        L_0x009d:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x009d }
            throw r0;
        L_0x00a0:
            r3 = r8.e;	 Catch:{ all -> 0x009d }
            r3 = com.yandex.metrica.impl.y.a(r3);	 Catch:{ all -> 0x009d }
            r4 = r8.c;	 Catch:{ all -> 0x009d }
            r4 = r4.e();	 Catch:{ all -> 0x009d }
            r3.a(r4);	 Catch:{ all -> 0x009d }
            r3 = r8.c;	 Catch:{ all -> 0x009d }
            r3 = r3.c();	 Catch:{ all -> 0x009d }
            r3 = com.yandex.metrica.impl.p.a(r3);	 Catch:{ all -> 0x009d }
            if (r3 != 0) goto L_0x00be;
        L_0x00bb:
            r2.a(r0);	 Catch:{ all -> 0x009d }
        L_0x00be:
            r0 = r8.c;	 Catch:{ all -> 0x009d }
            r0 = com.yandex.metrica.MetricaService.a(r2, r0);	 Catch:{ all -> 0x009d }
            if (r0 != 0) goto L_0x00cb;
        L_0x00c6:
            r0 = r8.c;	 Catch:{ all -> 0x009d }
            r2.a(r0);	 Catch:{ all -> 0x009d }
        L_0x00cb:
            monitor-exit(r1);	 Catch:{ all -> 0x009d }
            goto L_0x000c;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.MetricaService.a.run():void");
        }
    }

    static /* synthetic */ boolean a(j jVar, h hVar) {
        if (com.yandex.metrica.impl.p.a.EVENT_TYPE_STARTUP.a() == hVar.c()) {
            jVar.e();
            return true;
        } else if (com.yandex.metrica.impl.p.a.EVENT_TYPE_REFERRER_RECEIVED.a() != hVar.c()) {
            return false;
        } else {
            jVar.b(hVar);
            return true;
        }
    }

    public void onCreate() {
        super.onCreate();
        f.a(getApplicationContext());
        new az(getApplicationContext()).a((Context) this);
        com.yandex.metrica.impl.utils.g.a().a(getApplicationContext());
        GoogleAdvertisingIdGetter.a().a((Context) this);
        bm bmVar = new bm(bc.a(getApplicationContext()).c(), getPackageName());
        bv.a().a(this, bmVar.b(null), bmVar.h(null));
        br.a().a(getApplicationContext());
    }

    public void onStart(Intent intent, int startId) {
        a(intent);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        a(intent);
        return 2;
    }

    private void a(Intent intent) {
        if (intent != null) {
            intent.getExtras().setClassLoader(CounterConfiguration.class.getClassLoader());
            if (!b(intent)) {
                h b = h.b(intent.getExtras());
                if (b.n()) {
                    int intExtra = intent.getIntExtra("EXTRA_KEY_KEY_START_TYPE", com.yandex.metrica.impl.p.a.EVENT_TYPE_UNDEFINED.a());
                    b.a(intExtra).b(intent.getStringExtra("EXTRA_KEY_KEY_START_EVENT")).c("");
                }
                if ((b.m() | b.n()) == 0) {
                    Object obj;
                    Bundle bundleExtra = intent.getBundleExtra("EXTRA_KEY_LIB_CFG");
                    if (bundleExtra == null) {
                        bundleExtra = intent.getExtras();
                    }
                    CounterConfiguration a = bg.a(bundleExtra);
                    if (a == null) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj == null) {
                        String encodedAuthority = intent.getData().getEncodedAuthority();
                        b(a, encodedAuthority);
                        b(a);
                        y.a((Context) this).a(b.e());
                        try {
                            j jVar = new j(this, a, com.yandex.metrica.impl.ob.h.a(this, a, null, encodedAuthority), a, d);
                            jVar.a(b);
                            jVar.d();
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
        stopSelf();
    }

    public void onDestroy() {
        super.onDestroy();
        y.a((Context) this).b((Object) this);
        cy.a((Context) this).b();
    }

    public boolean onUnbind(Intent intent) {
        if (b(intent)) {
            return false;
        }
        String encodedAuthority = intent.getData().getEncodedAuthority();
        synchronized (c) {
            for (Entry entry : new HashMap(c).entrySet()) {
                Object obj;
                String str = (String) entry.getKey();
                j jVar = (j) entry.getValue();
                if (str == null || jVar == null || str.startsWith(encodedAuthority)) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    c.remove(str);
                    if (jVar != null) {
                        jVar.c();
                    }
                }
            }
        }
        return true;
    }

    private void b(CounterConfiguration counterConfiguration) {
        if (TextUtils.isEmpty(counterConfiguration.h())) {
            c(counterConfiguration);
            return;
        }
        CharSequence d = br.a().d();
        Object obj = (TextUtils.isEmpty(d) || TextUtils.equals(counterConfiguration.h(), d)) ? 1 : null;
        if (obj == null) {
            c(counterConfiguration);
        }
    }

    private void c(CounterConfiguration counterConfiguration) {
        String c = bg.c(this, counterConfiguration.f());
        if (!be.a(c)) {
            counterConfiguration.e(c);
        }
    }

    private static void b(CounterConfiguration counterConfiguration, String str) {
        if (TextUtils.isEmpty(counterConfiguration.f())) {
            counterConfiguration.c(str);
        }
    }

    private static boolean b(Intent intent) {
        return intent == null || intent.getData() == null;
    }

    com.yandex.metrica.impl.ob.h a(h hVar, CounterConfiguration counterConfiguration, int i) {
        if (!p.a(hVar)) {
            return com.yandex.metrica.impl.ob.h.a(getApplicationContext(), counterConfiguration, Integer.valueOf(i), null);
        }
        int i2;
        String l = hVar.l();
        for (ApplicationInfo applicationInfo : getApplicationContext().getPackageManager().getInstalledApplications(0)) {
            if (applicationInfo.packageName.equals(l)) {
                i2 = 1;
                break;
            }
        }
        i2 = 0;
        if (i2 != 0) {
            return com.yandex.metrica.impl.ob.h.a(l);
        }
        return null;
    }

    public IBinder onBind(Intent intent) {
        y.a((Context) this).a((Object) this);
        cy.a((Context) this).a();
        return this.e;
    }

    static /* synthetic */ j a(MetricaService metricaService, com.yandex.metrica.impl.ob.h hVar, CounterConfiguration counterConfiguration, h hVar2) {
        if (hVar == null) {
            return null;
        }
        j jVar = (j) c.get(hVar.toString());
        if (jVar == null) {
            jVar = new j(metricaService.getApplicationContext(), a, hVar, counterConfiguration, d);
            if (hVar2 != null && p.a(hVar2)) {
                return jVar;
            }
            c.put(hVar.toString(), jVar);
            return jVar;
        }
        jVar.b(counterConfiguration);
        return jVar;
    }

    static /* synthetic */ boolean a(CounterConfiguration counterConfiguration) {
        return counterConfiguration == null;
    }

    static /* synthetic */ boolean a(j jVar) {
        return jVar == null || jVar.o();
    }
}
