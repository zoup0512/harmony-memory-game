package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class ca {
    private static final ch c = new ch("UNDEFINED_");
    protected final String a;
    protected final SharedPreferences b;
    private final Map<String, Object> d = new HashMap();
    private boolean e = false;

    protected abstract String f();

    public ca(Context context, String str) {
        this.a = str;
        this.b = a(context);
        h();
    }

    protected void h() {
        ch chVar = new ch(c.a(), this.a);
    }

    protected SharedPreferences a(Context context) {
        return ci.a(context, f());
    }

    protected <T extends ca> T a(String str, Object obj) {
        synchronized (this) {
            if (obj != null) {
                this.d.put(str, obj);
            }
        }
        return this;
    }

    protected <T extends ca> T h(String str) {
        synchronized (this) {
            this.d.put(str, this);
        }
        return this;
    }

    protected <T extends ca> T i() {
        synchronized (this) {
            this.e = true;
            this.d.clear();
        }
        return this;
    }

    protected String j() {
        return this.a;
    }

    public void k() {
        synchronized (this) {
            Editor edit = this.b.edit();
            if (this.e) {
                edit.clear();
                edit.apply();
            } else {
                for (Entry entry : this.d.entrySet()) {
                    String str = (String) entry.getKey();
                    ca value = entry.getValue();
                    if (value == this) {
                        edit.remove(str);
                    } else if (value instanceof String) {
                        edit.putString(str, (String) value);
                    } else if (value instanceof Long) {
                        edit.putLong(str, ((Long) value).longValue());
                    } else if (value instanceof Integer) {
                        edit.putInt(str, ((Integer) value).intValue());
                    } else if (value instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) value).booleanValue());
                    } else if (value != null) {
                        throw new UnsupportedOperationException();
                    }
                }
                edit.apply();
            }
            this.d.clear();
            this.e = false;
        }
    }
}
