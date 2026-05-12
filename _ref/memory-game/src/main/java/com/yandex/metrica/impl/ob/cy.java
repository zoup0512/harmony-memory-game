package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.facebook.places.model.PlaceFields;

public class cy extends cr {
    private static final Object a = new Object();
    private static volatile cy b;
    private cr c;

    public static cy a(Context context) {
        if (b == null) {
            synchronized (a) {
                if (b == null) {
                    b = new cy(context.getApplicationContext());
                }
            }
        }
        return b;
    }

    cy(Context context) {
        if (((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getPhoneType() == 0) {
            this.c = new cv();
        } else {
            this.c = new cu(context);
        }
    }

    public void a() {
        this.c.a();
    }

    public void b() {
        this.c.b();
    }

    public void a(da daVar) {
        this.c.a(daVar);
    }

    public void a(ct ctVar) {
        this.c.a(ctVar);
    }
}
