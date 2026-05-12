package com.yandex.metrica.impl;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

final class bi implements d {
    private static volatile bi b;
    private static final Object c = new Object();
    private final WifiManager a;
    private com.yandex.metrica.impl.d.a<JSONArray> d = new com.yandex.metrica.impl.d.a();
    private com.yandex.metrica.impl.d.a<List<a>> e = new com.yandex.metrica.impl.d.a();

    public static final class a {
        public final String a;
        public final String b;

        public a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }
    }

    private bi(Context context) {
        this.a = (WifiManager) context.getSystemService("wifi");
    }

    static bi a(Context context) {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new bi(context.getApplicationContext());
                }
            }
        }
        return b;
    }

    synchronized JSONArray a() {
        JSONArray jSONArray;
        if (d()) {
            if (this.d.c() || this.d.d()) {
                this.d.a(c());
            }
            jSONArray = (JSONArray) this.d.b();
        } else {
            jSONArray = new JSONArray();
        }
        return jSONArray;
    }

    private JSONArray c() {
        try {
            Object bssid;
            List<ScanResult> scanResults = this.a.getScanResults();
            JSONArray jSONArray = new JSONArray();
            WifiInfo connectionInfo = this.a.getConnectionInfo();
            if (connectionInfo != null) {
                bssid = connectionInfo.getBSSID();
            } else {
                bssid = null;
            }
            for (ScanResult scanResult : scanResults) {
                if (scanResult != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("mac", scanResult.BSSID.toUpperCase(Locale.US).replace(":", ""));
                    jSONObject.put("signal_strength", scanResult.level);
                    jSONObject.put("ssid", scanResult.SSID);
                    jSONObject.put("is_connected", scanResult.BSSID.equals(bssid));
                    jSONArray.put(jSONObject);
                }
            }
            return jSONArray;
        } catch (Exception e) {
            return new JSONArray();
        }
    }

    private boolean d() {
        try {
            return this.a.isWifiEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public List<a> b() {
        if (this.e.c() || this.e.d()) {
            List arrayList = new ArrayList();
            a(arrayList);
            this.e.a(arrayList);
        }
        return (List) this.e.b();
    }

    private static void a(List<a> list) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (hardwareAddress != null) {
                    for (byte b : hardwareAddress) {
                        stringBuilder.append(String.format(Locale.US, "%02X:", new Object[]{Byte.valueOf(b)}));
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                        list.add(new a(networkInterface.getName(), stringBuilder.toString()));
                        stringBuilder.setLength(0);
                    }
                }
            }
        } catch (Throwable th) {
        }
    }
}
