package com.yandex.metrica.impl.ob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.cmcm.adsdk.nativead.RequestResultLogger.Model;
import com.yandex.metrica.impl.be;
import com.yandex.metrica.impl.bg;
import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.k;
import com.yandex.metrica.impl.p;
import com.yandex.metrica.impl.utils.i;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ba implements Closeable {
    private final ReentrantReadWriteLock a = new ReentrantReadWriteLock();
    private final Lock b = this.a.readLock();
    private final Lock c = this.a.writeLock();
    private final bb d;
    private a e;
    private final Object f = new Object();
    private List<ContentValues> g;
    private ContentValues h;
    private final Context i;
    private k j;
    private final AtomicLong k = new AtomicLong();

    private class a extends Thread {
        final /* synthetic */ ba a;
        private final List<ContentValues> b = new ArrayList();
        private k c;

        public a(ba baVar) {
            this.a = baVar;
        }

        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    synchronized (this) {
                        if (this.a.c()) {
                            wait();
                        }
                    }
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
                synchronized (this.a.f) {
                    this.b.clear();
                    this.b.addAll(this.a.g);
                    this.a.g.clear();
                    ba.a(this.a, this.a.h);
                    ba.a(this.a, this.b);
                    this.a.h = null;
                }
                b();
            }
        }

        synchronized void a() {
            interrupt();
            this.c = null;
        }

        synchronized void a(k kVar) {
            this.c = kVar;
        }

        synchronized void b() {
            if (!(this.c == null || this.c.o())) {
                this.c.b();
            }
        }
    }

    static /* synthetic */ void a(ba baVar, List list) {
        SQLiteDatabase writableDatabase;
        Throwable th;
        SQLiteDatabase sQLiteDatabase = null;
        if (list != null && !list.isEmpty()) {
            baVar.c.lock();
            try {
                writableDatabase = baVar.d.getWritableDatabase();
                try {
                    writableDatabase.beginTransaction();
                    for (ContentValues contentValues : list) {
                        writableDatabase.insertOrThrow("reports", null, contentValues);
                        baVar.a(contentValues, "Event saved to db");
                    }
                    writableDatabase.setTransactionSuccessful();
                    baVar.k.incrementAndGet();
                    bg.a(writableDatabase);
                    baVar.c.unlock();
                } catch (Exception e) {
                    sQLiteDatabase = writableDatabase;
                    bg.a(sQLiteDatabase);
                    baVar.c.unlock();
                } catch (Throwable th2) {
                    th = th2;
                    bg.a(writableDatabase);
                    baVar.c.unlock();
                    throw th;
                }
            } catch (Exception e2) {
                bg.a(sQLiteDatabase);
                baVar.c.unlock();
            } catch (Throwable th3) {
                Throwable th4 = th3;
                writableDatabase = null;
                th = th4;
                bg.a(writableDatabase);
                baVar.c.unlock();
                throw th;
            }
        }
    }

    public ba(k kVar, bb bbVar) {
        this.d = bbVar;
        this.i = kVar.m();
        a(kVar);
        this.k.set(b());
    }

    void a(k kVar) {
        this.j = kVar;
        this.g = new ArrayList(3);
        if (this.e != null) {
            this.e.a();
        }
        this.e = new a(this);
        this.e.setName("DatabaseWorker [" + kVar.l() + "]");
        this.e.start();
    }

    public void b(k kVar) {
        this.e.a(kVar);
    }

    public void a(long j, ay ayVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Long.valueOf(j));
        contentValues.put("start_time", Long.valueOf(System.currentTimeMillis() / 1000));
        contentValues.put("server_time_offset", Long.valueOf(i.a()));
        contentValues.put("type", Integer.valueOf(ayVar.a()));
        new k(this.i).a(this.j).a(contentValues).a();
        a(contentValues);
    }

    public void a(h hVar, aw awVar, com.yandex.metrica.impl.a.a aVar) {
        ContentValues contentValues = new ContentValues(19);
        contentValues.put("number", Long.valueOf(awVar.c()));
        contentValues.put(Model.KEY_loadtime, Long.valueOf(awVar.d()));
        contentValues.put("session_id", Long.valueOf(awVar.a()));
        contentValues.put("session_type", Integer.valueOf(awVar.b().a()));
        new k(this.i).a(this.j).a(contentValues).a(hVar, aVar);
        b(contentValues);
    }

    private static long a(Cursor cursor) {
        try {
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                return j;
            }
            bg.a(cursor);
            return 0;
        } finally {
            bg.a(cursor);
        }
    }

    private long b() {
        long a;
        this.b.lock();
        try {
            a = a(this.d.getReadableDatabase().rawQuery("SELECT count() FROM reports", null));
        } catch (Exception e) {
            return 0;
        } finally {
            this.b.unlock();
        }
        return a;
    }

    public void a(ContentValues contentValues) {
        synchronized (this.f) {
            this.h = contentValues;
        }
        synchronized (this.e) {
            this.e.notifyAll();
        }
    }

    public void b(ContentValues contentValues) {
        synchronized (this.f) {
            this.g.add(contentValues);
        }
        synchronized (this.e) {
            this.e.notifyAll();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(long r10) {
        /*
        r9 = this;
        r1 = 0;
        r0 = 0;
        r2 = r9.c;
        r2.lock();
        r2 = com.yandex.metrica.impl.ob.az.a;	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        r2 = r2.booleanValue();	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        if (r2 == 0) goto L_0x004e;
    L_0x000f:
        r2 = r9.b;	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        r2.lock();	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        r2 = r9.d;	 Catch:{ Exception -> 0x00cb, all -> 0x00bf }
        r3 = r2.getReadableDatabase();	 Catch:{ Exception -> 0x00cb, all -> 0x00bf }
        r2 = " SELECT DISTINCT id From sessions order by id asc ";
        r4 = 0;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x00cb, all -> 0x00bf }
        r2 = r3.rawQuery(r2, r4);	 Catch:{ Exception -> 0x00cb, all -> 0x00bf }
        r4 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x0042, all -> 0x00c5 }
        r4.<init>();	 Catch:{ Exception -> 0x0042, all -> 0x00c5 }
        r5 = "All sessions in db: ";
        r4.append(r5);	 Catch:{ Exception -> 0x0042, all -> 0x00c5 }
    L_0x002d:
        r5 = r2.moveToNext();	 Catch:{ Exception -> 0x0042, all -> 0x00c5 }
        if (r5 == 0) goto L_0x006c;
    L_0x0033:
        r5 = 0;
        r5 = r2.getString(r5);	 Catch:{ Exception -> 0x0042, all -> 0x00c5 }
        r5 = r4.append(r5);	 Catch:{ Exception -> 0x0042, all -> 0x00c5 }
        r6 = ", ";
        r5.append(r6);	 Catch:{ Exception -> 0x0042, all -> 0x00c5 }
        goto L_0x002d;
    L_0x0042:
        r3 = move-exception;
    L_0x0043:
        r3 = r9.b;	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        r3.unlock();	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        com.yandex.metrica.impl.bg.a(r2);	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        com.yandex.metrica.impl.bg.a(r1);	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
    L_0x004e:
        r1 = r9.d;	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        r1 = r1.getWritableDatabase();	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        r2 = "sessions";
        r3 = com.yandex.metrica.impl.ob.az.w.c;	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        r5 = 0;
        r6 = java.lang.String.valueOf(r10);	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        r0 = r1.delete(r2, r3, r4);	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        r1 = r9.c;
        r1.unlock();
    L_0x006b:
        return r0;
    L_0x006c:
        r4 = " SELECT DISTINCT session_id From reports order by session_id asc ";
        r5 = 0;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x0042, all -> 0x00c5 }
        r1 = r3.rawQuery(r4, r5);	 Catch:{ Exception -> 0x0042, all -> 0x00c5 }
        r3 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x0042, all -> 0x0094 }
        r3.<init>();	 Catch:{ Exception -> 0x0042, all -> 0x0094 }
        r4 = "All sessions in reports db: ";
        r3.append(r4);	 Catch:{ Exception -> 0x0042, all -> 0x0094 }
    L_0x007f:
        r4 = r1.moveToNext();	 Catch:{ Exception -> 0x0042, all -> 0x0094 }
        if (r4 == 0) goto L_0x00ac;
    L_0x0085:
        r4 = 0;
        r4 = r1.getString(r4);	 Catch:{ Exception -> 0x0042, all -> 0x0094 }
        r4 = r3.append(r4);	 Catch:{ Exception -> 0x0042, all -> 0x0094 }
        r5 = ", ";
        r4.append(r5);	 Catch:{ Exception -> 0x0042, all -> 0x0094 }
        goto L_0x007f;
    L_0x0094:
        r3 = move-exception;
        r7 = r3;
        r3 = r2;
        r2 = r1;
        r1 = r7;
    L_0x0099:
        r4 = r9.b;	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        r4.unlock();	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        com.yandex.metrica.impl.bg.a(r3);	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        com.yandex.metrica.impl.bg.a(r2);	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        throw r1;	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
    L_0x00a5:
        r1 = move-exception;
        r1 = r9.c;
        r1.unlock();
        goto L_0x006b;
    L_0x00ac:
        r3 = r9.b;	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        r3.unlock();	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        com.yandex.metrica.impl.bg.a(r2);	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        com.yandex.metrica.impl.bg.a(r1);	 Catch:{ Exception -> 0x00a5, all -> 0x00b8 }
        goto L_0x004e;
    L_0x00b8:
        r0 = move-exception;
        r1 = r9.c;
        r1.unlock();
        throw r0;
    L_0x00bf:
        r2 = move-exception;
        r3 = r1;
        r7 = r1;
        r1 = r2;
        r2 = r7;
        goto L_0x0099;
    L_0x00c5:
        r3 = move-exception;
        r7 = r3;
        r3 = r2;
        r2 = r1;
        r1 = r7;
        goto L_0x0099;
    L_0x00cb:
        r2 = move-exception;
        r2 = r1;
        goto L_0x0043;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.ba.a(long):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(long r10, int r12, int r13) throws android.database.sqlite.SQLiteException {
        /*
        r9 = this;
        r0 = 0;
        if (r13 > 0) goto L_0x0004;
    L_0x0003:
        return;
    L_0x0004:
        r1 = r9.c;
        r1.lock();
        r1 = r9.d;	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r2 = r1.getWritableDatabase();	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r1 = java.util.Locale.US;	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r3 = "%1$s = %2$s AND %3$s = %4$s AND %5$s <= (SELECT %5$s FROM %6$s WHERE %1$s = %2$s AND %3$s = %4$s ORDER BY %5$s ASC LIMIT %7$s, 1)";
        r4 = 7;
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r5 = 0;
        r6 = "session_id";
        r4[r5] = r6;	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r5 = 1;
        r6 = java.lang.Long.toString(r10);	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r5 = 2;
        r6 = "session_type";
        r4[r5] = r6;	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r5 = 3;
        r6 = java.lang.Integer.toString(r12);	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r5 = 4;
        r6 = "id";
        r4[r5] = r6;	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r5 = 5;
        r6 = "reports";
        r4[r5] = r6;	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r5 = 6;
        r6 = r13 + -1;
        r6 = java.lang.Integer.toString(r6);	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r3 = java.lang.String.format(r1, r3, r4);	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r1 = r9.j;	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r1 = r1.p();	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        r1 = r1.b();	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        if (r1 == 0) goto L_0x00bb;
    L_0x0051:
        r1 = r9.a(r3);	 Catch:{ Exception -> 0x00b9, all -> 0x00a5 }
        if (r1 == 0) goto L_0x0083;
    L_0x0057:
        r4 = r1.getCount();	 Catch:{ Exception -> 0x0078, all -> 0x00b2 }
        if (r4 <= 0) goto L_0x0083;
    L_0x005d:
        r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0078, all -> 0x00b2 }
        r4 = r1.getCount();	 Catch:{ Exception -> 0x0078, all -> 0x00b2 }
        r0.<init>(r4);	 Catch:{ Exception -> 0x0078, all -> 0x00b2 }
    L_0x0066:
        r4 = r1.moveToNext();	 Catch:{ Exception -> 0x0078, all -> 0x00b2 }
        if (r4 == 0) goto L_0x0083;
    L_0x006c:
        r4 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0078, all -> 0x00b2 }
        r4.<init>();	 Catch:{ Exception -> 0x0078, all -> 0x00b2 }
        android.database.DatabaseUtils.cursorRowToContentValues(r1, r4);	 Catch:{ Exception -> 0x0078, all -> 0x00b2 }
        r0.add(r4);	 Catch:{ Exception -> 0x0078, all -> 0x00b2 }
        goto L_0x0066;
    L_0x0078:
        r0 = move-exception;
        r0 = r1;
    L_0x007a:
        com.yandex.metrica.impl.bg.a(r0);
        r0 = r9.c;
        r0.unlock();
        goto L_0x0003;
    L_0x0083:
        r7 = r0;
        r0 = r1;
        r1 = r7;
    L_0x0086:
        r4 = "reports";
        r5 = 0;
        r2 = r2.delete(r4, r3, r5);	 Catch:{ Exception -> 0x00b9, all -> 0x00b4 }
        if (r1 == 0) goto L_0x0094;
    L_0x008f:
        r3 = "Event removed from db";
        r9.a(r1, r3);	 Catch:{ Exception -> 0x00b9, all -> 0x00b4 }
    L_0x0094:
        r1 = r9.k;	 Catch:{ Exception -> 0x00b9, all -> 0x00b4 }
        r2 = -r2;
        r2 = (long) r2;	 Catch:{ Exception -> 0x00b9, all -> 0x00b4 }
        r1.addAndGet(r2);	 Catch:{ Exception -> 0x00b9, all -> 0x00b4 }
        com.yandex.metrica.impl.bg.a(r0);
        r0 = r9.c;
        r0.unlock();
        goto L_0x0003;
    L_0x00a5:
        r1 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
    L_0x00a9:
        com.yandex.metrica.impl.bg.a(r1);
        r1 = r9.c;
        r1.unlock();
        throw r0;
    L_0x00b2:
        r0 = move-exception;
        goto L_0x00a9;
    L_0x00b4:
        r1 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x00a9;
    L_0x00b9:
        r1 = move-exception;
        goto L_0x007a;
    L_0x00bb:
        r1 = r0;
        goto L_0x0086;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.ba.a(long, int, int):void");
    }

    private Cursor a(String str) {
        try {
            return this.d.getReadableDatabase().query("reports", null, str, null, null, null, null, null);
        } catch (Exception e) {
            return null;
        }
    }

    public Cursor a(Map<String, String> map) {
        Cursor query;
        this.b.lock();
        try {
            query = this.d.getReadableDatabase().query("sessions", null, a("id >= ?", (Map) map), a(new String[]{Long.toString(0)}, (Map) map), null, null, "id ASC", null);
        } catch (Exception e) {
            return null;
        } finally {
            this.b.unlock();
        }
        return query;
    }

    public Cursor a(long j, Map<String, String> map) {
        Cursor query;
        this.b.lock();
        try {
            query = this.d.getReadableDatabase().query("sessions", null, a("id = ?", (Map) map), a(new String[]{Long.toString(j)}, (Map) map), null, null, null, null);
        } catch (Exception e) {
            return null;
        } finally {
            this.b.unlock();
        }
        return query;
    }

    public Cursor b(long j, ay ayVar) throws SQLiteException {
        Cursor query;
        this.b.lock();
        try {
            query = this.d.getReadableDatabase().query("reports", null, "session_id = ? AND session_type = ?", new String[]{Long.toString(j), Integer.toString(ayVar.a())}, null, null, "number ASC", null);
        } catch (Exception e) {
            return null;
        } finally {
            this.b.unlock();
        }
        return query;
    }

    private void a(List<ContentValues> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            a((ContentValues) list.get(i), str);
        }
    }

    public List<ContentValues> a(Long l) {
        Cursor cursor = null;
        List<ContentValues> arrayList = new ArrayList();
        this.b.lock();
        try {
            SQLiteDatabase readableDatabase = this.d.getReadableDatabase();
            String str = "SELECT DISTINCT report_request_parameters FROM sessions WHERE id >= 0";
            if (l != null) {
                str = String.format(Locale.US, "SELECT DISTINCT report_request_parameters FROM sessions WHERE id = %s", new Object[]{l});
            }
            cursor = readableDatabase.rawQuery(str, null);
            while (cursor.moveToNext()) {
                ContentValues contentValues = new ContentValues();
                DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
                arrayList.add(contentValues);
            }
        } catch (Exception e) {
            arrayList = new ArrayList();
        } finally {
            bg.a(cursor);
            this.b.unlock();
        }
        return arrayList;
    }

    public ContentValues c(long j, ay ayVar) {
        Throwable th;
        Cursor cursor = null;
        ContentValues contentValues = new ContentValues();
        this.b.lock();
        Cursor rawQuery;
        try {
            rawQuery = this.d.getReadableDatabase().rawQuery(String.format(Locale.US, "SELECT report_request_parameters FROM sessions WHERE id = %s AND type = %s ORDER BY id DESC LIMIT 1", new Object[]{Long.valueOf(j), Integer.valueOf(ayVar.a())}), null);
            try {
                ContentValues contentValues2;
                if (rawQuery.moveToNext()) {
                    contentValues2 = new ContentValues();
                    DatabaseUtils.cursorRowToContentValues(rawQuery, contentValues2);
                } else {
                    contentValues2 = contentValues;
                }
                bg.a(rawQuery);
                this.b.unlock();
                return contentValues2;
            } catch (Exception e) {
                cursor = rawQuery;
                bg.a(cursor);
                this.b.unlock();
                return contentValues;
            } catch (Throwable th2) {
                th = th2;
                bg.a(rawQuery);
                this.b.unlock();
                throw th;
            }
        } catch (Exception e2) {
            bg.a(cursor);
            this.b.unlock();
            return contentValues;
        } catch (Throwable th3) {
            rawQuery = null;
            th = th3;
            bg.a(rawQuery);
            this.b.unlock();
            throw th;
        }
    }

    private static String a(String str, Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder(str);
        for (String str2 : map.keySet()) {
            stringBuilder.append(stringBuilder.length() > 0 ? " AND " : "");
            stringBuilder.append(str2 + " = ? ");
        }
        return be.a(stringBuilder.toString()) ? null : stringBuilder.toString();
    }

    private static String[] a(String[] strArr, Map<String, String> map) {
        List arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(strArr));
        for (Entry value : map.entrySet()) {
            arrayList.add(value.getValue());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public long a() {
        this.b.lock();
        try {
            long j = this.k.get();
            return j;
        } finally {
            this.b.unlock();
        }
    }

    public void close() {
        this.g.clear();
        this.e.a();
    }

    private boolean c() {
        boolean z;
        synchronized (this.f) {
            z = this.h == null && this.g.isEmpty();
        }
        return z;
    }

    private void a(ContentValues contentValues, String str) {
        int intValue;
        Integer asInteger = contentValues.getAsInteger("type");
        if (asInteger != null) {
            intValue = asInteger.intValue();
        } else {
            intValue = -1;
        }
        if (p.b(intValue)) {
            this.j.p().a("%s: %s", str, be.b(contentValues.getAsString("name"), ""));
        }
    }

    static /* synthetic */ void a(ba baVar, ContentValues contentValues) {
        if (contentValues != null) {
            baVar.c.lock();
            try {
                baVar.d.getWritableDatabase().insertOrThrow("sessions", null, contentValues);
            } catch (Exception e) {
            } finally {
                baVar.c.unlock();
            }
        }
    }
}
