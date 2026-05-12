package com.yandex.metrica.impl;

import android.database.Cursor;
import com.yandex.metrica.c.a.g.b;
import com.yandex.metrica.impl.ob.ay;
import com.yandex.metrica.impl.ob.j;

class ap extends ao {
    public ap(j jVar) {
        super(jVar);
    }

    protected long n() {
        return Long.MIN_VALUE;
    }

    protected long o() {
        return Long.MIN_VALUE;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected com.yandex.metrica.impl.ao.c s() {
        /*
        r7 = this;
        r0 = 0;
        r1 = r7.u();	 Catch:{ Exception -> 0x0043, all -> 0x004c }
        if (r1 == 0) goto L_0x0013;
    L_0x0007:
        r2 = r1.moveToFirst();	 Catch:{ Exception -> 0x0062, all -> 0x0058 }
        if (r2 == 0) goto L_0x0013;
    L_0x000d:
        r2 = r1.getCount();	 Catch:{ Exception -> 0x0062, all -> 0x0058 }
        if (r2 != 0) goto L_0x0038;
    L_0x0013:
        r2 = r7.n;	 Catch:{ Exception -> 0x0062, all -> 0x0058 }
        r4 = r7.n();	 Catch:{ Exception -> 0x0062, all -> 0x0058 }
        r3 = com.yandex.metrica.impl.ob.ay.BACKGROUND;	 Catch:{ Exception -> 0x0062, all -> 0x0058 }
        r0 = r2.b(r4, r3);	 Catch:{ Exception -> 0x0062, all -> 0x0058 }
        if (r0 == 0) goto L_0x0038;
    L_0x0021:
        r2 = r0.moveToFirst();	 Catch:{ Exception -> 0x0067, all -> 0x005d }
        if (r2 == 0) goto L_0x0038;
    L_0x0027:
        r2 = r0.getCount();	 Catch:{ Exception -> 0x0067, all -> 0x005d }
        if (r2 <= 0) goto L_0x0038;
    L_0x002d:
        r2 = r7.n;	 Catch:{ Exception -> 0x0067, all -> 0x005d }
        r4 = r7.n();	 Catch:{ Exception -> 0x0067, all -> 0x005d }
        r3 = com.yandex.metrica.impl.ob.ay.BACKGROUND;	 Catch:{ Exception -> 0x0067, all -> 0x005d }
        r2.a(r4, r3);	 Catch:{ Exception -> 0x0067, all -> 0x005d }
    L_0x0038:
        com.yandex.metrica.impl.bg.a(r1);
        com.yandex.metrica.impl.bg.a(r0);
    L_0x003e:
        r0 = super.s();
        return r0;
    L_0x0043:
        r1 = move-exception;
        r1 = r0;
    L_0x0045:
        com.yandex.metrica.impl.bg.a(r0);
        com.yandex.metrica.impl.bg.a(r1);
        goto L_0x003e;
    L_0x004c:
        r1 = move-exception;
        r2 = r0;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x0051:
        com.yandex.metrica.impl.bg.a(r1);
        com.yandex.metrica.impl.bg.a(r2);
        throw r0;
    L_0x0058:
        r2 = move-exception;
        r6 = r2;
        r2 = r0;
        r0 = r6;
        goto L_0x0051;
    L_0x005d:
        r2 = move-exception;
        r6 = r2;
        r2 = r0;
        r0 = r6;
        goto L_0x0051;
    L_0x0062:
        r2 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0045;
    L_0x0067:
        r2 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ap.s():com.yandex.metrica.impl.ao$c");
    }

    protected Cursor u() {
        return this.n.a(n(), this.b);
    }

    protected Cursor a(long j, ay ayVar) {
        return this.n.b(n(), ayVar);
    }

    protected boolean a(long j) {
        return false;
    }

    protected b a(long j, b bVar) {
        return super.a(o(), bVar);
    }

    public String a() {
        return super.a() + " [" + n() + "]";
    }
}
