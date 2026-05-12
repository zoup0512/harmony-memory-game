package com.yandex.metrica.impl;

import android.content.Context;
import android.util.SparseArray;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.ob.cd;

public abstract class ae {

    interface a {
        void a(Context context);
    }

    protected abstract int a(cd cdVar);

    abstract SparseArray<a> a();

    protected abstract void a(cd cdVar, int i);

    public void a(Context context) {
        cd cdVar = new cd(context);
        int a = a(cdVar);
        int b = b();
        if (a < b) {
            SparseArray a2 = a();
            for (int i = a; i <= b; i++) {
                a aVar = (a) a2.get(i);
                if (aVar != null) {
                    aVar.a(context);
                }
            }
            a(cdVar, b);
            cdVar.k();
        }
    }

    int b() {
        return YandexMetrica.getLibraryApiLevel();
    }
}
