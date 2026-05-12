package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.Closeable;

public class bb extends SQLiteOpenHelper implements Closeable {
    protected final be a;

    public bb(Context context, String str, be beVar) {
        super(context, str, null, az.b);
        this.a = beVar;
    }

    public void onCreate(SQLiteDatabase database) {
        this.a.b(database);
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        this.a.a(database, oldVersion, newVersion);
    }

    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        this.a.a(db);
    }
}
