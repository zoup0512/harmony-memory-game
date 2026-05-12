package com.my.target.core.providers;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.places.model.PlaceFields;
import com.my.target.Tracer;
import com.my.target.core.utils.f;
import com.my.target.core.utils.k;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

/* compiled from: DeviceParamsDataProvider */
public final class b extends a {
    private boolean a = false;
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";
    private String f = "";
    private String g = "";
    private String h = "";
    private String i = "";
    private String j = "";
    private String k = "";
    private String l = "";
    private int m = 0;
    private int n = 0;
    private int o = 0;
    private float p = 0.0f;
    private String q = "";
    private String r = "";
    private String s = "";
    private String t = "";
    private String u = "";
    private Map<String, String> v = new HashMap();

    public final int a() {
        return this.m;
    }

    public final int b() {
        return this.n;
    }

    public final synchronized void a(Context context) {
        if (!this.a) {
            Tracer.d("collect application info...");
            this.b = Build.DEVICE;
            this.h = Build.MANUFACTURER;
            this.i = Build.MODEL;
            this.d = VERSION.RELEASE;
            this.e = context.getPackageName();
            this.j = Locale.getDefault().getLanguage();
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(this.e, 0);
                this.f = packageInfo.versionName;
                this.g = Integer.toString(packageInfo.versionCode);
            } catch (NameNotFoundException e) {
            }
            ContentResolver contentResolver = context.getContentResolver();
            if (contentResolver != null) {
                this.c = Secure.getString(contentResolver, "android_id");
                if (this.c == null) {
                    this.c = "";
                }
            }
            this.k = context.getResources().getConfiguration().locale.getLanguage();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
            this.l = telephonyManager.getSimCountryIso();
            this.q = telephonyManager.getNetworkOperator();
            this.r = telephonyManager.getNetworkOperatorName();
            if (telephonyManager.getSimState() == 5) {
                this.s = telephonyManager.getSimOperator();
            }
            b(context);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            this.o = displayMetrics.densityDpi;
            this.p = displayMetrics.density;
            TimeZone timeZone = TimeZone.getDefault();
            this.t = timeZone.getDisplayName(false, 0) + " " + timeZone.getID();
            this.u = c(context);
            addParam("android_id", this.c);
            addParam("device", this.b);
            addParam("os", "Android");
            addParam("manufacture", this.h);
            addParam("osver", this.d);
            addParam(SettingsJsonConstants.APP_KEY, this.e);
            addParam("appver", this.f);
            addParam("appbuild", this.g);
            addParam("lang", this.j);
            addParam("app_lang", this.k);
            addParam("sim_loc", this.l);
            addParam("euname", this.i);
            addParam("w", this.m);
            addParam("h", this.n);
            addParam("dpi", this.o);
            addParam("density", this.p);
            addParam("operator_id", this.q);
            addParam("operator_name", this.r);
            addParam("sim_operator_id", this.s);
            addParam("timezone", this.t);
            addParam("mrgs_device_id", this.u);
            this.v.put("android_id", this.c);
            this.v.put("euname", this.i);
            this.v.put("os", "Android");
            this.v.put("osver", this.d);
            this.v.put("manufacture", this.h);
            for (Entry entry : getMap().entrySet()) {
                Tracer.d(((String) entry.getKey()) + " = " + ((String) entry.getValue()));
            }
            this.a = true;
            Tracer.d("collected");
        }
    }

    public final void b(Context context) {
        if (!this.a) {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            if (VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealSize(point);
            } else if (VERSION.SDK_INT >= 13) {
                defaultDisplay.getSize(point);
            } else {
                point.x = defaultDisplay.getWidth();
                point.y = defaultDisplay.getHeight();
            }
            this.m = point.x;
            this.n = point.y;
        }
    }

    private String c(Context context) {
        k a;
        Throwable th;
        String str;
        Object obj;
        String str2 = null;
        try {
            a = k.a().a(context);
            try {
                str2 = a.b();
            } catch (Throwable th2) {
                th = th2;
                Tracer.d("PreferencesManager error");
                th.printStackTrace();
                if (TextUtils.isEmpty(str2)) {
                    str2 = VERSION.SDK_INT >= 9 ? "sdk < 9" : Build.SERIAL;
                    str = "";
                    if (context.checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS") == 0) {
                        obj = null;
                    } else {
                        obj = 1;
                    }
                    if (obj != null) {
                        str = d(context);
                    }
                    str2 = f.a(this.c + str2 + str);
                    if (a != null) {
                        a.a(str2);
                    }
                }
                return str2;
            }
        } catch (Throwable th3) {
            th = th3;
            a = str2;
            Tracer.d("PreferencesManager error");
            th.printStackTrace();
            if (TextUtils.isEmpty(str2)) {
                if (VERSION.SDK_INT >= 9) {
                }
                str = "";
                if (context.checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS") == 0) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    str = d(context);
                }
                str2 = f.a(this.c + str2 + str);
                if (a != null) {
                    a.a(str2);
                }
            }
            return str2;
        }
        if (TextUtils.isEmpty(str2)) {
            if (VERSION.SDK_INT >= 9) {
            }
            str = "";
            if (context.checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS") == 0) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                str = d(context);
            }
            str2 = f.a(this.c + str2 + str);
            if (a != null) {
                a.a(str2);
            }
        }
        return str2;
    }

    private static String d(Context context) {
        Account[] accountArr = null;
        try {
            accountArr = AccountManager.get(context).getAccountsByType("com.google");
        } catch (Throwable th) {
        }
        if (accountArr == null || accountArr.length <= 0) {
            return "";
        }
        return accountArr[0].name;
    }
}
