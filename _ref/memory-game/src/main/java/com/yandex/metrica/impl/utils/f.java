package com.yandex.metrica.impl.utils;

import android.content.Context;
import android.text.TextUtils;
import com.yandex.metrica.c.a.g;
import com.yandex.metrica.c.a.g.a;
import com.yandex.metrica.impl.be;
import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.p;
import java.util.Locale;

public final class f extends a {
    private static final int[] a = new int[]{3, 6, 4};
    private static final f b = new f();
    private static String c = "";

    public f() {
        super(false);
    }

    public static f e() {
        return b;
    }

    public static void a(Context context) {
        c = String.format("[%s] : ", new Object[]{context.getPackageName()});
    }

    public String c() {
        return "AppMetrica";
    }

    String d(String str, Object[] objArr) {
        return String.format(Locale.US, str, objArr);
    }

    String d() {
        return be.b(c, "");
    }

    public void a(h hVar, String str) {
        if (p.b(hVar.c())) {
            a("%s: %s", str, hVar.a());
        }
    }

    public void a(a aVar, String str) {
        int i;
        for (int i2 : a) {
            if (aVar.d == i2) {
                i = 1;
                break;
            }
        }
        i = 0;
        if (i != 0) {
            String b;
            String str2 = "%s: %s";
            Object[] objArr = new Object[2];
            objArr[0] = str;
            if (aVar.d == 3 && TextUtils.isEmpty(aVar.e)) {
                b = p.a.EVENT_TYPE_NATIVE_CRASH.b();
            } else {
                b = aVar.e;
            }
            objArr[1] = b;
            a(str2, objArr);
        }
    }

    public void a(g gVar, String str) {
        for (a a : gVar.d) {
            a(a, str);
        }
    }
}
