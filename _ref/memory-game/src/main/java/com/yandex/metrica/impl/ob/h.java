package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.be;
import com.yandex.metrica.impl.bg;
import io.fabric.sdk.android.services.events.EventsFilesManager;

public class h {
    private boolean a;
    private final String b;
    private final String c;

    public h(String str, String str2, boolean z) {
        this.a = z;
        this.b = str;
        this.c = str2;
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.b;
    }

    public boolean c() {
        return (this.a || bg.c(this.c)) ? false : true;
    }

    public String toString() {
        String str = this.b;
        if (this.a) {
            return str;
        }
        return str + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + this.c;
    }

    public static h a(Context context, CounterConfiguration counterConfiguration, Integer num, String str) {
        String f = counterConfiguration.f();
        if (be.a(f)) {
            if (num != null) {
                String[] packagesForUid = context.getPackageManager().getPackagesForUid(num.intValue());
                if (packagesForUid == null || packagesForUid.length <= 0) {
                    f = null;
                } else {
                    f = packagesForUid[0];
                }
            } else {
                f = str;
            }
        }
        if (be.a(f)) {
            return null;
        }
        return new h(f, bg.a(context, counterConfiguration, f), counterConfiguration.A());
    }

    public static h a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new h(str, null, true);
    }
}
