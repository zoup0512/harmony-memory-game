package com.yandex.metrica.impl.interact;

import android.content.Context;
import android.text.TextUtils;
import com.yandex.metrica.impl.ob.cs;
import com.yandex.metrica.impl.ob.cy;
import com.yandex.metrica.impl.ob.cz;
import com.yandex.metrica.impl.ob.da;
import java.util.HashMap;
import java.util.Map.Entry;

public class CellularNetworkInfo {
    private String a = "";

    public CellularNetworkInfo(Context context) {
        cy.a(context).a(new da(this) {
            final /* synthetic */ CellularNetworkInfo a;

            {
                this.a = r1;
            }

            public void a(cz czVar) {
                Object valueOf;
                Object obj = null;
                cs b = czVar.b();
                String g = b.g();
                String f = b.f();
                Integer c = b.c();
                Integer b2 = b.b();
                Integer e = b.e();
                Integer d = b.d();
                Integer a = b.a();
                HashMap hashMap = new HashMap();
                hashMap.put("network_type", g);
                hashMap.put("operator_name", f);
                hashMap.put("country_code", b2 != null ? String.valueOf(b2) : null);
                g = "operator_id";
                if (c != null) {
                    valueOf = String.valueOf(c);
                } else {
                    valueOf = null;
                }
                hashMap.put(g, valueOf);
                g = "cell_id";
                if (e != null) {
                    valueOf = String.valueOf(e);
                } else {
                    valueOf = null;
                }
                hashMap.put(g, valueOf);
                g = "lac";
                if (d != null) {
                    valueOf = String.valueOf(d);
                } else {
                    valueOf = null;
                }
                hashMap.put(g, valueOf);
                String str = "signal_strength";
                if (a != null) {
                    obj = String.valueOf(a);
                }
                hashMap.put(str, obj);
                StringBuilder stringBuilder = new StringBuilder();
                g = "";
                for (Entry entry : hashMap.entrySet()) {
                    String str2 = (String) entry.getValue();
                    if (TextUtils.isEmpty(str2)) {
                        str = g;
                    } else {
                        stringBuilder.append(g);
                        stringBuilder.append((String) entry.getKey());
                        stringBuilder.append("=");
                        stringBuilder.append(str2);
                        str = "&";
                    }
                    g = str;
                }
                this.a.a = stringBuilder.toString();
            }
        });
    }

    public String getCelluralInfo() {
        return this.a;
    }
}
