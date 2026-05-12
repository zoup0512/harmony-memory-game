package com.cmcm.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.cmcm.adsdk.CMAdManager;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PackageManagerWrapper */
public class j {
    private static j a = new j();
    private Context b = CMAdManager.getContext();
    private PackageManager c = this.b.getPackageManager();
    private List<PackageInfo> d;
    private Object e = new Object();

    public static synchronized j a() {
        j jVar;
        synchronized (j.class) {
            jVar = a;
        }
        return jVar;
    }

    public List<String> a(boolean z) {
        List<PackageInfo> a = a(0);
        List<String> arrayList = new ArrayList();
        if (a != null && a.size() > 0) {
            for (PackageInfo packageInfo : a) {
                if (z || Commons.isUserApp(packageInfo.applicationInfo)) {
                    arrayList.add(packageInfo.packageName);
                }
            }
        }
        return arrayList;
    }

    private List<PackageInfo> a(int i) {
        try {
            synchronized (this.e) {
                if (this.d == null) {
                    this.d = this.c.getInstalledPackages(i);
                }
            }
        } catch (Exception e) {
        }
        return this.d;
    }

    public void a(String str) {
        synchronized (this.e) {
            if (this.d != null) {
                for (int i = 0; i < this.d.size(); i++) {
                    if (((PackageInfo) this.d.get(i)).packageName.equals(str)) {
                        this.d.remove(i);
                        break;
                    }
                }
            }
        }
    }

    public void a(String str, Context context) {
        try {
            synchronized (this.e) {
                if (this.d != null) {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
                    for (int i = 0; i < this.d.size(); i++) {
                        if (((PackageInfo) this.d.get(i)).packageName.equals(str)) {
                            this.d.remove(i);
                            break;
                        }
                    }
                    this.d.add(packageInfo);
                }
            }
        } catch (Exception e) {
        }
    }
}
