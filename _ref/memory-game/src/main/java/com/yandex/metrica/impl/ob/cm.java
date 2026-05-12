package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.bg;
import java.util.HashMap;
import java.util.Map;

public class cm {
    private final Map<String, Integer> a = new HashMap();

    public void a(String str, int i) {
        this.a.put(str, Integer.valueOf(i));
    }

    public String a() {
        return bg.b(this.a);
    }
}
