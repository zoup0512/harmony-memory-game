package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Libraries.e.a;
import com.facebook.places.model.PlaceFields;
import java.util.Date;
import java.util.Locale;

public class as {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public String e;
    public String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final String m;
    public final String n;
    public final String o;
    public final String p;
    public final a q;
    public final boolean r;
    public final String s;
    public final Integer t;

    public as(Context context, String str) {
        a a;
        int width;
        int height;
        Throwable th;
        DisplayMetrics displayMetrics;
        int i;
        Object obj = null;
        int i2 = 0;
        this.o = str;
        if ("sdk".equals(Build.PRODUCT)) {
            this.a = "Android Simulator";
        } else {
            this.a = Build.MODEL;
        }
        this.p = Build.MANUFACTURER + " " + Build.MODEL;
        this.b = "Android " + VERSION.RELEASE;
        this.c = Locale.getDefault().getCountry();
        this.d = Locale.getDefault().getLanguage();
        this.g = "6.5.1";
        this.m = String.valueOf(Long.valueOf(new Date().getTime() / 1000).intValue());
        this.n = "" + context.getResources().getDisplayMetrics().density;
        try {
            String packageName = context.getPackageName();
            this.e = context.getPackageManager().getPackageInfo(packageName, 128).versionName;
            this.f = packageName;
        } catch (Throwable e) {
            CBLogging.b("RequestBody", "Exception raised getting package mager object", e);
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
        if (telephonyManager == null || telephonyManager.getPhoneType() == 0) {
            a = a.a();
        } else {
            Object obj2;
            String simOperator = telephonyManager.getSimOperator();
            if (TextUtils.isEmpty(simOperator)) {
                obj2 = null;
            } else {
                obj2 = simOperator.substring(0, 3);
                obj = simOperator.substring(3);
            }
            a = e.a(e.a("carrier-name", telephonyManager.getNetworkOperatorName()), e.a("mobile-country-code", obj2), e.a("mobile-network-code", obj), e.a("iso-country-code", telephonyManager.getNetworkCountryIso()), e.a("phone-type", Integer.valueOf(telephonyManager.getPhoneType())));
        }
        this.q = a;
        this.r = CBUtility.d();
        this.s = CBUtility.e();
        this.t = ac.d(context);
        try {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                Rect rect = new Rect();
                activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                width = rect.width();
                try {
                    height = rect.height();
                    i2 = width;
                } catch (Throwable e2) {
                    Throwable th2 = e2;
                    height = width;
                    th = th2;
                    CBLogging.c("RequestBody", "Exception getting activity size", th);
                    i2 = height;
                    height = 0;
                    displayMetrics = context.getResources().getDisplayMetrics();
                    i = displayMetrics.widthPixels;
                    width = displayMetrics.heightPixels;
                    this.j = "" + i;
                    this.k = "" + width;
                    this.l = "" + displayMetrics.densityDpi;
                    i2 = i;
                    height = width;
                    this.h = "" + i2;
                    this.i = "" + height;
                }
            }
            height = 0;
        } catch (Throwable e22) {
            th = e22;
            height = 0;
            CBLogging.c("RequestBody", "Exception getting activity size", th);
            i2 = height;
            height = 0;
            displayMetrics = context.getResources().getDisplayMetrics();
            i = displayMetrics.widthPixels;
            width = displayMetrics.heightPixels;
            this.j = "" + i;
            this.k = "" + width;
            this.l = "" + displayMetrics.densityDpi;
            i2 = i;
            height = width;
            this.h = "" + i2;
            this.i = "" + height;
        }
        displayMetrics = context.getResources().getDisplayMetrics();
        i = displayMetrics.widthPixels;
        width = displayMetrics.heightPixels;
        this.j = "" + i;
        this.k = "" + width;
        this.l = "" + displayMetrics.densityDpi;
        if (i2 <= 0 || i2 > i) {
            i2 = i;
        }
        if (height <= 0 || height > width) {
            height = width;
        }
        this.h = "" + i2;
        this.i = "" + height;
    }
}
