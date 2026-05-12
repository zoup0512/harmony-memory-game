package com.yandex.metrica.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.yandex.metrica.impl.ob.bc;

public class ay {
    private static final Object a = new Object();
    private static volatile ay b;
    private SQLiteOpenHelper c;

    public static ay a(Context context) {
        if (b == null) {
            synchronized (a) {
                if (b == null) {
                    b = new ay(context);
                }
            }
        }
        return b;
    }

    public ay(Context context) {
        this.c = bc.a(context).a();
    }

    void a(byte[] bArr) {
        SQLiteDatabase writableDatabase = this.c.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("GeoLocation", bArr);
        writableDatabase.update("GeoLocationInfo", contentValues, null, null);
    }

    Cursor a() {
        return this.c.getReadableDatabase().rawQuery("SELECT * FROM GeoLocationInfo", null);
    }
}
