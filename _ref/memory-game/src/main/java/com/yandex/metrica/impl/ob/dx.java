package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import io.fabric.sdk.android.services.common.IdManager;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

class dx {
    private String a;
    private String b;
    private String c;

    dx(Context context) {
        try {
            this.a = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            this.a = IdManager.DEFAULT_VERSION_NAME;
        }
        this.b = context.getFilesDir().getAbsolutePath();
        this.c = context.getPackageName();
    }

    String a() {
        return this.a;
    }

    String b() {
        return this.b;
    }

    String c() {
        return this.c;
    }

    el a(List<X509Certificate> list) throws GeneralSecurityException, IOException {
        return dz.a((List) list);
    }

    el d() throws GeneralSecurityException, IOException {
        List arrayList = new ArrayList();
        for (String a : a.a()) {
            arrayList.add(dq.a(a));
        }
        return dz.a(arrayList);
    }
}
