package com.yandex.metrica;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ServiceInfo;
import android.location.Location;
import com.yandex.metrica.impl.al;
import com.yandex.metrica.impl.ba;
import com.yandex.metrica.impl.ba.a;
import com.yandex.metrica.impl.bg;
import com.yandex.metrica.impl.bj;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.interact.CellularNetworkInfo;
import com.yandex.metrica.impl.interact.DeviceInfo;
import com.yandex.metrica.impl.y;
import java.util.List;
import java.util.Map;

public final class p {
    public static void rolu(Context context, Object registrant) {
        y.a(context).a(registrant);
    }

    public static void urolu(Context context, Object registrant) {
        y.a(context).b(registrant);
    }

    public static Location glkl(Context context) {
        return y.a(context).d();
    }

    public static String u(String sdkName) {
        return bg.a(sdkName);
    }

    public static Boolean plat() {
        return bg.a();
    }

    public static boolean iifa() {
        return bj.a();
    }

    public static String pgai() {
        return bj.b();
    }

    public static Integer gbc(Context context) {
        Intent registerReceiver = context.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return null;
        }
        int intExtra = registerReceiver.getIntExtra("level", -1);
        int intExtra2 = registerReceiver.getIntExtra("scale", -1);
        return (intExtra < 0 || intExtra2 <= 0) ? null : Integer.valueOf(Math.round((((float) intExtra) / ((float) intExtra2)) * 100.0f));
    }

    public static String gmsvn(int apiLevel) {
        return al.a(apiLevel);
    }

    public static YandexMetricaConfig cpcwh(YandexMetricaConfig config, String h) {
        return e.b(config).d(h).b();
    }

    public static void a(IIdentifierCallback callback) {
        bk.b().a(callback);
    }

    public static DeviceInfo gdi(Context context) {
        return DeviceInfo.getInstance(context);
    }

    public static String gcni(Context context) {
        return new CellularNetworkInfo(context).getCelluralInfo();
    }

    public static String guid() {
        return bk.b().e();
    }

    public static String mpn(Context context) {
        List b = ba.b(context);
        ServiceInfo serviceInfo = ((a) b.get(b.size() - 1)).d;
        return new ComponentName(serviceInfo.packageName, serviceInfo.name).getPackageName();
    }

    public static void rce(int type, String name, String value, Map<String, String> environment) {
        bk.b().a(type, name, value, environment);
    }
}
