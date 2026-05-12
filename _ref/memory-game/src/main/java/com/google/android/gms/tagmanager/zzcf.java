package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class zzcf implements zzav {
    private static final String zzcxv = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", new Object[]{"gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time"});
    private final zzb awD;
    private volatile zzac awE;
    private final zzaw awF;
    private final String awG;
    private long awH;
    private final int awI;
    private final Context mContext;
    private zze zzaoc;

    class zza implements com.google.android.gms.tagmanager.zzde.zza {
        final /* synthetic */ zzcf awJ;

        zza(zzcf com_google_android_gms_tagmanager_zzcf) {
            this.awJ = com_google_android_gms_tagmanager_zzcf;
        }

        public void zza(zzar com_google_android_gms_tagmanager_zzar) {
            this.awJ.zzs(com_google_android_gms_tagmanager_zzar.zzcbr());
        }

        public void zzb(zzar com_google_android_gms_tagmanager_zzar) {
            this.awJ.zzs(com_google_android_gms_tagmanager_zzar.zzcbr());
            zzbn.v("Permanent failure dispatching hitId: " + com_google_android_gms_tagmanager_zzar.zzcbr());
        }

        public void zzc(zzar com_google_android_gms_tagmanager_zzar) {
            long zzcbs = com_google_android_gms_tagmanager_zzar.zzcbs();
            if (zzcbs == 0) {
                this.awJ.zzh(com_google_android_gms_tagmanager_zzar.zzcbr(), this.awJ.zzaoc.currentTimeMillis());
            } else if (zzcbs + 14400000 < this.awJ.zzaoc.currentTimeMillis()) {
                this.awJ.zzs(com_google_android_gms_tagmanager_zzar.zzcbr());
                zzbn.v("Giving up on failed hitId: " + com_google_android_gms_tagmanager_zzar.zzcbr());
            }
        }
    }

    class zzb extends SQLiteOpenHelper {
        final /* synthetic */ zzcf awJ;
        private boolean awK;
        private long awL = 0;

        zzb(zzcf com_google_android_gms_tagmanager_zzcf, Context context, String str) {
            this.awJ = com_google_android_gms_tagmanager_zzcf;
            super(context, str, null, 1);
        }

        private boolean zza(String str, SQLiteDatabase sQLiteDatabase) {
            Cursor cursor;
            String str2;
            String valueOf;
            Throwable th;
            Cursor cursor2 = null;
            try {
                Cursor query = sQLiteDatabase.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
                try {
                    boolean moveToFirst = query.moveToFirst();
                    if (query == null) {
                        return moveToFirst;
                    }
                    query.close();
                    return moveToFirst;
                } catch (SQLiteException e) {
                    cursor = query;
                    try {
                        str2 = "Error querying for table ";
                        valueOf = String.valueOf(str);
                        zzbn.zzcx(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf));
                        if (cursor != null) {
                            cursor.close();
                        }
                        return false;
                    } catch (Throwable th2) {
                        cursor2 = cursor;
                        th = th2;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    cursor2 = query;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (SQLiteException e2) {
                cursor = null;
                str2 = "Error querying for table ";
                valueOf = String.valueOf(str);
                if (valueOf.length() == 0) {
                }
                zzbn.zzcx(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf));
                if (cursor != null) {
                    cursor.close();
                }
                return false;
            } catch (Throwable th4) {
                th = th4;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        }

        private void zzc(SQLiteDatabase sQLiteDatabase) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM gtm_hits WHERE 0", null);
            Set hashSet = new HashSet();
            try {
                String[] columnNames = rawQuery.getColumnNames();
                for (Object add : columnNames) {
                    hashSet.add(add);
                }
                if (!hashSet.remove("hit_id") || !hashSet.remove("hit_url") || !hashSet.remove("hit_time") || !hashSet.remove("hit_first_send_time")) {
                    throw new SQLiteException("Database column missing");
                } else if (!hashSet.isEmpty()) {
                    throw new SQLiteException("Database has extra columns");
                }
            } finally {
                rawQuery.close();
            }
        }

        public SQLiteDatabase getWritableDatabase() {
            if (!this.awK || this.awL + 3600000 <= this.awJ.zzaoc.currentTimeMillis()) {
                SQLiteDatabase sQLiteDatabase = null;
                this.awK = true;
                this.awL = this.awJ.zzaoc.currentTimeMillis();
                try {
                    sQLiteDatabase = super.getWritableDatabase();
                } catch (SQLiteException e) {
                    this.awJ.mContext.getDatabasePath(this.awJ.awG).delete();
                }
                if (sQLiteDatabase == null) {
                    sQLiteDatabase = super.getWritableDatabase();
                }
                this.awK = false;
                return sQLiteDatabase;
            }
            throw new SQLiteException("Database creation failed");
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            zzam.zzes(sQLiteDatabase.getPath());
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (VERSION.SDK_INT < 15) {
                Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
                try {
                    rawQuery.moveToFirst();
                } finally {
                    rawQuery.close();
                }
            }
            if (zza("gtm_hits", sQLiteDatabase)) {
                zzc(sQLiteDatabase);
            } else {
                sQLiteDatabase.execSQL(zzcf.zzcxv);
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    zzcf(zzaw com_google_android_gms_tagmanager_zzaw, Context context) {
        this(com_google_android_gms_tagmanager_zzaw, context, "gtm_urls.db", 2000);
    }

    zzcf(zzaw com_google_android_gms_tagmanager_zzaw, Context context, String str, int i) {
        this.mContext = context.getApplicationContext();
        this.awG = str;
        this.awF = com_google_android_gms_tagmanager_zzaw;
        this.zzaoc = zzh.zzavm();
        this.awD = new zzb(this, this.mContext, this.awG);
        this.awE = new zzde(this.mContext, new zza(this));
        this.awH = 0;
        this.awI = i;
    }

    private void zzcce() {
        int zzccf = (zzccf() - this.awI) + 1;
        if (zzccf > 0) {
            List zzzl = zzzl(zzccf);
            zzbn.v("Store full, deleting " + zzzl.size() + " hits to make room.");
            zzg((String[]) zzzl.toArray(new String[0]));
        }
    }

    private void zzh(long j, long j2) {
        SQLiteDatabase zzoq = zzoq("Error opening database for getNumStoredHits.");
        if (zzoq != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_first_send_time", Long.valueOf(j2));
            try {
                zzoq.update("gtm_hits", contentValues, "hit_id=?", new String[]{String.valueOf(j)});
            } catch (SQLiteException e) {
                zzbn.zzcx("Error setting HIT_FIRST_DISPATCH_TIME for hitId: " + j);
                zzs(j);
            }
        }
    }

    private void zzh(long j, String str) {
        SQLiteDatabase zzoq = zzoq("Error opening database for putHit");
        if (zzoq != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_time", Long.valueOf(j));
            contentValues.put("hit_url", str);
            contentValues.put("hit_first_send_time", Integer.valueOf(0));
            try {
                zzoq.insert("gtm_hits", null, contentValues);
                this.awF.zzch(false);
            } catch (SQLiteException e) {
                zzbn.zzcx("Error storing hit");
            }
        }
    }

    private SQLiteDatabase zzoq(String str) {
        try {
            return this.awD.getWritableDatabase();
        } catch (SQLiteException e) {
            zzbn.zzcx(str);
            return null;
        }
    }

    private void zzs(long j) {
        zzg(new String[]{String.valueOf(j)});
    }

    public void dispatch() {
        zzbn.v("GTM Dispatch running...");
        if (this.awE.zzcbg()) {
            List zzzm = zzzm(40);
            if (zzzm.isEmpty()) {
                zzbn.v("...nothing to dispatch");
                this.awF.zzch(true);
                return;
            }
            this.awE.zzai(zzzm);
            if (zzccg() > 0) {
                zzdb.zzcdc().dispatch();
            }
        }
    }

    int zzccf() {
        Cursor cursor = null;
        int i = 0;
        SQLiteDatabase zzoq = zzoq("Error opening database for getNumStoredHits.");
        if (zzoq != null) {
            try {
                cursor = zzoq.rawQuery("SELECT COUNT(*) from gtm_hits", null);
                if (cursor.moveToFirst()) {
                    i = (int) cursor.getLong(0);
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (SQLiteException e) {
                zzbn.zzcx("Error getting numStoredHits");
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return i;
    }

    int zzccg() {
        int count;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        SQLiteDatabase zzoq = zzoq("Error opening database for getNumStoredHits.");
        if (zzoq == null) {
            return 0;
        }
        try {
            Cursor query = zzoq.query("gtm_hits", new String[]{"hit_id", "hit_first_send_time"}, "hit_first_send_time=0", null, null, null, null);
            try {
                count = query.getCount();
                if (query != null) {
                    query.close();
                }
            } catch (SQLiteException e) {
                cursor = query;
                try {
                    zzbn.zzcx("Error getting num untried hits");
                    if (cursor == null) {
                        count = 0;
                    } else {
                        cursor.close();
                        count = 0;
                    }
                    return count;
                } catch (Throwable th2) {
                    cursor2 = cursor;
                    th = th2;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor2 = query;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            cursor = null;
            zzbn.zzcx("Error getting num untried hits");
            if (cursor == null) {
                cursor.close();
                count = 0;
            } else {
                count = 0;
            }
            return count;
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
        return count;
    }

    public void zzg(long j, String str) {
        zzzy();
        zzcce();
        zzh(j, str);
    }

    void zzg(String[] strArr) {
        boolean z = true;
        if (strArr != null && strArr.length != 0) {
            SQLiteDatabase zzoq = zzoq("Error opening database for deleteHits.");
            if (zzoq != null) {
                try {
                    zzoq.delete("gtm_hits", String.format("HIT_ID in (%s)", new Object[]{TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
                    zzaw com_google_android_gms_tagmanager_zzaw = this.awF;
                    if (zzccf() != 0) {
                        z = false;
                    }
                    com_google_android_gms_tagmanager_zzaw.zzch(z);
                } catch (SQLiteException e) {
                    zzbn.zzcx("Error deleting hits");
                }
            }
        }
    }

    List<String> zzzl(int i) {
        Cursor query;
        SQLiteException e;
        String str;
        String valueOf;
        Throwable th;
        List<String> arrayList = new ArrayList();
        if (i <= 0) {
            zzbn.zzcx("Invalid maxHits specified. Skipping");
            return arrayList;
        }
        SQLiteDatabase zzoq = zzoq("Error opening database for peekHitIds.");
        if (zzoq == null) {
            return arrayList;
        }
        try {
            query = zzoq.query("gtm_hits", new String[]{"hit_id"}, null, null, null, null, String.format("%s ASC", new Object[]{"hit_id"}), Integer.toString(i));
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(String.valueOf(query.getLong(0)));
                    } while (query.moveToNext());
                }
                if (query != null) {
                    query.close();
                }
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    str = "Error in peekHits fetching hitIds: ";
                    valueOf = String.valueOf(e.getMessage());
                    zzbn.zzcx(valueOf.length() == 0 ? str.concat(valueOf) : new String(str));
                    if (query != null) {
                        query.close();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
            str = "Error in peekHits fetching hitIds: ";
            valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() == 0) {
            }
            zzbn.zzcx(valueOf.length() == 0 ? str.concat(valueOf) : new String(str));
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.tagmanager.zzar> zzzm(int r17) {
        /*
        r16 = this;
        r11 = new java.util.ArrayList;
        r11.<init>();
        r2 = "Error opening database for peekHits";
        r0 = r16;
        r2 = r0.zzoq(r2);
        if (r2 != 0) goto L_0x0011;
    L_0x000f:
        r2 = r11;
    L_0x0010:
        return r2;
    L_0x0011:
        r12 = 0;
        r3 = "gtm_hits";
        r4 = 3;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r5 = 0;
        r6 = "hit_id";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r5 = 1;
        r6 = "hit_time";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r5 = 2;
        r6 = "hit_first_send_time";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = "%s ASC";
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r13 = 0;
        r14 = "hit_id";
        r10[r13] = r14;	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r9 = java.lang.String.format(r9, r10);	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r10 = java.lang.Integer.toString(r17);	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r13 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x00ca, all -> 0x0172 }
        r12 = new java.util.ArrayList;	 Catch:{ SQLiteException -> 0x0178, all -> 0x0174 }
        r12.<init>();	 Catch:{ SQLiteException -> 0x0178, all -> 0x0174 }
        r3 = r13.moveToFirst();	 Catch:{ SQLiteException -> 0x017e, all -> 0x0174 }
        if (r3 == 0) goto L_0x0068;
    L_0x004b:
        r3 = new com.google.android.gms.tagmanager.zzar;	 Catch:{ SQLiteException -> 0x017e, all -> 0x0174 }
        r4 = 0;
        r4 = r13.getLong(r4);	 Catch:{ SQLiteException -> 0x017e, all -> 0x0174 }
        r6 = 1;
        r6 = r13.getLong(r6);	 Catch:{ SQLiteException -> 0x017e, all -> 0x0174 }
        r8 = 2;
        r8 = r13.getLong(r8);	 Catch:{ SQLiteException -> 0x017e, all -> 0x0174 }
        r3.<init>(r4, r6, r8);	 Catch:{ SQLiteException -> 0x017e, all -> 0x0174 }
        r12.add(r3);	 Catch:{ SQLiteException -> 0x017e, all -> 0x0174 }
        r3 = r13.moveToNext();	 Catch:{ SQLiteException -> 0x017e, all -> 0x0174 }
        if (r3 != 0) goto L_0x004b;
    L_0x0068:
        if (r13 == 0) goto L_0x006d;
    L_0x006a:
        r13.close();
    L_0x006d:
        r11 = 0;
        r3 = "gtm_hits";
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteException -> 0x0170 }
        r5 = 0;
        r6 = "hit_id";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x0170 }
        r5 = 1;
        r6 = "hit_url";
        r4[r5] = r6;	 Catch:{ SQLiteException -> 0x0170 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = "%s ASC";
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ SQLiteException -> 0x0170 }
        r14 = 0;
        r15 = "hit_id";
        r10[r14] = r15;	 Catch:{ SQLiteException -> 0x0170 }
        r9 = java.lang.String.format(r9, r10);	 Catch:{ SQLiteException -> 0x0170 }
        r10 = java.lang.Integer.toString(r17);	 Catch:{ SQLiteException -> 0x0170 }
        r3 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ SQLiteException -> 0x0170 }
        r2 = r3.moveToFirst();	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        if (r2 == 0) goto L_0x00c2;
    L_0x009d:
        r4 = r11;
    L_0x009e:
        r0 = r3;
        r0 = (android.database.sqlite.SQLiteCursor) r0;	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r2 = r0;
        r2 = r2.getWindow();	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r2 = r2.getNumRows();	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        if (r2 <= 0) goto L_0x00fa;
    L_0x00ac:
        r2 = r12.get(r4);	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r2 = (com.google.android.gms.tagmanager.zzar) r2;	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r5 = 1;
        r5 = r3.getString(r5);	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r2.zzou(r5);	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
    L_0x00ba:
        r2 = r4 + 1;
        r4 = r3.moveToNext();	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        if (r4 != 0) goto L_0x0184;
    L_0x00c2:
        if (r3 == 0) goto L_0x00c7;
    L_0x00c4:
        r3.close();
    L_0x00c7:
        r2 = r12;
        goto L_0x0010;
    L_0x00ca:
        r2 = move-exception;
        r3 = r2;
        r4 = r12;
        r2 = r11;
    L_0x00ce:
        r5 = "Error in peekHits fetching hitIds: ";
        r3 = r3.getMessage();	 Catch:{ all -> 0x00f2 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ all -> 0x00f2 }
        r6 = r3.length();	 Catch:{ all -> 0x00f2 }
        if (r6 == 0) goto L_0x00ec;
    L_0x00de:
        r3 = r5.concat(r3);	 Catch:{ all -> 0x00f2 }
    L_0x00e2:
        com.google.android.gms.tagmanager.zzbn.zzcx(r3);	 Catch:{ all -> 0x00f2 }
        if (r4 == 0) goto L_0x0010;
    L_0x00e7:
        r4.close();
        goto L_0x0010;
    L_0x00ec:
        r3 = new java.lang.String;	 Catch:{ all -> 0x00f2 }
        r3.<init>(r5);	 Catch:{ all -> 0x00f2 }
        goto L_0x00e2;
    L_0x00f2:
        r2 = move-exception;
        r12 = r4;
    L_0x00f4:
        if (r12 == 0) goto L_0x00f9;
    L_0x00f6:
        r12.close();
    L_0x00f9:
        throw r2;
    L_0x00fa:
        r5 = "HitString for hitId %d too large.  Hit will be deleted.";
        r2 = 1;
        r6 = new java.lang.Object[r2];	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r7 = 0;
        r2 = r12.get(r4);	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r2 = (com.google.android.gms.tagmanager.zzar) r2;	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r8 = r2.zzcbr();	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r2 = java.lang.Long.valueOf(r8);	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r6[r7] = r2;	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        r2 = java.lang.String.format(r5, r6);	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        com.google.android.gms.tagmanager.zzbn.zzcx(r2);	 Catch:{ SQLiteException -> 0x0118, all -> 0x016d }
        goto L_0x00ba;
    L_0x0118:
        r2 = move-exception;
        r13 = r3;
    L_0x011a:
        r3 = "Error in peekHits fetching hit url: ";
        r2 = r2.getMessage();	 Catch:{ all -> 0x0161 }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ all -> 0x0161 }
        r4 = r2.length();	 Catch:{ all -> 0x0161 }
        if (r4 == 0) goto L_0x015b;
    L_0x012a:
        r2 = r3.concat(r2);	 Catch:{ all -> 0x0161 }
    L_0x012e:
        com.google.android.gms.tagmanager.zzbn.zzcx(r2);	 Catch:{ all -> 0x0161 }
        r3 = new java.util.ArrayList;	 Catch:{ all -> 0x0161 }
        r3.<init>();	 Catch:{ all -> 0x0161 }
        r4 = 0;
        r5 = r12.iterator();	 Catch:{ all -> 0x0161 }
    L_0x013b:
        r2 = r5.hasNext();	 Catch:{ all -> 0x0161 }
        if (r2 == 0) goto L_0x0153;
    L_0x0141:
        r2 = r5.next();	 Catch:{ all -> 0x0161 }
        r2 = (com.google.android.gms.tagmanager.zzar) r2;	 Catch:{ all -> 0x0161 }
        r6 = r2.zzcbt();	 Catch:{ all -> 0x0161 }
        r6 = android.text.TextUtils.isEmpty(r6);	 Catch:{ all -> 0x0161 }
        if (r6 == 0) goto L_0x0169;
    L_0x0151:
        if (r4 == 0) goto L_0x0168;
    L_0x0153:
        if (r13 == 0) goto L_0x0158;
    L_0x0155:
        r13.close();
    L_0x0158:
        r2 = r3;
        goto L_0x0010;
    L_0x015b:
        r2 = new java.lang.String;	 Catch:{ all -> 0x0161 }
        r2.<init>(r3);	 Catch:{ all -> 0x0161 }
        goto L_0x012e;
    L_0x0161:
        r2 = move-exception;
    L_0x0162:
        if (r13 == 0) goto L_0x0167;
    L_0x0164:
        r13.close();
    L_0x0167:
        throw r2;
    L_0x0168:
        r4 = 1;
    L_0x0169:
        r3.add(r2);	 Catch:{ all -> 0x0161 }
        goto L_0x013b;
    L_0x016d:
        r2 = move-exception;
        r13 = r3;
        goto L_0x0162;
    L_0x0170:
        r2 = move-exception;
        goto L_0x011a;
    L_0x0172:
        r2 = move-exception;
        goto L_0x00f4;
    L_0x0174:
        r2 = move-exception;
        r12 = r13;
        goto L_0x00f4;
    L_0x0178:
        r2 = move-exception;
        r3 = r2;
        r4 = r13;
        r2 = r11;
        goto L_0x00ce;
    L_0x017e:
        r2 = move-exception;
        r3 = r2;
        r4 = r13;
        r2 = r12;
        goto L_0x00ce;
    L_0x0184:
        r4 = r2;
        goto L_0x009e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcf.zzzm(int):java.util.List<com.google.android.gms.tagmanager.zzar>");
    }

    int zzzy() {
        boolean z = true;
        long currentTimeMillis = this.zzaoc.currentTimeMillis();
        if (currentTimeMillis <= this.awH + 86400000) {
            return 0;
        }
        this.awH = currentTimeMillis;
        SQLiteDatabase zzoq = zzoq("Error opening database for deleteStaleHits.");
        if (zzoq == null) {
            return 0;
        }
        int delete = zzoq.delete("gtm_hits", "HIT_TIME < ?", new String[]{Long.toString(this.zzaoc.currentTimeMillis() - 2592000000L)});
        zzaw com_google_android_gms_tagmanager_zzaw = this.awF;
        if (zzccf() != 0) {
            z = false;
        }
        com_google_android_gms_tagmanager_zzaw.zzch(z);
        return delete;
    }
}
