package com.yandex.metrica.impl.ob;

import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

public class be {
    private final k a;
    private final k b;
    private final SparseArray<k> c;
    private final bf d;

    public be(k kVar, k kVar2, SparseArray<k> sparseArray, bf bfVar) {
        this.a = kVar;
        this.b = kVar2;
        this.c = sparseArray;
        this.d = bfVar;
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            if (this.d != null && !this.d.a(sQLiteDatabase)) {
                a(sQLiteDatabase, this.a, this.b);
            }
        } catch (Exception e) {
        }
    }

    public void b(SQLiteDatabase sQLiteDatabase) {
        a(this.a, sQLiteDatabase);
    }

    void a(k kVar, SQLiteDatabase sQLiteDatabase) {
        try {
            kVar.a(sQLiteDatabase);
        } catch (Exception e) {
        }
    }

    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        int i3;
        int i4 = 1;
        if (i2 > i) {
            int i5 = i + 1;
            while (i5 <= i2) {
                try {
                    k kVar = (k) this.c.get(i5);
                    if (kVar != null) {
                        kVar.a(sQLiteDatabase);
                    }
                    i5++;
                } catch (Exception e) {
                    i3 = 1;
                }
            }
            i3 = 0;
        } else {
            i3 = 1;
        }
        if (this.d.a(sQLiteDatabase)) {
            i4 = 0;
        }
        if ((i3 | i4) != 0) {
            a(sQLiteDatabase, this.a, this.b);
        }
    }

    void a(SQLiteDatabase sQLiteDatabase, k kVar, k kVar2) {
        try {
            kVar2.a(sQLiteDatabase);
        } catch (Exception e) {
        }
        a(kVar, sQLiteDatabase);
    }
}
