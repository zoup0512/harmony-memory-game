package com.yandex.metrica.impl.ob;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

class eb extends eh {
    private Map<String, String> a;

    public eb(String str, Map<String, String> map) {
        super(0, str, null);
        this.a = map;
    }

    public String a() {
        String a = super.a();
        Map map = this.a;
        Builder buildUpon = Uri.parse(a).buildUpon();
        for (Entry entry : map.entrySet()) {
            buildUpon.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        return buildUpon.build().toString();
    }

    public Map<String, String> b() {
        Map<String, String> hashMap = new HashMap();
        String format = String.format(Locale.US, "%s.%s.%s", new Object[]{Integer.valueOf(2), Integer.valueOf(12), Integer.valueOf(20)});
        String str = Build.DEVICE;
        String str2 = VERSION.RELEASE;
        hashMap.put("User-agent", String.format(Locale.US, "com.yandex.mobile.pinning/%s (%s; Android %s)", new Object[]{format, str, str2}));
        return hashMap;
    }
}
