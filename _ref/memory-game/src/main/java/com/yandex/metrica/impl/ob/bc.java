package com.yandex.metrica.impl.ob;

import android.content.Context;
import com.yandex.metrica.impl.bg;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class bc {
    private static volatile bc a;
    private final Map<String, bb> b = new HashMap();
    private final Map<String, bd> c = new HashMap();
    private final Context d;
    private bb e;
    private bd f;
    private bd g;
    private bd h;

    public static bc a(Context context) {
        if (a == null) {
            synchronized (bc.class) {
                if (a == null) {
                    a = new bc(context);
                }
            }
        }
        return a;
    }

    public bc(Context context) {
        this.d = context;
    }

    public synchronized bb a(h hVar) {
        bb bbVar;
        String str = "db_metrica_" + hVar;
        bbVar = (bb) this.b.get(str);
        if (bbVar == null) {
            bbVar = a(str, az.a());
            this.b.put(str, bbVar);
        }
        return bbVar;
    }

    public synchronized bb a() {
        if (this.e == null) {
            this.e = a("metrica_data.db", az.b());
        }
        return this.e;
    }

    public synchronized bd b(h hVar) {
        bd bdVar;
        String hVar2 = hVar.toString();
        bdVar = (bd) this.c.get(hVar2);
        if (bdVar == null) {
            bdVar = new bd(a(hVar), "preferences");
            this.c.put(hVar2, bdVar);
        }
        return bdVar;
    }

    public synchronized bd b() {
        if (this.f == null) {
            this.f = new bd(a(), "preferences");
        }
        return this.f;
    }

    public synchronized bd c() {
        if (this.g == null) {
            this.g = new bd(a(), "startup");
        }
        return this.g;
    }

    public synchronized bd d() {
        if (this.h == null) {
            this.h = new bd(a("metrica_client_data.db", az.c()), "preferences");
        }
        return this.h;
    }

    bb a(String str, be beVar) {
        if (bg.a(21)) {
            str = a(str);
        }
        return new bb(this.d, str, beVar);
    }

    public String a(String str) {
        try {
            File noBackupFilesDir = this.d.getNoBackupFilesDir();
            File file = new File(noBackupFilesDir, str);
            if (!file.exists()) {
                File databasePath = this.d.getDatabasePath(str);
                if (databasePath.exists() && databasePath.renameTo(file)) {
                    String str2 = str + "-journal";
                    this.d.getDatabasePath(str2).renameTo(new File(noBackupFilesDir, str2));
                }
            }
            str = file.getAbsolutePath();
        } catch (Exception e) {
        }
        return str;
    }
}
