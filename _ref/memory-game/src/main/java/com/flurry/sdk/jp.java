package com.flurry.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;

@SuppressLint({"MissingPermission"})
public class jp implements com.flurry.sdk.lq.a {
    private static final String a = jp.class.getSimpleName();
    private static int b = -1;
    private static int c = -1;
    private static int d = -1;
    private static jp e;
    private boolean f;
    private Location g;
    private long h = 0;
    private LocationManager i = ((LocationManager) jy.a().a.getSystemService("location"));
    private a j = new a(this);
    private Location k;
    private boolean l = false;
    private int m = 0;
    private kh<ls> n = new kh<ls>(this) {
        final /* synthetic */ jp a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(kg kgVar) {
            if (this.a.h > 0 && this.a.h < System.currentTimeMillis()) {
                km.a(4, jp.a, "No location received in 90 seconds , stopping LocationManager");
                this.a.i();
            }
        }
    };

    class a implements LocationListener {
        final /* synthetic */ jp a;

        public a(jp jpVar) {
            this.a = jpVar;
        }

        public final void onLocationChanged(Location location) {
            if (location != null) {
                this.a.k = location;
            }
            if (jp.c(this.a) >= 3) {
                km.a(4, jp.a, "Max location reports reached, stopping");
                this.a.i();
            }
        }

        public final void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public final void onProviderEnabled(String str) {
        }

        public final void onProviderDisabled(String str) {
        }
    }

    static /* synthetic */ int c(jp jpVar) {
        int i = jpVar.m + 1;
        jpVar.m = i;
        return i;
    }

    private jp() {
        lq a = lp.a();
        this.f = ((Boolean) a.a("ReportLocation")).booleanValue();
        a.a("ReportLocation", (com.flurry.sdk.lq.a) this);
        km.a(4, a, "initSettings, ReportLocation = " + this.f);
        this.g = (Location) a.a("ExplicitLocation");
        a.a("ExplicitLocation", (com.flurry.sdk.lq.a) this);
        km.a(4, a, "initSettings, ExplicitLocation = " + this.g);
    }

    public static synchronized jp a() {
        jp jpVar;
        synchronized (jp.class) {
            if (e == null) {
                e = new jp();
            }
            jpVar = e;
        }
        return jpVar;
    }

    public static int b() {
        return b;
    }

    public static int c() {
        return c;
    }

    public static int d() {
        return d;
    }

    public final synchronized void e() {
        km.a(4, a, "Location update requested");
        if (this.m < 3 && !this.l && this.f && this.g == null) {
            Context context = jy.a().a;
            if (context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
                this.m = 0;
                String str = null;
                if (a(context)) {
                    str = "passive";
                } else if (b(context)) {
                    str = "network";
                }
                if (!TextUtils.isEmpty(str)) {
                    this.i.requestLocationUpdates(str, 10000, 0.0f, this.j, Looper.getMainLooper());
                }
                this.k = a(str);
                this.h = System.currentTimeMillis() + 90000;
                km.a(4, a, "Register location timer");
                lt.a().a(this.n);
                this.l = true;
                km.a(4, a, "LocationProvider started");
            }
        }
    }

    private static boolean a(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    private static boolean b(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    private Location a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.i.getLastKnownLocation(str);
    }

    public final synchronized void f() {
        km.a(4, a, "Stop update location requested");
        i();
    }

    private void i() {
        if (this.l) {
            this.i.removeUpdates(this.j);
            this.m = 0;
            this.h = 0;
            km.a(4, a, "Unregister location timer");
            lt.a().b(this.n);
            this.l = false;
            km.a(4, a, "LocationProvider stopped");
        }
    }

    public final Location g() {
        Location location = null;
        if (this.g != null) {
            return this.g;
        }
        if (this.f) {
            Context context = jy.a().a;
            if (!a(context) && !b(context)) {
                return null;
            }
            String str = a(context) ? "passive" : b(context) ? "network" : null;
            if (str != null) {
                location = a(str);
                if (location != null) {
                    this.k = location;
                }
                location = this.k;
            }
        }
        km.a(4, a, "getLocation() = " + location);
        return location;
    }

    public final void a(String str, Object obj) {
        Object obj2 = -1;
        switch (str.hashCode()) {
            case -864112343:
                if (str.equals("ReportLocation")) {
                    obj2 = null;
                    break;
                }
                break;
            case -300729815:
                if (str.equals("ExplicitLocation")) {
                    obj2 = 1;
                    break;
                }
                break;
        }
        switch (obj2) {
            case null:
                this.f = ((Boolean) obj).booleanValue();
                km.a(4, a, "onSettingUpdate, ReportLocation = " + this.f);
                return;
            case 1:
                this.g = (Location) obj;
                km.a(4, a, "onSettingUpdate, ExplicitLocation = " + this.g);
                return;
            default:
                km.a(6, a, "LocationProvider internal error! Had to be LocationCriteria, ReportLocation or ExplicitLocation key.");
                return;
        }
    }
}
