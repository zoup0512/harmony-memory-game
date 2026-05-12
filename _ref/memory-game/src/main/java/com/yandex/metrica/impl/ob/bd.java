package com.yandex.metrica.impl.ob;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import com.facebook.internal.ServerProtocol;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.yandex.metrica.impl.bg;
import com.yandex.metrica.impl.utils.e;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class bd {
    private final Map<String, Object> a = new HashMap();
    private final Map<String, Object> b = new HashMap();
    private final bb c;
    private final String d;
    private final b e;
    private volatile boolean f;

    static class a extends RuntimeException {
        public a(String str, String str2, String str3) {
            super(String.format("%s expected, but key %s has value of type %s", new Object[]{str, str2, str3}));
        }
    }

    class b extends Thread {
        final /* synthetic */ bd a;

        b(bd bdVar) {
            this.a = bdVar;
        }

        public void run() {
            synchronized (this.a.a) {
                bd.b(this.a);
                this.a.f = true;
                this.a.a.notifyAll();
            }
            while (!isInterrupted()) {
                synchronized (this) {
                    if (this.a.b.size() == 0) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            interrupt();
                        }
                    }
                    Map hashMap = new HashMap(this.a.b);
                    this.a.b.clear();
                }
                if (hashMap.size() > 0) {
                    bd.a(this.a, hashMap);
                    hashMap.clear();
                }
            }
        }
    }

    static /* synthetic */ void b(bd bdVar) {
        Throwable th;
        Cursor cursor = null;
        Cursor query;
        try {
            query = bdVar.c.getReadableDatabase().query(bdVar.a(), new String[]{TransferTable.COLUMN_KEY, Param.VALUE, "type"}, null, null, null, null, null);
            while (query.moveToNext()) {
                try {
                    CharSequence string = query.getString(query.getColumnIndex(TransferTable.COLUMN_KEY));
                    Object string2 = query.getString(query.getColumnIndex(Param.VALUE));
                    int i = query.getInt(query.getColumnIndex("type"));
                    if (!TextUtils.isEmpty(string)) {
                        switch (i) {
                            case 1:
                                if (!ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(string2)) {
                                    if (!"false".equals(string2)) {
                                        string2 = null;
                                        break;
                                    } else {
                                        string2 = Boolean.FALSE;
                                        break;
                                    }
                                }
                                string2 = Boolean.TRUE;
                                break;
                            case 2:
                                string2 = e.b(string2);
                                break;
                            case 3:
                                string2 = e.a(string2);
                                break;
                            case 4:
                                break;
                            default:
                                string2 = null;
                                break;
                        }
                        if (string2 != null) {
                            bdVar.a.put(string, string2);
                        }
                    }
                } catch (Exception e) {
                    cursor = query;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            bg.a(query);
        } catch (Exception e2) {
            bg.a(cursor);
        } catch (Throwable th3) {
            th = th3;
            query = null;
            bg.a(query);
            throw th;
        }
    }

    static {
        bd.class.getSimpleName();
    }

    public bd(bb bbVar, String str) {
        this.c = bbVar;
        this.d = str;
        this.e = new b(this);
        this.e.start();
    }

    String a() {
        return this.d;
    }

    public void b() {
        synchronized (this.e) {
            this.e.notifyAll();
        }
    }

    private void a(ContentValues[] contentValuesArr) {
        if (contentValuesArr != null) {
            SQLiteDatabase writableDatabase = this.c.getWritableDatabase();
            try {
                writableDatabase.beginTransaction();
                for (ContentValues contentValues : contentValuesArr) {
                    if (contentValues.getAsString(Param.VALUE) == null) {
                        String asString = contentValues.getAsString(TransferTable.COLUMN_KEY);
                        writableDatabase.delete(a(), "key = ?", new String[]{asString});
                    } else {
                        writableDatabase.insertWithOnConflict(a(), null, contentValues, 5);
                    }
                }
                writableDatabase.setTransactionSuccessful();
            } catch (Exception e) {
            } finally {
                bg.a(writableDatabase);
            }
        }
    }

    public String a(String str, String str2) {
        Object b = b(str);
        if (b instanceof String) {
            return (String) b;
        }
        if (b == null || (b instanceof String)) {
            return str2;
        }
        throw new a("String", str, b.getClass().getSimpleName());
    }

    public int a(String str, int i) {
        Object b = b(str);
        if (b instanceof Integer) {
            return ((Integer) b).intValue();
        }
        if (b == null || (b instanceof Integer)) {
            return i;
        }
        throw new a("Integer", str, b.getClass().getSimpleName());
    }

    public long a(String str, long j) {
        Object b = b(str);
        if (b instanceof Long) {
            return ((Long) b).longValue();
        }
        if (b == null || (b instanceof Long)) {
            return j;
        }
        throw new a("Long", str, b.getClass().getSimpleName());
    }

    public boolean a(String str, boolean z) {
        Object b = b(str);
        if (b instanceof Boolean) {
            return ((Boolean) b).booleanValue();
        }
        if (b == null || (b instanceof Boolean)) {
            return z;
        }
        throw new a("Boolean", str, b.getClass().getSimpleName());
    }

    public bd a(String str) {
        synchronized (this.a) {
            c();
            this.a.remove(str);
        }
        synchronized (this.e) {
            this.b.put(str, this);
            this.e.notifyAll();
        }
        return this;
    }

    public synchronized bd b(String str, String str2) {
        a(str, (Object) str2);
        return this;
    }

    public bd b(String str, long j) {
        a(str, Long.valueOf(j));
        return this;
    }

    public synchronized bd b(String str, int i) {
        a(str, Integer.valueOf(i));
        return this;
    }

    public bd b(String str, boolean z) {
        a(str, Boolean.valueOf(z));
        return this;
    }

    private void a(String str, Object obj) {
        synchronized (this.a) {
            c();
            this.a.put(str, obj);
        }
        synchronized (this.e) {
            this.b.put(str, obj);
            this.e.notifyAll();
        }
    }

    private Object b(String str) {
        Object obj;
        synchronized (this.a) {
            c();
            obj = this.a.get(str);
        }
        return obj;
    }

    private void c() {
        if (!this.f) {
            try {
                this.a.wait();
            } catch (InterruptedException e) {
            }
        }
    }

    static /* synthetic */ void a(bd bdVar, Map map) {
        ContentValues[] contentValuesArr = new ContentValues[map.size()];
        int i = 0;
        for (Entry entry : map.entrySet()) {
            ContentValues contentValues = new ContentValues();
            String str = (String) entry.getKey();
            bd value = entry.getValue();
            contentValues.put(TransferTable.COLUMN_KEY, str);
            if (value == bdVar) {
                contentValues.putNull(Param.VALUE);
            } else if (value instanceof String) {
                contentValues.put(Param.VALUE, (String) value);
                contentValues.put("type", Integer.valueOf(4));
            } else if (value instanceof Long) {
                contentValues.put(Param.VALUE, (Long) value);
                contentValues.put("type", Integer.valueOf(3));
            } else if (value instanceof Integer) {
                contentValues.put(Param.VALUE, (Integer) value);
                contentValues.put("type", Integer.valueOf(2));
            } else if (value instanceof Boolean) {
                contentValues.put(Param.VALUE, String.valueOf(((Boolean) value).booleanValue()));
                contentValues.put("type", Integer.valueOf(1));
            } else if (value != null) {
                throw new UnsupportedOperationException();
            }
            contentValuesArr[i] = contentValues;
            i++;
        }
        bdVar.a(contentValuesArr);
    }
}
