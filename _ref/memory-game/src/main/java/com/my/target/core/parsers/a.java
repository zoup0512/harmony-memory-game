package com.my.target.core.parsers;

import android.content.Context;
import com.my.target.Tracer;

/* compiled from: ParseErrorMessages */
public final class a {

    /* compiled from: ParseErrorMessages */
    public static class a {
        public final Context a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;

        public a(Context context) {
            this.a = context;
        }
    }

    public static void a(String str, a aVar, String str2) {
        a(str + ", Operation: " + aVar.d + ", Unit: " + aVar.e, aVar, null, str2);
    }

    public static void a(String str, a aVar, Exception exception, String str2) {
        Tracer.d(str + (exception != null ? " message: " + exception.getMessage() : ""));
        com.my.target.core.async.a.a(str, aVar.c, 40, "JSON parse exception: " + str2, aVar.b, aVar.a);
    }

    public static void b(String str, a aVar, String str2) {
        Tracer.d(str);
        com.my.target.core.async.a.a(str + ", Operation: " + aVar.d + ", Unit: " + aVar.e, aVar.c, 40, "VAST Exception:  " + str2, aVar.b, aVar.a);
    }
}
