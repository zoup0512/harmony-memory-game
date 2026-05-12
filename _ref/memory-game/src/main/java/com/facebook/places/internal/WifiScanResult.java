package com.facebook.places.internal;

public class WifiScanResult {
    public String bssid;
    public int frequency;
    public int rssi;
    public String ssid;

    public WifiScanResult(String ssid, String bssid, int rssi, int frequency) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.rssi = rssi;
        this.frequency = frequency;
    }
}
