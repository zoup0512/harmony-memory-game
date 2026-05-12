package com.cmcm.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import com.cmcm.adsdk.nativead.PicksViewCheckHelper;
import java.util.ArrayList;
import java.util.List;

public class ReceiverUtils extends BroadcastReceiver {
    public static List<PicksViewCheckHelper> a = new ArrayList();
    private static ReceiverUtils b;

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Uri data;
        if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
            data = intent.getData();
            j.a().a(data != null ? data.getSchemeSpecificPart() : "", context);
        } else if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
            data = intent.getData();
            j.a().a(data != null ? data.getSchemeSpecificPart() : "");
        } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
            for (PicksViewCheckHelper onScreenOn : a) {
                onScreenOn.onScreenOn();
            }
        } else if ("android.intent.action.SCREEN_ON".equals(action)) {
            for (PicksViewCheckHelper onScreenOn2 : a) {
                onScreenOn2.onScreenOff();
            }
        }
    }

    public static void a(Context context) {
        if (context != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addDataScheme("package");
            if (b == null) {
                b = new ReceiverUtils();
            }
            context.registerReceiver(b, intentFilter);
        }
    }
}
