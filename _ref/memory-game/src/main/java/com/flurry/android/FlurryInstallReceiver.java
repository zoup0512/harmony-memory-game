package com.flurry.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.flurry.sdk.hs;
import com.flurry.sdk.km;
import com.flurry.sdk.ly;

public final class FlurryInstallReceiver extends BroadcastReceiver {
    private static final String a = FlurryInstallReceiver.class.getSimpleName();

    public final void onReceive(Context context, Intent intent) {
        km.a(4, a, "Received an Install notification of " + intent.getAction());
        String string = intent.getExtras().getString("referrer");
        km.a(4, a, "Received an Install referrer of " + string);
        if (string == null || !"com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            km.a(5, a, "referrer is null");
            return;
        }
        if (!string.contains("=")) {
            km.a(4, a, "referrer is before decoding: " + string);
            string = ly.h(string);
            km.a(4, a, "referrer is: " + string);
        }
        new hs(context).a(string);
    }
}
