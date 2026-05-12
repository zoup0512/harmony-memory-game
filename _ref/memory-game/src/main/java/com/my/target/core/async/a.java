package com.my.target.core.async;

import android.content.Context;
import android.webkit.URLUtil;
import com.my.target.Tracer;
import com.my.target.core.factories.b;
import com.my.target.core.models.g;
import com.my.target.core.models.i;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/* compiled from: Sender */
public final class a {
    private static long a;

    private static void a(String str, String str2, float f, Context context) {
        Exception e;
        try {
            str = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e = e2;
            Tracer.d(e.toString());
            if (str == null) {
            }
            Tracer.d("invalid stat url: " + str);
        } catch (IllegalArgumentException e3) {
            e = e3;
            Tracer.d(e.toString());
            if (str == null) {
            }
            Tracer.d("invalid stat url: " + str);
        }
        if (str == null && URLUtil.isNetworkUrl(str)) {
            Tracer.d("add stat type: " + str2 + (f == -1.0f ? "" : " value: " + f) + " url: " + str);
            b.a(str, context).b();
            return;
        }
        Tracer.d("invalid stat url: " + str);
    }

    public static void a(i iVar, Context context) {
        if (iVar instanceof g) {
            a(iVar.d(), iVar.c(), ((g) iVar).a(), context);
        } else {
            a(iVar.d(), iVar.c(), -1.0f, context);
        }
    }

    public static void a(List<i> list, String str, Context context) {
        for (i iVar : list) {
            if (iVar.c().equals(str)) {
                a(iVar.d(), iVar.c(), -1.0f, context);
            }
        }
    }

    public static void a(List<String> list, Context context) {
        for (String a : list) {
            a(a, null, -1.0f, context);
        }
    }

    public static void a(String str, String str2, String str3, String str4, String str5, Context context) {
        Tracer.d("add log message level: 40");
        if (a < System.currentTimeMillis()) {
            String str6 = " with data";
            b.a(str + str6, str2, 40, str3 + str6, null, str4, str5, context).b();
            a = System.currentTimeMillis() + 86400000;
            return;
        }
        b.a(str, str2, 40, str3, null, str5, context).b();
    }

    public static void a(String str, String str2, int i, String str3, String str4, Context context) {
        Tracer.d("add log message level: " + i);
        b.a(str, str2, i, str3, null, str4, context).b();
    }
}
