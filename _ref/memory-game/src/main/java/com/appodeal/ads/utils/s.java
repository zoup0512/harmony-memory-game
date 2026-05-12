package com.appodeal.ads.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import com.appodeal.ads.Appodeal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;

public class s {
    private static final HashMap<String, String> a = new HashMap<String, String>(3) {
    };

    public static JSONArray a(Context context) {
        JSONArray jSONArray = new JSONArray();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4100);
            for (String put : a(packageInfo)) {
                jSONArray.put(put);
            }
            if (b(packageInfo)) {
                jSONArray.put("S");
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        if (jSONArray.length() == 0) {
            return null;
        }
        return jSONArray;
    }

    private static List<String> a(PackageInfo packageInfo) {
        List<String> arrayList = new ArrayList();
        try {
            if (packageInfo.requestedPermissions != null) {
                for (Object obj : packageInfo.requestedPermissions) {
                    if (a.containsKey(obj)) {
                        arrayList.add(a.get(obj));
                    }
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        return arrayList;
    }

    private static boolean b(PackageInfo packageInfo) {
        try {
            if (packageInfo.services == null) {
                return false;
            }
            for (ServiceInfo serviceInfo : packageInfo.services) {
                if (!serviceInfo.name.equals("com.yandex.metrica.MetricaService")) {
                    return true;
                }
            }
            return false;
        } catch (Throwable e) {
            Appodeal.a(e);
            return false;
        }
    }
}
