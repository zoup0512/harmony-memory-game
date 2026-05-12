package com.cmcm.picks.loader;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.cmcm.adsdk.CMAdManager;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MarketStorage */
public class i {
    private static i a = new i();
    private a b = new a(CMAdManager.getContext());

    /* compiled from: MarketStorage */
    private static class a extends SQLiteOpenHelper {
        public a(Context context) {
            super(context, "market.db", null, a.a);
        }

        public void onCreate(SQLiteDatabase db) {
            a.a(db, "tbl_41");
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            for (String str : b(sQLiteDatabase)) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
            }
        }

        public static List<String> b(SQLiteDatabase sQLiteDatabase) {
            List<String> arrayList = new ArrayList();
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM sqlite_master WHERE type='table';", null);
            try {
                rawQuery.moveToFirst();
                while (!rawQuery.isAfterLast()) {
                    String string = rawQuery.getString(1);
                    if (!(string.equals("android_metadata") || string.equals("sqlite_sequence"))) {
                        arrayList.add(string);
                    }
                    rawQuery.moveToNext();
                }
                return arrayList;
            } finally {
                if (rawQuery != null) {
                    rawQuery.close();
                }
            }
        }

        public void a(SQLiteDatabase sQLiteDatabase, String str) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int currentVersion) {
            a(db);
            onCreate(db);
        }
    }

    private i() {
    }

    public static i a() {
        return a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.database.sqlite.SQLiteDatabase r6, java.lang.String r7) {
        /*
        r2 = 0;
        r0 = 1;
        r1 = 0;
        r3 = "SELECT * FROM sqlite_master WHERE type='table' AND name='%s';";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x0024, all -> 0x002d }
        r5 = 0;
        r4[r5] = r7;	 Catch:{ Exception -> 0x0024, all -> 0x002d }
        r3 = java.lang.String.format(r3, r4);	 Catch:{ Exception -> 0x0024, all -> 0x002d }
        r4 = 0;
        r2 = r6.rawQuery(r3, r4);	 Catch:{ Exception -> 0x0024, all -> 0x002d }
        if (r2 == 0) goto L_0x0022;
    L_0x0016:
        r3 = r2.getCount();	 Catch:{ Exception -> 0x0034, all -> 0x002d }
        if (r3 <= 0) goto L_0x0022;
    L_0x001c:
        if (r2 == 0) goto L_0x0021;
    L_0x001e:
        r2.close();
    L_0x0021:
        return r0;
    L_0x0022:
        r0 = r1;
        goto L_0x001c;
    L_0x0024:
        r0 = move-exception;
        r0 = r2;
    L_0x0026:
        if (r0 == 0) goto L_0x002b;
    L_0x0028:
        r0.close();
    L_0x002b:
        r0 = r1;
        goto L_0x0021;
    L_0x002d:
        r0 = move-exception;
        if (r2 == 0) goto L_0x0033;
    L_0x0030:
        r2.close();
    L_0x0033:
        throw r0;
    L_0x0034:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmcm.picks.loader.i.a(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int a(java.lang.String r11, java.util.List<com.cmcm.picks.loader.Ad> r12) {
        /*
        r10 = this;
        monitor-enter(r10);
        r2 = r10.b();	 Catch:{ all -> 0x005d }
        if (r2 != 0) goto L_0x000a;
    L_0x0007:
        r1 = -1;
    L_0x0008:
        monitor-exit(r10);
        return r1;
    L_0x000a:
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x005d }
        r0.<init>();	 Catch:{ all -> 0x005d }
        r1 = "tbl_";
        r0 = r0.append(r1);	 Catch:{ all -> 0x005d }
        r0 = r0.append(r11);	 Catch:{ all -> 0x005d }
        r3 = r0.toString();	 Catch:{ all -> 0x005d }
        r1 = 0;
        r2.beginTransaction();	 Catch:{ Exception -> 0x0051, all -> 0x0058 }
        com.cmcm.picks.loader.a.a(r2, r3);	 Catch:{ Exception -> 0x0051, all -> 0x0058 }
        r4 = r12.iterator();	 Catch:{ Exception -> 0x0051, all -> 0x0058 }
    L_0x0028:
        r0 = r4.hasNext();	 Catch:{ Exception -> 0x0051, all -> 0x0058 }
        if (r0 == 0) goto L_0x0048;
    L_0x002e:
        r0 = r4.next();	 Catch:{ Exception -> 0x0051, all -> 0x0058 }
        r0 = (com.cmcm.picks.loader.Ad) r0;	 Catch:{ Exception -> 0x0051, all -> 0x0058 }
        r5 = "";
        r0 = com.cmcm.picks.loader.a.a(r0, r11);	 Catch:{ Exception -> 0x0051, all -> 0x0058 }
        r6 = r2.insert(r3, r5, r0);	 Catch:{ Exception -> 0x0051, all -> 0x0058 }
        r8 = 0;
        r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r0 <= 0) goto L_0x0062;
    L_0x0044:
        r0 = r1 + 1;
    L_0x0046:
        r1 = r0;
        goto L_0x0028;
    L_0x0048:
        r2.setTransactionSuccessful();	 Catch:{ Exception -> 0x0051, all -> 0x0058 }
        r2.endTransaction();	 Catch:{ Exception -> 0x004f }
        goto L_0x0008;
    L_0x004f:
        r0 = move-exception;
        goto L_0x0008;
    L_0x0051:
        r0 = move-exception;
        r2.endTransaction();	 Catch:{ Exception -> 0x0056 }
        goto L_0x0008;
    L_0x0056:
        r0 = move-exception;
        goto L_0x0008;
    L_0x0058:
        r0 = move-exception;
        r2.endTransaction();	 Catch:{ Exception -> 0x0060 }
    L_0x005c:
        throw r0;	 Catch:{ all -> 0x005d }
    L_0x005d:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x0060:
        r1 = move-exception;
        goto L_0x005c;
    L_0x0062:
        r0 = r1;
        goto L_0x0046;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmcm.picks.loader.i.a(java.lang.String, java.util.List):int");
    }

    public synchronized void a(java.lang.String r8, com.cmcm.picks.loader.Ad r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.cmcm.picks.loader.i.a(java.lang.String, com.cmcm.picks.loader.Ad):void. bs: [B:6:0x0009, B:19:0x001a]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r7 = this;
        monitor-enter(r7);
        r1 = r7.b();	 Catch:{ all -> 0x0017 }
        if (r1 != 0) goto L_0x001a;
    L_0x0007:
        if (r1 == 0) goto L_0x000c;
    L_0x0009:
        r1.endTransaction();	 Catch:{ Exception -> 0x000e }
    L_0x000c:
        monitor-exit(r7);
        return;
    L_0x000e:
        r0 = move-exception;
        r1 = com.cmcm.utils.g.a;	 Catch:{ all -> 0x0017 }
        if (r1 == 0) goto L_0x000c;	 Catch:{ all -> 0x0017 }
    L_0x0013:
        r0.printStackTrace();	 Catch:{ all -> 0x0017 }
        goto L_0x000c;
    L_0x0017:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
    L_0x001a:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r0.<init>();	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r2 = "tbl_";	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r0 = r0.append(r8);	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r2 = 1;	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r9.setShowed(r2);	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r1.beginTransaction();	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r2 = com.cmcm.picks.loader.a.a(r9, r8);	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r3 = "pkg = ?";	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r4 = 1;	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r5 = 0;	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r6 = r9.getPkg();	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r4[r5] = r6;	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r1.update(r0, r2, r3, r4);	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        r1.setTransactionSuccessful();	 Catch:{ Exception -> 0x0059, all -> 0x0069 }
        if (r1 == 0) goto L_0x000c;
    L_0x004c:
        r1.endTransaction();	 Catch:{ Exception -> 0x0050 }
        goto L_0x000c;
    L_0x0050:
        r0 = move-exception;
        r1 = com.cmcm.utils.g.a;	 Catch:{ all -> 0x0017 }
        if (r1 == 0) goto L_0x000c;	 Catch:{ all -> 0x0017 }
    L_0x0055:
        r0.printStackTrace();	 Catch:{ all -> 0x0017 }
        goto L_0x000c;
    L_0x0059:
        r0 = move-exception;
        if (r1 == 0) goto L_0x000c;
    L_0x005c:
        r1.endTransaction();	 Catch:{ Exception -> 0x0060 }
        goto L_0x000c;
    L_0x0060:
        r0 = move-exception;
        r1 = com.cmcm.utils.g.a;	 Catch:{ all -> 0x0017 }
        if (r1 == 0) goto L_0x000c;	 Catch:{ all -> 0x0017 }
    L_0x0065:
        r0.printStackTrace();	 Catch:{ all -> 0x0017 }
        goto L_0x000c;
    L_0x0069:
        r0 = move-exception;
        if (r1 == 0) goto L_0x006f;
    L_0x006c:
        r1.endTransaction();	 Catch:{ Exception -> 0x0070 }
    L_0x006f:
        throw r0;	 Catch:{ all -> 0x0017 }
    L_0x0070:
        r1 = move-exception;	 Catch:{ all -> 0x0017 }
        r2 = com.cmcm.utils.g.a;	 Catch:{ all -> 0x0017 }
        if (r2 == 0) goto L_0x006f;	 Catch:{ all -> 0x0017 }
    L_0x0075:
        r1.printStackTrace();	 Catch:{ all -> 0x0017 }
        goto L_0x006f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmcm.picks.loader.i.a(java.lang.String, com.cmcm.picks.loader.Ad):void");
    }

    public synchronized void a(String str) {
        String str2 = "tbl_" + str;
        SQLiteDatabase b = b();
        if (b != null) {
            this.b.a(b, str2);
        }
    }

    public synchronized int b(String str) {
        int i;
        Cursor query;
        Cursor cursor;
        Throwable th;
        SQLiteDatabase b = b();
        if (b == null) {
            i = 0;
        } else {
            String str2 = "tbl_" + str;
            if (a(b, str2)) {
                try {
                    query = b.query(str2, new String[]{TransferTable.COLUMN_ID}, TransferTable.COLUMN_ID, null, null, null, null);
                    if (query != null) {
                        try {
                            if (query.getCount() > 0) {
                                i = query.getCount();
                                if (query != null) {
                                    query.close();
                                }
                            }
                        } catch (Exception e) {
                            cursor = query;
                            if (cursor != null) {
                                cursor.close();
                            }
                            i = 0;
                            return i;
                        } catch (Throwable th2) {
                            th = th2;
                            if (query != null) {
                                query.close();
                            }
                            throw th;
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Exception e2) {
                    cursor = null;
                    if (cursor != null) {
                        cursor.close();
                    }
                    i = 0;
                    return i;
                } catch (Throwable th3) {
                    th = th3;
                    query = null;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
                i = 0;
            } else {
                i = 0;
            }
        }
        return i;
    }

    public synchronized List<Ad> a(String str, String[] strArr, String str2, String[] strArr2) {
        List<Ad> list;
        Throwable th;
        Cursor cursor = null;
        synchronized (this) {
            List<Ad> arrayList = new ArrayList();
            SQLiteDatabase b = b();
            if (b == null) {
                list = arrayList;
            } else {
                String str3 = "tbl_" + str;
                if (a(b, str3)) {
                    try {
                        r0 = b.query(str3, strArr, str2, strArr2, null, null, null);
                        if (r0 != null) {
                            try {
                                if (r0.getCount() > 0) {
                                    r0.moveToFirst();
                                    do {
                                        arrayList.add(a.a(r0));
                                    } while (r0.moveToNext());
                                }
                            } catch (Exception e) {
                                if (r0 != null) {
                                    r0.close();
                                }
                                list = arrayList;
                                return list;
                            } catch (Throwable th2) {
                                cursor = r0;
                                th = th2;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                throw th;
                            }
                        }
                        if (r0 != null) {
                            r0.close();
                        }
                    } catch (Exception e2) {
                        r0 = null;
                        Cursor query;
                        if (query != null) {
                            query.close();
                        }
                        list = arrayList;
                        return list;
                    } catch (Throwable th3) {
                        th = th3;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                    list = arrayList;
                } else {
                    list = arrayList;
                }
            }
        }
        return list;
    }

    private SQLiteDatabase b() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = this.b.getWritableDatabase();
        } catch (Exception e) {
        }
        return sQLiteDatabase;
    }
}
