package com.my.target.core.net.cookie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URI;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@SuppressLint({"NewApi"})
/* compiled from: MyTargetCookieManager */
public final class a {
    private static a b;
    private final CookieHandler a;

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (b == null && VERSION.SDK_INT >= 9) {
                b = new a(context);
            }
            aVar = b;
        }
        return aVar;
    }

    private a(Context context) {
        this.a = new CookieManager(new b(context), null);
    }

    public final void a(URLConnection uRLConnection) throws IOException {
        Map headerFields = uRLConnection.getHeaderFields();
        this.a.put(URI.create(uRLConnection.getURL().toString()), headerFields);
    }

    public final void b(URLConnection uRLConnection) throws IOException {
        Map hashMap = new HashMap();
        Iterator it = this.a.get(URI.create(uRLConnection.getURL().toString()), hashMap).entrySet().iterator();
        for (boolean hasNext = it.hasNext(); hasNext; hasNext = it.hasNext()) {
            Entry entry = (Entry) it.next();
            String str = (String) entry.getKey();
            Iterator it2 = ((List) entry.getValue()).iterator();
            for (hasNext = it2.hasNext(); hasNext; hasNext = it2.hasNext()) {
                uRLConnection.addRequestProperty(str, (String) it2.next());
            }
        }
    }
}
