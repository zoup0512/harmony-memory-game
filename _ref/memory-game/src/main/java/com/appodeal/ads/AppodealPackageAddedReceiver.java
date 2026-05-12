package com.appodeal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.appodeal.ads.t.c;
import com.appodeal.ads.utils.n;

public class AppodealPackageAddedReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                String packageName = getPackageName(intent.getData().toString());
                if (packageName != null && n.a(context, packageName)) {
                    new c(context, -1, "install").a(packageName).a().a();
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    private String getPackageName(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length == 2) {
            return split[1];
        }
        return null;
    }
}
