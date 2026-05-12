package com.flurry.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class jr extends BroadcastReceiver {
    private static jr c;
    boolean a;
    public boolean b;
    private boolean d = false;

    public enum a {
        ;

        static {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
            e = new int[]{a, b, c, d};
        }
    }

    private jr() {
        boolean z = false;
        Context context = jy.a().a;
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0) {
            z = true;
        }
        this.d = z;
        this.b = a(context);
        if (this.d) {
            c();
        }
    }

    private boolean a(Context context) {
        if (!this.d || context == null) {
            return true;
        }
        NetworkInfo activeNetworkInfo = d().getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    private synchronized void c() {
        if (!this.a) {
            Context context = jy.a().a;
            this.b = a(context);
            context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.a = true;
        }
    }

    private static ConnectivityManager d() {
        return (ConnectivityManager) jy.a().a.getSystemService("connectivity");
    }

    public static synchronized jr a() {
        jr jrVar;
        synchronized (jr.class) {
            if (c == null) {
                c = new jr();
            }
            jrVar = c;
        }
        return jrVar;
    }

    public final void onReceive(Context context, Intent intent) {
        boolean a = a(context);
        if (this.b != a) {
            this.b = a;
            kg jqVar = new jq();
            jqVar.a = a;
            jqVar.b = b();
            ki.a().a(jqVar);
        }
    }

    public final int b() {
        if (!this.d) {
            return a.a;
        }
        NetworkInfo activeNetworkInfo = d().getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return a.a;
        }
        switch (activeNetworkInfo.getType()) {
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
                return a.d;
            case 1:
                return a.c;
            case 8:
                return a.a;
            default:
                if (activeNetworkInfo.isConnected()) {
                    return a.b;
                }
                return a.a;
        }
    }
}
