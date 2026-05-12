package com.my.target.core.providers;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.widget.AutoScrollHelper;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.facebook.places.model.PlaceFields;
import com.my.target.Tracer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: EnvironmentParamsDataProvider */
public final class c extends a {
    private boolean a = true;
    private boolean b = true;

    /* compiled from: EnvironmentParamsDataProvider */
    private static class a {
        public GsmCellLocation a;
        public String b;

        public a(Context context) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
            CellLocation cellLocation = null;
            try {
                if (c.b(context)) {
                    cellLocation = telephonyManager.getCellLocation();
                }
                if (cellLocation != null && (cellLocation instanceof GsmCellLocation)) {
                    this.a = (GsmCellLocation) cellLocation;
                    this.b = telephonyManager.getNetworkOperator();
                }
            } catch (SecurityException e) {
                Tracer.d("No permissions for access to coarse state");
            }
        }
    }

    /* compiled from: EnvironmentParamsDataProvider */
    private static class b {
        public WifiInfo a;
        public List<ScanResult> b;

        public b(Context context) {
            try {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager.isWifiEnabled()) {
                    this.a = wifiManager.getConnectionInfo();
                    if (c.b(context)) {
                        this.b = wifiManager.getScanResults();
                    }
                    if (this.b != null) {
                        Collections.sort(this.b, new Comparator<ScanResult>(this) {
                            final /* synthetic */ b a;

                            {
                                this.a = r1;
                            }

                            public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
                                ScanResult scanResult = (ScanResult) obj;
                                ScanResult scanResult2 = (ScanResult) obj2;
                                if (scanResult.level < scanResult2.level) {
                                    return 1;
                                }
                                if (scanResult.level > scanResult2.level) {
                                    return -1;
                                }
                                return 0;
                            }
                        });
                    }
                }
            } catch (SecurityException e) {
                Tracer.d("No permissions for access to wifi state");
            }
        }
    }

    public final void a(boolean z) {
        this.b = z;
    }

    public final void b(boolean z) {
        this.a = z;
    }

    public final synchronized void a(Context context) {
        String str = null;
        int i = -1;
        synchronized (this) {
            removeAll();
            if (this.a) {
                if (a("android.permission.ACCESS_FINE_LOCATION", context) || a("android.permission.ACCESS_COARSE_LOCATION", context)) {
                    float f = AutoScrollHelper.NO_MAX;
                    long j = 0;
                    LocationManager locationManager = (LocationManager) context.getSystemService("location");
                    Location location = null;
                    for (String str2 : locationManager.getAllProviders()) {
                        try {
                            Location location2;
                            float f2;
                            long j2;
                            Location lastKnownLocation = locationManager.getLastKnownLocation(str2);
                            if (lastKnownLocation != null) {
                                Tracer.d("locationProvider: " + str2);
                                float accuracy = lastKnownLocation.getAccuracy();
                                long time = lastKnownLocation.getTime();
                                if (location == null || (time > j && accuracy < f)) {
                                    location2 = lastKnownLocation;
                                    f2 = accuracy;
                                    j2 = time;
                                    j = j2;
                                    f = f2;
                                    location = location2;
                                    str = str2;
                                }
                            }
                            String str22 = str;
                            location2 = location;
                            f2 = f;
                            j2 = j;
                            j = j2;
                            f = f2;
                            location = location2;
                            str = str22;
                        } catch (SecurityException e) {
                            Tracer.d("No permissions for get geo data");
                        }
                    }
                    if (location != null) {
                        addParam("location", location.getLatitude() + "," + location.getLongitude() + "," + location.getAccuracy() + "," + location.getSpeed() + "," + (j / 1000));
                        addParam("location_provider", str);
                        Tracer.d("location: " + location.getLatitude() + ", " + location.getLongitude() + " accuracy = " + location.getAccuracy() + " speed = " + location.getSpeed() + " time = " + (j / 1000) + "  provider: " + str);
                    }
                }
                if (this.b) {
                    if (a("android.permission.ACCESS_WIFI_STATE", context)) {
                        c(context);
                    }
                    if (a("android.permission.ACCESS_COARSE_LOCATION", context)) {
                        a aVar = new a(context);
                        str = "gsm";
                        if (aVar.a != null) {
                            int i2;
                            if (TextUtils.isEmpty(aVar.b)) {
                                i2 = i;
                            } else {
                                try {
                                    i2 = Integer.parseInt(aVar.b.substring(0, 3));
                                    try {
                                        i = Integer.parseInt(aVar.b.substring(3));
                                    } catch (Exception e2) {
                                    }
                                } catch (Exception e3) {
                                    i2 = i;
                                }
                            }
                            addParam("cell", str + "," + aVar.a.getCid() + "," + aVar.a.getLac() + "," + i2 + "," + i);
                            Tracer.d("cell: " + str + "," + aVar.a.getCid() + "," + aVar.a.getLac() + "," + i2 + "," + i);
                        }
                    }
                }
            }
        }
    }

    private void c(Context context) {
        b bVar = new b(context);
        if (bVar.a != null) {
            WifiInfo wifiInfo = bVar.a;
            String bssid = wifiInfo.getBSSID();
            if (bssid == null) {
                bssid = "";
            }
            int linkSpeed = wifiInfo.getLinkSpeed();
            int networkId = wifiInfo.getNetworkId();
            int rssi = wifiInfo.getRssi();
            String ssid = wifiInfo.getSSID();
            if (ssid == null) {
                ssid = "";
            }
            addParam("wifi", bssid + "," + ssid + "," + rssi + "," + networkId + "," + linkSpeed);
            Tracer.d("mac: " + wifiInfo.getMacAddress());
            Tracer.d("ip: " + wifiInfo.getIpAddress());
            Tracer.d("wifi: " + bssid + "," + ssid + "," + rssi + "," + networkId + "," + linkSpeed);
        }
        if (bVar.b != null) {
            int i = 1;
            for (ScanResult scanResult : bVar.b) {
                int i2;
                if (i < 6) {
                    Tracer.d(scanResult.level);
                    String str = scanResult.BSSID;
                    if (str == null) {
                        str = "";
                    }
                    String str2 = scanResult.SSID;
                    if (str2 == null) {
                        str2 = "";
                    }
                    addParam("wifi" + i, str + "," + str2 + "," + scanResult.level);
                    Tracer.d("wifi" + i + ": " + str + "," + str2 + "," + scanResult.level);
                    i2 = i + 1;
                } else {
                    i2 = i;
                }
                i = i2;
            }
        }
    }

    private static boolean a(String str, Context context) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    static /* synthetic */ boolean b(Context context) {
        return a("android.permission.ACCESS_FINE_LOCATION", context) && a("android.permission.ACCESS_COARSE_LOCATION", context);
    }
}
