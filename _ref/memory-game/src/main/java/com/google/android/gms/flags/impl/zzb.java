package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.internal.zzua;
import java.util.concurrent.Callable;

public class zzb {
    private static SharedPreferences Pc = null;

    class AnonymousClass1 implements Callable<SharedPreferences> {
        final /* synthetic */ Context zzala;

        AnonymousClass1(Context context) {
            this.zzala = context;
        }

        public /* synthetic */ Object call() throws Exception {
            return zzbex();
        }

        public SharedPreferences zzbex() {
            return this.zzala.getSharedPreferences("google_sdk_flags", 1);
        }
    }

    public static SharedPreferences zzn(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (SharedPreferences.class) {
            if (Pc == null) {
                Pc = (SharedPreferences) zzua.zzb(new AnonymousClass1(context));
            }
            sharedPreferences = Pc;
        }
        return sharedPreferences;
    }
}
