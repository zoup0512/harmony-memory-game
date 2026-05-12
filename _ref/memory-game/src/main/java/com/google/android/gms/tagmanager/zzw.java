package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class zzw implements zzc {
    private static final String avu = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", new Object[]{"datalayer", "ID", TransferTable.COLUMN_KEY, Param.VALUE, "expires"});
    private final Executor avv;
    private zza avw;
    private int avx;
    private final Context mContext;
    private zze zzaoc;

    class zza extends SQLiteOpenHelper {
        final /* synthetic */ zzw avA;

        zza(zzw com_google_android_gms_tagmanager_zzw, Context context, String str) {
            this.avA = com_google_android_gms_tagmanager_zzw;
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
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM datalayer WHERE 0", null);
            Set hashSet = new HashSet();
            try {
                String[] columnNames = rawQuery.getColumnNames();
                for (Object add : columnNames) {
                    hashSet.add(add);
                }
                if (!hashSet.remove(TransferTable.COLUMN_KEY) || !hashSet.remove(Param.VALUE) || !hashSet.remove("ID") || !hashSet.remove("expires")) {
                    throw new SQLiteException("Database column missing");
                } else if (!hashSet.isEmpty()) {
                    throw new SQLiteException("Database has extra columns");
                }
            } finally {
                rawQuery.close();
            }
        }

        public SQLiteDatabase getWritableDatabase() {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                sQLiteDatabase = super.getWritableDatabase();
            } catch (SQLiteException e) {
                this.avA.mContext.getDatabasePath("google_tagmanager.db").delete();
            }
            return sQLiteDatabase == null ? super.getWritableDatabase() : sQLiteDatabase;
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
            if (zza("datalayer", sQLiteDatabase)) {
                zzc(sQLiteDatabase);
            } else {
                sQLiteDatabase.execSQL(zzw.avu);
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    private static class zzb {
        final byte[] avD;
        final String zzaxp;

        zzb(String str, byte[] bArr) {
            this.zzaxp = str;
            this.avD = bArr;
        }

        public String toString() {
            String str = this.zzaxp;
            return new StringBuilder(String.valueOf(str).length() + 54).append("KeyAndSerialized: key = ").append(str).append(" serialized hash = ").append(Arrays.hashCode(this.avD)).toString();
        }
    }

    public zzw(Context context) {
        this(context, zzh.zzavm(), "google_tagmanager.db", 2000, Executors.newSingleThreadExecutor());
    }

    zzw(Context context, zze com_google_android_gms_common_util_zze, String str, int i, Executor executor) {
        this.mContext = context;
        this.zzaoc = com_google_android_gms_common_util_zze;
        this.avx = i;
        this.avv = executor;
        this.avw = new zza(this, this.mContext, str);
    }

    private List<zza> zzag(List<zzb> list) {
        List<zza> arrayList = new ArrayList();
        for (zzb com_google_android_gms_tagmanager_zzw_zzb : list) {
            arrayList.add(new zza(com_google_android_gms_tagmanager_zzw_zzb.zzaxp, zzaj(com_google_android_gms_tagmanager_zzw_zzb.avD)));
        }
        return arrayList;
    }

    private List<zzb> zzah(List<zza> list) {
        List<zzb> arrayList = new ArrayList();
        for (zza com_google_android_gms_tagmanager_DataLayer_zza : list) {
            arrayList.add(new zzb(com_google_android_gms_tagmanager_DataLayer_zza.zzaxp, zzah(com_google_android_gms_tagmanager_DataLayer_zza.zzcnn)));
        }
        return arrayList;
    }

    private byte[] zzah(Object obj) {
        ObjectOutputStream objectOutputStream;
        Throwable th;
        byte[] bArr = null;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(obj);
                bArr = byteArrayOutputStream.toByteArray();
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e) {
                    }
                }
                byteArrayOutputStream.close();
            } catch (IOException e2) {
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e3) {
                    }
                }
                byteArrayOutputStream.close();
                return bArr;
            } catch (Throwable th2) {
                th = th2;
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e4) {
                        throw th;
                    }
                }
                byteArrayOutputStream.close();
                throw th;
            }
        } catch (IOException e5) {
            objectOutputStream = bArr;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            byteArrayOutputStream.close();
            return bArr;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            objectOutputStream = bArr;
            th = th4;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            byteArrayOutputStream.close();
            throw th;
        }
        return bArr;
    }

    private Object zzaj(byte[] bArr) {
        ObjectInputStream objectInputStream;
        Object readObject;
        Throwable th;
        ObjectInputStream objectInputStream2 = null;
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            try {
                readObject = objectInputStream.readObject();
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e) {
                    }
                }
                byteArrayInputStream.close();
            } catch (IOException e2) {
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e3) {
                    }
                }
                byteArrayInputStream.close();
                return readObject;
            } catch (ClassNotFoundException e4) {
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e5) {
                    }
                }
                byteArrayInputStream.close();
                return readObject;
            } catch (Throwable th2) {
                th = th2;
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e6) {
                        throw th;
                    }
                }
                byteArrayInputStream.close();
                throw th;
            }
        } catch (IOException e7) {
            objectInputStream = objectInputStream2;
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            byteArrayInputStream.close();
            return readObject;
        } catch (ClassNotFoundException e8) {
            objectInputStream = objectInputStream2;
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            byteArrayInputStream.close();
            return readObject;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            objectInputStream = objectInputStream2;
            th = th4;
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            byteArrayInputStream.close();
            throw th;
        }
        return readObject;
    }

    private synchronized void zzb(List<zzb> list, long j) {
        try {
            long currentTimeMillis = this.zzaoc.currentTimeMillis();
            zzbt(currentTimeMillis);
            zzzf(list.size());
            zzc(list, currentTimeMillis + j);
            zzcbe();
        } catch (Throwable th) {
            zzcbe();
        }
    }

    private void zzbt(long j) {
        SQLiteDatabase zzoq = zzoq("Error opening database for deleteOlderThan.");
        if (zzoq != null) {
            try {
                zzbn.v("Deleted " + zzoq.delete("datalayer", "expires <= ?", new String[]{Long.toString(j)}) + " expired items");
            } catch (SQLiteException e) {
                zzbn.zzcx("Error deleting old entries.");
            }
        }
    }

    private void zzc(List<zzb> list, long j) {
        SQLiteDatabase zzoq = zzoq("Error opening database for writeEntryToDatabase.");
        if (zzoq != null) {
            for (zzb com_google_android_gms_tagmanager_zzw_zzb : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("expires", Long.valueOf(j));
                contentValues.put(TransferTable.COLUMN_KEY, com_google_android_gms_tagmanager_zzw_zzb.zzaxp);
                contentValues.put(Param.VALUE, com_google_android_gms_tagmanager_zzw_zzb.avD);
                zzoq.insert("datalayer", null, contentValues);
            }
        }
    }

    private List<zza> zzcbb() {
        try {
            zzbt(this.zzaoc.currentTimeMillis());
            List<zza> zzag = zzag(zzcbc());
            return zzag;
        } finally {
            zzcbe();
        }
    }

    private List<zzb> zzcbc() {
        SQLiteDatabase zzoq = zzoq("Error opening database for loadSerialized.");
        List<zzb> arrayList = new ArrayList();
        if (zzoq == null) {
            return arrayList;
        }
        Cursor query = zzoq.query("datalayer", new String[]{TransferTable.COLUMN_KEY, Param.VALUE}, null, null, null, null, "ID", null);
        while (query.moveToNext()) {
            try {
                arrayList.add(new zzb(query.getString(0), query.getBlob(1)));
            } finally {
                query.close();
            }
        }
        return arrayList;
    }

    private int zzcbd() {
        Cursor cursor = null;
        int i = 0;
        SQLiteDatabase zzoq = zzoq("Error opening database for getNumStoredEntries.");
        if (zzoq != null) {
            try {
                cursor = zzoq.rawQuery("SELECT COUNT(*) from datalayer", null);
                if (cursor.moveToFirst()) {
                    i = (int) cursor.getLong(0);
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (SQLiteException e) {
                zzbn.zzcx("Error getting numStoredEntries");
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

    private void zzcbe() {
        try {
            this.avw.close();
        } catch (SQLiteException e) {
        }
    }

    private void zzf(String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            SQLiteDatabase zzoq = zzoq("Error opening database for deleteEntries.");
            if (zzoq != null) {
                try {
                    zzoq.delete("datalayer", String.format("%s in (%s)", new Object[]{"ID", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
                } catch (SQLiteException e) {
                    String str = "Error deleting entries ";
                    String valueOf = String.valueOf(Arrays.toString(strArr));
                    zzbn.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                }
            }
        }
    }

    private void zzop(String str) {
        SQLiteDatabase zzoq = zzoq("Error opening database for clearKeysWithPrefix.");
        if (zzoq != null) {
            try {
                zzbn.v("Cleared " + zzoq.delete("datalayer", "key = ? OR key LIKE ?", new String[]{str, String.valueOf(str).concat(".%")}) + " items");
            } catch (SQLiteException e) {
                String valueOf = String.valueOf(e);
                zzbn.zzcx(new StringBuilder((String.valueOf(str).length() + 44) + String.valueOf(valueOf).length()).append("Error deleting entries with key prefix: ").append(str).append(" (").append(valueOf).append(").").toString());
            } finally {
                zzcbe();
            }
        }
    }

    private SQLiteDatabase zzoq(String str) {
        try {
            return this.avw.getWritableDatabase();
        } catch (SQLiteException e) {
            zzbn.zzcx(str);
            return null;
        }
    }

    private void zzzf(int i) {
        int zzcbd = (zzcbd() - this.avx) + i;
        if (zzcbd > 0) {
            List zzzg = zzzg(zzcbd);
            zzbn.zzcw("DataLayer store full, deleting " + zzzg.size() + " entries to make room.");
            zzf((String[]) zzzg.toArray(new String[0]));
        }
    }

    private List<String> zzzg(int i) {
        SQLiteException e;
        String str;
        String valueOf;
        Throwable th;
        List<String> arrayList = new ArrayList();
        if (i <= 0) {
            zzbn.zzcx("Invalid maxEntries specified. Skipping.");
            return arrayList;
        }
        SQLiteDatabase zzoq = zzoq("Error opening database for peekEntryIds.");
        if (zzoq == null) {
            return arrayList;
        }
        Cursor query;
        try {
            query = zzoq.query("datalayer", new String[]{"ID"}, null, null, null, null, String.format("%s ASC", new Object[]{"ID"}), Integer.toString(i));
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
                    str = "Error in peekEntries fetching entryIds: ";
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
            str = "Error in peekEntries fetching entryIds: ";
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

    public void zza(final com.google.android.gms.tagmanager.DataLayer.zzc.zza com_google_android_gms_tagmanager_DataLayer_zzc_zza) {
        this.avv.execute(new Runnable(this) {
            final /* synthetic */ zzw avA;

            public void run() {
                com_google_android_gms_tagmanager_DataLayer_zzc_zza.zzaf(this.avA.zzcbb());
            }
        });
    }

    public void zza(List<zza> list, final long j) {
        final List zzah = zzah((List) list);
        this.avv.execute(new Runnable(this) {
            final /* synthetic */ zzw avA;

            public void run() {
                this.avA.zzb(zzah, j);
            }
        });
    }

    public void zzoo(final String str) {
        this.avv.execute(new Runnable(this) {
            final /* synthetic */ zzw avA;

            public void run() {
                this.avA.zzop(str);
            }
        });
    }
}
