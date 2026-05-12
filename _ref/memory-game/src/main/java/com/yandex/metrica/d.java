package com.yandex.metrica;

import android.text.TextUtils;
import java.util.Map;

public class d {
    private String a;
    private String b;
    private Map<String, String> c;

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public Map<String, String> c() {
        return this.c;
    }

    public void a(Map<String, String> map) {
        this.c = map;
    }

    public boolean equals(Object o) {
        if (!(o instanceof d)) {
            return false;
        }
        d dVar = (d) o;
        if (!TextUtils.equals(this.a, dVar.a) || !TextUtils.equals(this.b, dVar.b)) {
            return false;
        }
        if (this.c == dVar.c || this.c == null || this.c.equals(dVar.c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.a != null) {
            hashCode = this.a.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = hashCode * 31;
        if (this.b != null) {
            hashCode = this.b.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.c != null) {
            i = this.c.hashCode();
        }
        return hashCode + i;
    }
}
