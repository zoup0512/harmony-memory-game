package com.chartboost.sdk.impl;

import android.graphics.Bitmap;
import com.chartboost.sdk.impl.w.a;
import java.util.Collections;
import java.util.Map;

class aj extends w<Bitmap> {
    private static final Object a = new Object();
    private final aa<Bitmap> e;

    aj(String str, aa<Bitmap> aaVar, v vVar) {
        super(a.a, str, vVar);
        this.e = aaVar;
    }

    public y<Bitmap> a(ab abVar) {
        y<Bitmap> a;
        synchronized (a) {
            try {
                Object a2 = a.a().a(abVar.a());
                if (a2 != null) {
                    a = y.a(a2);
                } else {
                    a = y.a(new aq());
                }
            } catch (Throwable e) {
                a = y.a(new aq(e));
            }
        }
        return a;
    }

    public void a(Bitmap bitmap) {
        this.e.a(bitmap);
    }

    public Map<String, String> b() {
        return Collections.emptyMap();
    }
}
