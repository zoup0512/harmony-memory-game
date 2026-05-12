package com.yandex.metrica.impl;

import com.yandex.metrica.impl.utils.c;
import com.yandex.metrica.impl.utils.c.a;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class o {
    private Map<String, String> a = new HashMap();
    private c b = new c();
    private a c;

    o(a aVar) {
        this.c = aVar;
    }

    void a(String str, String str2) {
        if (str2 == null) {
            this.a.remove(str);
            return;
        }
        this.b.a(this.a, str, str2, this.c, "Crash Environment");
    }

    String a() {
        return this.a.isEmpty() ? null : new JSONObject(this.a).toString();
    }
}
