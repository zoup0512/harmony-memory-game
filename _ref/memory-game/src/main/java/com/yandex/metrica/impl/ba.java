package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import com.yandex.metrica.IMetricaService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ba {

    public static class a implements Comparable<a> {
        public final int a;
        public final int b;
        public final long c;
        public final ServiceInfo d;
        public final String e;

        public /* synthetic */ int compareTo(Object obj) {
            return a((a) obj);
        }

        public a(ServiceInfo serviceInfo, int i, int i2, long j) {
            this.a = i2;
            this.b = i;
            this.d = serviceInfo;
            this.c = j;
            this.e = serviceInfo.applicationInfo.packageName;
        }

        public int a(a aVar) {
            if (this.b != aVar.b) {
                return Integer.valueOf(this.b).compareTo(Integer.valueOf(aVar.b));
            }
            if (this.c != aVar.c) {
                return Long.valueOf(this.c).compareTo(Long.valueOf(aVar.c));
            }
            return 0;
        }

        public String toString() {
            return "MetricaServiceDescriptor{apiLevel=" + this.a + ", score=" + this.b + ", timeInstalled=" + this.c + '}';
        }
    }

    public static Intent a(Context context) {
        Intent intent = new Intent(IMetricaService.class.getName(), Uri.parse("metrica://" + context.getPackageName()));
        if (bg.b(11)) {
            intent.addFlags(32);
        }
        return intent;
    }

    public static List<ResolveInfo> a(Context context, Intent intent) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 128);
        return queryIntentServices != null ? queryIntentServices : new ArrayList();
    }

    public static List<a> b(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<a> arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : a(context, a(context))) {
            int i;
            PackageItemInfo packageItemInfo = resolveInfo.serviceInfo;
            int i2 = !packageItemInfo.enabled ? 1 : 0;
            if (packageItemInfo.exported) {
                i = 0;
            } else {
                i = 1;
            }
            i |= i2;
            if (be.a(packageItemInfo.permission)) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            if ((i2 | i) == 0) {
                long a = bg.a(packageManager, packageItemInfo.packageName);
                if (ah.a(packageManager, packageItemInfo.packageName, "android.permission.INTERNET")) {
                    int a2 = a(packageItemInfo);
                    i = (a2 << 5) + ((ah.a(packageManager, packageItemInfo.packageName, "android.permission.ACCESS_COARSE_LOCATION") ? 1 : 0) * 16);
                    if (ah.a(packageManager, packageItemInfo.packageName, "android.permission.ACCESS_FINE_LOCATION")) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    i += i2 * 8;
                    if (ah.a(packageManager, packageItemInfo.packageName, "android.permission.ACCESS_WIFI_STATE")) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    i += i2 * 4;
                    if (ah.a(packageManager, packageItemInfo.packageName, "android.permission.ACCESS_NETWORK_STATE")) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    i += i2 * 2;
                    if (ah.a(packageManager, packageItemInfo.packageName, "android.permission.READ_PHONE_STATE")) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    arrayList.add(new a(resolveInfo.serviceInfo, i + (i2 * 1), a2, a));
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static int a(PackageItemInfo packageItemInfo) {
        if (packageItemInfo.metaData != null) {
            return packageItemInfo.metaData.getInt("metrica:api:level");
        }
        return -1;
    }

    public static Intent c(Context context) {
        return a(context).putExtras(d(context)).setPackage(context.getApplicationContext().getPackageName());
    }

    private static Bundle d(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                return new Bundle();
            }
            return bundle;
        } catch (Exception e) {
            return new Bundle();
        }
    }
}
