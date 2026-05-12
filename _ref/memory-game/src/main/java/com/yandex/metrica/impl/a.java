package com.yandex.metrica.impl;

import android.os.Bundle;
import com.yandex.metrica.impl.utils.c;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private JSONObject a = new JSONObject();
    private long b;
    private boolean c;
    private com.yandex.metrica.impl.utils.c.a d = com.yandex.metrica.impl.utils.c.a.d();
    private final c e = new c();

    public static final class a {
        public final String a;
        public final long b;

        public a(String str, long j) {
            this.a = str;
            this.b = j;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            a aVar = (a) o;
            if (this.b != aVar.b) {
                return false;
            }
            if (this.a != null) {
                if (this.a.equals(aVar.a)) {
                    return true;
                }
            } else if (aVar.a == null) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return ((this.a != null ? this.a.hashCode() : 0) * 31) + ((int) (this.b ^ (this.b >>> 32)));
        }
    }

    public a(String str, long j) {
        this.b = j;
        try {
            this.a = new JSONObject(str);
        } catch (JSONException e) {
            this.a = new JSONObject();
            this.b = 0;
        }
    }

    public synchronized void a() {
        this.a = new JSONObject();
        this.b = 0;
    }

    public synchronized void a(String str, String str2) {
        try {
            String a = this.e.a(str, this.d.b(), "App Environment");
            String a2 = this.e.a(str2, this.d.c(), "App Environment");
            if (this.a.has(a)) {
                String string = this.a.getString(a);
                if (a2 == null || !a2.equals(string)) {
                    b(a, a2);
                }
            } else if (a2 != null) {
                b(a, a2);
            }
        } catch (JSONException e) {
        }
    }

    public synchronized void a(Bundle bundle) {
        for (String str : bundle.keySet()) {
            a(str, bundle.getString(str));
        }
    }

    synchronized void b(String str, String str2) throws JSONException {
        if (this.a.length() < this.d.a() || (this.d.a() == this.a.length() && this.a.has(str))) {
            this.a.put(str, str2);
            this.c = true;
        } else {
            this.e.b(str, this.d.a(), "App Environment");
        }
    }

    public synchronized a b() {
        if (this.c) {
            this.b++;
            this.c = false;
        }
        return new a(this.a.toString(), this.b);
    }

    public synchronized String toString() {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder("Map size ");
        stringBuilder.append(this.a.length());
        stringBuilder.append(". Is changed ");
        stringBuilder.append(this.c);
        stringBuilder.append(". Current revision ");
        stringBuilder.append(this.b);
        return stringBuilder.toString();
    }
}
