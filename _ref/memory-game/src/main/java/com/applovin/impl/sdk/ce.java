package com.applovin.impl.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkSettings;
import com.yalantis.ucrop.util.FileUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.JSONObject;

class ce {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final Context c;
    private final Object[] d = new Object[cb.b()];

    ce(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.getLogger();
        this.c = appLovinSdkImpl.getApplicationContext();
    }

    private static cd a(String str) {
        for (cd cdVar : cb.a()) {
            if (cdVar.b().equals(str)) {
                return cdVar;
            }
        }
        return null;
    }

    private static Object a(String str, JSONObject jSONObject, Object obj) {
        if (obj instanceof Boolean) {
            return Boolean.valueOf(jSONObject.getBoolean(str));
        }
        if (obj instanceof Float) {
            return Float.valueOf((float) jSONObject.getDouble(str));
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(jSONObject.getInt(str));
        }
        if (obj instanceof Long) {
            return Long.valueOf(jSONObject.getLong(str));
        }
        if (obj instanceof String) {
            return jSONObject.getString(str);
        }
        throw new RuntimeException("SDK Error: unknown value type: " + obj.getClass());
    }

    private String e() {
        return "com.applovin.sdk." + dm.a(this.a.getSdkKey()) + FileUtils.HIDDEN_PREFIX;
    }

    public SharedPreferences a() {
        if (this.c != null) {
            return this.c.getSharedPreferences("com.applovin.sdk.1", 0);
        }
        throw new IllegalArgumentException("No context specified");
    }

    public Object a(cd cdVar) {
        if (cdVar == null) {
            throw new IllegalArgumentException("No setting type specified");
        }
        Object obj;
        synchronized (this.d) {
            try {
                obj = this.d[cdVar.a()];
                if (obj != null) {
                    obj = cdVar.a(obj);
                } else {
                    obj = cdVar.c();
                }
            } catch (Throwable th) {
                this.a.getLogger().e("SettingsManager", "Unable to retrieve value for setting " + cdVar.b() + "; using default...");
                obj = cdVar.c();
            }
        }
        return obj;
    }

    public void a(cd cdVar, Object obj) {
        if (cdVar == null) {
            throw new IllegalArgumentException("No setting type specified");
        } else if (obj == null) {
            throw new IllegalArgumentException("No new value specified");
        } else {
            synchronized (this.d) {
                this.d[cdVar.a()] = obj;
            }
            this.b.d("SettingsManager", "Setting update: " + cdVar.b() + " set to \"" + obj + "\"");
        }
    }

    void a(AppLovinSdkSettings appLovinSdkSettings) {
        long j = 0;
        boolean z = false;
        this.b.i("SettingsManager", "Loading user-defined settings...");
        if (appLovinSdkSettings != null) {
            synchronized (this.d) {
                boolean z2;
                boolean z3;
                this.d[cb.i.a()] = Boolean.valueOf(appLovinSdkSettings.isVerboseLoggingEnabled());
                long bannerAdRefreshSeconds = appLovinSdkSettings.getBannerAdRefreshSeconds();
                if (bannerAdRefreshSeconds >= 0) {
                    if (bannerAdRefreshSeconds > 0) {
                        j = Math.max(30, bannerAdRefreshSeconds);
                    }
                    this.d[cb.u.a()] = Long.valueOf(j);
                    this.d[cb.t.a()] = Boolean.valueOf(true);
                } else if (bannerAdRefreshSeconds == -1) {
                    this.d[cb.t.a()] = Boolean.valueOf(false);
                }
                String autoPreloadSizes = appLovinSdkSettings.getAutoPreloadSizes();
                if (autoPreloadSizes == null) {
                    autoPreloadSizes = "NONE";
                }
                Object[] objArr = this.d;
                int a = cb.D.a();
                if (autoPreloadSizes.equals("NONE")) {
                    autoPreloadSizes = "";
                }
                objArr[a] = autoPreloadSizes;
                autoPreloadSizes = appLovinSdkSettings.getAutoPreloadTypes();
                if (autoPreloadSizes == null) {
                    autoPreloadSizes = "NONE";
                }
                if (autoPreloadSizes.equals("NONE")) {
                    z2 = false;
                    z3 = false;
                } else {
                    z2 = false;
                    z3 = false;
                    for (String str : autoPreloadSizes.split(",")) {
                        if (str.equals(AppLovinAdType.REGULAR.getLabel())) {
                            z3 = true;
                        } else if (str.equals(AppLovinAdType.INCENTIVIZED.getLabel()) || str.contains("INCENT") || str.contains("REWARD")) {
                            z2 = true;
                        } else if (str.equals(NativeAdImpl.TYPE_NATIVE.getLabel())) {
                            z = true;
                        }
                    }
                }
                if (!z3) {
                    this.d[cb.D.a()] = "";
                }
                this.d[cb.E.a()] = Boolean.valueOf(z2);
                this.d[cb.aE.a()] = Boolean.valueOf(z);
                if (appLovinSdkSettings instanceof bb) {
                    for (Entry entry : ((bb) appLovinSdkSettings).b().entrySet()) {
                        this.d[((cd) entry.getKey()).a()] = entry.getValue();
                    }
                }
            }
        }
    }

    void a(JSONObject jSONObject) {
        this.b.d("SettingsManager", "Loading settings from JSON array...");
        synchronized (this.d) {
            String str = "";
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                str = (String) keys.next();
                if (str != null && str.length() > 0) {
                    try {
                        cd a = a(str);
                        if (a != null) {
                            Object a2 = a(str, jSONObject, a.c());
                            this.d[a.a()] = a2;
                            this.b.d("SettingsManager", "Setting update: " + a.b() + " set to \"" + a2 + "\"");
                        } else {
                            this.b.w("SettingsManager", "Unknown setting recieved: " + str);
                        }
                    } catch (Throwable e) {
                        this.b.e("SettingsManager", "Unable to parse JSON settings array", e);
                    } catch (Throwable e2) {
                        this.b.e("SettingsManager", "Unable to convert setting object ", e2);
                    }
                }
            }
        }
    }

    void b() {
        if (this.c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        this.b.i("SettingsManager", "Saving settings with the application...");
        String e = e();
        Editor edit = a().edit();
        synchronized (this.d) {
            for (cd cdVar : cb.a()) {
                Object obj = this.d[cdVar.a()];
                if (obj != null) {
                    String str = e + cdVar.b();
                    if (obj instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof Float) {
                        edit.putFloat(str, ((Float) obj).floatValue());
                    } else if (obj instanceof Integer) {
                        edit.putInt(str, ((Integer) obj).intValue());
                    } else if (obj instanceof Long) {
                        edit.putLong(str, ((Long) obj).longValue());
                    } else if (obj instanceof String) {
                        edit.putString(str, (String) obj);
                    } else {
                        throw new RuntimeException("SDK Error: unknown value: " + obj.getClass());
                    }
                }
            }
        }
        edit.commit();
        this.b.d("SettingsManager", "Settings saved with the application.");
    }

    void c() {
        if (this.c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        this.b.i("SettingsManager", "Loading settings saved with the application...");
        String e = e();
        SharedPreferences a = a();
        synchronized (this.d) {
            for (cd cdVar : cb.a()) {
                try {
                    Boolean valueOf;
                    String str = e + cdVar.b();
                    Object c = cdVar.c();
                    if (c instanceof Boolean) {
                        valueOf = Boolean.valueOf(a.getBoolean(str, ((Boolean) c).booleanValue()));
                    } else if (c instanceof Float) {
                        valueOf = Float.valueOf(a.getFloat(str, ((Float) c).floatValue()));
                    } else if (c instanceof Integer) {
                        valueOf = Integer.valueOf(a.getInt(str, ((Integer) c).intValue()));
                    } else if (c instanceof Long) {
                        valueOf = Long.valueOf(a.getLong(str, ((Long) c).longValue()));
                    } else if (c instanceof String) {
                        valueOf = a.getString(str, (String) c);
                    } else {
                        throw new RuntimeException("SDK Error: unknown value: " + c.getClass());
                    }
                    this.d[cdVar.a()] = valueOf;
                } catch (Throwable e2) {
                    this.b.e("SettingsManager", "Unable to load \"" + cdVar.b() + "\"", e2);
                }
            }
        }
    }

    void d() {
        synchronized (this.d) {
            Arrays.fill(this.d, null);
        }
        Editor edit = a().edit();
        edit.clear();
        edit.commit();
    }
}
