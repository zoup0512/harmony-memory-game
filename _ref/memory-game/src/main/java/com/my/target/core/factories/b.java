package com.my.target.core.factories;

import android.content.Context;
import android.text.TextUtils;
import com.my.target.core.async.commands.i;
import com.my.target.core.net.a;
import com.my.target.core.utils.g;
import com.my.target.core.utils.h;

/* compiled from: CommandsFactory */
public final class b {
    public static com.my.target.core.async.commands.b<Boolean> a(long j, int i, String str, Context context) {
        return new i(j, i, str, context);
    }

    public static com.my.target.core.async.commands.b<Void> a(String str, String str2, int i, String str3, Throwable th, String str4, Context context) {
        return a(str, str2, i, str3, th, null, str4, context);
    }

    public static com.my.target.core.async.commands.b<Void> a(String str, String str2, int i, String str3, Throwable th, String str4, String str5, Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        String name = str == null ? th != null ? th.getClass().getName() : "" : str;
        String a = h.a(name, h.a(currentTimeMillis), str2, i, str3, th, str4, str5, a.a());
        String a2 = h.a(h.b(a, currentTimeMillis, a.a().d()), currentTimeMillis, a.a().c());
        g a3 = a.a();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a3.b());
        stringBuilder.append("://");
        stringBuilder.append(a3.a());
        if (!(a3.g() == 0 || a3.g() == 80 || a3.g() == -1)) {
            stringBuilder.append(":").append(a3.g());
        }
        stringBuilder.append(a3.e());
        stringBuilder.append("/admanmobile/");
        name = stringBuilder.toString();
        if (!TextUtils.isEmpty(a.b)) {
            name = a.b;
        }
        return new com.my.target.core.async.commands.g(name, a2, a, context);
    }

    public static com.my.target.core.async.commands.b<String> a(String str, Context context) {
        return new com.my.target.core.async.commands.h(str, context);
    }
}
