package com.yandex.metrica.impl;

import android.location.Location;
import com.yandex.metrica.e;
import com.yandex.metrica.e.a;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class n implements ab {
    private Integer a;
    private Boolean b;
    private Boolean c;
    private Location d;
    private Boolean e;
    private String f;
    private Boolean g;
    private Map<String, String> h = new HashMap();
    private Map<String, String> i = new HashMap();
    private boolean j;
    private boolean k;

    public Integer a() {
        return this.a;
    }

    public Boolean b() {
        return this.b;
    }

    public Boolean c() {
        return this.c;
    }

    public Location d() {
        return this.d;
    }

    public Boolean e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public Boolean g() {
        return this.g;
    }

    public boolean h() {
        if (this.g == null) {
            return false;
        }
        return this.g.booleanValue();
    }

    public void a(boolean z) {
        this.g = Boolean.valueOf(z);
    }

    public void a(String str) {
        this.f = str;
    }

    public void b(boolean z) {
        this.e = Boolean.valueOf(z);
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.a = Integer.valueOf(sessionTimeout);
    }

    public void c(boolean z) {
        this.b = Boolean.valueOf(z);
    }

    public void a(Location location) {
        this.d = location;
    }

    public void d(boolean z) {
        this.c = Boolean.valueOf(z);
    }

    public boolean i() {
        return this.j;
    }

    public void a(String str, String str2) {
        this.i.put(str, str2);
    }

    public e a(e eVar) {
        if (this.k) {
            return eVar;
        }
        boolean z;
        a a = e.a(eVar.getApiKey());
        a.a(eVar.e(), eVar.j());
        a.a(eVar.d());
        a.a(eVar.getPreloadInfo());
        a.c(eVar.a());
        a.a(eVar.getLocation());
        if (eVar.b() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.d(eVar.b());
        }
        if (eVar.getAppVersion() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.a(eVar.getAppVersion());
        }
        if (eVar.h() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.d(eVar.h().intValue());
        }
        if (eVar.c() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.b(eVar.c().intValue());
        }
        if (eVar.g() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.c(eVar.g().intValue());
        }
        if (eVar.isLogEnabled() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z && eVar.isLogEnabled().booleanValue()) {
            a.a();
        }
        if (eVar.getSessionTimeout() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.a(eVar.getSessionTimeout().intValue());
        }
        if (eVar.isReportCrashEnabled() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.a(eVar.isReportCrashEnabled().booleanValue());
        }
        if (eVar.isReportNativeCrashEnabled() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.b(eVar.isReportNativeCrashEnabled().booleanValue());
        }
        if (eVar.isTrackLocationEnabled() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.c(eVar.isTrackLocationEnabled().booleanValue());
        }
        if (eVar.isCollectInstalledApps() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.d(eVar.isCollectInstalledApps().booleanValue());
        }
        if (eVar.f() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            a.b(eVar.f());
        }
        a(eVar.i(), a);
        b(eVar.getErrorEnvironment(), a);
        Integer a2 = a();
        if (eVar.getSessionTimeout() == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (a2 != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a.a(a2.intValue());
            }
        }
        Boolean b = b();
        if (eVar.isReportCrashEnabled() == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (b != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a.a(b.booleanValue());
            }
        }
        b = c();
        if (eVar.isReportNativeCrashEnabled() == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (b != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a.b(b.booleanValue());
            }
        }
        b = e();
        if (eVar.isTrackLocationEnabled() == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (b != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a.c(b.booleanValue());
            }
        }
        Location d = d();
        if (eVar.getLocation() == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (d != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a.a(d);
            }
        }
        b = g();
        if (eVar.isCollectInstalledApps() == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (b != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a.d(b.booleanValue());
            }
        }
        String f = f();
        if (eVar.getAppVersion() == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (f != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a.a(f);
            }
        }
        a(this.h, a);
        b(this.i, a);
        this.k = true;
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h.clear();
        this.i.clear();
        this.j = false;
        return a.b();
    }

    private static void a(Map<String, String> map, a aVar) {
        if (!bg.a((Map) map)) {
            for (Entry entry : map.entrySet()) {
                aVar.b((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    private static void b(Map<String, String> map, a aVar) {
        if (!bg.a((Map) map)) {
            for (Entry entry : map.entrySet()) {
                aVar.a((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }
}
