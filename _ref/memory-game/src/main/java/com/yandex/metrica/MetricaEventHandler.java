package com.yandex.metrica;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.yandex.metrica.impl.be;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.utils.f;
import java.util.HashSet;
import java.util.Set;

public final class MetricaEventHandler extends BroadcastReceiver {
    public static final Set<BroadcastReceiver> a = new HashSet();

    public void onReceive(Context context, Intent intent) {
        if ("com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("referrer");
            if (!be.a(stringExtra)) {
                bk.a(context);
                bk.b().d(stringExtra);
            }
        }
        for (BroadcastReceiver broadcastReceiver : a) {
            f.e().a(String.format("Sending referrer to %s", new Object[]{broadcastReceiver.getClass().getName()}), new Object[0]);
            broadcastReceiver.onReceive(context, intent);
        }
    }

    static void a(BroadcastReceiver... broadcastReceiverArr) {
        for (Object add : broadcastReceiverArr) {
            a.add(add);
        }
    }
}
