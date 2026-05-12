package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.customtabs.CustomTabsService;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class zzaqa {
    private static String bkw;

    public static String zzex(Context context) {
        if (bkw != null) {
            return bkw;
        }
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
        Object obj = resolveActivity != null ? resolveActivity.activityInfo.packageName : null;
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        List arrayList = new ArrayList();
        for (ResolveInfo resolveActivity2 : queryIntentActivities) {
            Intent intent2 = new Intent();
            intent2.setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
            intent2.setPackage(resolveActivity2.activityInfo.packageName);
            if (packageManager.resolveService(intent2, 0) != null) {
                arrayList.add(resolveActivity2.activityInfo.packageName);
            }
        }
        if (arrayList.isEmpty()) {
            bkw = null;
        } else if (arrayList.size() == 1) {
            bkw = (String) arrayList.get(0);
        } else if (!TextUtils.isEmpty(obj) && !zzq(context, intent) && arrayList.contains(obj)) {
            bkw = obj;
        } else if (arrayList.contains("com.android.chrome")) {
            bkw = "com.android.chrome";
        } else if (arrayList.contains("com.chrome.beta")) {
            bkw = "com.chrome.beta";
        } else if (arrayList.contains("com.chrome.dev")) {
            bkw = "com.chrome.dev";
        } else if (arrayList.contains("com.google.android.apps.chrome")) {
            bkw = "com.google.android.apps.chrome";
        }
        return bkw;
    }

    private static boolean zzq(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 64);
            if (queryIntentActivities == null || queryIntentActivities.size() == 0) {
                return false;
            }
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                IntentFilter intentFilter = resolveInfo.filter;
                if (intentFilter != null && intentFilter.countDataAuthorities() != 0 && intentFilter.countDataPaths() != 0 && resolveInfo.activityInfo != null) {
                    return true;
                }
            }
            return false;
        } catch (RuntimeException e) {
            Log.e("CustomTabsHelper", "Runtime exception while getting specialized handlers");
        }
    }
}
