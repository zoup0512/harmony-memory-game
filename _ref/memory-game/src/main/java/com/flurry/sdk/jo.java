package com.flurry.sdk;

import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Locale;

public final class jo {
    private static jo a;

    private jo() {
    }

    public static synchronized jo a() {
        jo joVar;
        synchronized (jo.class) {
            if (a == null) {
                a = new jo();
            }
            joVar = a;
        }
        return joVar;
    }

    public static String b() {
        return Locale.getDefault().getLanguage() + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + Locale.getDefault().getCountry();
    }
}
