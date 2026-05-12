package com.cmcm.utils;

import android.text.TextUtils;
import com.cmcm.picks.loader.h;
import java.util.Hashtable;
import java.util.Map;

/* compiled from: GlobalCache */
public class e {
    private static e b;
    private Map<String, h> a;

    public static e a() {
        if (b == null) {
            synchronized (e.class) {
                if (b == null) {
                    b = new e();
                }
            }
        }
        return b;
    }

    public synchronized void a(String str, h hVar) {
        if (this.a == null) {
            this.a = new Hashtable();
        }
        if (!(TextUtils.isEmpty(str) || hVar == null)) {
            this.a.put(str, hVar);
        }
    }

    public synchronized h a(String str) {
        h hVar;
        if (this.a != null) {
            hVar = (h) this.a.get(str);
        } else {
            hVar = null;
        }
        return hVar;
    }

    public synchronized void b(String str) {
        if (!(this.a == null || ((h) this.a.get(str)) == null)) {
            this.a.remove(str);
        }
    }
}
