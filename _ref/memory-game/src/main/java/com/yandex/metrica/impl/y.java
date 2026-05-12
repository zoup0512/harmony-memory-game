package com.yandex.metrica.impl;

import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Parcel;
import com.yandex.metrica.impl.d.a;
import com.yandex.metrica.impl.ob.bc;
import com.yandex.metrica.impl.ob.bl;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public final class y implements d {
    static final long a = TimeUnit.SECONDS.toMillis(300);
    static final long b = TimeUnit.SECONDS.toMillis(120);
    static final Set<String> c = new HashSet(Arrays.asList(new String[]{"gps"}));
    private static volatile y i;
    private static final Object j = new Object();
    private final Context d;
    private final HandlerThread e;
    private final LocationManager f;
    private final WeakHashMap<Object, Object> g;
    private boolean h;
    private a<Location> k = new a();
    private boolean l = false;
    private bl m;
    private LocationListener n = new LocationListener(this) {
        final /* synthetic */ y a;

        {
            this.a = r1;
        }

        public void onLocationChanged(Location location) {
            this.a.a(location);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };

    private y(Context context) {
        this.d = context;
        this.g = new WeakHashMap();
        this.h = false;
        this.e = new HandlerThread("LHandlerThread");
        this.e.start();
        this.f = (LocationManager) context.getSystemService("location");
        this.m = new bl(bc.a(this.d).b());
        this.l = this.m.c();
    }

    public static y a(Context context) {
        if (i == null) {
            synchronized (j) {
                if (i == null) {
                    i = new y(context.getApplicationContext());
                }
            }
        }
        return i;
    }

    public synchronized void a(Object obj) {
        if (this.l && ah.a(this.d.getPackageManager(), this.d.getPackageName(), "android.permission.ACCESS_COARSE_LOCATION")) {
            this.g.put(obj, null);
            if (!this.h) {
                this.h = true;
                a("network", 0.0f, a, this.n, this.e.getLooper());
                a("passive", 0.0f, a, this.n, this.e.getLooper());
            }
        }
    }

    private void a(String str, float f, long j, LocationListener locationListener, Looper looper) {
        try {
            if (this.f != null && b(this.d)) {
                this.f.requestLocationUpdates(str, j, f, locationListener, looper);
            }
        } catch (Exception e) {
        }
    }

    public synchronized void b(Object obj) {
        this.g.remove(obj);
        b();
    }

    synchronized void a() {
        this.g.clear();
        b();
    }

    void b() {
        if (this.h && this.g.isEmpty()) {
            this.h = false;
            try {
                if (this.f != null) {
                    this.f.removeUpdates(this.n);
                }
            } catch (Exception e) {
            }
        }
    }

    public synchronized void a(Location location) {
        if (this.k.a()) {
            this.k.a(e());
        }
        if (a(location, (Location) this.k.b())) {
            Location location2;
            if (location == null) {
                location2 = null;
            } else {
                location2 = new Location(location);
            }
            this.k.a(location2);
            c(location2);
        }
    }

    synchronized Location c() {
        if (this.k.a()) {
            this.k.a(e());
        }
        return (Location) this.k.b();
    }

    private synchronized void c(Location location) {
        try {
            ay.a(this.d).a(b(location));
        } catch (Exception e) {
        }
    }

    private synchronized Location e() {
        Cursor a;
        Throwable th;
        Location location = null;
        synchronized (this) {
            try {
                a = ay.a(this.d).a();
                if (a != null) {
                    try {
                        if (a.moveToFirst()) {
                            location = a(a.getBlob(a.getColumnIndex("GeoLocation")));
                            bg.a(a);
                        }
                    } catch (Exception e) {
                        bg.a(a);
                        return location;
                    } catch (Throwable th2) {
                        th = th2;
                        bg.a(a);
                        throw th;
                    }
                }
                bg.a(a);
            } catch (Exception e2) {
                a = null;
                bg.a(a);
                return location;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                a = null;
                th = th4;
                bg.a(a);
                throw th;
            }
        }
        return location;
    }

    public Location d() {
        if (this.f == null) {
            return null;
        }
        Location location = null;
        for (String str : this.f.getAllProviders()) {
            Location lastKnownLocation;
            if (!c.contains(str)) {
                try {
                    if (b(this.d)) {
                        lastKnownLocation = this.f.getLastKnownLocation(str);
                        if (lastKnownLocation != null && a(lastKnownLocation, location)) {
                            location = lastKnownLocation;
                        }
                    }
                } catch (Exception e) {
                }
                lastKnownLocation = null;
                location = lastKnownLocation;
            }
            lastKnownLocation = location;
            location = lastKnownLocation;
        }
        return location;
    }

    private static boolean b(Context context) {
        if (!bg.a(23) || context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 || context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
            return true;
        }
        return false;
    }

    static boolean a(Location location, Location location2) {
        if (location2 == null) {
            return true;
        }
        if (location == null) {
            return false;
        }
        boolean z;
        boolean z2;
        long time = location.getTime() - location2.getTime();
        boolean z3 = time > b;
        if (time < (-b)) {
            z = true;
        } else {
            z = false;
        }
        if (time > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z3) {
            return true;
        }
        if (z) {
            return false;
        }
        boolean z4;
        int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
        boolean z5 = accuracy > 0;
        if (accuracy < 0) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (((long) accuracy) > 200) {
            z3 = true;
        } else {
            z3 = false;
        }
        String provider = location.getProvider();
        String provider2 = location2.getProvider();
        z = provider == null ? provider2 == null : provider.equals(provider2);
        if (z4) {
            return true;
        }
        if (z2 && !z5) {
            return true;
        }
        if (z2 && !r0 && r3) {
            return true;
        }
        return false;
    }

    public static byte[] b(Location location) {
        Parcel obtain = Parcel.obtain();
        byte[] bArr = new byte[0];
        try {
            obtain.writeValue(location);
            bArr = obtain.marshall();
        } catch (Exception e) {
        } finally {
            obtain.recycle();
        }
        return bArr;
    }

    public static Location a(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        Location location;
        try {
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            location = (Location) obtain.readValue(Location.class.getClassLoader());
            return location;
        } catch (Exception e) {
            location = e;
            return null;
        } finally {
            obtain.recycle();
        }
    }

    public void a(Object obj, boolean z, boolean z2) {
        if (this.l == z2) {
            return;
        }
        if (z) {
            this.l = z2;
            this.m.a(this.l);
            if (this.l) {
                a(obj);
            } else {
                a();
            }
        } else if (!z2) {
            b(obj);
        }
    }
}
